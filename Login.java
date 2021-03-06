/*

Login creates a window with a place to enter account number and password.
Should check if account exists and if account number and password match.
If they do, creates a new BankOptions object and passes the account number to it.
Has main(), which creates instance of login.

*/
package BankAccount;

import java.awt.*; //Layouts
import java.awt.event.*; //event-handling
import javax.swing.*; //UIstuff with a J in front of it
import java.io.*; //for File()
import java.util.Scanner; //Scanner

public class Login extends JFrame implements ActionListener, WindowListener{
        //extends JFrame makes this a window, ActionListener for event-handling
        //window listener for window close on 'x'
	
    //new UI objects
	private JLabel acctNumLabel = new JLabel("Account Number: "); //label in front of acctNumField
	private JTextField acctNumField = new JTextField(20); //place to enter account number
	private JLabel passwdLabel = new JLabel("Password: "); //label designating password field
	private JPasswordField passwdField = new JPasswordField(20); //place to put password
	private JButton loginButton = new JButton("Login"); //login button
	private JPanel buttonPanel = new JPanel(new BorderLayout());
	private JLabel badPasswordLabel = new JLabel("<html><font color=red>The password is incorrect<br>or you don't have permission to access ATM</font></html>");
	
	
	public Login() //When login object is created, creates frame and places all necessary UI elements
	{
		
		super();
        
        //creates subcontainer, adds acctNum elements to it, adds action listener
        //adds all of that to JFrame
		JPanel acctNumPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
                                    //Creates subcontainer
		acctNumPanel.add(acctNumLabel); //add to JPanel
		acctNumPanel.add(acctNumField); //add to JPanel
		acctNumField.addActionListener(this);
		add(acctNumPanel, BorderLayout.NORTH); //Adds JPanel with elements to JFrame
		
        //Same for passwd Elements
		JPanel passwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		passwdPanel.add(passwdLabel);
		passwdPanel.add(passwdField);
		passwdField.addActionListener(this);
		add(passwdPanel, BorderLayout.CENTER);
		
        //Adds button to JFrame, adds action listener
		buttonPanel.add(loginButton, BorderLayout.CENTER);
		loginButton.addActionListener(this);
        buttonPanel.add(badPasswordLabel, BorderLayout.NORTH);
        badPasswordLabel.setVisible(false);
		add(buttonPanel,BorderLayout.SOUTH);

		
		setTitle("Login");
		setSize(400,160);
		
		setVisible(true);
		
		//setResizable(false); //user can't change window size
		
		addWindowListener(this); //for close on x
		
		
	} //end of login()
	
	public void actionPerformed(ActionEvent evt)
	{
		String userPath = System.getProperty("User.dir"); //string representing current project file path
		
		Scanner loginStream = null; //initializes loginStream, if not initialized, and error is
                //thrown because the try block might fail. In that case loginStream would never
                //initialized
		
		File credentialsFile = new File(userPath, "loginInfo.txt"); 
                //FIXED: Put loginInfo.txt into project file and this will work
                //Might use different file i/o object like FileInputStream, but this works
                //path to .txt file with accountNums and passwords
                //in format:
                //accountNum password
                //accountNum password

        //creates scanner for accountFile
		try{
			loginStream = new Scanner(credentialsFile);
		}
		catch(FileNotFoundException e){
			System.out.println("File doesn't exist.");
		}
		
        
        //iterates through accountFile to see if accountNum is in it, then compares password
        //creates new BankOptions and passes it the account number
        //deletes Login() object
		while(loginStream.hasNext()){
			int accountNumber = loginStream.nextInt();
			
			if (accountNumber == Integer.parseInt(acctNumField.getText()))
			{
				String password = loginStream.next();
                Account account = new Account(accountNumber);
				
				if (password.equals(passwdField.getText()) && account.getAtmAccess()) {
					
					loginStream.close();
			
					new BankOptions(accountNumber);

					this.dispose();
						
					break; //don't think this is necessary, dispose() probably ends the process
					
				} else {
                    if (!badPasswordLabel.isVisible()) {
                        badPasswordLabel.setVisible(true);
                        buttonPanel.revalidate();
                        buttonPanel.repaint();

                    }
				}
				
			} else {	
				loginStream.nextLine();
			} // end of while loop
		}
		
		loginStream.close();
	} //end of actionPerformed
    
    
	//main
	public static void main(String [] args)
	{
		new Login();
		System.out.println(System.getProperty("user.dir"));
	}
	
    //closes window on x
	@Override
	public void windowClosing(WindowEvent e){
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
	
	

} //end of Login
