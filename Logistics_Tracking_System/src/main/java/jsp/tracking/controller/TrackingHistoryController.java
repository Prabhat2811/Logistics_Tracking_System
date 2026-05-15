package jsp.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.dto.Status;
import jsp.tracking.dto.TrackingHistoryDto;
import jsp.tracking.entity.TrackingHistory;
import jsp.tracking.service.TrackingHistoryService;

@RestController
@RequestMapping("/api/trackhistory")
public class TrackingHistoryController {
	
	@Autowired
	private TrackingHistoryService trackingService;
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getAllTrackingHistory(){
		return new ResponseEntity<ResponseStructure<List<TrackingHistory>>>(trackingService.getAllTrackingHistory(), HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryById(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<TrackingHistory>>(trackingService.getTrackingHistoryById(id), HttpStatus.FOUND);
	}
	
	@GetMapping("/trackNum/{trackNum}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getTrackingHistoryByNumber(@PathVariable Integer trackNum){
		return new ResponseEntity<ResponseStructure<List<TrackingHistory>>>(trackingService.getTrackingHistoryByNumber(trackNum), HttpStatus.FOUND);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getTrackingHistoryByStatus(@PathVariable Status status){
		return new ResponseEntity<ResponseStructure<List<TrackingHistory>>>(trackingService.getTrackingHistoryByStatus(status), HttpStatus.FOUND);
	}
	
	@GetMapping("/shipment/{id}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getTrackingHistoryByShipment(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<List<TrackingHistory>>>(trackingService.getTrackingHistoryByShipment(id), HttpStatus.FOUND);
	}
	
	@PatchMapping
	public ResponseEntity<ResponseStructure<TrackingHistory>> updateTrackingHistory(@RequestBody TrackingHistoryDto historyDto){
		return new ResponseEntity<ResponseStructure<TrackingHistory>>(trackingService.updateTrackingHistory(historyDto), HttpStatus.OK);
	}
}
