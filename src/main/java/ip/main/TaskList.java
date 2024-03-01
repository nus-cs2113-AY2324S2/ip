package ip.main;

import ip.task.Deadline;
import ip.task.Event;
import ip.task.Task;
import ip.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;
    private static Ui ui;

    public TaskList(Ui ui) {
        tasks = new ArrayList<>();
        TaskList.ui = ui;
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void printTaskList() {
        ui.print("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.print((i + 1) + "." + tasks.get(i).getDetails());
        }
    }

    public void find(String line) {
        String keyword;
        try {
            keyword = line.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            ui.print("Please provide a keyword");
            return;
        }
        int matchingCount = 0;
        ui.print("Here are the tasks containing '" + keyword + "':");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDetails().contains(keyword)) {
                matchingCount++;
                ui.print(matchingCount + "." + tasks.get(i).getDetails());
            }
        }
        if (matchingCount == 0) {
            ui.print("There are no matching tasks");
        }
    }

    public boolean markTask(String line) {
        int indexMarked;
        try {
            indexMarked = Integer.parseInt(line.substring(5)) - 1;
            tasks.get(indexMarked).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            ui.print("Please provide a number from 1 to " + tasks.size());
            return false;
        } catch (NumberFormatException e) {
            ui.print("Please input an integer");
            return false;
        }
        ui.print("Nice! I've marked this task as done:");
        ui.print(tasks.get(indexMarked).getDetails());
        return true;
    }

    public boolean unmarkTask(String line) {
        int indexUnmarked;
        try {
            indexUnmarked = Integer.parseInt(line.substring(7)) - 1;
            tasks.get(indexUnmarked).setDone(false);
        } catch (IndexOutOfBoundsException e) {
            ui.print("Please provide a number from 1 to " + tasks.size());
            return false;
        } catch (NumberFormatException e) {
            ui.print("Please input an integer");
            return false;
        }
        ui.print("OK, I've marked this task as not done yet:");
        ui.print(tasks.get(indexUnmarked).getDetails());
        return true;
    }

    public boolean deleteTask(String line) {
        int indexDeleted;
        try {
            indexDeleted = Integer.parseInt((line.substring(7))) - 1;
            ui.print("Task removed: " + tasks.get(indexDeleted).getDetails());
            tasks.remove(indexDeleted);
        } catch (IndexOutOfBoundsException e) {
            ui.print("Please provide a number from 1 to " + tasks.size());
            return false;
        } catch (NumberFormatException e) {
            ui.print("Please input an integer");
            return false;
        }
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }

    public boolean addTodo(String line) {
        try {
            tasks.add(new Todo(line));
        } catch (StringIndexOutOfBoundsException e) {
            ui.print("Please input in the form 'todo <description>'");
            return false;
        }
        ui.print("New task added: " + tasks.get(tasks.size() - 1).getDetails());
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }

    public boolean addDeadline(String line) {
        try {
            tasks.add(new Deadline(line));
        } catch (StringIndexOutOfBoundsException e) {
            ui.print("Please input in the form 'deadline <description> /by <when>'");
            return false;
        }
        ui.print("New task added: " + tasks.get(tasks.size() - 1).getDetails());
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }

    public boolean addEvent(String line) {
        try {
            tasks.add(new Event(line));
        } catch (StringIndexOutOfBoundsException e) {
            ui.print("Please input in the form 'event <description> /from <when> /to <when>'");
            return false;
        }
        ui.print("New task added: " + tasks.get(tasks.size() - 1).getDetails());
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }
}
