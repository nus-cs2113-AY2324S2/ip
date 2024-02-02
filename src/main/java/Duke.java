import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String botName = "Huan";
        System.out.println("Hello! I'm " + botName + ", a chat bot");
        while(true) {
            System.out.println("-------------------------");
            String userInput = scanner.nextLine();

            if(userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye! See ya!");
                break;
            }

            System.out.println(userInput);
        }
    }
}
