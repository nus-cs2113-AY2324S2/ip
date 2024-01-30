public class Beefy {
    /**
     * Prints a separation row of n "-", where n = WIDTH
     */
    public static void printSeparation() {
        int WIDTH = 59;
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }

    public static void main(String[] args) {
        String BOTNAME = "BEEFY";
        printSeparation();
        System.out.println("Hello! I'm " + BOTNAME);
        System.out.println("What can I do for you?");
        printSeparation();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparation();
    }
}