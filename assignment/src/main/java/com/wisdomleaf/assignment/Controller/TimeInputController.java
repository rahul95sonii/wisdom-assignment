package com.wisdomleaf.assignment.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.assignment.util.CommonUtil;

@RestController
@RequestMapping("/speakingClock")
public class TimeInputController {

	private static final String[] hoursWords = { "twelve", "one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "eleven" };

	private static final String[] tensWords = { "", "", "twenty", "thirty", "forty", "fifty" };

	@GetMapping("/{time}")
	public ResponseEntity<String> parseTime(@PathVariable String timeInput) {
		try {
			String[] parts = timeInput.split(":");
			if (parts.length != 2) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Invalid input. Please enter time in the format HH:MM.");
			}

			int parsedHours = Integer.parseInt(parts[0]);
			int parsedMinutes = Integer.parseInt(parts[1]);

			if (parsedHours < 0 || parsedHours > 23 || parsedMinutes < 0 || parsedMinutes > 59) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						"Invalid time format. Hours should be between 0 and 23, and minutes should be between 0 and 59.");
			}

			String hourWords = (parsedHours == 0 || parsedHours == 12) ? "twelve" : hoursWords[parsedHours % 12];
			String minuteWords = convertMinutesToWords(parsedMinutes);

			String timeInWords = "It's " + hourWords + " " + minuteWords;

			int hour = Integer.parseInt(timeInput.split(":")[0]);
			String timePeriod = (hour == 12 || hour == 0) ? "Midnight" : (hour == 12) ? "Midday" : "";

			if (!timePeriod.isEmpty()) {
				System.out.println("It's " + timePeriod);
			}

			return ResponseEntity.ok(timeInWords);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid input. Please enter time in the format HH:MM.");
		}
	}

	private String convertMinutesToWords(int minutes) {
		if (minutes < 10) {
			return "o'clock";
		} else if (minutes < 20) {
			return unitsToWords(minutes);
		} else {
			int tens = minutes / 10;
			int units = minutes % 10;
			return tensWords[tens] + " " + unitsToWords(units);
		}
	}

	private String unitsToWords(int units) {
		return (units == 0) ? "" : hoursWords[units];
	}
}
