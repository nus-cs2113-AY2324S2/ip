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
    public int executeQuery(Query q){
        inputQuery(q);
        String command = q.getCommand();
        switch (command) {
            default:
                System.out.println("NANI? I don't understand");
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
