package interactions;
import customexceptions.UnknownPromptException;
import interactions.*;
import customexceptions.IncompletePromptException;

public class ToDoList extends TaskList {
    public ToDoList() {
        super.currSize = 0;
        super.list = new ToDo[100];
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
        super.list[currSize++] = newToDo;
        System.out.println("Got it. I've added this task:");
        System.out.print(INDENT);
        newToDo.print();
        System.out.println(INDENT + "Now you have " + currSize + " task" + (currSize > 1 ? "s " : " ") + "in the list");
    }
    @Override
    public void printList() { // is this necessary?
        super.printList();
    }
}
