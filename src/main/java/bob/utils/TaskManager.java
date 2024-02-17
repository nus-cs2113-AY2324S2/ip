package bob.utils;

import java.util.List;
import java.util.ArrayList;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

public class TaskManager {
    private final List<Task> tasks;
    private int taskCount;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    public List<List<String>> tokenizeTasks() {
        List<List<String>> tokenizedTaskList = new ArrayList<>();

        for (Task task : tasks) {
            List<String> token = new ArrayList<>();
            token.add("T");
            token.add(task.getCompletionStatus());
            token.add(task.getTaskName());

            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                token.set(0, "D");
                token.add(deadline.getDueDate());
            } else if (task instanceof Event) {
                Event event = (Event) task;
                token.set(0, "E");
                token.add(event.getStartDate());
                token.add(event.getEndDate());
            }

            tokenizedTaskList.add(token);
        }

        return tokenizedTaskList;
    }

    /**
     * Add new Task to Task array
     */
    private String addNewTask(Task newTask) {
        String output = " Got it. I've added this task:\n";

        tasks.add(newTask);
        taskCount++;

        output += String.format("   %s\n", newTask);
        output += String.format(" Now you have %d tasks in the list.", taskCount);

        return output;
    }

    public String addTodo(String taskName) {
        Task newTodo = new Todo(taskName);
        return addNewTask(newTodo);
    }

    public String addDeadline(String taskName, String dueDate) {
        Task newDeadline = new Deadline(taskName, dueDate);
        return addNewTask(newDeadline);
    }

    public String addEvent(String taskName, String startDate, String endDate) {
        Task newEvent = new Event(taskName, startDate, endDate);
        return addNewTask(newEvent);
    }

    /**
     * List out tasks (completed/uncompleted) in Task array
     */
    public String listTasks() {
        StringBuilder output = new StringBuilder(" Here are the tasks in your list:\n");

        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks.get(i);
            int currentTaskId = i + 1;
            output.append(String.format(" %d.%s\n", currentTaskId, currentTask));
        }

        return output.toString().stripTrailing();
    }

    /**
     * Update progress of Task in Task array
     */
    public String updateTaskProgress(int taskId, Command command) {
        String output;
        int currentTaskIndex = taskId - 1; // Index in array is ID - 1
        Task currentTask = tasks.get(currentTaskIndex);

        if (command.equals(Command.MARK)) {
            output = " Nice! I've marked this task as done:\n";
            currentTask = currentTask.markTaskAsComplete();
        } else {
            output = " OK, I've marked this task as not done yet:\n";
            currentTask = currentTask.markTaskAsIncomplete();
        }

        output += String.format("   %s", currentTask);

        tasks.set(currentTaskIndex, currentTask); // Update array

        return output;
    }

    public String deleteTask(int taskId) {
        int currentTaskIndex = taskId - 1;
        Task taskToDelete = tasks.remove(currentTaskIndex);
        taskCount--;

        String output = " Noted. I've removed this task:\n";
        output += String.format("   %s\n", taskToDelete);
        output += String.format(" Now you have %d tasks in the list.", taskCount);

        return output;
    }
}
