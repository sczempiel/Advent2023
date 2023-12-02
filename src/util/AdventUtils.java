package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

public class AdventUtils {
	private static final String EXTRA_FILE_FORMAT = "extra_task%d_%s.txt";
	private static final String RESULT_FILE_FORMAT = "result_task%d.txt";
	private static final String INPUT_FILE_FORMAT = "day%s/input.txt";

	private AdventUtils() {

	}

	public static void publishResult(int day, int task, int result) {
		publishResult(day, task, String.valueOf(result));
	}

	public static void publishResult(int day, int task, long result) {
		publishResult(day, task, String.valueOf(result));
	}

	public static void publishResult(int day, int task, String result) {
		System.out.println(result);
		writeResult(day, task, result);
	}

	public static void writeResult(int day, int task, String result) {
		writeFile(result, getResultFilePath(day, task));
	}

	public static void publishExtra(int day, int task, String result, String extraName) {
		System.out.println(result);
		writeExtra(day, task, result, extraName);
	}

	public static void writeExtra(int day, int task, String result, String extraName) {
		writeFile(result, getExtraFilePath(day, task, extraName));
	}

	public static void eraseExtraFile(int day, int task, String extraName) {
		writeExtra(day, task, "", extraName);
	}

	public static void writeNewExtraLine(int day, int task, String result, String extraName) {
		try (Writer w = new BufferedWriter(new FileWriter(new File(getExtraFilePath(day, task, extraName)), true))) {
			w.write(result);
			w.write("\n");
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static void publishNewExtraLine(int day, int task, String result, String extraName) {
		System.out.println(result);
		writeNewExtraLine(day, task, result, extraName);
	}

	private static void writeFile(String result, String filePath) {
		try (Writer w = new OutputStreamWriter(new FileOutputStream(filePath))) {
			w.write(result);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static String getResultFilePath(int day) {
		URL url = AdventUtils.class.getResource("../" + AdventUtils.getInputFileName(day));
		String path = url.getPath().replaceAll("/bin/", "/src/");
		return path.substring(0, path.lastIndexOf("/") + 1);
	}

	private static String getResultFilePath(int day, int task) {
		String path = getResultFilePath(day);

		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format(RESULT_FILE_FORMAT, task);
		formatter.close();
		return path + sb.toString();
	}

	private static String getExtraFilePath(int day, int task, String extraName) {
		String path = getResultFilePath(day);

		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format(EXTRA_FILE_FORMAT, task, extraName);
		formatter.close();
		return path + sb.toString();
	}

	public static List<Integer> getIntegerInput(int day) {
		List<Integer> input = getStringInput(day).stream().map(Integer::valueOf).collect(Collectors.toList());
		return Collections.unmodifiableList(input);
	}

	public static List<Long> getLongInput(int day) {
		List<Long> input = getStringInput(day).stream().map(Long::valueOf).collect(Collectors.toList());
		return Collections.unmodifiableList(input);
	}

	public static List<String> getStringInput(int day) {
		URL url = AdventUtils.class.getResource("../" + AdventUtils.getInputFileName(day));

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(url.getPath().replaceAll("/bin/", "/src/"))))) {

			List<String> result = br.lines().collect(Collectors.toList());
			return Collections.unmodifiableList(result);

		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static String getInputFileName(int day) {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format(INPUT_FILE_FORMAT, printNum(day, 2, '0'));
		formatter.close();
		return sb.toString();
	}

	public static String printNum(Number num, int length, char toAdd) {
		String formated = String.valueOf(num);

		while (formated.length() < length) {
			formated = toAdd + formated;
		}

		return formated;
	}

	public static String printNum(Number num, int length) {
		return printNum(num, length, ' ');
	}

	public static String getPrettyTimeElapsed(long start, long now) {
		long elapsed = now - start;

		long hours = (elapsed / 1000) / 60 / 60;
		long minutes = (elapsed / 1000) / 60;
		long seconds = (elapsed / 1000) % 60;
		long milli = elapsed % 1000;

		StringBuilder sb = new StringBuilder();

		if (hours != 0) {
			sb.append(hours);
			sb.append("h ");
		}
		if (minutes != 0) {
			sb.append(minutes);
			sb.append("m ");
		}
		if (seconds != 0) {
			sb.append(seconds);
			sb.append("s ");
		}
		if (milli != 0) {
			sb.append(milli);
			sb.append("ms ");
		}

		return sb.toString();

	}

}
