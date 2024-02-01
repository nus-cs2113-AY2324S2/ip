import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printsGreeting();
        mimicMessage();

    }
    private static void printsGreeting() {
        String greetingMessage = "Hello! I'm ThawBot!\nWhat can I do for you?\n";
        System.out.println(greetingMessage);
    }

    private static void printGoodByeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    private static void mimicMessage(){
        Scanner input = new Scanner(System.in);
        boolean canExit = false;
        while (!canExit) {
            String usersInput = input.nextLine();
            if (usersInput.equals("bye")) {
                canExit = true;
                printGoodByeMessage();
            }
            else {
                System.out.println(usersInput);
            }

        }
    }
}
