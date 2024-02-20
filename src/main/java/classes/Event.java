package classes;

import exceptions.MimiException;

public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public static Event processEvent(String input) throws
            MimiException.IncorrectFormat,
            MimiException.InsufficientParameters {

        // Further process the deadline input
        try {
            String[] inputs = input.split("/from", 2);
            String[] duration = inputs[1].split("/to", 2);

            // Check if the inputs are complete
            String taskName = inputs[0];
            String startDate = duration[0].strip();
            String endDate = duration[1].strip();

            // Throws an error if parameters is incomplete
            if (taskName.isBlank() || startDate.isBlank() || endDate.isBlank()) {
                throw new MimiException.IncorrectFormat(MimiException.INCORRECT_EVENT_FORMAT);
            }

            return new Event(taskName, startDate, endDate);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS);
        }
    }

    @Override
    public String toString(){
        String duration = "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")";
        return "[E]" + super.toString() + duration;
    }
}
