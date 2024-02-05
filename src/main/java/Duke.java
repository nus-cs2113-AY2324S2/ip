import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "         z$$$$P\n" +
                "        d$$$$\"\n" +
                "      .$$$$$\"\n" +
                "     z$$$$$\"\n" +
                "    z$$$$P\n" +
                "   d$$$$$$$$$$\"\n" +
                "  *******$$$\"\n" +
                "       .$$$\"\n" +
                "      .$$\"\n" +
                "     4$P\"\n" +
                "    z$\"\n" +
                "   zP\n" +
                "  z\"\n" +
                " /    M A V I S'\n" +
                "^";
        //Store added tasks to an array
        Task[] listOfTasks = new Task[100];

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Mavis!");
        System.out.println("What can I do for you?\n");

        //String to store the input entered by the user
        String inputToEcho;

        //This variable stores the current number of tasks added
        // and also uses it to place a new task in the next available spot.
        int listOfTasksSize = 0;

        Scanner in = new Scanner(System.in);

        while(true) {
            inputToEcho = in.nextLine();

            if (inputToEcho.equals("bye")) {
                break;
            }

            else if (inputToEcho.equals("list")) {
                System.out.println("____________________________________________________________");

                for (int i = 0; (listOfTasks[i] != null); i++) {

                    listTask(i, listOfTasks[i]);
                }

                System.out.println("____________________________________________________________");

            }
            else if (Arrays.asList(inputToEcho.split(" ")).contains("mark")) {

                //Extract the index of the task the user wishes to mark
                String[] splitInput = inputToEcho.split(" ");
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;

                listOfTasks[taskIndex].markAsCompleted();

                System.out.println("As you wish, good sir. Here is the task you just marked as completed:");
                listTask(taskIndex, listOfTasks[taskIndex]);
            } else if (Arrays.asList(inputToEcho.split(" ")).contains("unmark")) {

                //Extract the index of the task the user wishes to mark
                String[] splitInput = inputToEcho.split(" ");
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;

                listOfTasks[taskIndex].markAsNotCompleted();

                System.out.println("Reversing the flow of space and time to undo the task...");
                System.out.println("Here is the task you just marked as not completed:");
                listTask(taskIndex, listOfTasks[taskIndex]);
            } else {

                showNewlyAddedTask(inputToEcho);

                listOfTasks[listOfTasksSize] = new Task(inputToEcho);
                listOfTasksSize += 1;
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }

    public static void listTask(int currentTaskIndex, Task currentTask) {

        System.out. println("[" + currentTask.getStatusIcon() + "] " + (currentTaskIndex + 1) + ". " + currentTask.description);
    }

    public static void showNewlyAddedTask(String input) {
        System.out.println("____________________________________________________________");
        System.out.println("Added Task: " + input);
        System.out.println("____________________________________________________________");
    }
}



