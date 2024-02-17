package interactions;
import interactions.*;

public class ToDoList extends TaskList {
    public ToDoList() {
        super.currSize = 0;
        super.list = new ToDo[100];
    }
    @Override
    public void addToDo(String line) {
        ToDo newToDo = new ToDo(line);
        super.list[currSize++] = newToDo;
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T][ ]" + newToDo.task);
        System.out.println("Now you have " + (super.currSize + 1) + " tasks in the list.");
    }
}
