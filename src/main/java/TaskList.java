import java.util.ArrayList;

/**
 * Deals with modifying the task list based on the commands given by the user
 */
public class TaskList {
    private static final int MARK_TASK_INDEX = 5;
    private static final int UNMARK_TASK_INDEX = 7;
    private static final int DELETE_TASK_INDEX = 7;
    private static final int TODO_DESCRIPTION_POSITION = 5;
    private static final int FIND_TASK_POSITION = 5;

    /**
     * Displays the current list of tasks along with type and status
     *
     * @param taskList The task list to be displayed
     */
    protected static void displayTaskList(ArrayList<Task> taskList) {
        Ui.printLine();
        int taskListSize = taskList.size();
        if (taskListSize > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskListSize; i++) {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
        } else {
            System.out.println("You have no tasks");
        }
        Ui.printLine();
    }

    /**
     * Marks the relevant task as done based on the task number in the task list
     *
     * @param command The command given by the user which contains the task number of the relevant task
     * @param taskList The current list of tasks
     * @throws NehsikException If the user enters an invalid task number, doesn't mention the task number or types the command in types the command in wrong format
     */
    protected static void markTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < MARK_TASK_INDEX) {
            throw new NehsikException("Please mention the task number you would like to mark");
        }

        if (command.charAt(MARK_TASK_INDEX - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int taskNum = Integer.parseInt(command.substring(MARK_TASK_INDEX)) - 1;
        int taskListSize = taskList.size();
        if (taskNum >= taskListSize || taskNum < 0) {
            throw new NehsikException("Please enter a valid task number. There are " + taskListSize + " tasks in the list");
        }
        Ui.printLine();
        System.out.println("Nice! I've marked this task as done:");
        taskList.get(taskNum).markAsDone();
        System.out.println(taskList.get(taskNum).toString());
        Ui.printLine();
    }

    /**
     * Marks the relevant task as not done based on the task number in the task list
     *
     * @param command The command given by the user which contains the task number of the relevant task
     * @param taskList The current list of tasks
     * @throws NehsikException If the user enters an invalid task number, doesn't mention the task number or types the command in types the command in wrong format
     */
    protected static void unmarkTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < UNMARK_TASK_INDEX) {
            throw new NehsikException("Please mention the task number you would like to unmark");
        }

        if (command.charAt(UNMARK_TASK_INDEX - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int taskNum = Integer.parseInt(command.substring(UNMARK_TASK_INDEX)) - 1;
        int taskListSize = taskList.size();
        if (taskNum >= taskListSize || taskNum < 0) {
            throw new NehsikException("Please enter a valid task number. There are " + taskListSize + " tasks in the list");
        }

        Ui.printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        taskList.get(taskNum).markAsUndone();
        System.out.println(taskList.get(taskNum).toString());
        Ui.printLine();
    }

    /**
     * Adds a todo type task (with task description) to the list of tasks
     *
     * @param command The command entered by the user which contains the description of the todo task
     * @param taskList The current list of tasks before addition of task
     * @throws NehsikException If the user doesn't give a description or types the command in types the command in wrong format
     */
    protected static void addTodoTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < TODO_DESCRIPTION_POSITION) {
            throw new NehsikException("The description of a todo cannot be empty");
        }

        if (command.charAt(TODO_DESCRIPTION_POSITION - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        String taskDescription = command.substring(TODO_DESCRIPTION_POSITION).trim();
        taskList.add(new Todo(taskDescription));
    }

    /**
     * Adds a deadline type task (with task description and deadline) to the list of tasks
     *
     * @param command The command entered by the user which contains the description of the task and the deadline
     * @param taskList The current list of tasks before addition of task
     * @throws NehsikException If the user doesn't give a description or types the command in types the command in wrong format
     */
    protected static void addDeadlineTask(String command, ArrayList<Task> taskList) throws NehsikException {
        int descriptionStartPosition = command.indexOf("deadline ") + 9;
        if (command.length() <= descriptionStartPosition) {
            throw new NehsikException("The description of a deadline cannot be empty");
        }

        if (command.charAt(descriptionStartPosition - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int descriptionEndPosition = command.indexOf("/by ") - 1;
        String taskDescription = command.substring(descriptionStartPosition, descriptionEndPosition).trim();

        int byStringPosition = command.indexOf("/by ") + 4;
        String by = command.substring(byStringPosition).trim();

        taskList.add(new Deadline(taskDescription, by));
    }

    /**
     * Adds an event type task (with task description and duration of event) to the list of tasks
     *
     * @param command The command entered by the user which contains the description of the task, and the duration of the event
     * @param taskList The current list of tasks before addition of task
     * @throws NehsikException If the user doesn't give a description or types the command in types the command in wrong format
     */
    protected static void addEventTask(String command, ArrayList<Task> taskList) throws NehsikException {
        int descriptionStartPosition = command.indexOf("event ") + 6;
        if (command.length() <= descriptionStartPosition) {
            throw new NehsikException("The description of an event cannot be empty");
        }
        if (command.charAt(descriptionStartPosition - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int descriptionEndPosition = command.indexOf("/from ") - 1;
        String taskDescription = command.substring(descriptionStartPosition, descriptionEndPosition).trim();

        int fromStringStartPosition = command.indexOf("/from ") + 6;
        int fromStringEndPosition = command.indexOf("/to ") - 1;
        String from = command.substring(fromStringStartPosition, fromStringEndPosition).trim();

        int toStringPosition = command.indexOf("/to ") + 4;
        String to = command.substring(toStringPosition).trim();

        taskList.add(new Event(taskDescription, from, to));
    }

    /**
     * Deletes the required task from the list of tasks and acknowledges the deletion by displaying a message
     *
     * @param command The command entered by the user which contains the task number of the task to be deleted
     * @param taskList The current task list before deletion of the task
     * @throws NehsikException If the user enters an invalid task number, doesn't mention the task number or types the command in types the command in wrong format
     */
    protected static void deleteTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < DELETE_TASK_INDEX) {
            throw new NehsikException("Please mention the task number you would like to delete");
        }

        if (command.charAt(DELETE_TASK_INDEX - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int taskNum = Integer.parseInt(command.substring(DELETE_TASK_INDEX)) - 1;
        int taskListSize = taskList.size();
        if (taskNum >= taskListSize || taskNum < 0) {
            throw new NehsikException("Please enter a valid task number. There are " + taskListSize + " tasks in the list");
        }

        Ui.printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskList.get(taskNum).toString());
        taskList.remove(taskNum);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        Ui.printLine();
    }

    /**
     * Acknowledges the addition of a todo, deadline and event task by displaying a message
     *
     * @param taskList THe latest list of tasks after the task has been added
     */
    protected static void acknowledgeTaskAdded(ArrayList<Task> taskList) {
        int latestTaskIndex = taskList.size() - 1;
        Ui.printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(latestTaskIndex).toString());
        System.out.println("Now you have " + (latestTaskIndex + 1) + " tasks in the list");
        Ui.printLine();
    }

    protected static void findTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < FIND_TASK_POSITION) {
            throw new NehsikException("Please type a keyword of the task you would like to find");
        }

        if (command.charAt(FIND_TASK_POSITION - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        ArrayList<Task> matchingTasks = new ArrayList<>();
        String keyWord = command.substring(FIND_TASK_POSITION).trim();

        for (Task task : taskList) {
            if (task.description.contains(keyWord)) {
                matchingTasks.add(task);
            }
        }

        Ui.printLine();
        int numOfMatchingTasks = matchingTasks.size();
        if (numOfMatchingTasks > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < numOfMatchingTasks; i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i).toString());
            }
        } else {
            System.out.println("There are no matching tasks in your list");
        }
        Ui.printLine();
    }
}
