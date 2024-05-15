package ticket.controller;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ticket.entity.Airplane;
import ticket.entity.Train;
import ticket.service.TrainService;






@RestController
@RequestMapping("/train")
public class TrainController {
    @Autowired
    TrainService trainService;


    public TrainController() throws Exception {
        System.out.println("creo un oggetto AirplaneController");
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Train>> ticket(@RequestParam(required = false) String departureLocation,
                                                 @RequestParam(required = false) String arrivalLocation,
                                                 @RequestParam(required = false) String date) {   
        List<Train> ticket;

        // Se nessun filtro è fornito, restituisci tutti gli aerei in vendita
        if (departureLocation == null && arrivalLocation == null && date == null) {
            ticket = trainService.findByStatus("For Sale");
        } else {
        	String status = "For Sale";
            ticket = trainService.findByFilters(departureLocation, arrivalLocation, date, status);
        }

        return ResponseEntity.ok(ticket); 
    }
    
    @GetMapping(value = "/{id}")
	  public  ResponseEntity<Train> ticket(@PathVariable(name = "id") Integer id) {      
    	Train ticket = trainService.findById(id);
   
	      return ResponseEntity.ok(ticket); 
	  }
    
    @PostMapping(value = "/save-ticket")
    public ResponseEntity<Train> saveTicket(@RequestBody Train ticket) {
    	trainService.save(ticket);
        return ResponseEntity.ok(ticket);
    }
    
    @PutMapping(value = "/ticket-upd/{id}")
    public Train updateticket(@PathVariable(name = "id") Integer id, @RequestBody Train ticket) throws Exception {
    	Train ticketUpd = trainService.findById(id);
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
       trainService.save(ticketUpd);
        return ticketUpd;
    }
    
    @PutMapping(value = "/ticket-buy/{id}")
    public ResponseEntity<?> buyticket(@PathVariable(name = "id") Integer id, @RequestBody Train ticket) throws Exception {
       try {
    	   Train ticketBuy = trainService.findById(id);
           if (ticket.getName() != null && ticket.getSurname() != null) {
               ticketBuy.setName(ticket.getName());
               ticketBuy.setSurname(ticket.getSurname());
               ticketBuy.setStatus("Saled");
               trainService.save(ticketBuy);
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
    	trainService.deleteById(id);      
    }
}