import java.util.Scanner;

public class Daisy {
    public static void main(String[] args) {
        String INTRO_PROMPT = "Good day! This is Daisy.\nAny task for today?";
        String EXIT_PROMPT = "Ending prompt received. Remember to keep to the deadlines!";
        String LINE_BREAK = "____________________________________";
        Task[] tasks = new Task[100];
        Task new_task;
        int task_no = 0;


        System.out.println(LINE_BREAK);
        System.out.println(INTRO_PROMPT);
        System.out.println(LINE_BREAK);

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            System.out.println(LINE_BREAK);
            String[] separate_commands = command.split(" ");
            switch (separate_commands[0]) {
                case "list":
                    for (int i = 0; i < task_no; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    break;
                case "mark":
                    tasks[Integer.parseInt(separate_commands[1])-1].setDone();
                    System.out.println("Congrats on completing the task!");
                    System.out.println(tasks[Integer.parseInt(separate_commands[1])-1]);
                    break;
                case "unmark":
                    tasks[Integer.parseInt(separate_commands[1])-1].setUndone();
                    System.out.println("More time needed for the following task? Sure!");
                    System.out.println(tasks[Integer.parseInt(separate_commands[1])-1]);
                    break;
                default:
                    new_task = new Task(command);
                    tasks[task_no] = new_task;
                    task_no++;
                    System.out.println("added: " + new_task);
                    break;
            }
            System.out.println(LINE_BREAK);
            command = in.nextLine();
        }
        System.out.println(EXIT_PROMPT);
        System.out.println(LINE_BREAK);
    }
}
