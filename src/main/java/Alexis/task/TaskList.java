package Alexis.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int numberOfTasks;

    public void addToTaskList(Task task) {
        tasks.add(task);
        numberOfTasks++;
        printAddTaskMessage(task);
    }

    private void printAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.print("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", numberOfTasks);
    }

    public void addTask(TaskType taskType, String input) {
        switch (taskType) {
        case TODO:
        default:
            ToDo toDo = ToDo.getToDo(input);
            addToTaskList(toDo);
            break;
        case DEADLINE:
            Deadline deadline = Deadline.getDeadline(input);
            addToTaskList(deadline);
            break;
        case EVENT:
            Event event = Event.getEvent(input);
            addToTaskList(event);
            break;
        }
    }
    
    public void markTask(String input) {
        int number = extractInt(input);
        if (number > 0 && number <= tasks.size()) {
            Task task = tasks.get(number - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.print("\t");
            printTask(task, -1);
        }
    }

    public void unmarkTask(String input) {
        int number = extractInt(input);
        if (number > 0 && number <= tasks.size()) {
            Task task = tasks.get(number - 1);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.print("\t");
            printTask(task, -1);
        }
    }

    public void printTasks() {
        int taskIndex = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks){
            printTask(task, taskIndex);
            taskIndex++;
        }
    }

    public void printTask(Task task, int index) {
        if (index < 0) {
            System.out.printf(task.toString());
        } else {
            System.out.printf("%d.%s", index, task.toString());
        }
    }

    public int extractInt(String input) {
        try {
            String number = input.replaceAll("[^0-9]", "");
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("Please tell me which task you are referring to.");
        }
        return 0;
    }
}
