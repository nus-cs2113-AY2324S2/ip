public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    protected static Deadline getDeadline(String input) {
        String keyword = "/by";
        int keywordIndex = input.indexOf(keyword);
        String taskDeadline = input.substring(keywordIndex + keyword.length()).trim();
        String description = input.substring(0, keywordIndex).trim();
        return new Deadline(description, taskDeadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)\n", super.toString(), this.by);
    }
}

