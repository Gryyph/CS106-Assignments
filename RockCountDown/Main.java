package rockcountdown;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class Main {

	public static void main(String[] args) throws IOException {
		String fileName = readLine();
		String file = readFile(fileName);
		String[] fileSplit = file.split("\r\n");
		ArrayList<Song> newFile = new ArrayList<Song>();

		for (int i = 0; i < fileSplit.length; i++) {
			Song object = new Song(fileSplit[i]);
			newFile.add(object);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = fileSplit.length - 1; i >= 0; i--) {

			sb.append(newFile.get(i).getRank()).append("\t").append(newFile.get(i).getTitle()).append("\r\n");
		}
		println(sb + "\n" + "Complete");
	}

}
