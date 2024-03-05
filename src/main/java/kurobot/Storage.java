package kurobot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Load previous tasks from the file and save any changes to the tasks in the file.
 */
public class Storage {

    private ArrayList<Task> oldTasks;
    private int taskNum = 0;

    public Storage() {
        oldTasks = new ArrayList<>();
    }

    /**
     * Read contents from the file and store the previous tasks into a task list.
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
                    char taskType = content.charAt(3);
                    char markStatus = content.charAt(6);
                    String taskDetails = content.substring(9);
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
     * Create a todo task with the given task name and marking.
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
     * Extract the task name and deadline.
     * Create a task type of deadline with the name, deadline and marking.
     *
     * @param details Strings containing task name and deadline.
     * @param mark X for marked data and white space for unmarked.
     */
    private void addPreviousDeadline(String details, char mark) {
        String[] phrases = details.split("\\(");
        String taskName = phrases[0];
        String by = phrases[1];
        String deadline = by.substring(by.indexOf(" ")+1, by.indexOf(")"));
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
     * Extract the task name and start and end timings.
     * Create a task type of deadline with the name, timings and marking.
     *
     * @param details Strings containing task name, start and end timings.
     * @param mark X for marked data and white space for unmarked.
     */
    private void addPreviousEvent(String details, char mark) {
        String[] phrases = details.split("\\(from: ");
        String taskName = phrases[0];
        String[] periods = phrases[1].split(" to: ");
        String from = periods[0];
        String to = periods[1].substring(0,periods[1].length()-1);
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
     * Overwrite the file with tasks in the given task list.
     *
     * @param newTasks Updated task list.
     * @throws IOException If failed to write to the file.
     */
    public void writeToFile(ArrayList<Task> newTasks) throws IOException {
        FileWriter fw = new FileWriter("./data/prevData.txt");
        for (Task task : newTasks){
            fw.write(newTasks.indexOf(task)+1 + "." + task.printTask() + System.lineSeparator());
        }
        fw.close();
    }
}
