package day05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day5Task1Main {

	public static void main(String[] args) {
		try {
			List<String> lines = new ArrayList<>(AdventUtils.getStringInput(5));

			List<Stack<Character>> stacks = new ArrayList<>();

			for (int i = 1; i < lines.get(0).length(); i += 4) {
				stacks.add(new Stack<>());
			}

			String line;
			while (!(line = lines.remove(0)).isEmpty()) {

				int s = 0;
				for (int i = 1; i < lines.get(0).length(); i += 4) {
					Character c = line.charAt(i);

					if (Character.isAlphabetic(c)) {
						stacks.get(s).add(0, c);
					}

					s++;
				}

			}

			for (String move : lines) {
				String[] instuctions = move.split(" ");

				int count = Integer.valueOf(instuctions[1]);

				Stack<Character> from = stacks.get(Integer.valueOf(instuctions[3]) - 1);
				Stack<Character> to = stacks.get(Integer.valueOf(instuctions[5]) - 1);

				for (int i = 0; i < count; i++) {
					to.add(from.pop());
				}
			}

			String result = stacks.stream().map(Stack::peek).map(String::valueOf).collect(Collectors.joining(""));
			
			AdventUtils.publishResult(5, 1, result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
