import java.util.Scanner;
public class NyanBot {
    private static String line = "____________";

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

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals("bye")) {
            return;
        }
        System.out.println(line);
        System.out.println(input);
        echo();
    }
}
