package day07;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.Tuple;

public class Day7Task2Main {

	private static List<Character> order = Arrays.asList('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K',
			'A');
	private static Comparator<Character> cardComparator = Comparator.comparing(c -> order.indexOf(c));

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(7));

		AtomicInteger standing = new AtomicInteger(1);

		long result = lines.stream().map(s -> {
			String[] raw = s.split(" ");

			List<Character> cards = raw[0].chars().mapToObj(c -> (char) c).collect(toList());

			Hand hand = new Hand();
			hand.cards = cards;
			hand.bid = Long.valueOf(raw[1]);
			hand.cardCounts = getCardsWithJokersApplied(cards.stream().map(c -> Tuple.of(c, 1))
					.collect(groupingBy(Tuple::getLeft, mapping(Tuple::getRight, reducing(0, Integer::sum)))).entrySet()
					.stream().map(e -> Tuple.of(e.getValue(), e.getKey()))
					.sorted(Comparator.comparing(Tuple<Integer, Character>::getLeft).reversed())
					.collect(Collectors.toList()));

			return hand;

		}).sorted(Day7Task2Main::compareHand).mapToLong(h -> standing.getAndIncrement() * h.bid).sum();

		AdventUtils.publishResult(7, 2, result);
	}

	public static int compareHand(Hand h1, Hand h2) {
		List<Tuple<Integer, Character>> c1 = h1.cardCounts;
		List<Tuple<Integer, Character>> c2 = h2.cardCounts;

		if (c1.get(0).getLeft() < c2.get(0).getLeft()) {
			return -1;
		}

		if (c1.get(0).getLeft() > c2.get(0).getLeft()) {
			return 1;
		}

		if (c1.size() == c2.size()) {
			int res;
			int i = 0;
			while ((res = cardComparator.compare(h1.cards.get(i), h2.cards.get(i))) == 0) {
				i++;
			}
			return res;
		}

		if (c1.get(1).getLeft() < c2.get(1).getLeft()) {
			return -1;
		}

		if (c1.get(1).getLeft() > c2.get(1).getLeft()) {
			return 1;
		}

		return 0;
	}

	private static List<Tuple<Integer, Character>> getCardsWithJokersApplied(List<Tuple<Integer, Character>> cards) {
		List<Tuple<Integer, Character>> result = new ArrayList<>(cards);

		if (result.size() == 1) {
			return result;
		}

		int count = 0;
		for (Iterator<Tuple<Integer, Character>> it = result.iterator(); it.hasNext();) {
			Tuple<Integer, Character> c = it.next();
			if (c.getRight() == 'J') {
				count = c.getLeft();
				it.remove();
				break;
			}
		}

		result.get(0).setLeft(result.get(0).getLeft() + count);
		return result;
	}

	private static class Hand {
		List<Character> cards;
		List<Tuple<Integer, Character>> cardCounts;

		long bid;

		@Override
		public String toString() {
			return "Hand [cards=" + this.cards + ", cardCounts=" + this.cardCounts + ", bid=" + this.bid + "]";
		}

	}
}
