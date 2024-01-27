import java.util.Scanner;

public class BossMan {

    private final Scanner scanner;
    private final String sep = "____________________________________________________________";
    private final String botName = "BossMan";

    public BossMan() {
        this.scanner = new Scanner(System.in);
    }

    public void greetUser(){
        String greet = "Hello! I'm " + botName;
        String offerService = "What can I do for you?";
        System.out.println(sep + "\n" + greet);
        System.out.println(offerService + "\n" + sep);
    }

    public void endChat(){
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit + "\n" + sep);
    }

    public void startChat() {
        String userInput;

        do {
            System.out.print("You: ");
            userInput = scanner.nextLine();
            System.out.println(botName + ": " + userInput + "\n" + sep);
        } while (!userInput.equalsIgnoreCase("bye"));
    }
}


