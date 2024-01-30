public class ListItem {
    private String item;
    private int index;
    private boolean isDone;
    public ListItem(String line, int counter) {
        this.item = line;
        this.index = counter + 1;
    }
    public void printCheckbox() {
        if (isDone) {
            System.out.print("✔");
        } else {
            System.out.print(" ");
        }
    }
    public void printItem() {
        System.out.print(index + ". [");
        printCheckbox();
        System.out.println("] " + item);
    }
}