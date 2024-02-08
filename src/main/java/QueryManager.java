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
        String argument = q.getArgument();
        // return 0 means exit programme, return -1 means ask for query
        switch (command) {
            default:
                // command not found
                System.out.println("ERROR: command not found");
                return -1;
            case "list":
                list.show();
                return -1;
            case "mark":
                //list.markAsDone(argument);
                list.markAsDone(Integer.parseInt(argument));
                return -1;
            case "unmark":
                list.markAsNotDone(Integer.parseInt(argument));
                return -1;
            case "bye":
                System.out.println("Sankyuuu! BYE (^ _^ )");
                return 0;
            case "echo":
                System.out.println("Can I double check that you said: " + currentQuery.getArgument());
                return -1;
            case "todo":
                Task task = new Task();
                task.setDescription(argument);
                list.addTask(task);
                return -1;
            case "deadline":
                DeadlineTask deadline = new DeadlineTask(false, argument);
                // add deadline to list of task (substitutability)
                list.addTask(deadline);
                return -1;
            case "event":
                EventTask event = new EventTask(false, argument);
                list.addTask(event);
                return -1;
        }
    }

}
