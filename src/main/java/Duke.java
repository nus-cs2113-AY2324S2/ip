import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String lineBreak = "----------------------------------------------------------";
        String idle = "       █████████████    \n"
                    + "      ██           ██   \n"
                    + "      ██   █████   ██   \n"
                    + "      ██   █████   ██   \n"
                    + "      ██           ██   \n"
                    + "       █████████████    \n"
                    + "   ██                  ██\n"
                    + "     ██████████████████ \n\n";

        String happy= "            ███         \n"
                    + "          ███████       \n"
                    + "         ████ ████      \n"
                    + "        ███     ███     \n"
                    + "       ██         ██    \n"
                    + "   ██                 ██\n"
                    + "    ███             ███ \n"
                    + "      ███████████████   \n\n";

        String input = "Start";
        Scanner in = new Scanner(System.in);
        System.out.println(idle + "Hello, Im Pythia, how may I help you today?\n"+lineBreak);
        while (!input.equals("bye")) {
            input = in.nextLine();
            System.out.println(input+"\n"+lineBreak);
        }
        System.out.println(happy+"Happy to help, have a great day.\n"+lineBreak);
    }
}
