package errorhandle;

import format.Formatting;

public class UserInputErrorOutputHandle {

    protected Formatting format;

    public UserInputErrorOutputHandle() {
        format = new Formatting();
    }

    public void undefinedTaskError() {
        format.dividingLine();
        System.out.println("\tUnknown Command!");
        format.dividingLine();
    }

    public void noTaskContentError(String taskType) {
        format.dividingLine();
        System.out.println("\tOh nooooo!! The description of " + taskType + "is missing!!");
        format.dividingLine();
    }

    public void noSpacingError(String identity) {
        format.dividingLine();
        System.out.println("\tPlease add a spacing between " + identity + " and 'number'");
        format.dividingLine();
    }

    public void inputNotNumberError(String identity) {
        format.dividingLine();
        System.out.println("\tPlease type " + identity + " + 'NUMBER'!");
        format.dividingLine();
    }

    public void requestTaskOutOfBound() {
        format.dividingLine();
        System.out.println("\tOops, you do not have this task");
        format.dividingLine();
    }
}
