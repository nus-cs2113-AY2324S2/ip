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

    /**
     * Returns a string representation of the task's completion status.
     */
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

    /**
     * Marks the task as completed or not completed.
     * @param isCompleted whether the task is to be marked as completed or not completed
     */
    public void mark(boolean isCompleted) {
        this.isCompleted = isCompleted;
        if (this.isCompleted) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + this);
    }

    /**
     * Check if the task description contains the keywords
     * @param keywordsToFind keywords mentioned by the user
     * @return true if the task description contains at least one keyword
     */
    public boolean hasKeywords(String[] keywordsToFind) {
        for (String keywordToFind : keywordsToFind) {
            if (this.wordsInTaskName.contains(keywordToFind)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the task.
     * Abstract method to be implemented by subclasses.
     * @return a string representation of the task
     */
    public abstract String getStringRepresentation();
}
