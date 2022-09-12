/*
 * Author: Sayyed Afran
 * Roll No.: 2050
 * Module: Discount
 * */



package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Discount {
	int discountId;
	double discountMoney;
	String discountName;
	static String separator = ", ";
	static String path = "../SAMPLE/src/practice/Discounts.txt";
	
	public void addDiscount(double discountMoney, String discountName) {		
		int min = 11000, max = 12000;
		int discountId = (int) (Math.random() * (max - min + 1) + min);  // generate random discount id
		
		try {
			File writeFile = new File(path);
			BufferedWriter buf = new BufferedWriter(new FileWriter(writeFile, true));
						
			// append customer details into the file
			buf.write(String.valueOf(discountId));		
			buf.write(separator);
			buf.write(discountName);		
			buf.write(separator);
			buf.write(String.valueOf(discountMoney));			
			buf.newLine();
			
			buf.close();
			
	        System.out.println("Discount " + discountName + " added successfully!"); 	
		}
		catch (Exception e) {
			System.out.println("Discount " + discountName + " not added successfully!"); 
			e.printStackTrace();
		}
	}
	
	// get discount for the total bill obtained in the transaction
	public String getDiscount(String totalBill) {
		String line = null, bestDiscount = null;
		double max = 0;
		
		try {  
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
			
			String tokens[] = null;
			while((line = bufRead.readLine()) != null) {  
				tokens = line.split(separator);
				if(Double.valueOf(totalBill) >= Double.valueOf(tokens[2]) && Double.valueOf(tokens[2]) > max) {
					max = Double.valueOf(tokens[2]);
					bestDiscount = tokens[1];
				}
			}  
			
			readFile.close();    
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
		return bestDiscount;
	}
	
	// view discount details of all discount offers
	public void getAllDiscounts() {
		int flag = 0;
		
		try {  
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	
			System.out.println("Discount Id\tDiscount Name\tDiscount Money");
			System.out.println("--------------------------------------------------------------------------");
			
			String line;  
			String[] tokens = null;
			while((line = bufRead.readLine()) != null) {  
				flag = 1;
				tokens = line.split(separator);
				System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t" + tokens[2]);	
			}  
			
			readFile.close();    
			
			if(flag == 0) 
				System.out.println("************ NO DISCOUNTS FOUND ************");
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	// delete discount offer
	public void deleteDiscount(String discountId) {
		int flag = 0;
		try {  
			File inpFile = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(inpFile));  
			
			// temporary file
			File tempFile = new File("tempFile.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String line;  
			String[] tokens = null;
			while((line = reader.readLine()) != null) {  
				if(line.contains(discountId)) {
					flag = 1;
					tokens = line.split(separator);
					continue;
				}
				writer.write(line + System.getProperty("line.separator"));
			}  
			
			reader.close();
			writer.close();
			
			if(flag == 1) {
				tempFile.renameTo(inpFile); 			// rename temporary file
				System.out.println("Discount id " + tokens[0] + " deleted successfully!");
			}
			else
				System.out.println("Discount id " + discountId + " not existing!");

		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
	


}
