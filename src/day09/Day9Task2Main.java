package day09;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

import util.AdventUtils;
import util.Tuple;
import util.grid.map.GridUtils;

public class Day9Task2Main {

	private static Tuple<Integer, Integer> headPos = Tuple.of(0, 0);
	private static SortedMap<Integer, Tuple<Integer, Integer>> rope = new TreeMap<>();

	private static Set<Tuple<Integer, Integer>> visited = new HashSet<>();

	public static void main(String[] args) {
		try {
			List<String> instructions = AdventUtils.getStringInput(9);

			IntStream.range(1, 10).forEach(i -> rope.put(i, Tuple.of(0, 0)));

			visited.add(Tuple.of(0, 0));

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

					Tuple<Integer, Integer> ahead = headPos;
					for (Entry<Integer, Tuple<Integer, Integer>> entry : rope.entrySet()) {

						Tuple<Integer, Integer> ropePartPos = entry.getValue();

						int dist = Math.abs(ahead.getLeft() - ropePartPos.getLeft())
								+ Math.abs(ahead.getRight() - ropePartPos.getRight());

						if (dist == 2) {
							int moveRL = (ahead.getLeft() - ropePartPos.getLeft()) / 2;
							int moveUD = (ahead.getRight() - ropePartPos.getRight()) / 2;

							ropePartPos = Tuple.of(ropePartPos.getLeft() + moveRL, ropePartPos.getRight() + moveUD);
						}
						if (dist > 2) {
							int moveRL = diagnalTailMove(ahead.getLeft(), ropePartPos.getLeft());
							int moveUD = diagnalTailMove(ahead.getRight(), ropePartPos.getRight());

							ropePartPos = Tuple.of(ropePartPos.getLeft() + moveRL, ropePartPos.getRight() + moveUD);
						}

						rope.put(entry.getKey(), ropePartPos);
						ahead = ropePartPos;
					}

					visited.add(rope.get(9));
					steps--;
				}

			}

			print();

			AdventUtils.publishResult(9, 2, visited.size());
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
		rope.entrySet().stream().sorted(comp).forEach(e -> grid.put(e.getValue(), String.valueOf(e.getKey())));
		grid.put(headPos, "H");

		AdventUtils.writeExtra(9, 2, GridUtils.print(grid, s -> s != null ? s : "."), "trail");
	}

}
