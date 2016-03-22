/*

An object of this class is created when login credentials are created.
It is how the user interacts with the account.
Creates a new object of Account.
Will use Account's get and set functions for interacting with account file.

*/
package BankAccount;


import java.awt.*; //UI stuff
import java.awt.event.*; //event-handling stuff



public class BankOptions extends Frame implements WindowListener{ //Frame for UI, Window listener                                               for closing whendow. 
    
    
	//new button instances
	Button depositButton = new Button("Deposit");
	Button withdrawButton = new Button("Withdraw");
	Button checkBalanceButton = new Button("Check Balance");
	Button transferButton = new Button("Transfer Money");
	Button changePasswordButton = new Button("Change Password");
	Button logoutButton = new Button("Log Out");
	
	public BankOptions(int accountNumber){ //constructor takes account number from login
		
		Account account = new Account(accountNumber); //passes account number to Account
		
        //Sets UI stuff, six buttons in a 3x2 grid, attaches Action listeners
		setLayout(new GridLayout(3,2)); 
		
		add(depositButton);
		depositButton.addActionListener(new DepositButtonListener());
		
		add(withdrawButton);
		withdrawButton.addActionListener(new WithdrawButtonListener());
		
		add(checkBalanceButton);
		checkBalanceButton.addActionListener(new CheckBalanceButtonListener());
		
		add(transferButton);
		transferButton.addActionListener(new TransferButtonListener());
		
		add(changePasswordButton);
		changePasswordButton.addActionListener(new ChangePasswordButtonListener());
		
		add(logoutButton); //logout creates new Login object, 
		logoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
				new Login();
				dispose();
			}
		});
		
		setSize(400,300);
		
		setVisible(true);
		
		addWindowListener(this); //for closing window on pressing x
		
	}
    
    
	//Button listeners, code will run when corresponding button is pressed
	private class DepositButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent evt){
			//deposit money... IMPLEMENT LATER
		}
	}
	
	private class WithdrawButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt){
			//withdraw money... IMPLEMENT LATER
		}
	}
	
	private class CheckBalanceButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt){
			//show balance ... IMPLEMENT LATER
		}
	}
	
	private class TransferButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt){
			//make transfer ... IMPLEMENT LATER
		}
	}
	private class ChangePasswordButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt){
			//change password ... IMPLEMENT LATER
		}
	}
	
	
	@Override
	public void windowClosing(WindowEvent e){ //closes window when x in top left is clicked
		System.exit(0);
	}
	
    //not used
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override 
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override 
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override 
	public void windowDeactivated(WindowEvent e) {}

} //end of BankOptions
