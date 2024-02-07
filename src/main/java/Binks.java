import java.util.Arrays;
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
            //when the mark command is given
            else if(command.length == 2 && command[0].equalsIgnoreCase("mark")){
                int taskNumber = Integer.parseInt(command[1]);
                list.markAsDone(taskNumber);
            }
            //when the unmark command is given
            else if(command.length == 2 && command[0].equalsIgnoreCase("unmark")){
                int taskNumber = Integer.parseInt(command[1]);
                list.unmarkAsDone(taskNumber);
            }
            //when the bye command is given
            else if (line.equalsIgnoreCase("bye")) {
                exitChatbot();
                break;
            }
            //when there is a new todo to add
            else if (command.length >= 2 && command[0].equalsIgnoreCase("todo")){
                list.addTodo(line.substring(5));
            }
            //when there is a new deadline to add
            else if (command.length >= 2 && command[0].equalsIgnoreCase("deadline")) {
                int bySeparator = line.indexOf("/by ");
                String deadline = line.substring(bySeparator + 4);
                String task = line.substring(9, bySeparator);
                list.addDeadline(task + " (by: " + deadline + ")");
            }
            //when there is a new event to add
            else if (command.length >= 2 && command[0].equalsIgnoreCase("event")) {
                int fromSeparator = line.indexOf("/from ");
                int toSeparator = line.indexOf("/to ");
                String startTime = line.substring(fromSeparator + 6, toSeparator);
                String endTime = line.substring(toSeparator + 4);
                String task = line.substring(6, fromSeparator);
                list.addEvent(task + " (from: " + startTime + "to: " + endTime + ")");
            }
            else {
                System.out.println("This is not a valid command.");
            }
        }
    }
}
