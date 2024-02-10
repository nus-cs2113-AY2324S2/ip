public class Formatter {
    final static String botName = "Hirofumi";

    public static void printWelcomeMsg() {
        System.out.println("************************************************************");
        System.out.println(" Hello! I'm " + botName);
        System.out.println(" What can I do for you?");
        System.out.println("************************************************************");
    }

    public static void printGoodbyeMsg() {
        System.out.println("************************************************************");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("************************************************************");
    }

    public static void printListEmpty() {
        System.out.println("List is empty.");
    }

    public static void printListAll() {
        for (int i = 0; i < CommandExecutor.listCount; i++) {
            System.out.print((i + 1) + ".");
            System.out.println(CommandExecutor.tasks[i]);
        }
    }

    public static void printMarkDoneNotif(CommandParser readUserCommand) {
        int userSelectedIndex = Integer.parseInt(readUserCommand.getArgumentTokens()[0]);
        if (userSelectedIndex < CommandExecutor.listCount && userSelectedIndex > 0) {
            CommandExecutor.tasks[userSelectedIndex - 1].markAsDone();

            System.out.println("Nice! I've marked this task as done:");
            System.out.print((userSelectedIndex) + ".");
            System.out.printf("[%s] ", CommandExecutor.tasks[userSelectedIndex - 1].getStatusIcon());
            System.out.println(CommandExecutor.tasks[userSelectedIndex - 1].description);
        } else {
            System.out.println("Error, selected index not in range. Try again.");
        }
    }

    public static void printMarkUndoneNotif(CommandParser readUserCommand) {
        int userSelectedIndex = Integer.parseInt(readUserCommand.getArgumentTokens()[0]);
        if (userSelectedIndex < CommandExecutor.listCount && userSelectedIndex > 0) {
            CommandExecutor.tasks[userSelectedIndex - 1].markAsNotDone();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.print((userSelectedIndex) + ".");
            System.out.printf("[%s] ", CommandExecutor.tasks[userSelectedIndex - 1].getStatusIcon());
            System.out.println(CommandExecutor.tasks[userSelectedIndex - 1].description);
        } else {
            System.out.println("Error, selected index not in range. Try again.");
        }
    }

    public static void printTodoNotif(CommandParser readUserCommand) {
        System.out.println("added: " + CommandExecutor.tasks[CommandExecutor.listCount - 1]);
    }

    public static void printDeadlineNotif(CommandParser readUserCommand) {
        System.out.println("added: " + CommandExecutor.tasks[CommandExecutor.listCount - 1]);
    }

    public static void printEventNotif(CommandParser readUserCommand) {
        System.out.println("added: " + CommandExecutor.tasks[CommandExecutor.listCount - 1]);
    }

    public static void printErrorWrongCommand() {
        System.out.println("CommandParser: Command not found");
    }

    public static void printErrorExecutionFail() {
        System.out.println("CommandExecutor: Command could not be executed");
        System.out.println("Try again");
    }

    public static void printErrorUnknown() {
        System.out.println("Unexpected error");
    }

    public static void printErrorBadTokens() {
        System.out.println("Bad Token Error");
    }
}
