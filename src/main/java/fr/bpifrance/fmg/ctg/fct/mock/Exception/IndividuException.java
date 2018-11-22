package fr.bpifrance.fmg.ctg.fct.mock.Exception;

import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorResponse;
import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorRetour;

public class IndividuException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private ErrorRetour error;
	 
	public ErrorRetour getError() {
		return error;
	}
	public void setError(ErrorRetour error) {
		this.error = error;
	}	
	public IndividuException(ErrorRetour error) {
		super();
		this.error = error;
	}
	public IndividuException() {
		super();
	}
}
