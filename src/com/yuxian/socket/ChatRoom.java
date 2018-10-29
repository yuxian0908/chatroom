package com.yuxian.socket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatRoom {
	
	private static MySocketClient client;
	private static JTextArea textArea;
	
	public static void getText(String msg) {
        textArea.append(msg);
	}
	
	private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        //setBounds(x, y, width, height)
        JLabel userLabel = new JLabel("Message:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        // input field for type in message
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);
        
        // area all message
        textArea = new JTextArea(30, 50);
        textArea.setEditable(false);
        textArea.setBounds(100,100,200,50);
        panel.add(textArea);

        // submit button
        JButton submitButton = new JButton("submit");
        submitButton.setBounds(10, 80, 80, 25);
        panel.add(submitButton);
        
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	String newline = "\n";
            	String text = userText.getText();
                textArea.append(text + newline);
                userText.selectAll();
            	client.send.send(text);
         
                //Make sure the new text is visible, even if there
                //was a selection in the text area.
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
    }
	
	public ChatRoom(MySocketClient cli) {
		client = cli;
        JFrame frame = new JFrame("Login Frame");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add panel
        JPanel panel = new JPanel();    
        frame.add(panel);
        placeComponents(panel);

        // set the frame to be visible
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
	}
}
