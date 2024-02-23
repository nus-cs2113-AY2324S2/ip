package tasks;

import java.util.HashSet;
import java.util.List;

public abstract class Task {
    protected final String taskName;
    private boolean isCompleted;
    protected final HashSet<String> wordsInTaskName;

    public static final String IS_COMPLETED_STRING = "---IS_COMPLETED---";

    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        String[] wordsInTaskName = taskName.split(" ");
        this.wordsInTaskName = new HashSet<>(List.of(wordsInTaskName));
    }

    protected String getIsCompletedString() {
        if (this.isCompleted) {
            return IS_COMPLETED_STRING;
        }
        return "";
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.taskName;
        }
        return "[] " + this.taskName;
    }

    public void mark(boolean isCompleted) {
        this.isCompleted = isCompleted;
        if (this.isCompleted) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + this);
    }

    public boolean hasKeywords(String[] keywordsToFind) {
        for (String keywordToFind : keywordsToFind) {
            if (this.wordsInTaskName.contains(keywordToFind)) {
                return true;
            }
        }
        return false;
    }


    public abstract String getStringRepresentation();
}
