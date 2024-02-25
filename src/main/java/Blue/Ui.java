package Blue;

import java.util.Scanner;

public class Ui {
    private static Scanner in = new Scanner(System.in);
    private static InputParser parser = new InputParser();

    public static void talk(String line) {
        System.out.println(line);
    }

    public static void warn(String line) {
        System.out.println(line);
    }

    public static Input getRequest() {
        String line = in.nextLine();
        parser.parse(line);
        Input userInput = parser.getParsedInput();
        return userInput;
    }
}
