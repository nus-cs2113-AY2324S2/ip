import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    public int getLength() {
        return this.tasks.size();
    }

    public void displayTasksList() {
        int taskNumber = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println("\t" + taskNumber + "." + task.getTaskDetails());
            taskNumber += 1;
        }
        System.out.println();
    }

    public void markTask(int taskIndex) throws IndexOutOfBoundsException {
        try {
            this.tasks.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please enter a valid task number");
        }
    }

    public void unmarkTask(int taskIndex) throws IndexOutOfBoundsException {
        try {
            this.tasks.get(taskIndex).unmarkAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please enter a valid task number");
        }
    }

    public void addTask(String taskType, String[] userInput) {
        if (taskType.equals(Parser.TODO_COMMAND)) {
            handleToDo(userInput);
        } else if (taskType.equals(Parser.DEADLINE_COMMAND)) {
            handleDeadline(userInput[1]);
        } else {
            handleEvent(userInput[1]);
        }
    }

    public void validTask() {
        taskCount += 1;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + tasks.get(taskCount - 1).getTaskDetails());
        System.out.println("\tNow you have " + taskCount
                + (taskCount == 1 ? " task" : " tasks") + " in the list.\n");
    }

    public void handleToDo(String[] userInput) throws ArrayIndexOutOfBoundsException {
        try {
            tasks.add(new ToDo(userInput[1]));
            validTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ToDo task description cannot be empty. Please try again.");
        }
    }

    public void handleDeadline(String taskDetails) {
        int dividerPosition = taskDetails.indexOf('/');
        String description = taskDetails.substring(0, dividerPosition);
        String deadline = taskDetails.substring(dividerPosition + 4);
        tasks.add(new Deadline(description, deadline));
    }

    public void handleEvent(String taskDetails) {
        int firstDividerPosition = taskDetails.indexOf('/');
        int secondDividerPosition = taskDetails.indexOf("to");
        String description = taskDetails.substring(0, firstDividerPosition);
        String eventStart = taskDetails.substring(firstDividerPosition + 6, secondDividerPosition - 1);
        String eventEnd = taskDetails.substring(secondDividerPosition + 3);
        tasks.add(new Event(description, eventStart, eventEnd));
    }
}
