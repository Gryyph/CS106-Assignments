package flightsproject;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class Main {
	static ArrayList<Flight> ArrayListOfFlight = new ArrayList<Flight>();


	public static ArrayList generatesArrayList(String fileName) throws IOException {
		List<String> words = readFileAsLines(fileName);
		// ArrayListOfFlight = new ArrayList<Flight>();

		for (int i = 0; i < words.size(); i++) {
			Flight object = new Flight(words.get(i));
			ArrayListOfFlight.add(object);
		}
		return ArrayListOfFlight;
	}


	public static void main(String[] args) throws IOException {
		String fileName = readLine();
		// String file = readFile(fileName);
		// String[] fileSplit = file.split("\n");

		printf("%-20s%-10s%-8s%-8s\n", "AIRLINE", "FROM", "TO", "PRICE");
		printf("%-20s%-10s%-8s%-8s\n", "-------", "----", "--", "-----");

		generatesArrayList(fileName);

		for (int i = 0; i < ArrayListOfFlight.size(); i++) {
			printf("%-20s%-10s%-8s%-8s\n", ArrayListOfFlight.get(i).getAirline(), ArrayListOfFlight.get(i).getFrom(),
					ArrayListOfFlight.get(i).getTo(), ArrayListOfFlight.get(i).getPrice());
		}

	}

}
