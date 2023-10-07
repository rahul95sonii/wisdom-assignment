package com.wisdomleaf.assignment.service;

import org.springframework.stereotype.Service;

import com.wisdomleaf.assignment.model.Time;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeService {

	private static final String[] hoursWords = { "twelve", "one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "eleven" };

	private static final String[] tensWords = { "", "", "twenty", "thirty", "forty", "fifty" };

	public int[] parseTimeInput(String timeInput) {
		try {
			String[] parts = timeInput.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
			return new int[] { hours, minutes };
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid input format. Please enter time in the format 'HH:mm'.");
		}
	}

	public Time responseSpeakClock(int parsedHours, String timeInput, int parsedMinutes) {
		String hourWords = (parsedHours == 0 || parsedHours == 12) ? "twelve" : hoursWords[parsedHours % 12];
		String minuteWords = convertMinutesToWords(parsedMinutes);

		String timeInWords = "It's " + hourWords + " " + minuteWords;

		int hour = Integer.parseInt(timeInput.split(":")[0]);
		String timePeriod = (hour == 12 || hour == 0) ? "Midnight" : (hour == 12) ? "Midday" : "";

		if (!timePeriod.isEmpty()) {
			System.out.println("It's " + timePeriod);
		}
		Time time = new Time();
		time.setTimeInWord(timeInWords);
		time.setTimetype(timePeriod);
		return time;

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
