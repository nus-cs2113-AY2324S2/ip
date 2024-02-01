import java.util.Arrays;
import java.util.Scanner;
public class EDITH {

    public static void printFormattedMessage(String message){
        String horizontal_lines = "____________________________________________________________";
        System.out.println(horizontal_lines);
        System.out.println("  " + message);
        System.out.println(horizontal_lines);
    }
    public static void main(String[] args) {
        String chatbot_name = "EDITH";
        String horizontal_lines = "____________________________________________________________";

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println(horizontal_lines);
        System.out.println("Hello! I'm " + chatbot_name);
        System.out.println("What can I do for you?");
        System.out.println(horizontal_lines);

        while (true) {

            line = in.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                printFormattedMessage("Bye. Hope to see you again soon!");
                break;
            } else {
                printFormattedMessage(line);
            }
        }
    }
}
