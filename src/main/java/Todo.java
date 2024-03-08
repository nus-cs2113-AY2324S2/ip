public class Todo extends Task {
    protected String type = "T";
    /**
     * Constructs a todo task with a given description
     *
     * @param description description set by user
     *
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns a string indicating the type of the todo object
     *
     * @return Type of todo object
     *
     */
    @Override
    public String getType() {
        return type;
    }
    /**
     * Returns a string representing a todo object in the list
     *
     * @return String representation of todo in list
     *
     */
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
    /**
     * Returns a string representing a todo object in the cache file
     *
     * @param isFormatCache Formats the todo into cache file format if true
     * @return String representation of todo in cache file
     *
     */
    @Override
    public String toString(boolean isFormatCache) {
        return String.format("%s", super.toString(isFormatCache));
    }
}
