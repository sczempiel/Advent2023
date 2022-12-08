package day08;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day8Task2Main {

	public static void main(String[] args) {
		try {
			List<String> raw = AdventUtils.getStringInput(8);

			char[][] grid = toGrid(raw);

			int highestScenicScore = 0;

			for (int y = 1; y < grid.length - 1; y++) {
				for (int x = 1; x < grid[y].length - 1; x++) {
					char tree = grid[y][x];

					int scenicScoreUp = 0;
					for (int up = y - 1; up >= 0; up--) {
						scenicScoreUp++;
						char other = grid[up][x];
						if (other >= tree) {
							break;
						}
					}

					int scenicScoreRight = 0;
					for (int right = x + 1; right < grid[y].length; right++) {
						scenicScoreRight++;
						char other = grid[y][right];
						if (other >= tree) {
							break;
						}
					}

					int scenicScoreDown = 0;
					for (int down = y + 1; down < grid.length; down++) {
						scenicScoreDown++;
						char other = grid[down][x];
						if (other >= tree) {
							break;
						}
					}

					int scenicScoreleft = 0;
					for (int left = x - 1; left >= 0; left--) {
						scenicScoreleft++;
						char other = grid[y][left];
						if (other >= tree) {
							break;
						}
					}

					int scenicScore = scenicScoreUp * scenicScoreRight * scenicScoreDown * scenicScoreleft;
					if (scenicScore > highestScenicScore) {
						highestScenicScore = scenicScore;
					}
				}
			}

			AdventUtils.publishResult(8, 2, highestScenicScore);
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
