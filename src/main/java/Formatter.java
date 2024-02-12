public class Formatter {
    final static String botName = "Howard Smith";
    final static String emojiGrinning = "\uD83D\uDE00";
    final static String emojiConfused = "\uD83D\uDE35\u200D\uD83D\uDCAB";

    public static void printWrapper(String wrapWith) {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print(wrapWith);
        }
        System.out.print("\n");
    }

    public static void printWelcomeMsg() {
        printWrapper("\u3030");
        System.out.println("\t Hello! I'm " + botName + emojiGrinning);
        System.out.println("\t What can I do for you?");
        printWrapper("\u3030");
    }

    public static void printGoodbyeMsg() {
        printWrapper("\u3030");
        System.out.println("\t Bye. Hope to see you again soon!" + " \u30c4");
        printWrapper("\u3030");
    }

    public static void printListEmpty() {
        System.out.println("\t List is empty. Add tasks using commands \"todo\", \"deadline\", \"event\".");
    }

    public static void printListAll() {
        printWrapper("\u3013");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < CommandExecutor.listCount; i++) {
            System.out.printf(" \t%d. %s\n", (i + 1), CommandExecutor.tasks[i].toString());
        }
        printWrapper("\u3013");
    }

    public static void printMarkDoneNotif(CommandParser readUserCommand) {
        int userSelectedIndex = Integer.parseInt(readUserCommand.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.listCount && userSelectedIndex > 0) {
            CommandExecutor.tasks[userSelectedIndex - 1].markAsDone();

            System.out.println("\t Nice! I've marked this task as done:");
            System.out.printf("\t %s.\n", CommandExecutor.tasks[userSelectedIndex - 1]);
        } else {
            printErrorIndexOutOfRange();
        }
    }

    public static void printMarkUndoneNotif(CommandParser readUserCommand) {
        int userSelectedIndex = Integer.parseInt(readUserCommand.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.listCount && userSelectedIndex > 0) {
            CommandExecutor.tasks[userSelectedIndex - 1].markAsNotDone();

            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.printf("\t %s.\n", CommandExecutor.tasks[userSelectedIndex - 1]);
        } else {
            printErrorIndexOutOfRange();
        }
    }

    public static void printTaskNotif(CommandParser readUserCommand) {
        printWrapper("_");
        System.out.println("\t Got it. I've added this task:");
        System.out.printf("\t\t%s\n", CommandExecutor.tasks[CommandExecutor.listCount - 1].toString());
        System.out.printf("\t Now you have %d tasks in the list.\n", CommandExecutor.listCount);
        printWrapper("_");
    }

    public static void printErrorWrongCommand() {
        System.out.println("\t CommandParser: Command not found ");
    }

    public static void printErrorExecutionFail() {
        System.out.println("\t CommandExecutor: Command could not be executed " + emojiConfused);
        System.out.println("\t Try again");
    }

    public static void printErrorUnknown() {
        System.out.println("\t CommandExecutor: Unexpected error " + emojiConfused);
    }

    public static void printErrorBadTokens() {
        System.out.println("\t CommandExecutor: Bad Token Error ");
    }

    public static void printErrorArgumentsMismatch() {
        System.out.println("\t SyntaxChecker: Arguments Mismatch Error ");
    }

    public static void printErrorIndexOutOfRange() {
        System.out.println("\t List: Selected index not in range. Try again.");
    }
}
