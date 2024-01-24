public class BobBot {

    public static void drawLine() {
        System.out.println("__________________________________________");
    }

    public static void greet() {
        drawLine();
        System.out.println("Hello! I'm BobBot");
        System.out.println("What can I do for you?");

        drawLine();
        System.out.println("Bye. Hope to see you again soon!");

        drawLine();
    }
    public static void main(String[] args) {
        greet();
    }
}
