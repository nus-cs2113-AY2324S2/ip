import Duke.*;
import Tasks.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String line = "____________________________________________________________\n";
        String name = "Duck";
        System.out.println("Hello from\n" + logo);
        System.out.println(line + "Hello! I'm " + name
                + "\n" + "What can I do for you?\n" + "\n"
                + line);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        Task[] tasks = new Task[100];
        boolean InputBYE = userInput.contains("bye");
        while (!InputBYE) {

            System.out.println(line);
            int indexOfMark;
            boolean InputLIST = userInput.contains("list");
            boolean InputMARK = userInput.contains("mark");
            boolean InputUNMARK = userInput.contains("unmark");
            boolean InputDEADLINE = userInput.contains("deadline");
            boolean InputTODO = userInput.contains("todo");
            boolean InputEVENT = userInput.contains("event");
            if (InputLIST) {
                for (int i = 0; i < Task.numOfTask; i++) {
                    System.out.println((i+1) + "." + tasks[i]);
                }

            } else if (InputMARK) {
                if (InputUNMARK) {

                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[indexOfMark].markAsUndone();

                } else {
                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[indexOfMark].markAsDone();
                }
            } else {
                if (InputDEADLINE) {
                    try {
                        String descriptionAndBy = userInput.split(" ", 2)[1];
                        try {
                            String Description = descriptionAndBy.split("/")[0];
                            String By = descriptionAndBy.split("/")[1];
                            tasks[Task.numOfTask] = new Deadline(Description, By);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("OOPS!!! The by of a deadline cannot be empty.");
                            System.out.println(line);
                            userInput = scanner.nextLine();
                            userInput = userInput.toLowerCase();
                            InputBYE = userInput.contains("bye");
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description and by of a deadline cannot be empty.");
                        System.out.println(line);
                        userInput = scanner.nextLine();
                        userInput = userInput.toLowerCase();
                        InputBYE = userInput.contains("bye");
                        continue;
                    }
                }
                else if (InputTODO) {
                    try {
                        String Description = userInput.split(" ", 2)[1];
                        tasks[Task.numOfTask] = new ToDo(Description);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(line);
                        userInput = scanner.nextLine();
                        userInput = userInput.toLowerCase();
                        InputBYE = userInput.contains("bye");
                        continue;
                    }
                }
                else if (InputEVENT) {
                    String descriptionAndRange = userInput.split(" ", 2)[1];
                    String Description = descriptionAndRange.split("/",3)[0];
                    String from = descriptionAndRange.split("/",3)[1];
                    String to = descriptionAndRange.split("/", 3)[2];
                    tasks[Task.numOfTask] = new Event(Description, from, to);
                }
                else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(line);
                    userInput = scanner.nextLine();
                    userInput = userInput.toLowerCase();
                    InputBYE = userInput.contains("bye");
                    continue;

                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[Task.numOfTask - 1]);
                System.out.println("Now you have " + Task.numOfTask + " tasks in the list.");
            }

            System.out.println(line);
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            InputBYE = userInput.contains("bye");
        }
        System.out.println("Bye. Hope to see you again soon!\n"
                + "\n" + line);

        }
}
