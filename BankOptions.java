/*

An object of this class is created when login credentials are authenticated.
It is how the user interacts with the account.
Creates a new object of Account.
Will use Account's get and set functions for interacting with account file.

*/
package BankAccount;


import java.awt.*; //Layouts
import java.awt.event.*; //event-handling stuff
import java.math.BigDecimal;

import javax.swing.*; //UI stuff


public class BankOptions extends JFrame implements WindowListener{ //Frame for UI, Window listener                                               for closing whendow. 
    
	int accountNumber;
    
	//new button instances
	JButton depositButton = new JButton("Deposit");
	JButton withdrawButton = new JButton("Withdraw");
	JButton checkBalanceButton = new JButton("Check Balance");
	JButton transferButton = new JButton("Transfer Money");
	JButton changePasswordButton = new JButton("Change Password");
	JButton logoutButton = new JButton("Log Out");
	
	public BankOptions(int anAccountNumber){ //constructor takes account number from login
		
		super();
		
		accountNumber = anAccountNumber; 
		
		Account account = new Account(accountNumber); //passes account number to Account. This may not be needed.. create Account objects in buttons
		
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
		public void actionPerformed(ActionEvent evt){//IMPLEMENTING
			BankOptions.this.setVisible(false);
			new DepositWindow();
		}
		
		private class DepositWindow extends JFrame implements ActionListener{
			
			Account account = new Account(accountNumber);
			
			JTextField depositField = new JTextField(20);
			JButton depositButton = new JButton("submit");
			JLabel depositLabel = new JLabel("How much do you want to deposit?");
			
			private DepositWindow() {
				
				setLayout(new FlowLayout());
				
				depositField.addActionListener(this);
				depositButton.addActionListener(this);
				
				add(depositLabel);
				add(depositField);
				add(depositButton);
				
				setVisible(true);
				
				setSize(350, 120);
			}
			
			public void actionPerformed(ActionEvent evt) {
				
				Double amountDeposited = Double.parseDouble(depositField.getText());
				
				JButton okButton = new JButton("Okay");
				okButton.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt){
						BankOptions.this.setVisible(true);
						dispose();
					}});
				
				double newBalance = account.getBalance() + amountDeposited;
				account.setBalance(newBalance);
				
				remove(depositField);
				remove(depositButton);
				remove(depositLabel);
				
				add(new JLabel("You deposited $" + amountDeposited + ". Your Balance is now " + newBalance));
				add(okButton);
				
				revalidate();
				repaint();
				
			}
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

//TEST
