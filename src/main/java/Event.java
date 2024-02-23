public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, boolean isDone) {
        super(getTask(description), isDone);
        this.from = getFrom(description);
        this.to = getTo(description);
    }

    private static String getTask(String description) {
        String[] split = description.split("\\(from|/from");
        return split[0];
    }

    private static String getFrom(String description) {
        String[] split = description.split("\\(from: |to: |/from |/to ");
        return split[1];
    }

    private static String getTo(String description) {
        String[] split = description.split("\\(from: |to: |/from |/to ");
        int endIndex = split[2].length();
        if (description.contains(")")) {
            endIndex -= 1;
        }
        return split[2].substring(0, endIndex );
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(from: " + from + "to: " + to + ")";
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description +
                "(from: " + this.from + "to: " + this.to + ")") ;
    }

    @Override
    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description +
                "(from: " + this.from + "to: " + this.to + ")") ;
    }
}
