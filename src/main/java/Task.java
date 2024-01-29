import java.util.Scanner;

public class Task {

    private String[] tasklist;
    private int taskcount;

    public Task() {
        tasklist = new String[100];
        taskcount = 0;
    }
    public void addTask(String task) {
        tasklist[taskcount] = task;
        taskcount += 1;
        System.out.println("added: " + task);
    }

    public void userCommand() {
        while(true) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.toLowerCase().equals("bye")) {  //exit dobby
                System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
                break;
            }
            addTask(command);
            //System.out.println("~~~~~~~~~~~~~~~~\n" + command);
        }
    }



}
