import java.util.Scanner;
import java.util.ArrayList;

public class Gab {

    public static class Task { //each task represent one instance of this class
        private String description;
        private boolean isDone;

        public Task (String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getDescription() {
            return description;
        }

        public void markAsDone() {
            isDone = true;
        }

        public String getStatusIcon() { //if it is done mark as [X]
            return (isDone ? "[X]" : "[ ]");
        }
    }

    private static ArrayList<Task> taskList = new ArrayList<>(); //array of Task instances

    public static Task getTask() {
        String taskDescription;
        Scanner in = new Scanner(System.in);
        System.out.println("\tEnter your new task: ");
        taskDescription = in.nextLine();
        checkKeywords(taskDescription);
        Task newTask = new Task(taskDescription);
        taskList.add(newTask); //adding the instances of the new task to the array
        getTask();
        return newTask; //create new instance of the task class
    }

    public static void checkKeywords (String task) {
        final String exitCode = "bye";
        final String displayCode = "list";
        switch (task) {
            case exitCode:
                System.out.println("I hope you complete them!");
                System.exit(0);
                break;
            case displayCode:
                listTask();
                break;
            default:
                displayTask(task);
                break;
        }
    }



    public static void listTask() { //using the array to list the tasks
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i); //instance of task in the display list
            String taskName = task.getDescription();
            String status = task.getStatusIcon(); //getting the status from the instance
            System.out.println((i + 1) + ". " + status + taskName);
        }
        getTask();
    }

    public static void displayTask(String task) {
        //String exitCode = "Bye";
        System.out.println("_____________");
        System.out.println("\tadded: " + task);
        System.out.println("_____________");
        //checkKeywords();
    }


    public static void main(String[] args) {
        String logo =
                          "  _____           __ \n"
                        + "/  ____|         |  |\n"
                        + "|  |  __   ____  |  |__\n"
                        + "|  | |_  |/  _   |  -   \\ \n"
                        + "|  |__|  |  (_|  | |_)   |\n"
                        + "\\_______ |__ ,_ |_.___ /\n";

        System.out.println(logo);
        System.out.println("\tI am Gab the Bot! Nice to meet you!");
        System.out.println("\tAnything I can help you with?");


        Task task = Gab.getTask();
        //Gab.checkKeywords(task);

    }

}


