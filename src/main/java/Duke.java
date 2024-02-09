import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static final String lineBreak = "----------------------------------------------------------";
    public static void printList(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1)+"."+list.get(i));
        }
    }
    public static void main(String[] args) {
        String input = "Start";
        MoodSprite mood = new MoodSprite();
        Parser parser = new Parser();
        List<Task> list = new ArrayList<>(2);
        Scanner in = new Scanner(System.in);
        System.out.println(mood.getIdle() + "Hello, Im Pythia, how may I help you today?\n"+lineBreak);
        while (!input.equals("bye")) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("list")) {
                printList(list);
            }
            else if (parser.isValidCommand(input)) {
                list.add(parser.addTask(input));
                System.out.println("added: "+ list.get(list.size()-1));
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
        System.out.println(mood.getHappy()+"Happy to help, have a great day.\n"+lineBreak);
    }
}
