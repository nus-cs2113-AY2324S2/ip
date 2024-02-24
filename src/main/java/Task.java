import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    protected String type; // T or D or E

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static void addTask(String text, ArrayList<Task> tasksArrayList, int num) throws InvalidTodoSyntaxException {
        // Todo
        if (text.startsWith("todo")) {
            if (text.length() < "todo".length() + 2) {
                throw new InvalidTodoSyntaxException();
            } else {
                tasksArrayList.add(new ToDo(text));
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[T] " + "[ ] " + tasksArrayList.get(num).description);
            }
        }

        // Deadline
        else if (text.startsWith("deadline")) {
            if (text.length() < "deadline".length() + 2) {
                System.out.println("OH NOOO! Please enter the deadline task name.");
            } else {
                tasksArrayList.add(new Deadline(text));
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[D] " + "[ ] " + tasksArrayList.get(num).description);
            }
        }
        // Event
        else if (text.startsWith("event")) {
            if (text.length() < "event".length() + 2) {
                System.out.println("OH NOOO! Please enter the event task name.");
            } else {
                tasksArrayList.add(new Event(text));
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[E] " + "[ ] " + tasksArrayList.get(num).description);
            }
        }
        // ERROR
        else {
            System.out.println("Oh no, I do not understand.");
        }

    }

    public static void deleteTask(int taskIndex,  ArrayList<Task> taskArrayList) {
        String removedTask = taskArrayList.get(taskIndex).description;
        taskArrayList.remove(taskIndex);
        System.out.println("Bala-lala. I've removed this task:");
        System.out.println(removedTask);
    }
}
