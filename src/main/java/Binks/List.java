package Binks;

import Binks.task.Deadlines;
import Binks.task.Events;
import Binks.task.Task;
import Binks.task.Todo;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class List {
    private final ArrayList<Task> list;



    public List() {

        this.list = new ArrayList<>();
    }

    /**
     * Obtains and prints out the task list as well as whether it is marked as done or not done.
     */
    public void getList() {
        Binks.createLineSpacing();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++){
            System.out.println( (i + 1) + ". " + list.get(i));
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
        list.add(newTodo);
        updateFile(list);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [T][ ] " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
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
        list.add(newDeadline);
        updateFile(list);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [D][ ] " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        Binks.createLineSpacing();
    }

    /**
     * Adds the event task into the task list
     *
     * @param task Event task that is being added to the task list
     */
    public void addEvent(String task){
        Binks.createLineSpacing();
        Events newEvent = new Events(task);
        list.add(newEvent);
        updateFile(list);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [E][ ] " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        Binks.createLineSpacing();
    }

    /**
     * Marks the task as done by adding an "X" in the square brackets and prints out the task that is done.
     *
     * @param index Index of the task that is marked as done
     */
    public void markAsDone(int index){
        Binks.createLineSpacing();
        if (index > 0 && index <= list.size()){
            list.get(index - 1).markTaskAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(list.get(index - 1));
        }
        else {
            System.out.println("This task does not exist");
        }
        Binks.createLineSpacing();
        updateFile(list);
    }

    /**
     * Marks the task as not done by removing the "X" in the square brackets and prints out the task that is being
     * marked as undone.
     *
     * @param index Index of the task that is being marked as undone
     */
    public void unmarkAsDone(int index){
        Binks.createLineSpacing();
        if (index > 0 && index <= list.size()){
            list.get(index - 1).unmarkTaskAsDone();
            System.out.println("OK, I have marked this task as not done yet:");
            System.out.println(list.get(index - 1));
        }
        else {
            System.out.println("This task does not exist");
        }
        Binks.createLineSpacing();
    }


    public void deleteTask(int index){
        Binks.createLineSpacing();
        if (index > 0 && index <= list.size()) {
            System.out.println("I have deleted the task: ");
            System.out.println(list.get(index - 1));
            list.remove(index - 1);
            System.out.println("That task was useless anyways ¯\\_(ツ)_/¯");
            System.out.println("You now have " + list.size() + " tasks left");
        }
        else {
            System.out.println("This task does not exist");
        }
        Binks.createLineSpacing();
        updateFile(list);
    }

    private void updateFile(ArrayList<Task> list) {
        String file = "binkslist.txt";
        //String taskDescription = list.toString();
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : list) {
                if (task != null) {
                    fw.write(task + "\n"); // Assuming each task is represented as a single line
                }
            }
            fw.close();
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
