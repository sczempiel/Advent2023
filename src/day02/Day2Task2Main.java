package day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.AdventUtils;

public class Day2Task2Main {

	public static void main(String[] args) {
		int result = AdventUtils.getStringInput(2).stream().mapToInt(s -> {

			int maxRed = 0;
			int maxGreen = 0;
			int maxBlue = 0;

			for (String r : s.split(";")) {

				int red = colorCounter("red", r);
				int green = colorCounter("green", r);
				int blue = colorCounter("blue", r);

				if (red > maxRed) {
					maxRed = red;
				}
				if (green > maxGreen) {
					maxGreen = green;
				}
				if (blue > maxBlue) {
					maxBlue = blue;
				}
			}

			return maxRed * maxGreen * maxBlue;
		}).sum();

		AdventUtils.publishResult(2, 2, result);
	}

	private static int colorCounter(String color, String game) {
		int count = 0;
		Matcher matcher = Pattern.compile("(\\d+) " + color).matcher(game);
		while (matcher.find()) {
			count += Integer.valueOf(matcher.group(1));
		}

		return count;
	}

}
