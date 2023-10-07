package com.wisdomleaf.assignment.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.assignment.model.Time;

@RestController
@RequestMapping("/parseTime")
public class TimeController {

	@GetMapping("/{timeInput}")
	public ResponseEntity<Time> parseTime(@PathVariable String timeInput) {
		try {
			int[] time = parseTimeInput(timeInput);
			Time parsedTime = new Time(time[0], time[1]);
			return ResponseEntity.ok(parsedTime);
		} catch (Exception e) {
			// Handle parsing errors
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	private int[] parseTimeInput(String timeInput) {
		try {
			String[] parts = timeInput.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
			return new int[] { hours, minutes };
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid input format. Please enter time in the format 'HH:mm'.");
		}
	}
}
