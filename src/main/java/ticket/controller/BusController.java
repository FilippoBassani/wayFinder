package ticket.controller;




import ticket.entity.Bus;
import ticket.service.BusService;

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
	  public  ResponseEntity<List<Bus>> ticket() {   
          String status = "For Sale";
	      List<Bus> ticket = busService.findByStatus(status);
   
	      return ResponseEntity.ok(ticket); 
	  }
    
    @GetMapping(value = "/{id}")
	  public  ResponseEntity<Bus> ticket(@PathVariable(name = "id") Integer id) {      
    	Bus ticket = busService.findById(id);
   
	      return ResponseEntity.ok(ticket); 
	  }
    
    @PostMapping(value = "/save-ticket")
    public ResponseEntity<Bus> saveTicket(@RequestBody Bus ticket) {
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
               ticketBuy.setStatus("Saled");
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