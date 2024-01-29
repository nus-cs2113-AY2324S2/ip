import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;

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

        public void markAsDone() { //need to choose which task to mark as done
            isDone = true;
        }

        public void markAsNotDone() { isDone = false; }

        public String getStatusIcon() { //if it is done mark as [X]
            return (isDone ? "[X]" : "[ ]");
        }
    }

    private static ArrayList<Task> taskList = new ArrayList<>(); //array of Task instances

    public static void getTask() {
        String taskDescription;
        Scanner in = new Scanner(System.in);
        System.out.println("\tWhat do you want to do?: ");
        taskDescription = in.nextLine();
        checkKeywords(taskDescription); //check if its un mark/mark/exit/list
    }

    public static void addToList(String taskName) { //only add to list if it is a valid task
        Task newTask = new Task(taskName);
        taskList.add(newTask);
    }

    public static void checkKeywords (String task) { //going to input task description from getTask here
        final String exitCode = "bye";
        final String displayCode = "list";
        final String markCommand = "mark";
        final String unMarkCommand = "unmark";
        if (task.startsWith(markCommand) || task.startsWith(unMarkCommand)) {
            markTask(task);
            getTask();
        } else {
            switch (task) {
                case exitCode:
                    System.out.println("I hope you complete them!");
                    System.exit(0);
                    break;
                case displayCode:
                    listTask();
                    getTask();
                    break;
                default:
                    displayTask(task);
                    getTask();
                    break;
            }
        }
    }

    public static void listTask() { //using the array to list the tasks
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i); //instance of task in the display list
            String taskName = task.getDescription();
            String status = task.getStatusIcon(); //getting the status from the instance
            System.out.println((i + 1) + ". " + status + " " + taskName);
        }
    }

    public static void displayTask(String task) {
        addToList(task);
        System.out.println("_____________");
        System.out.println("\tadded: " + task);
        System.out.println("_____________");
    }

    public static void markTask (String tasks) {
        String[] task = tasks.split(" ");
        String taskIndex = task[1]; //getting the task to mark or un mark
        int index = Integer.parseInt(taskIndex) - 1;
        Task taskToMark = taskList.get(index);
        String taskName = taskToMark.getDescription();
        if (task[0].equals("mark")) {
            taskToMark.markAsDone();
            System.out.println("Ok! One task down!");
        } else {
            taskToMark.markAsNotDone();
            System.out.println("Oh no! More tasks!");
        }
        String status = taskToMark.getStatusIcon();
        System.out.println(status + " " + taskName);
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

        Gab.getTask();
        //Gab.checkKeywords(task);
    }
}


