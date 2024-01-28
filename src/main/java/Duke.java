import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        
        String greet = "Hello! I'm J@RV15\n" + "What can I do for you? \n" + "__________________________";
        System.out.println(greet);
        boolean isStillChatting = true;
        Scanner myScanner = new Scanner(System.in);

        while (isStillChatting){
            String userCommand = myScanner.nextLine();

            if (userCommand.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                isStillChatting = false;
            } else {
                System.out.println("Currently processing your request to do: " + userCommand);
                System.out.println("Request completed! \n" + "_____________________________");

            }
        }

        myScanner.close();
    }
}