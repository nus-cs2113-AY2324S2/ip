import java.util.Scanner;

public class Daisy {
    public static void main(String[] args) {
        String INTRO_PROMPT = "Good day! This is Daisy.\nAny task for today?";
        String EXIT_PROMPT = "Ending prompt received. Remember to keep to the deadlines!";
        String LINE_BREAK = "____________________________________";

        System.out.println(LINE_BREAK);
        System.out.println(INTRO_PROMPT);
        System.out.println(LINE_BREAK);

        String command = "";
        Scanner in = new Scanner(System.in);

        while(!command.equals("bye")) {
            command = in.nextLine();
            System.out.println(LINE_BREAK);
            System.out.println(command);
            System.out.println(LINE_BREAK);
        }

        System.out.println(EXIT_PROMPT);
        System.out.println(LINE_BREAK);
    }
}
