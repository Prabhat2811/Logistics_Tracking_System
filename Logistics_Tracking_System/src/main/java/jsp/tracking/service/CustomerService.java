package jsp.tracking.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.tracking.dto.CustomerDto;
import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.dto.ShipmentStatus;
import jsp.tracking.entity.Customer;
import jsp.tracking.exception.ActiveShipmentException;
import jsp.tracking.exception.IdNotFoundException;
import jsp.tracking.exception.ResourceNotFoundException;
import jsp.tracking.exception.RuleViolationException;
import jsp.tracking.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public @Nullable ResponseStructure<Customer> createCustomer(Customer customer) {
		
		if(customerRepository.existsByEmail(customer.getEmail())) {
			throw new RuleViolationException("Email Already Exits");
		}
		if(customerRepository.existsByContact(customer.getContact())) {
			throw new RuleViolationException("Contact Number Already Exits");
		}
		if(String.valueOf(customer.getContact()).length()!=10) {
			throw new RuleViolationException("Invalid input: Contact number must be 10 digits");
		}
		ResponseStructure<Customer> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Customer Registered");
		res.setData(customerRepository.save(customer));
		return res;
	}

	public @Nullable ResponseStructure<List<Customer>> getAllCustomer() {
		ResponseStructure<List<Customer>> res=new ResponseStructure<>();
		List<Customer> customers=customerRepository.findAll();
		if(!customers.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage(customers.size()+" Customer Record Found");
			res.setData(customers);
			return res;
		}
		else
			throw new ResourceNotFoundException("No customer Record Found");
	}

	public @Nullable ResponseStructure<Customer> getCustomerById(Integer id) {
		ResponseStructure<Customer> res=new ResponseStructure<>();
		Customer customer=customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Cutomer Record Found"));		
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Customer Record Found");
		res.setData(customer);
		return res;
	}

	public @Nullable ResponseStructure<Customer> getCustomerByEmail(String email) {
		ResponseStructure<Customer> res=new ResponseStructure<>();
		Customer customer=customerRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("No Customer Record Found"));
		res.setMessage("Customer Record Found");
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setData(customer);
		return res;
		
	}

	public @Nullable ResponseStructure<Customer> updateCustomer(CustomerDto customerDto) {
		ResponseStructure<Customer> res=new ResponseStructure<>();
		Customer customer=customerRepository.findById(customerDto.getId()).orElseThrow(()-> new IdNotFoundException("Id Does Not Exist"));
		
		if(customerRepository.existsByEmail(customerDto.getEmail())) throw new RuleViolationException("Email Already Registered");
		
		customer.setAddress(customerDto.getAddress());
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Record Updated");
		res.setData(customerRepository.save(customer));
		return res;
	}

	public @Nullable ResponseStructure<String> deleteCustomer(Integer id) {
		ResponseStructure<String> res=new ResponseStructure<>();
		Customer customer=customerRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Id Does Not Exist"));
		
		 if (customerRepository.existsByIdAndShipmentsStatusIn(id, 
		            List.of(ShipmentStatus.CONFIRMED, ShipmentStatus.CREATED, ShipmentStatus.DELAYED,ShipmentStatus.PACKED, ShipmentStatus.DISPATCHED, ShipmentStatus.OUT_FOR_DELIVERY, ShipmentStatus.IN_TRANSIT)))
		        throw new ActiveShipmentException("Customer has active shipments, cannot delete");
		
		
		customerRepository.delete(customer);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Deleted");
		res.setData("Record Deleted With Id : "+id);
		return res;
	}

	public @Nullable ResponseStructure<Customer> getCustomerByContact(Long contact) {
		if(String.valueOf(contact).length()!=10) throw new RuleViolationException("Invalid input: Contact number must be 10 digits");
		
		Customer customer =customerRepository.findByContact(contact).orElseThrow(()-> new ResourceNotFoundException("No Record Found"));
		
		ResponseStructure<Customer> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Customer Record Found");
		res.setData(customer);
		return res;
	}

	public @Nullable ResponseStructure<Page<Customer>> getCustomerByPagination(int pageNumber, int pageSize) {
		ResponseStructure<Page<Customer>> res=new ResponseStructure<>();
		Page<Customer> pages=customerRepository.findAll(PageRequest.of(pageNumber, pageSize));
		if(!pages.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage(pages.getSize()+" Customer Record Displaying");
			res.setData(pages);
			return res;
		}
		else
			throw new ResourceNotFoundException("No Record Available");
	}
	
	
	

}
