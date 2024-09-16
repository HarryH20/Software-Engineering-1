/*
 * Author: Harrison Hassler
 * Course: CSI 3471
 * Assignment: Practicum Redo
 * File: Tester.java
 * Description: This file uses the make class
 * to sort and parse the cvs file.
 */

package edu.baylor.cs.csi3471;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tester {


	private static int readOption(String optionStr) {
		try {
			return Integer.parseInt(optionStr);
		} catch (NumberFormatException e) {
			System.err.println("Invalid option: " + optionStr);
			System.exit(1);
			return -1; // Dummy return
		}
	}


	private static Set<Make> loadCSV(String file) throws FileNotFoundException {
		BufferedReader reader = null;
		Set<Make> makes = new HashSet<>();


		try {
			reader = new BufferedReader(new FileReader(new File(file)));
			String line = null;
			reader.readLine();
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				if (split.length != 11 || containsNull(split)) {
					continue;
				}
				Make make = new Make(split);
				make.creatorPattern(split, makes);

			}

			return makes;
		} catch (IOException e) {
			String hint = "";
			try {
				hint = "Current dir is: " + new File(".").getCanonicalPath();
			} catch (Exception local) {
				hint = local.getLocalizedMessage();
			}
			throw new FileNotFoundException(e.getLocalizedMessage() + "\n" + hint);

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e.getLocalizedMessage());
				}
			}
		}

	}

	private static boolean containsNull(String[] array) {
		for (String str : array) {
			if (str == null || str.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static void printOption1(Collection<Make> makes) {
		System.out.println("Total number of makes " + makes.size());
		System.out.println("===============");
		List<Make> sortedMakes = makes.stream()
				.sorted(Comparator.comparing(m -> m.getMakeName().toLowerCase()))
				.collect(Collectors.toList());
		Collections.reverse(sortedMakes);


		for (Make make : sortedMakes) {
			System.out.println("Make: " + make.getMakeName());
			System.out.println("Number of ModelSettings instances: " + make.getModelSettings().size());
			System.out.println("--------------");

		}
		System.out.println("===============");

		for (Make make : sortedMakes) {
			System.out.println(make);
		}

	}

	public static void printOption2(Collection<Make> makes, String columnName, String value) {
		List<Make> sortedMakes = makes.stream()
				.sorted(Comparator.comparing((Make make) -> make.getMakeName().toLowerCase())).collect(Collectors.toList());
		sortedMakes.stream().filter(make -> make.getModelSettings().stream().
						anyMatch(model -> findColumn(model, make, columnName, value))).
				forEach(make -> {
					System.out.println(make.getMakeName());
					make.getModelSettings().stream().filter(model -> findColumn(model, make, columnName, value))
							.forEach(System.out::println);
				});

	}

	private static boolean findColumn(ModelSettings model, Make make, String columnName, String value) {
		switch (columnName.toLowerCase()) {
			case "city08" -> {
				return model.getMpg().getCity() == Integer.parseInt(value);
			}
			case "comb08" -> {
				return model.getMpg().getAvg() == Integer.parseInt(value);
			}
			case "cylinders" -> {
				return model.getEngineCylinders() == Integer.parseInt(value);
			}
			case "displ" -> {
				return model.getEngineDisplacement() == Double.parseDouble(value);
			}
			case "fuelType" -> {
				return model.getFuelType().equalsIgnoreCase(value);
			}
			case "highway08" -> {
				return model.getMpg().getHighway() == Integer.parseInt(value);
			}
			case "make" -> {
				return make.getMakeName().equalsIgnoreCase(value);
			}
			case "model" -> {
				return model.getModelName().equalsIgnoreCase(value);
			}
			case "trany" -> {
				return model.getTransmission().equalsIgnoreCase(value);
			}
			case "VClass" -> {
				return model.getvClass().equalsIgnoreCase(value);
			}
			case "year" -> {
				return model.getModelYear() == Integer.parseInt(value);
			}
			default -> {
				return false;
			}

		}
	}

	public static void printOption3(Collection<Make> makes) {
		Map<String, Double> averageMPGPerClass = makes.stream()
				.flatMap(make -> make.getModelSettings().stream())
				.collect(Collectors.groupingBy(ModelSettings::getvClass,
						Collectors.averagingDouble(setting -> setting.getMpg().getAvg())));
		averageMPGPerClass.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getKey().toLowerCase())).
				forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));


	}

	public static void printOption4(Collection<Make> makes) {
		List<Make> sortedMakes = makes.stream()
				.sorted(Comparator.comparing(m -> m.getMakeName().toLowerCase()))
				.collect(Collectors.toList());
		for (Make make : sortedMakes) {
			Map<Integer, Long> modelCountsByYear = make.getModelSettings().stream().
					collect(Collectors.groupingBy(ModelSettings::getModelYear,
							Collectors.counting()));
			modelCountsByYear.forEach((year, count)
					-> System.out.println(make.getMakeName() + " " + year + " " + count));


		}

	}

	public static void printOption5(Collection<Make> makes) {
		List<ModelSettings> modelSettingsList = makes.stream()
				.flatMap(make -> make.getModelSettings().stream())
				.collect(Collectors.toList());
		Map<String, Double> makeNameToAvg = modelSettingsList.stream()
				.collect(Collectors.groupingBy(ModelSettings::getModelName, Collectors
						.averagingDouble(setting -> setting.getMpg().getAvg())));
		List<Map.Entry<String, Double>> sortedByAvg = makeNameToAvg.entrySet().stream()
				.sorted(Map.Entry.<String, Double>comparingByValue().reversed()).collect(Collectors.toList());
		List<Map.Entry<String, Double>> topEntries = sortedByAvg.stream().limit(5).collect(Collectors.toList());
		topEntries.forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));



	}

	public static void printOption6(Collection<Make> makes) {
		Stream<ModelSettings> modelSettingsStream = makes.stream().flatMap(make -> make.getModelSettings().stream());

		Map<String, List<Double>> mpgListByClass = modelSettingsStream.collect(Collectors.groupingBy(ModelSettings::getvClass,
				Collectors.mapping(setting ->
						Double.valueOf(setting.getMpg().getAvg()), Collectors.toList())));

		Map<String, Double> medianPerVClass = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

		for (Map.Entry<String, List<Double>> entry : mpgListByClass.entrySet()) {
			medianPerVClass.put(entry.getKey(), calculateMedian(entry.getValue()));
		}
		medianPerVClass.forEach((key, value) -> System.out.println(key + " " + value));


	}

	public static void printOption7(Collection<Make> makes) {
		List<Make> reversedSortedMakes = makes.stream()
				.sorted(Comparator.comparing((Make make) -> make.getMakeName().toLowerCase())
						.reversed()).collect(Collectors.toList());
		for (Make make : reversedSortedMakes) {
			Map<Integer, Long> highwayNumberModels = make.getModelSettings().stream()
					.collect(Collectors.groupingBy(setting -> setting.getMpg().getHighway(), Collectors.counting()));

			highwayNumberModels.forEach((key, value)
					-> System.out.println(make.getMakeName() + "; highway MPG " + key + "; number of models " + value));
		}


	}

	public static void printOption8(Collection<Make> makes) {
		Map<String, Double[]> cityMpgMap = new HashMap<>();

		for (Make make : makes) {
			Set<ModelSettings> uniqueModelSettings = new HashSet<>();
			double sumCityMpg = 0;
			int countCityMpg = 0;

			for (ModelSettings modelSettings : make.getModelSettings()) {
				if (uniqueModelSettings.add(modelSettings)) {
					sumCityMpg += modelSettings.getMpg().getCity();
					countCityMpg++;
				}
			}

			double avgCityMpg;
			if (countCityMpg > 0) {
				avgCityMpg = sumCityMpg / countCityMpg;
			} else {
				avgCityMpg = 0;
			}

			cityMpgMap.put(make.getMakeName(), new Double[]{avgCityMpg});
		}

		List<String> topMakes = cityMpgMap.entrySet().stream()
				.sorted(Map.Entry.<String, Double[]>comparingByValue((a, b) -> Double.compare(b[0], a[0])))
				.limit(5)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());

		for (String make : topMakes) {
			System.out.println(make + ": " + cityMpgMap.get(make)[0]);
		}
	}


	private static double calculateMedian(List<Double> values) {
		Collections.sort(values);
		int size = values.size();

		if (size % 2 == 0) {
			return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
		} else {
			return values.get(size / 2);
		}
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("USAGE: java Tester <option> <filename> [<columnName> <value>]");
			System.exit(1);
		}

		Set<Make> makes = null;
		try {
			makes = loadCSV(args[1]);
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
			System.exit(1);
		}

		int option = readOption(args[0]);
		switch (option) {
			case 1 -> {
				if (args.length != 2) {
					System.err.println("USAGE: java Tester 1 <filename>");
					System.exit(1);
				}
				printOption1(makes);
			}
			case 2 -> {
				if (args.length != 4) {
					System.err.println("USAGE: java Tester 2 <filename> <columnName> <value>");
					System.exit(1);
				}
				printOption2(makes, args[2], args[3]);
			}
			case 3 -> {
				if (args.length != 2) {
					System.err.println("USAGE: java Tester 3 <filename>");
					System.exit(1);
				}
				printOption3(makes);
			}
			case 4 -> {
				if (args.length != 2) {
					System.err.println("USAGE: java Tester 4 <filename>");
					System.exit(1);
				}
				printOption4(makes);
			}
			case 5 -> {
				if(args.length != 2){
					System.err.println("USAGE: java Tester 4 <filename>");
					System.exit(1);
				}
				printOption5(makes);
			}
			case 6 -> {
				if (args.length != 2) {
					System.err.println("USAGE: java Tester 6 <filename>");
					System.exit(1);
				}
				printOption6(makes);
			}
			case 7 -> {
				if (args.length != 2) {
					System.err.println("USAGE: java Tester 7 <filename>");
					System.exit(1);
				}
				printOption7(makes);
			}
			case 8 -> {
				if (args.length != 2) {
					System.err.println("USAGE: java Tester 8 <filename>");
					System.exit(1);
				}
				printOption8(makes);
			}
			default -> System.err.println("Invalid option");
		}
	}
}



	





