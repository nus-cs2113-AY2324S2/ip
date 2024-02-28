package omoh.tasktypes;
import omoh.Omoh;

import omoh.customexceptions.EmptyTaskNumberException;
import omoh.customexceptions.EmptyTodoException;
import omoh.customexceptions.CorruptedFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

    public static void readFile() throws FileNotFoundException, CorruptedFileException, EmptyTodoException {
        //open file for reading
        File f = new File("data/output.txt");
        Scanner s = new Scanner(f);
        Task.initArray();
        //iteration required so that programme knows which task to mark as done
        int iteration = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            // Process each line (splitting by "|", for example)
            String[] parts = line.split("\\|"); // Split the line by "|"
            if (parts.length < 3 || parts.length > 5 ) {
                throw new CorruptedFileException();
            }
            processFileText(parts, iteration);
            iteration++;
        }
        s.close();
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


    //function creates directory for output.txt file if it is not present
    public static void createFileDirectory () {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs(); // This will create the necessary directories
        }
    }

    //creates output.txt file to store data
    public static void createOutputFile () {
        //code to create new file in data directory
        File f = new File("data/output.txt");
        try {
            Task.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (EmptyTodoException e) {
            System.out.println("Todo task empty");
        } catch (CorruptedFileException e) {
            System.out.println("Output.txt file format is wrong. File corrupted.");
        }
    }

    //processes the output.txt file. According to the text it adds tasks and marks tasks
    public static void processFileText (String[] parts, int iteration) throws EmptyTodoException {
        String command;
        switch (parts[0].trim().charAt(0)) {
        case 'T':
            command = "todo " + parts[2].trim();
            Todo.addTodo(command);
            break;
        case 'D':
            command = "deadline " + parts[2].trim() + " /by " + parts[3].trim();
            Deadline.addDeadline(command);
            break;
        case 'E':
            command = "event " + parts[2].trim() + " /from " + parts[3].trim() + " /to " + parts[4].trim();
            Event.addEvent(command);
            break;
        }

        if (parts[1].trim().equals("1")) {
            command = "mark " + iteration + 1;
            modifyDoneStateOrDelete(iteration + 1, command);
        }
    }

    //Method that prints all the tasks stored in myTaskList array
    public static void printAllTasks() {
        Omoh.printHorizontalLine();
        Task.getAllTasks();
        Omoh.printHorizontalLine();
    }
}


