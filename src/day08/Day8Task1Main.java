package day08;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import util.AdventUtils;
import util.grid.array.GridUtils;

public class Day8Task1Main {

	public static void main(String[] args) {
		try {
			List<String> raw = AdventUtils.getStringInput(8);
			
			Character[][] grid = GridUtils.toGrid(raw, Function.identity());

			int visible = 0;

			visible += (grid.length - 1) * 2;
			visible += (grid[0].length - 1) * 2;

			for (int y = 1; y < grid.length - 1; y++) {
				for (int x = 1; x < grid[y].length - 1; x++) {
					char tree = grid[y][x];

					boolean visibleUp = true;
					for (int up = y - 1; up >= 0; up--) {
						char other = grid[up][x];
						if (other >= tree) {
							visibleUp = false;
							break;
						}
					}

					boolean visibleRight = true;
					for (int right = x + 1; right < grid[y].length; right++) {
						char other = grid[y][right];
						if (other >= tree) {
							visibleRight = false;
							break;
						}
					}

					boolean visibleDown = true;
					for (int down = y + 1; down < grid.length; down++) {
						char other = grid[down][x];
						if (other >= tree) {
							visibleDown = false;
							break;
						}
					}

					boolean visibleLeft = true;
					for (int left = x - 1; left >= 0; left--) {
						char other = grid[y][left];
						if (other >= tree) {
							visibleLeft = false;
							break;
						}
					}

					if (visibleUp || visibleRight || visibleDown || visibleLeft) {
						visible++;
					}
				}
			}

			AdventUtils.publishResult(8, 1, visible);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static char[][] toGrid(List<String> lines) {

		char[][] grid = new char[lines.size()][];

		for (int y = 0; y < lines.size(); y++) {
			grid[y] = lines.get(y).toCharArray();
		}

		return grid;

	}
}
