package nick.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import nick.NickException;
import nick.task.Task;
import nick.task.Todo;
import nick.task.Deadline;
import nick.task.Event;

/**
 * Creates a storage object which deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    File f;
    Scanner s;
    String filePath;
    ArrayList<Task> tasks = new ArrayList<>();

    public static final int TO_OFFSET_IDX = 4;
    public static final int FROM_OFFSET_IDX = 6;
    public static final int DEADLINE_OFFSET_IDX = 4;
    public static final String FORMAT_LINES = "____________________________________________________________";

    /**
     * Initialize a new file and scanner object.
     *
     * @param filePath Filepath of file to be saved or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all commands from the file.
     *
     * @return ArrayList tasks.
     * @throws NickException Exception object.
     */
    public ArrayList<Task> load() throws NickException {
        try {
            f = new File(filePath);
            s = new Scanner(f);
            while (s.hasNext()) {
                String lineData = s.nextLine();
                String taskType = lineData.split(" ")[0];
                loadData(lineData, taskType, tasks);
            }
        } catch (FileNotFoundException e) {
            System.out.println("I am unable to load any data as data/nick.txt does not exist!");
        }
        return tasks;
    }

    /**
     * Loads all data command from input file at file path.
     *
     * @param command Full input command from user.
     * @param taskType Task command.
     * @param userTasks ArrayList of task objects.
     */
    public static void loadData(String command, String taskType, ArrayList<Task> userTasks) {
        Task task;
        String taskName;
        int taskDescriptionIndex = taskType.length() + 7;
        int taskDescriptionEndIndex = command.indexOf("/") - 1;
        boolean taskDone = command.contains("| 1 |");

        switch (taskType) {
        case "todo":
            try {
                taskName = command.substring(taskDescriptionIndex);
                task = new Todo(taskName, taskDone);
                userTasks.add(task);
                break;
            }
            catch (StringIndexOutOfBoundsException exception) {
                System.out.println(FORMAT_LINES + System.lineSeparator() +
                        "I cannot add a Todo task which has no description. " + System.lineSeparator() +
                        "Try adding the task description after specifying the Todo command!");
                break;
            }
        case "deadline":
            int deadlineIndex = command.indexOf("/by") + DEADLINE_OFFSET_IDX;
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String deadline = command.substring(deadlineIndex);
            task = new Deadline(taskName, deadline, taskDone);
            userTasks.add(task);
            break;
        case "event":
            int fromIndex = command.indexOf("/from");
            int toIndex = command.indexOf("/to");
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String from = command.substring(fromIndex + FROM_OFFSET_IDX, toIndex - 1);
            String to = command.substring(toIndex + TO_OFFSET_IDX);
            task = new Event(taskName, from, to, taskDone);
            userTasks.add(task);
            break;
        default:
            try {
                throw new NickException();
            }
            catch (NickException exception) {
                System.out.println(FORMAT_LINES + System.lineSeparator() +
                        "Invalid command! Please try again.");
            }
        }
    }

    /**
     * Save all data and overwrites input file at file path.
     *
     * @param userTasks Arraylist of task objects.
     */
    public static void saveData(ArrayList<Task> userTasks) {
        try {
            new FileWriter("data/nick.txt", false).close();
            FileWriter fw = new FileWriter("data/nick.txt", true);

            for (int i = 0; i < userTasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + userTasks.get(i));
                String done = (userTasks.get(i).getDoneStatus()) ? "1" : "0";
                if (userTasks.get(i) instanceof Todo) {
                    fw.write("todo " + "| " + done + " | " + userTasks.get(i).description + "\n");
                }
                else if (userTasks.get(i) instanceof Deadline) {
                    fw.write("deadline " + "| " + done + " | " + userTasks.get(i).description + " /by "
                            + ((Deadline) userTasks.get(i)).getDeadline() + "\n");
                }
                else if (userTasks.get(i) instanceof Event) {
                    fw.write("event " + "| " + done + " | " + userTasks.get(i).description + " /from "
                            + ((Event) userTasks.get(i)).getFrom() + " /to "
                            + ((Event) userTasks.get(i)).getTo() + "\n");
                }
            }
            fw.close();
        }
        catch (IOException ignored) {
        }
    }
}
