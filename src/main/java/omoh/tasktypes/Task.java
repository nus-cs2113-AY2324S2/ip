package omoh.tasktypes;
import omoh.Omoh;

import omoh.customexceptions.EmptyTaskNumberException;
import omoh.customexceptions.EmptyTodoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task {
    protected static Task[] tasks;
    public static int totalTasks = 0;
    protected String description;
    protected boolean isDone;

    protected String type;

    public static void initArray() {
        tasks = new Task[100];
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
            System.out.println(serialNumber + "." + tasks[i].toString());
            serialNumber += 1;
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("data/output.txt");
        for(int i = 0; i < totalTasks ; i++) {
            String taskType = tasks[i].type;
            int isMark = tasks[i].isDone ? 1 : 0;

            switch (taskType) {
            case "T":
                fw.write(taskType + " | " + isMark + " | " + tasks[i].description);
                break;
            case "D":
                Deadline deadline = (Deadline) tasks[i]; // Casting to Deadline
                fw.write(taskType + " | " + isMark + " | " + tasks[i].description + " | " + deadline.getBy());
                break;
            case "E":
                Event event = (Event) tasks[i]; //Casting to event
                fw.write(taskType + " | " + isMark + " | " + tasks[i].description +
                        " | " + event.getFrom() + " | " + event.getTo());
                break;
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public static void readFile() throws FileNotFoundException, EmptyTodoException {
        //open file for reading
        File f = new File("data/output.txt");
        Scanner s = new Scanner(f);
        Task.initArray();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            // Process each line (splitting by "|", for example)
            String[] parts = line.split("\\|"); // Split the line by "|"
            String command;

            if (parts[0].trim().startsWith("T")) {
                command = "todo " + parts[2].trim();
                Todo.addTodo(command);
            } else if (parts[0].trim().startsWith("D")) {
                command = "deadline " + parts[2].trim() + " /by " + parts[3].trim() ;
                Deadline.addDeadline(command);
            } else if (parts[0].trim().startsWith("E")) {
                command = "event " + parts[2].trim() + " /from " + parts[3].trim()
                        + " /to " + parts[4].trim();
                Event.addEvent(command);
            }
        }
        s.close();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //method that extracts the task number to mark or unmark
    public static int extractTaskNumber(String input) throws NumberFormatException, EmptyTaskNumberException {
        String keyword;
        if (input.startsWith("mark")) {
            keyword = "mark";
        } else {
            keyword = "unmark";
        }
        String numberString = input.substring(keyword.length()).trim();
        if (numberString.isEmpty()) {
            throw new EmptyTaskNumberException();
        }
        int taskNumber = Integer.parseInt(numberString);
        return taskNumber;
    }


    //method that modifies whether task is done or not done, depending on keyword mark or unmark detected
    public static void modifyDoneState(int taskNumber, String input) {
        //only executes if taskNumber is valid
        if (taskNumber != -1) {
            if (input.startsWith("mark")) {
                tasks[taskNumber - 1].isDone = true;
            } else {
                tasks[taskNumber - 1].isDone = false;
            }
        }
    }


    //method that prints out the task that has been marked done or unmarked
    public static void printMarkTask(int index, String input) {
        Omoh.printHorizontalLine();
        if (input.startsWith("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked his task as not done yet:");
        }
        System.out.println(tasks[index - 1].toString());
        Omoh.printHorizontalLine();
    }

    public static void addTask(String taskDescription) {
        tasks[totalTasks] = new Task(taskDescription);
        totalTasks++;
    }

    public static void printAddedTask() {
        Omoh.printHorizontalLine();
        System.out.println("added: " + tasks[totalTasks - 1].description);
        Omoh.printHorizontalLine();
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}


