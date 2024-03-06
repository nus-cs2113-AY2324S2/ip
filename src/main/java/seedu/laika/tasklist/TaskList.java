package seedu.laika.tasklist;

import seedu.laika.LaikaException;
import seedu.laika.task.Deadline;
import seedu.laika.task.Event;
import seedu.laika.task.Task;
import seedu.laika.task.Todo;
import seedu.laika.ui.TextUi;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;
    protected ArrayList<String> lines;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.lines = new ArrayList<>();
    }


    public int getSize() {
        return tasks.size();
    }

    public void modifyTask(String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        if (words[0].equals("mark")) {
            tasks.get(taskNumber - 1).markAsDone();
            TextUi.markAsDoneMessage();
        } else {
            tasks.get(taskNumber - 1).markAsUndone();
            TextUi.markAsUnDoneMessage();
        }
        System.out.println(tasks.get(taskNumber - 1));
    }

    public void deleteTask(String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        TextUi.deleteTaskMessage(tasks, taskNumber);
        tasks.remove(taskNumber - 1);
        lines.remove(taskNumber - 1);
    }

    public void addTask(String line) throws LaikaException {
        if (line.startsWith("todo")) {
            String[] words = line.split(" ", 2);
            if (words.length != 2) {
                TextUi.showErrorMessage(TextUi.MESSAGE_TODO_FORMAT);
                throw new LaikaException();
            }
            tasks.add(new Todo(words[1]));
            lines.add(line);
        } else if (line.startsWith("deadline")) {
            String[] words = line.split("/by");
            String description = words[0].replaceFirst("deadline ", "");
            if (words.length != 2 || description.isEmpty()) {
                TextUi.showErrorMessage(TextUi.MESSAGE_DEADLINE_FORMAT);
                throw new LaikaException();
            }
            String by = words[1].trim();
            if (by.isEmpty()) {
                TextUi.showErrorMessage(TextUi.MESSAGE_DEADLINE_FORMAT);
                throw new LaikaException();
            }
            tasks.add(new Deadline(description, by));
            lines.add(line);
        } else if (line.startsWith("event")) {
            String[] words = line.split("/from",2);
            String description = words[0].replaceFirst("event ", "");
            if (words.length != 2 || description.isEmpty()) {
                TextUi.showErrorMessage(TextUi.MESSAGE_EVENT_FORMAT);
                throw new LaikaException();
            }
            String[] fromAndTo = words[1].split("/to",2);
            String from = fromAndTo[0].trim();
            String to = fromAndTo[1].trim();
            if (fromAndTo.length != 2 || from.isEmpty() || to.isEmpty()) {
                TextUi.showErrorMessage(TextUi.MESSAGE_EVENT_FORMAT);
                throw new LaikaException();
            }
            tasks.add(new Event(description, from, to));
            lines.add(line);
        } else {
            TextUi.showErrorMessage(TextUi.MESSAGE_INVALID_COMMAND_WORD);
            throw new LaikaException();
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            TextUi.showErrorMessage(TextUi.MESSAGE_EMPTY_LIST);
        } else {
            System.out.println("Task List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ") " + tasks.get(i));
            }
        }
    }

    public void findTasks(String word) {
        ArrayList<Task> similarTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(word)) {
                similarTasks.add(task);
            }
        }
        if (similarTasks.isEmpty()) {
            System.out.println("Laika: There are no matching tasks.:(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < similarTasks.size(); i++) {
                System.out.println((i + 1) + ") " + similarTasks.get(i));
            }
        }
    }

    public Task getTasks(int index) {
        return tasks.get(index);
    }

    public String getLine(int index) {
        return lines.get(index);
    }

    public void addLines(String command) {
        lines.add(command);
    }

    public void addTaskToTasks(Task task) {
        tasks.add(task);
    }
}