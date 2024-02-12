package Binks;

import Binks.task.Deadlines;
import Binks.task.Events;
import Binks.task.Task;
import Binks.task.Todo;

public class List {
    private Task[] list;
    private int taskCount = 0;


    public List() {
        this.list = new Task[100];
    }

    /**
     * Obtains and prints out the task list as well as whether it is marked as done or not done.
     */
    public void getList() {
        Binks.createLineSpacing();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.println( (i + 1) + ". " + list[i]);
        }
        Binks.createLineSpacing();
    }

    /**
     * Adds the todo task into the task list
     *
     * @param task Todo task that is being added to the task list
     */
    public void addTodo(String task){
        Binks.createLineSpacing();
        Todo newTodo = new Todo(task);
        list[taskCount] = newTodo;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println(" [T][ ] " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        Binks.createLineSpacing();
    }

    /**
     * Adds the deadline task into the task list
     *
     * @param task Deadline task that is being added to the task list
     */
    public void addDeadline(String task){
        Binks.createLineSpacing();
        Deadlines newDeadline = new Deadlines(task);
        list[taskCount] = newDeadline;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println(" [D][ ] " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        Binks.createLineSpacing();
    }

    /**
     * Adds the event task into the task list
     *
     * @param task Event task that is being added to the task list
     */
    public void addEvent(String task){
        Binks.createLineSpacing();;
        Events newEvent = new Events(task);
        list[taskCount] = newEvent;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println(" [E][ ] " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        Binks.createLineSpacing();
    }

    /**
     * Marks the task as done by adding an "X" in the square brackets and prints out the task that is done.
     *
     * @param index Index of the task that is marked as done
     */
    public void markAsDone(int index){
        Binks.createLineSpacing();
        if (index > 0 && index <= taskCount){
            list[index - 1].markTaskAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(list[index - 1]);
        }
        else {
            System.out.println("This task does not exist");
        }
        Binks.createLineSpacing();
    }

    /**
     * Marks the task as not done by removing the "X" in the square brackets and prints out the task that is being
     * marked as undone.
     *
     * @param index Index of the task that is being marked as undone
     */
    public void unmarkAsDone(int index){
        Binks.createLineSpacing();
        if (index > 0 && index <= taskCount){
            list[index - 1].unmarkTaskAsDone();
            System.out.println("OK, I have marked this task as not done yet:");
            System.out.println(list[index - 1]);
        }
        else {
            System.out.println("This task does not exist");
        }
        Binks.createLineSpacing();
    }
}
