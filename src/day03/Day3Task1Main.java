package day03;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day3Task1Main {

	public static void main(String[] args) {
		try {
			List<String> backpacks = AdventUtils.getStringInput(3);

			int sum = 0;

			for (String types : backpacks) {				
				List<Integer> firstComp = types.substring(0, types.length() / 2).chars().mapToObj(Integer::valueOf)
						.collect(Collectors.toList());
				List<Integer> secondComp = types.substring(types.length() / 2).chars().mapToObj(Integer::valueOf)
						.collect(Collectors.toList());

				Optional<Integer> foundInBoth = firstComp.stream().filter(t -> secondComp.contains(t)).findFirst();

				if (!foundInBoth.isPresent()) {
					throw new IllegalStateException("no matches");
				}

				int typeVal = foundInBoth.get();
				
				if (typeVal >= 'a') {
					typeVal = typeVal - 'a' + 1;
				} else {
					typeVal = typeVal - 'A' + 27;
				}

				sum += typeVal;

			}

			AdventUtils.publishResult(3, 1, sum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
