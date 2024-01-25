public class Soot {
    public static void main(String[] args) {
        drawLine();
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        drawLine();

        System.out.println("Bye! Till the next time we meet...");
        drawLine();
    }

    public static void drawLine() {
        int length = 60;
        for (int i = 0; i < length; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
}
