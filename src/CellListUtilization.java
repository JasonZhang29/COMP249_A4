// ----------------------------------------------
// COMP 249
// Assignment 4
// Written by: Xin Yuan Zhang (26373127)
// ----------------------------------------------
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CellListUtilization {

	public static void main(String[] args) {

		CellList cl1 = new CellList();
		Scanner fin = null;
		CellPhone cell = null;
		try {
			fin = new Scanner(new FileInputStream("/Users/jasonzhang/Documents/Cell_Info.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find Cell info file!");
		}
		while (fin.hasNext()) {
			String cellInfo = fin.nextLine();
			StringTokenizer st = new StringTokenizer(cellInfo);
			long serialNum = Long.parseLong(st.nextToken());
			String brand = st.nextToken();
			double price = Double.parseDouble(st.nextToken());
			int year = Integer.parseInt(st.nextToken());
			cell = new CellPhone(serialNum, brand, price, year);
			cl1.addToStart(cell);
		}
		cl1.showContents();
		Scanner keyIn = new Scanner(System.in);
		System.out.print("\nPlease enter a few serialNumber to search(q to quit): ");
		while (keyIn.hasNextLine()) {
			String str = keyIn.nextLine();
			if (str.contentEquals("q")) {
				break;
			}
			Long serial = Long.parseLong(str);
			cl1.find(serial);
		}
		
		System.out.println("\nBelow is the result of copy constructor: ");
		CellList cl2 = new CellList(cl1);
		cl2.showContents();
		
		System.out.println("\nBelow is the result of functions equals(): ");
		System.out.println(cl2.equals(cl1));
		
		System.out.println("\nBelow is the result of function addToStart(): ");
		CellPhone cell1 = new CellPhone(1234567, "Google", 1499.99, 2020);
		cl2.addToStart(cell1);
		cl2.showContents();
		
		System.out.println("\nBelow is the result of function insertAtIndex(): ");
		CellPhone cell2 = new CellPhone(2345678, "Apple", 599.99, 2020);
		cl2.insertAtIndex(cell2, 2);
		cl2.showContents();
		
		System.out.println("\nBelow is the result of function deleteFromIndex(): ");
		cl2.deleteFromIndex(18);
		cl2.showContents();
		
		System.out.println("\nBelow is the result of function deleteFromStart(): ");
		cl2.deleteFromStart();
		cl2.showContents();
		
		System.out.println("\nBelow is the result of function replaceAtIndex(): ");
		CellPhone cell3 = new CellPhone(3456789, "OnePlus", 1299.99, 2020);
		cl2.replaceAtIndex(cell3, 5);
		cl2.showContents();

		
		System.out.println("\nBelow is the result of function contains(): ");
		System.out.println(cl2.contains(3456789));
		
		fin.close();
		keyIn.close();
	}

}
