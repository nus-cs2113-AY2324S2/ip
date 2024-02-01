import java.util.ArrayList;
import java.util.Scanner;

public class Lovie {
    public static void main(String[] args) {
        Scanner inputGetter = new Scanner(System.in);   
        String input = new String(); 
        ArrayList<Task> tasksList = new ArrayList<Task>();   

        String logo = 
        "       ██╗░░░░░░█████╗░██╗░░░██╗██╗███████╗ \n" +
        "       ██║░░░░░██╔══██╗██║░░░██║██║██╔════╝ \n" +
        "       ██║░░░░░██║░░██║╚██╗░██╔╝██║█████╗░░ \n" +
        "       ██║░░░░░██║░░██║░╚████╔╝░██║██╔══╝░░ \n" +
        "       ███████╗╚█████╔╝░░╚██╔╝░░██║███████╗ \n" +
        "       ╚══════╝░╚════╝░░░░╚═╝░░░╚═╝╚══════╝ \n\n"; 
        System.out.println("        Hey hey! My name is \n\n" + logo);
        System.out.println("        How can I help you today?\n");
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                "\n\n");

        while (true) {
            input = inputGetter.nextLine(); 
            if (input.equals("bye")) {
                break; 
            } else if (input.equals("list")) {
                System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                        "~~~~~~~~~~~~~~\n\n");
                int counter = 0; 
                while (counter < tasksList.size()) {
                    String listNumber = Integer.toString(counter + 1); 
                    System.out.println("        " + listNumber + ". " + "[" + 
                            tasksList.get(counter).getStatusIcon() + "] " + 
                            tasksList.get(counter).description + "\n\n");
                    counter += 1; 
                }
                System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                        "~~~~~~~~~~~~~~~\n\n");
            } else if (input.contains("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1].toString()) - 1; 
                if (taskNumber >= tasksList.size() || taskNumber <= 1) {
                    System.out.println("\n\n");
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                    System.out.println("        Sorry, there is no record of a task number " + 
                            input.split(" ")[1].toString() + "\n");
                    System.out.println("        Can I help you with anything else?\n\n"); 
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "~~~~~~~~~~~~~~~~~~~~~~~~\n\n"); 
                } else {
                    Task selectedTask = tasksList.get(taskNumber); 
                    selectedTask.markAsUndone();
                    System.out.println("\n\n");
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "~~~~~~~~~~~~~~~~~~~~~~\n\n");
                    System.out.println("        Okay, no worries. I've unmarked this task for you: \n"); 
                    System.out.println("            [" + selectedTask.getStatusIcon() + "] " + 
                            selectedTask.description + "\n"); 
                    System.out.println("        What else can I do for you today?\n\n"); 
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "~~~~~~~~~~~~~~~~~~~~~~\n\n"); 
                }
            } else if (input.contains("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1].toString()) - 1; 
                if (taskNumber >= tasksList.size() || taskNumber <= 1) {
                    System.out.println("\n\n");
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                    System.out.println("        Sorry, there is no record of a task number " + 
                            input.split(" ")[1].toString() + "\n");
                    System.out.println("        Can I help you with anything else?\n\n"); 
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"); 
                } else {
                    Task selectedTask = tasksList.get(taskNumber); 
                    selectedTask.markAsDone();
                    System.out.println("\n\n");
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                    System.out.println("        Woo hoo! Way to go. I've marked this task as done:\n"); 
                    System.out.println("            [" + selectedTask.getStatusIcon() + "] " + 
                            selectedTask.description + "\n"); 
                    System.out.println("        What else can I do for you today?\n\n"); 
                    System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                } 
            } else {
                Task newTask = new Task(input);
                tasksList.add(newTask);
                System.out.println("\n\n");
                System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                        "~~~~~~~~~~~~~~~~~~~~~\n\n");
                System.out.println("        added: " + newTask.description + "\n\n"); 
                System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                        "~~~~~~~~~~~~~~~~~~~~~\n\n"); 
            }
        }
        System.out.println("\n\n");
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
        System.out.println("        Thanks for using me! See you next time <3\n\n");
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
    }
}
