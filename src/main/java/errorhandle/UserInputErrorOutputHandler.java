package errorhandle;

import format.Formatter;

public class UserInputErrorOutputHandler {

    protected Formatter formatter;

    public UserInputErrorOutputHandler() {
        formatter = new Formatter();
    }

    public void printUndefinedTaskError() {
        formatter.printDividingLine();
        System.out.println("\tUnknown Command!");
        formatter.printDividingLine();
    }

    public void printNoTaskContentError(String taskType) {
        formatter.printDividingLine();
        System.out.println("\tOh nooooo!! The description of " + taskType + "is missing!!");
        formatter.printDividingLine();
    }

    public void printNoSpacingError(String identity) {
        formatter.printDividingLine();
        System.out.println("\tPlease add a spacing between " + identity + " and 'number'");
        formatter.printDividingLine();
    }

    public void printInputNotNumberError(String identity) {
        formatter.printDividingLine();
        System.out.println("\tPlease type " + identity + " + 'NUMBER'!");
        formatter.printDividingLine();
    }

    public void printRequestTaskOutOfBoundError() {
        formatter.printDividingLine();
        System.out.println("\tOops, you do not have this task");
        formatter.printDividingLine();
    }
}
