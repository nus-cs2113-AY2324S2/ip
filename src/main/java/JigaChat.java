import java.util.Scanner;
import java.util.Arrays;
public class JigaChat {
    Task[] taskList = new Task[100];
    int taskCounter = 0;

    public void addtoList() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            printList();
            addtoList();
        }
        else {
            taskList[taskCounter] = new Task(command);
            taskCounter++;
            System.out.println("added: " + command);
            addtoList();
        }
    }

    public void printList() {
        for (int i = 0; i < taskCounter; i ++) {
            System.out.println ((i + 1) + ". " + taskList[i].description);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm JigaChat");
        System.out.println("What can I do for you?");
        JigaChat chat = new JigaChat();
        chat.addtoList();
    }
}

