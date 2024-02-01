import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void printList(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1)+"."+list.get(i).getDoneStatus()+" "+list.get(i).getDescription());
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
        List<Task> list = new ArrayList<>(2);
        Scanner in = new Scanner(System.in);
        System.out.println(idle + "Hello, Im Pythia, how may I help you today?\n"+lineBreak);
        while (!input.equals("bye")) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("list")) {
                printList(list);
            }
            else if (input.contains("add ")) {
                String[] splitInput = input.split(" ");
                System.out.println("added: "+splitInput[1]);
                list.add(new Task(input));
            }
            else if (input.contains("unmark ")) {
                String[] splitInput = input.split(" ");
                list.get(Integer.parseInt(splitInput[1])-1).doneIsFalse();
                System.out.println("Unmarked "+ Integer.parseInt(splitInput[1]));
            }
            else if (input.contains("mark ")) {
                String[] splitInput = input.split(" ");
                list.get(Integer.parseInt(splitInput[1])-1).doneIsTrue();
                System.out.println("Marked "+ Integer.parseInt(splitInput[1]));
            }
            else {
                System.out.println(input);
            }
            System.out.println(lineBreak);
        }
        System.out.println(happy+"Happy to help, have a great day.\n"+lineBreak);
    }
}
