/*this class will use the account number passed to it from login.java to open an account file
then, using that file, it will fill the variables
getVariable() functions will behave normally
setVariable() functions might have to overwrite the account file
This class has to make the account file associated with it store the correct information, not sure yet how this should be implemented
ex. when a customer deposits money, the file must reflect that there is more money in the account.

*/



package BankAccount;

public class Account {
	
	private int acctNum;
	private String lastName, firstName;
	private double balance;
	private boolean atmAccess; 
	
	
	public Account(int anAccountNumber){ //arugument passed by login.java
		
	}
	
	//get and set for all variables
	
}
