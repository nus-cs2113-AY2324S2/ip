package joe.task;

import joe.JoeException;
import joe.util.FileManager;
import joe.util.InputParser;
import joe.util.Printer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;
    protected int numberOfTasks;

    public TaskManager() {
        tasks = new ArrayList<>();
        numberOfTasks = 0;
    }

    public void addTask(Task t) {
        tasks.add(t);
        numberOfTasks++;
    }

    public void addTask(TaskType type, String message) throws JoeException {
        if (message.isEmpty()) {
            throw new JoeException();
        }

        Task t;
        switch (type) {
        case TODO:
            t = new ToDo(message);
            break;
        case DEADLINE:
            String deadlineName = InputParser.getTaskName(message);
            LocalDateTime deadlineTime = InputParser.getDeadlineTime(message);
//            if (deadlineName.isEmpty() || deadlineTime.isEmpty()) {
//                throw new JoeException();
//            }
            t = new Deadline(deadlineName, deadlineTime);
            break;
        case EVENT:
            String eventName = InputParser.getTaskName(message);
            String[] eventDuration = InputParser.getEventTime(message);
            if (eventName.isEmpty() || eventDuration[0].isEmpty() || eventDuration[1].isEmpty()) {
                throw new JoeException();
            }
            t = new Event(eventName, eventDuration[0], eventDuration[1]);
            break;
        default:
            throw new JoeException();
        }

        tasks.add(t);
        numberOfTasks++;
        Printer.printTaskAddingMessage(t.getTaskStatus(), numberOfTasks);

        saveList();
    }

    public void deleteTask(int taskNumber) throws JoeException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new JoeException();
        }
        Printer.printDeleteMessage();
        System.out.println("  " + tasks.get(taskNumber - 1).getTaskStatus());
        tasks.remove(taskNumber - 1);
        numberOfTasks--;
        Printer.printNumOfTasks(numberOfTasks);

        saveList();
    }

    public void listTasks() {
        Printer.printListMessage();
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).getTaskStatus());
        }
        Printer.printHeaderLine();
    }

    public void toggleTaskMarkedStatus(int taskNumber, boolean isMark) throws JoeException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new JoeException();
        }
        tasks.get(taskNumber - 1).setDone(isMark);
        if (isMark) {
            Printer.printMarkMessage();
        } else {
            Printer.printUnmarkMessage();
        }
        System.out.println("  " + tasks.get(taskNumber - 1).getTaskStatus());
        Printer.printHeaderLine();

        saveList();
    }

    protected void saveList() {
        try {
            FileManager.saveData(tasks);
        } catch (IOException e) {
            Printer.printSaveError();
        }
    }
}
