package day06;

import java.util.ArrayList;
import java.util.List;

import util.AdventUtils;

public class Day6Task2Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(6));

		long time = Long.valueOf(lines.get(0).replaceAll("\\w+:\\s+", "").replaceAll(" +", ""));
		long distance = Long.valueOf(lines.get(1).replaceAll("\\w+:\\s+", "").replaceAll(" +", ""));

		int beaten = 0;

		for (long t = 1; t <= time; t++) {
			long traveled = (time - t) * t;

			if (traveled > distance) {
				beaten++;
			}

		}

		AdventUtils.publishResult(6, 2, beaten);
	}

}
