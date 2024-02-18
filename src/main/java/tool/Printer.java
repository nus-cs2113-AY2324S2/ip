package tool;

import nyanbot.task.Task;
import java.util.ArrayList;

public class Printer {
    protected static final String LINE = "____________________";
    protected static final String SIKE_MESSAGE = "lmao u got siked wwwwwwwwww";
    protected static final String MARK_MESSAGE = "はい、markしました！";
    protected static final String UNMARK_MESSAGE = "はい、UN-markしました！";
    protected static final String MARK_USAGE_MESSAGE = "使い方：mark [index]";
    protected static final String UNMARK_USAGE_MESSAGE = "使い方：unmark [index]";
    protected static final String DELETE_MESSAGE = "分かりました！deleteするをsuccessful desu!";
    protected static final String DELETE_USAGE_MESSAGE = "使い方: delete [index]";
    protected static final String DELETE_FOLLOW_UP_MESSAGE = "今は tasks remaining:";
    protected static final String TODO_USAGE_MESSAGE = "使い方: todo [description]";
    protected static final String DEADLINE_USAGE_MESSAGE = "使い方：deadline [description] /[date]";
    protected static final String EVENT_USAGE_MESSAGE = "使い方：event [description] /[start] /[end]";
    protected static final String BYE_MESSAGE = "じゃー、またね〜！！\nsee you again♡";
    protected static final String GREET_MESSAGE = "お帰りなさいませ、ご主人様。ニャンー♡♡";
    protected static final String HELP_MESSAGE = "use \"help\" for list commands desu";
    protected static final String NULL_ERROR_MESSAGE = "item not exist...";
    protected static final String ERROR_MESSAGE = "ダメだよ。。。";
    protected static final String INVALID_INPUT_MESSAGE = "あの。。。puriisu taipu valid input ne \uD83D\uDC31\uD83C\uDF08";
    protected static final String MISSING_DESCRIPTION_MESSAGE = "puriisu add description!";
    protected static final String MISSING_DATE_MESSAGE = "puriisu add date!";
    protected static final String MISSING_START_END_MESSAGE = "puriisu add start & end";

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printBye() {
        System.out.println(LINE);
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printGreet() {
        System.out.println(LINE);
        System.out.println(GREET_MESSAGE);
        System.out.println(HELP_MESSAGE);
        System.out.println(LINE);
    }

    public static void printDeleteSuccess() {
        System.out.println(DELETE_MESSAGE);
        System.out.println(DELETE_FOLLOW_UP_MESSAGE);
    }

    public static void printDeleteUsage() {
        System.out.println(DELETE_USAGE_MESSAGE);
    }

    public static void printMarkSuccess() {
        System.out.println(MARK_MESSAGE);
        System.out.println(LINE);
    }

    public static void printMarkUsage() {
        System.out.println(ERROR_MESSAGE);
        System.out.println(MARK_USAGE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printUnmarkSuccess() {
        System.out.println(UNMARK_MESSAGE);
        System.out.println(LINE);
    }

    public static void printUnmarkUsage() {
        System.out.println(ERROR_MESSAGE);
        System.out.println(UNMARK_USAGE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printNullError() {
        System.out.println(NULL_ERROR_MESSAGE);
        System.out.println(LINE);
    }

    public static void printTodoUsage() {
        System.out.println(TODO_USAGE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printAddTaskSuccess(Task task) {
        System.out.println(LINE);
        if (task != null) {
            System.out.println("Added " + task.getDescription());
        }
    }

    public static void printDeadlineUsage() {
        System.out.println(DEADLINE_USAGE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printMissingDescription() {
        System.out.println(MISSING_DESCRIPTION_MESSAGE);
    }

    public static void printMissingDate() {
        System.out.println(MISSING_DATE_MESSAGE);
    }

    public static void printEventUsage() {
        System.out.println(EVENT_USAGE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printMissingStartEnd() {
        System.out.println(MISSING_START_END_MESSAGE);
    }
    public static void printTasks (ArrayList<Task> tasks) {
        int count = 0;
        for (Task task : tasks) {
            count++;
            System.out.print(count + ". " + task.getStatusIcon() + " ");
            System.out.println(task.getDescription());
        }
    }

    public static void printInvalidInput() {
        System.out.println(INVALID_INPUT_MESSAGE);
        System.out.println(HELP_MESSAGE);
        System.out.println(LINE);
    }

    public static void printSike() {
        System.out.println(SIKE_MESSAGE);
        System.out.println(LINE);
    }
}
