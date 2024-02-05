package Main;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import Logic.*;
import Command.BaseCommand;
import Command.ByeCommand;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mario {
    public static final String mario_filename = "MARIO.ser";
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private LogicController mario;
    private JScrollPane scrollPane;
    private String fullCommand = "";
    private String response = "";
    private BaseCommand command;

    public Mario(String filename) {

        this.mario = new LogicController(filename);
        // Create the frame
        frame = new JFrame("Mario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        // Create the chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);

        // Create a scroll pane that contains the chat area
        scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(20, 20, 450, 350);
        frame.add(scrollPane);

        // Create the input field
        inputField = new JTextField();
        inputField.setBounds(20, 380, 450, 30);
        frame.add(inputField);

        // Create the send button
        sendButton = new JButton("Talk to Mario");
        sendButton.setBounds(20, 420, 450, 30);
        frame.add(sendButton);

        // Customisations
        frame.getContentPane().setBackground(new Color(224,16,47)); // Example color
        chatArea.setBackground(Color.white); // Light Lavender Background
        chatArea.setForeground(new Color(23,65,154)); // Black text
        inputField.setBackground(Color.WHITE);
        inputField.setForeground(Color.BLACK);
        chatArea.setFont(new Font("Apple LiGothic", Font.BOLD, 18));
        inputField.setFont(new Font("Apple LiGothic", Font.BOLD, 16));

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick();
            }
        });

        // Add action listener to the button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullCommand = inputField.getText();
                process_command();
                chatArea.append("\nYou: " + fullCommand + "\n\n");
                new Thread(new ResponseStreamer(response, chatArea)).start();
                inputField.setText("");
            }
        });

        DefaultCaret caret = (DefaultCaret) chatArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Display the frame
        frame.setVisible(true);
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Mario! What can I do for you?";
        chatArea.append(welcomeMessage + "\n");
    }

    private String inGuards(String input) {
        String len = "_____________________________________________________________";
        System.out.println(len.length());
        return input + "\n_____________________________________________________________\n";

    }

    public String marioResponse(String marioinput, String userinput) {
        return inGuards("\nYou: " + userinput + "\n\n" + "Mario: " + marioinput);
    }

    private void process_command() {
        try {
            command = Parser.parse(fullCommand);
            assert command != null : "Command is null!";
            response = command.execute(mario.geTaskList());
        } catch (Exception error) {
            response = "OOPS!! " + error.getMessage();
        } finally {
            mario.saveMario();
        }

        if(command instanceof ByeCommand){
            new Timer(5000, e -> frame.dispose()).start(); // Wait for 5 seconds then close the app
        }
    }

    public static void main(String[] args) {
        new Mario(mario_filename);
    }
}
