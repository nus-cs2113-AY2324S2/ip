import java.util.Scanner;

public class Duke {
    private static void handleLine(String line, TaskManager tmMaster) {
        String request = line.split(" ")[0];
        switch (request) {
        case "list":
            tmMaster.listTasks();
            break;
        case "mark":
            tmMaster.markTask(Integer.parseInt(line.substring(5)) - 1);
            break;
        case "todo":
            tmMaster.addTask(line.substring(5));
            break;
        case "deadline":
            int byStringIndex = line.indexOf("/by");
            tmMaster.addTask(line.substring(9, byStringIndex - 1), line.substring(byStringIndex + 4));
            break;
        case "event":
            int fromStringIndex = line.indexOf("/from");
            int toStringIndex = line.indexOf("/to");
            tmMaster.addTask(line.substring(6, fromStringIndex - 1), line.substring(fromStringIndex + 6, toStringIndex - 1), line.substring(toStringIndex + 4));
            break;
        default:
            System.out.println("Invalid action");
        }
    }
    public static void main(String[] args) {
        System.out.println("Greetings! I'm Blue, your personal chatbot assistant. How may I be of service?");
        Scanner in = new Scanner(System.in);
        TaskManager tmMaster = new TaskManager();
        for (String line = in.nextLine(); !line.equals("bye"); line = in.nextLine()) {
            handleLine(line, tmMaster);
        }
        System.out.println("Glad to help. Bye!");
    }
}