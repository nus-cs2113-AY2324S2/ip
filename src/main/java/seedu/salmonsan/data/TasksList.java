package seedu.salmonsan.data;

import seedu.salmonsan.data.exception.SalmonNotInListException;
import seedu.salmonsan.data.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksList {
    private Integer noOfTasks;
    private List<Task> list = new ArrayList<Task>();

    public TasksList(){
        this.noOfTasks = 0;
    }

    /**
     * Add task T into TaskList
     * @param t
     */
    public void addTask(Task t) {
        this.noOfTasks++;
        this.list.add(t);
        System.out.println("Hai ~ your task have been added successfully");
        System.out.print("    ");
        t.printTask();
        System.out.println("You have " + this.noOfTasks + " tasks remaining");
    }

    /**
     * deleteTask
     * @param s is index of list in String
     * @throws SalmonNotInListException
     */
    public void deleteTask(String s) throws SalmonNotInListException{
        int index = Integer.parseInt(s);
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            throw new SalmonNotInListException();
        } else {
            System.out.println("Deleted! The following task has been removed!");
            System.out.print("    ");
            list.get(index).printTask();

            // update no of task
            this.noOfTasks--;

            System.out.println("You have " + this.noOfTasks + " tasks remaining");

            list.remove(index);
        }
    }

    /**
     * list out the Tasks in the TaskList
     */
    public void show() {
        System.out.println("Osu! Your tasks are as follows:");
        for(int i = 0; i < this.noOfTasks; i++) {
            System.out.print("  ");
            System.out.print((i + 1) + ".");
            this.list.get(i).printTask();
        }
    }

    /**
     * print the tasks which indices are in tasksIndex array
     * @param tasksIndex
     */
    public void show(ArrayList<Integer> tasksIndex) {
        System.out.println("Osu! Your tasks are as follows:");
        for(int i = 0; i < tasksIndex.size(); i++) {
            System.out.print("  ");
            System.out.print((i + 1) + ". ");
            System.out.print("(index: " + (tasksIndex.get(i) + 1) + ") |  ");
            this.list.get(tasksIndex.get(i)).printTask();
        }
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < this.noOfTasks; i++) {
            result = result + list.get(i).toString() + System.lineSeparator();
        }
        return result;
    }

    /**
     * Find task with description String s (case-sensitive), and mark it as done.
     * @param s
     */
    public void markAsDone(String s) throws SalmonNotInListException{
        Integer index = this.findIndexWithDesc(s);
        if (index == -1) {
            throw new SalmonNotInListException();
        } else {
            this.list.get(index).markAsDone();
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("    ");
        this.list.get(index).printTask();
    }

    /**
     * Mark task at number index as done
     * @param index
     */
    public void markAsDone(Integer index) throws SalmonNotInListException{
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            throw new SalmonNotInListException();
        } else {
            this.list.get(index).markAsDone();
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("    ");
        this.list.get(index).printTask();
    }

    /**
     * Find task with description String s (case-sensitive), and mark it as not done.
     * @param s
     */
    public void markAsNotDone(String s) throws SalmonNotInListException{
        Integer index = this.findIndexWithDesc(s);
        if (index == -1) {
            throw new SalmonNotInListException();
        } else {
            this.list.get(index).markAsNotDone();
        }
        System.out.println("As you wished, your task is now undone:");
        System.out.print("    ");
        this.list.get(index).printTask();
    }

    /**
     * Mark task at number index as not done
     * @param index
     */
    public void markAsNotDone(Integer index) throws SalmonNotInListException{
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            throw new SalmonNotInListException();
        } else {
            this.list.get(index).markAsNotDone();
        }
        System.out.println("As you wished, your task is now undone:");
        System.out.print("    ");
        this.list.get(index).printTask();
    }

    /**
     * Find the index of inputted task String s (case-sensitive)
     * @param s
     * @return Integer index
     */
    public Integer findIndexWithDesc(String s) {
        for (int i = 0; i < this.noOfTasks; i++) {
            if (this.list.get(i).getDescription().equals(s)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Find tasks that has inputted keyword
     * @param keyword
     * @return ArrayList<Integer> containing index of tasks with keyword
     */
    public ArrayList<Integer> findWordInDesc(String keyword) {
        ArrayList<Integer> tasksWithWord = new ArrayList<Integer>();
        for (int i = 0; i < this.noOfTasks; i++) {
            String sentence = this.list.get(i).getDescription();
            String[] wordsOfSentence = sentence.split(" ", -1);
            for (String word : wordsOfSentence) {
                if (keyword.equals(word)) {
                    tasksWithWord.add(i);
                }
            }
        }
        return tasksWithWord;
    }

    /**
     * return seedu.salmonsan.data.task.Task at specific index in list (0 indexed)
     * @param i
     * @return seedu.salmonsan.data.task.Task
     */
    public Task getTaskWithIndex(int i) {
        return this.list.get(i);
    }

    /**
     * return the latest seedu.salmonsan.data.task.Task inserted
     * @return seedu.salmonsan.data.task.Task
     */
    public Task getLatestTask() {
        return this.list.get(this.noOfTasks - 1); // -1 to get index
    }

}
