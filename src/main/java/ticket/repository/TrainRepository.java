package ticket.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ticket.entity.Airplane;
import ticket.entity.Train;



@Repository
public interface TrainRepository extends JpaRepository<Train,Integer> {

    public abstract Train findById(int id);
    public abstract List<Train> findByStatus(String status);
   
	public abstract List<Train> findByDepartureAndStatus(String departureLocation, String status);

	public abstract List<Train> findByArrivalAndStatus(String arrivalLocation, String status);

	public abstract List<Train> findByDepartureAndArrivalAndStatus(String departureLocation, String arrivalLocation, String status);

	@Query(value ="SELECT * FROM ticket_train a WHERE DATE(a.departure_date) = :date AND a.status = :status", nativeQuery = true)
    List<Train> findByDepartureDateAndStatus(@Param("date") String date,@Param("status") String status);

	@Query(value ="SELECT * FROM ticket_train a WHERE DATE(a.departure_date) = :date AND a.arrival = :arrivalLocation AND a.status = :status", nativeQuery = true)
    List<Train> findByArrivalAndDepartureDateAndStatus(@Param("arrivalLocation") String arrivalLocation, @Param("date") String date,@Param("status") String status);

	@Query(value = "SELECT * FROM ticket_train a WHERE DATE(a.departure_date) = :date AND a.departure = :departureLocation AND a.status = :status", nativeQuery = true)
	List<Train> findByDepartureAndDepartureDateAndStatus(@Param("departureLocation") String departureLocation, @Param("date") String date,@Param("status") String status);

	@Query(value ="SELECT * FROM ticket_train a WHERE DATE(a.departure_date) = :date AND a.departure = :departureLocation AND a.arrival = :arrivalLocation AND a.status = :status", nativeQuery = true)
    List<Train> findByDepartureAndArrivalAndDepartureDateAndStatus(@Param("departureLocation") String departureLocation, @Param("arrivalLocation") String arrivalLocation, @Param("date") String date, @Param("status") String status);
    

}
