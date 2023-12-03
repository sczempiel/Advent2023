package day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import util.AdventUtils;
import util.Tuple;
import util.grid.map.GridUtils;

public class Day3Task1Main {

	public static void main(String[] args) {
		List<String> schematic = AdventUtils.getStringInput(3);
		Map<Tuple<Integer, Integer>, Character> grid = GridUtils.toGrid(schematic, Function.identity());

		int sum = 0;

		String number = "";
		boolean adjacent = false;

		List<Tuple<Integer, Integer>> sorted = new ArrayList<>(grid.keySet());
		sorted.sort(GridUtils.COORDINATE_COMPARATOR);

		for (Tuple<Integer, Integer> coordinate : sorted) {
			Character value = grid.get(coordinate);

			if (Character.isDigit(value)) {
				number += String.valueOf(value);
				adjacent |= GridUtils.matchAnySurrounding(coordinate, grid,
						(c1, c2) -> c2 != null && !Character.isDigit(c2) && c2 != '.');
			} else {
				if (adjacent && !number.isEmpty()) {
					sum += Integer.valueOf(number);
				}

				number = "";
				adjacent = false;
			}

		}

		AdventUtils.publishResult(3, 1, sum);
	}
}
