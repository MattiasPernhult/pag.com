package pag.com.beans;

import java.io.Serializable;

public class ErrorBean implements Serializable {

	private static final long serialVersionUID = -3168349974480377280L;

	private String message;
	
	public ErrorBean() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
