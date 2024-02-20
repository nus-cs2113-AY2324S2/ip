public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
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
