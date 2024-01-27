public class List {
    private String[] items;
    private int size;

    public List() {
        items = new String[100];
        size = 0;
    }

    public void addItem(String item) {
        items[size] = item;
        size++;
        System.out.println("    ________________________________________________");
        System.out.println("    added: " + item);
        System.out.println("    ________________________________________________\n");
    }

    public void printList() {
        System.out.println("    ________________________________________________");
        for (int i = 0; i < size; i++) {
            System.out.println("    " + (i + 1) + ". " + items[i]);
        }
        System.out.println("    ________________________________________________\n");
    }
}
