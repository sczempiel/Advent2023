package day15;

import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.List;

import util.AdventUtils;

public class Day15Task1Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(15));

		long result = stream(lines.get(0).split(",")).map(String::toCharArray).mapToLong(s -> {
			long r = 0;

			for (char c : s) {
				r += c;
				r *= 17;

				if (r > 256) {
					r = r % 256;
				}

			}

			return r;
		}).sum();

		AdventUtils.publishResult(15, 1, result);
	}

}
