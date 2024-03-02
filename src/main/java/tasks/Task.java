package tasks;

import java.util.ArrayList;

import exceptions.DuplicateUnmarkException;
import storage.Storage;
import java.io.IOException;
import exceptions.DuplicateMarkException;
import exceptions.EmptyTaskException;
import exceptions.DuplicateUnmarkException;

public class Task {
    public static int taskLength = 0;
    public static ArrayList<Task> list = new ArrayList<>();
    public static final String NEW_LINE = "____________________________________________________________\n";
    public String description;
    public boolean isDone;
    protected String taskType;

    public Task(String description, boolean taskDone) {
        this.description = description;
        this.isDone = taskDone;
    }

    //Create a new task
    public static void createTask(String taskType, boolean taskDone, String task, boolean quietLoad) {
        switch (taskType) {
        case "todo":
            list.add(new Todo(task, taskDone));
            break;
        case "deadline":
            list.add(new Deadline(task, taskDone));
            break;
        default:
            list.add(new Event(task, taskDone));
        }
        taskLength++;
        if (!quietLoad) {
            System.out.println(NEW_LINE + "Okay, I've added: " + task + "\n" + NEW_LINE);
        }
    }

    public static void deleteTask(int taskNumber) {
        Task task = list.get(taskNumber);
        list.remove(taskNumber);
        taskLength--;
        try {
            Storage.saveFile(list);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(NEW_LINE + "Okay, I've removed: " + task.description + "\n" + NEW_LINE);
    }

    //Handle different types of tasks
    public static void handleTasks(String input) throws EmptyTaskException {
        int index = input.indexOf(" ");
        String taskType = input.substring(0, index);
        String taskContent = input.substring(index);
        String taskFinal;

        switch (taskType) {
        case "deadline":
            try {
                //Empty deadline exception
                String deadlineContent = taskContent.split("/by ")[0];
                if (deadlineContent.trim().isEmpty()) {
                    throw new EmptyTaskException();
                } else {
                    String deadlineTiming = taskContent.split("/by ")[1];
                    taskFinal = taskContent.split("/")[0] + " (by: " + deadlineTiming + ")";
                    createTask(taskType, false, taskFinal, false);
                    Storage.saveFile(list);
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot parse deadline start date!\n"
                        + "Format: deadline [task] /by [time]");
            }
            catch (IOException e) {
                System.out.println("Error saving file" + e.getMessage());
            }
            break;

        case "event":
            try {
                //empty event exception
                String eventContent = taskContent.split("/from ")[0];
                if (eventContent.trim().isEmpty()) {
                    throw new EmptyTaskException();
                } else {
                    String eventTiming = taskContent.split("/from ")[1];
                    String eventFrom = eventTiming.split("/to ")[0];
                    String eventTo = eventTiming.split("/to ")[1];
                    taskFinal = taskContent.split("/")[0] + " (from: " + eventFrom + " to: " + eventTo + ")";
                    createTask(taskType, false, taskFinal, false);
                    Storage.saveFile(list);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot parse event start/end date!\n"
                        +  "Format: event [task] /from [start time] /to [end time]");
            } catch (IOException e) {
                System.out.println("Error saving file" + e.getMessage());
            }
            break;

        //case for T0D0
        default:
            if (taskContent.trim().isEmpty()) {
                throw new EmptyTaskException();
            } else {
                try {
                    createTask(taskType, false, taskContent, false);
                    Storage.saveFile(list);
                } catch (IOException e) {
                    System.out.println("Error saving file" + e.getMessage());
                }
            }
        }
    }

    //show current tasks
    public static void showTasks() {
        System.out.println(NEW_LINE);
        System.out.println("Your current tasks");
        for (int i = 0; i < taskLength; i++){
            System.out.print((i + 1) + ". ");
            System.out.println(list.get(i).getTask());
        }
        System.out.print(NEW_LINE);
    }

    public static void mark(int taskNumber) throws DuplicateMarkException {
        if (list.get(taskNumber - 1).isDone) {
            throw new DuplicateMarkException();
        } else {
            list.get(taskNumber - 1).markAsDone();
            try {
                Storage.saveFile(list);
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Good job! I've marked task " + taskNumber + " as done");
        }
    }

    public static void unMark(int taskNumber) throws DuplicateUnmarkException{
        if (!list.get(taskNumber - 1).isDone) {
            throw new DuplicateUnmarkException();
        } else {
            list.get(taskNumber - 1).markAsDone();
            try {
                Storage.saveFile(list);
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Okay! I've marked task " + taskNumber + " as not done");
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getTask() {
        String taskType;
        if (this.getClass().toString().equals("class tasks.Todo")) {
            taskType = "[T]";
        } else if (this.getClass().toString().equals("class tasks.Deadline")) {
            taskType = "[D]";
        } else {
            taskType = "[E]";
        }
        this.taskType = taskType;
        return (taskType + "[" + (isDone ? "X" : " ") + "]" + this.description);
    }

    public String getTime() {
        String output = "";
        try {
        if (this.taskType.equals("[D]")) {
            String initial = this.getTask().split("by:")[1];
            output = initial.split("\\)")[0];
        } else if (this.taskType.equals("[E]")) {
            String initialFrom = (this.getTask().split("from:")[1]).split("to:")[0];
            String initialTo = (this.getTask().split("to:")[1]).split("\\)")[0];
            output = initialFrom.trim() + " to: " + initialTo.trim();
        }
        } catch (Exception e) {
            System.out.println("Error loading data:" + e.getMessage());
        }
        return output;
    }

}
