package com.codecool.finastra.models;

//This class created based on account history users table

public class AccountHistory {
	
	private int accountHistoryId;
	private String sourceTargetAccount;
	private String currency;
	private int amount;
	private String tranzactionType;
	private String accountNumber;
	
	public AccountHistory(int accountHistoryId, String sourceTargetAccount, String currency, int amount,
			String tranzactionType, String accountNumber) {
		super();
		this.accountHistoryId = accountHistoryId;
		this.sourceTargetAccount = sourceTargetAccount;
		this.currency = currency;
		this.amount = amount;
		this.tranzactionType = tranzactionType;
		this.accountNumber = accountNumber;
	}

	public int getAccountHistoryId() {
		return accountHistoryId;
	}

	public void setAccountHistoryId(int accountHistoryId) {
		this.accountHistoryId = accountHistoryId;
	}

	public String getSourceTargetAccount() {
		return sourceTargetAccount;
	}

	public void setSourceTargetAccount(String sourceTargetAccount) {
		this.sourceTargetAccount = sourceTargetAccount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTranzactionType() {
		return tranzactionType;
	}

	public void setTranzactionType(String tranzactionType) {
		this.tranzactionType = tranzactionType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
