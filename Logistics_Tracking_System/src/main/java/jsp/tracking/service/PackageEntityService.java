package jsp.tracking.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.entity.PackageEntity;
import jsp.tracking.exception.IdNotFoundException;
import jsp.tracking.exception.ResourceNotFoundException;
import jsp.tracking.repository.PackageEntityRepository;
import jsp.tracking.repository.ShipmentRepository;

@Service
public class PackageEntityService {

	@Autowired
	private PackageEntityRepository packageRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	public @Nullable ResponseStructure<List<PackageEntity>> getAllPackaging() {
		List<PackageEntity> packages=packageRepository.findAll();
		if(packages.isEmpty()) throw new ResourceNotFoundException("No Package Record Available");
		
		ResponseStructure<List<PackageEntity>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(packages.size()+" Package Record Found");
		res.setData(packages);
		return res;
	}

	public @Nullable ResponseStructure<PackageEntity> getPackagingById(Integer id) {
		PackageEntity packageEntity=packageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does not Exist"));
		
		ResponseStructure<PackageEntity> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Package Record Found");
		res.setData(packageEntity);
		return null;
	}

	public @Nullable ResponseStructure<PackageEntity> getPackagingByShipment(Integer id) {
		shipmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Shipment Record Found"));
		
		PackageEntity packageEntity=packageRepository.findByShipment_id(id).orElseThrow(()-> new ResourceNotFoundException("No Package Found for Shipment Id: " +id));
		ResponseStructure<PackageEntity> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Package Record Found");
		res.setData(packageEntity);
		return res;
	}

	public @Nullable ResponseStructure<PackageEntity> updatePackage(PackageEntity packageEntity) {
		PackageEntity packageEntity2=packageRepository.findById(packageEntity.getId()).orElseThrow(()-> new IdNotFoundException("Id Must be Passed to Update an Record"));
		
		 	packageEntity2.setPackageType(packageEntity.getPackageType());
		    packageEntity2.setFragile(packageEntity.getFragile());
		    packageEntity2.setDimension(packageEntity.getDimension());
		    
		    ResponseStructure<PackageEntity> res=new ResponseStructure<>();
		    res.setStatusCode(HttpStatus.OK.value());
		    res.setMessage("Package Record Updated");
		    res.setData(packageEntity2);
		    return res;
	}

}
