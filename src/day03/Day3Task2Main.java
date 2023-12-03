package day03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import util.AdventUtils;
import util.Tuple;
import util.grid.map.GridUtils;

public class Day3Task2Main {

	private static Map<Tuple<Integer, Integer>, Character> grid;

	public static void main(String[] args) {
		List<String> schematic = AdventUtils.getStringInput(3);
		grid = GridUtils.toGrid(schematic, Function.identity());

		int sum = 0;

		List<Tuple<Integer, Integer>> sorted = new ArrayList<>(grid.keySet());
		sorted.sort(GridUtils.COORDINATE_COMPARATOR);

		for (Tuple<Integer, Integer> coordinate : sorted) {
			Character value = grid.get(coordinate);

			if (value == '*') {
				List<Integer> numbers = new ArrayList<>();

				Set<Tuple<Integer, Integer>> visited = new HashSet<>();

				for (Tuple<Integer, Integer> toCheck : toCheck(coordinate.getLeft(), coordinate.getRight())) {
					if (visited.contains(toCheck)) {
						continue;
					}

					Tuple<Optional<Integer>, Set<Tuple<Integer, Integer>>> res = getNumber(toCheck);
					visited.addAll(res.getRight());

					res.getLeft().ifPresent(numbers::add);
				}

				if (numbers.size() == 2) {
					sum += (numbers.get(0) * numbers.get(1));
				}

			}

		}

		AdventUtils.publishResult(3, 2, sum);
	}

	private static boolean isDigit(Tuple<Integer, Integer> cord) {
		return grid.get(cord) != null && Character.isDigit(grid.get(cord));
	}

	private static Tuple<Optional<Integer>, Set<Tuple<Integer, Integer>>> getNumber(Tuple<Integer, Integer> start) {
		Tuple<Integer, Integer> toCheck = start;
		while (isDigit(toCheck)) {
			Tuple<Integer, Integer> next = Tuple.of(toCheck.getLeft(), toCheck.getRight() - 1);

			if (!isDigit(next)) {
				break;
			}

			toCheck = next;
		}

		Set<Tuple<Integer, Integer>> visited = new HashSet<>();
		visited.add(start);

		String number = "";
		while (isDigit(toCheck)) {
			number += String.valueOf(grid.get(toCheck));
			visited.add(toCheck);

			toCheck = Tuple.of(toCheck.getLeft(), toCheck.getRight() + 1);
		}

		Optional<Integer> parsedNum = number.isEmpty() ? Optional.empty() : Optional.of(Integer.valueOf(number));
		return Tuple.of(parsedNum, visited);
	}

	private static List<Tuple<Integer, Integer>> toCheck(int y, int x) {
		List<Tuple<Integer, Integer>> toCheck = new ArrayList<>();
		toCheck.add(Tuple.of(y - 1, x - 1));
		toCheck.add(Tuple.of(y - 1, x));
		toCheck.add(Tuple.of(y - 1, x + 1));
		toCheck.add(Tuple.of(y, x - 1));
		toCheck.add(Tuple.of(y, x + 1));
		toCheck.add(Tuple.of(y + 1, x - 1));
		toCheck.add(Tuple.of(y + 1, x));
		toCheck.add(Tuple.of(y + 1, x + 1));

		return toCheck;
	}

}
