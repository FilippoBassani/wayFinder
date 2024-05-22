package ticket.controller;




import ticket.entity.Airplane;
import ticket.service.AirplaneService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;






@RestController
@RequestMapping("/airplane")
public class AirplaneController {
    @Autowired
    AirplaneService airplaneService;


    public AirplaneController() throws Exception {
        System.out.println("creo un oggetto AirplaneController");
    }

//    @GetMapping(value = "")
//	  public  ResponseEntity<List<Airplane>> ticket() {   
//          String status = "For Sale";
//	      List<Airplane> ticket = airplaneService.findByStatus(status);
//   
//	      return ResponseEntity.ok(ticket); 
//	  }
    @GetMapping(value = "")
    public ResponseEntity<List<Airplane>> ticket(@RequestParam(required = false) String departureLocation,
                                                 @RequestParam(required = false) String arrivalLocation,
                                                 @RequestParam(required = false) String date) {   
        List<Airplane> ticket;

        // Se nessun filtro è fornito, restituisci tutti gli aerei in vendita
        if (departureLocation == null && arrivalLocation == null && date == null) {
            ticket = airplaneService.findByStatus("For Sale");
        } else {
        	String status = "For Sale";
            ticket = airplaneService.findByFilters(departureLocation, arrivalLocation, date, status);
        }

        return ResponseEntity.ok(ticket); 
    }
    
    @GetMapping(value = "/{id}")
	  public  ResponseEntity<Airplane> ticket(@PathVariable(name = "id") Integer id) {      
	      Airplane ticket = airplaneService.findById(id);
   
	      return ResponseEntity.ok(ticket); 
	  }
    
    @PostMapping(value = "/save-ticket")
    public ResponseEntity<Airplane> saveTicket(@RequestBody Airplane ticket) {
        airplaneService.save(ticket);
        return ResponseEntity.ok(ticket);
    }
    
    @PutMapping(value = "/ticket-upd/{id}")
    public Airplane updateticket(@PathVariable(name = "id") Integer id, @RequestBody Airplane ticket) throws Exception {
       Airplane ticketUpd = airplaneService.findById(id);
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
       airplaneService.save(ticketUpd);
        return ticketUpd;
    }
    
    @PutMapping(value = "/ticket-buy/{id}")
    public ResponseEntity<?> buyticket(@PathVariable(name = "id") Integer id, @RequestBody Airplane ticket) throws Exception {
       try {
           Airplane ticketBuy = airplaneService.findById(id);
           if (ticket.getName() != null && ticket.getSurname() != null) {
               ticketBuy.setName(ticket.getName());
               ticketBuy.setSurname(ticket.getSurname());
               ticketBuy.setStatus("Sold");
               airplaneService.save(ticketBuy);
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
            airplaneService.deleteById(id);      
    }
}