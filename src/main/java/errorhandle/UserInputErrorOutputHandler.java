package errorhandle;

import format.Formatter;

public class UserInputErrorOutputHandler {

    protected Formatter format;

    public UserInputErrorOutputHandler() {
        format = new Formatter();
    }

    public void printUndefinedTaskError() {
        format.dividingLine();
        System.out.println("\tUnknown Command!");
        format.dividingLine();
    }

    public void printNoTaskContentError(String taskType) {
        format.dividingLine();
        System.out.println("\tOh nooooo!! The description of " + taskType + "is missing!!");
        format.dividingLine();
    }

    public void printNoSpacingError(String identity) {
        format.dividingLine();
        System.out.println("\tPlease add a spacing between " + identity + " and 'number'");
        format.dividingLine();
    }

    public void printInputNotNumberError(String identity) {
        format.dividingLine();
        System.out.println("\tPlease type " + identity + " + 'NUMBER'!");
        format.dividingLine();
    }

    public void printRequestTaskOutOfBoundError() {
        format.dividingLine();
        System.out.println("\tOops, you do not have this task");
        format.dividingLine();
    }
}
