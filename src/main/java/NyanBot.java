import java.util.Scanner;
public class NyanBot {
    private static String line = "____________";
    private static String[] userInput = new String[100];
    private static int userInputCount = 0;

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        switch(input) {
        case "bye":
            return;
        case "list":
            System.out.println(line);
            for (int i = 0; i < userInputCount; i++) {
                System.out.println(i + 1 + ". " + userInput[i]);
            }
            break;
        default:
            System.out.println(line);
            userInput[userInputCount] = input;
            userInputCount++;
            System.out.println("Added: " + input);
        }
        echo();
    }
    public static void main(String[] args) {
        String greet = "お帰りなさいませ、ご主人様。ニャンー♡♡";
        String prompt = "なにをしたいの？";
        String bye = "じゃー、またね〜！！";

        System.out.println(line);
        System.out.println(greet + "\n" + prompt);

        echo();

        System.out.println(line);
        System.out.println(bye);
    }
}
