package jsp.tracking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer> {

	boolean existsByContact(Long contact);

	boolean existsByVehicleNumber(String vehicleNumber);

	Optional<DeliveryAgent> findByVehicleNumber(String vehicle);

	Optional<DeliveryAgent> findByContact(Long contact);

	List<DeliveryAgent> findByRatingGreaterThan(double rating);

}
