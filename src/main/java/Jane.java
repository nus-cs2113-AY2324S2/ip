import java.util.Scanner;
public class Jane {
    public static void main(String[] args) {
        String logo = " _____    _____    ____ _    _____ \n" +
                "|____ |  |     |  |    | |  | ____|\n" +
                "    | |  |  |  |  | |  | |  | |___ \n" +
                " _  | |  |  _  |  | |  | |  |  ___|\n" +
                "| |_| |  | | | |  | |  | |  | |___ \n" +
                "|_____|  |_| |_|  |_| ___|  |_____|\n";
        String horizontalLine = "____________________________________________________________\n";
        String greetMessage = "Hello! I am Jane.\nWhat can I do for you?\n";
        String exitMessage = "Bye. Hope to see you again soon!\n";

        System.out.print(logo + horizontalLine);
        System.out.print(greetMessage + horizontalLine);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Task t = new Task(input);
        TaskList taskList = new TaskList();

        while (!input.equals("bye")) {
            System.out.print(horizontalLine);
            Task currentTask;
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.count; i++) {
                    currentTask = taskList.list[i];
                    System.out.println(currentTask.getIndex() + "." +
                            currentTask.getStatusIcon() + currentTask.getDescription());
                }
            } else if (input.startsWith("mark")) {
                input = input.replace("mark ", "");
                currentTask = taskList.list[Integer.parseInt(input)-1];
                currentTask.isDone(true);
                System.out.println("Nice! I've marked this task as done:\n" +
                        currentTask.getStatusIcon() + currentTask.getDescription());
            } else if (input.startsWith("unmark")) {
                input = input.replace("unmark ", "");
                currentTask = taskList.list[Integer.parseInt(input)-1];
                currentTask.isDone(false);
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        currentTask.getStatusIcon() + currentTask.getDescription());
            } else {
                taskList.addTask(t);
                System.out.println("added: " + t.getDescription());
            }
            System.out.print(horizontalLine);
            input = in.nextLine();
            t = new Task(input);
        }

        System.out.print(horizontalLine + exitMessage + horizontalLine);

    }
}
