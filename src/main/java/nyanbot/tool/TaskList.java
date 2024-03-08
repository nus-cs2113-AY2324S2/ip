package nyanbot.tool;

import java.util.ArrayList;

import nyanbot.exception.NyanException;
import nyanbot.task.Task;
import nyanbot.task.Todo;
import nyanbot.task.Deadline;
import nyanbot.task.Event;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /***
     * Add task to current instance of TaskList
     * invokes UI class method to print success message as feedback to user
     * @param task task to add to tasklist
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        UI.printAddTaskSuccess(task);
    }

    /***
     * Stores arraylist of tasks from input in arraylist of current instance
     * @param tasks arraylist containing tasks
     */
    public void importTask(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /***
     * Returns task list stored in current instance
     * @return arraylist of tasks stored in current instance
     */
    public ArrayList<Task> exportTask() {
        return this.tasks;
    }

    /***
     *searches for task in task list with description matching specified keyword
     * @param string containing keyword
     * @return foundTasks arraylist of tasks matching search
     */
    public ArrayList<Task> findTasks(String input) throws NyanException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        if (input.isBlank()) {
            throw new NyanException("missing keyword\n" + UI.FIND_USAGE_MESSAGE);
        }
        for (Task task : tasks) {
            String string = task.toString();
            String[] tokens = string.split("//", 3);
            if (tokens[2].contains(input)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /***
     * delete task of specified index in tasklist of current instance
     * invokes UI method to print feedback for user on success
     * @param input string containing index of task to delete
     */
    public void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.remove(index);
            UI.printDeleteSuccess();
            UI.printTasks(this.tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            UI.printNullError();
        } catch (NumberFormatException e) {
            UI.printDeleteUsage();
        }
    }

    /***
     * mark specified task in tasklist as done
     * invokes UI method to print feedback for user on success
     * @param input string containing index of task to mark in arraylist of current instance
     */
    public void markTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.get(index).markAsDone();
            UI.printMarkSuccess();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            UI.printNullError();
        } catch (NumberFormatException e) {
            UI.printMarkUsage();
        }
    }

    /***
     * mark specified task in tasklist as not done
     * invokes UI method to print feedback for user on success
     * @param input string containing index of task to unmark in arraylist of current instance
     */
    public void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.get(index).markAsUndone();
            UI.printUnmarkSuccess();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            UI.printNullError();
        } catch (NumberFormatException e) {
            UI.printUnmarkUsage();
        }
    }

    /***
     * add new todo task to tasklist of current instance
     * @param description string containing description for task to add
     */
    public void addTodo(String description) {
        try {
            if (description.isBlank()) {
                UI.printMissingDescription();
                UI.printTodoUsage();
                return;
            }
            Todo todo = new Todo(description);
            this.addTask(todo);
        } catch (StringIndexOutOfBoundsException e) {
            UI.printMissingDescription();
            UI.printTodoUsage();
        }
    }

    /***
     * add new deadline task to tasklist of current instance
     * @param input string containing description, start and end time for task
     */
    public void addDeadline(String input) {
        try {
            String[] splitInputs = input.split("//", 2);
            if (splitInputs[0].isBlank()) {
                UI.printMissingDescription();
                UI.printDeadlineUsage();
                return;
            }
            if (splitInputs[1].isBlank()) {
                UI.printMissingDate();
                UI.printDeadlineUsage();
                return;
            }
            String description = splitInputs[0];
            String date = splitInputs[1];
            Deadline deadline = new Deadline(description, date);
            addTask(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.printMissingDate();
            UI.printDeadlineUsage();
        }
    }

    /***
     * add new event to tasklist of current instance
     * @param input string containing description and time for task
     */
    public void addEvent(String input) {
        try {
            String[] splitInputs = input.split("//", 3);
            if (splitInputs[0].isBlank()) {
                UI.printMissingDescription();
                UI.printEventUsage();
                return;
            }
            if (splitInputs[1].isBlank() || splitInputs[2].isBlank()) {
                UI.printMissingDate();
                UI.printEventUsage();
                return;
            }
            String description = splitInputs[0];
            String start = splitInputs[1];
            String end = splitInputs[2];
            Event event = new Event(description, start, end);
            addTask(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.printMissingStartEnd();
            UI.printEventUsage();
        }
    }
}
