public class Duke {

    public static void drawLine() {
        System.out.println("__________________________________________");
    }

    public static void greet(String botName) {
        drawLine();
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        drawLine();
        System.out.println("Bye. Hope to see you again soon!");

        drawLine();
    }
    public static void main(String[] args) {
        String botName = "BobBot";
        greet(botName);
    }
}
