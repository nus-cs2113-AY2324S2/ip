public class EventTask extends Task{
    protected static String eventStart;
    protected static String eventEnd;

    public EventTask(boolean status, String description) {
        this.isDone = status;
        this.description = setEventTask(description);
        this.type = "E";
    }

    /**
     * setEventTask parse argument of inputted query,
     * assign start and end of event to eventStart and eventEnd
     * @param argument
     * @return String description of eventTask
     */
    public String setEventTask(String argument) {
        // find index of '/'
        int firstSlash = argument.indexOf('/');
        int secondSlash = argument.indexOf('/', firstSlash + 1);
        eventStart = argument.substring(firstSlash + 6, secondSlash - 1);
        eventEnd = argument.substring(secondSlash + 4);
        return argument.substring(0, firstSlash - 1);
    }

    public String getEventStart() {
        return eventStart;
    }
    public String getEventEnd() {
        return eventEnd;
    }

    @Override
    public void printTask() {
        if (isDone) {
            System.out.println("[" + this.type + "][X] " + this.getDescription() + " (from: " + this.getEventStart() + " to: " + this.getEventEnd() + ")");
        } else {
            System.out.println("[" + this.type + "][ ] " + this.getDescription() + " (from: " + this.getEventStart() + " to: " + this.getEventEnd() + ")");
        }
    }
}
