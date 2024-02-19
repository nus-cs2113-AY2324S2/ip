import java.util.ArrayList;
import java.util.List;

public class ToDo extends Task{

    public ToDo(String description) throws MissingParamsException {
        super(description);

        if (hasMissingParams()) {
            List<TaskParams> missingParams = findMissingParams();
            throw new MissingParamsException(missingParams);
        }
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.getStatusIcon(), this.description);
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

        return missingParams;
    }
}