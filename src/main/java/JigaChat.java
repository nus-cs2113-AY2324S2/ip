import java.util.Scanner;
public class JigaChat {
    Task[] taskList = new Task[100];
    int taskCounter = 0;

    public void readCommand() {
        String input;
        String[] commands = new String[2];
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        commands = input.split(" ", 2);

        switch(commands[0]) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        case "list":
            printList();
            return;
        case "mark":
            System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as done!");
            taskList[Integer.parseInt(commands[1]) - 1].markAsDone();
            return;
        case "unmark":
            System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as not done!");
            taskList[Integer.parseInt(commands[1]) - 1].markAsUndone();
            return;
        }
        addToList(commands);
    }

    public void addToList(String[] taskToAdd) {
        if (taskToAdd[0].equals("todo")) {
            taskList[taskCounter] = new ToDo(taskToAdd[1]);
        }
        if (taskToAdd[0].equals("deadline")) {
            String[] deadline = taskToAdd[1].split("/", 2);
            String by = deadline[1].substring(3);
            String description = deadline[0].substring(0, deadline[0].length() - 1);
            taskList[taskCounter] = new Deadline(description, by);
        }
        if (taskToAdd[0].equals("event")) {
            String[] event = taskToAdd[1].split("/", 3);
            String description = event[0].substring(0, event[0].length() - 1);
            String from = event[1].substring(5, event[1].length() - 1);
            String to = event[2].substring(3);
            taskList[taskCounter] = new Event(description, from, to);
        }
        System.out.println("The following task has been added: ");
        taskList[taskCounter].printTask();
        taskCounter++;
        System.out.print("You have " + taskCounter + " task");
        if (taskCounter != 1) {
            System.out.print("s");
        }
        System.out.println(" in your list.");
    }

    public void printList() {
        if (taskCounter == 0) {
            System.out.println ("Your list is empty!");
            return;
        }
        if (taskCounter != 1) {
            System.out.print("Here are the tasks");
        }
        else {
            System.out.println("Here is the task");
        }
        System.out.print(" in your list:");

        for (int i = 0; i < taskCounter; i ++) {
            System.out.println ((i + 1) + ". " + taskList[i].getTypeIcon() + taskList[i].getStatusIcon() + " " + taskList[i].description);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm JigaChat");
        System.out.println("What can I do for you?");
        JigaChat chat = new JigaChat();
        while (1 == 1) {
            chat.readCommand();
        }
    }
}

