package day04;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day4Task2Main {

	public static void main(String[] args) {
		try {
			List<String> pairs = AdventUtils.getStringInput(4);

			int count = 0;

			for (String pair : pairs) {

				String[] assignments = pair.split("[,-]");

				int lower1 = Integer.parseInt(assignments[0]);
				int upper1 = Integer.parseInt(assignments[1]);

				int lower2 = Integer.parseInt(assignments[2]);
				int upper2 = Integer.parseInt(assignments[3]);

				if ((lower1 <= upper2 && upper1 >= lower2) || (lower2 <= upper1 && upper2 >= lower1)) {
					count++;
				}

			}

			AdventUtils.publishResult(4, 2, count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
