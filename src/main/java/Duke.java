import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void printList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1)+"."+list.get(i));
        }
    }
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
        List<String> list = new ArrayList<String>(10);
        Scanner in = new Scanner(System.in);
        System.out.println(idle + "Hello, Im Pythia, how may I help you today?\n"+lineBreak);
        while (!input.equals("bye")) {
            input = in.nextLine();
            if (input.equals("list")) {
                printList(list);
            }
            else {
                System.out.println("added: "+input+"\n"+lineBreak);
                list.add(input);
            }
        }
        System.out.println(happy+"Happy to help, have a great day.\n"+lineBreak);
    }
}
