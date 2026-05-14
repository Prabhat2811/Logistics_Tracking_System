package jsp.tracking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jsp.tracking.dto.ResponseStructure;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNoRecordAvailableException(ResourceNotFoundException exception){
		ResponseStructure<String> res=  new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.NOT_FOUND.value());
		res.setMessage(exception.getMessage());
		res.setData("Faliure");
		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception){
		ResponseStructure<String> res=  new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.NOT_FOUND.value());
		res.setMessage(exception.getMessage());
		res.setData("Faliure");
		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuleViolationException.class)
	public ResponseEntity<ResponseStructure<String>> handleRuleViolationException(RuleViolationException exception){
		ResponseStructure<String> res=  new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.NOT_FOUND.value());
		res.setMessage(exception.getMessage());
		res.setData("Faliure");
		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ActiveShipmentException.class)
	public ResponseEntity<ResponseStructure<String>> handleActiveShipmentException(ActiveShipmentException exception){
		ResponseStructure<String> res=  new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		res.setMessage(exception.getMessage());
		res.setData("Faliure");
		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
