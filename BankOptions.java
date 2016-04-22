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
import java.io.*;
import java.util.Scanner;


public class BankOptions extends JFrame implements WindowListener{ //Frame for UI, Window listener                                               for closing whendow. 
    
	private int accountNumber;
    
	//new button instances
    private JButton depositButton = new JButton("Deposit");
    private JButton withdrawButton = new JButton("Withdraw");
    private JButton checkBalanceButton = new JButton("<html><center>Check<br>Balance</center></html>");
    private JButton transferButton = new JButton("Transfers");
    private JButton changePasswordButton = new JButton("<html><center>Change<br>Password</center></html>");
    private JButton logoutButton = new JButton("Log Out");
    private JPanel buttonPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JLabel welcomeLabel;
    private final static Color BACKGROUND_COLOR = new Color(40,120,80);


	
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

        buttonPanel.setBackground(BACKGROUND_COLOR);

        String welcomeMessage = "<html><font size=+2 color=white><center>Welcome " + account.getFirstName() + ",<br> what would you like to do?</center></font></html>";
        welcomeLabel = new JLabel(welcomeMessage);
        infoPanel.add(welcomeLabel, BorderLayout.CENTER);
        infoPanel.setBackground(BACKGROUND_COLOR);

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
			new DepositPage();
		}
		
		private class DepositPage implements ActionListener{
			
			Account account = new Account(accountNumber);
			
			JTextField depositField = new JTextField(20);
			JButton depositButton = new JButton("submit");
			JLabel depositLabel = new JLabel("<html><font color=white size=+1>How much do you want to deposit?</font></html>");
			
			private DepositPage() {
				
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
				
				infoPanel.add(new JLabel("<html><font color=white size=+1>You deposited $" + amountDeposited + ". Your Balance is now " + newBalance +"</font></html>"), BorderLayout.CENTER);
				infoPanel.add(okButton, BorderLayout.SOUTH);
				
				infoPanel.revalidate();
				infoPanel.repaint();
				
			}
		}
	}
	
	private class WithdrawButtonListener implements ActionListener{

		@Override
        public void actionPerformed(ActionEvent evt){ new WithdrawPage(); }

        private class WithdrawPage implements ActionListener{

            Account account = new Account(accountNumber);

            JTextField withdrawField = new JTextField(20);
            JButton withdrawButton = new JButton("submit");
            JLabel withdrawLabel = new JLabel("<html><font color=white size=+1>How much do you want to withdraw?</font></html>");

            private WithdrawPage(){
                withdrawField.addActionListener(this);
                withdrawButton.addActionListener(this);
                infoPanel.removeAll();
                infoPanel.add(withdrawLabel, BorderLayout.CENTER);
                infoPanel.add(withdrawField, BorderLayout.CENTER);
                infoPanel.add(withdrawButton, BorderLayout.SOUTH);

                infoPanel.revalidate();
                infoPanel.repaint();

            }

            public void actionPerformed(ActionEvent evt) {

                Double amountWithdrawn = Double.parseDouble(withdrawField.getText());

                JButton okButton = new JButton("Okay");
                okButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        infoPanel.removeAll();
                        infoPanel.add(welcomeLabel, BorderLayout.CENTER);
                        infoPanel.revalidate();
                        infoPanel.repaint();
                    }

                });
                double newBalance = account.getBalance()-amountWithdrawn;
                account.setBalance(newBalance);

                infoPanel.removeAll();

                infoPanel.add(new JLabel("<html><font color=white size=+1>You withdrew $"+amountWithdrawn+".<br> Your Balance is now "+newBalance + "</font></html>"), BorderLayout.CENTER);
                infoPanel.add(okButton, BorderLayout.SOUTH);

                infoPanel.revalidate();
                infoPanel.repaint();
            }
        }
    }
	
	private class CheckBalanceButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){ new BalancePage(); }

        private class BalancePage implements ActionListener{

            Account account = new Account(accountNumber);

            JButton balanceButton = new JButton ("okay");
            JLabel balanceLabel = new JLabel("<html><font color=white size=+2>Your balance is "+account.getBalance() + "</font></html>");

            private BalancePage() {

                balanceButton.addActionListener(this);
                infoPanel.removeAll();
                infoPanel.add(balanceLabel, BorderLayout.CENTER);
                infoPanel.add(balanceButton, BorderLayout.SOUTH);

                infoPanel.revalidate();
                infoPanel.repaint();
            }

            public void actionPerformed(ActionEvent evt){

                infoPanel.removeAll();
                infoPanel.add(welcomeLabel, BorderLayout.CENTER);
                infoPanel.revalidate();
                infoPanel.repaint();
            }

        }
    }
	
	private class TransferButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){ new TransferPage(); }

        private class TransferPage implements ActionListener{

            Account account = new Account(accountNumber);

            JTextField transtoField = new JTextField(15);
            JLabel transtoLabel = new JLabel("<html><font color=white size=+1>What is the recipient's account number?</font></html>");
            JTextField transferField = new JTextField(15);
            JLabel transferLabel = new JLabel("<html><font color=white size=+1>How much do you want to transfer?</font></html>");
            JButton transferButton = new JButton("Transfer");
            JPanel transtoPanel = new JPanel();
            JPanel transferPanel = new JPanel();


            private TransferPage() {

                transferPanel.setLayout(new BoxLayout(transferPanel, BoxLayout.Y_AXIS));
                transferPanel.setBackground(BACKGROUND_COLOR);
                transtoPanel.setLayout(new BoxLayout(transtoPanel, BoxLayout.Y_AXIS));
                transtoPanel.setBackground(BACKGROUND_COLOR);

                transferButton.addActionListener(this);
                transferField.addActionListener(this);
                transtoField.addActionListener(this);
                transtoPanel.add(transtoLabel);
                transtoPanel.add(transtoField);

                transferPanel.add(transferLabel);
                transferPanel.add(transferField);

                infoPanel.removeAll();
                infoPanel.add(transtoPanel, BorderLayout.CENTER);
                infoPanel.add(transferPanel, BorderLayout.CENTER);
                infoPanel.add(transferButton, BorderLayout.SOUTH);

                infoPanel.revalidate();
                infoPanel.repaint();



            }

            public void actionPerformed (ActionEvent evt){

                Double amountTrans = Double.parseDouble(transferField.getText());
                int transNum = Integer.parseInt(transtoField.getText());

                JButton okButton = new JButton("Okay");
                okButton.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        infoPanel.removeAll();
                        infoPanel.add(welcomeLabel, BorderLayout.CENTER);
                        infoPanel.revalidate();
                        infoPanel.repaint();

                    }});

                double newBalance = account.getBalance()-amountTrans;
                account.setBalance(newBalance);
                Account accountTrans = new Account(transNum);
                double transAccountNewBalance = accountTrans.getBalance()+amountTrans;
                accountTrans.setBalance(transAccountNewBalance);

                infoPanel.removeAll();

                infoPanel.add(new JLabel("<html><font size=+1 color=white>You transfered $" +amountTrans+". Your Balance is now "+ newBalance + "</font></html>"), BorderLayout.CENTER);
                infoPanel.add(okButton, BorderLayout.SOUTH);

                infoPanel.revalidate();
                infoPanel.repaint();
            }
        }
    }

	private class ChangePasswordButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) { new changePasswordPage(); }

		private class changePasswordPage implements ActionListener {

			JPasswordField oldPasswordField = new JPasswordField(18);
			JPasswordField newPasswordField = new JPasswordField(18);
			JPasswordField confirmNewPasswordField = new JPasswordField(18);
			JButton submitButton = new JButton("Change Password");
			JLabel oldPasswordLabel = new JLabel("<html><font color=white>Current Password:</font></html>");
			JLabel newPasswordLabel = new JLabel("<html><font color=white>New Password:</font></html>");
			JLabel confirmNewPasswordLabel = new JLabel("<html><font color=white>Confirm New Password:</font></html>");
            JPanel changePasswordPanel = new JPanel();

			private changePasswordPage () {
                changePasswordPanel.setLayout(new BoxLayout(changePasswordPanel, BoxLayout.Y_AXIS));
				oldPasswordField.addActionListener(this);
				newPasswordField.addActionListener(this);
				confirmNewPasswordField.addActionListener(this);
				changePasswordButton.addActionListener(this);
				submitButton.addActionListener(this);
                changePasswordPanel.setBackground(BACKGROUND_COLOR);


                changePasswordPanel.add(oldPasswordLabel);
				changePasswordPanel.add(oldPasswordField);

                changePasswordPanel.add(newPasswordLabel);
				changePasswordPanel.add(newPasswordField);

                changePasswordPanel.add(confirmNewPasswordLabel);
                changePasswordPanel.add(confirmNewPasswordField);

				changePasswordPanel.add(submitButton);

                infoPanel.removeAll();
                infoPanel.add(changePasswordPanel, BorderLayout.CENTER);

				infoPanel.revalidate();
				infoPanel.repaint();

			}

			public void actionPerformed(ActionEvent evt) {

                String userPath = System.getProperty("user.dir");
                Scanner loginInfoStream = null;
                File loginInfo = new File(userPath, "loginInfo.txt");

				try{
					loginInfoStream = new Scanner(loginInfo);
				}
				catch(FileNotFoundException e){
					System.out.println("File does not exist");
				}

                boolean flag = false;
				while(loginInfoStream.hasNext()){

                    int acctNumToTest = loginInfoStream.nextInt();
                    String passwordToTest = loginInfoStream.next();

					if ((accountNumber == acctNumToTest) && (oldPasswordField.getText().equals(passwordToTest)) &&
                            (newPasswordField.getText().equals(confirmNewPasswordField.getText()))) {
                        String line = null;
                        BufferedWriter passwordChanger = null;
                        BufferedReader loginFileReader = null;
                        File tempFile = new File(userPath, "temp.txt");

                        try {
                            passwordChanger = new BufferedWriter(new FileWriter(tempFile));
                            loginFileReader = new BufferedReader(new FileReader(loginInfo));

                            while ((line = loginFileReader.readLine()) != null) {
                                line  = line.replaceAll(accountNumber + " " + oldPasswordField.getText(),
                                        accountNumber + " " + newPasswordField.getText());

                                passwordChanger.write(line);
                                passwordChanger.newLine();

                            }

                            loginInfo.delete();
                            tempFile.renameTo(loginInfo);

                            passwordChanger.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
					}

			    }

                loginInfoStream.close();


                infoPanel.removeAll();
                infoPanel.add(welcomeLabel, BorderLayout.CENTER);
                infoPanel.revalidate();
                infoPanel.repaint();

            }//and of actionPerformed
        }//end of changePasswordPanel
    }//end of changePasswordButtonListener

	
	
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
