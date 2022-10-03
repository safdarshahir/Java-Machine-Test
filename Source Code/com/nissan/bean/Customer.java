package com.nissan.bean;

import java.util.Random;

public class Customer {
	
	private int accountNo;
	private String customerName;
	private String accountType;
	private double accountBalance;
	private String mobileNumber;
	private String emailId;
	private String panNo;

	private int atmPin;
	
	private boolean isDisabled = false;
	private static final double MINIMUM_BALANCE =5000;

	// default constructor
	public Customer() {
		super();
	}
	
	//parameterized constructor
	public Customer(int accountNo, String customerName, String accountType, double accountBalance, String mobileNumber,
			String emailId, int atmPin) {
		super();
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.atmPin = atmPin;
	}

	//getters and setters
	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getAtmPin() {
		return atmPin;
	}

	public void setAtmPin(int atmPin) {
		this.atmPin = atmPin;
	}

	public double getMinimumBalance() {
		return MINIMUM_BALANCE;
	}

	public boolean isDisabled() {
		return isDisabled;
	}
	
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	@Override
	public String toString() {
		return "Customer [accountNo=" + accountNo + ", customerName=" + customerName + ", accountType=" + accountType
				+ ", accountBalance=" + accountBalance + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
				+ "]";
	}

	//Method to generate 9 digit pin with banks IFSC code as 147000000
	public int generateAccNo(){
		Random rndNo = new Random(System.currentTimeMillis());
		return 14700000+rndNo.nextInt(1000000);
	}
	
	//Method to generate 4 digit pin
	public int generatePin() {
		Random rndNo = new Random(System.currentTimeMillis());
		return rndNo.nextInt((9999-1000)+1)+1000;
	}
}
