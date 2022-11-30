package util.grid.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

import util.Tuple;

public class GridUtils {
	public static <T> String print(Map<Tuple<Integer, Integer>, T> map, Function<T, String> printValue) {
		Integer smallestY = null;
		Integer biggestY = null;
		Integer smallestX = null;
		Integer biggestX = null;

		for (Tuple<Integer, Integer> pos : map.keySet()) {

			int y = pos.getLeft();
			int x = pos.getRight();

			if (smallestY == null || y < smallestY) {
				smallestY = y;
			}

			if (biggestY == null || y > biggestY) {
				biggestY = y;
			}

			if (smallestX == null || x < smallestX) {
				smallestX = x;
			}

			if (biggestX == null || x > biggestX) {
				biggestX = x;
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int y = smallestY; y <= biggestY; y++) {
			for (int x = smallestX; x <= biggestX; x++) {
				sb.append(printValue.apply(map.get(Tuple.of(y, x))));
			}

			if (y < biggestY) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	public static <T> Map<Tuple<Integer, Integer>, T> toGrid(List<String> lines, Function<Character, T> converter) {
		return toGrid(lines, converter, false);
	}

	public static <T> Map<Tuple<Integer, Integer>, T> toGrid(List<String> lines, Function<Character, T> converter,
			boolean addNull) {

		Map<Tuple<Integer, Integer>, T> grid = new HashMap<>();

		for (int y = 0; y < lines.size(); y++) {
			char[] chars = lines.get(y).toCharArray();

			for (int x = 0; x < chars.length; x++) {
				T value = converter.apply(chars[x]);

				if (value != null || addNull) {
					grid.put(Tuple.of(y, x), value);
				}
			}
		}

		return grid;

	}

	public static <T> boolean matchSurrounding(Tuple<Integer, Integer> point, Map<Tuple<Integer, Integer>, T> grid,
			BiPredicate<T, T> matcher) {
		return matchSurrounding(point.getLeft(), point.getRight(), grid, matcher);
	}

	public static <T> boolean matchSurrounding(int y, int x, Map<Tuple<Integer, Integer>, T> grid,
			BiPredicate<T, T> matcher) {
		T value = grid.get(Tuple.of(y, x));

		boolean matches = true;

		matches = matches && matcher.test(value, grid.get(Tuple.of(y - 1, x - 1)));
		matches = matches && matcher.test(value, grid.get(Tuple.of(y - 1, x)));
		matches = matches && matcher.test(value, grid.get(Tuple.of(y - 1, x + 1)));
		matches = matches && matcher.test(value, grid.get(Tuple.of(y, x - 1)));
		matches = matches && matcher.test(value, grid.get(Tuple.of(y, x + 1)));
		matches = matches && matcher.test(value, grid.get(Tuple.of(y + 1, x - 1)));
		matches = matches && matcher.test(value, grid.get(Tuple.of(y + 1, x)));
		matches = matches && matcher.test(value, grid.get(Tuple.of(y + 1, x + 1)));

		return matches;

	}

	public static void consumeSurrounding(Tuple<Integer, Integer> point, Consumer<Tuple<Integer, Integer>> consumer) {
		consumeSurrounding(point.getLeft(), point.getRight(), consumer);
	}

	public static void consumeSurrounding(int y, int x, Consumer<Tuple<Integer, Integer>> consumer) {
		consumer.accept(Tuple.of(y - 1, x - 1));
		consumer.accept(Tuple.of(y - 1, x));
		consumer.accept(Tuple.of(y - 1, x + 1));
		consumer.accept(Tuple.of(y, x - 1));
		consumer.accept(Tuple.of(y, x + 1));
		consumer.accept(Tuple.of(y + 1, x - 1));
		consumer.accept(Tuple.of(y + 1, x));
		consumer.accept(Tuple.of(y + 1, x + 1));
	}

}
