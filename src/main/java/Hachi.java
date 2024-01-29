import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program currently starts the chatbot with a greeting,
 * and ends off the program with a goodbye message.
 *
 * @author clarencepohh
 * @version 23/01/2024
 */
public class Hachi {
    /**
     * Prints a greeting to the user in the console
     * with the bot's name, Hachi.
     */
    public static void greet() {
        String logo = "._. ._.  ._____.  ._____.  ._. ._.  ._.\n"
                + "| | | |  | ._. |  |  ___|  | | | |  | |\n"
                + "| |_| |  | |_| |  | |      | |_| |  | |\n"
                + "| ._. |  | ._. |  | |___   |  _  |  | |\n"
                + "|_| |_|  |_| |_|  |_____|  |_| |_|  |_|\n";

        System.out.println("Hey, Hachi Here!\n" + logo + "How can I assist you today?\n");
        spacerInsert("medium", false);
    }

    /**
     * Prints to the console a spacer line made of tildes.
     *
     * @param length The desired length of the spacer line. Medium is chosen by default.
     * @param hasTab Whether the spacer line has a 4-space indent.
     */
    public static void spacerInsert(String length, boolean hasTab) {
        String spacer;
        if (hasTab) {
            System.out.print("    ");
        }
        switch (length) {
        case "small": // 20 tildes
            spacer = "~~~~~~~~~~~~~~~~~~~~";
            break;
        case "medium": // 40 tildes
        default:
            spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
            break;
        case "large": // 60 tildes
            spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
            break;
        }
        System.out.println(spacer);
    }

    public static void retrieveList(Task[] listOfTasks) {
        int numTasks = Task.getTotalNumTasks();
        spacerInsert("medium", true);
        System.out.println("    The following tasks are in your to-do:");
        for (int i = 0; i < numTasks; i++) {
            System.out.print(i + ": ");
            System.out.println(listOfTasks[i].getName());
        }
    }

    public static void addTask(String name, Task[] listOfTasks) {
        int numTasks = Task.getTotalNumTasks();
        Task toAdd = new Task(name);
        listOfTasks[numTasks] = toAdd;

        System.out.println("Task added to list: " + name);
    }

    /**
     * Prints to the console a goodbye message for the user.
     */
    public static void goodbye() {
        System.out.println("    Goodbye! Hope you have a marvelous day.");
        spacerInsert("medium", true);
    }

    /**
     * The main program that starts and ends the chatbot.
     * Prints to the console for the user to read its messages.
     *
     * @param args Command line arguments - not used.
     */

    public static void main(String[] args) {
        spacerInsert("medium", false);
        boolean isBye = false;
        Task[] listOfTasks = new Task[100];

        greet();
        while (!isBye) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            String cleanedInput = line.toUpperCase().trim();
            switch (cleanedInput){
            case "BYE":
                isBye = true;
                goodbye();
                break;
            case "LIST":
                retrieveList(listOfTasks);
                break;
            default:
                addTask(line, listOfTasks);
                break;
            }
        }
    }
}
