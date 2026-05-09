package jsp.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

}
