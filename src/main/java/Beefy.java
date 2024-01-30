public class Beefy {
    private static final String BOTNAME = "BEEFY";

    /**
     * Prints a separation row of n "-", where n = WIDTH
     */
    private void printSeparation() {
        int WIDTH = 59;
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }

    public void startChat() {
        printSeparation();
        System.out.println("Hello! I'm " + BOTNAME);
        System.out.println("What can I do for you?");
        printSeparation();
    }

    public void endChat() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparation();
    }
}
