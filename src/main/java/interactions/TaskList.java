package interactions;
import customexceptions.UnknownPromptException;
import interactions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import customexceptions.IncompletePromptException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<ToDo> list;
    protected static final String INDENT = "      ";
    public TaskList() {
        list = new ArrayList<>();
    }
    protected Task lastActionTask = null; // allows for 'undo' functionality

    public Task getLastActionTask() {
        return lastActionTask;
    }
    public void setLastActionTask(Task lastActionTask) {
        this.lastActionTask = lastActionTask;
    }

    public void countTasks() {
        int currSize = list.size();
        System.out.println(INDENT + "Now you have " + currSize + " task" + (currSize > 1 ? "s " : " ") + "in the list");
    }

    private String extractToDoOrDate(String line, String keyword) {
        int index = line.indexOf(keyword) + keyword.length();
        String nextWord; // any commands that require a 'next' word
        switch (keyword) {
        case "event":
            nextWord = " from";
            break;
        case "deadline":
            nextWord = " by";
            break;
        case "from":
            nextWord = " to";
            break;
        default:
            nextWord = null;
        }
        if (nextWord != null) {
            int nextIndex = line.indexOf(nextWord, index);
            if (nextIndex != -1) {
                return line.substring(index, nextIndex).trim();
            }
            return "incomplete";
        }
        return line.substring(index).trim();
    }

    public void addNewTask(String line, String type) throws IncompletePromptException {
        String toDoDescription = extractToDoOrDate(line, type);
        ToDo newToDo = new ToDo(toDoDescription);
        switch (type) { // only three cases considered
        case "todo":
            newToDo.setHaveToDo(true);
            break;
        case "deadline":
            String deadline = extractToDoOrDate(line, "by");
            if (deadline.equals("incomplete")) {
                throw new IncompletePromptException();
            } else {
                newToDo.setDeadline(deadline);
            }
            break;
        case "event":
            String dateFrom = extractToDoOrDate(line, "from");
            String dateTo = extractToDoOrDate(line, "to");
            if (dateFrom.equals("incomplete") || dateTo.equals("incomplete")) {
                throw new IncompletePromptException();
            } else {
                newToDo.setEventFrom(dateFrom);
                newToDo.setEventTo(dateTo);
                newToDo.setEvent(true);
            }
            break;
        }
        list.add(newToDo);
        System.out.println("Got it. I've added this task:");
        System.out.print(INDENT);
        newToDo.print();
        countTasks();
    }
    public void mark(String line, boolean isMark) {
        int index = Integer.parseInt(line.substring(isMark ? 5 : 7));
        Task markedTask = list.get(index - 1);
        if (markedTask.isMarked() == isMark) {
            System.out.println("This task is already set as " + (isMark ? "marked." : "unmarked."));
            return;
        }
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.print(INDENT);
        markedTask.setMarked(isMark);
        markedTask.print();
    }
    public void deleteTask(int index) {
        //int index = Integer.parseInt((line.substring(6)));
        System.out.println("Noted. I've removed this task:");
        System.out.print(INDENT);
        lastActionTask = list.get(index - 1);
        lastActionTask.print();
        list.remove(index - 1);
        countTasks();
    }
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("      ");
                Task task = list.get(i);
                System.out.print(i + 1 + ".");
                task.print();
            }
        } else {
            System.out.println(INDENT + "There's nothing in this list.");
        }
    }
    public void saveFile(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        FileWriter fw = new FileWriter(filePath);
        for (ToDo task : list) {
            //System.out.println("HELLO");
            fw.write(task.lineToWrite() + System.lineSeparator());
            //fw.write("TESTING");
        }
        fw.close();
    }
}
