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
                Task currentTask = TaskList.taskList.get(i);
                currentTask.printTaskInListFormat(i + 1);
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
            break;
        default:
            System.out.println("there was an error when adding the task.");
        }
    }

    /**
     * Add specifically a new Todo task to the task list.
     * Task has not been completed when added, thus isDone is false.
     * Details of this task will be printed to the terminal for the user to view.
     *
     * @param userInput details of the todo task as given by the user.
     */
    //TODO: clean up add task methods
    public static void addTodoTask(String userInput) {
        String todoTaskName = userInput.substring(5);
        taskList.add(new Todo(todoTaskName, false));

        int taskIndexInArrayList = taskList.size() - 1;
        taskList.get(taskIndexInArrayList).printRespondWhenAddTask();
        UserUi.displayDividerLine();
    }

    /**
     * Add specifically a Deadline task to the task list, where the details of the Deadline task
     * such as taskName etc. will be extracted.
     * Details of this task will be printed to the terminal for the user to view.
     *
     * @param userInput details of the deadline task as given by the user.
     */
    public static void addDeadlineTask(String userInput) {
        String inputTaskDetails = userInput.substring(9);

        int slashIndex = inputTaskDetails.indexOf('/'); //slash splits the taskName and dueDate
        String taskName = inputTaskDetails.substring(0, slashIndex - 1);
        String dueDate = inputTaskDetails.substring(slashIndex + 4);

        taskList.add(new Deadline(taskName, false, dueDate));
        int taskIndexInArrayList = taskList.size() - 1;
        taskList.get(taskIndexInArrayList).printRespondWhenAddTask();
        UserUi.displayDividerLine();
    }

    /**
     * Add specifically an Event task to the task list, where the details of the Event task
     * such as endDate etc. will be extracted.
     * Details of this task will be printed to the terminal for the user to view.
     *
     * @param userInput details of the event task as given by the user.
     */
    public static void addEventTask(String userInput) {
        String inputTaskDetails = userInput.substring(6);

        int firstSlashIndex = inputTaskDetails.indexOf('/');
        String taskName = inputTaskDetails.substring(0, firstSlashIndex - 1);
        String eventTimelineDetails = inputTaskDetails.substring(firstSlashIndex + 6);

        int secondSlashIndex = eventTimelineDetails.indexOf('/');
        String startDate = eventTimelineDetails.substring(0, secondSlashIndex - 1);
        String endDate = eventTimelineDetails.substring(secondSlashIndex + 4);

        taskList.add(new Event(taskName, false, startDate, endDate));
        int taskIndexInArrayList = taskList.size() - 1;
        taskList.get(taskIndexInArrayList).printRespondWhenAddTask();
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

    /**
     * Mark the task at the specified index completed.
     *
     * @param taskIndex index of task in the task list.
     */
    public static void markTaskDone(int taskIndex) {
        taskList.get(taskIndex).markTaskDone();
        UserUi.displayDividerLine();
    }

    /**
     * Mark the task at the specified index uncompleted.
     *
     * @param taskIndex index of task in the task list.
     */
    public static void markTaskUndone(int taskIndex) {
        taskList.get(taskIndex).markTaskUndone();
        UserUi.displayDividerLine();
    }

    /**
     * Remove the task at the specified index from the list.
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
     * Add a saved todo task to the task list,
     * where the details of the task that was previously saved in the data file is provided.
     *
     * @param taskName name of the todo task to be added.
     * @param isTaskDone state of the todo task whether it has been completed or not.
     */
    public static void addSavedTodoTask(String taskName, boolean isTaskDone) {
        taskList.add(new Todo(taskName, isTaskDone));
    }

    /**
     * Add a saved deadline task to the task list,
     * where the details of the task that was previously saved in the data file is provided.
     *
     * @param taskName name of the deadline task to be added.
     * @param isTaskDone state of the deadline task whether it has been completed or not.
     * @param taskDeadline deadline for this particular Deadline task.
     */
    public static void addSavedDeadlineTask(String taskName, boolean isTaskDone, String taskDeadline) {
        taskList.add(new Deadline(taskName, isTaskDone, taskDeadline));
    }

    /**
     * Add an event deadline task to the task list,
     * where the details of the task that was previously saved in the data file is provided.
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

        int taskDoneState = formatTaskDoneState(task.isDone);

        switch (task.taskType) {
        case TODO:
            formattedLine = "T ; " + taskDoneState + " ; " + task.taskName;
            break;
        case DEADLINE:
            Deadline deadlineTask = (Deadline) task;
            formattedLine = "D ; " + taskDoneState + " ; " + deadlineTask.taskName + " ; " + deadlineTask.dueDate;
            break;
        case EVENT:
            Event eventTask = (Event) task;
            formattedLine = "E ; " + taskDoneState + " ; " + eventTask.taskName + " ; "
                    + eventTask.startDate + " ; " + eventTask.endDate;
            break;
        default:
            formattedLine = "i could not read this task";
            System.out.println("task of unknown type detected");
        }
        return formattedLine;
    }

    /**
     * Returns an integer value of 1 is the state of the task is done, else return 0.
     *
     * @param isDone boolean value of whether a task is done.
     * @return integer representation of the task's done state.
     */
    private static int formatTaskDoneState(boolean isDone) {
        if (isDone) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns an ArrayList of tasks that contains the keyword specified by the user.
     *
     * @param keyword string that the user is trying to find occurences of in the tasks.
     * @return arraylist of tasks that contains the keyword.
     */
    public static ArrayList<Task> findKeyword(String keyword) {
        ArrayList<Task> compiledList = new ArrayList<>();
        for (Task taskToCheck : taskList) {
            if (taskToCheck.taskName.equals(keyword)) {
                compiledList.add(taskToCheck);
            }
        }
        return compiledList;
    }

    /**
     * Removes all tasks in the task list.
     * Task list will now have 0 tasks.
     */
    public static void clearTaskList() {
        taskList.clear();
    }
}
