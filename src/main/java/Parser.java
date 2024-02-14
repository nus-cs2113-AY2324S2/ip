public class Parser {
    public static void printMessageBorder() {
        System.out.println("\t--------------------------------------------------");
    }

    public static void printMessageWithBorder(String string) {
        printMessageBorder();
        System.out.println(string);
        printMessageBorder();
    }

    public static void printWelcomeMessage() {
        String welcome = "\tHello! I'm Misty\n"
                + "\tWhat can I do for you?";

        printMessageWithBorder(welcome);
    }

    public static void printByeMessage() {
        printMessageWithBorder("\tBye! Hope to see you again soon!");
    }

    public static void printUnknownCommandMessage() {
        String bye = "Sorry, unknown command entered!";
        System.out.println("\t" + bye);
    }

    public static void printTaskCount(int taskCount) {
        System.out.println(String.format("\tYou now have %d tasks in the list.", taskCount));
    }

    public static void printAddTaskMessage(Task newTask) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t\t%s", newTask));
    }

    public static void printTaskMarkAsDone(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println(String.format("\t%s",task));
    }

    public static void printTaskUnmarkAsNotDone(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println(String.format("\t%s",task));
    }

    public static void printList(Task[] list, int itemCount) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0 ; i < itemCount; i++) {
            System.out.println(String.format("\t%d.%s", i + 1, list[i]));
        }
    }
}
