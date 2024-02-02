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
        String[] listOfTasks = new String[100];

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

                for (int i = 0; (listOfTasks[i] != null); i++) {

                    listTask(i, listOfTasks[i]);
                }

            }
            else {

                showNewlyAddedTask(inputToEcho);

                listOfTasks[listOfTasksSize] = inputToEcho;
                listOfTasksSize += 1;
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }

    public static void listTask(int currentTaskIndex, String currentTask) {
        System.out.println("____________________________________________________________");
        System.out. println((currentTaskIndex + 1) + ". " + currentTask);
        System.out.println("____________________________________________________________");
    }

    public static void showNewlyAddedTask(String input) {
        System.out.println("____________________________________________________________");
        System.out.println("Added Task: " + input);
        System.out.println("____________________________________________________________");
    }
}



