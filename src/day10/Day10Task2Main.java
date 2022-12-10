package day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day10Task2Main {

	private static int cycles = 0;
	private static int row = -1;
	private static int registerX = 1;
	private static List<List<String>> screen = new ArrayList<>();

	public static void main(String[] args) {
		try {
			List<String> instructions = AdventUtils.getStringInput(10);

			for (String inst : instructions) {

				cycles++;

				print();

				if ("noop".equals(inst)) {
					continue;
				}

				cycles++;

				print();

				registerX += Integer.valueOf(inst.split(" ")[1]);
			}

			String printed = screen.stream().map(r -> r.stream().collect(Collectors.joining()))
					.collect(Collectors.joining("\n"));

			AdventUtils.publishResult(10, 2, printed);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void print() {
		int column = (cycles - 1) % 40;

		if (column == 0) {
			screen.add(new ArrayList<>());
			row++;
		}

		if (column == registerX - 1 || column == registerX || column == registerX + 1) {
			screen.get(row).add("#");
		} else {
			screen.get(row).add(".");
		}
	}
}
