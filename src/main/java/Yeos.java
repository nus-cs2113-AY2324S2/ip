import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Yeos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greeting = "Hello! I'm Yeos, your personal chatbot!\n"
                + "What can I do for you today?";
        String bye = "Bye. Hope to see you again soon!";
        String line = "_____________________________________________________________________";
        String nl = "\n";
        System.out.println(line + nl + greeting + nl+ line);
        List<String> task_list = new ArrayList<>();
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {  // Check if the input is "bye"
                System.out.println(line + nl + bye + nl + line);
                break;
            }
            else if (input.equals("list")) {
                int task_counter = 1;
                System.out.println(line);
                for (String tasks : task_list) {
                    System.out.println(task_counter + ": " + tasks);
                    task_counter += 1;
                }
                System.out.println(line);
            }
            else {
                task_list.add(input);
                System.out.println(line + nl + "added: " + input + nl + line);
            }
        }
        scanner.close();
    }
}
