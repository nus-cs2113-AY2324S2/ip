import java.util.Scanner;
import java.util.ArrayList;

//All src/java files use this Java Coding Standard: https://se-education.org/guides/conventions/java/index.html

public class Duke {

    public static final int MAX_NUM_COMMANDS = 100;
    static ArrayList<Task> enteredCommands = new ArrayList<>();


    /**
     * Prints greeting for chatbot user upon initial program execution
     *
     */
    public static void greet() {
        System.out.printf("%s%n", "____________________________________________________________");
        System.out.printf("%s%n", "Hello! I'm CHAT-MAN");
        System.out.printf("%s%n", "What can I do for you?");
    }

    /**
     * Prints goodbye message for chatbot user; called if they enter "bye" command
     *
     */
    public static void exit() {
        System.out.printf("%s%n", "____________________________________________________________");
        System.out.printf("%s%n", "Bye. Hope to see you again soon!");
        System.out.printf("%s%n", "____________________________________________________________");
    }

    /**
     * Prints message when chatbot user enters a task for list; prints specific task added and new array list size
     *
     */
    public static void replyAddedTask() {
        Task addedTask = enteredCommands.get(enteredCommands.size() - 1);
        int size = enteredCommands.size();

        System.out.printf("Got it. I've added this task:%n%s%nNow you have %d tasks in the list.%n",
                addedTask.toString(), size);
    }

    /**
     * Parses task entered by user to determine specific subclass then adds appropriate object to array list
     *
     * @param userCommand user's command specifying task-to-add's description & other subclass relevant details
     */
    public static void addTask(String userCommand) {

        System.out.printf("%s%n", "____________________________________________________________");
        String taskType = userCommand.split(" ")[0].toUpperCase();

        switch (taskType) {

        case "TODO":
            String toDoDesc = userCommand.replaceAll("(?i)TODO", "");

            enteredCommands.add(new Todo(toDoDesc));

            replyAddedTask();
            break;

        case "DEADLINE":
            String[] deadLine = userCommand.split("/");
            String deadLineDesc = deadLine[0].replaceAll("(?i)DEADLINE", "");
            String by = deadLine[1].replaceAll("(?i)BY", "").stripLeading();

            enteredCommands.add(new Deadline(deadLineDesc, by));

            replyAddedTask();
            break;

        case "EVENT":
            String[] event = userCommand.split("/");
            String eventDesc = event[0].replaceAll("(?i)EVENT", "");
            String from = event[1].replaceAll("(?i)FROM", "").stripLeading();
            String to = event[2].replaceAll("(?i)TO", "").stripLeading();

            enteredCommands.add(new Event(eventDesc, from, to));

            replyAddedTask();
            break;

        default:
            break;
        }

    }

    /**
     * Prints return value from each task object's toString() method for entire array list
     *
     */
    public static void list() {
        System.out.printf("%s%n", "____________________________________________________________");
        for (int i = 0; i < enteredCommands.size(); i++) {
            System.out.printf("%d.%s%n", (i + 1), enteredCommands.get(i).toString());
        }
    }

    /**
     * Calls specified task object's setDone() method with true/false argument depending on mark/unmark user command.
     * Uses integer value provided with user's mark/unmark command to identify specified task within array list.
     *
     * @param userCommand user's mark/unmark command which provides integer value of task in array list
     * @param markChoice specifies whether to mark or unmark task as complete
     */
    public static void markUnmark(String userCommand, String markChoice) {

        switch (markChoice) {

        case "MARK":
            try {
                boolean mark = true;
                int toMark = Integer.parseInt(userCommand.replaceAll("[^0-9]", "")) - 1;

                enteredCommands.get(toMark).setDone(mark);

                System.out.printf("%s%n", "____________________________________________________________");
                System.out.printf("Nice! I've marked this task as done:%n%s%n",
                        enteredCommands.get(toMark).toString());
            } catch (Exception e) {
                System.out.printf("%s%n", "____________________________________________________________");
                System.out.printf("Could not complete mark request.%n");
            }
            break;

        case "UNMARK":
            try {
                boolean mark = false;
                int toUnmark = Integer.parseInt(userCommand.replaceAll("[^0-9]", "")) - 1;

                enteredCommands.get(toUnmark).setDone(false);

                System.out.printf("%s%n", "____________________________________________________________");
                System.out.printf("OK, I've marked this task as not done yet:%n%s%n",
                        enteredCommands.get(toUnmark).toString());
            } catch (Exception E) {
                System.out.printf("%s%n", "____________________________________________________________");
                System.out.printf("Could not complete unmark request.%n");
            }
            break;

        }

    }

    /**
     * Parses command entered by chatbot user and calls corresponding method with any necessary arguments
     *
     * @param userCommand command entered by chatbot user
     * @return boolean specifying whether or not to exit chatbot after executing user command
     */
    public static boolean executesCommand(String userCommand) {

        String[] separatedCommand = userCommand.split(" ");

        switch (separatedCommand[0].toUpperCase()) {

        default:
            addTask(userCommand);
            return false;
            //break statement unneeded due to return statement

        case "BYE":
            exit();
            return true;
            //break statement unneeded due to return statement


            case "LIST":
            list();
            return false;
            //break statement unneeded due to return statement


            case "MARK":
            markUnmark(userCommand, "MARK");
            return false;
            //break statement unneeded due to return statement


            case "UNMARK":
            markUnmark(userCommand, "UNMARK");
            return false;
            //break statement unneeded due to return statement


        }

    }

    /**
     * Accepts user command input and passes to executesCommand() as argument for command execution.
     * Loops to allow continuous command entry.
     *
     */
    public static void getUserCommands() {
        Scanner commandReader = new Scanner(System.in);
        String userCommand;
        boolean shouldExitLoop = false;

        while (!shouldExitLoop) {
            if (enteredCommands.size() >= MAX_NUM_COMMANDS) {
                exit();
                shouldExitLoop = true;
                continue;
            }

            System.out.printf("%s%n%n", "____________________________________________________________");
            userCommand = commandReader.nextLine();
            shouldExitLoop = executesCommand(userCommand);

        }

    }

    /**
     * Main method of chatbot
     *
     */
    public static void main(String[] args) {
        greet();
        getUserCommands();

    }
}
