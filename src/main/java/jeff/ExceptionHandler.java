package jeff;

import java.io.IOException;

public class ExceptionHandler {
    public static void handleInvalidCommandException() {
        Printer.printIndent("Invalid command.");
        Printer.printIndent("List of valid commands:");
        Printer.printIndent("list, todo, deadline, event, mark, unmark, bye");
    }

    public static void handleInvalidTodoSyntaxException() {
        Printer.printIndent("Invalid todo syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("todo [description]");
    }

    public static void handleInvalidDeadlineSyntaxException() {
        Printer.printIndent("Invalid deadline syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("deadline [description] /by [yyyy-mm-dd]");
    }

    public static void handleInvalidEventSyntaxException() {
        Printer.printIndent("Invalid event syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("event [description] /from [from] /to [to]");
    }

    public static void handleInvalidMarkSyntaxException() {
        Printer.printIndent("Invalid mark syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("mark [any number from 1 to " + TaskList.size() + "]");
    }

    public static void handleInvalidUnmarkSyntaxException() {
        Printer.printIndent("Invalid unmark syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("unmark [any number from 1 to " + TaskList.size() + "]");
    }

    public static void handleInvalidDeleteSyntaxException() {
        Printer.printIndent("Invalid delete syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("delete [any number from 1 to " + TaskList.size() + "]");
    }

    public static void handleInvalidFindSyntaxException() {
        Printer.printIndent("Invalid find syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("find [text to find]");
    }

    public static void handleFileNotFoundException() {
        Storage.createNewFile();
        Printer.printIndent("File not found. data/jeff.txt created.");
        Printer.printDivider();
    }

    public static void handleCorruptFileException() {
        Printer.printIndent("File is corrupt. Content not in expected format.");
        Printer.printDivider();
        System.exit(1);
    }

    public static void handleIOException(IOException e) {
        Printer.printIndent("Something went wrong: " + e.getMessage());
        Printer.printDivider();
        System.exit(1);
    }

    public static void handleUnableToMarkException() {
        Printer.printUnableToMark();
    }

    public static void handleUnableToUnmarkException() {
        Printer.printUnableToUnmark();
    }

    public static void handleUnableToDeleteException() {
        Printer.printUnableToDelete();
    }
}
