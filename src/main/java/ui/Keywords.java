package ui;

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

    public static int getExpectedInputSize(String keyword) {
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
}
