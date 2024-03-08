package duke;

import java.util.ArrayList;
import java.util.List;

public class Deadline extends Task{

    /** Date by which task should be completed */
    protected String by;

    public Deadline(String description, String by) throws MissingParamsException {
        super(description);
        this.by = by;

        if(hasMissingParams()) {
            List<TaskParams> missingParams = findMissingParams();
            throw new MissingParamsException(missingParams);
        }
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.getStatusIcon(), this.description, this.by);
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
        if (by.isEmpty()) {
            missingParams.add(TaskParams.BY);
        }
        return missingParams;
    }
}