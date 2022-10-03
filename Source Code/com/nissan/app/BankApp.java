package com.nissan.app;

import java.util.Scanner;

import com.nissan.dao.AdminDao;
import com.nissan.dao.CustomerDao;

public class BankApp {

	public static void main(String[] args) {

		System.out.println("-----------------------------------------------");
		System.out.println("-                Welcome To ATM               -");
		System.out.println("-----------------------------------------------");
		System.out.print(" 1. Admin Panel \n 2. Customer Panel \n 3. Exit\n");
		System.out.print("-----------------------------------------------");
		
System.out.print("\n Enter your choice:");
		
		//input from the user 
				Scanner input = new Scanner(System.in);
				switch (input.nextInt()) {
				case 1:
					//go to Admin menu
					if(AdminDao.checkAdminPin()) {
						AdminDao.menu();
					}else {
						
						System.out.println("Incorrect Password!!!\n Try again.");
					}
					break;
				case 2:
					//go to customer menu
					CustomerDao.menu();
					break;
				case 3:
					//Exiting application
					exitApp();
					System.exit(0);
					break;
				default:
					System.out.println("Invalid entry.!!!");
					break;
				}
				input.close();
	}

	private static void exitApp() {
		System.out.println("Thank You for using CRM App. \n Exiting...");
	}
}
