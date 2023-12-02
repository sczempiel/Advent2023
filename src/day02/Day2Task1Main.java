package day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.AdventUtils;

public class Day2Task1Main {

	public static void main(String[] args) {
		int result = AdventUtils.getStringInput(2).stream().mapToInt(s -> {
			Matcher matcherGame = Pattern.compile("Game (\\d+)").matcher(s);
			matcherGame.find();

			Integer game = Integer.valueOf(matcherGame.group(1));

			for (String r : s.split(";")) {

				int red = colorCounter("red", r);
				int blue = colorCounter("blue", r);
				int green = colorCounter("green", r);

				if (red > 12 || green > 13 || blue > 14) {
					return 0;
				}
			}

			return game;
		}).sum();

		AdventUtils.publishResult(2, 1, result);
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
