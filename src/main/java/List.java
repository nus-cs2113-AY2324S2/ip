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
        Task task = new Task(taskName);
        list.add(task);
        size++;
    }

    public void setItemStatus(int index, boolean status) {
        list.get(index - 1).setStatus(status);
    }
}
