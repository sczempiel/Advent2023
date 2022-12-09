package util.grid.array;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import util.AdventUtils;

public class GridUtils {
	public static String gridToString(char[][] grid) throws IOException {
		StringBuilder sb = new StringBuilder();

		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				sb.append(grid[y][x]);
			}
			if (y < grid.length - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public static void sysoGrid(char[][] grid) throws IOException {
		System.out.println(gridToString(grid));
	}

	public static void printGrid(int day, int task, char[][] grid, boolean publish) throws IOException {
		String printable = gridToString(grid);
		if (publish) {
			AdventUtils.publishExtra(day, task, printable, "grid");
		} else {
			AdventUtils.writeExtra(day, task, printable, "grid");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T[][] toGrid(List<String> lines, Function<Character, T> converter) {

		List<List<T>> rows = lines.stream()
				.map(s -> s.chars().mapToObj(c -> converter.apply((char) c)).collect(Collectors.toList()))
				.collect(Collectors.toList());

		if (rows.isEmpty() || rows.get(0).isEmpty()) {
			throw new IllegalStateException("Can't create empty grid");
		}

		Class<T> clazz = (Class<T>) rows.get(0).get(0).getClass();

		T[][] grid = (T[][]) Array.newInstance(clazz, rows.size(), rows.get(0).size());

		for (int y = 0; y < rows.size(); y++) {
			for (int x = 0; x < rows.get(y).size(); x++) {
				grid[y][x] = rows.get(y).get(x);
			}
		}

		return grid;
	}
}
