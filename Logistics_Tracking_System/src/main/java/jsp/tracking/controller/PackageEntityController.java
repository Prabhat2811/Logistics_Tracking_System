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
import jsp.tracking.entity.PackageEntity;
import jsp.tracking.service.PackageEntityService;

@RestController
@RequestMapping("/api/package")
public class PackageEntityController {
	@Autowired
	private PackageEntityService packageService;
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<PackageEntity>>> getAllPackaging(){
		return new ResponseEntity<ResponseStructure<List<PackageEntity>>>(packageService.getAllPackaging(), HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<PackageEntity>> getPackagingById(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<PackageEntity>>(packageService.getPackagingById(id), HttpStatus.FOUND);
	}
	
	@GetMapping("/shipment/{id}")
	public ResponseEntity<ResponseStructure<PackageEntity>> getPackagingByShipment(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<PackageEntity>>(packageService.getPackagingByShipment(id), HttpStatus.FOUND);
	}
	
	@PatchMapping
	public ResponseEntity<ResponseStructure<PackageEntity>> updatePackage(@RequestBody PackageEntity packageEntity){
		return new ResponseEntity<ResponseStructure<PackageEntity>>(packageService.updatePackage(packageEntity), HttpStatus.FOUND);
	}
	
}
