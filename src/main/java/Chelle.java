import java.util.Scanner;

public class Chelle {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Hello! I'm Chelle.\nI like to talkity talkity talk!");

        while (true) {
            System.out.print("You: ");
            String userInput = Sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Chelle: Bye. Hope to see you again soon!");
                break;
            }

            System.out.println("Chelle: " + userInput);
        }

        Sc.close();
    }
}
