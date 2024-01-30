import java.util.Scanner;

public class Binks {
    /**
     * Prints out the greeting message from the chatbot "Binks".
     */
    public static void greetUser(){
        createLineSpacing();
        System.out.println("Hello! I'm Binks.");
        System.out.println("What can I do for you?");
        createLineSpacing();
    }

    /**
     * Prints out the line spacing between inputs and outputs in the console.
     */
    public static void createLineSpacing(){
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the exit message from the chatbot "Binks" when user inputs "Bye".mark 
     */
    public static void exitChatbot(){
        createLineSpacing();
        System.out.println("Bye. Hope to see you again soon!");
        createLineSpacing();
    }
    public static void main(String[] arg){
        greetUser();
        List list = new List();
        while(true){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] command = line.split(" ");
            if (line.equalsIgnoreCase("list")){
                list.getList();
            }
            else if(command.length == 2 && command[0].equalsIgnoreCase("mark")){
                int taskNumber = Integer.parseInt(command[1]);
                list.markAsDone(taskNumber);
            }
            else if(command.length == 2 && command[0].equalsIgnoreCase("unmark")){
                int taskNumber = Integer.parseInt(command[1]);
                list.unmarkAsDone(taskNumber);
            }
            else if (line.equalsIgnoreCase("bye")) {
                exitChatbot();
                break;
            }
            else {
                list.addItem(line);
            }
        }
    }
}
