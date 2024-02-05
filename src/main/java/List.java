import java.util.ArrayList;

public class List {
    private ArrayList<Task> list = new ArrayList<Task>();
    private int size = 0;

    /**
    * Marks a task as done or not done.
    * 
    * @param index The index of the task in the list.
    * @param status The status of the task.
    * @return None
    */
    public void setItemStatus(int index, boolean status) {
        try {
            list.get(index - 1).setStatus(status);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Huhhhhhhh? I cannot find!");
        }
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
     * Adds a task to the list.
     * 
     * @param taskName The name of the task.
     * @return None
     */
    public void addItem(String taskName) {
        // Create new task object, add to list and update size
        Task task = new Task(taskName);
        list.add(task);
        size++;

        // Echo the input
        System.out.print("added: ");
        System.out.println(taskName);

        this.printSize();
    }

    /**
     * Adds a task of specified type to the list.
     * Overloaded method.
     * 
     * @param taskName
     * @param type
     * @return None
     */
    public void addItem(String taskName, String type) {
        if (type.equals("T")) {
            Todo task = new Todo(taskName);
            list.add(task);
            size++;
        } else if (type.equals("D")) {
            Deadline task = new Deadline(taskName);
            list.add(task);
            size++;
        } else if (type.equals("E")) {
            Event task = new Event(taskName);
            list.add(task);
            size++;
        } else {
            System.out.println("HUHHH? What is this even?");
            return;
        }

        this.printSize();
    }
}
