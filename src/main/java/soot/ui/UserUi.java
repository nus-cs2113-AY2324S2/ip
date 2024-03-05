package soot.ui;

public class UserUi {
    public static void showUserGreeting() {
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        displayDividerLine();
    }

    public static void showGoodbyeMessage() {
        System.out.println("Bye! Till the next time we meet...");
        displayDividerLine();
    }

    public static void displayDividerLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
}
