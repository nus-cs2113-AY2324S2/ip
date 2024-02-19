import java.util.ArrayList;
import java.util.List;

public class Event extends Task{

    /** Start date of Event */
    protected String start;
    /** End date of Event */
    protected String end;
    
    public Event(String description, String start, String end) throws MissingParamsException{
        super(description);
        this.start = start;
        this.end = end;

        if(hasMissingParams()) {
            List<TaskParams> missingParams = findMissingParams();
            throw new MissingParamsException(missingParams);
        }
    }
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(), this.description, this.start, this.end);
    }

    /**
     * Checks for parameters object is missing.
     * Returns an array of missing parameters.
     *
     * @return List of missing parameters.
     */
    @Override
    public List<TaskParams> findMissingParams() {
        List<TaskParams> missingParams = new ArrayList<>();

        if (description.isEmpty()) {
            missingParams.add(TaskParams.DESCRIPTION);
        }
        if (start.isEmpty()) {
            missingParams.add(TaskParams.START);
        }
        if (end.isEmpty()) {
            missingParams.add(TaskParams.END);
        }

        return missingParams;
    }
}