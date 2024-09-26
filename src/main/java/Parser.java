import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the methods that handles user input
 * It interacts with the TaskList, ToDo, Deadline, Storage, and Ui classes to perform various operations
 * such as listing tasks, marking tasks as done, adding tasks, deleting tasks, finding tasks, and exiting the program.
 *
 */
public class Parser {

    private static final String LINE_SEPARATOR = "____________________________________________________________";

    /**
     *
     * @param tasks arraylist of tasks
     * @param index number of tasks in arraylist tasks
     * @throws IOException If an I/O exception occurs during file handling.
     */
    public static void handleUserInput(ArrayList<Task> tasks, int index) throws IOException {
        boolean isFinished = false; //isFinished will be true if user types in bye and the program is finished and terminates

        while (!isFinished) {
            try {
                Scanner in = new Scanner(System.in);
                String userInput = in.nextLine();
                if (userInput.equals("list")) {
                    TaskList.listTasks(tasks, index);
                    System.out.println(LINE_SEPARATOR + "\n");
                } else if (userInput.startsWith("mark ")) {
                    TaskList.markTask(tasks, userInput, index);
                } else if (userInput.startsWith("unmark ")) {
                    TaskList.unmarkTask(tasks, userInput, index);
                } else if (userInput.startsWith("todo ")) {
                    index = ToDo.addToDo(tasks, userInput, index);
                } else if (userInput.startsWith("deadline ")) {
                    index = Deadline.addDeadline(tasks, userInput, index);
                } else if (userInput.startsWith("event ")) {
                    index = Event.addEvent(tasks, userInput, index);
                } else if (userInput.startsWith("delete ")) {
                    index = TaskList.deleteTask(tasks,userInput,index);
                } else if (userInput.startsWith("find ")) {
                    TaskList.findTask(tasks,userInput);
                } else if (userInput.startsWith("bye")) {
                    Storage.updateFile(tasks);
                    isFinished = Ui.exitMessage();
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, that is not a valid command. Please try again!");
            }
        }
    }
}
