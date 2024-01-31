import java.util.Scanner;
import java.util.Arrays;
public class JigaChat {
    Task[] taskList = new Task[100];
    int taskCounter = 0;

    public void readCommand () {
        String input;
        String[] commands = new String[2];
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        commands = input.split(" ");

        switch (commands[0]) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        case "list":
            printList();
            return;
        case "mark":
            System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1]) - 1) + " as done!");
            taskList[Integer.parseInt(commands[1]) - 1].markAsDone();
            return;
        case "unmark":
            System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1]) - 1) + " as not done!");
            taskList[Integer.parseInt(commands[1]) - 1].markAsUndone();
            return;
        }
        addToList(input);

    }

    public void addToList(String taskToAdd) {
            taskList[taskCounter] = new Task(taskToAdd);
            taskCounter++;
            System.out.println("added: " + taskToAdd);
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i ++) {
            System.out.println ((i + 1) + ". " +  "[" + taskList[i].getStatusIcon() + "]" + taskList[i].description);
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

