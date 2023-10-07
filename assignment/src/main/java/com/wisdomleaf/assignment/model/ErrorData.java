package com.wisdomleaf.assignment.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String field;
	String errorMessage;

}
