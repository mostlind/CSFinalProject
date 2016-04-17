/*

An object of this class is created when login credentials are authenticated.
It is how the user interacts with the account.
Creates a new object of Account.
Will use Account's get and set functions for interacting with account file.

*/
package BankAccount;


import java.awt.*; //Layouts
import java.awt.event.*; //event-handling stuff

import javax.swing.*; //UI stuff


public class BankOptions extends JFrame implements WindowListener{ //Frame for UI, Window listener                                               for closing whendow. 
    
	private int accountNumber;
    
	//new button instances
    private JButton depositButton = new JButton("Deposit");
    private JButton withdrawButton = new JButton("Withdraw");
    private JButton checkBalanceButton = new JButton("Check Balance");
    private JButton transferButton = new JButton("Transfer Money");
    private JButton changePasswordButton = new JButton("Change Password");
    private JButton logoutButton = new JButton("Log Out");
    private JPanel buttonPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JLabel welcomeLabel;
	
	public BankOptions(int anAccountNumber){ //constructor takes account number from login
		
		super("MCB Financial Services, LLC");

		accountNumber = anAccountNumber;
		
		Account account = new Account(accountNumber); //passes account number to Account. This may not be needed.. create Account objects in buttons

        //Sets UI stuff, six buttons in a 3x2 grid, attaches Action listeners
		buttonPanel.setLayout(new GridLayout(6,1));
		
		buttonPanel.add(depositButton);
		depositButton.addActionListener(new DepositButtonListener());
		
		buttonPanel.add(withdrawButton);
		withdrawButton.addActionListener(new WithdrawButtonListener());
		
		buttonPanel.add(checkBalanceButton);
		checkBalanceButton.addActionListener(new CheckBalanceButtonListener());
		
		buttonPanel.add(transferButton);
		transferButton.addActionListener(new TransferButtonListener());
		
		buttonPanel.add(changePasswordButton);
		changePasswordButton.addActionListener(new ChangePasswordButtonListener());
		
		buttonPanel.add(logoutButton); //logout creates new Login object,
		logoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
				new Login();
				dispose();
			}
		});


        buttonPanel.setBackground(new Color(130,200,160));

        String welcomeMessage = "<html><font size=+2>Welcome " + account.getFirstName() + ",<br> what would you like to do?</font>";
        welcomeLabel = new JLabel(welcomeMessage);
        infoPanel.add(welcomeLabel, BorderLayout.CENTER);
        infoPanel.setBackground(new Color(130, 200, 160));

        add(buttonPanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);

		
		setSize(600,400);
		
		setVisible(true);
		
		addWindowListener(this); //for closing window on pressing x
		
	}
    
    
	//Button listeners, code will run when corresponding button is pressed
	private class DepositButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent evt){
			new DepositPanel();
		}
		
		private class DepositPanel implements ActionListener{
			
			Account account = new Account(accountNumber);
			
			JTextField depositField = new JTextField(20);
			JButton depositButton = new JButton("submit");
			JLabel depositLabel = new JLabel("How much do you want to deposit?");
			
			private DepositPanel() {
				
				depositField.addActionListener(this);
				depositButton.addActionListener(this);
                infoPanel.removeAll();
                infoPanel.add(depositLabel, BorderLayout.CENTER);
                infoPanel.add(depositField, BorderLayout.CENTER);
                infoPanel.add(depositButton, BorderLayout.SOUTH);

                infoPanel.revalidate();
                infoPanel.repaint();


			}
			
			public void actionPerformed(ActionEvent evt) {
				
				Double amountDeposited = Double.parseDouble(depositField.getText());
				
				JButton okButton = new JButton("Okay");
				okButton.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt){

                        infoPanel.removeAll();
                        infoPanel.add(welcomeLabel, BorderLayout.CENTER);
                        infoPanel.revalidate();
                        infoPanel.repaint();

					}});
				
				double newBalance = account.getBalance() + amountDeposited;
				account.setBalance(newBalance);
				
				infoPanel.removeAll();
				
				infoPanel.add(new JLabel("You deposited $" + amountDeposited + ". Your Balance is now " + newBalance), BorderLayout.CENTER);
				infoPanel.add(okButton, BorderLayout.SOUTH);
				
				infoPanel.revalidate();
				infoPanel.repaint();
				
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
