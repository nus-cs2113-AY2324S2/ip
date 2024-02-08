public class TaskManager {
    private static final int INDEX_COMMAND_TYPE = 0;
    private static final int INDEX_DESCRIPTION = 1;
    private static final int INDEX_DEADLINE = 2;
    private static final int INDEX_FROM_DATE = 2;
    private static final int INDEX_TO_DATE = 3;

    protected int noOfTasks;
    protected Task[] tasks;

    public TaskManager() {
        this.tasks = new Task[100];   //initialize an array of Tasks, to act as a list
        noOfTasks = 0;
    }
    public void executeCommand(String[] commandTypeAndParams) {
        String commandType = commandTypeAndParams[INDEX_COMMAND_TYPE];

        switch (commandType) {
        case ("mark"):
            int markIndex = Integer.parseInt(commandTypeAndParams[INDEX_DESCRIPTION]) - 1;
            tasks[markIndex].markAsDone();
            ConsolePrint.printMarkStatement(tasks[markIndex]);
            break;
        case ("unmark"):
            int unmarkIndex = Integer.parseInt(commandTypeAndParams[INDEX_DESCRIPTION]) - 1;
            tasks[unmarkIndex].markAsNotDone();
            ConsolePrint.printUnmarkStatement(tasks[unmarkIndex]);
            break;
        case ("list"):
            ConsolePrint.printList(tasks);
            break;
        case ("todo"):
            tasks[noOfTasks] = new Todo(commandTypeAndParams[INDEX_DESCRIPTION]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        case ("deadline"):
            tasks[noOfTasks] = new Deadline(commandTypeAndParams[INDEX_DESCRIPTION],
                    commandTypeAndParams[INDEX_DEADLINE]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        case ("event"):
            tasks[noOfTasks] = new Event(commandTypeAndParams[INDEX_DESCRIPTION], commandTypeAndParams[INDEX_FROM_DATE],
                    commandTypeAndParams[INDEX_TO_DATE]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        }
    }
}
