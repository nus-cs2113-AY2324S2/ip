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
                "__   __   ___    _____ \n"
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
            System.out.println("added: " + userInput);
            printLine();
            if (userInput.equals("list")) {
                Task.printList();
            } else if(userInput.matches("mark \\d+")) {
                String[] taskIndex = userInput.split(" ");
                int index = Integer.parseInt(taskIndex[1]);
                Task.markDone(index-1);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + Task.tasks[index-1]);
            } else if(userInput.matches("unmark \\d+")) {
                String[] taskIndex = userInput.split(" ");
                int index = Integer.parseInt(taskIndex[1]);
                Task.markUndone(index-1);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + Task.tasks[index-1]);
            } else {
                Task.addTask(userInput);
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
