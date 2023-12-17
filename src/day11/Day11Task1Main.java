package day11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.Tuple;
import util.grid.map.GridUtils;

//9676728 l
public class Day11Task1Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(11));

		List<Integer> rowsToAdd = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.matches("^\\.+$")) {
				rowsToAdd.add(i);
			}
		}

		List<Integer> columnsToAdd = new ArrayList<>();
		column: for (int i = 0; i < lines.get(0).length(); i++) {
			for (String line : lines) {
				if (line.charAt(i) != '.') {
					continue column;
				}
			}

			columnsToAdd.add(i);
		}

		AtomicInteger count = new AtomicInteger(1);
		Map<Tuple<Integer, Integer>, String> grid = GridUtils.toGrid(lines,
				c -> c == '#' ? String.valueOf(count.getAndIncrement()) : String.valueOf(c));

		List<Tuple<Tuple<Integer, Integer>, String>> galaxies = grid.entrySet().stream()
				.filter(e -> !Objects.equals(".", e.getValue())).map(e -> Tuple.of(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		long totalDist = 0;

		for (Iterator<Tuple<Tuple<Integer, Integer>, String>> it = galaxies.iterator(); it.hasNext();) {
			Tuple<Tuple<Integer, Integer>, String> galaxy = it.next();
			it.remove();

			for (Tuple<Tuple<Integer, Integer>, String> other : galaxies) {
				int rowIncrement = addSpace(rowsToAdd, galaxy.getLeft().getLeft(), other.getLeft().getLeft());
				int columnIncrement = addSpace(columnsToAdd, galaxy.getLeft().getRight(), other.getLeft().getRight());

				int dist = rowIncrement + Math.abs(galaxy.getLeft().getLeft() - other.getLeft().getLeft())
						+ columnIncrement + Math.abs(galaxy.getLeft().getRight() - other.getLeft().getRight());
				totalDist += dist;
			}
		}

		// get tile to walk on

		AdventUtils.publishResult(11, 1, totalDist);
	}

	private static int addSpace(List<Integer> toAdd, Integer bound1, Integer bound2) {
		int space = 0;

		int left = bound1 < bound2 ? bound1 : bound2;
		int right = bound1 > bound2 ? bound1 : bound2;

		for (Integer column : toAdd) {
			if (column < left) {
				continue;
			}

			if (column > right) {
				break;
			}

			space++;
		}
		return space;
	}

}
