package ticket.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ticket.entity.Airplane;
import ticket.entity.Bus;



@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {

    public abstract Bus findById(int id);
    public abstract List<Bus> findByStatus(String status);
    public abstract List<Bus> findByDepartureAndStatus(String departureLocation, String status);

	public abstract List<Bus> findByArrivalAndStatus(String arrivalLocation, String status);

	public abstract List<Bus> findByDepartureAndArrivalAndStatus(String departureLocation, String arrivalLocation, String status);

	@Query(value ="SELECT * FROM ticket_bus a WHERE DATE(a.departure_date) = :date AND a.status = :status", nativeQuery = true)
    List<Bus> findByDepartureDateAndStatus(@Param("date") String date,@Param("status") String status);

	@Query(value ="SELECT * FROM ticket_bus a WHERE DATE(a.departure_date) = :date AND a.arrival = :arrivalLocation AND a.status = :status", nativeQuery = true)
    List<Bus> findByArrivalAndDepartureDateAndStatus(@Param("arrivalLocation") String arrivalLocation, @Param("date") String date,@Param("status") String status);

	@Query(value = "SELECT * FROM ticket_bus a WHERE DATE(a.departure_date) = :date AND a.departure = :departureLocation AND a.status = :status", nativeQuery = true)
	List<Bus> findByDepartureAndDepartureDateAndStatus(@Param("departureLocation") String departureLocation, @Param("date") String date,@Param("status") String status);

	@Query(value ="SELECT * FROM ticket_bus a WHERE DATE(a.departure_date) = :date AND a.departure = :departureLocation AND a.arrival = :arrivalLocation AND a.status = :status", nativeQuery = true)
    List<Bus> findByDepartureAndArrivalAndDepartureDateAndStatus(@Param("departureLocation") String departureLocation, @Param("arrivalLocation") String arrivalLocation, @Param("date") String date, @Param("status") String status);

    

}
