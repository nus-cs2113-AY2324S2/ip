package kurobot;

import kurobot.exceptions.InvalidDescriptionException;
import kurobot.exceptions.InvalidTimeException;

import java.util.ArrayList;

/**
 * Make amendments to the current task lists or find tasks
 * according to the user's command.
 * and display any errors that occurred during amendments.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private Ui display = new Ui();
    private int taskNum;
    private final int LINE_LEN = 60;
    private final String LINE =  "-".repeat(LINE_LEN);

    private Parser parserInput;

    /**
     * Store the given list of tasks, total number of tasks and the input entered.
     *
     * @param prevTasks Original list of tasks.
     * @param taskNum total number of tasks.
     * @param userInput input entered.
     */
    public TaskList(ArrayList<Task> prevTasks, int taskNum, String userInput) {
        tasks = prevTasks;
        this.taskNum = taskNum;
        parserInput = new Parser(userInput);
    }

    /**
     * Add new task of type [todo] to the current task list
     * and print the newly added task.
     *
     * @return Updated task list.
     */
    public ArrayList<Task> addTodo() {
        try{
            //get the task name
            parserInput.parserToDo();
            String taskName = parserInput.getTaskName();
            Todo task = new Todo(taskName, false);
            tasks.add(task);
            taskNum++;
            printAddedTask(task);
        } catch (InvalidDescriptionException e) {
            display.showNoTaskGiven();
        }
        return tasks;
    }

    /**
     * Display the task that was added.
     *
     * @param task Added task.
     */
    private void printAddedTask(Task task) {
        display.printGivenTask(task, taskNum, true);
    }

    /**
     * Display the task that was deleted.
     *
     * @param task Deleted task.
     */
    private void printDeletedTask(Task task) {
        display.printGivenTask(task, taskNum, false);
    }

    public int getTaskNum() {
        return taskNum;
    }

    /**
     * Add new task of type [deadline] to the current task list
     * and print the newly added task.
     *
     * @return Updated task list.
     */
    public ArrayList<Task> addDeadline() {
        try{
            parserInput.parserDeadline();
            String taskName = parserInput.getTaskName();
            String by = parserInput.getDeadline();
            Deadline task = new Deadline(taskName, by, false);
            tasks.add(task);
            taskNum++;
            printAddedTask(task);
        } catch (InvalidDescriptionException e) {
            display.showNoTaskGiven();
        } catch (InvalidTimeException e) {
            display.showNoTimingGiven();
        }
        return tasks;
    }

    /**
     * Add new task of type [event] to the current task list
     * and print the newly added task.
     *
     * @return Updated task list.
     */
    public ArrayList<Task> addEvent() {
        try {
            parserInput.parserEvent();
            String taskName = parserInput.getTaskName();
            String from = parserInput.getStart();
            String to = parserInput.getEnd();
            Event task = new Event(taskName, from, to, false);
            tasks.add(task);
            taskNum++;
            printAddedTask(task);
        } catch (InvalidDescriptionException e) {
            display.showNoTaskGiven();
        } catch (InvalidTimeException e) {
            display.showNoTimingGiven();
        }
        return tasks;
    }

    /**
     * Mark or unmark the task at a given index, depending on the status value.
     *
     * @param isMark Mark the task if status is true, unmark if it is false.
     * @return Updated task list.
     */
    public ArrayList<Task> markTask(boolean isMark) {
        try {
            parserInput.parserTaskIndex();
            int i = parserInput.getIndex();
            try {
                if (isMark) {
                    tasks.get(i - 1).mark();
                } else {
                    tasks.get(i - 1).unmark();
                }
            } catch (IndexOutOfBoundsException e){
                display.showNoSuchTask();
            }
        } catch (InvalidDescriptionException e) {
            display.showNoIndexGiven();
        }
        return tasks;
    }

    /**
     * Delete a task at a given index from the task list.
     *
     * @return Updated task list with the given task deleted.
     */
    public ArrayList<Task> deleteTask() {
        try {
            parserInput.parserTaskIndex();
            int i = parserInput.getIndex();
            try {
                Task deleteTask = tasks.get(i - 1);
                taskNum--;
                printDeletedTask(deleteTask);
                tasks.remove(deleteTask);
            } catch (IndexOutOfBoundsException e){
                display.showNoSuchTask();
            }
        } catch (InvalidDescriptionException e) {
            display.showNoIndexGiven();
        }
        return tasks;
    }

    /**
     * Show the tasks with name related to the given task name.
     */
    public void findTask() {
        try{
            parserInput.parserToDo();
            String taskName = parserInput.getTaskName();
            int count = 1;
            display.printFoundTasks();
            for (Task task : tasks) {
                if (task.getTaskName().contains(taskName)) {
                    System.out.println(count + "." + task.printTask());
                    count++;
                }
            }
            display.showLine();
        } catch (InvalidDescriptionException e) {
            display.showNoTaskGiven();
        }
    }

}
