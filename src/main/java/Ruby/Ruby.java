package Ruby; /**
 * Main class for the chatbot named Ruby.
 * Ruby assists users in recording and organizing their daily tasks.
 * It interacts with users through the command line, processing commands to manage tasks.
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ruby {
    private static final Scanner in  = new Scanner(System.in);
    private static TaskList t1 = new TaskList();
    private static String userInput; //User command input through the command line


    public static void main(String[] args) {
        greet();
        processor();
        exit();
    }

    /**
     * Prints a greeting message to the user.
     */
    private static void greet(){
        print(new String[]{"Hi, I am here. Greets from Ruby.", "What can I do for you?"});
        try{
            t1.readFileRecords();
        }catch (IOException e){
            System.out.println("There is no records found.");
        }
    }

    /**
     * Prints an exit message and terminates the chatbot.
     */
    private static void exit(){
        print("Bye. Always feels good work with you.");
    }

    /**
     * Main processing loop for user commands.
     * Continuously captures and processes user input until the "bye" command is issued.
     */
    private static void processor(){
        String [] userInputs = inputCatcher();
    private static void run(){
        t1.setTaskList(s1.loadFile());
        String[] userInputs = p1.inputParser(u1.readUserInput());

        while (p1.isTerminateCommand()) {
            switch (userInputs[0].toLowerCase()){
            case "list":
                t1.showTaskList();
                break;
            case "mark":
                p1.parseMarkTask(t1);
                break;
            case "unmark":
                p1.parseUnmarkTask(t1);
                break;
            case "delete":
                p1.parseDeleteTask(t1);
                break;
            case "todo":
            case "deadline":
            case "event":
                p1.parseAddTask(t1);
                break;
            default:
                p1.parseDefault();
                break;
            }
            t1.saveToFile();
            userInputs = inputCatcher();
        }
    }

    /**
     * Captures and splits the user's input into a command and arguments.
     *
     * @return An array of strings, where the first element is the command, and subsequent elements are arguments.
     */
    private static String[] inputCatcher(){
        userInput = in.nextLine();
        return userInput.split(" ");
    }

    /**
     * Checks if the user's input is a command to continue or to terminate the session.
     *
     * @param userInputs An array of user inputs split into command and arguments.
     * @return true if the session should continue, false if it should terminate.
     */
    private static boolean checkout(String[] userInputs){

        return !userInputs[0].equalsIgnoreCase("bye");
    }

    /**
     * Prints a formatted message to the console.
     *
     * @param thingToPrint The message to be printed.
     */
    private static void print(String thingToPrint){
        System.out.println("    " + "--------------");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }

    /**
     * Prints multiple formatted messages to the console.
     *
     * @param thingsToPrint An array of messages to be printed.
     */
    private static void print(String[] thingsToPrint){
        System.out.println("    " + "--------------");
        for (String thing: thingsToPrint){
            System.out.println("    " + thing);
        }
        System.out.println("    " + "--------------");
    }
}
