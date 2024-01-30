public class Gene {
    private static String botName = "Gene";

    private void printLineSeparation() {
        for (int i = 0; i < 29; i++) {
            System.out.print("__");
        }
        System.out.println("__");
    }

    public void startChat() {
        printLineSeparation();
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        printLineSeparation();
    }

    public void endChat() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparation();
    }
}
