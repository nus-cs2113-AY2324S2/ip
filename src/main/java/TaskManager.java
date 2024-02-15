public class TaskManager {
    public static int noOfTasks;
    protected Task[] tasks;

    public TaskManager() {
        this.tasks = new Task[100];   //initialize an array of Tasks, to act as a list
        noOfTasks = 0;
    }
    public void executeCommand(String[] commandTypeAndParams) {
        String commandType = commandTypeAndParams[Constants.INDEX_COMMAND_TYPE];

        switch (commandType) {
        case ("mark"):
            int markIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            tasks[markIndex].markAsDone();
            ConsolePrint.printMarkStatement(tasks[markIndex]);
            break;
        case ("unmark"):
            int unmarkIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            tasks[unmarkIndex].markAsNotDone();
            ConsolePrint.printUnmarkStatement(tasks[unmarkIndex]);
            break;
        case ("list"):
            ConsolePrint.printList(tasks);
            break;
        case ("todo"):
            tasks[noOfTasks] = new Todo(commandTypeAndParams[Constants.INDEX_DESCRIPTION]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        case ("deadline"):
            tasks[noOfTasks] = new Deadline(commandTypeAndParams[Constants.INDEX_DESCRIPTION],
                    commandTypeAndParams[Constants.INDEX_DEADLINE]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        case ("event"):
            tasks[noOfTasks] = new Event(commandTypeAndParams[Constants.INDEX_DESCRIPTION],
                    commandTypeAndParams[Constants.INDEX_FROM_DATE],
                    commandTypeAndParams[Constants.INDEX_TO_DATE]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        }
    }
}
