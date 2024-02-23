public class Deadline extends Task{
    protected String by;

    public Deadline(String description, boolean isDone) {
        super(getTask(description), isDone);
        this.by = getDueDate(description);
    }

    private static String getTask(String description) {
        String[] split = description.split("\\(by:|/by");
        return split[0];
    }

    private static String getDueDate(String description) {
        String[] split = description.split("\\(by:|/by");
        int endIndex = split[1].length();
        if (description.contains(")")) {
            endIndex -= 1;
        }
        return split[1].substring(0, endIndex );
    }
    @Override

    public String getDescription() {
        return super.getDescription() + "(by:" + by + ")";
    }
    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description +
                "(by:" + this.by + ")" ) ;
    }

    @Override
    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description +
                "(by:" + this.by + ")" ) ;
    }
}
