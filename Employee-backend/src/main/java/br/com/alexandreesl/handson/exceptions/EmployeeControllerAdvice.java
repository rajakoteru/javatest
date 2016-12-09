package br.com.alexandreesl.handson.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Raja koteru
 */
@ControllerAdvice  
@RestController 
class EmployeeControllerAdvice {
	   
	    @ResponseBody
		@ExceptionHandler(EmployeeNotFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
	    public String EmployeeNotFoundExceptionHandler(EmployeeNotFoundException ex) {
			return  ex.getMessage();
		}
	    @ExceptionHandler(value = Exception.class)  
	    public String handleException(Exception e){return e.getMessage();}  
	  
}
