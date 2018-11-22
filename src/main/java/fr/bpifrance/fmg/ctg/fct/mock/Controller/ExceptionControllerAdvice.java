package fr.bpifrance.fmg.ctg.fct.mock.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.bpifrance.fmg.ctg.fct.mock.Exception.IndividuException;
import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorDetails;
import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorResponse;
import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorRetour;
  
@ControllerAdvice
public class ExceptionControllerAdvice {
 
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorResponse exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		error.setMessage("Invalid parameter type");
		return error;
	}	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorRetour> exceptionHandler2(Exception ex) {
		ErrorRetour error = new ErrorRetour();
		error.setStatus("IMX-ERRORS");
		error.setMessage("Empty Body");
		List<ErrorDetails> listDetailError = new ArrayList<>();
		  ErrorDetails errorDetail = new ErrorDetails();
		  errorDetail.setCode("IMX-0005");
		  errorDetail.setDescription("la requête est vide ");
		  errorDetail.setTitle("requet");
		  listDetailError.add(errorDetail);
		  
		  error.setListErrors(listDetailError);
		return new ResponseEntity<ErrorRetour>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(IndividuException.class)
	public ResponseEntity<ErrorRetour> exceptionHandler3(IndividuException ex) {
		ErrorRetour error = new ErrorRetour();
		error.setStatus(ex.getError().getStatus());
		error.setMessage(ex.getError().getMessage());
		error.setListErrors(ex.getError().getListErrors());
		
		return new ResponseEntity<ErrorRetour>( error,HttpStatus.OK);
	}
}
