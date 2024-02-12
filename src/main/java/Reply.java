public class Reply {
    public static final String PARTITION_LINE = "____________________________________________________________";

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
    }
    public static void printManual(String command) {
        printLine();
        try {
            switch (command) {
            case "": {
                System.out.println("man format: man *command*");
                break;
            }
            case "todo": {
                System.out.println("todo format: todo *parameter*");
                break;
            }
            case "event": {
                System.out.println("event format: event *parameter* /from *start time* /to *end time*");
                break;
            }
            case "deadline": {
                System.out.println("deadline format: deadline *parameter* /by *end time*");
                break;
            }
            default:
                throw new CustomException("Invalid command. Enter 'help' to view available commands.");
            }
        } catch (CustomException e) {
            System.err.println("Custom Exception Caught!" + "\n" + e.getMessage());
        }
    }


    public static void printReply(String reply) {
        printLine();
        System.out.println(reply);
        printLine();
    }

    public static void printReply(String s1, String s2) {
        printLine();
        System.out.println(s1);
        System.out.println(s2);
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
}
