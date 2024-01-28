import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        
        System.out.println("Hello! I'm J@RV15\n" + "What can I do for you? \n" + "__________________________");

        Scanner myScanner = new Scanner(System.in);
        String[] commands = new String[100];
        int totalCommands = 0;
        String userCommand= "";

        do {
            userCommand = myScanner.nextLine();

            if (userCommand.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            } else if (userCommand.equals("list")){
                System.out.println("Current commands:");
                for (int i=0; i<totalCommands; i++){
                    System.out.println(Integer.toString(i+1) + ". " + commands[i]);
                }
            } else {
                System.out.println("Successfully added: " + userCommand + "\n" + "__________________________");
                commands[totalCommands] = userCommand;
                totalCommands++;
            }
        } while (!userCommand.equals("bye"));

        myScanner.close();
    }
}