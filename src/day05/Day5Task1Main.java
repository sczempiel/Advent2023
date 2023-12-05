package day05;

import static java.lang.Long.valueOf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.AdventUtils;

public class Day5Task1Main {

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>(AdventUtils.getStringInput(5));

		List<Long> seeds = Arrays.stream(lines.remove(0).split(": ")[1].split(" ")).map(Long::valueOf)
				.collect(Collectors.toList());

		lines.remove(0);

		List<Mapping> seedToSoil = readMapping(lines, "seedToSoil");
		List<Mapping> soilToFerilizer = readMapping(lines, "soilToFerilizer");
		List<Mapping> fertilizerToWater = readMapping(lines, "fertilizerToWater");
		List<Mapping> waterToLight = readMapping(lines, "waterToLight");
		List<Mapping> lightToTemperature = readMapping(lines, "lightToTemperature");
		List<Mapping> termperatureToHumidity = readMapping(lines, "termperatureToHumidity");
		List<Mapping> humidityToLocation = readMapping(lines, "humidityToLocation");

		List<List<Mapping>> mappings = Arrays.asList(seedToSoil, soilToFerilizer, fertilizerToWater, waterToLight,
				lightToTemperature, termperatureToHumidity, humidityToLocation);

		Stream<Long> stream = seeds.stream();

		for (List<Mapping> m : mappings) {
			stream = stream.map(s -> getMapping(s, m));
		}

		OptionalLong result = stream.mapToLong(Long::longValue).min();

		AdventUtils.publishResult(5, 1, result.getAsLong());
	}

	private static long getMapping(long source, List<Mapping> mapping) {
		return mapping.stream().filter(m -> m.lowerSource <= source).filter(m -> m.upperSource >= source).findFirst()
				.map(m -> m.offset + source).orElse(source);
	}

	private static List<Mapping> readMapping(List<String> lines, String name) {
		lines.remove(0);

		String line;

		List<Mapping> mapping = new ArrayList<>();

		while (!lines.isEmpty() && !(line = lines.remove(0)).isBlank()) {
			String[] parts = line.split(" ");
			mapping.add(new Mapping(valueOf(parts[1]), valueOf(parts[0]), valueOf(parts[2]), name));
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
