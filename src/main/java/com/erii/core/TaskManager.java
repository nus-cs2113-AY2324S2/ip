package com.erii.core;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    public enum Priority {
        SS, S, A, B, C, D
    }

    public abstract class Task {
        protected String name;
        protected String description;
        protected Priority priority;

        public Task(String name, String description, Priority priority) {
            this.name = name;
            this.description = description;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Priority getPriority() {
            return priority;
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public abstract String getStatusIcon();

        @Override
        public abstract String toString();
    }

    public class Todo extends Task {
        protected boolean isDone;

        public Todo(String name, String description, Priority priority) {
            super(name, description, priority);
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
            return "[T]" + getStatusIcon() + " " + description + " <" + priority + "> ";
        }
    }

    public class Deadline extends Todo {
        protected String by;

        public Deadline(String name, String description, String by, Priority priority) {
            super(name, description, priority); // Fixed: Correctly initialize the parent class
            this.by = by;
        }

        @Override
        public String toString(){
            return "[D]" + super.getStatusIcon() + " " + description + " <" + priority + "> " + " (by: " + by + ")";
        }
    }

    public class Event extends Task {
        protected String start;
        protected String end;
        protected boolean isDone; // Added completion status
    
        public Event(String name, String description, String start, String end, Priority priority) {
            super(name, description, priority);
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
            return "[E]" + getStatusIcon() + " " + description + " <" + priority + "> " + " (from: " + start + " to: " + end + ")";
        }
    }


    private List<Task> tasks = new ArrayList<>();

    public int listSize(){
        return tasks.size();
    } 

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

    public void sortListByPriority() {
        tasks.sort((Task t1, Task t2) -> t1.getPriority().compareTo(t2.getPriority()));
        System.out.println("Tasks sorted by priority.");
    }

    public void sortListByType() {
        tasks.sort((Task t1, Task t2) -> t1.getName().compareTo(t2.getName()));
        System.out.println("Tasks sorted by type.");
    }

    // public void sortListByDeadline() {
    //     tasks.sort((Task t1, Task t2) -> {
    //         if (t1 instanceof Deadline && t2 instanceof Deadline) {
    //             return ((Deadline) t1).by.compareTo(((Deadline) t2).by);
    //         } else {
    //             return 0;
    //         }
    //     });
    //     System.out.println("Tasks sorted by deadline.");
    // }

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

    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Validates and converts a string representation of priority to the Priority enum.
     * @param priorityString The string representation of priority.
     * @return The Priority enum value if valid, otherwise throws an IllegalArgumentException.
     */
    public static Priority validateAndConvertPriority(String priorityString) throws IllegalArgumentException{
        try {
            return Priority.valueOf(priorityString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority. Please enter a valid priority value (SS, S, A, B, C, D).");
        }
    }
    
}
