import java.util.Scanner;
import java.util.Arrays;

public class Duke {
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
        String[] task_list = new String[100];
        int number_of_task = 0;
        Integer[] done_or_not = new Integer[100];
        while(!instruction.equals("bye")) //main loop of the chat bot
        {
            instruction = in.nextLine();
            String[] split_instruction = instruction.split(" ");

            if(instruction.equals("list")) //list method
            {
                int order = 1;
                for(String element:task_list){
                    if(element==null)
                        break;
                    if(done_or_not[order-1]==0)
                        System.out.println("\t"+String.valueOf(order)+". "+ "[ ] "+element);
                    else
                        System.out.println("\t"+String.valueOf(order)+". "+"[X] " + element);
                    order++;
                }
                continue;
            }


            if(split_instruction[0].equals("mark")) //mark method
            {
                int mark_number = Integer.parseInt(split_instruction[1]);
                mark_number--; //fit with start with 1
                done_or_not[mark_number] = 1;
                System.out.println(prefix+"Nice! I've marked this task as done:");
                System.out.println("\t"+"[X] "+task_list[mark_number]);
                continue;
            }

            if(split_instruction[0].equals("unmark")) //mark method
            {
                int mark_number = Integer.parseInt(split_instruction[1]);
                mark_number--;
                done_or_not[mark_number] = 0;
                System.out.println(prefix+"OK, I've marked this task as not done yet:");
                System.out.println("\t"+"[ ] "+task_list[mark_number]);
                continue;
            }


            if(Arrays.asList(task_list).contains(instruction))  //add the task if not exist
            {
                System.out.println(prefix+"The task "+ instruction + " already exist!");
                continue;
            }
            else
            {
                System.out.println(prefix+"added: "+instruction+"\n");
                task_list[number_of_task] = instruction;
                done_or_not[number_of_task] = 0;
                number_of_task++;
            }


        }
        System.out.println(prefix+"Bye.Hope to see you again soon!");
    }
}
