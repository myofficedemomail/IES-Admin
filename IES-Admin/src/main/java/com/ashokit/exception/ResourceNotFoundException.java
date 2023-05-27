package com.ashokit.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourcename;
	private String fieldname;
	private String fieldvalue;
	public ResourceNotFoundException(String resourcename, String fieldname, String fieldvalue) {
		//super(String.format("%s is not found with %s : %s", resourcename,fieldname,fieldvalue));
		super(String.format(resourcename+" is not found with "+fieldname+" : "+fieldvalue));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}
	
}
