package GermaBot;

public class UiOutputs {
    static final String LINE= "____________________________________________";
    static final String WELCOME_MESSAGE = "Hello! GermaBot here! \n"
            + "Let me load your saved To Do List first...";
    static final String GOODBYE_MESSAGE = "Thanks for using me! Hope to see you again soon~!";

    public static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println(WELCOME_MESSAGE);
    }
}
