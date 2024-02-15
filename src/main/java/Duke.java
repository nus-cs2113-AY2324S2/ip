import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");

        input = in.nextLine();

        while (!input.equals("bye")) {
            String[] commandCheck = input.split(" ");
            if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++){
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
            }
            else if(commandCheck[0].equals("mark")) {
                int listIndex = Integer.parseInt(commandCheck[1]);
                System.out.println("Congratulations. I have marked this task as finished:");
                System.out.println("[X] " + tasks[listIndex - 1].getDescription());
                tasks[listIndex - 1].isDone = true;
            }

            else if(commandCheck[0].equals("unmark")) {
                int listIndex = Integer.parseInt(commandCheck[1]);
                System.out.println("Do not neglect your duties. I have marked this task as unfinished:");
                System.out.println("[ ] " + tasks[listIndex - 1].getDescription());
                tasks[listIndex - 1].isDone = false;
            }

            else {
                System.out.println("added: " + input);
                tasks[taskCount] = new Task(input);
                taskCount++;
            }
            input = in.nextLine();
        }
        System.out.println("Farewell, and may the fortunes be ever in your favour.");
    }
}