import Duke.*;
import Tasks.*;
import Tasks.Task;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    private static List<Task> tasks = new ArrayList<Task>();
    private static String line = "____________________________________________________________\n";
    private static void delete (int index) {
        index = index -1;
        Task.numOfTask -= 1;
        System.out.println(line + "Noted. I've removed this task:\n" + tasks.get(index) +"\n"
                + "Now you have " + Task.numOfTask + " tasks in the list.\n" + line);
        tasks.remove(index);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Duck";
        System.out.println("Hello from\n" + logo);
        System.out.println(line + "Hello! I'm " + name
                + "\n" + "What can I do for you?\n" + "\n"
                + line);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();

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
            boolean InputDELETE = userInput.contains("delete");
            if (InputLIST) {
                for (int i = 0; i < Task.numOfTask; i++) {
                    System.out.println((i+1) + "." + tasks.get(i));
                }

            } else if (InputMARK) {
                if (InputUNMARK) {

                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(indexOfMark).markAsUndone();

                } else {
                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(indexOfMark).markAsDone();
                }
            } else {
                if (InputDEADLINE) {
                    try {
                        String descriptionAndBy = userInput.split(" ", 2)[1];
                        try {
                            String Description = descriptionAndBy.split("/")[0];
                            String By = descriptionAndBy.split("/")[1];
                            tasks.add(new Deadline(Description, By));
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
                } else if (InputTODO) {
                    try {
                        String Description = userInput.split(" ", 2)[1];
                        tasks.add(new ToDo(Description));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(line);
                        userInput = scanner.nextLine();
                        userInput = userInput.toLowerCase();
                        InputBYE = userInput.contains("bye");
                        continue;
                    }
                } else if (InputEVENT) {
                    String descriptionAndRange = userInput.split(" ", 2)[1];
                    String Description = descriptionAndRange.split("/",3)[0];
                    String from = descriptionAndRange.split("/",3)[1];
                    String to = descriptionAndRange.split("/", 3)[2];
                    tasks.add(new Event(Description, from, to));
                } else if (InputDELETE) {
                    int deleteIndex = Integer.parseInt(userInput.split(" ",2)[1]);
                    delete(deleteIndex);
                    userInput = scanner.nextLine();
                    userInput = userInput.toLowerCase();
                    InputBYE = userInput.contains("bye");
                    continue;
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(line);
                    userInput = scanner.nextLine();
                    userInput = userInput.toLowerCase();
                    InputBYE = userInput.contains("bye");
                    continue;

                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(Task.numOfTask - 1));
                System.out.println("Now you have " + Task.numOfTask + " tasks in the list.");
            }

            System.out.println(line);
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            InputBYE = userInput.contains("bye");
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n"
                + "\n" + line);

        }
}
