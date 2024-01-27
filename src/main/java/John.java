import java.util.Scanner;

public class John {
    public static void main(String[] args) {

        System.out.println("Hello! I'm John Chatbot.\n" + "What can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
