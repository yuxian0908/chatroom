package com.yuxian.socket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginFrame {
	static MySocketClient client;
	static JFrame frame;
	
	private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        //setBounds(x, y, width, height)
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        // input field for type in message
        JTextField userName = new JTextField(20);
        userName.setBounds(100,20,165,25);
        panel.add(userName);
        

        // submit button
        JButton submitButton = new JButton("submit");
        submitButton.setBounds(10, 80, 80, 25);
        panel.add(submitButton);
        
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	String name = userName.getText();
            	client.setUserName(name);
            	client.send.send(name);
            	frame.dispose();
            	new ChatRoom(client);
            }
        });
    }
	
	public static void main(String[] args) {
		
		client = new MySocketClient("127.0.0.1",8080);
		client.start();
		
        frame = new JFrame("Login Example");
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add panel
        JPanel panel = new JPanel();    
        frame.add(panel);
        placeComponents(panel);

        // set the frame to be visible
        frame.setVisible(true);
	}
}
