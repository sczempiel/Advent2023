package day01;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day1Task1Main {

	public static void main(String[] args) {
		try {
			List<String> calories = AdventUtils.getStringInput(1);

			int max = 0;
			int cur = 0;

			for (String cal : calories) {

				if (cal.isBlank()) {
					if (max < cur) {
						max = cur;
					}

					cur = 0;
				} else {
					cur += Integer.parseInt(cal);
				}
			}
			
			if (max < cur) {
				max = cur;
			}

			AdventUtils.publishResult(1, 1, max);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
