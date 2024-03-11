import java.util.ArrayList;
import java.io.IOException;

public class TaskList {
    private static int count;
    private static ArrayList<Task> tasks;

    public TaskList() {
        count = 0;
        tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void addTask(Task t, boolean prnt) {
        tasks.add(t);
        count += 1;
        if (prnt) {
            Ui.printAddTask(count, t);
        }
    }

    public void delete(String[] arr) {
        try{
            int delNum = Parser.markDeleteParse(arr, this.count);
            tasks.remove(delNum);
            count -= 1;
            Ui.printNumTasks(count);
        } catch (IllegalArgumentException e) {
            Ui.markDeleteFormatError("delete");
            return;
        }
    }

    public void mark(String[] arr) {
        try{
            int markNum = Parser.markDeleteParse(arr, this.count);
            tasks.get(markNum).markTask(true);
        } catch (IllegalArgumentException e) {
            Ui.markDeleteFormatError("mark");
            return;
        }
    }

    public void unmark(String[] arr) {
        try{
            int markNum = Parser.markDeleteParse(arr, this.count);
            tasks.get(markNum).unmarkTask();
        } catch (IllegalArgumentException e) {
            Ui.markDeleteFormatError("unmark");
            return;
        }
    }

    public void addTodo(String[] arr) {
        try {
            Task t = Parser.todoParse(arr);
            addTask(t, true);
            try {
                Storage.writeToFile(t);
            } catch (IOException e) {
                Ui.printError(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            Ui.taskFormatError("todo");
            return;
        }
    }

    public void addDeadline(String[] arr) {
        try {
            Task t = Parser.deadlineParse(arr);
            addTask(t, true);
            try {
                Storage.writeToFile(t);
            } catch (IOException e) {
                Ui.printError(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            Ui.taskFormatError("deadline");
            return;
        }
    }
    public void addEvent(String[] arr) {
        try {
            Task t = Parser.eventParse(arr);
            addTask(t, true);
            try {
                Storage.writeToFile(t);
            } catch (IOException e) {
                Ui.printError(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            Ui.taskFormatError("event");
            return;
        }
    }
}
