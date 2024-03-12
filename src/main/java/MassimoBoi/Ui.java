package MassimoBoi;

/**
 * Creates the user interface for the command line.
 */
public class Ui {

    private String logo;

    /**
     * Creates a new user interface and stores the logo.
     */
    Ui(){
        this.logo = " __  __               _                   _           _ \n" +
                "|  \\/  | __ _ ___ ___(_)_ __ ___   ___   | |__   ___ (_)\n" +
                "| |\\/| |/ _` / __/ __| | '_ ` _ \\ / _ \\  | '_ \\ / _ \\| |\n" +
                "| |  | | (_| \\__ \\__ \\ | | | | | | (_) | | |_) | (_) | |\n" +
                "|_|  |_|\\__,_|___/___/_|_| |_| |_|\\___/  |_.__/ \\___/|_|";
    }

    /**
     * Prints greeting message along with logo.
     */
    public void printGreetingMessage(){
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints instructions on how to use the program.
     */
    public void printUserGuideMessage(){

        System.out.println("To add task, enter the task type (todo,deadline,event) followed by description with following rules:");
        System.out.println("\t1. For deadline add {/by} at end of description followed by due date");
        System.out.println("\t2. For events add {/from} followed by start date and {/to} followed by end date");
        System.out.println("To check list of tasks, type list.");
        System.out.println("To mark a task as done, type mark {list index}.");
        System.out.println("To unmark a task, type unmark {list index}.");
        System.out.println("To delete a task, type delete {list index}.");
        System.out.println("To find a task, type find {key words}.");
        System.out.println("To end chat, type bye.");
    }


    public void printHorizontalRow() {
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints a goodbye message.
     * Called after user has entered {bye} command.
     */
    public void printGoodbyeMessage() {
        System.out.println("This is Massimo boi signing out!");
    }
}
