public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public void printTask() {
        if (this.isDone) {
            System.out.println("[T][X] " + this.description);
        } else {
            System.out.println("[T][ ] " + this.description);
        }
    }
}
