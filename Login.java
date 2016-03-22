/*

Login creates a window with a place to enter account number and password.
Should check if account exists and if account number and password match.
If they do, creates a new BankOptions object and passes the account number to it.
Has main(), which creates instance of login.

*/
package BankAccount;


import java.awt.*; //UIstuff
import java.awt.event.*; //event-handling
import javax.swing.*; //UIstuff with a J in front of it
import java.io.*; //for File()
import java.util.Scanner; //Scanner

public class Login extends JFrame implements ActionListener, WindowListener{
        //extends JFrame makes this a window, ActionListener for event-handling
        //window listener for window close on 'x'
	
    //new UI objects
	Label acctNumLabel = new Label("Account Number: "); //label in front of acctNumField
	TextField acctNumField = new TextField(30); //place to enter account number
	Label passwdLabel = new Label("Password: "); //label designating password field
	TextField passwdField = new TextField(30); //place to put password
	Button loginButton = new Button("Login"); //login button
	
	
	public Login() //constructor
	{
		
		setLayout( new FlowLayout(FlowLayout.RIGHT)); //uses flow layout 
		
        
        //creates subcontainer, adds acctNum elements to it, adds action listener
        //adds all of that to JFrame
		JPanel acctNumPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
                                    //Creates subcontainer
		acctNumPanel.add(acctNumLabel); //add to JPanel
		acctNumPanel.add(acctNumField); //add to JPanel
		acctNumField.addActionListener(this);
		add(acctNumPanel); //Adds JPanel with elements to JFrame
		
        //Same for passwd Elements
		JPanel passwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		passwdPanel.add(passwdLabel);
		passwdPanel.add(passwdField);
		passwdField.addActionListener(this);
		add(passwdPanel);
		
        //Adds button to JFrame, adds action listener
		add(loginButton);
		loginButton.addActionListener(this);
		
		setTitle("Login");
		setSize(400,150);
		
		setVisible(true);
		
		setResizable(false); //user can't change window size
		
		addWindowListener(this); //for close on x
		
		
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		Scanner loginStream = null; //initializes loginStream, if not initialized, and error is
                //thrown because the try block might fail. In that case loginStream would never
                //initialized
		
		File accountFile = new File("/Users/mostlind/Documents/loginInfo.txt"); 
                //Change this for your filesystem
                //Might use different file i/o object like FileInputStream, but this works
                //path to .txt file with accountNums and passwords
                //in format:
                //accountNum password
                //accountNum password

        //creates scanner for accountFile
		try{
			loginStream = new Scanner(accountFile);
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
				
				if (password.equals(passwdField.getText())) {
					
					loginStream.close();
			
					new BankOptions(accountNumber);

					this.dispose();
						
					break;
					
				}
				
			} else {	
				loginStream.nextLine();
			}
		}
		
		loginStream.close();
	} //end of actionPerformed
    
    
	//main
	public static void main(String [] args)
	{
		new Login();
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
