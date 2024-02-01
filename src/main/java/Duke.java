import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int itemsCount = 0;
        System.out.println("Hello! My name is Jeff.");
        System.out.println("What can I do for you?");
        boolean isChatting = true;
        while (isChatting){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String firstWord = line.split(" ")[0];
            int taskIndex;
            switch (firstWord){
            case "list":
                for (int i = 0; i < itemsCount; i += 1){
                    System.out.println((i +1) + ".[" + tasks[i].getStatusIcon() + "]" + tasks[i].getDescription());
                }
                break;

            case "bye":
                isChatting = false;
                break;

            case "mark":
                System.out.println("Nice! I've marked this task as done:");
                taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[taskIndex].markTask();
                System.out.println("[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                break;

            case "unmark":
                System.out.println("OK, I've marked this task as not done yet: ");
                taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[taskIndex].unmarkTask();
                System.out.println("[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                break;

            default:
                tasks[itemsCount] = new Task(line);
                System.out.println("added: " + line);
                itemsCount += 1;

            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
