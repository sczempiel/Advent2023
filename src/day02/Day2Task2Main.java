package day02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AdventUtils;

public class Day2Task2Main {

	public static void main(String[] args) {
		try {
			List<String> rounds = AdventUtils.getStringInput(2);

			long sum = 0;

			for (String round : rounds) {
				String[] plays = round.split(" ");

				long them = plays[0].charAt(0) - 'A' + 1;

				long points = points(them, plays[1].charAt(0));

				sum += points;
			}

			AdventUtils.publishResult(2, 2, sum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static long points(long them, long result) {
		if (result == 'X') {
			if (them == 1) {
				return 3;
			} else if (them == 2) {
				return 1;
			} else {
				return 2;
			}
		}

		if (result == 'Y') {
			return 3 + them;
		}

		if (result == 'Z') {
			if (them == 1) {
				return 6 + 2;
			} else if (them == 2) {
				return 6 + 3;
			} else {
				return 6 + 1;
			}
		}

		throw new IllegalStateException();
	}
}
