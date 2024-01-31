import java.util.Scanner;

public class Task {

    private String[] tasklist;
    protected boolean[] taskstatus;
    private int taskcount;


    public Task() {
        this.tasklist = new String[100];
        this.taskstatus = new boolean[100];
        this.taskcount = 0;
    }

    /**
     * Adds user input into taskList
     */
    public void addTask(String task) {
        tasklist[taskcount] = task;
        taskstatus[taskcount] = false;
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
            System.out.println("  " + (i+1) + ". " + getStatusIcon(i) + " " + tasklist[i]);
        }
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    public String getStatusIcon(int taskindex) {
        return (taskstatus[taskindex] ? "[X]" : "[ ]" );
    }

    public void markTask(int taskindex) {
        if (taskindex < 1 || taskindex > taskcount) {
            System.out.println("Invalid number> Please try again");
        } else {
            taskstatus[taskindex-1] = true;
            System.out.println("OK, I've marked this task as done:");
            System.out.println("  " + getStatusIcon(taskindex - 1) + " " + tasklist[taskindex - 1]);
            System.out.println("~~~~~~~~~~~~~~~~");
        }
    }

    public void unmarkTask(int taskindex) {
        if (taskindex < 1 || taskindex > taskcount) {
            System.out.println("Invalid number> Please try again");
        } else {
            taskstatus[taskindex-1] = false;
            System.out.println("OK, I've marked this task as not done:");
            System.out.println("  " + getStatusIcon(taskindex - 1) + " " + tasklist[taskindex - 1]);
            System.out.println("~~~~~~~~~~~~~~~~");
        }
    }

    public void userCommand() {
        while(true) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine().toLowerCase();

            switch (command) {
            case "bye":
                System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
                return;
            case "list":
                listTask();
                break;
            default:
                if (command.startsWith("add")) {
                    addTask(command.substring(4).trim());
                } else if (command.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]);
                    markTask(taskIndex);
                } else if (command.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]);
                    unmarkTask(taskIndex);
                } else {
                    addTask(command);
                }
                break;
            }
//            switch (command) {
//            case "bye": System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
//                break;
//            case "list": listTask();
//            case command.starsWith("mark"):
//                //int taskIndex = Integer.parseInt(command.split(" ")[1]);
//                markTask(Integer.parseInt(command.split(" ")[1]));
//            case "unmark":
//                //int taskIndex = Integer.parseInt(command.split(" ")[1]);
//                unmarkTask(Integer.parseInt(command.split(" ")[1]));
//            default: addTask(co);
//
//            }
//            if (command.toLowerCase().equals("bye")) {  //exit dobby
//                System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
//                break;
//            }
//            if (command.toLowerCase().equals("list")) {
//                listTask();
//            } else {
//                addTask(command);
//            }
        }
    }



}
