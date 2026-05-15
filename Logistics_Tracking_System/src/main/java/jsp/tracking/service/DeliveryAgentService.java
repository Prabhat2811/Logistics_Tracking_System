package jsp.tracking.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.tracking.dto.DeliveryAgentDto;
import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.entity.DeliveryAgent;
import jsp.tracking.exception.ResourceNotFoundException;
import jsp.tracking.exception.RuleViolationException;
import jsp.tracking.repository.DeliveryAgentRepository;

@Service
public class DeliveryAgentService {

	@Autowired
	private DeliveryAgentRepository agentRepository;
	public @Nullable ResponseStructure<DeliveryAgent> createAgent(DeliveryAgent deliveryAgent) {
		if(String.valueOf(deliveryAgent.getContact()).length()!=10) throw new RuleViolationException("Invalid Input : Contact Number must be 10 Digits");
		
		if(agentRepository.existsByContact(deliveryAgent.getContact())) throw new RuleViolationException("Contact Number Already Exist");
		
		if(agentRepository.existsByVehicleNumber(deliveryAgent.getVehicleNumber())) throw new RuleViolationException("Vehicle Number Already Registered");
		
		ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Agent Registerd");
		res.setData(agentRepository.save(deliveryAgent));
		return res;
	}
	public @Nullable ResponseStructure<List<DeliveryAgent>> getAllAgent() {
		List<DeliveryAgent> agents=agentRepository.findAll();
		
		if(agents.isEmpty()) throw new ResourceNotFoundException("No Delivery Agent Record Found");
		
		ResponseStructure<List<DeliveryAgent>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(agents.size()+" Agent Record Found");
		res.setData(agents);
		return res;
	}
	public @Nullable ResponseStructure<DeliveryAgent> getAgentById(Integer id) {
		DeliveryAgent agent=agentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does Not Exist"));
		
		ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Delivery Agent Record Found");
		res.setData(agent);
		return res;
	}
	public @Nullable ResponseStructure<DeliveryAgent> getAgentByVehicle(String vehicle) {
		DeliveryAgent agent=agentRepository.findByVehicleNumber(vehicle).orElseThrow(()-> new ResourceNotFoundException("Vehicle Number Does not Exist"));
		
		ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Delivery Agent Record Found");
		res.setData(agent);
		return res;
	}
	public @Nullable ResponseStructure<DeliveryAgent> getAgentByContact(Long contact) {
		DeliveryAgent agent=agentRepository.findByContact(contact).orElseThrow(()-> new ResourceNotFoundException("Contact Number Does not Exist"));
		
		ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Delivery Agent Record Found");
		res.setData(agent);
		return res;
		
	}
	public @Nullable ResponseStructure<List<DeliveryAgent>> getAgentByRatingGreaterThan(double rating) {
		List<DeliveryAgent> agents=agentRepository.findByRatingGreaterThan(rating);
		
		if(agents.isEmpty()) throw new ResourceNotFoundException("No Delivery Agent Record Found");
		ResponseStructure<List<DeliveryAgent>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(agents.size()+" Delivery agent Records Found");
		res.setData(agents);
		return res;
	}
	public @Nullable ResponseStructure<List<DeliveryAgent>> getAgentBySorting(String fieldName) {
		List<DeliveryAgent> agents=agentRepository.findAll(Sort.by(fieldName).descending());
		if(agents.isEmpty()) throw new ResourceNotFoundException("No Delivery Agent Record Available");
		
		ResponseStructure<List<DeliveryAgent>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(agents.size()+" Delivery agent Records Found Sorted in Descending Order");
		res.setData(agents);
		return res;
	}
	public @Nullable ResponseStructure<DeliveryAgent> updateAgent(DeliveryAgentDto deliveryAgentDto) {
		
		if(deliveryAgentDto.getId()==null) throw new RuleViolationException("Id Must Be passed to Update a Record");
		DeliveryAgent agent=agentRepository.findById(deliveryAgentDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Id Does Not Exist"));
		
		if(agentRepository.existsByVehicleNumber(deliveryAgentDto.getVehicleNumber())) throw new RuleViolationException("Vehicle Already Registered");
		
		agent.setName(deliveryAgentDto.getName());
		agent.setRating(deliveryAgentDto.getRating());
		agent.setVehicleNumber(deliveryAgentDto.getVehicleNumber());
		
		ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Record Updated");
		res.setData(agentRepository.save(agent));
		return res;
	}
	public @Nullable ResponseStructure<String> deleteAgent(Integer id) {
		DeliveryAgent agent=agentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does Not Exist"));
		
		agentRepository.delete(agent);
		ResponseStructure<String> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Record Deleted");
		res.setData("Delivery Agent with Id :"+id+" is Removed");
		return res;
	}
	public @Nullable ResponseStructure<DeliveryAgent> updateAgentAvailability(Integer id) {
		DeliveryAgent agent=agentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does Not Exist"));
		
		if(agent.getAvailabilityStatus()) { agent.setAvailabilityStatus(false); } else agent.setAvailabilityStatus(true);
		
		ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Delivery Agent Availability Status Updated");
		res.setData(agentRepository.save(agent));
		return res;
		
	}

}
