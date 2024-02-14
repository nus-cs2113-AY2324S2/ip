public class List {
    private Task[] list;
    private int itemCount;

    public List() {
        itemCount = 0;
        list = new Task[100];
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

    public void addTodo(String description) {
        Task newTask = new Todo(description);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void addDeadline(String description, String by) {
        Task newTask = new Deadline(description, by);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void addEvent(String description, String from, String to) {
        Task newTask = new Event(description, from, to);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void markTask(int index) {
        list[index - 1].setTaskAsDone();
        Parser.printTaskMarkAsDone(list[index-1]);
    }

    public void unmarkTask(int index) {
        list[index - 1].setTaskAsNotDone();
        Parser.printTaskUnmarkAsNotDone(list[index-1]);
    }

    public void listAll() {
        Parser.printList(list, itemCount);
    }
}
