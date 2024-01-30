public class ListItem {
    private String item;
    private int index;
    public ListItem(String line, int counter) {
        this.item = line;
        this.index = counter + 1;
    }

    public void printItem() {
        System.out.println(index + ". " + item);
    }
}