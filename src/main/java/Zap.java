public class Zap {
    public static void main(String[] args) {
        String logo = "  _____   _   _   ____\n"
                + " |__  /  / \\ / | |  _ \\\n"
                + "   / /  / _ \\| | | |_) |\n"
                + "  / /_ / ___ \\ | |  __/\n"
                + " /____/_/   \\_\\_| |_|";
        System.out.println("Hello from\n" + logo);
        greeting();
        exit();
    }
    //User Greeting
    private static void greeting() {
        String chatbotName = "ZAP";
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

    }
    //Exit Chatbot
    private static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}