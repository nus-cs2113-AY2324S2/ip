public class Reply {
    public static final String PARTITION_LINE = "____________________________________________________________";

    public static void printLine() {
        System.out.println(PARTITION_LINE);
    }
    public static void printReply(String reply) {
        System.out.println(PARTITION_LINE);
        System.out.println(reply);
        System.out.println(PARTITION_LINE);
    }

    public static void printReply(String s1, String s2) {
        System.out.println(PARTITION_LINE);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(PARTITION_LINE);
    }


    public static void printReply(ToDo toDo, int total){
        System.out.println(PARTITION_LINE);
        System.out.println("Got it. I've added: ");
        System.out.println(toDo);
        System.out.println("You now have " + total + " tasks in the list.");
        System.out.println(PARTITION_LINE);
    }
    public static void printReply(Event event, int total){
        System.out.println(PARTITION_LINE);
        System.out.println("Got it. I've added: ");
        System.out.println(event);
        System.out.println("You now have " + total + " tasks in the list.");
        System.out.println(PARTITION_LINE);
    }
    public static void printReply(Deadline deadline, int total){
        System.out.println(PARTITION_LINE);
        System.out.println("Got it. I've added: ");
        System.out.println(deadline);
        System.out.println("You now have " + total + " tasks in the list.");
        System.out.println(PARTITION_LINE);
    }
}
