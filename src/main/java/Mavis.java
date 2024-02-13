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

    private final static String LINE ="____________________________________________________________";

    public static void main(String[] args) {
        Task[] listOfTasks = new Task[100];
        greetUser();

        int listOfTasksSize = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            String inputToEcho = in.nextLine();
            String[] splitInput = inputToEcho.split(" ");
            try {

                if (inputToEcho.equals("bye")) {
                    break;
                } else if (inputToEcho.equals("list")) {
                    printTasks(listOfTasks, listOfTasksSize);
                } else if (inputToEcho.startsWith("mark")) {
                    if (splitInput.length < 2 || splitInput[1].isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    }
                    markTask(splitInput, listOfTasks);
                } else if (inputToEcho.startsWith("unmark")) {
                    if (splitInput.length < 2 || splitInput[1].isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    unmarkTask(splitInput, listOfTasks);
                } else if (inputToEcho.startsWith("todo") || inputToEcho.startsWith("deadline") || inputToEcho.startsWith("event")) {
                    if (splitInput.length < 2 || splitInput[1].isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    } else if (listOfTasksSize >= 100) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    addTask(inputToEcho, listOfTasks, splitInput, listOfTasksSize);
                    listOfTasksSize++;
                }
                else
                {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                MavisException.handleException(e, inputToEcho);
            }
        }

        bidFarewell();
    }

    private static void greetUser() {
        System.out.println("Greetings.\n" + LOGO);
        System.out.println(LINE);
        System.out.println("Greetings. I am Mavis.");
        System.out.println("What would you command of me?\n");
        listOptions();
    }

    static void listOptions() {
        System.out.println("┌───────────────────────────────────────────────┐");
        System.out.println("│ Please enter a recognized command from the    │");
        System.out.println("│ following list to maintain temporal coherence:│");
        System.out.println("│                                               │");
        System.out.println("│  1. todo <<task name>                         │");
        System.out.println("│  2. deadline <task name> /by:<date/time>      │");
        System.out.println("│  3. event <task name> /from:<date> /to:<date> │");
        System.out.println("│  4. mark <task number>                        │");
        System.out.println("│  5. unmark <task number>                      │");
        System.out.println("│  6. list                                      │");
        System.out.println("│  7. bye                                       │");
        System.out.println("└───────────────────────────────────────────────┘");
    }

    private static void printTasks(Task[] listOfTasks, int size) {
        System.out.println(LINE);
        System.out.println("Herein lies the catalog of your labors: ");
        for (int i = 0; i < size; i++) {
            listTask(i, listOfTasks[i]);
        }
        System.out.println(LINE);
    }

    private static void listTask(int currentTaskIndex, Task currentTask) {
        System.out.println("[" + currentTask.taskType + "]" + "[" + currentTask.getStatusIcon() + "] " + (currentTaskIndex + 1) + ". " + currentTask.description);
    }

    private static void markTask(String[] splitInput, Task[] listOfTasks) {
        int taskIndex = Integer.parseInt(splitInput[1]) - 1;
        listOfTasks[taskIndex].markAsCompleted();
        System.out.println("Your command has been executed."
                + "Behold the task, now marked as completed:");
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
        System.out.println(LINE);
        System.out.println("A new task is etched upon the sands of time:");
        System.out.println("[" + newTask.taskType + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Your roster now bears " + currentNumberOfTasks + " endeavors.");
        System.out.println(LINE);
    }
    
    private static void bidFarewell() {
        System.out.println(LINE);
        System.out.println("Time's tide ebbs, and so must I. Farewell, traveler.");
        System.out.println(LINE);
    }
}
