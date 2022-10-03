package com.nissan.dao;
import java.util.Scanner;
import com.nissan.app.BankApp;
import com.nissan.bean.Admin;
import com.nissan.bean.Customer;

public class AdminDao {

	public static void menu() {
		char choice;
		Customer customer;
		do {

			System.out.println("-------------------------------------------------");
			System.out.println("-                Admin Panel                    -");
			System.out.println("-------------------------------------------------");
			System.out.print(
					" 1. List One Customer \n 2. List all Customer \n 3. Delete Customer \n 4.Go Back \n 5. Exit \n");
			System.out.println("-------------------------------------------------");
			System.out.print("\n Enter your choice:");

			// input from the user
			Scanner input = new Scanner(System.in);
			switch (input.nextInt()) {
			case 1:
				//getting customer object to be displayed
				customer = CustomerDao.getCustomer();
				displayOneCustomer(customer);
				break;
			case 2:
				displayAllCustomer();
				break;
			case 3:
				//getting customer object to be displayed
				customer = CustomerDao.getCustomer();
				deleteCustomer(customer);
				break;

			case 4:
				// go back to main menu
				BankApp.main(null);
				break;

			case 5:
				System.out.println("Exiting....");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid entry.!!!");
				break;
			}

			System.out.print("Continue (y/n):");
			choice = input.next().charAt(0);
		} while (choice == 'Y' || choice == 'y');

		System.out.println("Going Back to Main Menu");
		menu();
	}

	// Deleting a customer
	private static void deleteCustomer(Customer customer) {
		customer.setDisabled(true);
		System.out.println("Customer Deleted.");
	}

	// Displaying one customer
	private static void displayOneCustomer(Customer customer) {
		if (customer.isDisabled()) {

			System.out.println("Customer Doest not exist.");

		} else {

			System.out.println(
					" -----------------------------------------------------------------------------------------------------");
			System.out.printf("%53s", "Customer List\n");
			System.out.println(
					" -----------------------------------------------------------------------------------------------------");
			System.out.printf("   %-15s %-15s %-15s %-30s %-15s \n", " Account No. ", "Name", "Phone", "Email",
					"Balance");
			System.out.println(
					" -----------------------------------------------------------------------------------------------------");

			System.out.printf("  %-15d %-15s %-15s %-30s %-15.2f\n", customer.getAccountNo(),
					customer.getCustomerName(), customer.getMobileNumber(), customer.getEmailId(),
					customer.getAccountBalance());

			System.out.println(
					" -----------------------------------------------------------------------------------------------------");

		}

		menu();

	}

	// method to check administrators pin
	public static boolean checkAdminPin() {

		Scanner input = new Scanner(System.in);
		System.out.print("Enter Your Secure Pin to Continue:");
		int userPin = input.nextInt();
		if (userPin == Admin.getADMIN_PIN()) {
			return true;
		} else {
			return false;
		}

	}

	// Method to display all customers
	private static void displayAllCustomer() {

		if (CustomerDao.customerList.size() == 0) {
			System.out.println("Customer List Empty.");
		} else {

			System.out.println(
					" -----------------------------------------------------------------------------------------------------");
			System.out.printf("%53s", "Customer List\n");
			System.out.println(
					" -----------------------------------------------------------------------------------------------------");
			System.out.printf("   %-15s %-15s %-15s %-30s %-15s \n", " Account No. ", "Name", "Phone", "Email",
					"Balance");
			System.out.println(
					" -----------------------------------------------------------------------------------------------------");

			for (Customer customer : CustomerDao.customerList) {
				if (customer.isDisabled()) {
					continue;
				} else {

					System.out.printf("  %-15d %-15s %-15s %-30s %-15.2f\n", customer.getAccountNo(),
							customer.getCustomerName(), customer.getMobileNumber(), customer.getEmailId(),
							customer.getAccountBalance());
				}
			}
			System.out.println(
					" -----------------------------------------------------------------------------------------------------");
		}
		menu();
	}
}
