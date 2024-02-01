import java.util.Scanner;

public class QueryManager {
    private Query currentQuery;

    public QueryManager() {
        this(new Query());
    }

    public QueryManager(Query q){
        inputQuery(q);
    }

    // simply input or update query
    public void inputQuery(Query q){
        currentQuery = q;
    }

    // input or update query and executes it
    public int executeQuery(Query q, TasksList list){
        inputQuery(q);
        String command = q.getCommand();
        switch (command) {
            default:
                // adding task to tasks list
                Task task = new Task();
                task.setDescription(q.getInput());
                list.addTask(task);
                System.out.println("added: " + list.getLatestTask().getDescription());
                return -1;
            case "list":
                list.show();
                return -1;
            case "bye":
                System.out.println("Sankyuuu! BYE (^ _^ )");
                return 0;
            case "echo":
                System.out.println("Can I double check that you said: " + currentQuery.getArgument());
                return -1;

        }
    }

}
