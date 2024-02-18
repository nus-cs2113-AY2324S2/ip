package Binks;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Binks {

    public static void loadFileContents(List list, String file) throws FileNotFoundException{
        File f = new File(file);
        Scanner s = new Scanner(f);
        String description;
        for (int i = 1; s.hasNext(); i++) {
            String line = s.nextLine();
            String taskType = line.substring(1, 2);
            switch (taskType.toLowerCase()) {
            case "t":
                list.addTodo(line.substring(7));
                if (line.charAt(4) == 'X') {
                    list.markAsDone(i);
                }
                break;
            case "d":
                description = line.substring(7, line.indexOf(" (by:"));
                String deadline = line.substring(line.indexOf("(by: ") + 5, line.indexOf(")"));
                list.addDeadline(description + " (by: " + deadline + ")");
                if (line.charAt(4) == 'X') {
                    list.markAsDone(i);
                }
                break;
            case "e":
                description = line.substring(7, line.indexOf(" (from:"));
                String startTime = line.substring(line.indexOf("(from: ") + 7, line.indexOf(" to:"));
                String endTime = line.substring(line.indexOf("to:") + 4, line.indexOf(")"));
                list.addEvent(description + " (from: " + startTime + " to: " + endTime + ")");
                if (line.charAt(4) == 'X'){
                    list.markAsDone(i);
                }
                break;
            }
        }
        System.out.println("I have added all the tasks that you have saved!");
        System.out.println("Is there anything more tasks I can list for you?");
        createLineSpacing();
    }
    public static void readFile(List list) {
        try {
            loadFileContents(list,"C:\\Users\\eugen\\OneDrive\\Documents\\ip\\src\\main\\java\\binkslist.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
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

    /**
     * Handles the case where the mark command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException if command.length != 2
     */
    public static void markCommand(String[] command, List list) throws BinksException {
        if (command.length == 2) {
            int taskNumber = Integer.parseInt(command[1]);
            list.markAsDone(taskNumber);
        } else {
            throw new BinksException("Please specify which task to mark.");
        }
    }

    /**
     * Handles the case where the unmark command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException if command.length != 2
     */
    public static void unmarkCommand(String[] command, List list) throws BinksException {
        if (command.length == 2) {
            int taskNumber = Integer.parseInt(command[1]);
            list.unmarkAsDone(taskNumber);
        } else {
            throw new BinksException("Please specify which task to unmark!");
        }
    }

    /**
     * Handles the case where the todo command is given as input
     *
     * @param line Line of the initial command string including spaces
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException when command.length < 2
     */
    public static void todoCommand(String line, String[] command, List list) throws BinksException {
        if (command.length >= 2) {
            list.addTodo(line.substring(5));
        } else {
            throw new BinksException("The description of todo cannot be empty!");
        }
    }

    /**
     * Handles the case where the deadline command is given as input
     *
     * @param line Line of the initial command string including spaces
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException when command.length < 2 or deadline is not given
     */
    public static void deadlineCommand(String line, String[] command, List list) throws BinksException {
        if (command.length >= 2) {
            int bySeparator = line.indexOf("/by ");
            if (bySeparator != -1) {
                String deadline = line.substring(bySeparator + 4);
                String task = line.substring(9, bySeparator);
                list.addDeadline(task + "(by: " + deadline + ")");
            } else {
                throw new BinksException("Please indicate a deadline using /by!");
            }
        } else {
            throw new BinksException("The description of deadline cannot be empty!");

        }
    }

    /**
     * Handles the case where the event command is given as input
     *
     * @param line Line of the initial command string including spaces
     * @param command Command array of input string without spaces
     * @param list List of tasks
     * @throws BinksException when command.length < 2 or timeframe is not given
     */
    public static void eventCommand(String line, String[] command, List list) throws BinksException {
        if (command.length >= 2) {
            int fromSeparator = line.indexOf("/from ");
            int toSeparator = line.indexOf("/to ");
            if (fromSeparator != -1 && toSeparator != -1) {
                String startTime = line.substring(fromSeparator + 6, toSeparator);
                String endTime = line.substring(toSeparator + 4);
                String task = line.substring(6, fromSeparator);
                list.addEvent(task + "(from: " + startTime + "to: " + endTime + ")");
            } else {
                throw new BinksException("Please specify start and end times using /from and /to!");
            }
        } else {
            throw new BinksException("The description of event cannot be empty!");
        }
    }

    public static void deleteCommand(String[] command, List list) throws BinksException {
        if (command.length == 2) {
            int taskNumber = Integer.parseInt(command[1]);
            list.deleteTask(taskNumber);
        } else {
            throw new BinksException("Please specify which task to delete.");
        }
    }

    public static void main(String[] arg) {
        File f = new File ("C:\\Users\\eugen\\OneDrive\\Documents\\ip\\src\\main\\java\\binkslist.txt");
        greetUser();
        List list = new List();
        readFile(list);
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
                    markCommand(command, list);
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
                    unmarkCommand(command, list);
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
                    todoCommand(line, command, list);
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
                    deadlineCommand(line, command, list);
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
                    eventCommand(line, command, list);
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
                    deleteCommand(command, list);
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
