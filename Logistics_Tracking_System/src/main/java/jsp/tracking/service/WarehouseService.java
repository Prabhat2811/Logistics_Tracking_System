package jsp.tracking.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.entity.Warehouse;
import jsp.tracking.exception.IdNotFoundException;
import jsp.tracking.exception.ResourceNotFoundException;
import jsp.tracking.exception.RuleViolationException;
import jsp.tracking.repository.WarehouseRepository;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;
	
	public @Nullable ResponseStructure<Warehouse> createWarehouse(Warehouse warehouse) {
		ResponseStructure<Warehouse> res=new ResponseStructure<>();
		if(String.valueOf(warehouse.getContact()).length()!=10) throw new RuleViolationException("Invalid Input : Contact Number Should be 10 Digits");
		
		if(warehouseRepository.existsByContact(warehouse.getContact())) throw new RuleViolationException("Contact Number Already Exist");
		
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Warehouse Registered");
		res.setData(warehouseRepository.save(warehouse));
		return res;
		
	}

	public @Nullable ResponseStructure<List<Warehouse>> getAllWarehouse() {
		List<Warehouse> warehouses=warehouseRepository.findAll();
		if(warehouses.isEmpty()) throw new ResourceNotFoundException("No Warehouse Record Found");
		
		ResponseStructure<List<Warehouse>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(warehouses.size()+" Warehouse Record Found");
		res.setData(warehouses);
		return res;
	}

	public @Nullable ResponseStructure<Warehouse> getWarehouseById(Integer id) {
		Warehouse warehouse=warehouseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does Not Exist"));
		
		ResponseStructure<Warehouse> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Warehouse Record Found");
		res.setData(warehouse);
		return res;
	}

	public @Nullable ResponseStructure<Warehouse> getWarehouseByLocation(String location) {
		Warehouse warehouse=warehouseRepository.findByLocation(location).orElseThrow(()-> new ResourceNotFoundException("No Warehouse present in "+location));
		ResponseStructure<Warehouse> res=new ResponseStructure<>();
		
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Warehouse Record Found");
		res.setData(warehouse);
		return res;
	}

	public @Nullable ResponseStructure<List<Warehouse>> getWarehouseByCapacityGreaterThan(Integer cap) {
		List<Warehouse> warehouses=warehouseRepository.findByCapacityGreaterThan(cap);
		
		if(warehouses.isEmpty()) throw new ResourceNotFoundException("No Warehouse Record Found");
		
		ResponseStructure<List<Warehouse>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(warehouses.size()+" Warehouse Record Found");
		res.setData(warehouses);
		return res;
	}

	public @Nullable ResponseStructure<Warehouse> updateWarehouse(Warehouse warehouse) {
		if(warehouse.getId()==null) throw new IdNotFoundException("Id Must Be passed to update a Record");
		if(String.valueOf(warehouse.getContact()).length()!=10) throw new RuleViolationException("Invalid Input : Contact Number Must be 10 Digits");
		
		warehouseRepository.findById(warehouse.getId()).orElseThrow(()-> new ResourceNotFoundException("Id does not Exist"));
		
		ResponseStructure<Warehouse> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Warehouse Record Updated");
		res.setData(warehouseRepository.save(warehouse));
		return res;
		
	}

	public @Nullable ResponseStructure<String> deleteWarehouse(Integer id) {
		Warehouse warehouse=warehouseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does Not Exist"));
		if(warehouse.getShipments().size()>0) throw new RuleViolationException("Warehouse cannot be Removed since Shipment is Present");
		
		ResponseStructure<String> res=new ResponseStructure<>();
		warehouseRepository.delete(warehouse);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Warehouse Removed");
		res.setData("Warehouse Record Deleted with ID : "+id);
		return res;
	}

}
