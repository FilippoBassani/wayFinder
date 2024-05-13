package ticket.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ticket.entity.Airplane;



@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Integer> {

    public abstract Airplane findById(int id);
    
    public abstract List<Airplane> findByStatus(String status);
   
	public abstract List<Airplane> findByDepartureAndStatus(String departureLocation, String status);

	public abstract List<Airplane> findByArrivalAndStatus(String arrivalLocation, String status);

	public abstract List<Airplane> findByDepartureAndArrivalAndStatus(String departureLocation, String arrivalLocation, String status);

	@Query(value ="SELECT * FROM ticket_airplane a WHERE DATE(a.departure_date) = :date AND a.status = :status", nativeQuery = true)
    List<Airplane> findByDepartureDateAndStatus(@Param("date") String date,@Param("status") String status);

	@Query(value ="SELECT * FROM ticket_airplane a WHERE DATE(a.departure_date) = :date AND a.arrival = :arrivalLocation AND a.status = :status", nativeQuery = true)
    List<Airplane> findByArrivalAndDepartureDateAndStatus(@Param("arrivalLocation") String arrivalLocation, @Param("date") String date,@Param("status") String status);

	@Query(value = "SELECT * FROM ticket_airplane a WHERE DATE(a.departure_date) = :date AND a.departure = :departureLocation AND a.status = :status", nativeQuery = true)
	List<Airplane> findByDepartureAndDepartureDateAndStatus(@Param("departureLocation") String departureLocation, @Param("date") String date,@Param("status") String status);

	@Query(value ="SELECT * FROM ticket_airplane a WHERE DATE(a.departure_date) = :date AND a.departure = :departureLocation AND a.arrival = :arrivalLocation AND a.status = :status", nativeQuery = true)
    List<Airplane> findByDepartureAndArrivalAndDepartureDateAndStatus(@Param("departureLocation") String departureLocation, @Param("arrivalLocation") String arrivalLocation, @Param("date") String date, @Param("status") String status);

}
