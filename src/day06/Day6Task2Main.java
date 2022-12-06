package day06;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import util.AdventUtils;

public class Day6Task2Main {

	public static void main(String[] args) {
		try {
			char[] input = AdventUtils.getStringInput(6).get(0).toCharArray();

			int hit = 0;

			for (int i = 0; i < input.length - 13; i++) {
				Set<Character> chars = new HashSet<>();

				for (int j = i; j < i + 14; j++) {
					chars.add(input[j]);
				}


				if (chars.size() == 14) {
					hit = i + 14;
					break;
				}
			}

			AdventUtils.publishResult(6, 2, hit);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
