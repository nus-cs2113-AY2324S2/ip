import java.util.Scanner;

public class Jake {

    // List out all tasks
    private static void listTask(Task[] commands, int totalCommands) {
        System.out.println("Current commands being executed: ");
        for (int i=0; i<totalCommands; i++){
            System.out.println(Integer.toString(i+1) + "." + commands[i].getStatus());
        }
    }

    // Mark or Unmark respective task.
    private static void markOrUnmarkTask(Task[] commands, int totalCommands, String userInput) {
        int taskNumber = Integer.parseInt(userInput.substring(userInput.length()-1));
        if (taskNumber>totalCommands){
            System.out.println("Task does not exist!");
        } else if (userInput.startsWith("unmark")){
            System.out.println("Successfully unmarked");
            commands[taskNumber-1].markAsUndone();;
        } else {
            System.out.println("Successfully marked");
            commands[taskNumber-1].markAsDone();
        }
    }

    // Add Task. Allows ToDos, Deadlines, and Events
    private static void addTask(Task[] commands, int totalCommands, String userInput) {
        Task newTask;
        if (userInput.startsWith("todo")){
            newTask = new ToDo(userInput);
        } else if (userInput.startsWith("deadline")){
            String[] deadlineSections = userInput.split(" /by ");
            newTask = new Deadline(deadlineSections[0], deadlineSections[1]);
        } else if (userInput.startsWith("event")){
            String[] eventSections = userInput.split(" /from ");
            String[] eventTimings = eventSections[1].split(" /to ");
            newTask = new Event(eventSections[0], eventTimings[0], eventTimings[1]);
        } else {
            return;
        }
        
        commands[totalCommands] = newTask;
        System.out.println("______________________________________________");
        System.out.println("Got it! I have successfully added: \n" + "    " + newTask.getStatus());
        System.out.printf("You have a total of %d tasks in the list \n", totalCommands+1);
        System.out.println("______________________________________________");
    }


    public static void main(String[] args) {
        
        System.out.println("Hello! I'm Jake\n" + "What can I do for you? \n" + "______________________________________________");

        Scanner myScanner = new Scanner(System.in);
        Task[] commands = new Task[100];
        int totalCommands = 0;
        String userInput= "";

        do {
            userInput = myScanner.nextLine();
            if (userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            } else if (userInput.equals("list")){
                listTask(commands, totalCommands);
            } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")){
                markOrUnmarkTask(commands, totalCommands, userInput);
            } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")){
                addTask(commands, totalCommands, userInput);
                totalCommands++;
            } else {
                System.out.println("Invalid task! Please try again!");
            }
        } while (!userInput.equals("bye"));

        myScanner.close();
    }
}