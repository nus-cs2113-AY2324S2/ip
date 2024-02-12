public class Reply {
    public static final String PARTITION_LINE = "____________________________________________________________";

    public static void printLine() {
        System.out.println(PARTITION_LINE);
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
