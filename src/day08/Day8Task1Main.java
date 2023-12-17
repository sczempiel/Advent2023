package day08;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.AdventUtils;
import util.Tuple;

public class Day8Task1Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(8));

		char[] instructions = lines.remove(0).toCharArray();

		lines.remove(0);

		Map<String, Tuple<String, String>> map = lines.stream().map(s -> s.substring(0, s.length() - 1))
				.map(s -> s.split(" = \\(")).map(s -> {
					String[] outcomes = s[1].split(", ");
					return Tuple.of(s[0], Tuple.of(outcomes[0], outcomes[1]));
				}).collect(toMap(Tuple::getLeft, Tuple::getRight));

		String current = "AAA";
		int steps = 0;

		while (!"ZZZ".equals(current)) {
			for (char instr : instructions) {
				Tuple<String, String> directions = map.get(current);

				if ('L' == instr) {
					current = directions.getLeft();
				} else {
					current = directions.getRight();
				}

				steps++;

				if ("ZZZ".equals(current)) {
					break;
				}
			}
		}

		AdventUtils.publishResult(8, 1, steps);
	}

}
