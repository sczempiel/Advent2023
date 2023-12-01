package day01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import util.AdventUtils;
import util.Tuple;

//54906
public class Day1Task2Main {

	public static void main(String[] args) {
		try {
			List<String> calibrations = AdventUtils.getStringInput(1);

			int result = 0;

			for (String cal : calibrations) {
				int first = findDigit(cal::indexOf, (t1, t2) -> t1 < t2);
				int last = findDigit(cal::lastIndexOf, (t1, t2) -> t1 > t2);

				result += Integer.valueOf(String.valueOf(first) + String.valueOf(last));
			}

			AdventUtils.publishResult(1, 2, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int findDigit(Function<String, Integer> indexFinder, BiPredicate<Integer, Integer> comparator) {
		List<Tuple<Integer, Integer>> positions = new ArrayList<>();
		positions.add(Tuple.of(indexFinder.apply("one"), 1));
		positions.add(Tuple.of(indexFinder.apply("1"), 1));
		positions.add(Tuple.of(indexFinder.apply("two"), 2));
		positions.add(Tuple.of(indexFinder.apply("2"), 2));
		positions.add(Tuple.of(indexFinder.apply("three"), 3));
		positions.add(Tuple.of(indexFinder.apply("3"), 3));
		positions.add(Tuple.of(indexFinder.apply("four"), 4));
		positions.add(Tuple.of(indexFinder.apply("4"), 4));
		positions.add(Tuple.of(indexFinder.apply("five"), 5));
		positions.add(Tuple.of(indexFinder.apply("5"), 5));
		positions.add(Tuple.of(indexFinder.apply("six"), 6));
		positions.add(Tuple.of(indexFinder.apply("6"), 6));
		positions.add(Tuple.of(indexFinder.apply("seven"), 7));
		positions.add(Tuple.of(indexFinder.apply("7"), 7));
		positions.add(Tuple.of(indexFinder.apply("eight"), 8));
		positions.add(Tuple.of(indexFinder.apply("8"), 8));
		positions.add(Tuple.of(indexFinder.apply("nine"), 9));
		positions.add(Tuple.of(indexFinder.apply("9"), 9));

		return positions.stream().filter(t -> t.getLeft() != -1)
				.reduce((t1, t2) -> comparator.test(t1.getLeft(), t2.getLeft()) ? t1 : t2).map(Tuple::getRight)
				.orElse(0);
	}

}
