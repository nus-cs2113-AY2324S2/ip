public class ExceptionHandler {
    public static void handleInvalidCommandException() {
        Printer.printIndent("Invalid command.");
    }

    public static void handleInvalidTodoSyntaxException() {
        Printer.printIndent("Invalid todo syntax.");
    }

    public static void handleInvalidDeadlineSyntaxException() {
        Printer.printIndent("Invalid deadline syntax.");
    }

    public static void handleInvalidEventSyntaxException() {
        Printer.printIndent("Invalid event syntax.");
    }

    public static void handleInvalidMarkSyntaxException() {
        Printer.printIndent("Invalid mark syntax.");
    }

    public static void handleInvalidUnmarkSyntaxException() {
        Printer.printIndent("Invalid unmark syntax.");
    }
}
