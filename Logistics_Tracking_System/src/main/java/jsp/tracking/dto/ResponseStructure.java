package jsp.tracking.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure <T>{
	private Integer statusCode;
	private String message;
	private T data;
}
