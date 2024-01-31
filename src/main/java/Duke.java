public class Duke {
    public static String chatbotName = "Noriaki";
    public static int lineUnderscore = 30;
    public static void printLine(){
        for(int i = 0; i < lineUnderscore; i++){
            System.out.print("_");
        }
        System.out.println();
    }
    public static void greet(){
        printLine();
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
        printLine();
        System.out.println("Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        String logo =
                " _______               .__        __   .__ \n" +
                " \\      \\   ___________|__|____  |  | _|__|\n" +
                " /   |   \\ /  _ \\_  __ \\  \\__  \\ |  |/ /  |\n" +
                "/    |    (  <_> )  | \\/  |/ __ \\|    <|  |\n" +
                "\\____|__  /\\____/|__|  |__(____  /__|_ \\__|\n" +
                "        \\/                     \\/     \\/   \n";
        System.out.println("Hello from\n" + logo);
        greet();
    }
}
