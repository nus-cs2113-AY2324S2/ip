import java.util.Scanner;

public class Daisy {
    public static void main(String[] args) {
        String INTRO_PROMPT = "Good day! This is Daisy.\nAny task for today?";
        String EXIT_PROMPT = "Ending prompt received. Remember to keep to the deadlines!";
        String LINE_BREAK = "____________________________________";
        String[] tasks = new String[100];
        int task_no = 0;

        System.out.println(LINE_BREAK);
        System.out.println(INTRO_PROMPT);
        System.out.println(LINE_BREAK);

        String command = "";
        Scanner in = new Scanner(System.in);

        while (!command.equals("bye")) {
            command = in.nextLine();
            System.out.println(LINE_BREAK);
            if (command.equals("list")) {
                for (int i = 0; i<task_no; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
            }
            else {
                tasks[task_no] = command;
                task_no++;
                System.out.println("added: " + command);
            }
            System.out.println(LINE_BREAK);
        }

        System.out.println(EXIT_PROMPT);
        System.out.println(LINE_BREAK);
    }
}
