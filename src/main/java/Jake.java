import java.util.Scanner;

public class Jake {
    public static void main(String[] args) {
        
        System.out.println("Hello! I'm Jake\n" + "What can I do for you? \n" + "_______________________");

        Scanner myScanner = new Scanner(System.in);
        Task[] commands = new Task[100];
        int totalCommands = 0;
        String userCommand= "";

        do {
            userCommand = myScanner.nextLine();

            if (userCommand.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            } else if (userCommand.equals("list")){
                System.out.println("Current commands being executed: ");
                for (int i=0; i<totalCommands; i++){
                    System.out.println(Integer.toString(i+1) + ".[" 
                        + commands[i].getStatusIcon() + "] "+ commands[i].description);
                }
            } else if (userCommand.contains("mark")){
                int taskNumber = Integer.parseInt(userCommand.substring(userCommand.length()-1));
                if (taskNumber>totalCommands){
                    System.out.println("Task does not exist!");
                    continue;
                } else if (userCommand.startsWith("unmark")){
                    System.out.println("Successfully unmarked");
                    commands[taskNumber-1].markAsUndone();;
                } else {
                    System.out.println("Successfully marked");
                    commands[taskNumber-1].markAsDone();;
                }
            } else {
                System.out.println("Successfully added: " + userCommand + "\n" + "_______________________");
                Task newTask = new Task(userCommand);
                commands[totalCommands] = newTask;
                totalCommands++;
            }
        } while (!userCommand.equals("bye"));

        myScanner.close();
    }
}