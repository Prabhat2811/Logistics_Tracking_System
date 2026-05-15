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

import jsp.tracking.dto.DeliveryAgentDto;
import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.entity.DeliveryAgent;
import jsp.tracking.service.DeliveryAgentService;

@RestController
@RequestMapping("/api/deliveryagent")
public class DeliveryAgentController {
	
	@Autowired
	private DeliveryAgentService agentService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<DeliveryAgent>> createAgent(@RequestBody DeliveryAgent deliveryAgent){
		return new ResponseEntity<ResponseStructure<DeliveryAgent>>(agentService.createAgent(deliveryAgent), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAllAgent(){
		return new ResponseEntity<ResponseStructure<List<DeliveryAgent>>>(agentService.getAllAgent(), HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentById(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<DeliveryAgent>>(agentService.getAgentById(id), HttpStatus.FOUND);
	}
	
	@GetMapping("/vehicle/{vehicle}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentByVehicleNumber(@PathVariable String vehicle){
		return new ResponseEntity<ResponseStructure<DeliveryAgent>>(agentService.getAgentByVehicle(vehicle), HttpStatus.FOUND);
	}

	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentByContact(@PathVariable Long contact){
		return new ResponseEntity<ResponseStructure<DeliveryAgent>>(agentService.getAgentByContact(contact), HttpStatus.FOUND);
	}
	
	@GetMapping("/rating/{rating}")
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAgentByRatingGreaterThan(@PathVariable double rating){
		return new ResponseEntity<ResponseStructure<List<DeliveryAgent>>>(agentService.getAgentByRatingGreaterThan(rating), HttpStatus.FOUND);
	}
	
	@GetMapping("/sort/{fieldName}")
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAgentBySorting(@PathVariable String fieldName){
		return new ResponseEntity<ResponseStructure<List<DeliveryAgent>>>(agentService.getAgentBySorting(fieldName), HttpStatus.FOUND);
	}
	
	@PatchMapping
	public ResponseEntity<ResponseStructure<DeliveryAgent>> upadteAgent(@RequestBody DeliveryAgentDto deliveryAgentDto ){
		return new ResponseEntity<ResponseStructure<DeliveryAgent>>(agentService.updateAgent(deliveryAgentDto), HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAgent(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<String>>(agentService.deleteAgent(id), HttpStatus.OK);
	}
	
	@PatchMapping("/status/{id}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateAgentAvailability(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<DeliveryAgent>>(agentService.updateAgentAvailability(id ), HttpStatus.OK);
	}


}
