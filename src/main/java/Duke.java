import DukeException.Command_Not_Exist;
import DukeException.No_Description;
import Task.Task;

import java.util.Scanner;
import DukeException.*;
import Task.*;
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


            if(instruction.startsWith("mark")) //mark method
            {
                int mark_number = Integer.parseInt(split_instruction[1]);
                mark_number--; //fit with start with 1
                tasks_list[mark_number].mark(true);
                System.out.println(prefix+"Nice! I've marked this task as done:");
                System.out.println("\t"+tasks_list[mark_number]);
                continue;
            }

            if(instruction.startsWith("unmark")) //mark method
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
            try {
                if (tasks_type.equals("todo") || tasks_type.equals("deadline") || tasks_type.equals("event")) {
                    if (split_instruction.length == 1) {
                        throw new No_Description();
                    }

                    task_description = split_instruction[1].split("/", 2)[0];
                    switch (tasks_type) {
                        case "todo":
                            tasks_list[number_of_task] = new Todo(task_description);
                            break;
                        case "deadline": {
                            String task_date = split_instruction[1].split("/", 2)[1];
                            task_date = task_date.replace("by ", "");
                            tasks_list[number_of_task] = new Deadline(task_description, task_date);
                            break;
                        }
                        case "event": {
                            String task_date = split_instruction[1].split("/", 2)[1];
                            task_date = task_date.replace("/", "");
                            task_date = task_date.replace("from ", "");
                            tasks_list[number_of_task] = new Event(task_description, task_date);
                            break;

                        }

                    }
                    System.out.println(prefix + "Got it. I've added this task:");
                    System.out.println(prefix + tasks_list[number_of_task] + "\n");
                    number_of_task++;
                    System.out.println("Now you have " + number_of_task + " tasks in the list. \n");
                }
                else
                {
                    throw new Command_Not_Exist();
                }

            }catch(No_Description e){
                System.out.println("Sorry, you should give a more detailed description of your command.");
            }catch(Command_Not_Exist e){
                System.out.println("Sorry, I do not understand your command.");
            }catch(IndexOutOfBoundsException e){
                System.out.println("Sorry, you should use \\ to indicate the time!");
            }
            continue;


        }

    }
}
