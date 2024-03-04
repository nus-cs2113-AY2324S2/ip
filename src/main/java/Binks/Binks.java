package Binks;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Binks {

    /**
     * Prints out the greeting message from the chatbot "Binks".
     */
    public static void greetUser() {
        createLineSpacing();
        System.out.println("Hello! I'm Binks.");
        System.out.println("Let me check if you have any saved tasks!");
        createLineSpacing();
    }

    /**
     * Prints out the line spacing between inputs and outputs in the console.
     */
    public static void createLineSpacing() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the exit message from the chatbot "Binks" when user inputs "Bye".mark
     */
    public static void exitChatbot() {
        createLineSpacing();
        System.out.println("Bye. Hope to see you again soon!");
        createLineSpacing();
    }

    public static void main(String[] arg) {
        File f = new File ("binkslist.txt");
        greetUser();
        List list = new List();
        Files.readFile(list);
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] command = line.split(" ");

            switch (command[0].toLowerCase()) {
            case "list":
                list.getList();
                break;

            //when the mark command is given
            case "mark":
                try {
                    Commands.markCommand(command, list);
                }
                catch (BinksException exception){
                    createLineSpacing();
                    System.out.println("WARNING! " + exception.getMessage());
                    createLineSpacing();
                }
                break;

            //when the unmark command is given
            case "unmark":
                try {
                    Commands.unmarkCommand(command, list);
                }
                catch (BinksException exception){
                    createLineSpacing();
                    System.out.println("WARNING! " + exception.getMessage());
                    createLineSpacing();
                }
                break;

            //when the bye command is given
            case "bye":
                exitChatbot();
                return;

            //when there is a new todo to add
            case "todo":
                try {
                    Commands.todoCommand(line, command, list);
                }
                catch (BinksException exception){
                    createLineSpacing();
                    System.out.println("WARNING! " + exception.getMessage());
                    createLineSpacing();
                }
                break;

            //when there is a new deadline to add
            case "deadline":
                try {
                    Commands.deadlineCommand(line, command, list);
                }
                catch (BinksException exception){
                    createLineSpacing();
                    System.out.println("WARNING! " + exception.getMessage());
                    createLineSpacing();
                }
                break;

            //when there is a new event to add
            case "event":
                try {
                    Commands.eventCommand(line, command, list);
                }
                catch (BinksException exception){
                    createLineSpacing();
                    System.out.println("WARNING! " + exception.getMessage());
                    createLineSpacing();
                }
                break;

            //when there is a task to delete
            case "delete":
                try {
                   Commands.deleteCommand(command, list);
                }
                catch (BinksException exception){
                    createLineSpacing();
                    System.out.println("Warning! " + exception.getMessage());
                    createLineSpacing();
                }
                break;
            //when there is keyword to find
            case "find":
                try {
                    Commands.findCommand(command, list);
                }
                catch (BinksException exception){
                    createLineSpacing();
                    System.out.println("Warning! " + exception.getMessage());
                    createLineSpacing();
                }
                break;
            default:
                createLineSpacing();
                System.out.println("This is not a valid command!");
                createLineSpacing();
            }
        }
    }
}
