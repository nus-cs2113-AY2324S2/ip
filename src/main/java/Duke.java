import java.util.Scanner;
public class Duke {
    private String name;
    private String[] tasks;
    private int taskCount;

    public Duke(String name) {
        this.name = name;
        this.tasks = new String[100];
        this.taskCount = 0;
    }

    public void greet() {
        System.out.println("=========================");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
//        System.out.println("=========================");
    }

    public void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("added: " + task);
    }

    public void listTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }

    public void echoCommands() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            command = scanner.nextLine();
            // Level 1
//            System.out.println("=========================");
//            System.out.println(command);
//            System.out.println("=========================");

            // Level 2
            if (command.equals("list")) {
                listTasks();
            } else if (!command.equals("bye")) {
                addTask(command);
            }
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
