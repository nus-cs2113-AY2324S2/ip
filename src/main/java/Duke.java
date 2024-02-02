import java.util.Scanner;

public class Duke {
    public static void printList(Task[] list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                System.out.println();
                return;
            }
            System.out.println(Integer.toString(i+1) + ". [" + list[i].getStatusIcon() + "] " + list[i].description);
        }
    }

    public static void markTask(Task[] list, String line) {
        int taskNumber = Integer.parseInt(line.substring(5));
        list[taskNumber-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + list[taskNumber-1].getStatusIcon() + "] " + list[taskNumber-1].description + "\n");
    }

    public static void unmarkTask(Task[] list, String line) {
        int taskNumber = Integer.parseInt(line.substring(7));
        list[taskNumber-1].unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + list[taskNumber-1].getStatusIcon() + "] " + list[taskNumber-1].description + "\n");
    }

    public static String readCommand(String line) {
        if (!line.contains(" ")) {
            return line;
        }
        else {
            return line.substring(0, line.indexOf(" "));
        }
    }

    public static void main(String[] args) {
        String line;
        String command;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];

        System.out.println("Hello! I'm FredBot.\nWhat can I do for you?\n");

        line = in.nextLine();
        command = readCommand(line);
        int i = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                printList(list);
            }
            else if (command.equals("mark")) {
                markTask(list, line);
            }
            else if (command.equals("unmark")) {
                unmarkTask(list, line);
            }
            else {
                list[i] = new Task(line);
                i++;
                System.out.println("added: " + line + "\n");
            }
            line = in.nextLine();
            command = readCommand(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
