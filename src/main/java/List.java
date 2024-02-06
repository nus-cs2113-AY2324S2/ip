public class List {
    private Task[] list;
    private int itemCount;

    public List() {
        itemCount = 0;
        list = new Task[100];
    }

    public void printTaskCount() {
        System.out.println(String.format("\tYou now have %d tasks in the list.", itemCount));
    }

    private void printAddTaskMessage(Task newTask) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t\t%s", newTask));
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
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println(String.format("\t%s",list[index-1]));
    }

    public void unmarkTask(int index) {
        list[index - 1].setTaskAsNotDone();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println(String.format("\t%s",list[index-1]));
    }

    public void listAll() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0 ; i < itemCount; i++) {
            System.out.println(String.format("\t%d.%s", i + 1, list[i]));
        }
    }
}
