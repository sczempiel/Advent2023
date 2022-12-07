package day07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.AdventUtils;
import util.Tuple;

public class Day7Task1Main {

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

			updateSizeOfDirs("/");

			AdventUtils.publishResult(7, 1, totalSize("/"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static long totalSize(String dir) {
		List<Tuple<String, Long>> files = fileSystem.get(dir);

		if (files == null) {
			return 0;
		}

		long size = 0;

		for (Tuple<String, Long> file : files) {

			if (fileSystem.containsKey(getPath(dir, file.getLeft()))) {
				long dirSize = file.getRight();

				if (dirSize <= 100000) {
					size += dirSize;
				}
				size += totalSize(getPath(dir, file.getLeft()));
			}
		}

		return size;
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
