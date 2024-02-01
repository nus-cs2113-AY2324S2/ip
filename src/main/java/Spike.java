import java.util.Scanner;

public class Spike {
    public static void main(String[] args) {
        String chatbot = "Spike";
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?\n");

        String[] inputList = new String[100];
        int iter = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.contentEquals("list")) {
                for (String value : inputList) {
                    if (value == null) {
                        break;
                    }
                    System.out.println(value);
                }
            } else if (input.contentEquals("bye")) {
                break;
            } else {
                System.out.println("added: " + input);
                inputList[iter] = input;
            }
            iter += 1;
        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}
