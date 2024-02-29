package omoh.tasktypes;
import omoh.Omoh;

import omoh.Parser;
import omoh.customexceptions.EmptyFindException;
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

    /**
     * Initializes the tasks ArrayList.
     * This method is called once in the main method to initialize the tasks ArrayList.
     */
    public static void initArray() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "T";
    }

    /**
     * Returns the status icon of the task (X for done, empty for not done).
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets all tasks and prints them.
     */
    public static void getAllTasks() {
        System.out.println("Here are the tasks in your list:");
        int serialNumber = 1;
        for (int i = 0; i < totalTasks; i++) {
            System.out.println(serialNumber + "." + tasks.get(i).toString());
            serialNumber += 1;
        }
    }

    /**
     * Writes tasks to the output file.
     * Format of tasks is shown below
     * taskType | isMark | description | etc
     *
     * @throws IOException if an I/O error occurs while writing to the file.
     */
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

    /**
     * Modifies the status of a task (done or not done) or deletes a task.
     *
     * @param taskNumber The number of the task to be modified or deleted.
     * @param input The user input specifying whether to mark, unmark, or delete the task.
     * @throws IndexOutOfBoundsException if the task number is invalid.
     */
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

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param index The index of the task that is to be deleted.
     */
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

    /**
     * Prints a message indicating that a task has been marked as done or not done.
     *
     * @param index The index of the task that has been marked.
     * @param input The user input specifying whether to mark the task as done or not done.
     */
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

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task, including its status icon and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Prints all tasks stored in the tasks ArrayList.
     */
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

    public static ArrayList<String> searchTasks(String keyword) {
        ArrayList<String> findResult = new ArrayList<>();
        for (int i = 0; i < totalTasks; i++) {
            if (tasks.get(i).description.contains(keyword)) {
                findResult.add(tasks.get(i).toString());
            }
        }
        return findResult;
    }

    public static void printFindResults(ArrayList<String> findResult) {
        Omoh.printHorizontalLine();
        if(findResult.isEmpty()) {
            System.out.println("There were no matching tasks found :(");
            Omoh.printHorizontalLine();
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        int serialNumber = 1;
        for (String s : findResult) {
            System.out.println(serialNumber + "." + s);
            serialNumber++;
        }
        Omoh.printHorizontalLine();
    }
}


