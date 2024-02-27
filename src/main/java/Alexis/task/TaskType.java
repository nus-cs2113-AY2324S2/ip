package Alexis.task;

/**
 * The TaskType enum holds all possible task types.
 */
public enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    /**
     * Returns a TaskType enum value.
     *
     * @param type The string representation of the task type.
     * @return The corresponding TaskType enum value or null if the input string does not match any enum value.
     */
    public static TaskType getTaskType(String type) {
        try {
            return TaskType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

