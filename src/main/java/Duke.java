import java.util.Scanner;
//so scanner class can be used

public class Duke {
    public static void printLine() {
        System.out.println("________________________________________");
    }
    public static void printShortLine() {
        System.out.println("_____________");
    }

    public static void printHello() {
        String logo =
                "__    __   ___    ____ \n"
                        + "\\ \\ / /  / _ \\  | ___ |\n"
                        + " \\ Y /  | | | |     | | \n"
                        + "  \\ /   | | | |     | | \n"
                        + "  | |   | |_| |  ___| | \n"
                        + "  |_|    \\___/  |____/          \n";
        printLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm YOJ");
        System.out.println("What can I do for you?");
        printLine();
    }
    public static void main(String[] args) {
        printHello();
        // get user input
        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = in.nextLine();

        while(!userInput.equals("bye")) {
            printShortLine();
            System.out.println(userInput);
            printLine();
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
