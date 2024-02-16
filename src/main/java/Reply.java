import java.util.Scanner;
public class Reply {
    public static final String PARTITION_LINE = "____________________________________________________________";
    public static final String INVALID_COMMAND = "Invalid command. Enter 'help' to view available commands.";
    public static final String UNSPECIFIED_PARAMETER = "Parameter is unspecified.";
    public static final String INVALID_PARAMETER = "Parameter is invalid and out of bounds";
    public static final String EMPTY_LIST = "List is empty.";

    public static void printLine() {
        System.out.println(PARTITION_LINE);
    }
    public static void printHelp() {
        printLine();
        System.out.println("Commands List:" + "\n");
        System.out.println("list - prints out the List");
        System.out.println("man - procures utility commands' usage");
        System.out.println("help - procures command list");
        System.out.println("bye - terminates the bot");
        printLine();
        System.out.println("todo - adds an item to the List");
        System.out.println("event - adds an event to the List");
        System.out.println("deadline - adds a deadline to the List");
        System.out.println("mark - indicates an item on the List as done");
        System.out.println("unmark - indicates an item on the List as not done");
        printLine();
        System.out.println("todo format: todo *parameter*");
        System.out.println("event format: event *parameter* /from *start time* /to *end time*");
        System.out.println("deadline format: deadline *parameter* /by *end time*");
    }


    public static void printReply(String reply) {
        printLine();
        System.out.println(reply);
        printLine();
    }

    public static void printReply(String firstString, String secondString) {
        printLine();
        System.out.println(firstString);
        System.out.println(secondString);
        printLine();
    }


    public static void printReply(Task task, int total){
        printLine();
        System.out.println("Got it. I've added: ");
        System.out.println(task);
        if (total == 1) {
            System.out.println("You now have " + total + " task in the list.");
        } else {
            System.out.println("You now have " + total + " tasks in the list.");
        }
        printLine();
    }

    public static void printArt() {
        System.out.println(" _____                                  ");
        System.out.println("(___  )                                 ");
        System.out.println("    | |   _ _  _ __  _   _    _ _   ___ ");
        System.out.println(" _  | | /'_` )( '__)( ) ( ) /'_` )/',__)");
        System.out.println("( )_| |( (_| || |   | \\_/ |( (_| |\\__, \\");
        System.out.println("`\\___/'`\\__,_)(_)   `\\___/'`\\__,_)(____/");
    }
    public static void printWelcomeMessage(){
        printArt();
        printReply("Hello! I'm Jarvas", "What can I do for you?");
    }
    public static void printGoodbyeMessage(){
        printReply("Have a good day!", "Bye, see you soon!");
    }

    public static void printException(CustomException e) {
        System.err.println("Custom Exception Caught!" + "\n" + e.getMessage());
    }

    public static void printInvalidCommand() {
        System.err.println("Custom Exception Caught!" + "\n" + Reply.INVALID_COMMAND);
    }
}