import java.util.ArrayList;

public class List {
    private ArrayList<Task> list = new ArrayList<Task>();
    private int size = 0;

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
            System.out.print(i + 1 + ". ");
            System.out.print("[" + (list.get(i).getStatus() ? "X" : " ") + "] ");
            System.out.println(list.get(i).getName());
        }
    }

    /**
     * Adds a task to the list.
     * 
     * @param taskName The item to be added.
     * @return None
     */
    public void addItem(String taskName) {
        // Create new task object, add to list and update size
        Task task = new Task(taskName);
        list.add(task);
        size++;

        // Echo the input if not exiting
        System.out.print("added: ");
        System.out.println(taskName);
        // Add task to the list
    }

    /**
     * Sets the status of a task.
     * 
     * @param index The index of the task to be marked.
     * @param status The updated status of the task.
     * @return None
     */
    public void setItemStatus(int index, boolean status) {
        try {
            list.get(index - 1).setStatus(status);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Huhhhhhhh? I cannot find!");
        }
    }
}
