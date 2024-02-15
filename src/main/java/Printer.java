public class Printer {
    protected static final String GREETING = "    ____________________________________________________________\n" +
            "     Hello! I'm TimL\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";
    protected static final String GOODBYE = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";
    protected static final String LIST = "    ____________________________________________________________\n" +
            "     Here's your List DOOD:";
    protected static final String DEFAULT_ERROR = "    ____________________________________________________________\n" +
            "     OOF that's an invalid message :(\n" +
            "    ____________________________________________________________";

    public static void printGreeting() {
        System.out.println(GREETING);
    }

    public static void printGoodbye() {
        System.out.println(GOODBYE);
    }

    public static void printList() {
        System.out.println(LIST);
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________\n");
    }
    public static void printMarkedOpening() {
        System.out.println("    ____________________________________________________________\n" +
                "    Nice!! I've marked this task as done:");
    }
    public static void printUnMarkedOpening() {
        System.out.println("    ____________________________________________________________\n" +
                "    Awh :( I've unmarked this task");
    }
    public static void printDefaultError() {
        System.out.println(DEFAULT_ERROR);
    }
    public static void printAddedTaskOpening() {
        System.out.println("    ____________________________________________________________\n" +
                "    Roger that! i've added this task:");
    }
}

