public class QueryManager {
    private Query currentQuery;

    public QueryManager() {
        this(new Query());
    }

    public QueryManager(Query q) {
        inputQuery(q);
    }

    /**
     * simply input or update query
     * @param q
     */
    public void inputQuery(Query q) {
        currentQuery = q;
    }

    /**
     * input or update query and executes it
     * @param q
     * @param list
     * @return 0 to exit the programme, -1 to loop
     */
    public int executeQuery(Query q, TasksList list) {
        inputQuery(q);
        String command = q.getCommand();
        String argument = q.getArgument();
        // return 0 means exit programme, return -1 means ask for query
        try {
            switch (command) {
                default:
                    // command not found
                    System.out.println("ERROR: command not found");
                    return -1;
                case "list":
                    list.show();
                    return -1;
                case "mark": 
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
                    Task task = new Task(false, argument);
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
                case "delete":
                    list.deleteTask(argument);
                    return -1;
            }
        } catch (SalmonNotInListException e) {
            System.out.println("Gomen! Task is not in your list");
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid number to mark/unmark list!");
            return -1;
        } catch (SalmonMissingArgument e) {
            System.out.println("Please input an argument for your command!");
            return -1;
        }
    }
}
