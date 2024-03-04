package seedu.laika.tasklist;

import seedu.laika.LaikaException;
import seedu.laika.task.Deadline;
import seedu.laika.task.Event;
import seedu.laika.task.Task;
import seedu.laika.task.Todo;

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
            System.out.println("Laika: Good job! Task has been marked as done.");
        } else {
            tasks.get(taskNumber - 1).markAsUndone();
            System.out.println("Laika: Alright, task has been marked as undone.");
        }
        System.out.println(tasks.get(taskNumber-1));
    }

    public void deleteTask(String[] words){
        int taskNumber = Integer.parseInt(words[1]);
        System.out.println("Laika: Gotcha! I've dealt with this task:" + System.lineSeparator()
                + tasks.get(taskNumber-1));
        tasks.remove(taskNumber-1);
        lines.remove(taskNumber-1);
    }

    public void addTask(String line) throws LaikaException {
        lines.add(line);
        if(line.startsWith("todo")){
            tasks.add(new Todo(line.replaceFirst("todo ","")));
        }
        else if (line.startsWith("deadline")) {
            String[] words = line.split("/");
            if (words.length != 2){
                throw new LaikaException();
            }
            tasks.add(new Deadline(words[0].replaceFirst("deadline ",""),
                    words[1].replaceFirst("by ","")));
        }
        else if (line.startsWith("event")) {
            String[] words = line.split("/");
            if (words.length != 3){
                throw new LaikaException();
            }
            tasks.add(new Event(words[0].replaceFirst("event ",""),
                    words[1].replaceFirst("from ",""),
                    words[2].replaceFirst("to ","")));
        } else {
            throw new LaikaException();
        }
    }

    public void displayTasks(){
        System.out.println("Task List:");
        for (int i = 0; i < tasks.size(); i++){
            System.out.println((i + 1) + ") " + tasks.get(i));
        }
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public String getLine(int index) {
        return lines.get(index);
    }

    public void addLines(String command) {
        lines.add(command);
    }
}
