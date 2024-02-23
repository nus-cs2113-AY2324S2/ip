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
            System.out.println("No more taskies! Yay!");
        } else {
        System.out.println("We only have uhhhh " + size
                + " more thing" + (size > 1 ? "s" : "") + " left to go!");
        }
    }

    /**
     * Prints the task.
     * 
     * @param task
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints the task with leading text.
     * 
     * @param task
     * @param leading
     */
    public void printTask(Task task, String leading) {
        System.out.print(leading);
        System.out.println(task);
    }

    /**
     * Prints the task at the specified index.
     * 
     * @param index
     */
    public void printTask(int index) {
        System.out.println(list.get(index));
    }

    /**
     * Prints the task at the specified index with leading text.
     * 
     * @param index
     * @param leading
     */
    public void printTask(int index, String leading) {
        System.out.print(leading);
        System.out.println(list.get(index));
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

            size++;
            System.out.println("Okey dokey here we go");
            printTask(size - 1, " ");
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
     * @throws UwunzheException
     */
    public void deleteItem(String index) throws UwunzheException {
        try {
            int i = Integer.parseInt(index) - 1;
            Task toRemove = list.get(i);
            list.remove(i);   
            size--;
            
            System.out.println("There goes that task!");
            printTask(toRemove, " ");
            printSize();

        } catch (IndexOutOfBoundsException e) {
            throw new UwunzheException("Huhhhhhhh? I cannot find!");

        } catch (NumberFormatException e) {
            throw new UwunzheException("Something something not adding up...");
        }
    }
}
