package Blue;

import java.util.Scanner;

public class Blue {
    private static final String WELCOME_MESSAGE = "Greetings! I'm Blue, your personal chatbot assistant. How may I be of service?";
    private static final String GOODBYE_MESSAGE = "Till we meet again.";

    // no fancy formatting for now
    public static void talk(String line) {
        System.out.println(line);
    }

    private static Input getRequest(Scanner in, InputParser blueParser) {
        String line = in.nextLine();
        blueParser.parse(line);
        Input userInput = blueParser.getParsedInput();
        return userInput;
    }

    private static int handleRequest(Input request) {
        if (request.getCommand() == InputCommand.bye) {
            return 0;
        }
        if (request.getCommand() == InputCommand.undefined) {
            return 2;
        }
        TaskManager tmMaster = new TaskManager(request);
        tmMaster.performRequest();
        return 1;
    }

    public static void main(String[] args) {
        talk(WELCOME_MESSAGE);
        TaskManager tmMaster = new TaskManager();
        Scanner in = new Scanner(System.in);
        InputParser blueParser = new InputParser();
        Input userInput;
        int status = 1;
        while (status != 0) {
            userInput = getRequest(in, blueParser);
            status = handleRequest(userInput);
        }
        talk(GOODBYE_MESSAGE);
    }
}
