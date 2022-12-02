package day02;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day2Task1Main {

	public static void main(String[] args) {
		try {
			List<String> rounds = AdventUtils.getStringInput(2);

			long sum = 0;

			for (String round : rounds) {
				String[] plays = round.split(" ");

				long them = plays[0].charAt(0) - 'A' + 1;
				long me = plays[1].charAt(0) - 'X' + 1;

				long result = result(them, me);
				System.out.println(String.format("%s (%d) %s (%d) => %d", plays[0], them, plays[1], me, result));

				sum += result;
			}

			AdventUtils.publishResult(2, 1, sum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static long result(long them, long me) {
		if (them == me) {
			return 3 + me;
		}

		if (them == 1) {
			if (me == 2) {
				return 6 + me;
			} else {
				return me;
			}
		}

		if (them == 2) {
			if (me == 3) {
				return 6 + me;
			} else {
				return me;
			}
		}
		
		if (them == 3) {
			if (me == 1) {
				return 6 + me;
			} else {
				return me;
			}
		}

		throw new IllegalStateException();
	}

}
