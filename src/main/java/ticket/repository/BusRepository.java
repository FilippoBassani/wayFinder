package ticket.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticket.entity.Bus;



@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {

    public abstract Bus findById(int id);
    public abstract List<Bus> findByStatus(String status);
    

}
