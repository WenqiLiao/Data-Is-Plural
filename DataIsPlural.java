package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is the program searching for DataSet . The program is interactive.
 * When the program is executed the name of the input file containing the list
 * of DataSet is provided as the program's single command line argument. The
 * data in this file serves as a database of all the DataSet. In the interactive
 * part, the user enters keywords which includes title, descriptions and URL (or
 * combination of either two of them). The program responds by printing data
 * contain the keyword.
 * 
 * @author Wenqi Liao
 * @version 03/08/2021
 *
 */
public class DataIsPlural {

	/**
	 * The main() method of this program.
	 * 
	 * @param args array of Strings provided on the command line when the program is
	 *             started; the first string should be the name of the input file
	 *             containing the list of named DataSet.
	 */
	public static void main(String[] args) throws URISyntaxException, MalformedURLException {

		// verify that the command line argument exists
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		// verify that command line argument contains a name of an existing file
		File dataFile = new File(args[0]);
		if (!dataFile.exists()) {
			System.err.println("Error: the file " + dataFile.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}

		if (!dataFile.canRead()) {
			System.err.println("Error: the file " + dataFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// open the file for reading
		Scanner inDatas = null;

		try {
			inDatas = new Scanner(dataFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file " + dataFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		CSV csv = new CSV(inDatas);

		DataSetList list = new DataSetList();
		ArrayList<String> info = csv.getNextRow();
		DataSet ds = null;
		String title = null;
		String text = null;
		String links = null;
		String hatTips = null;
		String date = null;

		int rows = 0;
		info = csv.getNextRow();
		while (rows < csv.getNumOfRows()) {
			rows++;
			try {

				date = info.get(0);
				title = info.get(2);
				text = info.get(3);
				links = info.get(4);

				if (info.size() == 6) {
					hatTips = info.get(5);
				}

			} catch (NoSuchElementException ex) {

				continue;
			}

			try {
				String[] linkSplit = links.split("\n");
				ArrayList<URL> url = new ArrayList<URL>();
				for (String trans : linkSplit) {
					url.add(new URL(trans));
				}
				ds = new DataSet(title, text, url);
				if (date != null && date.length() > 0) {
					String[] dateSplit = date.split("\\.");
					int year = Integer.parseInt(dateSplit[0]);
					int month = Integer.parseInt(dateSplit[1]);
					int day = Integer.parseInt(dateSplit[2]);
					Date d = new Date(year, month, day);
					ds.setDate(d);
				}

				if (hatTips != null) {
					ds.setHatTips(hatTips);
					list.add(ds);
				}

			} catch (IllegalArgumentException | MalformedURLException e) {
				// ignore this exception and skip to the next line
			}
			info = csv.getNextRow();
		}

		// user interaction
		Scanner userInput = new Scanner(System.in);
		String userValue = "";
		String[] value;

		do {

			System.out.println("Enter which type you want to search(title/description/URL) and the keyword "
					+ "[typing example: title COVID; "
					+ "you can also combine 2 queries eg: title COVID url .gov] or \"quit\" to stop:");

			userValue = userInput.nextLine().toUpperCase();

			if (userValue.equals("QUIT")) {
				break;
			}
			value = userValue.split(" ");

			if (value.length != 2 && value.length != 4) {
				System.err.println("Invalid Input, try again.");
			} else {
				DataSetList temp = list;

				for (int i = 0; i < value.length; i += 2) {
					String type = value[i];
					String keyWord = value[i + 1];
					if (temp == null) {
						break;
					}
					if (type.equalsIgnoreCase("title")) {
						temp = temp.getByTitle(keyWord);
					} else if (type.equalsIgnoreCase("description")) {
						temp = temp.getByDescription(keyWord);
					} else if (type.equalsIgnoreCase("URL")) {
						temp = temp.getByURL(keyWord);
					} else {
						System.out.println("This is not a valid query. Try again.");
					}
				}
				if (temp == null) {
					System.out.println("No matches found. Try again.");
				} else {
					PrintString(temp);
				}
			}

		} while (!userValue.equalsIgnoreCase("quit"));

		userInput.close();
	}

	private static void PrintString(DataSetList list) {

		for (int i = 0; i < list.size(); i++) {
			DataSet output = list.get(i);
			if (output.getDate() == null) {
				System.out.println(output.getTitle() + "\n" + output.getDescription() + "\n" + output.getURL()
						+ "\n\n\n" + "-----");
			} else {
				System.out.println(output.getDate() + "\n" + output.getTitle() + "\n" + output.getDescription() + "\n"
						+ output.getURL() + "\n\n\n" + "-----");
			}
		}
	}

}
