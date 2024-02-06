public enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    public static TaskType getTaskType(String type) {
            return TaskType.valueOf(type.toUpperCase());
        }
}

