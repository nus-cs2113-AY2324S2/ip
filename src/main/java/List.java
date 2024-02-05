import java.util.ArrayList;

public class List {
    private ArrayList<String> list = new ArrayList<String>();
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

        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    /**
     * Adds an item to the list.
     * 
     * @param item The item to be added.
     * @return None
     */
    public void addItem(String item) {
        list.add(item);
        size++;
    }
}
