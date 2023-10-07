package com.wisdomleaf.assignment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.assignment.model.ErrorData;
import com.wisdomleaf.assignment.model.ResponseData;
import com.wisdomleaf.assignment.util.CommonUtil;

@RestController
public class BaseController {

	public ResponseEntity<ResponseData> requestValidate(HttpStatus badRequest, String message) {
		/*
		 * List<ErrorData> errors = badRequest.getFieldErrors().stream() .map(field ->
		 * new ErrorData(field.getField(),
		 * field.getDefaultMessage())).collect(Collectors.toList());
		 */
		return new ResponseEntity<>(
				ResponseData.builder().statusCode(HttpStatus.BAD_REQUEST.value()).statusMessage(message).build(),
				HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<ResponseData> successResponse(Object object) {
		return new ResponseEntity<>(ResponseData.builder().statusCode(CommonUtil.SUCCESSCODE)
				.statusMessage(CommonUtil.SUCCESSMSG).data(object).build(), HttpStatus.OK);
	}

	public ResponseEntity<ResponseData> commonApiResponse(Object object, HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(
				ResponseData.builder().statusCode(httpStatus.value()).statusMessage(message).data(object).build(),
				httpStatus);
	}

}
