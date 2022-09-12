/*
Author: Aaron de Sousa
Roll No.: 2062
Module: Transaction
*/

package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;

public class Transaction {
	int transId;
	Date transDate;
	static String separator = ", ";
	static String path = "../SAMPLE/src/practice/Transactions.txt";
	
	// Add products to be purchased 
	public void addTransaction(String custName, String custMobNo) {
		String productName, result, discountOffer;
		int productQty, availableQty, soldQty, opt = 1;
		double price, totalBill = 0;
		
		int min = 8000, max = 9000;
		int transId = (int) (Math.random() * (max - min + 1) + min);  // generate random number for transId
		
		transDate = new Date();
		Scanner sc = new Scanner(System.in);
		Product p = new Product();
		Discount d = new Discount();
		
		try {
			File writeFile = new File(path);
			BufferedWriter buf = new BufferedWriter(new FileWriter(writeFile, true));
			
			buf.write(String.valueOf(transId));		
			buf.write(separator);
			buf.write(transDate.toString());		
			buf.write(separator);
			buf.write(custName);		
			buf.write(separator);
			buf.write(custMobNo);		
			buf.write(separator);
		
			while(opt == 1) {
				System.out.println("Enter the product to be purchased: ");
				productName = sc.nextLine();
				
				result = p.getProduct(productName);
				if(result == null)
					System.out.println("Product " + productName + " not found!");
				else {
					System.out.println("Enter the product quantity to be purchased: ");
					productQty = sc.nextInt();
					
					String[] tokens = result.split(separator);
					
					availableQty = Integer.valueOf(tokens[3]);
					soldQty = Integer.valueOf(tokens[4]);
					price = Double.valueOf(tokens[5]);
					
					if(productQty <= availableQty) {
						availableQty -= productQty;
						soldQty += productQty;
						totalBill += (price * productQty); 
						
						// updating available and sold quantity
				        p.modifyProduct(productName, availableQty, soldQty, price, 1, 1, 0);
				
						buf.write(productName);		
						buf.write(separator);
						buf.write(String.valueOf(productQty));		
						buf.write(separator);
						buf.write(String.valueOf(price * productQty));		
						buf.write(separator);
						
				        System.out.println("Product " + productName + " added successfully!");
					}
					else
						System.out.println("Insufficient quantity!");
					
					System.out.println("Would you like to add more products? 1(yes)/0(no)");
					opt = sc.nextInt();
					sc.nextLine();
				}
			}
			
			buf.write(String.valueOf(totalBill));
			buf.write(separator);
			
			discountOffer = d.getDiscount(String.valueOf(totalBill));
			if(discountOffer != null)
				buf.write(discountOffer);
			else 
				buf.write("No discount availed!");
			buf.write(separator);	
			
			buf.newLine();
			buf.close();
			
			System.out.println("Transaction added successfully!"); 
			System.out.println();
			getTransaction(String.valueOf(transId));
			
		}
		catch (Exception e) {
			System.out.println("Transaction not added successfully!"); 
			e.printStackTrace();
		}
	}
	
	// Generate Transaction/Bill
	public void getTransaction(String transId) {
		String line = null; 
		
		try {  
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	 
			String[] tokens = null;
			while((line = bufRead.readLine()) != null) {  
				if(line.contains(transId)) {
					tokens = line.split(separator);
				}
			}  
			readFile.close();    
			
			if(tokens != null) {
				System.out.println("********* Transaction Details *********");
				System.out.println("Transaction Id: " + tokens[0] + "\t\tTransaction Date: " + tokens[1]);
				System.out.println("Customer Name: " + tokens[2] + "\t\tCustomer Mob No.: " + tokens[3]);
				
				System.out.println("--------- Product Purchased ---------");
				System.out.println("Product Name\tQuantity\tPrice");
				System.out.println("------------------------------------------");
				for(int i = 4; i < tokens.length - 2; i += 3)
					System.out.println(tokens[i] + "\t\t" + tokens[i + 1] + "\t\t" + tokens[i + 2]);
				
				System.out.println("------------------------------------------");
				System.out.println("Total Bill: " + tokens[tokens.length - 2] + "\t\tDiscount Availed: " + tokens[tokens.length - 1]);
			}
			else 
				System.out.println("Transaction " + transId + " not existing!");
			
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		}
	}
	
	// Display all transactions/bills
	public void getAllTransactions() {
		String line = null; 
		int flag = 0;
		
		try {  
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	 
			String[] tokens = null;
			while((line = bufRead.readLine()) != null) {  
				flag = 1;
				tokens = line.split(separator);
				getTransaction(tokens[0]);
				System.out.println();
			}  
			readFile.close();    
			
			if(flag == 0) 
				System.out.println("************ NO TRANSACTIONS FOUND ************");
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		}
	}
}