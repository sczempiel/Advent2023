package util.grid.array;

import java.io.IOException;

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
}
