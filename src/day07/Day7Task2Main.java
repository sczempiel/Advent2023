package day07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

import util.AdventUtils;
import util.Tuple;

public class Day7Task2Main {

	private static Map<String, List<Tuple<String, Long>>> fileSystem = new HashMap<>();

	private static String currentDir = "/";

	public static void main(String[] args) {
		try {
			List<String> consoleOutput = AdventUtils.getStringInput(7);

			fileSystem.put(currentDir, new ArrayList<>());

			for (int i = 0; i < consoleOutput.size(); i++) {
				String line = consoleOutput.get(i);
				if (line.startsWith("$ cd ")) {
					String dir = line.split(" ")[2];
					cd(dir);
					fileSystem.putIfAbsent(currentDir, new ArrayList<>());
				} else if (line.startsWith("$ ls")) {
					while (i + 1 < consoleOutput.size() && (line = consoleOutput.get(i + 1)) != null
							&& !line.startsWith("$")) {
						i++;
						String[] file = line.split(" ");

						if ("dir".equals(file[0])) {
							fileSystem.putIfAbsent(getPath(currentDir, file[1]), new ArrayList<>());
							fileSystem.get(currentDir).add(Tuple.of(file[1], 0l));
						} else {
							fileSystem.get(currentDir).add(Tuple.of(file[1], Long.parseLong(file[0])));
						}

					}
				}
			}

			long totalSize = updateSizeOfDirs("/");

			if (totalSize + 30000000 > 70000000) {
				List<Tuple<String, Long>> deletable = getDirsWithSize("/", totalSize - (70000000 - 30000000));

				OptionalLong smallest = deletable.stream().map(Tuple::getRight).mapToLong(Long::longValue).min();
				AdventUtils.publishResult(7, 2, smallest.getAsLong());
			} else {
				AdventUtils.publishResult(7, 2, 0);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<Tuple<String, Long>> getDirsWithSize(String dir, long minSize) {
		List<Tuple<String, Long>> matching = new ArrayList<>();

		List<Tuple<String, Long>> files = fileSystem.get(dir);

		if (files == null) {
			return matching;
		}

		for (Tuple<String, Long> file : files) {

			if (fileSystem.containsKey(getPath(dir, file.getLeft()))) {
				long dirSize = file.getRight();

				if (dirSize >= minSize) {
					matching.add(file);
				}
				matching.addAll(getDirsWithSize(getPath(dir, file.getLeft()), minSize));
			}
		}

		return matching;
	}

	private static long updateSizeOfDirs(String dir) {
		List<Tuple<String, Long>> files = fileSystem.get(dir);

		if (files == null) {
			return 0;
		}

		long size = 0;

		for (Tuple<String, Long> file : files) {
			size += file.getRight();

			long dirSize = updateSizeOfDirs(getPath(dir, file.getLeft()));

			if (dirSize > 0) {
				file.setRight(dirSize);
			}

			size += dirSize;
		}

		return size;
	}

	private static String getPath(String dir, String file) {
		if ("/".equals(dir)) {
			return dir + file;
		}

		return dir + "/" + file;
	}

	private static void cd(String path) {
		if ("/".equals(path)) {
			currentDir = path;
		} else {
			String[] dirChange = path.split("/");

			for (int d = 0; d < dirChange.length; d++) {
				if ("..".equals(dirChange[d])) {
					currentDir = currentDir.substring(0, currentDir.lastIndexOf('/'));
				} else {
					currentDir = getPath(currentDir, dirChange[d]);
				}
			}

		}
	}
}
