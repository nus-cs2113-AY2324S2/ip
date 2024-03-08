package lovie.ui;

import lovie.task.Task;
import lovie.task.TaskList;
import java.util.ArrayList;

public class Ui {

    public static final String DASHED_LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String INTRODUCTION_MESSAGE = "Hey hey! My name is Lovie! How can I help you today?\n" +
            "Just a heads up: you can type 'command' for a list of everything you can ask me!";
    public static final String GOODBYE_MESSAGE = "Thanks for using me! See you next time ♡〜٩( ˃▿˂ )۶〜♡";
    private static final String LOGO = "██╗░░░░░░█████╗░██╗░░░██╗██╗███████╗ \n" +
            "██║░░░░░██╔══██╗██║░░░██║██║██╔════╝ \n" +
            "██║░░░░░██║░░██║╚██╗░██╔╝██║█████╗░░ \n" +
            "██║░░░░░██║░░██║░╚████╔╝░██║██╔══╝░░ \n" +
            "███████╗╚█████╔╝░░╚██╔╝░░██║███████╗ \n" +
            "╚══════╝░╚════╝░░░░╚═╝░░░╚═╝╚══════╝ \n";
    public static final String FURTHER_HELP_MESSAGE = "\nCan I help you with anything else?";
    public static final String NO_TASK_NUMBER_MESSAGE = "Sorry, there is no record of a task number ";
    public static final String SPACE = " ";
    public static final String NEED_A_KEYWORD_MESSAGE = "Oopsies. Please make sure to include a keyword while " +
            "using the find command!";
    public static final String ALL_COMMANDS_MESSAGE = "Here are all the possible commands you can ask me:\n\n" +
            "(1) To add a todo: todo **description** \n(2) To add an event: event **description** /from **start**" +
            "/to **end**\n(3) To add a deadline: deadline **description** /by **end**\n(4) To list all tasks: list\n"
            + "(5) To mark a task as done: mark **task number**\n(6) To unmark a task as not done: unmark " +
            "**task number**\n(7) To delete a task: delete **task number**\n" +
            "(8) To find tasks with a keyword: find **keyword**\n" +
            "(9) To ask for a list of all commands: command\n" +
            "(10) To leave our session: bye";
    public static final String ADD_TASK_MESSAGE = "How cutesie! Of course I'll add that task for you.\nAdded:";
    public static final String EMPTY_LIST_MESSAGE = "Your list is empty right now. \nTry adding a task using one of the following " +
            "command formats: \n\n" + "(1) todo **description** \n" + "(2) event **description** " +
            "/from **start** /to **end**\n" + "(3) deadline **description** /by **end**";
    public static final String MATCHING_TASKS_MESSAGE = "Here are your matching tasks!\n";
    public static final String NO_MATCHING_TASKS_MESSAGE = "Aw, I'm sorry. There were no tasks that contained that keyword.\nTry searching for another.";
    public static final String LIST_NUMBER_BRACKET = ". [";
    public static final String OPEN_CLOSE_BRACKETS = "] [";
    public static final String CLOSE_BRACKET = "] ";
    public static final String NEW_LINE = "\n";
    public static final String MARK_TASK_MESSAGE = "Woo hoo! Good job cutie. Way to go. I've marked this task as done:";
    public static final String OPEN_BRACKET = "[";
    public static final String EMPTY_BRACKETS = "[ ] ";
    public static final String INVALID_COMMAND_MESSAGE = "Sorry, I can't understand what you are saying.\n" +
            "Please try again!";
    public static final String SUPPORT_MESSAGE = "Of course girlie <3 ";
    public static final String HAVE_MESSAGE = "You now have ";
    public static final String HAVE_MESSAGE_PART_TWO = " tasks in the list.\n";
    public static final String UNMARK_MESSAGE = "I've unmarked this task for you:";

    /**
     * Constructor for Ui (empty).
     */
    public Ui() {}

    /**
     * Prints the line.
     *
     * @param line The line to be printed.
     */
    public void print(String line) {
        System.out.println(DASHED_LINE);
        System.out.println(line);
        System.out.println(DASHED_LINE);
    }

    /**
     * Prints the introduction.
     */
    public void introductionPrinter() {
        print(LOGO + INTRODUCTION_MESSAGE);
    }

    /**
     * Prints the goodbye message.
     */
    public void goodbyePrinter() {
        print(GOODBYE_MESSAGE);
    }

    /**
     * Prints the error message if there is no valid task number.
     *
     * @param input The user's input.
     */
    public void noValidNumberPrinter(String input) {
        print(NO_TASK_NUMBER_MESSAGE + input.split(SPACE)[1] +
                FURTHER_HELP_MESSAGE);
    }

    /**
     * Prints the successful unmarked task message.
     *
     * @param selectedTask The task the user wanted unmarked.
     */
    public void unmarkTaskPrinter(Task selectedTask) {
        print(SUPPORT_MESSAGE + UNMARK_MESSAGE + NEW_LINE + OPEN_BRACKET + selectedTask.getTaskIcon() +
                OPEN_CLOSE_BRACKETS + selectedTask.getStatusIcon() + CLOSE_BRACKET + selectedTask.getDescription() +
                selectedTask.getTimespan() + FURTHER_HELP_MESSAGE);
    }
  
    
    /**
     * Prints the error message if user does not include key word for find command.
     */
    public void noValidFindPrinter() {
        print(NEED_A_KEYWORD_MESSAGE);
    }

    /**
     * Prints the successful deleted task message.
     *
     * @param selectedTask The task the user wanted deleted.
     * @param tasksList The list of tasks.
     */
    public void deleteTaskPrinter(Task selectedTask, ArrayList<Task> tasksList) {
        print(SUPPORT_MESSAGE + NEW_LINE + OPEN_BRACKET + selectedTask.getTaskIcon() + OPEN_CLOSE_BRACKETS +
                selectedTask.getStatusIcon() + CLOSE_BRACKET + selectedTask.getDescription() +
                selectedTask.getTimespan() + NEW_LINE + HAVE_MESSAGE + tasksList.size() + HAVE_MESSAGE_PART_TWO +
                FURTHER_HELP_MESSAGE);
    }

    /**
     * Prints the invalid command message.
     */
    public void invalidCommandPrinter() {
        print(INVALID_COMMAND_MESSAGE + ALL_COMMANDS_MESSAGE);
    }

    /**
     * Prints all possible user commands.
     */
    public void commandPrinter() {
        print(SUPPORT_MESSAGE + ALL_COMMANDS_MESSAGE);
    }

    /**
     * Prints the successful added task message.
     *
     * @param newTask The task the user wanted added.
     */
    public void addTaskPrinter(Task newTask) {
        print(ADD_TASK_MESSAGE + SPACE + OPEN_BRACKET + newTask.getTaskIcon() + CLOSE_BRACKET + EMPTY_BRACKETS +
                newTask.getDescription() + newTask.getTimespan());
    }

    /**
     * Prints the successful marked task message.
     *
     * @param selectedTask The task the user wanted marked.
     */
    public void markTaskPrinter(Task selectedTask) {
        print(MARK_TASK_MESSAGE + SPACE + NEW_LINE + OPEN_BRACKET + selectedTask.getTaskIcon() + CLOSE_BRACKET +
                OPEN_BRACKET + selectedTask.getStatusIcon() + CLOSE_BRACKET + selectedTask.getDescription() +
                selectedTask.getTimespan() + NEW_LINE + FURTHER_HELP_MESSAGE);
    }

    /**
     * Prints the empty list message.
     */
    public void emptyListPrinter() {
        print(EMPTY_LIST_MESSAGE);
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasksList The list of tasks.
     */
    public void listPrinter(ArrayList<Task> tasksList) {
        int counter = 0;
        String output = new String();
        while (counter < tasksList.size()) {
            String listNumber = Integer.toString(counter + 1);
            Task selected = tasksList.get(counter);
            output += listNumber + LIST_NUMBER_BRACKET + selected.getTaskIcon() + OPEN_CLOSE_BRACKETS +
                    selected.getStatusIcon() + CLOSE_BRACKET + selected.getDescription() + selected.getTimespan();
            if (counter != tasksList.size() - 1) {
                output += NEW_LINE;
            }
            counter += 1;
        }
        print(output);
    }

    public void findPrinter(TaskList miniList) {
        if (miniList.getSize() == 0) {
            print(NO_MATCHING_TASKS_MESSAGE);
        } else {
            int counter = 0;
            String output = new String();
            while (counter < miniList.getSize()) {
                String listNumber = Integer.toString(counter + 1);
                Task selected = miniList.get(counter);
                output += listNumber + LIST_NUMBER_BRACKET + selected.getTaskIcon() + OPEN_CLOSE_BRACKETS +
                        selected.getStatusIcon() + CLOSE_BRACKET + selected.getDescription() + selected.getTimespan();
                if (counter != miniList.getSize() - 1) {
                    output += NEW_LINE;
                }
                counter += 1;
            }
            print(MATCHING_TASKS_MESSAGE + output);
        }
    }
}
