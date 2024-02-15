public class List {
    private Task[] list;
    private int itemCount;

    public List() {
        itemCount = 0;
        list = new Task[100];
    }

    public int getItemCount() {
        return itemCount;
    }

    public void printTaskCount() {
        Parser.printTaskCount(itemCount);
    }

    private void printAddTaskMessage(Task newTask) {
        Parser.printAddTaskMessage(newTask);
        printTaskCount();
    }

    private void addTask(Task newTask) {
        list[itemCount] = newTask;
        itemCount++;
    }

    public void addTodo(String description) throws EmptyTaskNameException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        Task newTask = new Todo(description);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void addDeadline(String description, String by) throws EmptyTaskNameException, EmptyByException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        } else if (by.isEmpty()) {
            throw new EmptyByException();
        }

        Task newTask = new Deadline(description, by);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void addEvent(String description, String from, String to) throws EmptyTaskNameException, EmptyFromException, EmptyToException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        } else if (from.isEmpty()) {
            throw new EmptyFromException();
        } else if (to.isEmpty()) {
            throw new EmptyToException();
        }

        Task newTask = new Event(description, from, to);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void markTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > itemCount) {
            throw new IllegalListIndexException();
        }

        list[index - 1].setTaskAsDone();
        Parser.printTaskMarkAsDone(list[index-1]);
    }

    public void unmarkTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > itemCount) {
            throw new IllegalListIndexException();
        }

        list[index - 1].setTaskAsNotDone();
        Parser.printTaskUnmarkAsNotDone(list[index-1]);
    }

    public void listAll() {
        Parser.printList(list, itemCount);
    }
}
