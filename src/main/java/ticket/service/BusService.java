package ticket.service;



import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.entity.Bus;
import ticket.entity.Train;
import ticket.repository.BusRepository;


@Service
public class BusService {

    @Autowired
    BusRepository busRepository;



	public void save(Bus ticket) {
		busRepository.save(ticket);
	}

	public void delete(Bus ticket) {
		busRepository.delete(ticket);
	}
	
	public void deleteById(int id) { 
		busRepository.deleteById(id);    
	}

	public List<Bus> findAll() {
		List<Bus> allTicket = busRepository.findAll();
		return allTicket;
	}

	public Bus findById(int id) {	
		return busRepository.findById(id);
	}
	
	 public List<Bus>  findByStatus(String status){
	        return busRepository.findByStatus(status);
	    }

	 public List<Bus> findByFilters(String departureLocation, String arrivalLocation, String date, String status) {
		 
	        if (departureLocation != null && arrivalLocation != null && date != null) {
	        	
	        	List<Bus> a = busRepository.findByDepartureAndArrivalAndDepartureDateAndStatus(departureLocation, arrivalLocation, date, status);
	      return a;
	    		  } else if (departureLocation != null && arrivalLocation != null) {
	            return busRepository.findByDepartureAndArrivalAndStatus(departureLocation, arrivalLocation, status);
	        } else if (departureLocation != null && date != null) {
	            return busRepository.findByDepartureAndDepartureDateAndStatus(departureLocation, date, status);
	        } else if (arrivalLocation != null && date != null) {
	            return busRepository.findByArrivalAndDepartureDateAndStatus(arrivalLocation, date, status);
	        } else if (departureLocation != null) {
	            return busRepository.findByDepartureAndStatus(departureLocation, status);
	        } else if (arrivalLocation != null) {
	            return busRepository.findByArrivalAndStatus(arrivalLocation, status);
	        } else if (date != null) {
	            return busRepository.findByDepartureDateAndStatus(date, status);
	        } else {
	            // Se nessun filtro è fornito, restituisci una lista vuota
	            return Collections.emptyList();
	        }
	    }
	 
	 public static String formatDuration(Duration duration) {
//	        long days = duration.toDays();
//	        duration = duration.minusDays(days);
	        long hours = duration.toHours();
	        duration = duration.minusHours(hours);
	        long minutes = duration.toMinutes();
	        
	        return String.format("%dh %d", hours, minutes);
	    }
}

