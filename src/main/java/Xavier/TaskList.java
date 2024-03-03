package Xavier;


import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import java.util.ArrayList;

/**
 * Represents a todolist
 */
public class TaskList {
    ArrayList<Task> itemList;

    public TaskList() {
        this.itemList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> itemList) {
        this.itemList = itemList;
    }

    /** Lists all items in todo list  */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(i + 1 + "." + itemList.get(i).toString());
        }
    }

    public void markAsDone(int index) {
        itemList.get(index).markAsDone();
    }

    public void unmarkAsDone(int index) {
        itemList.get(index).markAsNotDone();
    }

    public void addTodoTask(String toDoString) {
        Todo toDo = new Todo(toDoString);
        itemList.add(toDo);
    }

    public void addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        itemList.add(deadline);
    }

    public void addEvent(String description, String from, String to) {
        Event event = new Event(description, from, to);
        itemList.add(event);
    }

    public void deleteTask(int index) {
        itemList.remove(index);
    }

    /** Returns number of items in list
     *
     * @return number of items in the list
     */
    public int getSize() {
        return itemList.size();
    }

    /** Returns specific task
     *
     * @param index index of desired task
     * @return task
     */
    public String getTask(int index) {
        return itemList.get(index).toString();
    }

    /** Returns last task added
     *
     * @return last task
     */
    public String getLastTask() {
        return itemList.get(itemList.size()-1).toString();
    }

    /** Returns todo List as an ArrayList<Task>
     *
     * @return list of tasks
     */
    public ArrayList<Task> getItemList() {
        return itemList;
    }
}
