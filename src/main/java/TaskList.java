import java.util.ArrayList;

import jason.tasks.Task;
import jason.tasks.Todo;
import jason.tasks.Deadline;
import jason.tasks.Events;

import jason.errorhandling.JasonException;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void appendToList(Task task) {
        list.add(task);
    }

    public void addTasks(String input) throws JasonException {
        try {
            if (input.startsWith("todo ")) {
                addTodoTask(input);
            } else if (input.startsWith("deadline ")) {
                addDeadlineTask(input);
            } else if (input.startsWith("event ")) {
                addEventTask(input);
            } else {
                throw new JasonException("Invalid input. Please enter a valid command");
            }
        } catch (JasonException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addTodoTask(String task) throws JasonException {
        String taskDescription = task.substring(4).trim(); // trim() removes leading and trailing spaces
        if (taskDescription.isEmpty()) {
            throw new JasonException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(taskDescription);
        list.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo);
        Ui.showTaskNumber(list);
    }

    public void addDeadlineTask(String task) throws JasonException {
        if (!task.contains("/by")) {
            throw new JasonException("Invalid task format. Use 'deadline [description] /by [date/time]'.");
        }
        String[] parts = task.split("/by", 2);
        String taskDescription = parts[0].substring(9).trim();
        String taskDeadlineBy = parts[1].trim();

        if (taskDescription.isEmpty() || taskDeadlineBy.isEmpty()) {
            throw new JasonException("Invalid task. Description and date/time cannot be empty.");
        }
        Deadline newDeadline = new Deadline(taskDescription, taskDeadlineBy);
        list.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        Ui.showTaskNumber(list);
    }

    public void addEventTask(String task) throws JasonException {
        if (!task.contains("/from") && !task.contains("/to")) {
            throw new JasonException("Invalid task format. Use 'event [description] /from [date/time] /to [date/time] '.");
        }
        String[] parts = task.split("/from", 2);
        String taskDescription = parts[0].substring(6).trim();
        if (parts.length < 2 || taskDescription.isEmpty()) {
            throw new JasonException("Invalid task. The description of an event cannot be empty.");
        }
        String[] timeParts = parts[1].trim().split("/to", 2);
        String eventStartFrom = timeParts[0].trim();
        String eventTill = timeParts.length > 1 ? timeParts[1].trim() : "";

        if (eventStartFrom.isEmpty() || eventTill.isEmpty()) {
            throw new JasonException("Invalid event times. Please type in the format: description /from (date/time) /to (date/time).");
        }
        Events newEvent = new Events(taskDescription, eventStartFrom, eventTill);
        list.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        Ui.showTaskNumber(list);
    }

    public void deleteTask(String[] input) throws JasonException {
        try {
            int taskNumber = Integer.parseInt(input[1]) - 1;
            if (taskNumber < 0 || taskNumber >= list.size()) {
                throw new JasonException("That task number is not valid. The list only has " + list.size() + " tasks currently");
            }
            Task removedTask = list.remove(taskNumber);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            Ui.showTaskNumber(list);
        } catch (NumberFormatException e) {
            throw new JasonException("Please enter a valid task number.");
        }
    }

    public void markTask(String[] input) throws JasonException {
        try {
            int taskNumber = Integer.parseInt(input[1]) - 1;
            if (taskNumber < 0 || taskNumber >= list.size()) {
                throw new JasonException("That task number is not valid. The list only has " + list.size() + " tasks currently");
            }
            list.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(taskNumber));
        } catch (NumberFormatException e) {
            throw new JasonException("Please enter a valid task number.");
        }
    }

    public void unmarkTask(String[] input) throws JasonException {
        try {
            int taskNumber = Integer.parseInt(input[1]) - 1;
            if (taskNumber < 0 || taskNumber >= list.size()) {
                throw new JasonException("That task number is not valid. The list only has " + list.size() + " tasks currently");
            }
            list.get(taskNumber).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(taskNumber));
        } catch (NumberFormatException e) {
            throw new JasonException("Please enter a valid task number.");
        }
    }
    public void findTasks(String[] parts) throws JasonException {
        if (parts.length < 2) {
            throw new JasonException("Please provide a keyword to find.");
        }
        String keyword = parts[1];
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchedTasks.add(task);
            }
        }
        Ui.showFoundTasks(matchedTasks);
    }

    public ArrayList<Task> getTasks() {
        return list;
    }




}
