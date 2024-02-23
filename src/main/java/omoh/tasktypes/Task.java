package omoh.tasktypes;
import omoh.Omoh;

import omoh.customexceptions.EmptyTaskNumberException;

import java.util.ArrayList;

public class Task {
    public static ArrayList<Task> tasks;
    public static int totalTasks = 0;
    protected String description;
    protected boolean isDone;

    protected String type;

    //initialise the ArrayList when called. This function is called in main once
    public static void initArray() {
        tasks = new ArrayList<>();
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "T";
    }


    public static void getAllTasks() {
        System.out.println("Here are the tasks in your list:");
        int serialNumber = 1;
        for (int i = 0; i < totalTasks; i++) {
            System.out.println(serialNumber + "." + tasks.get(i).toString());
            serialNumber += 1;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //method that extracts the task number to mark or unmark
    public static int extractTaskNumber(String input) throws NumberFormatException, EmptyTaskNumberException {
        String keyword;
        if (input.startsWith("mark")) {
            keyword = "mark";
        } else if (input.startsWith("unmark")) {
            keyword = "unmark";
        } else {
            keyword = "delete";
        }
        String numberString = input.substring(keyword.length()).trim();
        if (numberString.isEmpty()) {
            throw new EmptyTaskNumberException();
        }
        int taskNumber = Integer.parseInt(numberString);
        return taskNumber;
    }


    //method that modifies whether task is done or not done, depending on keyword mark or unmark detected
    public static void modifyDoneStateOrDelete(int taskNumber, String input) throws IndexOutOfBoundsException {
        //only executes if taskNumber is valid
        if (taskNumber != -1) {
            if (input.startsWith("mark")) {
                tasks.get(taskNumber - 1).isDone = true;
                printMarkTask(taskNumber, input);
            } else if (input.startsWith("unmark")){
                tasks.get(taskNumber - 1).isDone = false;
                printMarkTask(taskNumber, input);
            } else {
                if (totalTasks == 0 || totalTasks < taskNumber) {
                    throw new IndexOutOfBoundsException();
                }
                printDeleteTask(taskNumber);
            }
        }
    }

    //method that prints out the task that was deleted
    public static void printDeleteTask(int index) {
        //need to print out task that we deleted before we actually remove it because
        //once we remove th task, we can't retrieve it anymore
        Omoh.printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        totalTasks--;
        Omoh.printHorizontalLine();
    }

    //method that prints out the task that has been marked done or unmarked
    public static void printMarkTask(int index, String input) {
        Omoh.printHorizontalLine();
        if (input.startsWith("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index - 1).toString());
        } else {
            System.out.println("OK, I've marked his task as not done yet:");
            System.out.println(tasks.get(index - 1).toString());
        }
        Omoh.printHorizontalLine();
    }

    public static void addTask(String taskDescription) {
        tasks.add(new Task(taskDescription));
        totalTasks++;
    }

    public static void printAddedTask() {
        Omoh.printHorizontalLine();
        System.out.println("added: " + tasks.get(totalTasks - 1).description);
        Omoh.printHorizontalLine();
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}


