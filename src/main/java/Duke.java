import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    // assume no more than 100 tasks for now
    private static String[] tasks = new String[100];
    private static int numTasks = 0;
    public static void respond(String query) {
        switch (query) {
        case "list":
            for (int i = 0; i < numTasks; i += 1) {
                System.out.println(Integer.toString(i + 1) + ". " + tasks[i]);
            }
            break;
        default:
            tasks[numTasks % 100] = query;
            numTasks += 1;
            System.out.println("added: " + query);
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
