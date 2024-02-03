import java.util.ArrayList;

public class Todo {

    static final String HORIZONTAL_LINE = "---------------------------------";
    private final ArrayList<Task> todoList;

    public int totalTasks;
    public Todo() {
        todoList = new ArrayList<>();
        totalTasks = 0;
    }

    public Task getTodo(int idx) {
        return this.todoList.get(idx);
    }
    public void addTodo(String task) {
        Task todo = new Task(task);
        this.todoList.add(todo);
        this.totalTasks++;
        printLine();
        System.out.println("added: " + task);
        printLine();
    }

    public void getMark(int idx) {
        if (idx < 0 || idx >= totalTasks) {
            System.out.println("OUT OF INDEX!!!");
            return;
        }
        System.out.println("Nice! I've marked this task as done:");
        Task currTask = getTodo(idx);
        currTask.markDone();
        printTodo(idx);
    }

    public void getUnmark(int idx) {
        if (idx < 0 || idx >= totalTasks) {
            System.out.println("OUT OF INDEX!!!");
            return;
        }

        System.out.println("OK, I've marked this task as not done yet:");
        Task currTask = getTodo(idx);
        currTask.unmarkDone();
        printTodo(idx);
    }
    public void printTodo(int idx) {
        String task = this.todoList.get(idx).getName();
        String mark = this.todoList.get(idx).getTick();
        System.out.print(mark);
        System.out.println(task);
        printLine();
    }
    public void printTodoList() {
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(i+1 + ". " + todoList.get(i).getTick() + todoList.get(i).getName());
        }
        printLine();
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
