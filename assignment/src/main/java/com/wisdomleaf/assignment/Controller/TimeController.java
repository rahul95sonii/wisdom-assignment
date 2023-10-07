package com.wisdomleaf.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.assignment.model.ResponseData;
import com.wisdomleaf.assignment.model.Time;
import com.wisdomleaf.assignment.service.TimeService;

@RestController
@RequestMapping("/parseTime")
public class TimeController extends BaseController {

	@Autowired
	private TimeService timeService;

	@GetMapping("/{timeInput}")
	public ResponseEntity<ResponseData> parseTime(@PathVariable String timeInput) {
		try {
			int[] time = timeService.parseTimeInput(timeInput);
			Time parsedTime = new Time(time[0], time[1]);
			return super.commonApiResponse(parsedTime, HttpStatus.OK, "Time formate validation check done");

		} catch (Exception e) {
			// Handle parsing errors
			return super.requestValidate(HttpStatus.BAD_REQUEST,
					"Invalid input");
		}
	}

}
