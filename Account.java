/*this class will use the account number passed to it from login.java to open an account file
then, using that file, it will fill the variables
getVariable() functions will behave normally
setVariable() functions might have to overwrite the account file
This class has to make the account file associated with it store the correct information, not sure yet how this should be implemented
ex. when a customer deposits money, the file must reflect that there is more money in the account.
*/

package BankAccount;

import java.io.*;
import java.util.Scanner;

public class Account {
	private int acctNum;
	private String lastName, firstName;
	private double balance;
	private boolean atmAccess; 
	
	public Account(int anAccountNumber){ //argument passed by BankOptions
		
		super();
		
		String userPath = System.getProperty("User.dir"); //string representing path to project directory
		
		File accountFile = new File(userPath, anAccountNumber + ".txt"); //new file object for account file
		
		Scanner accountStream = null; // initializes accountStream
		
		//creates scanner for accountFile
		//IMPORTANT: an output stream will need to be created to write to accountFile
		try{
			accountStream = new Scanner(accountFile);	
		}
		catch(FileNotFoundException e){
			System.out.println("file not found");
		}
		
		//takes values from accountFile and assigns them to variables
		acctNum = accountStream.nextInt();
		lastName = accountStream.next();
		firstName = accountStream.next();
		balance = accountStream.nextDouble();
		atmAccess = accountStream.nextBoolean();
		
		System.out.println(acctNum + " " + lastName + " " + firstName + " " + balance + " " + atmAccess);
		
		
	} //end of Account()
	
	//get and set for all variables
	
} //end of Account
