package interactions;
import customexceptions.UnknownPromptException;
import interactions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import customexceptions.IncompletePromptException;

public class ToDoList {
    protected ArrayList<ToDo> list;
    int currSize;
    protected static final String INDENT = "      ";
    public ToDoList() {
        currSize = 0;
        list = new ArrayList<>();
    }
    private String extractToDoOrDate(String line, String keyword) {
        int index = line.indexOf(keyword) + keyword.length();
        String nextWord;
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
        list.add(currSize, newToDo);
        currSize++;
        //super.list[currSize++] = newToDo;
        System.out.println("Got it. I've added this task:");
        System.out.print(INDENT);
        newToDo.print();
        System.out.println(INDENT + "Now you have " + currSize + " task" + (currSize > 1 ? "s " : " ") + "in the list");
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
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        if (currSize > 0) {
            for (int i = 0; i < currSize; i++) {
                System.out.print("      ");
                //Task task = list[i];
                Task task = list.get(i);
                System.out.print(i + 1 + ".");
                task.print();
            }
        } else {
            System.out.println(INDENT + "There's nothing in this list.");
        }
    }
}
