/*
 * Author: Archita Samudra
 * Roll No.: 2049
 * Module: MainMenu
 * */

package practice;

import java.util.Scanner;

public class MainMenu {
	// customer main menu
	public static void displayCustomerMenu() {
		int choice;
		String separator = ", ";
		
		while(true) {
			System.out.println();
			System.out.println("**** Customer Main Menu ****");
			System.out.println("1. Add Customer");
			System.out.println("2. Search Customer");
			System.out.println("3. Modify Customer");
			System.out.println("4. View Customers");
			System.out.println("5. Delete Customer");
			System.out.println("6. Go back");
			
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			sc.nextLine();
			
			Customer c1 = new Customer();
			
			String custName, custMobNo;

			switch(choice) {
				case 1:
					// Add customer
					System.out.println("Enter customer name: ");
					custName = sc.nextLine();
					
					System.out.println("Enter customer's mobile number: ");
					custMobNo = sc.nextLine();
					
					c1.addCustomer(custName, custMobNo);
					
					break;
					
				case 2:
					// Search Customer
					String result;
					
					System.out.println("Enter customer's mobile number: ");
					custMobNo = sc.nextLine();
					
					result = c1.getCustomer(custMobNo);
					if(result == null)
						System.out.println("Mobile No. " + custMobNo + " not existing!");
					else {
						String[] tokens = null;
						tokens = result.split(separator);
						System.out.println("*** Customer Details ***");
						System.out.println("Customer Id: " + tokens[0]);
						System.out.println("Customer Name: " + tokens[1]);
						System.out.println("Customer Mobile No.: " + tokens[2]);
					}
					break;
					
				case 3:
					// Modify Customer
					System.out.println("Enter customer's mobile number: ");
					custMobNo = sc.nextLine();
					
					System.out.println("Enter customer's new mobile number: ");
					String newMobNo = sc.nextLine();
					
					c1.modifyCustomer(custMobNo, newMobNo);
					
					break;
					
				case 4:
					// View Customers
					c1.getAllCustomers();
					
					break;
					
				case 5:
					// Delete Customer
					System.out.println("Enter customer's mobile number: ");
					custMobNo = sc.nextLine();
					
					c1.deleteCustomer(custMobNo);
					
					break;
					
				case 6: 
					// Go back to previous menu
					return;
					
				default:
					// invalid choice
					System.out.println("Invalid choice! Enter a numnber between 1 to 6.");
			}			
		}
	}
	
	// product main menu
	public static void displayProductMenu() {
		int choice;
		String separator = ", ";
		
		while(true) {
			System.out.println();
			System.out.println("**** Product Main Menu ****");
			System.out.println("1. Add Product");
			System.out.println("2. Search Product");
			System.out.println("3. Modify Product");
			System.out.println("4. View Products");
			System.out.println("5. Delete Product");
			System.out.println("6. Go back");
			
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			sc.nextLine();
			
			Product p1 = new Product();
			
			String productName, productCategory;
			int availableQuantity = 0, soldQuantity = 0;
			double productPrice = 0;
			
			switch(choice) {
				case 1:
					// Add product					
					System.out.println("Enter product name: ");
					productName = sc.nextLine();
					
					System.out.println("Enter product category: ");
					productCategory = sc.nextLine();
					
					System.out.println("Enter product available quantity: ");
					availableQuantity = sc.nextInt();
					
					System.out.println("Enter product sold quantity: ");
					soldQuantity = sc.nextInt();
					
					System.out.println("Enter product price: ");
					productPrice = sc.nextDouble();
					
					p1.addProduct(productName, productCategory, availableQuantity, soldQuantity, productPrice);
					
					break;
					
				case 2:
					// Search product
					String result;
					
					System.out.println("Enter product name: ");
					productName = sc.nextLine();
					
					result = p1.getProduct(productName);
					if(result == null)
						System.out.println("Product " + productName + " not found!");
					else {
						String[] tokens = null;
						tokens = result.split(separator);
						System.out.println("********* Product Details *********");
						System.out.println("Product Id: " + tokens[0]);
						System.out.println("Product Name: " + tokens[1]);
						System.out.println("Product Category: " + tokens[2]);
						System.out.println("Product Available Quantity: " + tokens[3]);
						System.out.println("Product Sold Quantity: " + tokens[4]);
						System.out.println("Product Price: " + tokens[5]);
					}
					break;
					
				case 3:
					// Modify product
					System.out.println("Enter product name: ");
					productName = sc.nextLine();
					
					System.out.println("Would you like to modify available quantity? 1(yes)/0(no): ");
					int optA = sc.nextInt();
					
					System.out.println("Would you like to modify sold quantity? 1(yes)/0(no): ");
					int optS = sc.nextInt();
					
					System.out.println("Would you like to modify price? 1(yes)/0(no): ");
					int optP = sc.nextInt();
 									
					sc.nextLine();
					
					if(optA == 1) {
						System.out.println("Enter product new available quantity: ");
						availableQuantity = sc.nextInt();
					}
					
					if(optS == 1) {
						System.out.println("Enter product new sold quantity: ");
						soldQuantity = sc.nextInt();
					}
					
					if(optP == 1) {
						System.out.println("Enter new product price: ");
						productPrice = sc.nextDouble();
					}
					
					int temp = p1.modifyProduct(productName, availableQuantity, soldQuantity, productPrice, optA, optS, optP);
					if(temp == 1)
						System.out.println("Product " + productName + " modified successfully!");
					else
						System.out.println("Product " + productName + " not modified successfully!");
					
					break;
					
				case 4:
					// View Products
					p1.getAllProducts();
					
					break;
					
				case 5:
					// Delete Product
					System.out.println("Enter product name: ");
					productName = sc.nextLine();
					
					p1.deleteProduct(productName);
					
					break;
					
				case 6: 
					// Go back to previous menu
					return;
					
				default:
					// invalid choice
					System.out.println("Invalid choice! Enter a number between 1 to 6.");
			}			
		}
	}
	
	// Billing person main menu
	public static void displayBillingPersonMenu() {
		int choice;
		String separator = ", ";
		
		while(true) {
			System.out.println();
			System.out.println("**** Billing Person Main Menu ****");
			System.out.println("1. Add Billing Person");
			System.out.println("2. Search Billing Person");
			System.out.println("3. View Billing Persons");
			System.out.println("4. Delete Billing Person");
			System.out.println("5. Go back to Admin Menu");
			
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			sc.nextLine();
			
			BillingPerson b = new BillingPerson();
			
			String userName;

			switch(choice) {
				case 1:
					// Add billing person
					System.out.println("Enter billing persons user name: ");
					userName = sc.nextLine();
					
					b.addBillingPerson(userName);
					
					break;
					
				case 2:
					// Search Billing Person
					System.out.println("Enter billing persons user name: ");
					userName = sc.nextLine();
					
					String result = b.getBillingPerson(userName);
					
					if(result == null)
						System.out.println("Billing person " + userName + " not existing!");
					else {
						String[] tokens = null;
						tokens = result.split(separator);
						System.out.println("*** Billing Person Details ***");
						System.out.println("Billing Person Id: " + tokens[0]);
						System.out.println("Billing Person Name: " + tokens[1]);
						System.out.println("Billing Person Password: " + tokens[2]);
					}
					
					break;
					
				case 3:
					// View Billing Persons
					b.getAllBillingPersons();
					
					break;
					
				case 4:
					// Delete Billing Person
					System.out.println("Enter billing persons user name: ");
					userName = sc.nextLine();
					
					b.deleteBillingPersons(userName);
					
					break;
					
				case 5:
					// Go back to admin Menu
					return;
					
				default:
					// invalid choice
					System.out.println("Invalid choice! Enter a numnber between 1 to 5.");
			}			
		}
	}
	
	// transaction main menu
	public static void displayTransactionMenu() {
		int choice;
		String separator = ", ";
		
		while(true) {
			System.out.println();
			System.out.println("**** Transaction Main Menu ****");
			System.out.println("1. Add Transaction");
			System.out.println("2. Search Transaction");
			System.out.println("3. View Transaction");
			System.out.println("4. Go back");
			
			// read choice
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			sc.nextLine();
			
			Transaction t1 = new Transaction();

			switch(choice) {
				case 1:
					// Add transaction	
					System.out.println("Enter customer's mobile number: ");
					String custMobNo = sc.nextLine();
					
					Customer c2 = new Customer()
;					
					String result = c2.getCustomer(custMobNo);
					if(result == null)
						System.out.println("Mobile No. " + custMobNo + " not existing!");
					else {
						String[] tokens = null;
						tokens = result.split(separator);
						t1.addTransaction(tokens[1], custMobNo);
					}
					break;
					
				case 2:
					// Search transaction
					System.out.println("Enter transaction id: ");
					String transId = sc.nextLine();
					
					t1.getTransaction(transId);

					break;

					
				case 3:
					// View transactions
					t1.getAllTransactions();
					break;
					
					
				case 4: 
					// Go back to cashier menu
					return;
					
				default:
					// invalid choice
					System.out.println("Invalid choice! Enter a numnber between 1 to 4.");
			}			
		}
	}
	
	// discounts main menu
	public static void displayDiscountMenu() {
		int choice;
		String separator = ", ";
		
		while(true) {
			System.out.println();
			System.out.println("**** Discount Main Menu ****");
			System.out.println("1. Add Discount");
			System.out.println("2. View Discounts");
			System.out.println("3. Delete Discount");
			System.out.println("4. Go back to Admin Menu");
			
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			sc.nextLine();
			
			Discount d = new Discount();

			switch(choice) {
				case 1:
					// Add discount offer	
					System.out.println("Enter discount name: ");
					String discountName = sc.nextLine();
					
					System.out.println("Enter discount money: ");
					double discountMoney = sc.nextDouble();
										
					d.addDiscount(discountMoney, discountName);
					
					break;
					
				case 2:
					// view discount offers
					d.getAllDiscounts();

					break;

					
				case 3:
					// delete discount offers
					System.out.println("Enter discount id: ");
					String discountId = sc.nextLine();
					
					d.deleteDiscount(discountId);
					break;
					
					
				case 4: 
					// Go back to previous menu
					return;
					
				default:
					// invalid choice
					System.out.println("Invalid choice! Enter a numnber between 1 to 4.");
			}			
		}
	}
	
	// cashier menu
	public static void chooseMenuCashier() {
		int choice;
		BillingPerson b = new BillingPerson();
		
		while(true) {
			System.out.println();
			System.out.println("********** Cashier Menu **********");
			System.out.println("1. Display Customer Menu");
			System.out.println("2. Display Transaction Menu");
			System.out.println("3. Display Product Menu");
			System.out.println("4. Change password");
			System.out.println("5. Go Back to Main Menu");
			
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1:
					displayCustomerMenu();
					break;
					
				case 2:
					displayTransactionMenu();
					break;
					
				case 3:
					 displayProductMenu();
					break;
					
				case 4: 
					System.out.println("Enter user name: ");
					String userName = sc.nextLine();
					
					System.out.println("Enter old password: ");
					String oldPasswd = sc.nextLine();
					
					System.out.println("Enter new password: ");
					String newPasswd = sc.nextLine();
					
					b.modifyBillingPerson(userName, oldPasswd, newPasswd);
//					line.contains(oldPasswd)
					break;
					
				case 5: 
					// Go back to main menu
					return;
					
				default:
					// invalid choice
					System.out.println("Invalid choice! Enter a numnber between 1 to 5.");
			}	

		}
	}

	// admin menu
	public static void chooseMenuAdmin() {
		int choice;
		
		while(true) {
			System.out.println();
			System.out.println("********** Admin Menu **********");
			System.out.println("1. Display Customer Menu");
			System.out.println("2. Display Transaction Menu");
			System.out.println("3. Display Product Menu");
			System.out.println("4. Display Discount Menu");
			System.out.println("5. Display Billing Person Menu");
			System.out.println("6. Go Back to Main Menu");
			
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1:
					displayCustomerMenu();
					break;
					
				case 2:
					displayTransactionMenu();
					break;
					
				case 3:
					 displayProductMenu();
					break;
					
				case 4:
					 displayDiscountMenu();
					break;
					
				case 5:
					 displayBillingPersonMenu();
					break;
					
				case 6: 
					// Go back to main menu
					return;
					
				default:
					// invalid choice
					System.out.println("Invalid choice! Enter a numnber between 1 to 6.");
			}	

		}
	}

	public static void main(String[] args) {
		int choice;
		String separator = ", ";
		
		System.out.println("********************* WELCOME TO THE TECHIES SUPERMARKET *********************");
		
		while(true) {
			// main menu
			System.out.println();
			System.out.println("********** Main Menu **********");
			System.out.println("1. Login as Cashier");
			System.out.println("2. Login as Admin");
			System.out.println("3. Exit");
			
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			
			sc.nextLine();
			
			String userName, passwd;
			
			switch(choice) {
				case 1:
					// login as cashier					
					System.out.println("Enter user name: ");
					userName = sc.nextLine();
					
					// check for valid Billing person user name
					BillingPerson b = new BillingPerson();
					String result = b.getBillingPerson(userName);
					if(result == null)
						System.out.println("Billing person " + userName + " not existing!");
					else {
						// check for valid Billing person password
						String[] tokens = null;
						tokens = result.split(separator);
						
						if(tokens[1].equals(userName)) {
							System.out.println("Enter password: ");
							passwd = sc.nextLine();
							
							if(tokens[2].equals(passwd)) {
								System.out.println("Login successfull");
								chooseMenuCashier();
							}
								
							else
								System.out.println("Password incorrect! Login failed.");
						}	
						else 
							System.out.println("User name does not exists! Login failed.");	
					}
					
					break;
				
				case 2:
					// login as admin
					String adminName = "abcd", adminPasswd = "1234";
					
					System.out.println("Enter admin user name: ");
					userName = sc.nextLine();
					
					System.out.println("Enter admin password: ");
					passwd = sc.nextLine();
					
					if(adminName.equals(userName) && adminPasswd.equals(passwd)) {
						System.out.println("Login successfull");
						System.out.println();
						chooseMenuAdmin();
					}
					else 
						System.out.println("Login failed!");	

					break;
					
				case 3:
					// exit
					System.out.println("Exiting...");
					System.exit(0);
					break;
					
				default:
					// invalid choice
					System.out.println("Invalid choice! Enter a numnber between 1 to 3.");
			}			
		}
	}

}


