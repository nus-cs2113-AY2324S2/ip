import java.util.Scanner;
import java.util.ArrayList;
public class Lotes {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final String lineSeparator = System.lineSeparator();
    private static final String horizontalLine = "    _________________________"
                                                + "___________________________";

    private static void printTasksList() {
        System.out.println(horizontalLine);

        if (taskList.isEmpty()) {
            System.out.println("     List is empty, please enter some text to add to list.");
        } else {
            System.out.println("     Here are the tasks in your list:");

            int index = 0;
            for (Task task : taskList) {
                index++;
                System.out.print("     " + index + ".[" + task.getStatusIcon()
                                + "] " + task.getDescription() + lineSeparator);
            }
        }
        System.out.println(horizontalLine);
    }

    private static void markTask(String userInput) {
        try {
            int number = Integer.parseInt(userInput.substring(5))
                    - 1;

            taskList.get(number).setDone(true);

            System.out.print(horizontalLine + lineSeparator
                    + "     Nice! I've marked this task as done:"
                    + lineSeparator + "     [" + taskList.get(number).getStatusIcon()
                    + "] " + taskList.get(number).getDescription()
                    + lineSeparator + horizontalLine + lineSeparator);

        } catch (IndexOutOfBoundsException | NumberFormatException s) {
            System.out.println("     Invalid integer input");
            printTasksList();
        }
    }

    private static void unMarkTask(String userInput) {
        try {
            int taskListIndex = (Integer.parseInt(userInput.substring(7))
                    - 1);

            taskList.get(taskListIndex).setDone(false);

            System.out.print(horizontalLine + lineSeparator
                    + "     OK, I've marked this task as not done yet:" + lineSeparator
                    + "     [" + taskList.get(taskListIndex).getStatusIcon() + "] "
                    + taskList.get(taskListIndex).getDescription() + lineSeparator
                    + horizontalLine + lineSeparator);

        } catch (IndexOutOfBoundsException | NumberFormatException s) {
            System.out.println("     Invalid integer input");
            printTasksList();
        }
    }

    private static void addTask(String description){
        Task newTask = new Task(description);
        taskList.add(newTask);

        System.out.print(horizontalLine + lineSeparator
                + "     added: " + description + lineSeparator
                + horizontalLine + lineSeparator);
    }

    public static void main(String[] args) {
        String logo = "  #        ####  ##### ######  ####\n"
                + "                #       #    #   #   #      #\n"
                + "                #       #    #   #   #####   ####\n"
                + "                #       #    #   #   #           #\n"
                + "                #######  ####    #   ######  ####";

        System.out.println(horizontalLine + lineSeparator
                + "    Hello! I'm" + logo + lineSeparator
                + "    What can I do for you?" + lineSeparator
                + horizontalLine);

        Scanner inputCommand = new Scanner(System.in); // Prompt for continuous user input.

        while (true) {
            String userInput = inputCommand.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(horizontalLine + lineSeparator
                        + "    Bye. Hope to see you again soon!\n" + horizontalLine);
                break; // Exit the program
            } else if (userInput.equals("list")) {
                printTasksList();
            } else if (userInput.startsWith("mark ")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark ")) {
                unMarkTask(userInput);
            } else if (!userInput.isEmpty()) {
                addTask(userInput); // Automatically add user input to tasks
            } else {
                System.out.println("Enter something to add to your task list");
            }
        }
    }
}
