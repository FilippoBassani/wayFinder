package ticket.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

