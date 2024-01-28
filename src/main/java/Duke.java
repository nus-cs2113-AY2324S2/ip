import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatBot = "Hello! I'm TONY\n"
                + "What can I do for you?\n"
                + "_________________________";
        System.out.println(chatBot);
        String[] userCommands = new String[100];
        int count = 0;
        while(true){
            Scanner userInput = new Scanner(System.in);
            userCommands[count] = userInput.nextLine();
            if(userCommands[count].equals("bye")){
                System.out.println(
                        "_________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "_________________________\n");
                return;
            }
            System.out.println(
                    "_________________________\n"
                    + "    added: " + userCommands[count]
                    + "\n"
                    + "_________________________\n");
            count++;
        }

    }
}
