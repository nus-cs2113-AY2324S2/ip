import java.util.Arrays;
import java.util.Scanner;

public class Mavis {
    public static void main(String[] args) {
        final String LOGO = "                           z$$$$P\n" +
                "                           d$$$$\"\n" +
                "                         .$$$$$\"\n" +
                "                         z$$$$$\"\n" +
                "                        z$$$$P\n" +
                "                      d$$$$$$$$$$\"\n" +
                "                     *******$$$\"\n" +
                "                          .$$$\"\n" +
                "                          .$$\"\n" +
                "                         4$P\"\n" +
                "                        z$\"\n" +
                "                          zP\n" +
                "                         z\"\n" +
                "                         / \n" +
                "                       ^ \n" +
                "      ___           ___                                    ___     \n" +
                "     /__/\\         /  /\\          ___        ___          /  /\\    \n" +
                "    |  |::\\       /  /::\\        /__/\\      /  /\\        /  /:/_   \n" +
                "    |  |:|:\\     /  /:/\\:\\       \\  \\:\\    /  /:/       /  /:/ /\\  \n" +
                "  __|__|:|\\:\\   /  /:/~/::\\       \\  \\:\\  /__/::\\      /  /:/ /::\\ \n" +
                " /__/::::| \\:\\ /__/:/ /:/\\:\\  ___  \\__\\:\\ \\__\\/\\:\\__  /__/:/ /:/\\:\\\n" +
                " \\  \\:\\~~\\__\\/ \\  \\:\\/:/__\\/ /__/\\ |  |:|    \\  \\:\\/\\ \\  \\:\\/:/~/:/\n" +
                "  \\  \\:\\        \\  \\::/      \\  \\:\\|  |:|     \\__\\::/  \\  \\::/ /:/ \n" +
                "   \\  \\:\\        \\  \\:\\       \\  \\:\\__|:|     /__/:/    \\__\\/ /:/  \n" +
                "    \\  \\:\\        \\  \\:\\       \\__\\::::/      \\__\\/       /__/:/   \n" +
                "     \\__\\/         \\__\\/           ~~~~                   \\__\\/    ";

        Task[] listOfTasks = new Task[100];

        System.out.println("Hello from\n" + LOGO);
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
            String[] splitInput = inputToEcho.split(" ");

            if (inputToEcho.equals("bye")) {
                break;
            } else if (inputToEcho.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list: ");

                for (int i = 0; (listOfTasks[i] != null); i++) {

                    listTask(i, listOfTasks[i]);
                }

                System.out.println("____________________________________________________________");

            } else if (inputToEcho.startsWith("mark")) {

                //Extract the index of the task the user wishes to mark

                int taskIndex = Integer.parseInt(splitInput[1]) - 1;

                listOfTasks[taskIndex].markAsCompleted();

                System.out.println("As you wish, good sir. Here is the task you just marked as completed:");
                listTask(taskIndex, listOfTasks[taskIndex]);
            } else if (inputToEcho.startsWith("unmark")) {

                //Extract the index of the task the user wishes to mark

                int taskIndex = Integer.parseInt(splitInput[1]) - 1;

                listOfTasks[taskIndex].markAsNotCompleted();

                System.out.println("Reversing the flow of space and time to undo the task...");
                System.out.println("Here is the task you just marked as not completed:");
                listTask(taskIndex, listOfTasks[taskIndex]);
            } else if (inputToEcho.startsWith("todo")){


                listOfTasks[listOfTasksSize] = new ToDo(inputToEcho);
                listOfTasksSize += 1;

                showNewlyAddedTask(listOfTasks[listOfTasksSize - 1], listOfTasksSize);
            } else if (inputToEcho.startsWith("deadline")) {

                listOfTasks[listOfTasksSize] = new Deadline(inputToEcho);
                listOfTasksSize += 1;

                showNewlyAddedTask(listOfTasks[listOfTasksSize - 1], listOfTasksSize);
            } else if (inputToEcho.startsWith("event")) {

                listOfTasks[listOfTasksSize] = new Event(inputToEcho);
                listOfTasksSize += 1;

                showNewlyAddedTask(listOfTasks[listOfTasksSize - 1], listOfTasksSize);

            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }

    public static void listTask(int currentTaskIndex, Task currentTask) {

        System.out.println("[" + currentTask.taskType + "]" + "[" + currentTask.getStatusIcon() + "] " + (currentTaskIndex + 1) + ". " + currentTask.description);
    }

    public static void showNewlyAddedTask(Task newTask, int currentNumberOfTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Alright, you've added this new task: ");
        System.out.println("[" + newTask.taskType + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Now you have " + currentNumberOfTasks + " task(s) in your list!");
        System.out.println("____________________________________________________________");
    }
}



