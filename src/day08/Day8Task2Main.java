package day08;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import util.AdventUtils;
import util.Tuple;

public class Day8Task2Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(8));

		char[] instructions = lines.remove(0).toCharArray();

		lines.remove(0);

		Map<String, Tuple<String, String>> map = lines.stream().map(s -> s.substring(0, s.length() - 1))
				.map(s -> s.split(" = \\(")).map(s -> {
					String[] outcomes = s[1].split(", ");
					return Tuple.of(s[0], Tuple.of(outcomes[0], outcomes[1]));
				}).collect(toMap(Tuple::getLeft, Tuple::getRight));

		List<String> currentPositions = map.keySet().stream().filter(s -> s.endsWith("A")).collect(toList());

		System.out.println(currentPositions);

		int steps = 0;

		Predicate<List<String>> endsWithZ = s -> s.stream().allMatch(pos -> pos.endsWith("Z"));

		while (!endsWithZ.test(currentPositions)) {
			for (char instr : instructions) {
				for (int i = 0; i < currentPositions.size(); i++) {
					Tuple<String, String> directions = map.get(currentPositions.get(i));

					if ('L' == instr) {
						currentPositions.set(i, directions.getLeft());
					} else {
						currentPositions.set(i, directions.getRight());
					}
				}

				steps++;

				if (endsWithZ.test(currentPositions)) {
					break;
				}
			}
		}

		AdventUtils.publishResult(8, 2, steps);
	}

}
