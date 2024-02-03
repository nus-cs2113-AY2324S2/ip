import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static Boolean test_existence(Task[] tasks_list, String instruction){
        for(Task element:tasks_list){
            if(element==null)
                break;
            if(element.get_description().equals(instruction))
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        //init the parameters to be used
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name ="Altria";
        String prefix = "\n\t";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm "+name);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String instruction = " ";

        int number_of_task = 0;
        Task[] tasks_list = new Task[100];

        while(true) //main loop of the chat bot
        {
            instruction = in.nextLine();
            String[] split_instruction = instruction.split(" ", 2);

            if(instruction.equals("bye")) //end condition
            {
                System.out.println(prefix+"Bye.Hope to see you again soon!");
                break;
            }

            if(instruction.equals("list")) //list method
            {
                int order = 1;
                for(Task element:tasks_list){
                    if(element==null)
                        break;
                    System.out.println("\t"+String.valueOf(order)+"." + element);
                    order++;
                }
                continue;
            }


            if(split_instruction[0].equals("mark")) //mark method
            {
                int mark_number = Integer.parseInt(split_instruction[1]);
                mark_number--; //fit with start with 1
                tasks_list[mark_number].mark(true);
                System.out.println(prefix+"Nice! I've marked this task as done:");
                System.out.println("\t"+tasks_list[mark_number]);
                continue;
            }

            if(split_instruction[0].equals("unmark")) //mark method
            {
                int mark_number = Integer.parseInt(split_instruction[1]);
                mark_number--;
                tasks_list[mark_number].mark(false);
                System.out.println(prefix+"OK, I've marked this task as not done yet:");
                System.out.println("\t"+tasks_list[mark_number]);
                continue;
            }

            //case: the instructions is used to add a new task
            String tasks_type = split_instruction[0];
            String task_description = "";
            if(!instruction.equals("bye"))
                task_description = split_instruction[1].split("/", 2)[0];
            System.out.println(prefix+"Got it. I've added this task:");

            // add the task
            if(tasks_type.equals("todo"))
            {
                tasks_list[number_of_task] = new Todo(task_description);
            }
            if(tasks_type.equals("deadline"))
            {
                String task_date = split_instruction[1].split("/", 2)[1];
                task_date = task_date.replace("by ","");
                tasks_list[number_of_task] = new Deadline(task_description, task_date);
            }
            if(tasks_type.equals("event"))
            {
                String task_date = split_instruction[1].split("/", 2)[1];
                task_date = task_date.replace("/","");
                task_date = task_date.replace("from ","");
                tasks_list[number_of_task] = new Event(task_description, task_date);
            }

            System.out.println(prefix+tasks_list[number_of_task]+"\n");
            number_of_task++;
            System.out.println("Now you have "+number_of_task+" tasks in the list. \n");



        }

    }
}
