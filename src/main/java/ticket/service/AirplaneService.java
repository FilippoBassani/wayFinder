package ticket.service;



import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.entity.Airplane;
import ticket.repository.AirplaneRepository;


@Service
public class AirplaneService {

    @Autowired
    AirplaneRepository airplaneRepository;



	public void save(Airplane ticket) {
		airplaneRepository.save(ticket);
	}

	public void delete(Airplane ticket) {
		airplaneRepository.delete(ticket);
	}
	
	public void deleteById(int id) { 
	    airplaneRepository.deleteById(id);    
	}

	public List<Airplane> findAll() {
		List<Airplane> allTicket = airplaneRepository.findAll();
		return allTicket;
	}

	public Airplane findById(int id) {	
		return airplaneRepository.findById(id);
	}
	
	 public List<Airplane>  findByStatus(String status){
	        return airplaneRepository.findByStatus(status);
	    }
	 
	 public List<Airplane> findByFilters(String departureLocation, String arrivalLocation, String date, String status) {
		 
	        if (departureLocation != null && arrivalLocation != null && date != null) {
	        	
	        	List<Airplane> a = airplaneRepository.findByDepartureAndArrivalAndDepartureDateAndStatus(departureLocation, arrivalLocation, date, status);
	      return a;
	    		  } else if (departureLocation != null && arrivalLocation != null) {
	            return airplaneRepository.findByDepartureAndArrivalAndStatus(departureLocation, arrivalLocation, status);
	        } else if (departureLocation != null && date != null) {
	            return airplaneRepository.findByDepartureAndDepartureDateAndStatus(departureLocation, date, status);
	        } else if (arrivalLocation != null && date != null) {
	            return airplaneRepository.findByArrivalAndDepartureDateAndStatus(arrivalLocation, date, status);
	        } else if (departureLocation != null) {
	            return airplaneRepository.findByDepartureAndStatus(departureLocation, status);
	        } else if (arrivalLocation != null) {
	            return airplaneRepository.findByArrivalAndStatus(arrivalLocation, status);
	        } else if (date != null) {
	            return airplaneRepository.findByDepartureDateAndStatus(date, status);
	        } else {
	            // Se nessun filtro è fornito, restituisci una lista vuota
	            return Collections.emptyList();
	        }
	    }

}

