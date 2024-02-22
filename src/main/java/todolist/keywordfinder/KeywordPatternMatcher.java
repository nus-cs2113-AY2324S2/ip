package todolist.keywordfinder;

import todolist.task.DeadLinesTask;
import todolist.task.EventsTask;
import todolist.task.Task;
import todolist.task.ToDoTask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordPatternMatcher {

    private final String inputText;
    private final Keyword keywordType;

    //CORRECT PATTERN
    private static final String MARK_FEATURE_PATTERN = "^mark \\d+$"; //mark
    private static final String UNMARK_FEATURE_PATTERN = "^unmark \\d+$"; //unmark
    private static final String DELETE_TASK_FEATURE_PATTERN = "^delete \\d+$"; //delete
    private static final String TODOTASK_FEATURE_PATTERN = "^todo .+"; //todoItem
    private static final String DEADLINESTASK_FEATURE_PATTERN = "^deadline .*"; //todoItem
    private static final String EVENTSTASK_PATTERN = "^event .*"; //todoItem
    //INCORRECT PATTERN
    private static final String TODOTASK_FEATURE_PATTERN_INCORRECT = "^todo .*";


    private static final int SPACE_OFFSET = 1; //keyword " " offset
    private static final int BY_OFFSET = 4; //keyword "/by" offset
    private static final int FROM_OFFSET = 6; //keyword "/from" offset
    private static final int TO_OFFSET = 4; //keyword "/to" offset

    public KeywordPatternMatcher(String input) {
        this.inputText = input;
        this.keywordType = checkKeywordType();
    }

    public Keyword getKeywordType() {
        return keywordType;
    }

    private int getSpaceCharacterPosition(String inputText) {
        return inputText.indexOf(" ");
    }

    public Keyword checkKeywordType() {
        if (matchesPattern(this.inputText, MARK_FEATURE_PATTERN)) {
            return Keyword.mark;
        } else if (matchesPattern(this.inputText, UNMARK_FEATURE_PATTERN)) {
            return Keyword.unmark;
        } else if (matchesPattern(this.inputText, TODOTASK_FEATURE_PATTERN)) {
            return Keyword.todo;
        } else if (matchesPattern(this.inputText, DEADLINESTASK_FEATURE_PATTERN)) {
            return Keyword.deadline;
        } else if (matchesPattern(this.inputText, EVENTSTASK_PATTERN)) {
            return Keyword.event;
        } else if (matchesPattern(this.inputText, TODOTASK_FEATURE_PATTERN_INCORRECT)) {
            return Keyword.todoError;
        } else if (matchesPattern(this.inputText, DELETE_TASK_FEATURE_PATTERN)){
            return Keyword.delete;
        } else {
            return Keyword.none;
        }
    }

    public int findNumberIndex() {
        int spacePosition = getSpaceCharacterPosition(this.inputText);
        return Integer.parseInt(this.inputText.substring(spacePosition + SPACE_OFFSET));
    }

    public Task processKeywordInput() {
        int spacePosition = getSpaceCharacterPosition(this.inputText);
        try {
            if (this.keywordType == Keyword.todo) {
                return new ToDoTask(this.inputText.substring(spacePosition + SPACE_OFFSET));
            } else if (this.keywordType == Keyword.deadline) {
                int keywordPosition = this.inputText.indexOf("/by");
                return new DeadLinesTask(this.inputText.substring(spacePosition + SPACE_OFFSET, keywordPosition - 1),
                        this.inputText.substring(keywordPosition + BY_OFFSET));
            } else {
                int keywordPosition1 = this.inputText.indexOf("/from"); // check /from keyword position
                int keywordPosition2 = this.inputText.indexOf("/to"); // check /to keyword position
                return new EventsTask(this.inputText.substring(spacePosition + SPACE_OFFSET, keywordPosition1 - 1),
                        this.inputText.substring(keywordPosition1 + FROM_OFFSET, keywordPosition2 - 1),
                        this.inputText.substring(keywordPosition2 + TO_OFFSET));
            }
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    private static boolean matchesPattern(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        return matcher.matches();
    }

    public static void messageDivider() {
        System.out.println("------------------------------------------");
    }

}
