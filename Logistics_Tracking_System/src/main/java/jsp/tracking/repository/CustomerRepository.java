package jsp.tracking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.dto.ShipmentStatus;
import jsp.tracking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByContact(Long contact);

	boolean existsByIdAndShipmentsStatusIn(Integer id, List<ShipmentStatus> statuses);

	Optional<Customer> findByContact(Long contact);

}
