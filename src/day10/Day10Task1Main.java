package day10;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day10Task1Main {

	public static void main(String[] args) {
		try {
			List<String> instructions = AdventUtils.getStringInput(10);

			int cycles = 0;
			int registerX = 1;
			int result = 0;

			for (String inst : instructions) {
				cycles++;

				if (cycles == 20 || (cycles - 20) % 40 == 0) {
					result += registerX * cycles;
				}

				if ("noop".equals(inst)) {
					continue;
				}

				cycles++;

				if (cycles == 20 || (cycles - 20) % 40 == 0) {
					result += registerX * cycles;
				}

				registerX += Integer.valueOf(inst.split(" ")[1]);
			}

			AdventUtils.publishResult(10, 1, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
