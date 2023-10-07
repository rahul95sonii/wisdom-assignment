package com.wisdomleaf.assignment.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ResponseData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer statusCode;
	private String statusMessage;
	private Object data;
	private List<ErrorData> errors;

}