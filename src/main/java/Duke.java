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
    public static void printList(String[]tasks, int taskCount) {
        for(int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + ". " + tasks[i]);
        }
        printLine();
    }
    public static void main(String[] args) {
        printHello();
        // get user input
        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = in.nextLine();

        String[] tasks = new String[100];
        int taskCount = 0;

        while(!userInput.equals("bye")) {
            printShortLine();
            System.out.println("added: " + userInput);
            printLine();
            if (userInput.equals("list")) {
                printList(tasks, taskCount);
            } else {
                tasks[taskCount] = userInput;
                taskCount++;
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
