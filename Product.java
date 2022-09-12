/*
 * Author: Archita Samudra
 * Roll No.: 2049
 * Module: Product
 * */

package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Product {
	// attributes of class Product
	int productId;
	int availableQuantity, soldQuantity;
	String productName, productCategory;
	double productPrice;
	
	static String separator = ", ";
	static String path = "../SAMPLE/src/practice/Products.txt";
	
	// add product
	public void addProduct(String productName, String productCategory, int availableQuantity, int soldQuantity, double productPrice) {		
		// validate available quantity, sold quantity, product price
		if(availableQuantity < 0 || soldQuantity < 0 || productPrice <= 0) {
			System.out.println("Invalid input! Failed to add product.");
			return;
		}
		
		// generate random product id
		int min = 1000, max = 2000;
		int productId = (int) (Math.random() * (max - min + 1) + min);  
		
		try {
			// check if product is already added
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  

			String line;  
			while((line = bufRead.readLine()) != null) {  
				if(line.contains(productName)) {
					System.out.println("Product " + productName + " already exists! Failed to add product.");
					bufRead.close();
					return;
				}
			}
			bufRead.close();
			
			// write product details into the file
			File writeFile = new File(path);
			BufferedWriter buf = new BufferedWriter(new FileWriter(writeFile, true));
            			
			buf.write(String.valueOf(productId));		
			buf.write(separator);
			buf.write(productName);		
			buf.write(separator);
			buf.write(productCategory);		
			buf.write(separator);
			buf.write(String.valueOf(availableQuantity));		
			buf.write(separator);
			buf.write(String.valueOf(soldQuantity));		
			buf.write(separator);
			buf.write(String.valueOf(productPrice));		
			buf.newLine();
			
			buf.close();
			
			// display success message
	        System.out.println("Product " + productName + " added successfully!"); 		
		}
		catch (Exception e) {
			System.out.println("Product " + productName + " not added successfully!"); 
			e.printStackTrace();
		}
	}
	
	// get product details by product name
	public String getProduct(String productName) {
		String line = "";  
		
		try {
			// check if product exists and if it exists then return the containing row
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	
			while((line = bufRead.readLine()) != null) {  
				if(line.contains(productName))
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
	public void getAllProducts() {
		try {  
			File readFile = new File(path);
			BufferedReader bufRead = new BufferedReader(new FileReader(readFile)); 
	
			System.out.println("------------------------------------- Products Available -------------------------------------");
			System.out.println("Product Id\tProduct Name\tCategory\tAvailable Qty\tSold Qty\tPrice");
			System.out.println("----------------------------------------------------------------------------------------------");
			
			String line;  
			String[] tokens = null;
			while((line = bufRead.readLine()) != null) {  
				tokens = line.split(separator);
				System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t" + tokens[2] + "\t" + tokens[3] + "\t\t" + tokens[4] + "\t\t" + tokens[5]);	
			}  
			
			bufRead.close();    
			
			// File is empty
			if(readFile.length() == 0) 
				System.out.println("************ NO PRODUCTS FOUND ************");
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	// delete product
	public void deleteProduct(String productName) {
		int flag = 0;
		try {  
			File inpFile = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(inpFile));  
			
			// temporary file
			File tempFile = new File("tempFile.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String line;  
			while((line = reader.readLine()) != null) {  
				// skip the row which contains the product name
				if(line.contains(productName)) {
					flag = 1;
					continue;
				}
				writer.write(line + System.getProperty("line.separator"));
			}  
			
			reader.close();
			writer.close();
			
			if(flag == 1) {
				tempFile.renameTo(inpFile); 			// rename temporary file
				System.out.println("Product " + productName + " deleted successfully!");
			}
			else
				System.out.println("Product " + productName + " not found");

		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	// modify product's available quantity, sold quantity and price
	public int modifyProduct(String productName, int newAvailableQty, int newSoldQty, double newPrice, int aFlag, int sFlag, int pFlag) {
		int flag = 0;
		
		// validate available quantity, sold quantity, product price
		if(newAvailableQty < 0 || newSoldQty < 0 || newPrice <= 0) 
			System.out.println("Invalid input! Failed to modify product.");
		
		try {  
			File inpFile = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(inpFile));  
			
			// temporary file
			File tempFile = new File("tempFile.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String line;  
			String[] tokens = null;
			while((line = reader.readLine()) != null) {  
				if(line.contains(productName)) {
					flag = 1;
	
					tokens = line.split(separator);
					
					// identify and modify attribute values 
					if(aFlag == 1)
						tokens[3] = String.valueOf(newAvailableQty);
					if(sFlag == 1)
						tokens[4] = String.valueOf(newSoldQty);
					if(pFlag == 1)
						tokens[5] = String.valueOf(newPrice);
					
					line = tokens[0] + separator + tokens[1] + separator + tokens[2] + separator + tokens[3] + separator + tokens[4] + separator + tokens[5];
				}
				writer.write(line + System.getProperty("line.separator"));
			}  
			
			reader.close();
			writer.close();
			
			if(flag == 1) {
				tempFile.renameTo(inpFile); 			// rename temporary file
				return 1;
			}
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		} 
		
		return 0;
	}
}
