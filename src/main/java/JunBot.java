import java.util.Scanner;
public class JunBot {


    public static void unmarkTaskInList(Task[] tasks, String command){
        command = command.replace("unmark", "").trim();
        int listNumber = Integer.parseInt(command) - 1;
        tasks[listNumber].unmarkTask();
    }
    public static void markTaskInList(Task[] tasks, String command){
        command = command.replace("mark", "").trim();
        int listNumber = Integer.parseInt(command) - 1;
        tasks[listNumber].markTask();
    }

    public static String getCommand(String userInput){
        int i  = userInput.indexOf(' ');
        String command;
        if ( i != -1 ) {
            command = userInput.substring(0, i);
        } else {
            command = userInput;
        }
        return command;

    }
    public static void printList(Task[] tasks, int tasksCount){
        int taskNumber = 1;
        String divider = "____________________________________________________________";

        System.out.println(divider);
        for(int i = 0; i < tasksCount; i++){
            System.out.print( taskNumber + ". ");
            tasks[i].printTask();
            taskNumber += 1;
        }
        System.out.println(divider+"\n");
    }

    public static void addToList(String description, Task[] tasks, int listPosition){
        String divider = "____________________________________________________________\n";
        Task userTask = new Task(description,listPosition);
        tasks[listPosition] = userTask;
        System.out.println(divider + "added: " + userTask.description + "\n" + divider);
    }

    public static void main(String[] args) {
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm JunBot\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        Task[] tasks = new Task[100];

        System.out.println(divider + greeting + divider);

        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();
        int tasksCount = 0;

        while (!userInput.equals("bye")){
            String command = getCommand(userInput);
            switch (command) {
            case "list" : printList(tasks, tasksCount);
                break;

            case "mark" : markTaskInList(tasks, userInput);
                break;
            case "unmark": unmarkTaskInList(tasks, userInput);
                break;
            default: addToList(userInput, tasks, tasksCount); tasksCount++;
                break;
            }
            userInput = userInputScanner.nextLine();
        }

        System.out.println(divider + goodbye + divider);
    }
}
