package day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.AdventUtils;

public class Day4Task1Main {

	public static void main(String[] args) {
		List<String> cards = AdventUtils.getStringInput(4);

		int result = cards.stream().map(s -> s.split(" \\| ")).map(s -> {
			List<String> winning = Arrays.asList(s[0].split(" \\d+: ")[1].split(" "));
			List<String> having = new ArrayList<>(Arrays.asList(s[1].split(" ")));

			having.removeIf(String::isEmpty);
			having.retainAll(winning);

			return having;
		}).mapToInt(List::size).map(w -> {
			if (w <= 1) {
				return w;
			}

			int r = 1;
			while (w > 1) {
				r *= 2;
				w--;
			}

			return r;
		}).sum();

		AdventUtils.publishResult(4, 1, result);
	}
}
