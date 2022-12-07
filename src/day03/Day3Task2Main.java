package day03;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day3Task2Main {

	public static void main(String[] args) {
		try {
			List<String> backpacks = AdventUtils.getStringInput(3);

			int sum = 0;

			for (int i = 0; i < backpacks.size(); i++) {
				List<Integer> elv1 = backpacks.get(i).chars().mapToObj(Integer::valueOf).collect(Collectors.toList());
				List<Integer> elv2 = backpacks.get(++i).chars().mapToObj(Integer::valueOf)
						.collect(Collectors.toList());
				List<Integer> elv3 = backpacks.get(++i).chars().mapToObj(Integer::valueOf)
						.collect(Collectors.toList());

				Optional<Integer> foundInAll = elv1.stream().filter(t -> elv2.contains(t)).filter(t -> elv3.contains(t))
						.findFirst();

				if (!foundInAll.isPresent()) {
					throw new IllegalStateException("no matches");
				}

				int badge = foundInAll.get();
				System.out.print((char) badge);

				if (badge >= 'a') {
					badge = badge - 'a' + 1;
				} else {
					badge = badge - 'A' + 27;
				}
				
				System.out.println(" => " + badge);
				sum += badge;

			}

			AdventUtils.publishResult(3, 2, sum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
