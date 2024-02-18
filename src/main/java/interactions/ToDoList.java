package interactions;
import interactions.*;

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
                return line.substring(index, nextIndex);
            }
        }
        return line.substring(index);
    }
    public void addNewTask(String line, String type) {
        String toDoDescription = extractToDoOrDate(line, type);
        ToDo newToDo = new ToDo(toDoDescription);
        switch (type) { // only three cases considered
        case "todo":
            newToDo.setHaveToDo(true);
            break;
        case "deadline":
            newToDo.setDeadline(extractToDoOrDate(line, "by"));
            newToDo.setHaveDeadline(true);
            break;
        case "event":
            newToDo.setEventFrom(extractToDoOrDate(line, "from"));
            newToDo.setEventTo(extractToDoOrDate(line, "to"));
            newToDo.setEvent(true);
            break;
        }
        super.list[currSize++] = newToDo;
        System.out.println("Got it. I've added this task:");
        System.out.print(INDENT);
        newToDo.print();
        System.out.println(INDENT + "Now you have " + currSize + " tasks in the list");
    }
    @Override
    public void printList() { // is this necessary?
        super.printList();
    }
}
