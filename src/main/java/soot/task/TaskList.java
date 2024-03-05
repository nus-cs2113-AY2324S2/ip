package soot.task;

import soot.ui.UserUi;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    //TODO: remove listCounter variable, use .size()
    public TaskList() {
        taskList = new ArrayList<>();
    }

    //TODO: implement stream?
    public static void printList() {
        System.out.println("tasks to be done:");
        if (taskList.isEmpty()) {
            System.out.println("  >> nothing so far :)");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                UserUi.displayIndent();
                TaskList.taskList.get(i).printTask(i + 1);
            }
            UserUi.showTaskListCount();
        }
        UserUi.displayDividerLine();
    }

    public static void addTask(String userInput, TaskType taskType) {
        switch (taskType) {
        case TODO:
            addTodoTask(userInput);
            break;
        case DEADLINE:
            addDeadlineTask(userInput);
            break;
        case EVENT:
            addEventTask(userInput);
        }
    }

    //TODO: clean up add task methods
    public static void addTodoTask(String userInput) {
        String inputTask = userInput.substring(5);
        taskList.add(new Todo(inputTask, false));
        taskList.get(taskList.size() - 1).printRespond();
        UserUi.displayDividerLine();
    }

    public static void addDeadlineTask(String userInput) {
        String inputTask = userInput.substring(9);
        //split taskName and dueDate
        int deadlineIndex = inputTask.indexOf('/');
        String taskName = inputTask.substring(0, deadlineIndex - 1);
        String dueDate = inputTask.substring(deadlineIndex + 4);
        taskList.add(new Deadline(taskName, false, dueDate));
        taskList.get(taskList.size()-1).printRespond();
        UserUi.displayDividerLine();
    }

    public static void addEventTask(String userInput) {
        String inputTask = userInput.substring(6);
        //split taskName and time frames
        int startIndex = inputTask.indexOf('/');
        String taskName = inputTask.substring(0, startIndex - 1);

        String timeLine = inputTask.substring(startIndex + 6);
        int endIndex = timeLine.indexOf('/');

        String startDate = timeLine.substring(0, endIndex - 1);
        String endDate = timeLine.substring(endIndex + 4);
        taskList.add(new Event(taskName, false, startDate, endDate));
        taskList.get(taskList.size()-1).printRespond();
        UserUi.displayDividerLine();
    }

    public static int getSize() {
        return taskList.size();
    }

    public static Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public static TaskType getTaskType(int taskIndex) {
        return taskList.get(taskIndex).taskType;
    }

    public static void markTaskDone(int taskIndex) {
        taskList.get(taskIndex).markDone();
        UserUi.displayDividerLine();
    }

    public static void markTaskUndone(int taskIndex) {
        taskList.get(taskIndex).markUndone();
        UserUi.displayDividerLine();
    }

    public static void deleteTask(int listIndex) {
        Task tempTask = taskList.get(listIndex);
        taskList.remove(taskList.get(listIndex));
        tempTask.printDelete();
        UserUi.displayDividerLine();
    }

    public static void addSavedTodoTask(String taskName, boolean isTaskDone) {
        taskList.add(new Todo(taskName, isTaskDone));
    }

    public static void addSavedDeadlineTask(String taskName, boolean isTaskDone, String taskDeadline) {
        taskList.add(new Deadline(taskName, isTaskDone, taskDeadline));
    }

    public static void addSavedEventTask(String taskName, boolean isTaskDone, String taskStart, String taskEnd) {
        taskList.add(new Event(taskName, isTaskDone, taskStart, taskEnd));
    }

    public static String formatTaskToSave(int taskIndex) {
        String formattedLine;
        Task task = taskList.get(taskIndex);
        int taskDone = task.isDone ? 1 : 0;

        switch (task.taskType) {
        case TODO:
            formattedLine = "T ; " + taskDone + " ; " + task.taskName;
            break;
        case DEADLINE:
            Deadline deadlineTask = (Deadline) task;
            formattedLine = "D ; " + taskDone + " ; " + deadlineTask.taskName + " ; " + deadlineTask.dueDate;
            break;
        case EVENT:
            Event eventTask = (Event) task;
            formattedLine = "E ; " + taskDone + " ; " + eventTask.taskName + " ; " + eventTask.startDate + " ; " + eventTask.endDate;
            break;
        default:
            formattedLine = "i could not read this task";
            System.out.println("task of unknown type detected");
        }
        return formattedLine;
    }
}
