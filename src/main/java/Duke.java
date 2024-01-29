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

        while(!instruction.equals("bye")) //main loop of the chat bot
        {
            instruction = in.nextLine();
            String[] split_instruction = instruction.split(" ");

            if(instruction.equals("list")) //list method
            {
                int order = 1;
                for(Task element:tasks_list){
                    if(element==null)
                        break;
                    System.out.println("\t"+String.valueOf(order)+"["+element.getStatusIcon()+"] "+element.get_description());
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
                System.out.println("\t"+"[X] "+tasks_list[mark_number].get_description());
                continue;
            }

            if(split_instruction[0].equals("unmark")) //mark method
            {
                int mark_number = Integer.parseInt(split_instruction[1]);
                mark_number--;
                tasks_list[mark_number].mark(false);
                System.out.println(prefix+"OK, I've marked this task as not done yet:");
                System.out.println("\t"+"[ ] "+tasks_list[mark_number].get_description());
                continue;
            }


            if(test_existence(tasks_list, instruction))  //add the task if not exist
            {
                System.out.println(prefix+"The task "+ instruction + " already exist!");
                continue;
            }
            else
            {
                System.out.println(prefix+"added: "+instruction+"\n");
                // add the task
                tasks_list[number_of_task] = new Task(instruction);
                number_of_task++;
            }


        }
        System.out.println(prefix+"Bye.Hope to see you again soon!");
    }
}
