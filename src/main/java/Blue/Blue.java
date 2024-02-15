package Blue;

import java.util.Scanner;

public class Blue {
    private static final String WELCOME_MESSAGE = "Greetings! I'm Blue, your personal chatbot assistant. How may I be of service?";
    private static final String GOODBYE_MESSAGE = "Till we meet again.";

    // no fancy formatting for now
    public static void talk(String line) {
        System.out.println(line);
    }
    private static void handleRequest(InputParser blueRequest) {
        TaskManager tmMaster = new TaskManager();
        InputCommand request = blueRequest.getCommand();
        switch (request) {
        case list:
            tmMaster.listTasks();
            break;
        case mark:
            tmMaster.markTask(blueRequest.getTaskIndex());
            break;
        case todo:
        case deadline:
        case event:
            tmMaster.addTask(blueRequest.getTaskToAdd());
            break;
        default:
        }
    }
    public static void main(String[] args) {
        talk(WELCOME_MESSAGE);
        Scanner in = new Scanner(System.in);
        for (String line = in.nextLine(); !line.trim().equals("bye"); line = in.nextLine()) {
            InputParser blueRequest = new InputParser(line);
            handleRequest(blueRequest);
        }
        talk(GOODBYE_MESSAGE);
    }
}