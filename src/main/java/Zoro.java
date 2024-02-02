import java.util.Scanner;

public class Zoro {
    private static final int MAX_TASKS = 100;
    private static final String TAB_SPACE = "    ";
    private static final String LINE = TAB_SPACE + "_____________________________________________________________";
    private static final String CHATBOT_NAME = "Zoro";

    public static void main(String[] args) {

        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Hello I'm " + CHATBOT_NAME);
        System.out.println(TAB_SPACE + "What can I do for you?");
        System.out.println(LINE);

        Task[] taskLists = new Task[MAX_TASKS];
        int index = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            String input;
            input = in.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                int count = 0;
                System.out.println(LINE);
                System.out.println(TAB_SPACE + "Here are the tasks in your list:");

                while (count < MAX_TASKS && taskLists[count] != null) {
                    System.out.println(TAB_SPACE + (count + 1) + "." + taskLists[count].getStatusIcon() + " "
                            + taskLists[count].description);
                    count++;
                }
                System.out.println(LINE);

            } else if (input.startsWith("mark ")) {

                int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;

                if (taskIndex >= index) {
                    System.out.println(LINE);
                    System.out.println(TAB_SPACE + "Index out of bound for marking. Check your index properly");
                    System.out.println(LINE);
                    continue;
                }

                taskLists[taskIndex].setAsDone();
                System.out.println(LINE);
                System.out.println(TAB_SPACE + "Nice! I've marked this task as done:");
                System.out.println(
                        TAB_SPACE + taskLists[taskIndex].getStatusIcon() + " " + taskLists[taskIndex].description);
                System.out.println(LINE);

            } else if (input.startsWith("unmark ")) {

                int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                if (taskIndex >= index) {
                    System.out.println(LINE);
                    System.out.println(TAB_SPACE + "Index out of bound for to unmark. Check your index properly");
                    System.out.println(LINE);
                    continue;
                }
                taskLists[taskIndex].setAsNotDone();

                System.out.println(LINE);
                System.out.println(TAB_SPACE + "OK, I've marked this task as not done yet:");
                System.out.println(
                        TAB_SPACE + taskLists[taskIndex].getStatusIcon() + " " + taskLists[taskIndex].description);
                System.out.println(LINE);
            } else {
                if (index < MAX_TASKS) {
                    taskLists[index++] = new Task(input);
                    System.out.println(LINE);
                    System.out.println(TAB_SPACE + "added: " + input);
                    System.out.println(LINE);
                } else {
                    System.out.println(LINE);
                    System.out.println(TAB_SPACE + "Reached maximum limit for adding tasks");
                    System.out.println(LINE);
                }
            }
        }

        in.close();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
