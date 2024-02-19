import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duck {

    private static final String LINE_SEPARATOR = "____________________________________________________________";
//    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        System.out.println(LINE_SEPARATOR + "\n" +
                "Hello! I'm Duck\n" +
                "What can I do for you?\n" +
                "  _____  _    _  _____ _  __\n" +
                " |  __ \\| |  | |/ ____| |/ /\n" +
                " | |  | | |  | | |    | ' / \n" +
                " | |  | | |  | | |    |  <  \n" +
                " | |__| | |__| | |____| . \\ \n" +
                " |_____/ \\____/ \\_____|_|\\_\\");

        ArrayList<Task> tasks = new ArrayList<>(); //stores Tasks in ArrayList called tasks
        int index = 0; //index of where the userInput is stored in texts
        handleUserInput(tasks, index);
    }

    private static void handleUserInput(ArrayList<Task> tasks, int index) {
        boolean isFinished = false; //isFinished will be true if user types in bye and the program is finished and terminates

        while (!isFinished) {
            try {
                Scanner in = new Scanner(System.in);
                String userInput = in.nextLine();
                if (userInput.equals("list")) {
                    Task.listTasks(tasks, index);
                    System.out.println(LINE_SEPARATOR + "\n");
                } else if (userInput.startsWith("mark ")) {
                    Task.markTask(tasks, userInput, index);
                } else if (userInput.startsWith("unmark ")) {
                    Task.unmarkTask(tasks, userInput, index);
                } else if (userInput.startsWith("todo ")) {
                    index = ToDo.addToDo(tasks, userInput, index);
                } else if (userInput.startsWith("deadline ")) {
                    index = Deadline.addDeadline(tasks, userInput, index);
                } else if (userInput.startsWith("event ")) {
                    index = Event.addEvent(tasks, userInput, index);
                } else if (userInput.startsWith("delete ")) {
                    index = Task.deleteTask(tasks,userInput,index);
                } else if (userInput.startsWith("bye")) {
                    System.out.println(LINE_SEPARATOR + "\n" + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR);
                    isFinished = true;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, that is not a valid command. Please try again!");
            }
        }
    }
}
