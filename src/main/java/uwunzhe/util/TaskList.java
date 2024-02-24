package uwunzhe.util;

import java.util.ArrayList;

import uwunzhe.tasks.Task;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.tasks.TaskType;
import uwunzhe.tasks.Todo;
import uwunzhe.tasks.Deadline;
import uwunzhe.tasks.Event;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static int size = 0;

    /**
     * List access operator.
     * 
     * @param index
     * @return Task
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Returns the size of the list.
     * 
     * @param None
     * @return The size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Prints the size of the list with flavour text.
     * 
     * @param None
     * @return None
     */
    public void printSize() {
        int size = getSize();
        if (size == 0) {
            Ui.println("No more taskies! Yay!");
        } else {
        Ui.println("We only have uhhhh " + size
                + " more thing" + (size > 1 ? "s" : "") + " left to go!");
        }
    }

    /**
    * Marks a task as done or not done.
    * 
    * @param command The command from the user.
    * @param index The index of the task in the list.
    * @return None
    */
    public void setItemStatus(String command, int index) throws UwunzheException {
        boolean newStatus = command.equals("mark");
        boolean prevStatus = list.get(index).getStatus();
        
        // Check if completion status is already the same
        if (prevStatus == newStatus) {
            throw new UwunzheException("No no no, not again...");
        }

        list.get(index).setStatus(newStatus);
    }

    /**
     * Adds a task of specified type to the list.
     * Overloaded method.
     * 
     * @param taskName
     * @param type
     * @return None
     * @throws UwunzheException
     */
    public void addItem(String command, String description) throws UwunzheException {
        // Error if empty string
        if (description.equals("")) {
            throw new UwunzheException("ACKSHUALLY you are missing something...");
        }
        
        // Convert String command to TaskType enum
        TaskType type = TaskType.valueOf(command.toUpperCase());
        try {
            switch (type) {
            case TODO:
                addTodo(description);
                break;

            case DEADLINE:
                addDeadline(description);
                break;

            case EVENT:
                addEvent(description);
                break;
            }

            size++;
            Ui.println("Okey dokey here we go");
            Ui.printlnTask(this, size - 1, " ");
            printSize();

        } catch (IndexOutOfBoundsException e) {
            throw new UwunzheException("ACKSHUALLY you are missing something...");
        }
    }

    /**
     * Adds a task object to the list. Updates size.
     * 
     * @param task
     * @return None
     */
    public void addItem(Task task) {
        list.add(task);
        size++;
    }

    /**
     * Adds a todo to the list. Updates size.
     * 
     * @param description
     * @return None
     */
    public void addTodo(String description) {
        // String name = description;
        list.add(new Todo(description));
    }

    /**
     * Adds a deadline to the list. Updates size.
     * 
     * @param description
     * @return None
     */
    public void addDeadline(String description) {
        String[] nameEnd = description.split(" /by ", 2);
        String taskName = nameEnd[0];
        String taskEnd = nameEnd[1];

        list.add(new Deadline(taskName, taskEnd));
    }

    /**
     * Adds an event to the list. Updates size.
     * 
     * @param description
     * @return None
     */
    public void addEvent(String description) {
        String[] nameTimes = description.split(" /from ", 2);
        String times = nameTimes[1];

        String[] startEnd = times.split(" /to ", 2);
        String taskName = nameTimes[0];
        String taskStart = startEnd[0];
        String taskEnd = startEnd[1];

        list.add(new Event(taskName, taskStart, taskEnd));
    }

    /**
     * Deletes a task from the list.
     * 
     * @param index
     * @return None
     */
    public void deleteItem(int index) {
        Task toRemove = list.get(index);
        list.remove(index);   
        size--;
        
        Ui.println("There goes that task!");
        Ui.printlnTask(toRemove, " ");
        printSize();
    }
}
