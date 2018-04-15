package com.ubs.opsit.interviews;

public class TimeConverterImpl implements TimeConverter {

	// aTime format is hh:mm:ss
	// Output is Y-RRRR-RRRR-YYRYYRYYRYY-YYYY (Y - Yellow, R- Red, O - Off)
	public String convertTime(String aTime) {
		StringBuilder berlinTime = new StringBuilder();

		String[] timeSplitsString = aTime.split(":");
		int[] timeSplitsInt = new int[3];

		if (timeSplitsString.length > 0) {
			timeSplitsInt[0] = Integer.parseInt(timeSplitsString[0]);

			if (timeSplitsInt[0] < 0 || timeSplitsInt[0] > 23) {
				throw new IllegalArgumentException("Wrong Hours");
			}
		}

		if (timeSplitsString.length > 1) {
			timeSplitsInt[1] = Integer.parseInt(timeSplitsString[1]);

			if (timeSplitsInt[1] < 0 || timeSplitsInt[1] > 59) {
				throw new IllegalArgumentException("Wrong Minutes");
			}

		}

		if (timeSplitsString.length > 2) {
			timeSplitsInt[2] = Integer.parseInt(timeSplitsString[2]);

			if (timeSplitsInt[2] < 0 || timeSplitsInt[2] > 59) {
				throw new IllegalArgumentException("Wrong Seconds");
			}
		}

		berlinTime.append(getFirstRowSeconds(timeSplitsInt[2]));
		berlinTime.append("-");
		berlinTime.append(getSecondRowHours(timeSplitsInt[0]));
		berlinTime.append("-");
		berlinTime.append(getThirdRowHours(timeSplitsInt[0]));
		berlinTime.append("-");
		berlinTime.append(getFourthRowMinute(timeSplitsInt[1]));
		berlinTime.append("-");
		berlinTime.append(getFifthRowMinutes(timeSplitsInt[1]));

		return berlinTime.toString();
	}

	private String getFirstRowSeconds(int seconds) {
		if (seconds % 2 == 0) {
			return "Y";
		} else {
			return "O";
		}
	}

	private String getSecondRowHours(int hours) {
		int secondRowHours = (hours - (hours % 5)) / 5;
		String temp = "";

		for (int counter = 0; counter < secondRowHours; counter++) {
			temp = temp + "R";
		}

		for (int counter = 0; counter < (4 - secondRowHours); counter++) {
			temp = temp + "O";
		}

		return temp;
	}

	private String getThirdRowHours(int hours) {
		int thirdRowHours = hours % 5;
		String temp = "";

		for (int counter = 0; counter < thirdRowHours; counter++) {
			temp = temp + "R";
		}

		for (int counter = 0; counter < (4 - thirdRowHours); counter++) {
			temp = temp + "O";
		}

		return temp;
	}

	private String getFourthRowMinute(int minutes) {

		int fourthRowMinutes = (minutes - (minutes % 5)) / 5;
		String temp = "";

		for (int counter = 0; counter < fourthRowMinutes; counter++) {
			temp = temp + "Y";
		}

		for (int counter = 0; counter < (11 - fourthRowMinutes); counter++) {
			temp = temp + "O";
		}

		temp = temp.replaceAll("YYY", "YYR");

		return temp;
	}

	private String getFifthRowMinutes(int minutes) {
		int fifthRowMinutes = minutes % 5;
		String temp = "";

		for (int counter = 0; counter < fifthRowMinutes; counter++) {
			temp = temp + "Y";
		}

		for (int counter = 0; counter < (4 - fifthRowMinutes); counter++) {
			temp = temp + "O";
		}

		return temp;
	}

}
