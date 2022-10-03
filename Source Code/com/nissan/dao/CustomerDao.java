package com.nissan.dao;

import java.util.ArrayList;
import java.util.Scanner;

import com.nissan.app.BankApp;
import com.nissan.bean.Customer;

public class CustomerDao {

	static ArrayList<Customer> customerList =  new ArrayList<>();
	
	public static void menu() {

		System.out.println("-----------------------------------------------");
		System.out.println(" 1. Existing Customer \n 2. New Customer \n 3.Go back");
		System.out.println("-----------------------------------------------");
		System.out.print(" Enter your choice:");

		
		//input from the user 
				Scanner input = new Scanner(System.in);
				switch (input.nextInt()) {
				case 1:
					//Menu for Existing customer
					Customer existCustomer;
					existCustomer=  getCustomer();
					CustomerMenu(existCustomer);
					break;
				case 2:
					//Add new customer
					AddCustomer();
					customerList.size();
					break;
				case 3:
					//Go back
					BankApp.main(null);
					break;
				default:
					System.out.println("Invalid entry.!!!");
					break;
				}
				input.close();
	}



	// Method for services provided for Existing customers
	private static void CustomerMenu(Customer existCustomer) {

		char choice;
		  do {
			  
			  System.out.println("-------------------------------------------------");
			  System.out.println("-         Hello "+existCustomer.getCustomerName()+"         -");
			  System.out.println("-------------------------------------------------");
			  System.out.print(" 1. Deposit Amount \n 2. Withdraw Amount \n 3. Show Balance \n 4. Transfer Money  \n 5. Update Detail \n 6. Exit \n");
			  System.out.println("-------------------------------------------------");
			  System.out.print("\n Enter your choice:");
				
				//input from the user 
						Scanner input = new Scanner(System.in);
						switch (input.nextInt()) {
						case 1:
							
							double depositAmount;
							System.out.print("Enter Amount to Deposit:");
							depositAmount = input.nextDouble();
							depositAmount(existCustomer,depositAmount);
							break;
							
						case 2:
							
							double withDrawAmount;
							System.out.print("Enter Amount to Withdraw:");
							withDrawAmount = input.nextDouble();		
							withdrawAmount(existCustomer,withDrawAmount);
							System.out.println("Please collect your cash.");

							break;
							
						case 3:
							displayBalance(existCustomer);
							break;
							
						case 4:
							transferMoney(existCustomer);
							break;
							
						case 5:
							updateDetail(existCustomer);
							break;
							
						case 6:
							System.out.println("Exiting....");
							System.exit(0);
						default:
							System.out.println("Invalid entry.!!!");
							break;
						}
			  
			System.out.print("Continue (y/n):");
			choice = input.next().charAt(0);
		} while (choice == 'Y'||choice=='y');
		  
		  System.out.println("Going Back to Main Menu");
		  menu();
	}
	
	// Method to update details of a customer
	private static void updateDetail(Customer existCustomer) {

		char choice;
		  do {
			  
			  System.out.println("-------------------------------------------------");
			  System.out.println("------------------Update Details-----------------");
			  System.out.println("-------------------------------------------------");
			  System.out.print(" 1. Mobile Number \n 2. Email");
			  System.out.println("-------------------------------------------------");
			  System.out.print("\n Enter your choice:");
				
				//input from the user 
						Scanner input = new Scanner(System.in);
						switch (input.nextInt()) {
						case 1:
							
							System.out.print("Enter new Mobile Number:");
							String newNumber = input.next();
							existCustomer.setMobileNumber(newNumber);
							System.out.println("Mobile number updated successfully.");
							break;
							
						case 2:
							
							System.out.print("Enter new Email:");
							String newEmail = input.next();
							existCustomer.setEmailId(newEmail);
							System.out.println("Email updated successfully.");
							break;
						
						default:
							System.out.println("Invalid entry.!!!");
							break;
						}
			  
			System.out.print("Continue (y/n):");
			choice = input.next().charAt(0);
		} while (choice == 'Y'||choice=='y');
		
	}

	//Method to transfer money from one Account to another
	private static void transferMoney(Customer fromCustomer) {
		
		Scanner userInput = new Scanner(System.in);

        Customer toCustomer;
        System.out.print("\n Enter Account No to amount needs to be transfered:");
        int userAccNo = userInput.nextInt();
        toCustomer=searchAccount(userAccNo);
        System.out.print("\n Enter transfer amount:");
        double transferAmount = userInput.nextDouble();
  
        if(toCustomer!=null) {       	
        	withdrawAmount(fromCustomer,transferAmount);
        	depositAmount(toCustomer,transferAmount);  	
        	
        }else {
        	
        	System.out.println("Invalid Account No. Going to Main Menu..");
        	CustomerMenu(fromCustomer);
        }
		
}

	// Method to display balance
	private static void displayBalance(Customer customerAccount) {

		System.out.println("Your Available Balance : " + customerAccount.getAccountBalance());

	}

	// Method to with draw amount
	private static void withdrawAmount(Customer customerAccount,double withDrawAmount) {

		Scanner input = new Scanner(System.in);
		double accountBalance = customerAccount.getAccountBalance();
		double availableBalance = accountBalance - customerAccount.getMinimumBalance();
		// checking for insufficient amounts
		if (withDrawAmount > availableBalance) {

			System.out.println("Insufficient Balance.");
		} else {

			if(withDrawAmount > 50000) {
				
				System.out.print("Enter Your Pan-Card Number:");
				String panNo = input.next();
				customerAccount.setPanNo(panNo);
			}
			accountBalance -= withDrawAmount;
			customerAccount.setAccountBalance(accountBalance);
		}
	}

	// Method to deposit amount to account
	private static void depositAmount(Customer customerAccount, double depositAmount) {

		Scanner input = new Scanner(System.in);
		// checking invalid entered amount
		if (depositAmount < 0 || depositAmount == 0) {
			System.out.println("Invalid Amount.");
		} else {

			if(depositAmount > 50000) {
				
				System.out.print("Enter Your Pan-Card Number:");
				String panNo = input.next();
				customerAccount.setPanNo(panNo);
			}
			double accountBalance = customerAccount.getAccountBalance();
			accountBalance += depositAmount;
			customerAccount.setAccountBalance(accountBalance);
			System.out.println("Amount Deposited.");
		}
	}

	public static Customer getCustomer() {
		
		// get input
        Scanner userInput = new Scanner(System.in);

        Customer existCustomer;
        System.out.print("\n Enter Your Account No:");
        int userAccNo = userInput.nextInt();
      
        existCustomer=searchAccount(userAccNo);
        if(existCustomer!=null) {
        	
        	System.out.print("\n Enter Your Pin:");
        	int userPin = userInput.nextInt();
        	if(checkUserPin(existCustomer,userPin)) {
        		return existCustomer;
        	}else {
        		System.out.println("Invalid pin. Going to Main Menu..");
        		menu();
        	}
        	
        }else {
        	
        	System.out.println("Invalid Account No. Going to Main Menu..");
        	menu();
        }
        
        return existCustomer;
        
	}

	//Method to check user pin
	private static boolean checkUserPin(Customer existCustomer, int userPin) {
		return existCustomer.getAtmPin() == userPin;
	}

	//Method to search customer
	private static Customer searchAccount(int userAccNo) {
		for (Customer customer : customerList) {
			if(customer.getAccountNo() == userAccNo) {
				return customer;
			}
		}
		return null;	
	}

	// Method to add a new customer 
	private static void AddCustomer() {
		
		Customer customer  = new Customer();
	
        // get input
        Scanner userInput = new Scanner(System.in);

        System.out.print("\n Enter Your Name:");
        customer.setCustomerName(userInput.next());

        System.out.print(" \n 1. Savings \n 2. Fixed \n Enter Account Type: ");
        switch (userInput.nextInt()) {
		case 1:			
			customer.setAccountType("Savings.");
			break;
		case 2:
			customer.setAccountType("Fixed.");
			break;
		default:
			System.out.println("Invalid Entry.");
			break;
		}
        System.out.print(" Enter Mobile Number:");
        customer.setMobileNumber(userInput.next());

        System.out.print(" Enter Email ID:");
        customer.setEmailId(userInput.next());

        System.out.print(" Enter Initial Amount to Deposit (Minimum 5000):");
        customer.setAccountBalance(userInput.nextDouble());
        // auto-generate account no
        customer.setAccountNo(customer.generateAccNo());
        
        //auto generate customer pin
        customer.setAtmPin(customer.generatePin());
     
        //adding customer to entire customer list
        customerList.add(customer);
        
        System.out.println();
        System.out.println(" Account Created Successfully.");
        System.out.println("--------------------------------------------");
        System.out.println("-   Please Note Your Account No and Pin:   -");
        System.out.println("--------------------------------------------");
        System.out.println(" Account NO: "+customer.getAccountNo()+"\n Account Pin: "+customer.getAtmPin());
        System.out.println("--------------------------------------------");
        System.out.println();
                
        //Go to Existing Customer Menu
        menu();
        
	}

	
	
}
