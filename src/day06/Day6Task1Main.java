package day06;

import java.io.IOException;
import java.util.Arrays;

import util.AdventUtils;

public class Day6Task1Main {

	public static void main(String[] args) {
		try {
			char[] input = AdventUtils.getStringInput(6).get(0).toCharArray();

			int hit = 0;
			
			for (int i = 0; i < input.length - 3; i++) {
				char c1 = input[i];
				char c2 = input[i + 1];
				char c3 = input[i + 2];
				char c4 = input[i + 3];

				if (Arrays.asList(c1, c2, c3, c4).stream().distinct().count() == 4) {
					hit = i + 4;
					break;
				}
			}

			AdventUtils.publishResult(6, 1, hit);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
