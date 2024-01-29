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
        while(!instruction.equals("bye")) //main loop of the chat bot
        {
            instruction = in.nextLine();
            if(instruction.equals("list")) //list method
            {
                int order = 1;
                for(String element:task_list){
                    if(element==null)
                        break;
                    System.out.println("\t"+String.valueOf(order)+". "+ element);
                    order++;
                }
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
                number_of_task++;
            }


        }
        System.out.println(prefix+"Bye.Hope to see you again soon!");
    }
}
