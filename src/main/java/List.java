import java.util.ArrayList;

public class List {

    static final String HORIZONTAL_LINE = "---------------------------------";
    private final ArrayList<Task> todoList;

    public int totalTasks;
    public List() {
        todoList = new ArrayList<>();
        totalTasks = 0;
    }

    public Task getTodo(int idx) {
        return this.todoList.get(idx);
    }

    public void addTodo(String[] todo) {
        StringBuilder todoText = new StringBuilder(todo[1]);
        for (int i = 2; i < todo.length; i++) {
            todoText.append(' ').append(todo[i]);
        }
        Task task = new Task(todoText.toString(), 'T');
        addTask(task);
    }

    public void addEvent(String[] event) {
        StringBuilder eventText = new StringBuilder(event[1]);
        int i = 2;
        while (!event[i].startsWith("/")) {
            eventText.append(' ').append(event[i]);
            i++;
        }
        eventText.append(' ').append("(from: ");
        i++;
        while (!event[i].startsWith("/")) {
            eventText.append(' ').append(event[i]);
            i++;
        }
        i++;
        eventText.append(' ').append("to: ").append(event[i]).append(')');
        Task task = new Task(eventText.toString(), 'E');
        addTask(task);
    }

    public void addDeadline(String[] deadline) {
        StringBuilder deadlineText = new StringBuilder(deadline[1]);
        int i = 2;
        while (!deadline[i].startsWith("/")) {
            deadlineText.append(' ').append(deadline[i]);
            i++;
        }
        deadlineText.append(' ').append("(by: ");
        i++;
        while (i < deadline.length - 1) {
            deadlineText.append(deadline[i]).append(' ');
            i++;
        }
        deadlineText.append(deadline[i]).append(')');
        Task task = new Task(deadlineText.toString(), 'D');
        addTask(task);
    }

    public void addTask(Task task) {
        this.todoList.add(task);
        printLine();
        System.out.println("Got it. I've added this task:");
        this.todoList.get(totalTasks).printTask();
        this.totalTasks++;
        System.out.println(' ');
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }

    public void getMark(int idx, String mark) {
        if (idx < 0 || idx >= totalTasks) {
            System.out.println("OUT OF INDEX!!!");
            return;
        }
        Task currTask = getTodo(idx);
        if (mark.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            currTask.markDone();
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            currTask.unmarkDone();
            printTaskWithLine(idx);
        }
        printTaskWithLine(idx);
    }

    public void printTaskWithLine(int idx) {
        this.todoList.get(idx).printTask();
        System.out.println(' ');
        printLine();
    }

    public void printTodoList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.print(i+1 + ". ");
            todoList.get(i).printTask();
            System.out.println(' ');
        }
        printLine();
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
