import java.util.Scanner;

public class ChatBBT {

    public static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void greetingMessage() {
        System.out.println("Hello! I'm ChatBBT");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        greetingMessage();
        Scanner input = new Scanner(System.in);
        boolean isFinished = false;

        while(!isFinished) {
            String inputText = input.nextLine();
            if (inputText.equals("bye")) {
                exitMessage();
                isFinished = true;
            } else {
                System.out.println(inputText);
                System.out.println("------------------------------------------");
            }
        }

    }
}
