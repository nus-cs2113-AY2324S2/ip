import java.util.Scanner;

public class Mavis {
    private static final String LOGO = "                           z$$$$P\n" +
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
            "                        zP\n" +
            "                       z\"\n" +
            "                      / \n" +
            "                     ^ \n" +
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

    public static void main(String[] args) {
        Task[] listOfTasks = new Task[100];
        greetUser();

        int listOfTasksSize = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            String inputToEcho = in.nextLine();
            String[] splitInput = inputToEcho.split(" ");

            if (inputToEcho.equals("bye")) {
                break;
            } else if (inputToEcho.equals("list")) {
                printTasks(listOfTasks, listOfTasksSize);
            } else if (inputToEcho.startsWith("mark")) {
                markTask(splitInput, listOfTasks);
            } else if (inputToEcho.startsWith("unmark")) {
                unmarkTask(splitInput, listOfTasks);
            } else if (inputToEcho.startsWith("todo") || inputToEcho.startsWith("deadline") || inputToEcho.startsWith("event")) {
                addTask(inputToEcho, listOfTasks, splitInput, listOfTasksSize);
                listOfTasksSize++;
            }
        }

        bidFarewell();
    }

    private static void greetUser() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Mavis!");
        System.out.println("What can I do for you?\n");
    }

    private static void printTasks(Task[] listOfTasks, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < size; i++) {
            listTask(i, listOfTasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    private static void listTask(int currentTaskIndex, Task currentTask) {
        System.out.println("[" + currentTask.taskType + "]" + "[" + currentTask.getStatusIcon() + "] " + (currentTaskIndex + 1) + ". " + currentTask.description);
    }

    private static void markTask(String[] splitInput, Task[] listOfTasks) {
        int taskIndex = Integer.parseInt(splitInput[1]) - 1;
        listOfTasks[taskIndex].markAsCompleted();
        System.out.println("As you wish, good sir."
                + "Here is the task you just marked as completed:");
        listTask(taskIndex, listOfTasks[taskIndex]);
    }

    private static void unmarkTask(String[] splitInput, Task[] listOfTasks) {
        int taskIndex = Integer.parseInt(splitInput[1]) - 1;
        listOfTasks[taskIndex].markAsNotCompleted();
        System.out.println("Reversing the flow of space and time to undo the task...");
        System.out.println("Here is the task you just marked as not completed:");
        listTask(taskIndex, listOfTasks[taskIndex]);
    }

    private static void addTask(String inputToEcho, Task[] listOfTasks, String[] splitInput, int listOfTasksSize) {
        Task newTask;
        if (inputToEcho.startsWith("todo")) {
            newTask = new ToDo(inputToEcho);
        } else if (inputToEcho.startsWith("deadline")) {
            newTask = new Deadline(inputToEcho);
        } else { // event
            newTask = new Event(inputToEcho);
        }

        listOfTasks[listOfTasksSize] = newTask;
        showNewlyAddedTask(newTask, listOfTasksSize + 1);
    }

    private static void showNewlyAddedTask(Task newTask, int currentNumberOfTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Alright, you've added this new task: ");
        System.out.println("[" + newTask.taskType + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Now you have " + currentNumberOfTasks + " task(s) in your list!");
        System.out.println("____________________________________________________________");
    }

    private static void bidFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
