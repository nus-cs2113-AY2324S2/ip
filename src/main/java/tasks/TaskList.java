package tasks;
import customexceptions.UnknownPromptException;
import java.util.ArrayList;
import customexceptions.IncompletePromptException;

public class TaskList {
    protected ArrayList<Task> list;
    protected static final String INDENT = "      ";
    public TaskList() {
        list = new ArrayList<>();
    }
    protected Task lastActionTask = null;
    public ArrayList<Task> getList() {
        return list;
    }
    public void addToList(Task task) {
        list.add(task);
    }

    /** Prints out how many tasks there are in the task list. */
    public void countTasks() {
        int currSize = list.size();
        System.out.println(INDENT + "Now you have " + currSize + " task" + (currSize > 1 ? "s " : " ") + "in the list");
    }

    private String extractTaskOrDate(String line, String keyword) {
        int index = line.indexOf(keyword) + keyword.length();
        String nextWord; // any commands that require a 'next' word
        switch (keyword) {
        case "event":
            nextWord = " from ";
            break;
        case "deadline":
            nextWord = " by ";
            break;
        case "from":
            nextWord = " to ";
            break;
        default:
            nextWord = null;
        }
        if (nextWord != null) {
            int nextIndex = line.indexOf(nextWord, index);
            if (nextIndex != -1) {
                return line.substring(index, nextIndex).trim();
            }
            return "";
        }
        return line.substring(index).trim();
    }

    public void addNewTask(String line, String firstWord)
            throws IncompletePromptException, UnknownPromptException {
        String taskDescription = extractTaskOrDate(line, firstWord);
        if (taskDescription.isEmpty()) {
            throw new IncompletePromptException();
        }
        Task newTask;
        switch (firstWord) {
        case "todo":
            newTask = new ToDo(taskDescription);
            ((ToDo)newTask).setHaveToDo(true);
            newTask.setTaskType("T");
            break;
        case "deadline":
            newTask = new Deadline(taskDescription);
            String deadline = extractTaskOrDate(line, "by");
            if (deadline.isEmpty()) {
                throw new IncompletePromptException();
            }
            ((Deadline)newTask).setDeadline(deadline);
            newTask.setTaskType("D");
            break;
        case "event":
            newTask = new Event(taskDescription);
            String dateFrom = extractTaskOrDate(line, "from");
            String dateTo = extractTaskOrDate(line, "to");
            if (dateFrom.isEmpty() || dateTo.isEmpty()) {
                throw new IncompletePromptException();
            } else {
                ((Event)newTask).setEventFrom(dateFrom);
                ((Event)newTask).setEventTo(dateTo);
                ((Event)newTask).setEvent(true);
                newTask.setTaskType("E");
            }
            break;
        default:
            throw new UnknownPromptException();
        }
        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.print(INDENT);
        newTask.print();
        countTasks();
    }
    public void mark(String line, boolean isMark) {
        int index = Integer.parseInt(line.substring(isMark ? 5 : 7));
        Task markedTask = list.get(index - 1);
        if (markedTask.isMarked() == isMark) {
            System.out.println("This task is already set as " + (isMark ? "marked." : "unmarked."));
            return;
        }
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.print(INDENT);
        markedTask.setMarked(isMark);
        markedTask.print();
    }


    /**
     * Deletes a task from the task list based on the integer index inputted.
     *
     * @param index Index of task from task list to be deleted.
     */
    public void deleteTask(int index) {
        System.out.print(INDENT);
        lastActionTask = list.get(index - 1);
        lastActionTask.print();
        list.remove(index - 1);
        countTasks();
    }

    /** Prints all tasks in the task list. If empty, task list is not printed out. */
    public void printList() {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("      ");
                Task task = list.get(i);
                System.out.print(i + 1 + ".");
                task.print();
            }
        } else {
            System.out.println(INDENT + "There's nothing in this list.");
        }
    }
    /**
     * Returns task from task list based on index.
     *
     * @param index Index of task.
     * @return index-th task.
     */
    public Task getTask(int index) {
        return list.get(index);
    }
}