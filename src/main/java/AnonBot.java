import java.util.Scanner;

import task.TaskManager;

public class AnonBot {
    // ASCII Art Generated from: https://patorjk.com/software/taag/#p=display&f=Big&t=anonBot
    private static final String LOGO = "                          ____        _   \n"
            + "                         |  _ \\      | |  \n"
            + "   __ _ _ __   ___  _ __ | |_) | ___ | |_ \n"
            + "  / _` | '_ \\ / _ \\| '_ \\|  _ < / _ \\| __|\n"
            + " | (_| | | | | (_) | | | | |_) | (_) | |_ \n"
            + "  \\__,_|_| |_|\\___/|_| |_|____/ \\___/ \\__|\n";
    private static final String SECTION_BAR = "____________________________________________________________";

    /**
     * Splits the user's command into individual words. Space-delimited.
     *
     * @param userInput Command from the user.
     * @return String array containing space-delimited words.
     */
    private static String[] getCommandArguments(String userInput) {
        return userInput.split(" ");
    }

    /**
     * Takes user inputs as commands and process them.
     * If the input is not one of the supported commands below, the input shall be seen as a new task.
     * Tasks are NOT saved when the program exits.
     * Possible commands:
     * 1. `bye`, `exit` - Exits the program.
     * 2. `list` - Lists out all the tasks.
     * 3. `mark <task_number> - Marks specific task as done.
     * 4. `unmark` <task_number> - Marks specific task as undone.
     */
    private static void parseUserInputs() {
        Scanner in = new Scanner(System.in);
        String userInput;
        String[] commands;
        int taskNumber;

        while (true) {
            userInput = in.nextLine();
            commands = getCommandArguments(userInput);
            switch (commands[0]) {
            case "exit":
                // fallthrough
            case "bye":
                printGoodbye();
                return;
            case "list":
                System.out.println(SECTION_BAR);
                TaskManager.printTaskList();
                System.out.println(SECTION_BAR + "\n");
                break;
            case "mark":
                System.out.println(SECTION_BAR);
                if (commands.length >= 2) {
                    taskNumber = Integer.parseInt(commands[1]);
                    TaskManager.markTaskAsDone(taskNumber);
                }
                System.out.println(SECTION_BAR + "\n");
                break;
            case "unmark":
                System.out.println(SECTION_BAR);
                if (commands.length >= 2) {
                    taskNumber = Integer.parseInt(commands[1]);
                    TaskManager.markTaskAsUndone(taskNumber);
                }
                System.out.println(SECTION_BAR + "\n");
                break;
            default:
                TaskManager.createNewTask(userInput);
                System.out.println(SECTION_BAR);
                System.out.println("Added: " + userInput);
                System.out.println(SECTION_BAR + "\n");
            }
        }
    }

    /**
     * Prints greeting messages.
     */
    private static void printGreetings() {
        System.out.println(LOGO);
        System.out.println(SECTION_BAR);
        System.out.println("Hello! I'm anonBot");
        System.out.println("What can I do for you?");
        System.out.println(SECTION_BAR + "\n");
    }

    /**
     * Prints goodbye message.
     */
    private static void printGoodbye() {
        System.out.println(SECTION_BAR);
        System.out.println("See ya!");
        System.out.println(SECTION_BAR);
    }

    /**
     * Greets the user and process user inputs.
     *
     * @param args List of command-line arguments.
     */
    public static void main(String[] args) {
        printGreetings();
        parseUserInputs();
    }
}
