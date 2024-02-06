import java.util.Scanner;

public class Suv {
    public static void main(String[] args) {
        String name = "Suv";
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskIndex = 0;

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n"
        );

        while(true) {
            String input = in.nextLine();
            if(input.equals("bye")){
                System.out.println( "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n"
                );
                break;
            } else if (input.equals("list")){
                System.out.println("____________________________________________________________\n" + " Here are the tasks in your list:");
                for(int i = 0; i < taskIndex; i++){
                    int index = i + 1;
                    System.out.println(" " + index + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________\n"
                );
            } else if (input.contains("unmark")){
                int n = Integer.parseInt(input.split(" ")[1]);
                tasks[n - 1].unMark();
                System.out.println("____________________________________________________________\n" +
                        " OK, I've marked this task as not done yet:\n" + "   [ ] " + tasks[n - 1].getDescription() +
                        "\n____________________________________________________________\n"
                );
            } else if (input.contains("mark")){
                int n = Integer.parseInt(input.split(" ")[1]);
                Task currentTask = tasks[n - 1];
                currentTask.mark();
                System.out.println("____________________________________________________________\n" +
                        " Nice! I've marked this task as done:\n" + "   [X] " + currentTask.getDescription() +
                        "\n____________________________________________________________\n"
                );
            } else if(input.contains("todo")){
                Todo newTask = new Todo(input.substring(4).trim());

                tasks[taskIndex++] = newTask;
                String helper = (taskIndex > 1) ? "s " : " ";
                System.out.println("____________________________________________________________\n" +
                        " Got it. I've added this task:\n" + "  " + newTask +
                        "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper + "in the list." +
                        "\n____________________________________________________________\n"
                );
            }else if(input.contains("deadline")){
                String by = input.split("/by")[1].trim();
                String description = input.split("/by")[0].substring(8).trim();

                Deadline newTask = new Deadline(description, by);
                tasks[taskIndex++] = newTask;
                String helper = (taskIndex > 1) ? "s " : " ";
                System.out.println("____________________________________________________________\n" +
                        " Got it. I've added this task:\n" + "  " + newTask +
                        "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper +"in the list." +
                        "\n____________________________________________________________\n"
                );
            } else if(input.contains("event")){
                String from = input.split("/")[1].trim().substring(4).trim();
                String to = input.split("/")[2].trim().substring(2).trim();
                String description = input.split("/")[0].substring(5).trim();

                Event newTask = new Event(description, from, to);
                tasks[taskIndex++] = newTask;

                //If the number of tasks is 1 use the word 'task' instead of 'tasks'
                String helper = (taskIndex > 1) ? "s " : " ";
                System.out.println("____________________________________________________________\n" +
                        " Got it. I've added this task:\n" + "  " + newTask +
                        "\n Now you have " + Integer.toString((taskIndex)) +" task" + helper +"in the list." +
                        "\n____________________________________________________________\n"
                );
            }
        }
    }
}