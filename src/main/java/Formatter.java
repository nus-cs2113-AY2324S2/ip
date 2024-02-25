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
        printWrapper("〰");
        System.out.println("\t Hello! I'm " + botName + emojiGrinning);
        System.out.println("\t What can I do for you?");
        printWrapper("〰");
    }

    public static void printGoodbyeMsg() {
        printWrapper("〰");
        System.out.println("\t Bye. Hope to see you again soon!" + " ツ");
        printWrapper("〰");
    }

    public static void printListEmpty() {
        System.out.println("\t List is empty. Add tasks using commands \"todo\", \"deadline\", \"event\".");
    }

    public static void printListAll() {
        printWrapper("〓");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < CommandExecutor.tasks.size(); i++) {
            System.out.printf(" \t%d. %s\n", (i + 1), CommandExecutor.tasks.get(i));
        }
        printWrapper("〓");
    }

    public static void printMarkDoneNotif(CommandParser readUserCommand) {
        int userSelectedIndex = Integer.parseInt(readUserCommand.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.tasks.size() && userSelectedIndex > 0) {
            CommandExecutor.tasks.get(userSelectedIndex - 1).markAsDone();

            System.out.println("\t Nice! I've marked this task as done:");
            System.out.printf("\t %s.\n", CommandExecutor.tasks.get(userSelectedIndex - 1));
        } else {
            printErrorIndexOutOfRange();
        }
    }

    public static void printMarkUndoneNotif(CommandParser readUserCommand) {
        int userSelectedIndex = Integer.parseInt(readUserCommand.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.tasks.size() && userSelectedIndex > 0) {
            CommandExecutor.tasks.get(userSelectedIndex - 1).markAsNotDone();

            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.printf("\t %s.\n", CommandExecutor.tasks.get(userSelectedIndex - 1));
        } else {
            printErrorIndexOutOfRange();
        }
    }
    public static void printTaskNotif(Task newTask) {
        printWrapper("_");
        System.out.println("\t Got it. I've added this task:");
        System.out.printf("\t\t%s\n", newTask);
        System.out.printf("\t Now you have %d tasks in the list.\n",CommandExecutor.tasks.size());
        printWrapper("_");
    }
    public static void printDeleteNotif(Task removedTask) {
        printWrapper("_");
        System.out.println("\t Noted. I've removed this task:");
        System.out.printf("\t\t%s\n", removedTask);
        System.out.printf("\t Now you have %d tasks in the list.\n",CommandExecutor.tasks.size());
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
        System.out.println("\t CommandExecutor: Bad Token Error, please check your arguments");
    }
    public static void printErrorArgumentsMismatch(String userCommandName, int userArgumentCount, int correctArgumentCount) {
        System.out.printf("\t SyntaxAnalyser: Command %s contains too %s arguments. Given: %d - Expected: %d\n",
                userCommandName, 
                userArgumentCount < correctArgumentCount ? "few" : "many", 
                userArgumentCount, correctArgumentCount);
    }
    public static void printErrorWrongArgumentType(String COMMAND_NAME, String regex, int argumentPosition) {
        System.out.printf("\t SyntaxAnalyser: %s expects the %dth argument to be %s\n",
                COMMAND_NAME, argumentPosition + 1, regex);
    }
    public static void printErrorIndexOutOfRange() {
        System.out.println("\t List: Selected index not in range. Try again.");
    }
}
