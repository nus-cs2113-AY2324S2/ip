package junbot.common;

import junbot.tasks.Deadline;
import junbot.tasks.Event;
import junbot.tasks.Todo;
import junbot.error.InvalidInputException;
import junbot.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasksList() {
        return this.tasks;
    }

    public int getSize(){
        return getTasksList().size();
    }

    public Task addDeadline(String description) throws InvalidInputException {

        description = description.replace("deadline", "").trim();

        if (!description.contains("/by")) {
            throw new InvalidInputException("Include a /by for deadline");
        }

        String[] details = new String[2];
        details = description.split("/by", 2);

        Task userTask = new Deadline(details[0].trim(), details[1].trim());
        tasks.add(userTask);
        return userTask;
    }

    public Task addEvent(String description) throws InvalidInputException {
        description = description.replace("event", "").trim();
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new InvalidInputException("Include a /from and /to for event");
        }

        String[] details = new String[3];
        details = description.split("/from|/to", 3);

        Task userTask = new Event(details[0].trim(), details[1].trim(), details[2].trim());
        tasks.add(userTask);
        return userTask;
    }

    public Task addTodo(String description) throws InvalidInputException {
        description = description.replace("todo", "").trim();
        if(description.isEmpty()){
            throw new InvalidInputException("Please state a task : todo <task>");
        }

        Task userTask = new Todo(description);
        tasks.add(userTask);
        return userTask;
    }

    public boolean isValidListPosition(String command) {
        if (command == null) {
            return false;
        }
        try {
            int listPosition = Integer.parseInt(command);
            return listPosition <= this.tasks.size() && listPosition > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Task markTaskInList(int listPosition) {
        Task task = this.getTasksList().get(listPosition);
        task.markTask();
        return task;
    }

    public Task unmarkTaskInList(int listPosition) {
        Task task = this.getTasksList().get(listPosition);
        task.unmarkTask();
        return task;
    }

}
