package uwunzhe.util;

import java.util.ArrayList;

import uwunzhe.tasks.Task;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.tasks.TaskType;
import uwunzhe.tasks.Todo;
import uwunzhe.tasks.Deadline;
import uwunzhe.tasks.Event;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();
    private int size = 0;

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
        int size = this.getSize();
        System.out.println("We only have uhhhh " + size
                + " more thing" + (size > 1 ? "s" : "") + " left to go!");
    }   
   
    /**
     * Prints the list.
     * 
     * @param None
     * @return None
     */
    public void printList() {
        // Check if list is empty
        if (size == 0) {
            System.out.println("You KAIBAI-ing");
            return;
        }

        System.out.println("Yay! List!");
        for (int i = 0; i < size; i++) {
            Task task = list.get(i);
            System.out.print(i + 1 + ".");
            System.out.println(task);
        }
    }

    /**
    * Marks a task as done or not done.
    * 
    * @param index The index of the task in the list.
    * @param status The status of the task.
    * @return None
    */
    public void setItemStatus(String command, String indexString) throws UwunzheException {
        try {
            int index = Integer.parseInt(indexString) - 1;
            boolean newStatus = command.equals("mark");
            boolean prevStatus = list.get(index).getStatus();

            if (prevStatus == newStatus) {
                throw new UwunzheException("No no no, not again...");
            }

            list.get(index).setStatus(newStatus);

        } catch (IndexOutOfBoundsException e) {
            throw new UwunzheException("Huhhhhhhh? I cannot find!");

        } catch (NumberFormatException e) {
            throw new UwunzheException("Something something not adding up...");
        }
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

            System.out.println("Okey dokey here we go");
            System.out.print(" ");
            System.out.println(list.get(size - 1));
            this.printSize();

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
        size++;
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
        size++;
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
        size++;
    }
}
