public class Deadline extends Task {
    protected String type = "D";
    protected String deadline;
    /**
     * Constructs a deadline task with a given description
     *
     * @param description description set by user
     *
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    /**
     * Returns a string indicating the type of the deadline object
     *
     * @return Type of deadline object
     *
     */
    @Override
    public String getType() {
        return type;
    }
    /**
     * Returns a string representing a deadline object in the list
     *
     * @return String representation of deadline in list
     *
     */
    @Override
    public String toString() {
        return String.format("%s (%s)", super.toString(), deadline);
    }
    /**
     * Returns a string representing a deadline object in the cache file
     *
     * @param isFormatCache Formats the deadline into cache file format if true
     * @return String representation of deadline in cache file
     *
     */
    @Override
    public String toString(boolean isFormatCache) {
        return String.format("%s | %s", super.toString(isFormatCache), deadline);
    }
}
