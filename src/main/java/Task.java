public class Task {
    protected String taskName;
    protected int index;
    protected boolean isDone;

    public enum taskType {
        Todo, Deadline, Event
    }

    public Task(String line, int counter) {
        this.taskName = line;
        this.index = counter + 1;
        this.isDone = false;
    }
    public void printCheckbox() {
        if (isDone) {
            System.out.print("[/]");
        } else {
            System.out.print("[ ]");
        }
    }
    public void printTask() {
        System.out.print(index + ".");
        printTasktype();
        printCheckbox();
        System.out.println(" " + taskName);
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("good job! this task is marked as done now: ");
        System.out.println(" > " + this.taskName);
    }

    public void markUndone() {
        if (!isDone) {
            System.out.println("hm, this task (" + this.taskName + ") had not been done yet. wrong one?");
            return;
        }
        this.isDone = false;
        System.out.println("This task is now marked undone: ");
        printCheckbox();
        System.out.println(" > " + this.taskName);
    }

    public void printRespond() {
        System.out.println("added: " + this.taskName);
    }

    public void printTaskcount() {
        int taskCount = Soot.listCounter + 1;
        System.out.println("you now have " + taskCount + " tasks left...");
    }

    public void printTasktype() {
        System.out.print("[NA]");
    }
}