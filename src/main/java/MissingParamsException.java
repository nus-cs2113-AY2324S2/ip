import java.util.List;

public class MissingParamsException extends Exception {
    public List<TaskParams> missingParams;

    public MissingParamsException(List<TaskParams> missingParams) {
        this.missingParams = missingParams;
    }

    @Override
    public String toString() {
        StringBuilder errorMessage = new StringBuilder();

        for (TaskParams param : missingParams) {
            switch (param) {
            case DESCRIPTION:
                errorMessage.append("description ");
                break;
            case BY:
                errorMessage.append("by ");
                break;
            case START:
                errorMessage.append("from ");
                break;
            case END:
                errorMessage.append("to ");
                break;
            }
        }

        return errorMessage.toString().trim();
    }
}
