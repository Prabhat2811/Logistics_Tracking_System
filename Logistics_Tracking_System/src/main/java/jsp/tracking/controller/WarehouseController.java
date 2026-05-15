package jsp.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.entity.Warehouse;
import jsp.tracking.service.WarehouseService;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
	
	@Autowired
	private WarehouseService warehouseService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Warehouse>> createWarehouse(@RequestBody Warehouse warehouse){
		return new ResponseEntity<ResponseStructure<Warehouse>>(warehouseService.createWarehouse(warehouse), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Warehouse>>> getAllWarehouse(){
		return new ResponseEntity<ResponseStructure<List<Warehouse>>>(warehouseService.getAllWarehouse(), HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Warehouse>> getWarehouseById(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<Warehouse>>(warehouseService.getWarehouseById(id), HttpStatus.FOUND);
	}
	
	@GetMapping("/location/{location}")
	public ResponseEntity<ResponseStructure<Warehouse>> getWarehouseByLocation(@PathVariable String location){
		return new ResponseEntity<ResponseStructure<Warehouse>>(warehouseService.getWarehouseByLocation(location), HttpStatus.FOUND);
	}
	
	@GetMapping("/capacity/{cap}")
	public ResponseEntity<ResponseStructure<List<Warehouse>>> getWarehouseByCapacityGreaterThan(@PathVariable Integer cap){
		return new ResponseEntity<ResponseStructure<List<Warehouse>>>(warehouseService.getWarehouseByCapacityGreaterThan(cap), HttpStatus.FOUND);
	}
	
	@PatchMapping
	public ResponseEntity<ResponseStructure<Warehouse>> updateWarehouse(@RequestBody Warehouse warehouse){
		return new ResponseEntity<ResponseStructure<Warehouse>>(warehouseService.updateWarehouse(warehouse), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteWarehouse(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<String>>(warehouseService.deleteWarehouse(id), HttpStatus.OK);
	}
}
