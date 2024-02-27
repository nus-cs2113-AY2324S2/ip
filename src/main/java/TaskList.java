import java.util.Arrays;

public class TaskList {
    private Task[] list;
    private int count;

    public TaskList(Task[] list, int count) {
        this.list = list;
        this.count = count;
    }

    public void addTask(String description) {
        if (count == list.length) {
            list = Arrays.copyOf(list, count * 2);
        }
        list[count] = new Task(description);
        count++;
        System.out.println("____________________________________________________________");
        System.out.println("Added: " + description);
        System.out.println("____________________________________________________________");
    }
    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > count) {
            System.out.println("Task with specified number does not exist.");
            return;
        }

        // Adjust the index since taskNumber is 1-based and our array is 0-based
        int index = taskNumber - 1;

        System.out.println(Echo.break_line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list[index]);
        System.out.println(Echo.break_line);

        // Shift tasks one position to the left from the index of the removed task
        for (int i = index; i < count - 1; i++) {
            list[i] = list[i + 1];
        }

        list[count - 1] = null; // Help the garbage collector
        count--; // Decrease the count of tasks
    }


    public void addTodo(String description) {
        if (description.isEmpty()) {
            System.out.println("ToDo requires a description.");
        } else {
            ToDo newToDo = new ToDo(description);
            if (count == list.length) {
                list = Arrays.copyOf(list, count * 2);
            }
            list[count] = new Task(description);
            this.list[count].markAsToDo();
            count++;

            System.out.println(Echo.break_line);
            System.out.println("Got it. I've added this Event:");
            System.out.println(newToDo);
            System.out.println("Now you have " + this.count + " tasks in the list.");
            System.out.println(Echo.break_line);
        }
    }

    public void addDeadline(String description) {
        String[] deadlineParts = description.split(" /by ", 2);
        if (count == list.length) {
            list = Arrays.copyOf(list, count * 2);
        }
        Deadline newDDL = new Deadline(deadlineParts[0], deadlineParts[1]);

        list[count] = new Deadline(deadlineParts[0], deadlineParts[1]);
        this.list[count].markAsDDL();
        count++;

        System.out.println(Echo.break_line);
        System.out.println("Got it. I've added this Event:");
        System.out.println(newDDL);
        System.out.println("Now you have " + this.count + " tasks in the list.");
        System.out.println(Echo.break_line);
    }


    public void addEvent(String description) {
        String[] eventParts = description.split(" /from ", 2);
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (count == list.length) {
            list = Arrays.copyOf(list, count * 2);
        }

        list[count] = new Event(eventParts[0], timeParts[0], timeParts[1]);
        this.list[count].markAsEvent();
        count++;
        System.out.println(Echo.break_line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E][ ] " + eventParts[0] + " (from: " + timeParts[0] + " to: " + timeParts[1] + ")");
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(Echo.break_line);


    }


    public void clearList() {
        // Re-initialize the list with its initial capacity
        list = new Task[list.length]; // or a specific initial capacity, like new Task[10];
        count = 0; // Reset the count to 0
        System.out.println(Echo.break_line);
        System.out.println("All tasks have been cleared.");
        System.out.println(Echo.break_line);
    }

    public void listTasks() {
        System.out.println(Echo.break_line);
        System.out.println("Here are your tasks in your list:");

        for (int i = 0; i < this.count; i++) {
            Task task = this.list[i];
            // This line will automatically call the overridden toString() method
            // of each Task object, which includes ToDo tasks with their [T] prefix.
            System.out.println(" " + (i + 1) +".["+task.getTypeIcon()+"]"+ "[" + task.getStatusIcon() + "]" + task);
        }
        System.out.println(Echo.break_line);
    }

    public void markTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= count) {
            this.list[taskNumber - 1].markAsDone();
            System.out.println(Echo.break_line);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [X] " + this.list[taskNumber - 1]);
        } else {
            System.out.println(Echo.break_line);
            System.out.println("Error: Task number " + taskNumber + " does not exist in the list.");
        }
        System.out.println(Echo.break_line);
    }

    public void unmarkTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= count) {
            this.list[taskNumber - 1].unmarkAsDone();
            System.out.println(Echo.break_line);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + this.list[taskNumber - 1]);
        } else {
            System.out.println(Echo.break_line);
            System.out.println("Error: Task number " + taskNumber + " does not exist in the list.");
        }
        System.out.println(Echo.break_line);
    }

}
