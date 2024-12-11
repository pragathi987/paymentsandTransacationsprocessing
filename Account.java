package paymentsandTransacationsprocessing;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private int id;
	private List<Transaction> transactions;
	
	public Account(int id) {
		this.id = id;
		this.transactions = new ArrayList<>();
	}
	
	public static class Transaction{
	    Account accountFrom;
	    Account accountTo;
	    double moneyAmount;
	    StandardAccountOperations operation;
	    
	    public Transaction(Account accountFrom, Account accountTo,double moneyAmount, StandardAccountOperations operation) {
	    	this.accountFrom = accountFrom;
	    	this.accountTo = accountTo;
	    	this.moneyAmount = moneyAmount;
	    	this.operation = operation;
	    }
	    
	    @Override
	    public String toString() {
			return "transaction" + "accountFrom" + (accountFrom != null ? accountFrom.id : "null" ) 
			+ "accountTo" + (accountTo != null ? accountTo.id : "null") + "moneyAmount" +moneyAmount 
			+ "operation" + operation;
	    	
	    }
	    
	    }
	
	public void sendMoneyToAccount(Account accountTo, double moneyAmount) {
		if(moneyAmount <= 0) {
			System.out.println("Invalid money Amount");
		}
		
		Transaction sendTransaction = new Transaction(this, accountTo, moneyAmount, StandardAccountOperations.MONEY_TRANSFER_SEND);
	    this.transactions.add(sendTransaction);
	    
	    Transaction receiveTransaction = new Transaction(this, accountTo, moneyAmount, StandardAccountOperations.MONEY_TRANSFER_RECEIVE);
	    accountTo.transactions.add(receiveTransaction);
	    
	    System.out.println("Successfully transferred " + moneyAmount + " from Account " + this.id + " to Account " + accountTo.id);
	    
	}
	
	public void withdrawMoney(double moneyAmount)  {
		if(moneyAmount <= 0) {
			System.out.println("Invalid money Amount");
		}
		
		Transaction withDrawTransaction = new Transaction(this,null, moneyAmount, StandardAccountOperations.WITHDRAW);
		this.transactions.add(withDrawTransaction);
		
		System.out.println("Successfully withdrew " + moneyAmount + " from Account " + this.id);
	}
	
	public Transaction[] getTransactions() {
		return transactions.toArray(new Transaction[0]);
		
	}
	
	
	public static void main(String[] args) {
		Account account1 = new Account(1);
		Account account2 = new Account(2);
		
		account1.sendMoneyToAccount(account2, 1000.0);
		account2.withdrawMoney(60.0);
		
		for(Transaction t : account1.transactions) {
			System.out.println(t);
		}
		
		for(Transaction t : account2.transactions) {
			System.out.println(t);
		}
		
	}
	
	
	
	


}
