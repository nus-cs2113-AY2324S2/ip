import DukeException.Command_Not_Exist;
import DukeException.No_Description;
import Task.Task;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Scanner;
import DukeException.*;
import Task.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Duke {
    public static void save_status(ArrayList<Task> tasks_list) throws IOException {
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
    public static ArrayList<Task> load_status() throws IOException {
        File directory = new File("./data");
        File Load_file = new File(directory, "saver.txt");
        ArrayList<Task> tasks_list = new ArrayList<>();

        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (!Load_file.exists()) {
                Load_file.createNewFile();
                return new ArrayList<Task>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Task>();
        }

        int task_number=0;
        Scanner data = new Scanner(Load_file);
        while (data.hasNext()){
            String current_line = data.nextLine();
            String[] processed_data = current_line.split("\\|");
            if(current_line.startsWith("T")){
                tasks_list.add(new Todo(processed_data[2]));
                if(processed_data[1].equals(" 1 ")){
                    tasks_list.get(task_number).mark(true);
                }
                task_number++;
            }
            else if(current_line.startsWith("D")){
                tasks_list.add(new Deadline(processed_data[2],processed_data[3]));
                if(processed_data[1].equals(" 1 ")){
                    tasks_list.get(task_number).mark(true);
                }
                task_number++;
            }
            else if(current_line.startsWith("E")){
                tasks_list.add(new Event(processed_data[2],processed_data[3]));
                if(processed_data[1].equals(" 1 ")){
                    tasks_list.get(task_number).mark(true);
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
        //int number_of_task = 0;
        ArrayList<Task> tasks_list = load_status();

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
                if(tasks_list.isEmpty()){
                    System.out.println("\t"+"There is currently nothing in my memory, nothing to list!"+"\n");
                }
                int order = 1;
                for(Task element:tasks_list){
                    if(element==null)
                        break;
                    System.out.println("\t"+String.valueOf(order)+"." + element);
                    order++;
                }
                continue;
            }

            try {
                if (instruction.startsWith("mark")) //mark method
                {
                    int mark_number = Integer.parseInt(split_instruction[1]);
                    mark_number--; //fit with start with 1
                    tasks_list.get(mark_number).mark(true);
                    System.out.println(prefix + "Nice! I've marked this task as done:");
                    System.out.println("\t" + tasks_list.get(mark_number));
                    System.out.println("Status changed, I will save the data for you!");
                    save_status(tasks_list);
                    continue;
                }
                if (instruction.startsWith("unmark")) //mark method
                {
                    int mark_number = Integer.parseInt(split_instruction[1]);
                    mark_number--;
                    tasks_list.get(mark_number).mark(false);
                    System.out.println(prefix + "OK, I've marked this task as not done yet:");
                    System.out.println("\t" + tasks_list.get(mark_number));
                    System.out.println("Status changed, I will save the data for you!");
                    save_status(tasks_list);
                    continue;
                }

                if (instruction.startsWith("delete")) {
                    int delete_number = Integer.parseInt(split_instruction[1]);
                    System.out.println("\t" + "I see, I will remove the task " + delete_number + "\n");
                    System.out.println("\t" + tasks_list.get(delete_number - 1));
                    tasks_list.remove(delete_number - 1);
                    System.out.println("\t" + "Now you have " + tasks_list.size() + " tasks in my memory \n");
                    System.out.println("Status changed, I will save the data for you!");
                    save_status(tasks_list);
                    continue;
                }
            }catch(IndexOutOfBoundsException e){
                System.out.println("\t You should input a number smaller than "+(tasks_list.size()+1)+" !");
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
                        case "todo": {
                            tasks_list.add(new Todo(task_description));
                            break;
                        }
                        case "deadline": {
                            String task_date = split_instruction[1].split("/", 2)[1];
                            task_date = task_date.replace("by ", "");
                            tasks_list.add(new Deadline(task_description, task_date));
                            break;
                        }
                        case "event": {
                            String task_date = split_instruction[1].split("/", 2)[1];
                            task_date = task_date.replace("/", "");
                            task_date = task_date.replace("from ", "");
                            tasks_list.add(new Event(task_description, task_date));
                            break;

                        }

                    }
                    System.out.println(prefix + "Got it. I've added this task:");
                    System.out.println(prefix + tasks_list.get(tasks_list.size()-1) + "\n");
                    System.out.println("Now you have " + tasks_list.size() + " tasks in the list. \n");
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
