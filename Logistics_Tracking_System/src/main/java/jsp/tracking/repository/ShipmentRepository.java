package jsp.tracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.Shipment;
import jsp.tracking.entity.TrackingHistory;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

	Optional<TrackingHistory> findByTrackingNumber(Integer trackNum);

}
