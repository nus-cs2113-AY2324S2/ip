public class LogicManager {
    private TaskList taskList;
    private ResponseManager responseManager;

    public LogicManager(ResponseManager responseManager) {
        this.taskList = new TaskList();
        this.responseManager = responseManager;
    }

    public void executeCommand(String command, String details) {
        switch (command) {
        case "list":
            responseManager.printTaskList(taskList);
            break;
        case "todo":
            addTask(new Todo(details));
            break;
        case "mark":
        case "unmark":
            markUnmarkTask(details, command.equals("mark"));
            break;
        case "bye":
            responseManager.printGoodbye();
            System.exit(0);
            break;
        default:
            // Handle other commands or ignore unknown commands
            break;
        }
    }

    public void addDeadline(String title, String by) {
        addTask(new Deadline(title, by));
    }

    public void addEvent(String title, String from, String to) {
        addTask(new Event(title, from, to));
    }

    private void addTask(Task task) {
        taskList.add(task);
        responseManager.printAddTask(task, taskList.getListCount());
    }

    private void markUnmarkTask(String details, boolean isMark) {
        int taskIndex = Integer.parseInt(details) - 1;
        if (taskList.isCountValid(taskIndex)) {
            if (isMark) {
                taskList.markTask(taskIndex);
            } else {
                taskList.unmarkTask(taskIndex);
            }
            responseManager.printLine();
        } else {
            responseManager.printInvalidIndex();
        }
    }
}
