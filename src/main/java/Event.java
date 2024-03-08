public class Event extends Task {
    protected String type = "E";
    protected String startTime;
    protected String endTime;
    /**
     * Constructs an event task with a given description
     *
     * @param description description set by user
     *
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Returns a string indicating the type of the event object
     *
     * @return Type of event object
     *
     */
    @Override
    public String getType() {
        return type;
    }
    /**
     * Returns a string representing an event object in the list
     *
     * @return String representation of event in list
     *
     */
    @Override
    public String toString() {
        return String.format("%s (%s %s)", super.toString(), startTime, endTime);
    }
    /**
     * Returns a string representing an event object in the cache file
     *
     * @param isFormatCache Formats the event into cache file format if true
     * @return String representation of event in cache file
     *
     */
    @Override
    public String toString(boolean isFormatCache) {
        return String.format("%s | %s | %s", super.toString(isFormatCache), startTime, endTime);
    }
}
