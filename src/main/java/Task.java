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

    public String toFileString() {
        return null;
    }

    public static void addTask(String text, ArrayList<Task> tasksArrayList, int num) throws InvalidTodoSyntaxException {
        // Todo
        if (text.startsWith("todo")) {
            if (text.length() < "todo".length() + 2) {
                throw new InvalidTodoSyntaxException();
            } else {
                String todoDescription = text.substring(5);
                tasksArrayList.add(new ToDo(todoDescription));
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[T] " + "[ ] " + tasksArrayList.get(num).description);

            }
        }

        // Deadline
        else if (text.startsWith("deadline")) {
            if (text.length() < "deadline".length() + 2) {
                System.out.println("OH NOOO! Please enter the deadline task name.");
            } else {
                String deadlineDescription = text.substring(9,text.indexOf("/by")) + "(by: " + text.substring(text.indexOf("/by") + 4) + ")";
                tasksArrayList.add(new Deadline(deadlineDescription));
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[D] " + "[ ] " + tasksArrayList.get(num).description);
            }
        }
        // Event
        else if (text.startsWith("event")) {
            if (text.length() < "event".length() + 2) {
                System.out.println("OH NOOO! Please enter the event task name.");
            } else {
                String eventDescription = text.substring(6, text.indexOf(" /from")) + " (from: " + text.substring(text.indexOf("/from") + 6, text.indexOf(" /to"))
                        + " to: " + text.substring(text.indexOf("/to") + 4) + ")";
                tasksArrayList.add(new Event(eventDescription));
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[E] " + "[ ] " + tasksArrayList.get(num).description);
            }
        }
        // ERROR
        else {
            System.out.println("Oh no, I do not understand.");
        }

    }

    public static void deleteTask(int taskIndex, ArrayList<Task> taskArrayList) {
        String removedTask = taskArrayList.get(taskIndex).description;
        taskArrayList.remove(taskIndex);
        System.out.println("Bala-lala. I've removed this task:");
        System.out.println(removedTask);
    }
}
