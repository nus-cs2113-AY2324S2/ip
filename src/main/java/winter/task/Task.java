package winter.task;
public class Task {
    boolean isMarked;
    int order;
    String taskName;

    public String doneCheckbox ="[ ]";

    Task(int order, boolean marked, String taskName) {
        this.order = order;
        this.isMarked = marked;
        this.taskName = taskName;
    }

    // Method for marking a task complete
    public void mark() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        this.isMarked = true;
        this.doneCheckbox = "[X]";
        System.out.println("Woohoo! I've marked this task as done:");
        System.out.print(indent);
        System.out.println(this.doneCheckbox + this.taskName);
        System.out.print(line);
    }
    // Method for unmarking a class
    public void unmark() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        this.isMarked = false;
        this.doneCheckbox = "[ ]";
        System.out.println("Alright! I've marked this task as incomplete:");
        System.out.print(indent);
        System.out.println(this.doneCheckbox+ " " + this.taskName);
        System.out.print(line);
    }
    // Getter method for order
    public int getOrder() {
        return order;
    }
    // Getter method for taskName
    public String getTaskName() {
        return taskName;
    }
    // Getter method for marked
    public boolean isMarked() {
        return isMarked;
    }

    public String getType() {
        return "";
    }

    /*public String toString () {
        String checkboxes = "[ ] [ ]";
        return checkboxes + taskName;
    }*/

    public String getEndTime() {
        return "";
    }

    public String getStartTime() {
        return "";
    }
}