import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Greetings! I'm Blue, your personal chatbot assistant. How may I be of service?";
    private static final String GOODBYE_MESSAGE = "Till we meet again.";
    public static void talk(String line) {
        // no formatting for now
        System.out.println(line);
    }
    private static void warnInvalidInput() {
        talk("I'm sorry. I'm afraid I can't do that.");
    }
    private static void handleLine(String line) {
        TaskManager tmMaster = new TaskManager();
        String request = line.split(" ")[0];
        switch (request) {
        case "list":
            tmMaster.listTasks();
            break;
        case "mark":
            int taskIndex = Integer.parseInt(line.substring(5)) - 1;
            if (taskIndex < 0 || taskIndex >= tmMaster.getNumTasks()) {
                warnInvalidInput();
                break;
            }
            tmMaster.markTask(taskIndex);
            break;
        case "todo":
            tmMaster.addTask(line.substring(5));
            break;
        case "deadline":
            int byStringIndex = line.indexOf("/by");
            if (byStringIndex < 0) {
                warnInvalidInput();
                break;
            }
            tmMaster.addTask(line.substring(9, byStringIndex - 1), line.substring(byStringIndex + 4));
            break;
        case "event":
            int fromStringIndex = line.indexOf("/from");
            int toStringIndex = line.indexOf("/to");
            if (fromStringIndex < 0 || toStringIndex < 0) {
                warnInvalidInput();
                break;
            }
            tmMaster.addTask(line.substring(6, fromStringIndex - 1), line.substring(fromStringIndex + 6, toStringIndex - 1), line.substring(toStringIndex + 4));
            break;
        default:
            warnInvalidInput();
        }
    }
    public static void main(String[] args) {
        talk(WELCOME_MESSAGE);
        Scanner in = new Scanner(System.in);
        for (String line = in.nextLine(); !line.equals("bye"); line = in.nextLine()) {
            handleLine(line);
        }
        talk(GOODBYE_MESSAGE);
    }
}