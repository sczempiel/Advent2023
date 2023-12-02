package day01;

import java.util.List;

import util.AdventUtils;

public class Day1Task1Main {

	public static void main(String[] args) {
		List<String> calibrations = AdventUtils.getStringInput(1);

		int result = 0;

		for (String cal : calibrations) {
			char[] chars = cal.toCharArray();

			Character first = null;
			for (char element : chars) {
				first = element;
				if (Character.isDigit(first)) {
					break;
				}
			}

			Character last = null;
			for (int i = chars.length - 1; i >= 0; i--) {
				last = chars[i];
				if (Character.isDigit(last)) {
					break;
				}
			}

			result += Integer.valueOf(String.valueOf(first) + String.valueOf(last));

		}

		AdventUtils.publishResult(1, 1, result);
	}
}
