public class Todo extends Task {

    public Todo (String description) {
        super(description);
        this.taskType = "[T]";
    }


    @Override
    public void printTask() {
        System.out.println(taskType + getStatusIcon() + " " + description);
    }
}

