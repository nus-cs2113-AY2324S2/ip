public class ListItem {
    private String task;
    private int index;
    private boolean isDone;
    public ListItem(String line, int counter) {
        this.task = line;
        this.index = counter + 1;
    }
    public void printCheckbox() {
        if (isDone) {
            System.out.print("/");
        } else {
            System.out.print(" ");
        }
    }
    public void printItem() {
        System.out.print(index + ". [");
        printCheckbox();
        System.out.println("] " + task);
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("good job! this task is marked as done now: ");
        System.out.println(" > " + this.task);
    }
}