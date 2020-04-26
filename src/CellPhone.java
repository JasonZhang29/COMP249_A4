// ----------------------------------------------
// COMP 249
// Assignment 4
// Written by: Xin Yuan Zhang (26373127)
// ----------------------------------------------
import java.util.Scanner;

public class CellPhone {
	
	private long serialNum;
	private String brand;
	private int year;
	private double price;
	
	/**
	 * Constructor with parameters
	 * @param serialNum serial number (unique for every single phone)
	 * @param brand	cell brand
	 * @param year manufacture date
	 * @param price selling price
	 */
	public CellPhone(long serialNum, String brand, double price, int year) {
		this.setSerialNum(serialNum);
		this.setBrand(brand);
		this.setYear(year);
		this.setPrice(price);
	}
	/**
	 * copy constructor
	 * @param copyCell cell object being copied
	 * @param serialNum	new unique id
	 */
	public CellPhone(CellPhone copyCell, long serialNum) {
		this.setSerialNum(serialNum);
		this.setBrand(copyCell.brand);
		this.setYear(copyCell.year);
		this.setPrice(copyCell.price);
	}
	@Override
	public CellPhone clone() {
		System.out.print("Please enter a serial number: ");
		Scanner keyIn = new Scanner(System.in);
		Long serialNum = keyIn.nextLong();
		keyIn.close();
		return new CellPhone(serialNum, this.brand, this.price, this.year);
		
	}
	
	public void setSerialNum(long serialNum) {
		this.serialNum = serialNum;
	}
	
	public long getSerialNum() {
		return serialNum;
	}
	
	public void setBrand(String brand) {
		if (brand != null) {
			this.brand = brand;
		} else {
			throw new NullPointerException( );
		}
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	@Override
	public String toString() {
		String serialNumL = Long.toString(serialNum);
		while (serialNumL.length() != 7) {
			serialNumL = "0" + serialNumL; 
		}
		return "[" + serialNumL + ": " + brand + " " + price + "$ " + year + "]";
	}
	@Override
	public boolean equals(Object cmpCell) {
		if (cmpCell == null) {
			return false;
		} else if (cmpCell.getClass() != getClass()) {
			return false;
		} else {
			CellPhone cell = (CellPhone)cmpCell;
			return this.brand == cell.brand && this.price == cell.price && this.year == cell.year;
		}
	}
	
}
