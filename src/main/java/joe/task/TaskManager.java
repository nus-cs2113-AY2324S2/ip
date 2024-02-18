package joe.task;

import joe.JoeException;
import joe.util.Printer;
import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;
    protected int numberOfTasks;

    public TaskManager() {
        tasks = new ArrayList<>();
        numberOfTasks = 0;
    }

    public void addToDo(String taskName) throws JoeException {
        if (taskName.isEmpty()) {
            throw new JoeException();
        }
        ToDo newToDo = new ToDo(taskName);
        tasks.add(newToDo);
        numberOfTasks++;
        Printer.printTaskAddingMessage(newToDo.getTaskStatus(), numberOfTasks);
    }

    public void addDeadline(String taskName, String finishBy) throws JoeException {
        if (taskName.isEmpty() || finishBy.isEmpty()) {
            throw new JoeException();
        }
        Deadline newDeadline = new Deadline(taskName, finishBy);
        tasks.add(newDeadline);
        numberOfTasks++;
        Printer.printTaskAddingMessage(newDeadline.getTaskStatus(), numberOfTasks);
    }

    public void addEvent(String taskName, String startDate, String endDate) throws JoeException {
        if (taskName.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            throw new JoeException();
        }
        Event newEvent = new Event(taskName, startDate, endDate);
        tasks.add(newEvent);
        numberOfTasks++;
        Printer.printTaskAddingMessage(newEvent.getTaskStatus(), numberOfTasks);
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
    }
}
