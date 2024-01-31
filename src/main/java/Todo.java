import java.util.ArrayList;

public class Todo {

    private final ArrayList<String> todoList;
    public Todo() {
        todoList = new ArrayList<>();
    }

    public void addList(String task) {
        todoList.add(task);
    }

    public void printTodo() {
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(i+1 + ". " + todoList.get(i));
        }
    }
}
