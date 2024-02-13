package suv;

public class TaskManager{
    final static int DEADLINE_KEYWORD_END_INDEX = 8;
    final static int TODO_KEYWORD_END_INDEX = 4;
    final static int EVENT_KEYWORD_END_INDEX = 5;
    final static int TO_KEYWORD_END_INDEX = 2;
    final static int FROM_KEYWORD_END_INDEX = 4;
    final static String LINE = "____________________________________________________________\n";
    Task[] tasks;
    public static int taskIndex;

    TaskManager(){
        tasks = new Task[100];
        taskIndex = 0;
    }

    public void handleInput(String input){
        try {
            if(input.equals("bye")){
                handleBye();
            } else if (input.equals("list")){
                handleList(input);
            } else if (input.contains("unmark")){
                handleUnmark(input);
            } else if (input.contains("mark")){
                handleMark(input);
            } else if(input.contains("todo")){
                handleTodo(input);
            }else if(input.contains("deadline")){
                handleDeadline(input);
            } else if(input.contains("event")){
                handleEvent(input);
            } else {
                throw new SuvException(LINE +"Oh no! I am not sure what you mean.");
            }
        } catch (SuvException e){
            System.out.println(e.warning);
        }
    }

    public void handleTodo(String input) throws SuvException {
        if(input.trim().length() > 4){
            Todo newTask = new Todo(input.substring(TODO_KEYWORD_END_INDEX).trim());

            tasks[taskIndex++] = newTask;
            String helper = (taskIndex > 1) ? "s " : " ";
            System.out.println(LINE +
                    " Got it. I've added this task:\n" + "  " + newTask +
                            "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper + "in the list.\n" + LINE
            );
        } else {
            throw new SuvException(LINE +"Oh no! You are missing the suv.Todo description\n" + LINE);
        }
    }

    public void handleDeadline(String input) throws SuvException{
        if(input.trim().length() > 8 && input.contains("/by")){
            String by = input.split("/by")[1].trim();
            String description = input.split("/by")[0].substring(DEADLINE_KEYWORD_END_INDEX).trim();

            Deadline newTask = new Deadline(description, by);
            tasks[taskIndex++] = newTask;
            String helper = (taskIndex > 1) ? "s " : " ";
            System.out.println(LINE +
                    " Got it. I've added this task:\n" + "  " + newTask +
                            "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper +"in the list.\n" + LINE
            );
        } else {
            throw new SuvException(LINE +"Oh no! You are missing the suv.Deadline details\n" + LINE);
        }
    }

    public void handleEvent(String input) throws SuvException{
        if(input.trim().length() > 5 && input.contains("/from") &&  input.contains("/to")){
            String from = input.split("/")[1].trim().substring(FROM_KEYWORD_END_INDEX).trim();
            String to = input.split("/")[2].trim().substring(TO_KEYWORD_END_INDEX).trim();
            String description = input.split("/")[0].substring(EVENT_KEYWORD_END_INDEX).trim();

            Event newTask = new Event(description, from, to);
            tasks[taskIndex++] = newTask;

            //If the number of tasks is 1 use the word 'task' instead of 'tasks'
            String helper = (taskIndex > 1) ? "s " : " ";
            System.out.println(LINE +
                    " Got it. I've added this task:\n" + "  " + newTask +
                            "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper +"in the list.\n" + LINE
            );
        } else {
            throw new SuvException(LINE + "Oh no! You are missing the suv.Event details\n" + LINE);
        }
    }

    public void handleMark(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        Task currentTask = tasks[n - 1];
        currentTask.mark();
        System.out.println(LINE + " Nice! I've marked this task as done:\n" + "   [X] " + currentTask.getDescription() + "\n" + LINE);
    }

    public void handleUnmark(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        tasks[n - 1].unMark();
        System.out.println(LINE + " OK, I've marked this task as not done yet:\n" + "   [ ] " + tasks[n - 1].getDescription() + "\n" + LINE);
    }

    public void handleList(String input) throws SuvException {
        System.out.println(LINE + " Here are the tasks in your list:");
        for(int i = 0; i < taskIndex; i++){
            int index = i + 1;
            System.out.println(" " + index + "." + tasks[i] );
        }
        System.out.println(LINE);
    }

    public void  handleBye() throws SuvException{
        System.out.println(LINE +" Bye. Hope to see you again soon!\n" + LINE);
    }


}
