package day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day4Task2Main {

	private static List<Integer> stack;

	public static void main(String[] args) {
		List<String> cards = AdventUtils.getStringInput(4);

		stack = cards.stream().map(s -> s.split(" \\| ")).map(s -> {
			List<String> winning = Arrays.asList(s[0].split(" \\d+: ")[1].split(" "));
			List<String> having = new ArrayList<>(Arrays.asList(s[1].split(" ")));

			having.removeIf(String::isEmpty);
			having.retainAll(winning);

			return having;
		}).map(List::size).collect(Collectors.toList());

		System.out.println("stack " + stack);

//		for (ListIterator<Integer> it = stack.listIterator(); it.hasNext();) {
//			Integer card = it.next();
//
//			System.out.println("draw " + card);
//
//			List<Integer> toAdd = new ArrayList<>();
//			while (card > 0 && it.hasNext()) {
//				Integer nextCard = it.next();
//				toAdd.add(nextCard);
//				card--;
//			}
//
//			System.out.println(toAdd);
//
//			toAdd.forEach(a -> it.previous());
//			toAdd.forEach(it::add);
//
//			System.out.println("stack " + stack);
//		}

		int result = scratch(0, stack.size());
		result += stack.size();

		AdventUtils.publishResult(4, 2, result);
	}

	private static int scratch(int start, int end) {
		int result = 0;

		for (int i = start; i < end; i++) {
			int winning = stack.get(i);

			if (winning == 0) {
				continue;
			}

			result += winning;

			result += scratch(i + 1, i + winning + 1);
		}

		return result;
	}

}
