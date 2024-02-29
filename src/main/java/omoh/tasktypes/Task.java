package omoh.tasktypes;
import omoh.Omoh;

import omoh.customexceptions.EmptyTaskNumberException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Task {
    protected static ArrayList<Task> tasks;
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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static void getAllTasks() {
        System.out.println("Here are the tasks in your list:");
        int serialNumber = 1;
        for (int i = 0; i < totalTasks; i++) {
            System.out.println(serialNumber + "." + tasks.get(i).toString());
            serialNumber += 1;
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("data/output.txt");
        for(int i = 0; i < totalTasks ; i++) {
            String taskType = tasks.get(i).type;

            int isMark = tasks.get(i).isDone ? 1 : 0;

            switch (taskType) {
            case "T":
                fw.write(taskType + " | " + isMark + " | " + tasks.get(i).description);
                break;
            case "D":
                Deadline deadline = (Deadline) tasks.get(i); // Casting to Deadline
                fw.write(taskType + " | " + isMark + " | " + tasks.get(i).description + " | " + deadline.getBy());
                break;
            case "E":
                Event event = (Event) tasks.get(i); //Casting to event
                fw.write(taskType + " | " + isMark + " | " + tasks.get(i).description +
                        " | " + event.getFrom() + " | " + event.getTo());
                break;
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    //method that modifies whether task is done or not done, depending on keyword mark or unmark detected
    public static void modifyDoneStateOrDelete(int taskNumber, String input) throws IndexOutOfBoundsException {
            if (input.startsWith("mark")) {
                tasks.get(taskNumber - 1).isDone = true;
                printMarkTask(taskNumber, input);
            } else if (input.startsWith("unmark")){
                tasks.get(taskNumber - 1).isDone = false;
                printMarkTask(taskNumber, input);
            } else if (input.startsWith("delete")){
                if (totalTasks == 0 || totalTasks < taskNumber) {
                    throw new IndexOutOfBoundsException();
                }
                printDeleteTask(taskNumber);
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
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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

    //Method that prints all the tasks stored in myTaskList array
    public static void printAllTasks() {
        Omoh.printHorizontalLine();
        Task.getAllTasks();
        Omoh.printHorizontalLine();
    }
    //finds matching tasks based on user input
    public static void findMatchingTasks (String line) {
        try {
            String keyword = Parser.extractKeyword(line);
            ArrayList<String> findResult = searchTasks(keyword);
            printFindResults(findResult);
        } catch (EmptyFindException e) {
            e.printStackTrace();
        }
    }
}


