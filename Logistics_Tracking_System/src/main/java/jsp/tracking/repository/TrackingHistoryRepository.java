package jsp.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.dto.Status;
import jsp.tracking.entity.TrackingHistory;

public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Integer>{

	List<TrackingHistory> findByShipment_TrackingNumber(Integer trackNum);

	List<TrackingHistory> findByStatus(Status status);

	List<TrackingHistory> findByShipment_Id(Integer id);

}
