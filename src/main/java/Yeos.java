import java.util.Scanner;
public class Yeos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greeting = "Hello! I'm Yeos, your personal chatbot!\n"
                + "What can I do for you today?\n";
        String bye = "Bye. Hope to see you again soon!\n";
        String line = "_____________________________________________________________________\n";
        System.out.println(line + greeting + line);

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {  // Check if the input is "bye"
                System.out.println(line + bye + line);
                break;
            }
            System.out.println(line + input + "\n" + line);
        }
        scanner.close();
    }
}
