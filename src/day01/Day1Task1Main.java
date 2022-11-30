package day01;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day1Task1Main {

	public static void main(String[] args) {
		try {
			List<Integer> startValues = AdventUtils.getIntegerInput(1);

			AdventUtils.publishResult(1, 1, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
