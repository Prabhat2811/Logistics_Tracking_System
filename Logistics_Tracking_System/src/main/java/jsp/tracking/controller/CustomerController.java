package jsp.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import jsp.tracking.dto.CustomerDto;
import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.entity.Customer;
import jsp.tracking.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Customer>> createCustomer(@RequestBody Customer customer){
		return new ResponseEntity<ResponseStructure<Customer>>(customerService.createCustomer(customer), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
		return new ResponseEntity<ResponseStructure<List<Customer>>>(customerService.getAllCustomer(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<Customer>>(customerService.getCustomerById(id), HttpStatus.FOUND);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByEmail(@PathVariable String email){
		return new ResponseEntity<ResponseStructure<Customer>>(customerService.getCustomerByEmail(email), HttpStatus.FOUND);
	}
	
	@PatchMapping
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody CustomerDto customerDto){
		return new ResponseEntity<ResponseStructure<Customer>>(customerService.updateCustomer(customerDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<String>>(customerService.deleteCustomer(id), HttpStatus.OK);
	}
	
	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(@PathVariable Long contact){
		return new ResponseEntity<ResponseStructure<Customer>>(customerService.getCustomerByContact(contact), HttpStatus.FOUND);
	}
	
	@GetMapping("/page/{pageNumber}/{pageSize}")
	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPagination(@PathVariable int pageNumber,@PathVariable int pageSize){
		return new ResponseEntity<ResponseStructure<Page<Customer>>>(customerService.getCustomerByPagination(pageNumber, pageSize), HttpStatus.FOUND);
	}
}
