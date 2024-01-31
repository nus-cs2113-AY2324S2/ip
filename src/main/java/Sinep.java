import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Sinep {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greeting = "Hello! I'm Sinep, your personal chatbot!\n"
                + "What can I do for you today?";
        String bye = "Bye. Hope to see you again soon!";
        String line = "_____________________________________________________________________";
        String nl = System.lineSeparator();
        System.out.println(line + nl + greeting + nl+ line);
        List<Task> taskList = new ArrayList<>();
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {  // Check if the input is "bye"
                System.out.println(line + nl + bye + nl + line);
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i).toString());
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                Task markingTask = taskList.get(taskIndex);
                markingTask.markAsDone();
                System.out.println(line + nl + "Got it! Task " + (taskIndex + 1) + " marked as done:");
                System.out.println(markingTask.getStatusIcon() + " " + markingTask.description + nl + line);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                Task markingTask = taskList.get(taskIndex);
                markingTask.unmarkAsDone();
                System.out.println(line + nl + "Got it! Task " + (taskIndex + 1) + " marked as undone:");
                System.out.println(markingTask.getStatusIcon() + " " + markingTask.description + nl + line);
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println(line + nl + "added to task list: " + input + nl + line);
            }
        }
        scanner.close();
    }
}
