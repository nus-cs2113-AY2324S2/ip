import java.util.Scanner;

public class Task {

    private String[] tasklist;
    private int taskcount;

    public Task() {
        tasklist = new String[100];
        taskcount = 0;
    }

    /**
     * Adds user input into taskList
     */
    public void addTask(String task) {
        tasklist[taskcount] = task;
        taskcount += 1;
        System.out.println("added: " + task);
    }
    /**
     * list out current tasks
     */
    public void listTask() {
        if (taskcount == 0) {
            System.out.println("Dobby has no tasks :(");
            return;
        }
        System.out.println("List\n~~~~~~~~~~~~~~~~");
        for (int i = 0 ; i < taskcount ; i += 1) {
            System.out.println("  " + (i+1) + ". " + tasklist[i]);
        }
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    public void userCommand() {
        while(true) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.toLowerCase().equals("bye")) {  //exit dobby
                System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
                break;
            } else if (command.toLowerCase().equals("list")) {
                listTask();
            } else {
                addTask(command);
            }
        }
    }



}
