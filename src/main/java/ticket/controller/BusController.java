package ticket.controller;




import ticket.entity.Airplane;
import ticket.entity.Bus;
import ticket.service.BusService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;






@RestController
@RequestMapping("/bus")
public class BusController {
    @Autowired
    BusService busService;


    public BusController() throws Exception {
        System.out.println("creo un oggetto AirplaneController");
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Bus>> ticket(@RequestParam(required = false) String departureLocation,
                                                 @RequestParam(required = false) String arrivalLocation,
                                                 @RequestParam(required = false) String date) {   
        List<Bus> ticket;

        // Se nessun filtro è fornito, restituisci tutti gli aerei in vendita
        if (departureLocation == null && arrivalLocation == null && date == null) {
            ticket = busService.findByStatus("For Sale");
        } else {
        	String status = "For Sale";
            ticket = busService.findByFilters(departureLocation, arrivalLocation, date, status);
        }

        return ResponseEntity.ok(ticket); 
    }
    
    @GetMapping(value = "/{id}")
	  public  ResponseEntity<Bus> ticket(@PathVariable(name = "id") Integer id) {      
    	Bus ticket = busService.findById(id);
   
	      return ResponseEntity.ok(ticket); 
	  }
    
    @PostMapping(value = "/save-ticket")
    public ResponseEntity<Bus> saveTicket(@RequestBody Bus ticket) {
    	LocalDateTime departureDate = ticket.getDepartureDate();
    	LocalDateTime arrivalDate = ticket.getArrivalDate();
    	Duration duration = Duration.between(departureDate, arrivalDate);
    	String durationString = busService.formatDuration(duration);
    	ticket.setDuration(durationString);
    	busService.save(ticket);
        return ResponseEntity.ok(ticket);
    }
    
    @PutMapping(value = "/ticket-upd/{id}")
    public Bus updateticket(@PathVariable(name = "id") Integer id, @RequestBody Bus ticket) throws Exception {
    	Bus ticketUpd = busService.findById(id);
       if(ticket.getDeparture() != null){
    	   ticketUpd.setDeparture(ticket.getDeparture());
       }

       if(ticket.getArrival() != null){
    	   ticketUpd.setArrival(ticket.getArrival());
       }
       
       if(ticket.getDepartureDate() != null){
    	   ticketUpd.setDepartureDate(ticket.getDepartureDate());
       }
       
       if(ticket.getArrivalDate() != null){
    	   ticketUpd.setArrivalDate(ticket.getArrivalDate());
       }

       if(ticket.getPrice() != null){
    	   ticketUpd.setPrice(ticket.getPrice());
       }
       
       if(ticket.getCompany() != null){
    	   ticketUpd.setCompany(ticket.getCompany());
       }
       busService.save(ticketUpd);
        return ticketUpd;
    }
    
    @PutMapping(value = "/ticket-buy/{id}")
    public ResponseEntity<?> buyticket(@PathVariable(name = "id") Integer id, @RequestBody Bus ticket) throws Exception {
       try {
    	   Bus ticketBuy = busService.findById(id);
           if (ticket.getName() != null && ticket.getSurname() != null) {
               ticketBuy.setName(ticket.getName());
               ticketBuy.setSurname(ticket.getSurname());
               ticketBuy.setStatus("Sold");
               busService.save(ticketBuy);
               return ResponseEntity.ok(ticketBuy);
           } else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name and surname are required.");
           }
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
       }
    }
    @DeleteMapping(value = "/delete-ticket/{id}")
    public void deleteTicket(@PathVariable(name="id") Integer id) {
    	busService.deleteById(id);      
    }
}