package Utils;

import Task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents the Storage of the ChatBot. A <code>Storage</code> object corresponds to
 * a Storage of ChatBot to manage the memory of the ChatBot.
 */
public class Storage {
    ArrayList<Task> task_list;
    public Storage(ArrayList<Task> task_list){
        this.task_list = task_list;
    }

    public Storage(){
        this.task_list = new ArrayList<Task>();
    }

    public static void saveStatus(ArrayList<Task> tasks_list) throws IOException {
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

    public static ArrayList<Task> loadStatus() throws IOException {
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
                tasks_list.add(new Todo(processed_data[2].trim()));
                if(processed_data[1].equals(" 1 ")){
                    tasks_list.get(task_number).mark(true);
                }
                task_number++;
            }
            else if(current_line.startsWith("D")){
                tasks_list.add(new Deadline(processed_data[2].trim(),processed_data[3].trim()));
                if(processed_data[1].equals(" 1 ")){
                    tasks_list.get(task_number).mark(true);
                }
                task_number++;
            }
            else if(current_line.startsWith("E")){
                tasks_list.add(new Event(processed_data[2].trim(),processed_data[3].trim()));
                if(processed_data[1].equals(" 1 ")){
                    tasks_list.get(task_number).mark(true);
                }
                task_number++;
            }
            else{
                System.out.println("\tThe load data is not in correct format, stop loading");
                return tasks_list;
            }
        }
        return tasks_list;
    }
}
