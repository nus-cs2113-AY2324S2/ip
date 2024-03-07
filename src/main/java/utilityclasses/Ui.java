package utilityclasses;
import drosstasks.Task;
import java.util.ArrayList;

public class Ui {

    //Method to print a line of _ characters
    public static void printLine() {
        System.out.println("_".repeat(55));
    }

    //Method to exit Dross bot
    public static void printGoodbyeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    //Opening message for Dross
    public static void printWelcomeMessage(){
        printLine();
        System.out.println("Hello! I'm Dross");
        System.out.println("What can I do for you?");
        printLine();
    }

    //Empty search result message
    public static void printEmptySearchResult(){
        printLine();
        System.out.println("yeah that stuff, it not in the list...");
        printLine();
    }

    //Search result display message
    public static void printSearchResults(ArrayList<Task> matches){
        printLine();
        System.out.println("These stuff have the same description of what you searched for or whatever");
        for (int i = 0; i < matches.size(); i++) {
            Task currentTask = matches.get(i);
            System.out.print((i + 1) + ".");
            System.out.println(currentTask);
        }
        printLine();
    }

}
