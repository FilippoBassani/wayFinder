package ticket.service;



import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.entity.Airplane;
import ticket.entity.Train;
import ticket.repository.TrainRepository;



@Service
public class TrainService {

    @Autowired
    TrainRepository trainRepository;



	public void save(Train ticket) {
		trainRepository.save(ticket);
	}

	public void delete(Train ticket) {
		trainRepository.delete(ticket);
	}
	
	public void deleteById(int id) { 
		trainRepository.deleteById(id);    
	}

	public List<Train> findAll() {
		List<Train> allTicket = trainRepository.findAll();
		return allTicket;
	}

	public Train findById(int id) {	
		return trainRepository.findById(id);
	}
	
	 public List<Train>  findByStatus(String status){
	        return trainRepository.findByStatus(status);
	    }

	 public List<Train> findByFilters(String departureLocation, String arrivalLocation, String date, String status) {
		 
	        if (departureLocation != null && arrivalLocation != null && date != null) {
	        	
	        	List<Train> a = trainRepository.findByDepartureAndArrivalAndDepartureDateAndStatus(departureLocation, arrivalLocation, date, status);
	      return a;
	    		  } else if (departureLocation != null && arrivalLocation != null) {
	            return trainRepository.findByDepartureAndArrivalAndStatus(departureLocation, arrivalLocation, status);
	        } else if (departureLocation != null && date != null) {
	            return trainRepository.findByDepartureAndDepartureDateAndStatus(departureLocation, date, status);
	        } else if (arrivalLocation != null && date != null) {
	            return trainRepository.findByArrivalAndDepartureDateAndStatus(arrivalLocation, date, status);
	        } else if (departureLocation != null) {
	            return trainRepository.findByDepartureAndStatus(departureLocation, status);
	        } else if (arrivalLocation != null) {
	            return trainRepository.findByArrivalAndStatus(arrivalLocation, status);
	        } else if (date != null) {
	            return trainRepository.findByDepartureDateAndStatus(date, status);
	        } else {
	            // Se nessun filtro è fornito, restituisci una lista vuota
	            return Collections.emptyList();
	        }
	    }
}

