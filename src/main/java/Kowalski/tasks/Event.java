package Kowalski.tasks;

public class Event extends Task {
    protected String startDayAndTime;
    protected String endDayAndTime;

    public Event (String description, String startDayAndTime, String endDayAndTime) {
        super(description);
        this.startDayAndTime = startDayAndTime;
        this.endDayAndTime = endDayAndTime;
    }

    /**
     * Method obtains the start date and time of the event
     * @return String of the start date and time
     */
    public String getStartDayAndTime() {
        return startDayAndTime;
    }

    /**
     * Method obtains the end date and time of the event
     * @return String of the end date and time
     */
    public String getEndDayAndTime() {
        return endDayAndTime;
    }

    @Override
    public String textFileInputString(){
        return String.format("E | %s | %s | %s - %s",
                isDone? "X" : "0",
                getDescription().trim(),
                getStartDayAndTime(),
                getEndDayAndTime());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDayAndTime + " to: " + endDayAndTime + ")";
    }
}
