package jsp.tracking.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.dto.Status;
import jsp.tracking.dto.TrackingHistoryDto;
import jsp.tracking.entity.TrackingHistory;
import jsp.tracking.exception.IdNotFoundException;
import jsp.tracking.exception.ResourceNotFoundException;
import jsp.tracking.repository.ShipmentRepository;
import jsp.tracking.repository.TrackingHistoryRepository;

@Service
public class TrackingHistoryService {
	
	@Autowired
	private TrackingHistoryRepository historyRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;

	public @Nullable ResponseStructure<List<TrackingHistory>> getAllTrackingHistory() {
		List<TrackingHistory> histories=historyRepository.findAll();
		
		ResponseStructure<List<TrackingHistory>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(histories.size()+" Tracking Histories Found");
		res.setData(histories);
		return res;
	}

	public @Nullable ResponseStructure<TrackingHistory> getTrackingHistoryById(Integer id) {
		TrackingHistory history=historyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does not Exist"));
		
		ResponseStructure<TrackingHistory> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Tracking History Found");
		res.setData(history);
		return res;
	}

	public @Nullable ResponseStructure<List<TrackingHistory>> getTrackingHistoryByNumber(Integer trackNum) {
		shipmentRepository.findByTrackingNumber(trackNum).orElseThrow(()-> new ResourceNotFoundException("No Shipment Found with Tracking Number : "+trackNum));
		List<TrackingHistory> histories=historyRepository.findByShipment_TrackingNumber(trackNum);
		
		if(histories.isEmpty()) throw new ResourceNotFoundException("No Tracking History Found for Tracking Number : "+trackNum);
		
		ResponseStructure<List<TrackingHistory>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(histories.size()+" Tracking Records Found");
		res.setData(histories);
		return res;
		
		
	}

	public @Nullable ResponseStructure<List<TrackingHistory>> getTrackingHistoryByStatus(Status status) {
		List<TrackingHistory> histories=historyRepository.findByStatus(status);
		if(histories.isEmpty()) throw new ResourceNotFoundException("No Tracking History Found which is in "+status);
		
		ResponseStructure<List<TrackingHistory>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(histories.size()+" Tracking Records Found");
		res.setData(histories);
		return res;
	}

	public @Nullable ResponseStructure<List<TrackingHistory>> getTrackingHistoryByShipment(Integer id) {
		shipmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Shipment Found with Id: " +id));
		List<TrackingHistory> histories=historyRepository.findByShipment_Id(id);
		if(histories.isEmpty()) throw new ResourceNotFoundException("No Tracking History Found for Shipment Id: " +id);
		
		ResponseStructure<List<TrackingHistory>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(histories.size()+" Tracking Records Found");
		res.setData(histories);
		return res;
	}

	public @Nullable ResponseStructure<TrackingHistory> updateTrackingHistory(TrackingHistoryDto historyDto) {
		TrackingHistory trackingHistory = historyRepository.findById(historyDto.getId()).orElseThrow(() -> new IdNotFoundException("No Tracking History Found with Id: " + historyDto.getId()));

	    trackingHistory.setLocation(historyDto.getLocation());
	    trackingHistory.setRemark(historyDto.getRemark());
	    trackingHistory.setStatus(historyDto.getStatus());
	    
	    ResponseStructure<TrackingHistory> res=new ResponseStructure<>();
	    res.setStatusCode(HttpStatus.OK.value());
	    res.setMessage("Tracking History Updated Successfully");
	    res.setData(historyRepository.save(trackingHistory));
	    return res;
	}
	
	

}
