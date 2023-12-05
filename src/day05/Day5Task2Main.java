package day05;

import static java.lang.Long.valueOf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import util.AdventUtils;
import util.Tuple;

public class Day5Task2Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(5));

		String[] seedsRanges = lines.remove(0).split(": ")[1].split(" ");

		List<Mapping> seeds = new ArrayList<>();

		for (int i = 0; i < seedsRanges.length; i += 2) {
			seeds.add(new Mapping(valueOf(seedsRanges[i]), valueOf(seedsRanges[i]), valueOf(seedsRanges[i + 1]),
					"seeds"));
		}

		lines.remove(0);

		List<Mapping> soilToSeeds = readMapping(lines, "soilToSeeds");
		List<Mapping> fertilizerToSoil = readMapping(lines, "fertilizerToSoil");
		List<Mapping> waterToFertilizer = readMapping(lines, "waterToFertilizer");
		List<Mapping> lightToWater = readMapping(lines, "lightToWater");
		List<Mapping> temperatureToLight = readMapping(lines, "temperatureToLight");
		List<Mapping> humidityToTermperature = readMapping(lines, "humidityToTermperature");
		List<Mapping> locationToHumidity = readMapping(lines, "locationToHumidity");

		List<List<Mapping>> mappings = Arrays.asList(locationToHumidity, humidityToTermperature, temperatureToLight,
				lightToWater, waterToFertilizer, fertilizerToSoil, soilToSeeds);

		Stream<Tuple<Long, Long>> stream = LongStream.range(1, 100_000_000l).mapToObj(l -> Tuple.of(l, l));

		for (List<Mapping> m : mappings) {
			stream = stream.map(s -> Tuple.of(s.getLeft(), getMapping(s.getRight(), m)));
		}

		Optional<Tuple<Long, Long>> result = stream
				.map(s -> mapIfExists(s.getRight(), seeds).map(r -> Tuple.of(s.getLeft(), r)))
				.filter(Optional::isPresent).map(Optional::get).findFirst();

		System.out.println(result.get());

		AdventUtils.publishResult(5, 2, result.get().getLeft());
	}

	private static long getMapping(long source, List<Mapping> mapping) {
		return mapIfExists(source, mapping).orElse(source);
	}

	private static Optional<Long> mapIfExists(long source, List<Mapping> mapping) {
		return mapping.stream().filter(m -> m.lowerSource <= source).filter(m -> m.upperSource >= source).findFirst()
				.map(m -> m.offset + source);
	}

	private static List<Mapping> readMapping(List<String> lines, String name) {
		lines.remove(0);

		String line;

		List<Mapping> mapping = new ArrayList<>();

		while (!lines.isEmpty() && !(line = lines.remove(0)).isBlank()) {
			String[] parts = line.split(" ");
			mapping.add(new Mapping(valueOf(parts[0]), valueOf(parts[1]), valueOf(parts[2]), name));
		}

		return mapping;
	}

	private static class Mapping {
		final long lowerSource;
		final long upperSource;

		final long lowerDestination;
		final long upperDestination;

		final long range;
		final long offset;

		final String name;

		private Mapping(long lowerSource, long lowerDestination, long range, String name) {
			this.lowerSource = lowerSource;
			this.upperSource = lowerSource + range - 1;
			this.lowerDestination = lowerDestination;
			this.upperDestination = lowerDestination + range - 1;
			this.range = range;
			this.offset = lowerDestination - lowerSource;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Mapping [lowerSource=" + this.lowerSource + ", upperSource=" + this.upperSource
					+ ", lowerDestination=" + this.lowerDestination + ", upperDestination=" + this.upperDestination
					+ ", range=" + this.range + ", offset=" + this.offset + ", name=" + this.name + "]";
		}

	}

}
