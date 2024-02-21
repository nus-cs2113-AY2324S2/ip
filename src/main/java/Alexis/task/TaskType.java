package Alexis.task;

public enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    public static TaskType getTaskType(String type) {
        try {
            return TaskType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

