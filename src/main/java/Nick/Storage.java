package Nick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import Nick.task.Task;
import Nick.task.Todo;
import Nick.task.Deadline;
import Nick.task.Event;

public class Storage {
    File f;
    Scanner s;
    ArrayList<Task> tasks = new ArrayList<>();

    public static final int TO_OFFSET_IDX = 4;
    public static final int FROM_OFFSET_IDX = 6;
    public static final int DEADLINE_OFFSET_IDX = 4;
    public static final String FORMAT_LINES = "____________________________________________________________";

    public Storage(String filePath) {
        try {
            f = new File(filePath);
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<Task> load() throws NickException {
        while (s.hasNext()) {
            String lineData = s.nextLine();
            String taskType = lineData.split(" ")[0];
            loadData(lineData, taskType, tasks);
        }
        return tasks;
    }

    public static void loadData(String command, String taskType, ArrayList<Task> userTasks) {
        Task task;
        String taskName;
        int taskDescriptionIndex = taskType.length() + 7;
        int taskDescriptionEndIndex = command.indexOf("/") - 1;
        boolean taskDone = command.contains("1");

        switch (taskType) {
        case "todo":
            try {
                taskName = command.substring(taskDescriptionIndex);
                task = new Todo(taskName, taskDone);
                userTasks.add(task);
                //taskCount++;
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
            //taskCount++;
            break;
        case "event":
            int fromIndex = command.indexOf("/from");
            int toIndex = command.indexOf("/to");
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String from = command.substring(fromIndex + FROM_OFFSET_IDX, toIndex - 1);
            String to = command.substring(toIndex + TO_OFFSET_IDX);
            task = new Event(taskName, from, to, taskDone);
            userTasks.add(task);
            //taskCount++;
            break;
        default:
            try {
                throw new NickException();
            }
            catch (NickException exception) {
                System.out.println(FORMAT_LINES + System.lineSeparator() +
                        "Invalid Nick.command!!! Please try again.");
            }
        }
    }

    public static void saveData(ArrayList<Task> userTasks) {
        try {
            new FileWriter("data/nick.txt", false).close();
            FileWriter fw = new FileWriter("data/nick.txt", true);
            for (int i = 0; i < userTasks.size(); i++) {
                System.out.println("\t" + Integer.toString(i + 1) + "." + userTasks.get(i));
                String done = (userTasks.get(i).getDoneStatus()) ? "1" : "0";
                if (userTasks.get(i) instanceof Todo) {
                    fw.write("todo " + "| " + done + " | " + userTasks.get(i).description + "\n");
                }
                else if (userTasks.get(i) instanceof Deadline) {
                    fw.write("deadline " + "| " + done + " | " + userTasks.get(i).description + " /by " + ((Deadline) userTasks.get(i)).getDeadline() + "\n");
                }
                else if (userTasks.get(i) instanceof Event) {
                    fw.write("event " + "| " + done + " | " + userTasks.get(i).description + " /from " + ((Event) userTasks.get(i)).getFrom() + " /to " + ((Event) userTasks.get(i)).getTo() + "\n");
                }
            }
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
