import java.util.Scanner;

public class Blue {
    private static final String WELCOME_MESSAGE = "Greetings! I'm Blue, your personal chatbot assistant. How may I be of service?";
    private static final String GOODBYE_MESSAGE = "Till we meet again.";
    public static void talk(String line) {
        // no formatting for now
        System.out.println(line);
    }
    private static void handleRequest(String[] requestDetails) {
        TaskManager tmMaster = new TaskManager();
        switch (requestDetails[0]) {
        case "list":
            tmMaster.listTasks();
            break;
        case "mark":
            tmMaster.markTask(Integer.parseInt(requestDetails[1]) - 1);
            break;
        case "todo":
            tmMaster.addTask(requestDetails[1]);
            break;
        case "deadline":
            tmMaster.addTask(requestDetails[1], requestDetails[2]);
            break;
        case "event":
            tmMaster.addTask(requestDetails[1], requestDetails[3], requestDetails[2]);
            break;
        }
    }
    public static void main(String[] args) {
        talk(WELCOME_MESSAGE);
        Scanner in = new Scanner(System.in);
        InputParser blueParser = new InputParser();
        for (String line = in.nextLine(); !line.trim().equals("bye"); line = in.nextLine()) {
            try {
                String[] requestDetails = blueParser.parseLine(line);
                handleRequest(requestDetails);
            } catch (IllegalInput e) {
                talk(String.valueOf(e));
            }
        }
        talk(GOODBYE_MESSAGE);
    }
}