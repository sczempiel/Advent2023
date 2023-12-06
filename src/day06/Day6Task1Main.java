package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day6Task1Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(6));

		List<Integer> times = Arrays.stream(lines.get(0).replaceAll("\\w+:\\s+", "").split(" +")).map(Integer::valueOf)
				.collect(Collectors.toList());
		List<Integer> distances = Arrays.stream(lines.get(1).replaceAll("\\w+:\\s+", "").split(" +"))
				.map(Integer::valueOf).collect(Collectors.toList());

		int result = 1;

		for (int i = 0; i < times.size(); i++) {
			Integer time = times.get(i);
			Integer distance = distances.get(i);

			int beaten = 0;

			for (int t = 1; t <= time; t++) {
				int traveled = (time - t) * t;

				if (traveled > distance) {
					beaten++;
				}

			}

			result *= beaten;
		}

		AdventUtils.publishResult(6, 1, result);
	}

}
