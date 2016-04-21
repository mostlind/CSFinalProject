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
        public void actionPerformed(ActionEvent evt){ new WithdrawPanel(); }

        private class WithdrawPanel implements ActionListener{

            Account account = new Account(accountNumber);

            JTextField withdrawField = new JTextField(20);
            JButton withdrawButton = new JButton("submit");
            JLabel withdrawLabel = new JLabel("How much do you want to withdraw?");

            private WithdrawPanel(){
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

                infoPanel.add(new JLabel("You withdrew $"+amountWithdrawn+". Your Balance is now "+newBalance), BorderLayout.CENTER);
                infoPanel.add(okButton, BorderLayout.SOUTH);

                infoPanel.revalidate();
                infoPanel.repaint();
            }
        }
    }
	
	private class CheckBalanceButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            new BalancePanel();
        }

        private class BalancePanel implements ActionListener{

            Account account = new Account(accountNumber);

            JButton balanceButton = new JButton ("okay");
            JLabel balanceLabel = new JLabel("Your balance is"+account.getBalance());

            private BalancePanel() {

                balanceButton.addActionListener(this);
                infoPanel.removeAll();
                infoPanel.add(balanceButton, BorderLayout.SOUTH);
                infoPanel.add(balanceLabel, BorderLayout.CENTER);

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
        public void actionPerformed(ActionEvent evt){
            new TransferPanel();
        }

        private class TransferPanel implements ActionListener{

            Account account = new Account(accountNumber);

            JTextField transtoField = new JTextField(20);
            JLabel transtoLabel = new JLabel("Enter the account number of the person you'd like to transfer to.");
            JTextField transferField = new JTextField(20);
            JLabel transferLabel = new JLabel("How much do you want to transfer?");
            JButton transferButton = new JButton("Transfer");


            private TransferPanel(){
                transferButton.addActionListener(this);
                transferField.addActionListener(this);
                transtoField.addActionListener(this);
                infoPanel.removeAll();
                infoPanel.add(transtoLabel, BorderLayout.CENTER);
                infoPanel.add(transtoField, BorderLayout.CENTER);
                infoPanel.add(transferLabel, BorderLayout.CENTER);
                infoPanel.add(transferField, BorderLayout.CENTER);
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

                infoPanel.add(new JLabel("You transfered $" +amountTrans+". Your Balance is now"+ newBalance), BorderLayout.CENTER);
                infoPanel.add(okButton, BorderLayout.SOUTH);

                infoPanel.revalidate();
                infoPanel.repaint();
            }
        }
    }

	private class ChangePasswordButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) { new changePasswordPanel(); }

		private class changePasswordPanel implements ActionListener {

			JPasswordField oldPassword = new JPasswordField(20);
			JPasswordField newPassword = new JPasswordField(20);
			JPasswordField confirmNewPassword = new JPasswordField(20);
			JButton submitButton = new JButton("Change Password");
			JLabel oldPasswordLabel = new JLabel("Current Password: ");
			JLabel newPasswordLabel = new JLabel("New Password: ");
			JLabel confirmNewPasswordLabel = new JLabel("Confirm New Password: ");


			private changePasswordPanel () {

				oldPassword.addActionListener(this);
				newPassword.addActionListener(this);
				confirmNewPassword.addActionListener(this);
				changePasswordButton.addActionListener(this);
				submitButton.addActionListener(this);

				infoPanel.removeAll();
				infoPanel.add(oldPassword, BorderLayout.CENTER);
				infoPanel.add(newPassword, BorderLayout.CENTER);
				infoPanel.add(confirmNewPassword, BorderLayout.CENTER);
				infoPanel.add(oldPasswordLabel, BorderLayout.WEST);
				infoPanel.add(newPasswordLabel, BorderLayout.WEST);
				infoPanel.add(confirmNewPasswordLabel, BorderLayout.WEST);
				infoPanel.add(submitButton, BorderLayout.SOUTH);

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

					if ((accountNumber == acctNumToTest) && (oldPassword.getText().equals(passwordToTest)) &&
                            (newPassword.getText().equals(confirmNewPassword.getText()))) {
                        String line = null;
                        BufferedWriter passwordChanger = null;
                        BufferedReader loginFileReader = null;
                        File tempFile = new File(userPath, "temp.txt");

                        try {
                            passwordChanger = new BufferedWriter(new FileWriter(tempFile));
                            loginFileReader = new BufferedReader(new FileReader(loginInfo));

                            while ((line = loginFileReader.readLine()) != null) {
                                line  = line.replaceAll(accountNumber + " " + oldPassword.getText(),
                                        accountNumber + " " + newPassword.getText());

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
