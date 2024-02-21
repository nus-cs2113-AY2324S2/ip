import DukeException.Command_Not_Exist;
import DukeException.No_Description;
import Task.Task;

import java.io.IOException;
import java.util.Scanner;
import DukeException.*;
import Task.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

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
    public static void save_status(Task[] tasks_list) throws IOException {
        File directory = new File("data");
        File file = new File(directory, "saver.txt");

        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        FileWriter fw = new FileWriter("./data/saver.txt");

        for(Task task:tasks_list){
            if(task==null){
                break;
            }
            String task_string = task.toString();
            if(task_string.startsWith("[T]")){
                fw.write("T | ");
                if(task.getStatusIcon().equals("X"))
                    fw.write("1 | ");
                else
                    fw.write("0 | ");
                fw.write(task.get_description());
            }
            else if(task_string.startsWith("[D]")){
                fw.write("D | ");
                if(task.getStatusIcon().equals("X"))
                    fw.write("1 | ");
                else
                    fw.write("0 | ");
                fw.write(task.get_description()+" | ");
                fw.write(task.get_date());
            }
            else if(task_string.startsWith("[E]")){
                fw.write("E | ");
                if(task.getStatusIcon().equals("X"))
                    fw.write("1 | ");
                else
                    fw.write("0 | ");
                fw.write(task.get_description()+" | ");
                fw.write(task.get_date());
            }
            fw.write(System.lineSeparator());
        }

        fw.close();
    }
    public static Task[] load_status() throws IOException {
        File directory = new File("./data");
        File Load_file = new File(directory, "saver.txt");
        Task[] tasks_list = new Task[100];
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (!Load_file.exists()) {
                Load_file.createNewFile();
                return new Task[100];
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Task[100];
        }

        int task_number=0;
        Scanner data = new Scanner(Load_file);
        while (data.hasNext()){
            String current_line = data.nextLine();
            String[] processed_data = current_line.split("\\|");
            if(current_line.startsWith("T")){
                tasks_list[task_number] =  new Todo(processed_data[2]);
                if(processed_data[1].equals(" 1 ")){
                    tasks_list[task_number].mark(true);
                }
                task_number++;
            }
            else if(current_line.startsWith("D")){
                tasks_list[task_number] = new Deadline(processed_data[2],processed_data[3]);
                if(processed_data[1].equals(" 1 ")){
                    tasks_list[task_number].mark(true);
                }
                task_number++;
            }
            else if(current_line.startsWith("E")){
                tasks_list[task_number] = new Event(processed_data[2],processed_data[3]);
                if(processed_data[1].equals(" 1 ")){
                    tasks_list[task_number].mark(true);
                }
                task_number++;
            }
            else{
                System.out.println("The load data is not in correct format, stop loading");
                return tasks_list;
            }
        }
        return tasks_list;
    }
    public static void main(String[] args) throws IOException {
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
        Task[] tasks_list = load_status();
        for(int i=0;i<100;i++){
            if(tasks_list[i]==null){
                number_of_task =i;
                break;
            }

        }

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
                System.out.println("Status changed, I will save the data for you!");
                save_status(tasks_list);
                continue;
            }

            if(instruction.startsWith("unmark")) //mark method
            {
                int mark_number = Integer.parseInt(split_instruction[1]);
                mark_number--;
                tasks_list[mark_number].mark(false);
                System.out.println(prefix+"OK, I've marked this task as not done yet:");
                System.out.println("\t"+tasks_list[mark_number]);
                System.out.println("Status changed, I will save the data for you!");
                save_status(tasks_list);
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
                    System.out.println("Status changed, I will save the data for you!");
                    save_status(tasks_list);
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
