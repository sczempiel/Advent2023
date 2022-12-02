package day01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AdventUtils;

public class Day1Task2Main {

	public static void main(String[] args) {
		try {
			List<String> calories = AdventUtils.getStringInput(1);

			List<Long> calsPerElve = new ArrayList<>();
			long cur = 0;

			for (String cal : calories) {

				if (cal.isBlank()) {
					calsPerElve.add(cur);
					cur = 0;
				} else {
					cur += Long.parseLong(cal);
				}
			}
			
			calsPerElve.add(cur);
			
			long max1 = calsPerElve.stream().mapToLong(Long::longValue).max().getAsLong();
			calsPerElve.remove(max1);
			
			long max2 = calsPerElve.stream().mapToLong(Long::longValue).max().getAsLong();
			calsPerElve.remove(max2);
			
			long max3 = calsPerElve.stream().mapToLong(Long::longValue).max().getAsLong();
			calsPerElve.remove(max3);


			AdventUtils.publishResult(1, 2, max1 + max2 + max3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
