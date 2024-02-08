import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    public abstract class Task {
        protected String name;
        protected String description;

        public Task(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public abstract String getStatusIcon();

        @Override
        public abstract String toString();
    }

    public class Todo extends Task {
        protected boolean isDone;

        public Todo(String name, String description) {
            super(name, description);
            isDone = false;
        }

        public void setDone(boolean done) {
            isDone = done;
        }

        public boolean isDone() {
            return isDone;
        }

        @Override
        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        @Override
        public String toString(){
            return "[T]" + getStatusIcon() + " " + description;
        }
    }

    public class Deadline extends Todo {
        protected String by;

        public Deadline(String name, String description, String by) {
            super(name, description); // Fixed: Correctly initialize the parent class
            this.by = by;
        }

        @Override
        public String toString(){
            return "[D]" + super.getStatusIcon() + " " + description + " (by: " + by + ")";
        }
    }

    public class Event extends Task {
        protected String start;
        protected String end;
        protected boolean isDone; // Added completion status
    
        public Event(String name, String description, String start, String end) {
            super(name, description);
            this.start = start;
            this.end = end;
            this.isDone = false; // Initialize as not done
        }
    
        public void setDone(boolean done) {
            isDone = done;
        }
    
        @Override
        public String getStatusIcon() {
            return isDone ? "[X]" : "[ ]"; // Display X for done, space for not done
        }
    
        @Override
        public String toString() {
            return "[E]" + getStatusIcon() + " " + description + " (from: " + start + " to: " + end + ")";
        }
    }

    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            if (task instanceof Todo) {
                ((Todo) task).setDone(true);
            } else if (task instanceof Event) {
                ((Event) task).setDone(true); // Mark Event as done
            } else {
                System.out.println("This task type cannot be marked as done.");
                return;
            }
            // Print completion message for both Todos and Events
            System.out.println("----------------------------------");
            System.out.println("Task completed");
            System.out.println(task);
            System.out.println("--------------------------------------");
        } else {
            System.out.println("Invalid task number.");
        }
    }
    
}
