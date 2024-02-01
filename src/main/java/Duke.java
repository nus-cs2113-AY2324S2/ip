import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    // assume no more than 100 tasks for now
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;
    private static void respond(String input) {
        String[] inputWords = input.split(" ");
        String query = inputWords[0];
        switch (query) {
        case "list":
            for (int i = 0; i < numTasks; i += 1) {
                System.out.println(Integer.toString(i + 1) + ". " + tasks[i].getDescription() + " [" + tasks[i].getStatusIcon() + "]");
            }
            break;
        case "mark":
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            if (taskIndex >= numTasks || taskIndex < 0) {
                System.out.println("Task not found!");
                return;
            }
            tasks[taskIndex].setDone();
            System.out.println("Task " + tasks[taskIndex].getDescription() + " marked as done.");
            break;
        default:
            tasks[numTasks % 100] = new Task(input);
            numTasks += 1;
            System.out.println("added: " + input);
        }
    }
    public static void main(String[] args) {
        System.out.println("Greetings! I am Blue, your personal chatbot assistant. How may I help you?");
        Scanner in = new Scanner(System.in);
        for (String line = in.nextLine(); !line.equals("bye"); line = in.nextLine()) {
            respond(line);
        }
        System.out.println("Glad to be of service. Bye!");
    }
}
