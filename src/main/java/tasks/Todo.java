// Subclass of Task

package tasks;
public class Todo extends Task {

    // Constructor
    public Todo(String description, int index) {
        super(description, index);
        toPrint();
    }

    @Override
    public String toString() {
        String[] splitLine = description.split("\\s+");
        StringBuilder action = new StringBuilder();

        //obtain the action of the task
        for (int i = 1; i < splitLine.length; i += 1) {
            action.append(splitLine[i]).append(" ");
        }
        return "[T]" + "[" + this.getStatusIcon() + "] " + action;
    }

    public void toPrint() {
        printHeaders();
        System.out.println(this);
    }
}


