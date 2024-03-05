package Kowalski.UI;

public class Ui {
    private static final String DIVIDING_LINE = "____________________________________________________________";

    /**
     * Prints out the message introducing the functionalities of Kowalski Bot
     */
    public static void printIntro(){
        System.out.println(DIVIDING_LINE);
        System.out.println("Welcome Skipper! I'm Kowalski, reporting for Duty!"
                + System.lineSeparator() );
        printHelpCommands();
        System.out.println(System.lineSeparator()
                +"What can I do for you today?" );

        System.out.println(DIVIDING_LINE);
    }

    public static void printHelpCommands(){
        System.out.println("Enter commands, and I will echo them back to you, as well as add them to your list.");
        System.out.println("Type 'list' to see your to-do list.");
        System.out.println("Type 'mark' to mark a task as done.");
        System.out.println("Type 'unmark' to mark a task as not done.");
        System.out.println("Type 'todo <description>' to add a task to the list.");
        System.out.println("Type 'deadline <description> /by <deadline>' to add a task with a deadline to the list.");
        System.out.println("Type 'event <description> /from <start> /to <end>' to add an event to the list.");
        System.out.println("Type 'delete <index>' to delete a task from your list.");
        System.out.println("Type 'bye' to end the conversation.");
    }

    public static void printDivider(){
        System.out.println(DIVIDING_LINE);
    }

    /**
     * Prints out an accurate message for the number of tasks in the list.
     * @param numberOfTasks : represents the total current task count
     */
    public static void printCurrentTaskMessage(int numberOfTasks){
        switch (numberOfTasks){
        case 0:
            System.out.println("Now you have 0 tasks in the list.");
            break;
        case 1:
            System.out.println("Now you have 1 task in the list.");
            break;
        default:
            System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        }
    }
}
