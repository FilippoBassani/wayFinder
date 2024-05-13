package ticket.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticket.entity.Train;



@Repository
public interface TrainRepository extends JpaRepository<Train,Integer> {

    public abstract Train findById(int id);
    public abstract List<Train> findByStatus(String status);
    

}
