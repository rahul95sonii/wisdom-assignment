package com.wisdomleaf.assignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Time {
	private int hours;
	private int minutes;

	/*
	 * public Time(int hours, int minutes) { this.hours = hours; this.minutes =
	 * minutes; }
	 */

	@Override
	public String toString() {
		return "Time{" + "hours=" + hours + ", minutes=" + minutes + '}';
	}
}
