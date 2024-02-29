package ui;

import exceptions.IllegalNumberOfArguments;
import tasks.ListKeeper;

public class Keywords {
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String TODO = "todo";
    public static final String FROM = " /from ";
    public static final String TO = " /to ";
    public static final String BY = " /by ";
    public static final String LIST = "list";
    public static final String BYE = "bye";
    public static final String DELETE = "delete";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String FIND = "find";

    /**
     * Returns the expected number of inputs for a given keyword.
     * @param keyword the keyword to check
     * @return the expected number of inputs
     */
    private static int getExpectedInputSize(String keyword) {
        switch (keyword) {
        case Keywords.LIST:
            return 1;
        case Keywords.DELETE:
            return 2;
        case Keywords.MARK:
            return 2;
        case Keywords.UNMARK:
            return 2;
        default:
            return 1;
        }
    }

    private static String getSampleInput(String keyword) {
        switch (keyword) {
        case Keywords.LIST:
            return ListKeeper.getSampleListCommand();
        case Keywords.DELETE:
            return ListKeeper.getSampleDeleteCommand();
        case Keywords.MARK:
            return ListKeeper.getSampleMarkCommand();
        case Keywords.UNMARK:
            return ListKeeper.getSampleUnmarkCommand();
        default:
            return "";
        }
    }

    private static boolean hasInputSizeRequirements(String commandType) {
        return commandType.equals(Keywords.LIST) || commandType.equals(Keywords.DELETE)
                || commandType.equals(Keywords.MARK) || commandType.equals(Keywords.UNMARK);
    }

    public static boolean doesInputHaveCorrectNumOfArguments(String[] words) {
        String commandType = words[0];
        if (Keywords.hasInputSizeRequirements(commandType)) {
            int numOfArguments = words.length;
            return numOfArguments == Keywords.getExpectedInputSize(commandType);
        }
        return true;
    }

    public static void verifyInputSize(String[] words) throws IllegalNumberOfArguments {
        String commandType = words[0];
        if (!Keywords.hasInputSizeRequirements(commandType)) {
            return;
        }
        int numOfArgumentsSupplied = words.length;
        int numOfArgumentsExpected = Keywords.getExpectedInputSize(commandType);
        if (numOfArgumentsSupplied == numOfArgumentsExpected) {
            return;
        }
        throw new IllegalNumberOfArguments(numOfArgumentsSupplied, numOfArgumentsExpected, getSampleInput(commandType));
    }
}
