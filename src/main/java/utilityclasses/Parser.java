package utilityclasses;
import drosstasks.DrossList;
import utilityclasses.TaskActions;
import java.util.Scanner;


/**
 * The Parser class is responsible for interpreting and executing user input commands related to task management.
 */
public class Parser {
    private static Scanner in = new Scanner(System.in);
    private static TaskActions actions;

    /**
     * Reads a line of text from standard input, trimming any leading or trailing whitespace.
     * @return The trimmed input line.
     */
    public static String readLine() {
        return in.nextLine().trim();
    }

    /**
     * Processes and executes commands entered by the user until the "bye" command is received.
     * This method supports a variety of tasks such as listing tasks, marking tasks as done or undone,
     * creating new tasks, deleting tasks, and finding tasks.
     * @param list The DrossList containing the tasks to be manipulated.
     */
    public static void readParseExecuteUserInput(DrossList list) {
        actions = new TaskActions();
        String line = readLine();
        while (!line.equals("bye")) {
            if (line.startsWith("list")) {
                actions.listAllTasks(list);
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                actions.toggleMark(line, list);
            } else if (line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event")) {
                actions.handleTaskCreation(line, list);
            } else if (line.startsWith("find")){
                actions.handleSearchForTask(line.substring(line.indexOf("find ") + "find ".length()), list);
            }
            else if (line.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(line.split(" ")[1]);
                    actions.handleDeleteTask(index, list);
                    FileIO.saveTasksToFile(list);
                    System.out.println("Task " + index + " successfully deleted boss");
                    Ui.printLine();
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Delete what lah");
                    Ui.printLine();
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Delete simi delete");
                    Ui.printLine();
                }
            }
            else {
                Ui.printLine();
                System.out.println("Please enter a valid command");
                Ui.printLine();
            }
            line = readLine();
        }
    }

}
