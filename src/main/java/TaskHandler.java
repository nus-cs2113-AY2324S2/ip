public class TaskHandler {
    static int MAX_TASKS = 100;
    public static Task[] taskList = new Task[MAX_TASKS];
    public static int listCount = 0;

    public static ToDo createToDo(String userInput) throws Errors.InvalidTodoException {
        String taskName = Parser.parseToDo(userInput);

        return new ToDo(taskName);
    }

    public static Deadline createDeadline(String userInput) throws Errors.InvalidDeadlineException {
        String[] deadlineDetails = Parser.parseDeadline(userInput);

        return new Deadline(deadlineDetails[0], deadlineDetails[1]);
    }

    public static Event createEvent(String userInput) throws Errors.InvalidEventException {
        String[] eventDetails = Parser.parseEvent(userInput);

        return new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
    }

    public static void markUnmarkItem(int markItem, boolean isMarked) {
        taskList[markItem].markAsDone(isMarked);

        System.out.printf("alright! i have set \"%s\" as %s%s",
                taskList[markItem].taskName, isMarked ? "complete" : "incomplete",System.lineSeparator());
    }
}
