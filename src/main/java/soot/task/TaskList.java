package soot.task;

import soot.ui.UserUi;

import java.util.ArrayList;

/**
 * Class TaskList handles the created task list that stores the tasks added by the user.
 * This includes adding and deleting tasks from the created task list.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Print all tasks stored in the list in a list format,
     * if the list is empty, user will be informed.
     */
    //TODO: implement stream?
    public static void printList() {
        System.out.println("tasks to be done:");
        if (taskList.isEmpty()) {
            System.out.println("  >> nothing so far :)");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                UserUi.displayIndent();
                TaskList.taskList.get(i).printTaskInListFormat(i + 1);
            }
            printTaskListCount();
        }
        UserUi.displayDividerLine();
    }

    /**
     * Prints to the terminal the number of tasks the user currently has in the task list.
     */
    public static void printTaskListCount() {
        int taskCount = TaskList.getSize();
        System.out.println("you currently have " + taskCount + " tasks on your list :)");
    }

    /**
     * Add a given task to the task list, based on the Task Type of this task.
     *
     * @param userInput details of the task as given by the user.
     * @param taskType task type of the task to be added.
     */
    public static void addTask(String userInput, TaskType taskType) {
        switch (taskType) {
        case TODO:
            addTodoTask(userInput);
            break;
        case DEADLINE:
            addDeadlineTask(userInput);
            break;
        case EVENT:
            addEventTask(userInput);
        }
    }

    /**
     * Method to add specifically a Todo task.
     * Details of this task will be printed to the terminal for the user to view.
     *
     * @param userInput details of the todo task as given by the user.
     */
    //TODO: clean up add task methods
    public static void addTodoTask(String userInput) {
        String inputTask = userInput.substring(5);
        taskList.add(new Todo(inputTask, false));
        taskList.get(taskList.size() - 1).printRespondWhenAddTask();
        UserUi.displayDividerLine();
    }

    /**
     * Method to add specifically a Deadline task, where the details of the Deadline task
     * such as taskName etc. will be extracted.
     * Details of this task will be printed to the terminal for the user to view.
     *
     * @param userInput details of the deadline task as given by the user.
     */
    public static void addDeadlineTask(String userInput) {
        String inputTask = userInput.substring(9);
        //split taskName and dueDate
        int deadlineIndex = inputTask.indexOf('/');
        String taskName = inputTask.substring(0, deadlineIndex - 1);
        String dueDate = inputTask.substring(deadlineIndex + 4);
        taskList.add(new Deadline(taskName, false, dueDate));
        taskList.get(taskList.size()-1).printRespondWhenAddTask();
        UserUi.displayDividerLine();
    }

    /**
     * Method to add specifically an Event task, where the details of the Event task
     * such as endDate etc. will be extracted.
     * Details of this task will be printed to the terminal for the user to view.
     *
     * @param userInput details of the event task as given by the user.
     */
    public static void addEventTask(String userInput) {
        String inputTask = userInput.substring(6);
        //split taskName and time frames
        int startIndex = inputTask.indexOf('/');
        String taskName = inputTask.substring(0, startIndex - 1);

        String timeLine = inputTask.substring(startIndex + 6);
        int endIndex = timeLine.indexOf('/');

        String startDate = timeLine.substring(0, endIndex - 1);
        String endDate = timeLine.substring(endIndex + 4);
        taskList.add(new Event(taskName, false, startDate, endDate));
        taskList.get(taskList.size()-1).printRespondWhenAddTask();
        UserUi.displayDividerLine();
    }

    /**
     * Returns the size of the ArrayList taskList.
     *
     * @return size of the task list.
     */
    public static int getSize() {
        return taskList.size();
    }

    //TODO: methods are unused
    public static Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public static TaskType getTaskType(int taskIndex) {
        return taskList.get(taskIndex).taskType;
    }

    /**
     * For a task at the specified index, call method to mark it completed.
     *
     * @param taskIndex index of task in the task list.
     */
    public static void markTaskDone(int taskIndex) {
        taskList.get(taskIndex).markDone();
        UserUi.displayDividerLine();
    }

    /**
     * For a task at the specified index, call method to mark it uncompleted.
     *
     * @param taskIndex index of task in the task list.
     */
    public static void markTaskUndone(int taskIndex) {
        taskList.get(taskIndex).markUndone();
        UserUi.displayDividerLine();
    }

    /**
     * For a task at the specified index, remove the task from the list.
     * Details of this task will be printed to the terminal for the user to view.
     *
     * A tempTask is used to temporarily store the task when printing the details of the deleted task, so that the
     * task count printed will be accurate.
     *
     * @param listIndex index of task in the task list.
     */
    public static void deleteTask(int listIndex) {
        Task tempTask = taskList.get(listIndex);
        taskList.remove(taskList.get(listIndex));
        tempTask.printRespondWhenDeleteTask();
        UserUi.displayDividerLine();
    }

    /**
     * Given details of a todo task that was previously saved in the data file,
     * add this task to the task list.
     *
     * @param taskName name of the todo task to be added.
     * @param isTaskDone state of the todo task whether it has been completed or not.
     */
    public static void addSavedTodoTask(String taskName, boolean isTaskDone) {
        taskList.add(new Todo(taskName, isTaskDone));
    }

    /**
     * Given details of a deadline task that was previously saved in the data file,
     * add this task to the task list.
     *
     * @param taskName name of the deadline task to be added.
     * @param isTaskDone state of the deadline task whether it has been completed or not.
     * @param taskDeadline deadline for this particular Deadline task.
     */
    public static void addSavedDeadlineTask(String taskName, boolean isTaskDone, String taskDeadline) {
        taskList.add(new Deadline(taskName, isTaskDone, taskDeadline));
    }

    /**
     * Given details of an event task that was previously saved in the data file,
     * add this task to the task list.
     *
     * @param taskName name of the event task to be added.
     * @param isTaskDone state of the event task whether it has been completed or not.
     * @param taskStart date where this Event task will start.
     * @param taskEnd date where this Event task will end.
     */
    public static void addSavedEventTask(String taskName, boolean isTaskDone, String taskStart, String taskEnd) {
        taskList.add(new Event(taskName, isTaskDone, taskStart, taskEnd));
    }

    /**
     * Returns a string that is the task in the required format to be saved in saved file,
     * given the task index of this task.
     * The task type has to be identified first.
     *
     * @param taskIndex index of task in the task list.
     * @return formatted string to be used in saved file to save this task.
     */
    public static String formatTaskToSave(int taskIndex) {
        String formattedLine;
        Task task = taskList.get(taskIndex);
        int taskDone = task.isDone ? 1 : 0;

        switch (task.taskType) {
        case TODO:
            formattedLine = "T ; " + taskDone + " ; " + task.taskName;
            break;
        case DEADLINE:
            Deadline deadlineTask = (Deadline) task;
            formattedLine = "D ; " + taskDone + " ; " + deadlineTask.taskName + " ; " + deadlineTask.dueDate;
            break;
        case EVENT:
            Event eventTask = (Event) task;
            formattedLine = "E ; " + taskDone + " ; " + eventTask.taskName + " ; " + eventTask.startDate + " ; " + eventTask.endDate;
            break;
        default:
            formattedLine = "i could not read this task";
            System.out.println("task of unknown type detected");
        }
        return formattedLine;
    }
}
