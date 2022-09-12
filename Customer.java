/*
Author: Pushkar Gosavi
Roll no: 2025
Module Name: Customer

*/

package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Customer {
	int custId;
	String custName, custMobNo;
	static String separator = ", ";
	static String path = "../SAMPLE/src/practice/Customers.txt";
	
	// add customer
	public void addCustomer(String custName, String custMobNo) {		
		int min = 3000, max = 4000;
		int custId = (int) (Math.random() * (max - min + 1) + min);  // generate random customer id
		
		// customer not added if length of mobile no. != 10
		if(custMobNo.length() != 10){
			System.out.println("Customer mobile no. is invalid! Failed to add customer.");
			return;
		}
		
		try {
			File writeFile = new File(path);
			BufferedWriter buf = new BufferedWriter(new FileWriter(writeFile, true));
			
			// check if mobile no. is already taken
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	
			String line;  
			while((line = bufRead.readLine()) != null) {  
				if(line.contains(custMobNo)) {
					System.out.println("Mobile no. " + custMobNo + " already exists! Failed to add customer.");
					bufRead.close();
					return;
				}
			}  
			
			// append customer details into the file
			buf.write(String.valueOf(custId));		
			buf.write(separator);
			buf.write(custName);		
			buf.write(separator);
			buf.write(custMobNo);			
			buf.newLine();
			
			buf.close();
			
	        System.out.println("Customer " + custName + " added successfully!"); 	
	
		}
		catch (Exception e) {
			System.out.println("Customer " + custName + " not added successfully!"); 
			e.printStackTrace();
		}
	}
	
	// get billing details by customer's mobile no.
	public String getCustomer(String custMobNo) {
		String line = "";
		
		try {  
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
			
			while((line = bufRead.readLine()) != null) {  
				if(line.contains(custMobNo))
					return line;
			}  
			
			readFile.close();    
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
		return line;
	}
	
	// view product details of all products
	public void getAllCustomers() {
		int flag = 0;
		
		try {  
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	
			System.out.println("Customer Id\tCustomer Name\tMobile No.");
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
				System.out.println("************ NO CUSTOMERS FOUND ************");
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	// delete product
	public void deleteCustomer(String custMobNo) {
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
				if(line.contains(custMobNo)) {
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
				System.out.println("Customer " + tokens[1] + " deleted successfully!");
			}
			else
				System.out.println("Mobile no. " + custMobNo + " not existing!");

		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	// modify customer's mobile no.
	public void modifyCustomer(String custMobNo, String newMobNo) {
		int flag = 0;
		
		// check if the length of new mobile no. = 10
		if(newMobNo.length() != 10) {
			System.out.println("Customer mobile no. is invalid! Failed to modify customer.");
			return;
		}
		
		try {  
			File inpFile = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(inpFile));  
			
			// temporary file
			File tempFile = new File("tempFile.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			
			String line; 
			
			// cannot replace the current mobile no with a new mobile no, if the new mobile no is already taken
			BufferedReader tempReader = new BufferedReader(new FileReader(inpFile));  
			while((line = tempReader.readLine()) != null) {  
				if(line.contains(newMobNo)) {
					System.out.println("Mobile no. " + newMobNo + " already exists!");
					tempReader.close();
					return;
				}
			} 
		
			String[] tokens = null;
			while((line = reader.readLine()) != null) {  
				if(line.contains(custMobNo)) {
					flag = 1;
					
					tokens = line.split(separator);
					line = tokens[0] + separator + tokens[1] + separator + newMobNo;
				}
				writer.write(line + System.getProperty("line.separator"));
			}  
			
			reader.close();
			writer.close();
			
			if(flag == 1) {
				tempFile.renameTo(inpFile); 			// rename temporary file
				System.out.println("Customer " + tokens[1] + " modified successfully!");
			}
			else
				System.out.println("Mobile no. " + custMobNo + " not existing!");
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
}
