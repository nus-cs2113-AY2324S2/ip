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

        System.out.println("""
                To add task, type the task type (todo,deadline,event) followed by description with following rules:
                    1. For deadline add {/by} at end of description followed by due date
                    2. For events add {/from} followed by start date and {/to} followed by end date
                To check list of tasks, type list.
                To mark a task as done, type mark {list index}.
                To unmark a task, type unmark {list index}.
                To delete a task, type delete {list index}.
                """);
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
