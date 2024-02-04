import java.util.Scanner;

public class Duke {
    public Duke() {
    }

    public static void main(String[] args) {
        String botName = "Hirofumi";
        System.out.println("************************************************************");
        System.out.println(" Hello! I'm " + botName);
        System.out.println(" What can I do for you?");
        System.out.println("************************************************************");
        String userInput = null;
        Scanner in = new Scanner(System.in);

        int MAX_NUMBERED_LIST_LENGTH = 100;
        String[] numberedList = new String[MAX_NUMBERED_LIST_LENGTH];
        int numberedListIndex = 0;

        while(true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("************************************************************");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("************************************************************");
                break;
            }
            else if (!userInput.equals("list")) {
                numberedList[numberedListIndex] = userInput;
                System.out.println("added: " + userInput);
                numberedListIndex++;
            }
            else if (numberedListIndex == 0) {
                System.out.println("List is empty");
            }
            else {
                for (int i = 0; i < numberedListIndex; i++) {
                    System.out.println((i+1) + ". " + numberedList[i]);
                }
            }
        }
    }
}
