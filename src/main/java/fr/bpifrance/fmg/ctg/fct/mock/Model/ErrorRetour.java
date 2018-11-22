package fr.bpifrance.fmg.ctg.fct.mock.Model;

import java.util.List;

public class ErrorRetour {
	
	public String status;
	
	public String message;
	
	public List<ErrorDetails> listErrors;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorDetails> getListErrors() {
		return listErrors;
	}

	public void setListErrors(List<ErrorDetails> listErrors) {
		this.listErrors = listErrors;
	}
	
	

}
