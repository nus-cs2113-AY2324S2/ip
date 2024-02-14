import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Duck";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" + "Hello! I'm " + name
                + "\n" + "What can I do for you?\n" + "\n"
                + "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
//      //  List<String> userInputSaver = new ArrayList<>();
//      //  List<String> statusOfInput = new ArrayList<>();
        List<Task> taskList = new ArrayList<Task>();
        while (!userInput.contains("bye")) {

            System.out.println("____________________________________________________________\n");
            int indexOfMark;
            if (userInput.contains("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i+1) + "." + taskList.get(i));
                }

            } else if (userInput.contains("mark")) {
                if (userInput.contains("unmark")) {

                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    taskList.get(indexOfMark).markAsUndone();

                } else {
                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    taskList.get(indexOfMark).markAsDone();
                }
            } else {
                taskList.add(new Task(userInput));
                System.out.println("added: " + userInput );
            }

            System.out.println("____________________________________________________________\n");
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
        }
        System.out.println("Bye. Hope to see you again soon!\n"
                + "\n" + "____________________________________________________________");

        }
}
