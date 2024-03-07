package kurobot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads previous tasks from the file and save any changes to the tasks in the file.
 */
public class Storage {

    private ArrayList<Task> oldTasks;
    private int taskNum = 0;
    private final int START_OF_TASK_TYPE = 3;
    private final int START_OF_MARKING = 6;
    private final int START_OF_CONTENT = 9;

    public Storage() {
        oldTasks = new ArrayList<>();
    }

    /**
     * Reads contents from the file and store the previous tasks into a task list.
     *
     * @return A task list that contains all previous tasks.
     * @throws FileNotFoundException If the file could not be found.
     */
    public ArrayList<Task> readFileContents() throws FileNotFoundException {
        try {
            File file = new File("./data/prevData.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner scan = new Scanner(file); // create a Scanner using the File as the source
                while (scan.hasNext()) {
                    String content = scan.nextLine();
                    char taskType = content.charAt(START_OF_TASK_TYPE);
                    char markStatus = content.charAt(START_OF_MARKING);
                    String taskDetails = content.substring(START_OF_CONTENT);
                    switch (Character.toString(taskType)){
                    case "T":
                        addPreviousTodo(taskDetails, markStatus);
                        break;
                    case "D":
                        addPreviousDeadline(taskDetails, markStatus);
                        break;
                    case"E":
                        addPreviousEvent(taskDetails, markStatus);
                        break;
                    default:
                        System.out.println("Something went wrong!");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return oldTasks;
    }

    public int getTaskNum() {
        return taskNum;
    }

    /**
     * Creates a todo task with the given task name and marking.
     *
     * @param details Task name.
     * @param mark X for marked data and white space for unmarked.
     */
    private void addPreviousTodo(String details, char mark) {
        Todo task;
        if (Character.toString(mark).equals("X")) {
            task = new Todo(details, true);
        } else {
            task = new Todo(details, false);
        }
        oldTasks.add(task);
        taskNum++;
    }

    /**
     * Extracts the task name and deadline.
     * Creates a task type of deadline with the name, deadline and marking.
     *
     * @param details Strings containing task name and deadline.
     * @param mark X for marked data and white space for unmarked.
     */
    private void addPreviousDeadline(String details, char mark) {
        String[] phrases = details.split("\\(");
        String taskName = phrases[0];
        String by = phrases[1];
        String deadline = by.substring(by.indexOf(" ") + 1, by.indexOf(")"));
        Deadline task;
        if (Character.toString(mark).equals("X")) {
            task = new Deadline(taskName, deadline, true);
        } else {
            task = new Deadline(taskName, deadline, false);
        }
        oldTasks.add(task);
        taskNum++;
    }

    /**
     * Extracts the task name and start and end timings.
     * Creates a task type of deadline with the name, timings and marking.
     *
     * @param details Strings containing task name, start and end timings.
     * @param mark X for marked data and white space for unmarked.
     */
    private void addPreviousEvent(String details, char mark) {
        String[] phrases = details.split("\\(from: ");
        String taskName = phrases[0];
        String[] periods = phrases[1].split(" to: ");
        String from = periods[0];
        String to = periods[1].substring(0,periods[1].length() - 1);
        Event task;
        if (Character.toString(mark).equals("X")) {
            task = new Event(taskName, from, to, true);
        } else {
            task = new Event(taskName, from, to, false);
        }
        oldTasks.add(task);
        taskNum++;
    }

    /**
     * Overwrites the file with tasks in the given task list.
     *
     * @param newTasks Updated task list.
     * @throws IOException If failed to write to the file.
     */
    public void writeToFile(ArrayList<Task> newTasks) throws IOException {
        FileWriter fw = new FileWriter("./data/prevData.txt");
        for (Task task : newTasks){
            fw.write(newTasks.indexOf(task) + 1 + "." + task.printTask() + System.lineSeparator());
        }
        fw.close();
    }
}
