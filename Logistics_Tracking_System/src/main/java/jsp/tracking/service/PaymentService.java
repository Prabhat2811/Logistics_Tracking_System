package jsp.tracking.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.tracking.dto.PaymentStatus;
import jsp.tracking.dto.ResponseStructure;
import jsp.tracking.entity.Payment;
import jsp.tracking.exception.ResourceNotFoundException;
import jsp.tracking.exception.RuleViolationException;
import jsp.tracking.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	public @Nullable ResponseStructure<List<Payment>> getAllPayment() {
		List<Payment> payments=paymentRepository.findAll();
		if(payments.isEmpty()) throw new ResourceNotFoundException("No Payment Record Exist");
		
		ResponseStructure<List<Payment>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage(payments.size()+" Payment Record Found");
		res.setData(payments);
		return res;
	}
	public @Nullable ResponseStructure<Payment> getPaymentById(Integer id) {
		Payment payment=paymentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does not Exist"));
		
		ResponseStructure<Payment> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("Payment Record Found");
		res.setData(payment);
		return res;
	}
	public @Nullable ResponseStructure<Payment> updatePaymentStatus(Integer id, PaymentStatus status) {
		Payment payment=paymentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does Not Exist"));
		
		payment.setPaymentStatus(status);
		ResponseStructure<Payment> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Updated Payment Status");
		res.setData(payment);
		return res;
	}
	public @Nullable ResponseStructure<Payment> canclePayment(Integer id) {
		
		Payment payment=paymentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Does Not Exist"));
		
		switch (payment.getPaymentStatus()) {
        case SUCCESS:
            throw new RuleViolationException("Payment already completed, cannot cancel");

        case CANCELLED:
            throw new RuleViolationException("Payment is already cancelled");

        case FAILED:
            throw new RuleViolationException("Payment already failed, cannot cancel");

        case PENDING:
            payment.setPaymentStatus(PaymentStatus.CANCELLED);
            break;

        default:
            throw new RuleViolationException("Invalid payment status");
    }
		
		ResponseStructure<Payment> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Canceled");
		res.setData(paymentRepository.save(payment));
		return res;
		
	}

}
