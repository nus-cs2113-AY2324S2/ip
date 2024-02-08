import java.util.Scanner;
public class Duke {
    private String name;

    public Duke(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("=========================");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
//        System.out.println("=========================");
    }
    public void echoCommands() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            command = scanner.nextLine();
            System.out.println("=========================");
            System.out.println(command);
            System.out.println("=========================");
        } while (!command.equals("bye"));

        scanner.close();
    }
    public void exit() {
//        System.out.println("=========================");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("=========================");
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Duke chatbot = new Duke("aoliba");
        chatbot.greet();
        chatbot.echoCommands();
        chatbot.exit();
    }
}
