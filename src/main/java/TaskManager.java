public class TaskManager {
    protected Task[] tasks;
    protected int numberOfTasks;
    protected static final int MAX_TASK_SIZE = 100;

    public TaskManager() {
        tasks = new Task[MAX_TASK_SIZE];
        numberOfTasks = 0;
    }

    public void addToDo(String taskName) throws JoeException {
        if (taskName.isEmpty()) {
            throw new JoeException();
        }
        if (numberOfTasks >= MAX_TASK_SIZE) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ToDo newToDo = new ToDo(taskName);
        tasks[numberOfTasks] = newToDo;
        numberOfTasks++;
        Printer.printTaskAddingMessage(newToDo.getTaskStatus(), numberOfTasks);
    }

    public void addDeadline(String taskName, String finishBy) {
        if (numberOfTasks >= MAX_TASK_SIZE) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Deadline newDeadline = new Deadline(taskName, finishBy);
        tasks[numberOfTasks] = newDeadline;
        numberOfTasks++;
        Printer.printTaskAddingMessage(newDeadline.getTaskStatus(), numberOfTasks);
    }

    public void addEvent(String taskName, String startDate, String endDate) {
        if (numberOfTasks >= MAX_TASK_SIZE) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Event newEvent = new Event(taskName, startDate, endDate);
        tasks[numberOfTasks] = newEvent;
        numberOfTasks++;
        Printer.printTaskAddingMessage(newEvent.getTaskStatus(), numberOfTasks);
    }

    public void listTasks() {
        Printer.printListMessage();
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + "." + tasks[i].getTaskStatus());
        }
        Printer.printHeaderLine();
    }

    public void toggleTaskMarkedStatus(int taskNumber, boolean isMark) throws JoeException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new JoeException();
        }
        tasks[taskNumber - 1].setDone(isMark);
        if (isMark) {
            Printer.printMarkMessage();
        } else {
            Printer.printUnmarkMessage();
        }
        System.out.println("  " + tasks[taskNumber - 1].getTaskStatus());
        Printer.printHeaderLine();
    }
}
