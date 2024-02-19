import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static class InvalidCommandException extends Exception {
        public void printErrorMessage(){
            printMessage("Invalid Command!!\nYAMAGATA kita baby FACE!!");
        }
    }

    public static class EndListException extends Exception {

    }

    public static class MarkInstructionNotIntegerException extends Exception {
        public void printErrorMessage() {
            printMessage("Please input an integer!!\nInvalid mark instruction ZENBU FAKE!!");
        }
    }

    public static class MarkInstructionOutOfBoundsException extends Exception {
        public void printErrorMessage() {
            printMessage("Task does not exist!!\nInvalid mark instruction ZENBU FAKE!!");
        }
    }

    public static final String chatbotName = "Noriaki";
    public static final String[] validCommands =
            {"list", "mark", "unmark", "todo", "deadline", "event", "bye"};
    public static List<Task> taskList = new ArrayList<>();

    /**
     * Prints line of 30 underscores.
     */
    public static void printLine(){
        int lineUnderscore = 30;
        for(int i = 0; i < lineUnderscore; i++){
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Prints line, followed by message, followed by line.
     *
     * @param message Message to be printed.
     */
    public static void printMessage(String message){
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Prints greeting.
     */
    public static void greet(){
        String greetMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?";
        String logo =
                " _______               .__        __   .__ \n" +
                " \\      \\   ___________|__|____  |  | _|__|\n" +
                " /   |   \\ /  _ \\_  __ \\  \\__  \\ |  |/ /  |\n" +
                "/    |    (  <_> )  | \\/  |/ __ \\|    <|  |\n" +
                "\\____|__  /\\____/|__|  |__(____  /__|_ \\__|\n" +
                "        \\/                     \\/     \\/   \n";

        System.out.println("Hello from\n" + logo);
        printMessage(greetMessage);
    }

    /**
     * Prints goodbye message.
     */
    public static void goodbye(){
        String goodbyeMessage = "Bye! Hope to see you again soon! MEGANE!!";

        printMessage(goodbyeMessage);
    }

    /**
     * Prints list of tasks.
     */
    public static void printList(){
        printLine();
        for(int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
        printLine();
    }

    /**
     * Prints a newly-added task.
     */
    public static void printAddedTask(){
        Task lastTask = taskList.get(taskList.size() - 1);
        printMessage("Got it. I've added this task:\n"
                + "  " + lastTask + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Adds a to-do task to list of tasks.
     *
     * @param argument information of to-do to be added.
     */
    public static void addToDo(String argument) throws MissingParamsException {
        Task newTask = new ToDo(argument);
        taskList.add(newTask);
        printAddedTask();
    }

    /**
     * Adds a deadline task to list of tasks.
     * @param argument information of deadline to be added.
     */
    public static void addDeadline(String argument) throws MissingParamsException {
        String[] tokens = argument.split("/");
        String description = "", by = "";

        for(String token : tokens){
            String[] subTokens = token.split(" ", 2);
            String subCommand = subTokens[0].toLowerCase().trim();
            String subArgument = "";
            if (subTokens.length > 1) {
                subArgument = subTokens[1];
            }

            if (subCommand.equalsIgnoreCase("by")) {
                by = subArgument;
            } else {
                description = token.trim();
            }
        }

        Task newTask = new Deadline(description, by);
        taskList.add(newTask);
        printAddedTask();
    }

    /**
     * Adds an event task to list of tasks.
     *
     * @param argument information of deadline to be added.
     */
    public static void addEvent(String argument) throws MissingParamsException {
        String[] tokens = argument.split("/");
        String description = "", start = "", end = "";

        for(String token : tokens){
            String[] subTokens = token.split(" ", 2);
            String subCommand = subTokens[0].toLowerCase().trim();
            String subArgument = "";
            if (subTokens.length > 1) {
                subArgument = subTokens[1];
            }

            switch (subCommand){
            case "from":
                start = subArgument;
                break;
            case "to":
                end = subArgument;
                break;
            default:
                description = token.trim();
                break;
            }
        }

        Task newTask = new Event(description, start, end);
        taskList.add(newTask);
        printAddedTask();
    }

    /**
     * Marks task as done or undone.
     *
     * @param instruction User instruction on which task to mark.
     * @param done Status of task - done or undone.
     */
    public static void markTask(String instruction, boolean done)
            throws MarkInstructionNotIntegerException, MarkInstructionOutOfBoundsException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(instruction);
        } catch (NumberFormatException e) {
            throw new MarkInstructionNotIntegerException();
        }

        try {
            taskList.get(taskNumber - 1).setDone(done);
            printMessage("Nice! I've marked this task as done:\n"
                    + "  " + taskList.get(taskNumber - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new MarkInstructionOutOfBoundsException();
        }
    }

    public static void executeCommand(String command, String argument)
            throws MissingParamsException, InvalidCommandException, EndListException,
            MarkInstructionOutOfBoundsException, MarkInstructionNotIntegerException {
        if (command.equalsIgnoreCase("bye")) {
            throw new EndListException();
        }

        if (Arrays.stream(validCommands).noneMatch(command::equals)){
            throw new InvalidCommandException();
        }

        switch (command) {
        case "list":
            printList();
            break;
        case "mark":
            markTask(argument, true);
            break;
        case "unmark":
            markTask(argument, false);
            break;
        case "todo":
            addToDo(argument);
            break;
        case "deadline":
            addDeadline(argument);
            break;
        case "event":
            addEvent(argument);
            break;
        }
    }

    /**
     * Creates a list that users can add tasks to, read, and mark tasks as done or undone.
     */
    public static void startList(){
        String line;
        Scanner in = new Scanner(System.in);

        while(true){
            line = in.nextLine();

            String[] tokens = line.split(" ", 2);

            String command = tokens[0].toLowerCase().trim();
            String argument = "";

            // If input contains more than one word, assign remaining words to argument
            if (tokens.length > 1) {
                argument = tokens[1].trim();
            }

            try {
                executeCommand(command,argument);
            } catch (EndListException e) {
                return;
            } catch (InvalidCommandException e) {
                e.printErrorMessage();
            } catch (MissingParamsException e) {
                String errorMessage = "The following parameters are missing:\n"
                        + e + "\n"
                        + "YOU GOTTA SAVE ME AND MY HEART!!";
                printMessage(errorMessage);
            } catch (MarkInstructionOutOfBoundsException e) {
                e.printErrorMessage();
            } catch (MarkInstructionNotIntegerException e) {
                e.printErrorMessage();
            }
        }
    }

    public static void main(String[] args){
        greet();
        startList();
        goodbye();
    }
}