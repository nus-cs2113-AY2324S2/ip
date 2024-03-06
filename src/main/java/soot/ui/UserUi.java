package soot.ui;

/**
 * Class UserUi handles interactions with the user.
 * This includes display of user interfaces, such as displaying the divider line.
 */
public class UserUi {
    public static final String INDENT = "  ";

    public UserUi() {
        
    }

    /**
     * Prints a greeting to the user when the chatbot is first run.
     * Chatbot will introduce itself and question for a user input.
     */
    public static void showUserGreeting() {
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        displayDividerLine();
    }

    /**
     * Prints a goodbye greeting to the user when the user stops the chatbot.
     */
    public static void showGoodbyeMessage() {
        System.out.println("Bye! Till the next time we meet... \n " +
                "i've saved your list for future use <3");
        displayDividerLine();
    }

    /**
     * Prints a horizontal line that acts as a divider within the conversation between the
     * user input and the chatbot.
     * Divider Line will be displayed after the user input before the chatbot speaks.
     */
    public static void displayDividerLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    /**
     * Prints a 2 space indent for a pleasant UI experience.
     */
    public static void displayIndent() {
        System.out.print(INDENT);
    }

    //TODO: javadoc
    public static void printMessageWithDivider(String messageToPrint) {
        System.out.println(messageToPrint);
        UserUi.displayDividerLine();
    }
}
