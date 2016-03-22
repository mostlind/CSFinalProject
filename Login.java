
package BankAccount;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Login extends JFrame implements ActionListener, WindowListener{
	
	Label acctNumLabel = new Label("Account Number: ");
	TextField acctNumField = new TextField(30);
	Label passwdLabel = new Label("Password: ");
	TextField passwdField = new TextField(30);
	Button loginButton = new Button("Login");
	
	
	public Login()
	{
		
		setLayout( new FlowLayout(FlowLayout.RIGHT));
		
		JPanel acctNumPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		acctNumPanel.add(acctNumLabel);
		acctNumPanel.add(acctNumField);
		acctNumField.addActionListener(this);
		add(acctNumPanel);
		
		JPanel passwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		passwdPanel.add(passwdLabel);
		passwdPanel.add(passwdField);
		passwdField.addActionListener(this);
		add(passwdPanel);
		
		add(loginButton);
		loginButton.addActionListener(this);
		
		setTitle("Login");
		setSize(400,150);
		
		setVisible(true);
		
		setResizable(false);
		
		addWindowListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		Scanner loginStream = null;
		//boolean accountFound = false;
		boolean authenticated = false;
		

		File accountFile = new File("/Users/mostlind/Documents/loginInfo.txt");

		try{
			loginStream = new Scanner(accountFile);
		}
		
		catch(FileNotFoundException e){
			System.out.println("Account doesn't exist.");
		}
		
		while(loginStream.hasNext()){
			int accountNumber = loginStream.nextInt();
			
			if (accountNumber == Integer.parseInt(acctNumField.getText()))
			{
				String password = loginStream.next();
				System.out.println(password + " " + passwdField.getText());
				
				
				if (password.equals(passwdField.getText())) {
				
					authenticated = true;
					
					loginStream.close();
			
					new BankOptions(accountNumber);

			
					this.dispose();
						
					break;
					
				}
				
			} else {	
				loginStream.nextLine();
			}
		}
		
		
		/*if ((acctNumField.getText() == "12345") && (passwdField.getText() == "hello"))
		{
			new BankOptions();
			
			this.dispose();
		}*/
		
		loginStream.close();
	} //end of actionPerformed
	
	public static void main(String [] args)
	{
		new Login();
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
