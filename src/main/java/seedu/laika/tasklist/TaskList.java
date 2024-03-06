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
        this.tasks = new ArrayList<Task>();
        this.lines = new ArrayList<String>();
    }


    public int getSize() {
        return tasks.size();
    }
    public void modifyTask(String[] words){
        int taskNumber = Integer.parseInt(words[1]);
        if (words[0].equals("mark")) {
            tasks.get(taskNumber - 1).markAsDone();
            TextUi.markAsDoneMessage();
        } else {
            tasks.get(taskNumber - 1).markAsUndone();
            TextUi.markAsUnDoneMessage();
        }
        System.out.println(tasks.get(taskNumber-1));
    }

    public void deleteTask(String[] words){
        int taskNumber = Integer.parseInt(words[1]);
        TextUi.deleteTaskMessage(tasks,taskNumber);
        tasks.remove(taskNumber-1);
        lines.remove(taskNumber-1);
    }

    public void addTask(String line) throws LaikaException {
        if(line.startsWith("todo")){
            String[] words = line.split(" ", 2);
            if (words.length != 2){
                TextUi.showErrorMessage(TextUi.MESSAGE_TODO_FORMAT);
                throw new LaikaException();
            }
            tasks.add(new Todo(words[1]));
            lines.add(line);
        }
        else if (line.startsWith("deadline")) {
            String[] words = line.split("/");
            if (words.length != 2){
                TextUi.showErrorMessage(TextUi.MESSAGE_DEADLINE_FORMAT);
                throw new LaikaException();
            }
            tasks.add(new Deadline(words[0].replaceFirst("deadline ",""),
                    words[1].replaceFirst("by ","")));
            lines.add(line);
        }
        else if (line.startsWith("event")) {
            String[] words = line.split("/");
            if (words.length != 3){
                TextUi.showErrorMessage(TextUi.MESSAGE_EVENT_FORMAT);
                throw new LaikaException();
            }
            tasks.add(new Event(words[0].replaceFirst("event ",""),
                    words[1].replaceFirst("from ",""),
                    words[2].replaceFirst("to ","")));
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
