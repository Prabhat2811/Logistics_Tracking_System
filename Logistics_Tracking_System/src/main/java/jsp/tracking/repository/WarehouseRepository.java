package jsp.tracking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

	boolean existsByContact(Long contact);

	Optional<Warehouse> findByLocation(String location);

	List<Warehouse> findByCapacityGreaterThan(Integer cap);

}
