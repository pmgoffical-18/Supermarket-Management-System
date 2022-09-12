/*
Name: Fatima Jafari
Roll no: 2069
Java Final Project

class name: BillingPerson
methods used:
	Add Billing Person
	Delete Billing Person
	View Billing Person
	Modify Billing Person (Password)
	Search Billing Person
*/

package practice;

//import packages
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class BillingPerson {
	
	int userId;
	String userName, userPassword;
	static String separator = ", ";
	static String path = "../SAMPLE/src/practice/BillingPersons.txt";// file path
	
	//function for generating random numbers
	public int randomNumber(int min, int max) {
		int num = (int) (Math.random() * (max - min + 1) + min);  	
		return num;
	}
	
	// add billing person
	public void addBillingPerson(String userName) {	
		int userId = randomNumber(6000, 7000);
		String userPassword = String.valueOf(userId) + userName;			// auto-generated password
		
		try {			
			File writeFile = new File(path);
			BufferedWriter buf = new BufferedWriter(new FileWriter(writeFile, true));
			
			// check if user name is already taken
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	
			String line;  
			while((line = bufRead.readLine()) != null) {  
				if(line.contains(userName)) {
					System.out.println("User name " + userName + " already exists! Failed to add billing person.");
					bufRead.close();
					return;
				}
			} 
			
			// append billing person information into the file
			buf.write(String.valueOf(userId));		
			buf.write(separator);
			buf.write(userName);		
			buf.write(separator);
			buf.write(userPassword);			
			buf.newLine();
			
			buf.close();
			
	        System.out.println("Billing person " + userName + " added successfully!"); 	
	
		}
		//handling the exception
		catch (Exception e) {
			System.out.println("Billing person " + userName +  " not added successfully!"); 
			e.printStackTrace();
		}
	}
	
	// get billing person details by user name
	public String getBillingPerson(String userName) {
		String line = null; 
		
		try {  
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	 
			while((line = bufRead.readLine()) != null) {  
				if(line.contains(userName)) {
					return line;
				}
			}  
			readFile.close();    	
		}  
		//handling the exception
		catch(Exception e) {  
			e.printStackTrace();  
		}
		return line; 
	}
	
	// view Billing person information (id,username,password)
	public void getAllBillingPersons() {
		int flag = 0;
		
		try {  
			FileReader readFile = new FileReader(path);  
			BufferedReader bufRead = new BufferedReader(readFile);  
	
			System.out.println("Cashier Id\tUser Name\t Password");
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
				System.out.println("************ NO BILLING PERSONS FOUND ************");
		}  
		//handling the exception
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	// delete Billman Person
	public void deleteBillingPersons(String userName) {
		int flag = 0;
		try {  
			File inpFile = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(inpFile));  
			
			// temporary file
			File tempFile = new File("tempFile.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String line;  
			while((line = reader.readLine()) != null) {  
				if(line.contains(userName)) {
					flag = 1;
					continue;
				}
				writer.write(line + System.getProperty("line.separator"));
			}  
			
			reader.close();
			writer.close();
			
			if(flag == 1) {
				tempFile.renameTo(inpFile); 	// rename temporary file
				System.out.println("Billing Person " + userName + " deleted successfully!");
			}
			else
				System.out.println("Billing Person " + userName + " not existing!");
		} 
		//handling the exception
		catch(Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	/* Once billing person is added, its id, user name cannot be changed */
	
	// Changing the password
	public void modifyBillingPerson(String userName, String oldPasswd, String newPasswd) {
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
				if(line.contains(userName) && line.contains(oldPasswd)) {
					flag = 1;
					
					tokens = line.split(separator);
					line = tokens[0] + separator + tokens[1] + separator + newPasswd;
				}
				writer.write(line + System.getProperty("line.separator"));
			}  
			reader.close();
			writer.close();
			
			if(flag == 1) {
				tempFile.renameTo(inpFile); 			// rename temporary file
				System.out.println("Password of " + userName + " modified successfully!");
			}
			else
				System.out.println("Username " + userName + " not existing!");
		} 
		//handling the exception
		catch(Exception e) {  
			e.printStackTrace();  
		} 				
	}
}

