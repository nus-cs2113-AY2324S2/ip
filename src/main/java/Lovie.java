import java.util.ArrayList;
import java.util.Scanner;

public class Lovie {
    public static void main(String[] args) {
        Scanner inputGetter = new Scanner(System.in);   
        String input;
        ArrayList<Task> tasksList = new ArrayList<Task>();   

        String LOGO = 
                "██╗░░░░░░█████╗░██╗░░░██╗██╗███████╗ \n" +
                "██║░░░░░██╔══██╗██║░░░██║██║██╔════╝ \n" +
                "██║░░░░░██║░░██║╚██╗░██╔╝██║█████╗░░ \n" +
                "██║░░░░░██║░░██║░╚████╔╝░██║██╔══╝░░ \n" +
                "███████╗╚█████╔╝░░╚██╔╝░░██║███████╗ \n" +
                "╚══════╝░╚════╝░░░░╚═╝░░░╚═╝╚══════╝ \n"; 
        System.out.println(); 
        System.out.println(LOGO); 
        System.out.println("Hey hey! My name is Lovie! How can I help you today?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (true) {
            System.out.print("\t"); 
            input = inputGetter.nextLine();
            if (input.equals("bye")) {
                break; 
            } else if (input.equals("list")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                int counter = 0; 
                while (counter < tasksList.size()) {
                    String listNumber = Integer.toString(counter + 1);
                    Task selected = tasksList.get(counter);
                    System.out.print(listNumber + ". ");
                    System.out.print("[" + selected.getTaskIcon() + "] ");
                    System.out.println("[" + selected.getStatusIcon() + "] " +
                            selected.getDescription() + selected.getTimespan());
                    counter += 1; 
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (input.contains("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1].toString()) - 1; 
                if (taskNumber >= tasksList.size() || taskNumber < 0) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Sorry, there is no record of a task number " + 
                            input.split(" ")[1].toString());
                    System.out.println("Can I help you with anything else?"); 
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
                } else {
                    Task selectedTask = tasksList.get(taskNumber); 
                    selectedTask.markAsUndone();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Okay, no worries. I've unmarked this task for you: ");
                    System.out.print("[" + selectedTask.getTaskIcon() + "] ");
                    System.out.println("[" + selectedTask.getStatusIcon() + "] " +
                            selectedTask.getDescription() + selectedTask.getTimespan());
                    System.out.println("What else can I do for you today?"); 
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
                }
            } else if (input.contains("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1].toString()) - 1; 
                if (taskNumber >= tasksList.size() || taskNumber < 0) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Sorry, there is no record of a task number " + 
                            input.split(" ")[1].toString());
                    System.out.println("Can I help you with anything else?"); 
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    Task selectedTask = tasksList.get(taskNumber);
                    selectedTask.markAsDone();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Woo hoo! Way to go. I've marked this task as done:");
                    System.out.print("[" + selectedTask.getTaskIcon() + "] ");
                    System.out.println("[" + selectedTask.getStatusIcon() + "] " +
                            selectedTask.getDescription() + selectedTask.getTimespan());
                    System.out.println("What else can I do for you today?"); 
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } 
            } else {
                String taskType = input.split(" ")[0];
                Task newTask;

                switch (taskType) {
                    case "event":
                        newTask = new Event(input);
                        tasksList.add(newTask);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Added: [E] [ ] " + newTask.getDescription() + newTask.getTimespan());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    case "deadline":
                        newTask = new Deadline(input);
                        tasksList.add(newTask);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Added: [D] [ ] " + newTask.getDescription() + newTask.getTimespan());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    case "todo":
                        newTask = new ToDo(input);
                        tasksList.add(newTask);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Added: [T] [ ] " + newTask.description);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    default:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Sorry, I actually don't have a task in that category.");
                        System.out.println("Please try again");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Thanks for using me! See you next time ♡〜٩( ˃▿˂ )۶〜♡");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
