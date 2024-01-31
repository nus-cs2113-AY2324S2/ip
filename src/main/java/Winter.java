import java.util.Scanner;

public class Winter {
    public static void main(String[] args) {
        String logo = "  __      __.__        __                \n" +
                "/  \\    /  \\__| _____/  |_  ___________ \n" +
                "\\   \\/\\/   /  |/    \\   __\\/ __ \\_  __ \\\n" +
                " \\        /|  |   |  \\  | \\  ___/|  | \\/\n" +
                "  \\__/\\  / |__|___|  /__|  \\___  >__|   \n" +
                "       \\/          \\/          \\/    ";

        System.out.println("Hello from\n" + logo);
        sayHi();
        //echo();
        addTasks();
        sayBye();

    }

    /* Function for greeting and farewell messages*/
    private static void sayHi() {
        String line = "-----------------------------------\n";
        String greet = "Hello! I'm Winter!\nWhat can I do for you?";
        System.out.print(line);
        System.out.println(greet);
        System.out.print(line);
    }
    private static void sayBye() {
        String line = "-----------------------------------\n";
        String farewell = "Bye. Hope to see you again soon!";
        System.out.print(line);
        System.out.println(farewell);
        System.out.print(line);
    }

    private static void echo() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        String echoLine;
        Scanner input = new Scanner(System.in);
        echoLine = input.nextLine();
        while (true) {
            if(echoLine.equals("bye") || echoLine.equals("Bye") || echoLine.equals("BYE")) {
                break;
            }
            System.out.print(line);
            System.out.print(indent);
            System.out.println(echoLine);
            System.out.print(line);
            echoLine = input.nextLine();
        }

    }
    private static void addTasks() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        String[] taskList = new String[100];
        int taskIndex = 0;
        Scanner input = new Scanner(System.in);

        while (true) {
            String command = input.nextLine();
            if(command.equals("bye") || command.equals("Bye") || command.equals("BYE")) {
                break;
            }
            else if (command.equals("list") || command.equals("List") || command.equals("LIST")) {
                for (int i = 0; i < taskIndex; i++) {
                    System.out.print(indent);
                    System.out.println(taskList[i]);
                }
                System.out.print(line);
                continue;
            }
            taskList[taskIndex] = Integer.toString(taskIndex+1) + ". " + command;
            taskIndex++;
            System.out.print(line);
            System.out.print(indent);
            System.out.println("added: " + command);
            System.out.print(line);

        }
    }
}
