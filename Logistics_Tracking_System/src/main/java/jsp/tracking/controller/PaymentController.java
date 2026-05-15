package jsp.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.tracking.dto.PaymentStatus;
import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.entity.Payment;
import jsp.tracking.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment(){
		return new ResponseEntity<ResponseStructure<List<Payment>>>(paymentService.getAllPayment(), HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<Payment>>(paymentService.getPaymentById(id), HttpStatus.FOUND);
	}
	
	@PatchMapping("/status/{id}/{status}")
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(@PathVariable Integer id ,@PathVariable PaymentStatus status){
		return new ResponseEntity<ResponseStructure<Payment>>(paymentService.updatePaymentStatus(id,status), HttpStatus.OK);
	}
	
	@PatchMapping("/cancle/{id}")
	public ResponseEntity<ResponseStructure<Payment>> canclePayment(@PathVariable Integer id){
		return new ResponseEntity<ResponseStructure<Payment>>(paymentService.canclePayment(id), HttpStatus.OK);
	}
}
