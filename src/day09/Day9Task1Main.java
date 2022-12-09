package day09;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import util.AdventUtils;
import util.Tuple;
import util.grid.map.GridUtils;

public class Day9Task1Main {

	private static Tuple<Integer, Integer> headPos = Tuple.of(0, 0);
	private static Tuple<Integer, Integer> tailPos = Tuple.of(0, 0);

	private static Set<Tuple<Integer, Integer>> visited = new HashSet<>();

	public static void main(String[] args) {
		try {
			List<String> instructions = AdventUtils.getStringInput(9);

			visited.add(tailPos);

			for (String inst : instructions) {
				String[] instruction = inst.split(" ");

				String dir = instruction[0];
				int steps = Integer.parseInt(instruction[1]);

				while (steps > 0) {
					if ("U".equals(dir)) {
						headPos = Tuple.of(headPos.getLeft(), headPos.getRight() - 1);
					} else if ("R".equals(dir)) {
						headPos = Tuple.of(headPos.getLeft() + 1, headPos.getRight());
					} else if ("D".equals(dir)) {
						headPos = Tuple.of(headPos.getLeft(), headPos.getRight() + 1);
					} else if ("L".equals(dir)) {
						headPos = Tuple.of(headPos.getLeft() - 1, headPos.getRight());
					}

					int dist = Math.abs(headPos.getLeft() - tailPos.getLeft())
							+ Math.abs(headPos.getRight() - tailPos.getRight());

					if (dist == 2) {
						int moveRL = (headPos.getLeft() - tailPos.getLeft()) / 2;
						int moveUD = (headPos.getRight() - tailPos.getRight()) / 2;

						tailPos = Tuple.of(tailPos.getLeft() + moveRL, tailPos.getRight() + moveUD);
					}
					if (dist > 2) {
						int moveRL = diagnalTailMove(headPos.getLeft(), tailPos.getLeft());
						int moveUD = diagnalTailMove(headPos.getRight(), tailPos.getRight());

						tailPos = Tuple.of(tailPos.getLeft() + moveRL, tailPos.getRight() + moveUD);
					}

					visited.add(tailPos);

					steps--;
				}

			}
			
			print();
			
			AdventUtils.publishResult(9, 1, visited.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int diagnalTailMove(int head, int tail) {
		float move = (head - tail) / 2f;

		if (move < 0) {
			return (int) Math.floor(move);
		} else {
			return Math.round(move);
		}
	}

	private static void print() throws IOException {
		Map<Tuple<Integer, Integer>, String> grid = new HashMap<>();
		visited.forEach(p -> grid.put(p, "#"));
		grid.put(Tuple.of(0, 0), "S");

		Comparator<Entry<Integer, Tuple<Integer, Integer>>> comp = Comparator.comparing(Entry::getKey);
		comp = comp.reversed();
		grid.put(tailPos, "T");
		grid.put(headPos, "H");

		AdventUtils.writeExtra(9, 1, GridUtils.print(grid, s -> s != null ? s : "."), "trail");
	}

}
