package soot.task;

import soot.ui.UserUi;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;
    public static int listCounter;

    //TODO: remove listCounter variable, use .size()
    public TaskList() {
        taskList = new ArrayList<>();
        listCounter = 0;
    }

    //TODO: implement stream?
    public static void printList() {
        System.out.println("tasks to be done:");
        if (taskList.size() == 0) {
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
    public static void addTask(String userInput, taskType taskType) {
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
        TaskList.listCounter++;
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
        TaskList.listCounter++;
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
        TaskList.listCounter++;
        taskList.get(taskList.size()-1).printRespond();
        UserUi.displayDividerLine();
    }

    // TODO: method is unused
    public static int getSize() {
        return taskList.size();
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
        listCounter--; //TODO: should be unnecessary
        taskList.get(listIndex).printDelete();
        taskList.remove(listIndex);
        UserUi.displayDividerLine();
    }
}
