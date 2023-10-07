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
import com.wisdomleaf.assignment.util.CommonUtil;

@RestController
@RequestMapping("/speakingClock")
public class TimeInputController extends BaseController {

	private static final String[] hoursWords = { "twelve", "one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "eleven" };

	private static final String[] tensWords = { "", "", "twenty", "thirty", "forty", "fifty" };

	@Autowired
	private TimeService timeService;

	@GetMapping("/{time}")
	public ResponseEntity<ResponseData> parseTime(@PathVariable String timeInput) {
		try {
			String[] parts = timeInput.split(":");
			if (parts.length != 2) {
				return super.requestValidate(HttpStatus.BAD_REQUEST,
						"Invalid input. Please enter time in the format HH:MM.");

			}

			int parsedHours = Integer.parseInt(parts[0]);
			int parsedMinutes = Integer.parseInt(parts[1]);

			if (parsedHours < 0 || parsedHours > 23 || parsedMinutes < 0 || parsedMinutes > 59) {
				return super.requestValidate(HttpStatus.BAD_REQUEST,
						"Invalid time format. Hours should be between 0 and 23, and minutes should be between 0 and 59.");
			}

			Time responseSpeakClock = timeService.responseSpeakClock(parsedHours, timeInput, parsedMinutes);

			return super.commonApiResponse(responseSpeakClock, HttpStatus.OK, "Speacking Clock time with time period");
		} catch (NumberFormatException e) {
			return super.requestValidate(HttpStatus.BAD_REQUEST,
					"Invalid input. Please enter time in the format HH:MM.");

		}
	}

}
