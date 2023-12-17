package day15;

import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import util.AdventUtils;

public class Day15Task2Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(15));

		Map<Integer, List<Lens>> boxes = IntStream.range(0, 256).mapToObj(Integer::valueOf)
				.collect(Collectors.toMap(Function.identity(), ArrayList::new));

		stream(lines.get(0).split(",")).map(s -> {
			Lens lens = new Lens();

			if (!s.endsWith("-")) {
				lens.label = s.substring(0, s.length() - 2);
				lens.operation = s.charAt(s.length() - 2);
				lens.focalLength = Integer.valueOf(s.substring(s.length() - 1));
			} else {
				lens.label = s.substring(0, s.length() - 1);
				lens.operation = s.charAt(s.length() - 1);
			}

			int box = 0;

			for (char c : lens.label.toCharArray()) {
				box += c;
				box *= 17;

				if (box > 256) {
					box = box % 256;
				}

			}

			lens.box = box;

			return lens;
		}).forEach(l -> {
			List<Lens> lenses = boxes.get(l.box);

			if ('=' == l.operation) {
				int index = lenses.indexOf(l);
				if (index == -1) {
					lenses.add(l);
				} else {
					lenses.set(index, l);
				}
			}

			if ('-' == l.operation) {
				lenses.remove(l);
			}
		});

		int result = boxes.entrySet().stream().mapToInt(e -> {
			int mult = 1;
			int res = 0;
			for (Lens l : e.getValue()) {
				res += l.focalLength * mult;
				mult++;
			}

			return res * (e.getKey() + 1);
		}).sum();

		AdventUtils.publishResult(15, 2, result);
	}

	private static class Lens {
		String label;
		int box;
		char operation;
		int focalLength;

		@Override
		public int hashCode() {
			return Objects.hash(this.label);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (this.getClass() != obj.getClass()) {
				return false;
			}
			Lens other = (Lens) obj;
			return Objects.equals(this.label, other.label);
		}

		@Override
		public String toString() {
			return "Lens [label=" + this.label + ", focalLength=" + this.focalLength + "]";
		}

	}

}
