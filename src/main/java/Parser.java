public class Parser {
    public static final int TODO_START_INDEX = 5;
    public static final int DEADLINE_START_INDEX = 9;
    public static final int EVENT_START_INDEX = 6;

    public static Command parseToDo(String task) throws GabException {
        if (task.length() <= TODO_START_INDEX) {
            throw new GabException("Incomplete input! Correct usage: todo [Task name]");
        }
        //String todoName = task.substring(TODO_START_INDEX);
        int taskLength = task.split(" ").length;
        String[] taskArray = task.split(" ", 2);
        if (taskArray.length < 2) {
            throw new GabException("Todo task is empty! Correct usage: todo [task name]");
        }
        return new TodoCommand(taskArray[1]);
    }

    public static Command parseDeadline(String task) throws GabException {
        if (task.length() <= DEADLINE_START_INDEX) {
            throw new GabException("Incomplete input! Correct usage: deadline [Task name] /by [Due date]");
        }

        String deadlineSubstring = task.substring(DEADLINE_START_INDEX);
        String[] deadlineInfo = deadlineSubstring.split(" /by");

        if (deadlineInfo.length == 0 || deadlineInfo[0].trim().isEmpty()) {
            throw new GabException("Missing task and deadline! Correct usage: deadline [Task name] /by [Due date]");
        } else if (deadlineInfo.length < 2) {
            throw new GabException("Missing deadline! Correct usage: deadline [Task name] /by [Due date]");
        } else if (deadlineInfo[1].trim().isEmpty()) {
            throw new GabException("Missing deadline! Correct usage: deadline [Task name] /by [Due date]");
        }

        String deadlineName = deadlineInfo[0].trim();
        String deadline = deadlineInfo[1].trim();
        return new DeadlineCommand(deadlineName, deadline);
    }


    public static Command parseEvent(String task) throws GabException {
        if (task.length() <= EVENT_START_INDEX) {
            throw new GabException("Incomplete input! Correct usage: event [Event name] /from [Start date] /to [End date]");
        }

        String eventSubstring = task.substring(EVENT_START_INDEX);
        String[] eventArray = eventSubstring.split(" /from"); // Limit split to 2 parts

        if (eventArray.length < 2) {
            throw new GabException("Missing '/from' statement! Correct Usage: event [Event Name] /from [Start Date] /to [End Date]");
        }

        String eventName = eventArray[0].trim(); // Trim whitespace

        String[] eventTimeArray = eventArray[1].split(" /to"); // Limit split to 2 parts
        if (eventTimeArray.length < 2) {
            throw new GabException("Missing '/to' statement! Correct Usage: event [Event Name] /from [Start Date] /to [End Date]");
        }

        String startDate = eventTimeArray[0].trim();
        String endDate = eventTimeArray[1].trim();

        return new EventCommand(eventName, startDate, endDate);
    }


    public static Command markTask(String taskDescription, TaskList taskList) throws GabException {
        String[] task = taskDescription.split(" ");

        if (task.length < 2) {
            throw new GabException("Missing task to mark! Correct usage: mark [Task Number]");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(task[1]);
        } catch (NumberFormatException e) {
            throw new GabException("Task index to mark is not an integer! Please provide an integer");
        }

        if (taskIndex > taskList.taskList.size()) {
            throw new GabException("Task is not found!");
        }

        return new MarkCommand(taskIndex); // Convert taskIndex back to String for MarkCommand constructor
    }



    public static Command UnmarkTask(String taskDescription, TaskList taskList) throws GabException {
        String[] task = taskDescription.split(" ");

        if (task.length < 2) {
            throw new GabException("Missing task to unmark! Correct usage: mark [Task Number]");
        }
        String taskIndexString = task[1];
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException e) {
            throw new GabException("Task index to unmark is not integer! Please provide an integer");
        }

        if (taskIndex > taskList.taskList.size()) {
            throw new GabException("Task is not found!");
        }
        return new UnmarkCommand(taskIndexString);
    }

}




