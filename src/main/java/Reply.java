public class Reply {
    public static final String PARTITION_LINE = "____________________________________________________________";

    public static void printReply(String reply) {
        System.out.println(PARTITION_LINE);
        System.out.println(reply);
        System.out.println(PARTITION_LINE);
    }

    // Method to print two strings
    public static void printReply(String s1, String s2) {
        System.out.println(PARTITION_LINE);
        System.out.println(s1);
        System.out.println(s1);
        System.out.println(PARTITION_LINE);
    }
}
