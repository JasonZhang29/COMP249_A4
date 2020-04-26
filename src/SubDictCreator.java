// ----------------------------------------------
// COMP 249
// Assignment 4
// Written by: Xin Yuan Zhang (26373127)
// ----------------------------------------------
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SubDictCreator {

	public static void main(String[] args) {
		// ArrayList to store string parsed from input file
		ArrayList<String> arrayDict = new ArrayList<>();
		// Scanner for user input file name
		Scanner keyIn = new Scanner(System.in);
		System.out.print("Please enter input file name: ");
		String finName = keyIn.next();
		// Scanner for input file processing
		Scanner fileIn = null;
		PrintWriter fout = null;
		try {
			fileIn = new Scanner(new FileInputStream(finName));
			fout = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("\nCouldn't find input file!");
			System.exit(0);
		}
		while (fileIn.hasNext()) {
			String str = fileIn.next().toUpperCase();
			if (str.contains("?")) {
				str = str.substring(0, str.indexOf("?"));
			}
			if (str.contains(":")) {
				str = str.substring(0, str.indexOf(":"));
			}
			if (str.contains(",")) {
				str = str.substring(0, str.indexOf(","));
			}
			if (str.contains(";")) {
				str = str.substring(0, str.indexOf(";"));
			}
			if (str.contains("!")) {
				str = str.substring(0, str.indexOf("!"));
			}
			if (str.contains(".")) {
				str = str.substring(0, str.indexOf("."));
			}
			if (str.contains("'")) {
				str = str.substring(0, str.indexOf("'"));
			}
			if (str.length() > 1 && str.substring(0,2).contentEquals("MC")) {
				str = "MC\u00B2";
			}
			if (str.length() == 1 && !(str.contentEquals("A") || str.contentEquals("I"))) {
				continue;
			}
			if (str.chars().anyMatch(Character::isDigit)) {
				continue;
			}
			if (!arrayDict.contains(str)) {
				arrayDict.add(str);
			}
		}
		arrayDict.sort(null);
		fout.write("The document produced this sub-dictionary, which includes " + arrayDict.size() + " entries.\n\n");
		String ind = " ";
		for (int i = 0; i < arrayDict.size(); ++i) {
			if (!arrayDict.get(i).substring(0,1).contentEquals(ind)) {
				fout.write("\n" + arrayDict.get(i).substring(0,1) + "\n");
				fout.write("==\n");
				ind = arrayDict.get(i).substring(0,1);
			}
			fout.write(arrayDict.get(i));
			fout.write("\n");
		}
		System.out.println("\nFile is processed. Please review \"SubDictionary.txt\" for the result.");
		fileIn.close();
		keyIn.close();
		fout.close();
	}

}
