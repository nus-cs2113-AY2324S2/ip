import java.util.Scanner;


public class Suv {
    final static int DEADLINE_KEYWORD_END_INDEX = 8;
    final static int TODO_KEYWORD_END_INDEX = 4;
    final static int EVENT_KEYWORD_END_INDEX = 5;
    final static int TO_KEYWORD_END_INDEX = 2;
    final static int FROM_KEYWORD_END_INDEX = 4;
    final static String LINE = "____________________________________________________________\n";

    public static void main(String[] args) {
        String name = "Suv";
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskIndex = 0;

        System.out.println(LINE +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                LINE
        );

        while(true) {
            String input = in.nextLine();
            System.out.println(LINE);
            if(input.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")){
                System.out.println(" Here are the tasks in your list:");
                for(int i = 0; i < taskIndex; i++){
                    int index = i + 1;
                    System.out.println(" " + index + "." + tasks[i] + LINE);
                }
            } else if (input.contains("unmark")){
                int n = Integer.parseInt(input.split(" ")[1]);
                tasks[n - 1].unMark();
                System.out.println(" OK, I've marked this task as not done yet:\n" + "   [ ] " + tasks[n - 1].getDescription() + LINE);

            } else if (input.contains("mark")){
                int n = Integer.parseInt(input.split(" ")[1]);
                Task currentTask = tasks[n - 1];
                currentTask.mark();
                System.out.println(" Nice! I've marked this task as done:\n" + "   [X] " + currentTask.getDescription() + LINE);

            } else if(input.contains("todo")){

                if(input.trim().length() > 4){
                    Todo newTask = new Todo(input.substring(TODO_KEYWORD_END_INDEX).trim());

                    tasks[taskIndex++] = newTask;
                    String helper = (taskIndex > 1) ? "s " : " ";
                    System.out.println(
                            " Got it. I've added this task:\n" + "  " + newTask +
                                    "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper + "in the list." + LINE
                    );
                } else {
                    System.out.println("Oh no! You are missing the Todo description\n" + LINE);
                }

            }else if(input.contains("deadline")){

                if(input.trim().length() > 8){
                    String by = input.split("/by")[1].trim();
                    String description = input.split("/by")[0].substring(DEADLINE_KEYWORD_END_INDEX).trim();

                    Deadline newTask = new Deadline(description, by);
                    tasks[taskIndex++] = newTask;
                    String helper = (taskIndex > 1) ? "s " : " ";
                    System.out.println(
                            " Got it. I've added this task:\n" + "  " + newTask +
                                    "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper +"in the list." + LINE
                    );
                } else {
                    System.out.println("Oh no! You are missing the Deadline details\n" + LINE);
                }

            } else if(input.contains("event")){

                if(input.trim().length() > 5){
                    String from = input.split("/")[1].trim().substring(FROM_KEYWORD_END_INDEX).trim();
                    String to = input.split("/")[2].trim().substring(TO_KEYWORD_END_INDEX).trim();
                    String description = input.split("/")[0].substring(EVENT_KEYWORD_END_INDEX).trim();

                    Event newTask = new Event(description, from, to);
                    tasks[taskIndex++] = newTask;

                    //If the number of tasks is 1 use the word 'task' instead of 'tasks'
                    String helper = (taskIndex > 1) ? "s " : " ";
                    System.out.println(
                            " Got it. I've added this task:\n" + "  " + newTask +
                                    "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper +"in the list." + LINE
                    );
                } else {
                    System.out.println("Oh no! You are missing the Event details\n" + LINE);
                }

            } else {
                System.out.println("Oh no! I am not sure what you mean.");
            }
        }
    }
}