import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void welcomeMessage() {
        System.out.println(LINE_SEPARATOR + "\n" +
                "Hello! I'm Duck\n" +
                "What can I do for you?\n" +
                "  _____  _    _  _____ _  __\n" +
                " |  __ \\| |  | |/ ____| |/ /\n" +
                " | |  | | |  | | |    | ' / \n" +
                " | |  | | |  | | |    |  <  \n" +
                " | |__| | |__| | |____| . \\ \n" +
                " |_____/ \\____/ \\_____|_|\\_\\");
    }

    public static boolean exitMessage() {
        boolean isFinished;
        System.out.println(LINE_SEPARATOR + "\n" + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR);
        isFinished = true;
        return isFinished;
    }

    public static void handleUserInput(ArrayList<Task> tasks, int index) throws IOException {
        boolean isFinished = false; //isFinished will be true if user types in bye and the program is finished and terminates

        while (!isFinished) {
            try {
                Scanner in = new Scanner(System.in);
                String userInput = in.nextLine();
                if (userInput.equals("list")) {
                    Parser.listTasks(tasks, index);
                    System.out.println(LINE_SEPARATOR + "\n");
                } else if (userInput.startsWith("mark ")) {
                    Parser.markTask(tasks, userInput, index);
                } else if (userInput.startsWith("unmark ")) {
                    Parser.unmarkTask(tasks, userInput, index);
                } else if (userInput.startsWith("todo ")) {
                    index = ToDo.addToDo(tasks, userInput, index);
                } else if (userInput.startsWith("deadline ")) {
                    index = Deadline.addDeadline(tasks, userInput, index);
                } else if (userInput.startsWith("event ")) {
                    index = Event.addEvent(tasks, userInput, index);
                } else if (userInput.startsWith("delete ")) {
                    index = Parser.deleteTask(tasks,userInput,index);
                } else if (userInput.startsWith("bye")) {
                    Storage.updateFile(tasks);
                    isFinished = exitMessage();
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, that is not a valid command. Please try again!");
            }
        }
    }
}
