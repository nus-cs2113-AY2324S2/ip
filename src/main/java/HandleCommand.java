import java.util.Scanner;

public class HandleCommand {

    public static void setToDo(String task, Task[] taskList) {
        String todoName = task.substring(5);
        Todo newToDo = new Todo(todoName);
        taskList[Task.getTaskCount() - 1] = newToDo; //creating new class(to do) in the array
        Ui.printTodoTask(newToDo.toString());
        Ui.printTaskCount(Task.getTaskCount());
    }

    public static void setDeadline (String task, Task[] taskList) {
        int deadlineIndex = task.indexOf("/by"); //gives index of /by
        String deadline = task.substring(deadlineIndex + 4);
        String deadlineTask = task.substring(9, deadlineIndex - 1);
        Deadline newDeadline = new Deadline(deadlineTask, deadline);
        taskList[Task.getTaskCount() - 1] = newDeadline;
        Ui.printDeadlineTask(newDeadline.toString());
        Ui.printTaskCount(Task.getTaskCount());
    }

    public static void setEvent (String task, Task[] taskList) {
        int eventStartIndex = task.indexOf("/from"); //returns index of '/' of /from
        int eventEndIndex = task.indexOf("/to");
        String eventName = task.substring(6, eventStartIndex - 1);
        String eventStartDate = task.substring(eventStartIndex + 6, eventEndIndex - 1);
        String eventEndDate = task.substring(eventEndIndex + 3);
        Event newEvent = new Event(eventName, eventStartDate, eventEndDate);
        taskList[Task.getTaskCount() - 1] = newEvent;
        Ui.printEventTask(newEvent.toString());
        Ui.printTaskCount(Task.getTaskCount());
    }

    public static void markTask (String tasks, Task[] taskList) {
        String[] task = tasks.split(" ");
        String taskIndex = task[1]; //getting the index task to mark or un mark
        int index = Integer.parseInt(taskIndex) - 1;

        Task taskToMark = taskList[index];
        String taskName = taskToMark.getDescription();
        if (task[0].equals("mark")) {
            taskToMark.markAsDone();
            System.out.println("Ok! One task down!");
        } else {
            taskToMark.markAsNotDone();
            System.out.println("Oh no! More tasks!");
        }
        String status = taskToMark.getStatusIcon();
        System.out.println(taskToMark.toString());
    }
}
