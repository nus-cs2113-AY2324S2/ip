
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements a parser that parses the user input and performs the necessary
 * operations.
 *
 * @author nigelheng
 * @since February 2024
 * @version 1.0
 */
public class Parser {
    private static final String LINE = "____________________________________________________________";
    private static final String ErrorLINE = "************************************************************";

    /**
     * Parses the commands from the user input and performs the necessary operations.
     * Operations include adding, deleting tasks and
     * marking, unmarking and listing tasks.
     */
    public static void taskManager() {
        String userInput;
        Scanner in = new Scanner(System.in);

        userInput = in.nextLine();

        while (!userInput.equalsIgnoreCase("bye")){
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    UI.showList();
                } else if (userInput.startsWith("mark")) {
                    TaskList.operateTask(userInput, TaskList.taskStatus.MARK);
                } else if (userInput.startsWith("unmark")) {
                    TaskList.operateTask(userInput, TaskList.taskStatus.UNMARK);
                } else if (userInput.startsWith("delete")) {
                    TaskList.operateTask(userInput, TaskList.taskStatus.DELETE);

                } else if (userInput.startsWith("find")) {
                    ArrayList<Task> tasksWithKeyword = TaskList.findTasks(userInput);
                    System.out.println(LINE);
                    System.out.println("Here are the tasks found:");
                    int displayedTaskNum;
                    for (int index = 0; index < tasksWithKeyword.size(); index += 1) {
                        displayedTaskNum = index + 1;
                        Task displayedTask = tasksWithKeyword.get(index);
                        System.out.println(displayedTaskNum + " " + displayedTask);
                    }
                    System.out.println(LINE);

                } else if (userInput.equalsIgnoreCase("/help")) {
                    printHelpMessage();
                } else {
                    TaskList.addTask(userInput, false);
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println(LINE);
                System.out.println("Either the task doesn't exist or you didn't type a number");
                System.out.println("Try a different number or type list to check");
                System.out.println(LINE);
            }
            Storage.saveFile();
            userInput = in.nextLine();
        }
    }


    /**
     * Parses the input to create a Task object based on the input type.
     *
     * @param userInput The string input provided by the user.
     * @param taskToParse The task object to be parsed or initialized based on the user input.
     * @return A Task object parsed from the user input, or null if the input is invalid.
     */

    public static Task parseTask(String userInput) {
        Task taskToParse;
        try {
            if (userInput.startsWith("todo")) {
                taskToParse = new Todo(userInput);
            } else if (userInput.startsWith("deadline")) {
                taskToParse = new Deadline(userInput);
            } else if (userInput.startsWith("event")) {
                taskToParse = new Event(userInput);
            } else {
                InvalidInput();
                return null;
            }
        } catch (InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
            System.out.println(ErrorLINE);
            e.printExceptionMessage();
            System.out.println(ErrorLINE);
            return null;
        }
        return taskToParse;
    }

    private static void printHelpMessage() {
        System.out.println(LINE);
        System.out.println("Eln sees you require some help");
        System.out.println("Eln will try his best but don't expect too much");
        System.out.println();
        System.out.println("To create a todo, type");
        System.out.println("todo (task to do)");
        System.out.println();
        System.out.println("To create a deadline, type");
        System.out.println("deadline (task to be completed) /by (by when)");
        System.out.println();
        System.out.println("To create an event, type");
        System.out.println("event (the event) /from (start date/time) /to (end date/time)");
        System.out.println();
        System.out.println("Other things you can type include:");
        System.out.println("(list) for a list of tasks");
        System.out.println("(mark) to mark the task as done");
        System.out.println("(unmark) to unmark the task");
        System.out.println("(delete) to delete a task");
        System.out.println("and");
        System.out.println("(find) to look for keywords in your tasks");
        System.out.println();
        System.out.println(LINE);
        System.out.println("Eln hopes that this has been of help. If not there's nothing else Eln can do.");
        System.out.println(LINE);
    }

    private static void InvalidInput() {
        System.out.println(ErrorLINE);
        System.out.println("That doesn't really make sense to me.");
        System.out.println("Blink 3 times quick and type /help if you need assistance");
        System.out.println(ErrorLINE);
    }
}