import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatBot = "Hello! I'm TONY\n"
                + "What can I do for you?\n"
                + "_________________________";
        System.out.println(chatBot);
        while(true){
            Scanner userInput = new Scanner(System.in);
            String names = userInput.nextLine();

            System.out.println(
                    "_________________________\n"
                    + "    " + names
                    + "\n"
                    + "_________________________\n");
        }

    }
}
