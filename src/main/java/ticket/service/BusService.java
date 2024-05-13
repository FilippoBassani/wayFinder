package ticket.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.entity.Bus;
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

}

