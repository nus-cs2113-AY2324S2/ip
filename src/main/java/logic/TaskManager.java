package logic;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import exceptions.DeadlineNoByDateTimeException;
import exceptions.EventNoFromDateTimeException;
import exceptions.EventNoToDateTimeException;
import exceptions.EventToBeforeFromException;
import exceptions.InputIndexOutOfBoundsException;
import exceptions.InvalidInputException;
import exceptions.TaskNoNameException;
import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

public class TaskManager {
    public static final int TODO_LENGTH= 5;
    public static final int DEADLINE_LENGTH = 9;
    public static final int EVENT_LENGTH = 6;
    public static final int BY_LENGTH = 4;
    public static final int FROM_LENGTH = 6;
    public static final int TO_LENGTH = 4;
    private int currIndex;
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
        this.currIndex = 0;
    }

    public void addTask(String taskToAdd) throws Exception {
        String[] taskAsArray = taskToAdd.split(" ");
        if (taskAsArray.length == 1) {
            throw new TaskNoNameException();
        }
        String taskType = taskAsArray[0];
        switch (taskType) {
        case "todo":
            processToDo(taskToAdd);
            break;
        case "deadline":
            try {
                processDeadline(taskToAdd);
            } catch (DeadlineNoByDateTimeException e) {
                throw new DeadlineNoByDateTimeException();
            }
            break;
        case "event":
            try {
                processEvent(taskToAdd);
            } catch (EventNoFromDateTimeException e) {
                throw new EventNoFromDateTimeException();
            } catch (EventNoToDateTimeException e) {
                throw new EventNoToDateTimeException();
            } catch (EventToBeforeFromException e) {
                throw new EventToBeforeFromException();
            }
            break;
        default:
            throw new InvalidInputException();
        }
        printAndIncrementAfterAddTask();
        saveData();
    }
    
    private void processToDo(String taskToAdd) {
        String taskName;
        taskName = taskToAdd.substring(TODO_LENGTH);
        tasks.add(new ToDo(taskName));
    }

    private void processDeadline(String taskToAdd) throws Exception {
        if (!(taskToAdd.contains("/by "))) {
            throw new DeadlineNoByDateTimeException();
        }
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        if (firstBackslashIndex == DEADLINE_LENGTH) {
            // occurs when task is not given a name
            throw new TaskNoNameException();
        }
        taskName = taskToAdd.substring(DEADLINE_LENGTH, firstBackslashIndex - 1);
        int byWhenIndex = firstBackslashIndex + BY_LENGTH;
        String byWhen = taskToAdd.substring(byWhenIndex);
        tasks.add(new Deadline(taskName, byWhen));
    }

    private void processEvent(String taskToAdd) throws Exception {
        if (!(taskToAdd.contains("/from "))) {
            throw new EventNoFromDateTimeException();
        }
        if (!(taskToAdd.contains("/to "))) {
            throw new EventNoToDateTimeException();
        }
        if (taskToAdd.indexOf("/to ") < taskToAdd.indexOf("/from ")) {
            throw new EventToBeforeFromException();
        }
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        if (firstBackslashIndex == EVENT_LENGTH) {
            // occurs when task is not given a name
            throw new TaskNoNameException();
        }
        taskName = taskToAdd.substring(EVENT_LENGTH, firstBackslashIndex - 1);
        int fromWhenIndex = firstBackslashIndex + FROM_LENGTH;
        int secondBackslashIndex = taskToAdd.indexOf("/", fromWhenIndex + 1);
        int toWhenIndex = secondBackslashIndex + TO_LENGTH;
        String fromWhen = taskToAdd.substring(fromWhenIndex, secondBackslashIndex - 1);
        String toWhen = taskToAdd.substring(toWhenIndex);
        tasks.add(new Event(taskName, fromWhen, toWhen));
    }
    
    private void printAndIncrementAfterAddTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(currIndex));
        System.out.println("Now you have " + (currIndex+1) + " tasks in the list");
        currIndex++;
    }
    
    public void markTask(int taskIndex, boolean isDone) throws Exception {
        if (taskIndex < 0 || taskIndex >= currIndex) {
            throw new InputIndexOutOfBoundsException();
        }
        Task targetTask = tasks.get(taskIndex);
        if (isDone) {
            targetTask.markDone();
            System.out.println("Nice! I've marked this Task as done:");
            System.out.println(targetTask);
        } else {
            targetTask.markNotDone();
            System.out.println("Ok, I've marked this Task as not done yet:");
            System.out.println(targetTask);
        }
        saveData();
    }

    public void listTasks() {
        for (int i = 0; i < currIndex; i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    public void deleteTask(int deleteIndex) throws Exception {
        if (deleteIndex < 0 || deleteIndex >= currIndex) {
            throw new InputIndexOutOfBoundsException();
        }
        System.out.println("Okay. I've removed this task:");
        System.out.println(tasks.get(deleteIndex));
        tasks.remove(deleteIndex);
        System.out.println("Now you have " + (currIndex - 1) + " tasks in the list");
        currIndex--;
    }

    public void loadData() {
        File dataFile = new File("./data/dor.txt");
        Scanner s = null;
        try {
            s = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Could not load dor.txt!");
        }
        while (s.hasNext()) {
            String data = s.nextLine();
            String taskType = data.substring(0, 1);
            boolean taskDoneStatus = processTaskDoneStatus(data);
            String taskName;
            switch (taskType) {
            case "T":
                loadToDo(data, taskDoneStatus);
                break;
            case "D":
                loadDeadline(data, taskDoneStatus);
                break;
            case "E":
                loadEvent(data, taskDoneStatus);
                break;
            }
            currIndex++;
        }
        System.out.println("Successfully loaded data!");
        System.out.println("Current number of tasks: " + currIndex);
    }

    private boolean processTaskDoneStatus(String data) {
        boolean taskDoneStatus;
        if (data.substring(3, 4).equals("1")){
            taskDoneStatus = true;
        } else {
            taskDoneStatus = false;
        }
        return taskDoneStatus;
    }

    private void loadToDo(String data, boolean taskDoneStatus) {
        String taskName = data.substring(6);
        tasks.add(new ToDo(taskName, taskDoneStatus));
    }

    private void loadDeadline(String data, boolean taskDoneStatus) {
        int commaAfterTaskNameIndex = data.indexOf(',', 6);
        String taskName = data.substring(6, commaAfterTaskNameIndex);
        String byWhen = data.substring(commaAfterTaskNameIndex + 2);
        tasks.add(new Deadline(taskName, taskDoneStatus, byWhen));
    }

    private void loadEvent(String data, boolean taskDoneStatus) {
        int commaAfterTaskNameIndex = data.indexOf(',', 6);
        String taskName = data.substring(6, commaAfterTaskNameIndex);
        int commaAfterFromDateTimeIndex = data.indexOf(",", commaAfterTaskNameIndex + 1);
        String fromWhen = data.substring(commaAfterTaskNameIndex + 2, commaAfterFromDateTimeIndex - 1);
        String toWhen = data.substring(commaAfterFromDateTimeIndex + 2);
        tasks.add(new Event(taskName, taskDoneStatus, fromWhen, toWhen));
    }

    public void saveData() throws IOException {
        FileWriter fw = new FileWriter("./data/dor.txt");
        for (int i = 0; i < currIndex; i++) {
            Task currTask = tasks.get(i);
            String data = processData(currTask);
            fw.write(data + System.lineSeparator());
        }
        fw.close();
    }

    private String processData(Task currTask) {
        String data;
        String doneStatusAlt;
        if (currTask.getDoneStatus().equals("X")) {
            doneStatusAlt = "1";
        } else {
            doneStatusAlt = "0";
        }
        data = currTask.getType() + ", " + doneStatusAlt + ", " + currTask.getName();
        if (currTask.getType().equals("D")) {
            data = data + ", " + appendByDateTimeToData((Deadline) currTask);
        } else if (currTask.getType().equals("E")) {
            data = data + ", " + appendFromToDateTimeToData((Event) currTask);
        }
        return data;
    }

    public String appendByDateTimeToData(Deadline data) {
        return data.getBy();
    }

    public String appendFromToDateTimeToData(Event data) {
        return (data.getFrom() + ", " + data.getTo());
    }
}
