
package BankAccount;


import java.awt.*;
import java.awt.event.*;



public class BankOptions extends Frame implements WindowListener{
	
	Button depositButton = new Button("Deposit");
	Button withdrawButton = new Button("Withdraw");
	Button checkBalanceButton = new Button("Check Balance");
	Button transferButton = new Button("Transfer Money");
	Button changePasswordButton = new Button("Change Password");
	Button logoutButton = new Button("Log Out");
	
	public BankOptions(int accountNumber){
		
		Account account = new Account(accountNumber);
		

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
		
		add(logoutButton);
		logoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
				new Login();
				dispose();
			}
		});
		
		setSize(400,300);
		
		setVisible(true);
		
		addWindowListener(this);
		
	}
	
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
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
	
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

}
