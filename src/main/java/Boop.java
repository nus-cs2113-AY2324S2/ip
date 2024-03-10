import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Boop {
    public static Task[] tasks = new Task[100];
    public static int count = 0;
    public static String filepath = "./data/chat.txt";

    public static void main(String[] args) {
        //load in task list from hard disk
        File direcName = new File("./data");
        if(!direcName.exists()) {
            direcName.mkdir();
        }
        File f = new File(filepath);
        boolean res;
        try {
            f.createNewFile();
            System.out.println(f.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("BITCH WHY DONT U WORK");
            e.printStackTrace();
            System.out.println(e);
        }
        try {
            loadSavedTasks();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm BOOP");
        System.out.println("What can I do for you?");
        System.out.println("You currently have " + count + " tasks on your list");
        System.out.println("____________________________________________________________");
        Scanner in = new Scanner(System.in);
        String command;
        while(true) {
            System.out.println("____________________________________________________________");
            command = in.nextLine();
            String[] comArr = command.split(" ");
            String curCommand = comArr[0];
            curCommand = curCommand.toLowerCase();
            if (curCommand.equals("bye")) {
                break;
            } else if (curCommand.equals("list")) {
                printLst();
            } else if (curCommand.equals("mark")) {
                mark(comArr);
            } else if (curCommand.equals("unmark")) {
                unmark(comArr);
            } else if (curCommand.equals("delete")) {
                delete(comArr);
            } else if (curCommand.equals("todo")||curCommand.equals("deadline")||curCommand.equals("event")) {
                addTask(curCommand, command);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("____________________________________________________________");
        }
        try {
            saveToDisk();
        } catch (IOException e) {
            System.out.println("couldnt save to disk");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void saveToDisk() throws IOException{
        FileWriter fw = new FileWriter(filepath, false);
        fw.close();
        for (int i = 0; i < count; i+=1 ) {
            Task cur = tasks[i];
            writeToFile(cur);
        }

    }

    public static void writeToFile(Task t) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(t.saveTaskFormat());
        fw.close();
    }
    public static void loadSavedTasks() throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String taskLine = s.nextLine();
            String[] taskArr = taskLine.split("\\s\\|\\s");
            Task t;
            if (taskArr[0].equals("T")) { //todotask
                t = new Todo(taskArr[2]);
            } else if (taskArr[0].equals("D")) { //deadline
                t = new Deadline(taskArr[2], taskArr[3]);
            } else { //event
                t = new Event(taskArr[2], taskArr[3], taskArr[4]);
            }
            if (taskArr[1].equals("1")) {
                t.markTask();
            }
            tasks[count] = t;
            count += 1;
        }
    }

    public static void printLst() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < count; i += 1) {
            Task t = tasks[i];
            System.out.println("" + (i + 1) + ". " + t.toString());;
        }
    }

    public static void delete(String[] arr) {
        if(arr.length <= 1) {
            System.out.println("Please specify which item to delete");
            return;
        }
        if (arr.length > 2) {
            System.out.println("Please only specify one item to mark at a time");
            return;
        }
        int markNum;
        try {
            markNum = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a number to delete");
            return;
        }
        if (markNum >= 1 && markNum <= count) {
            for(int i = markNum; i < count; i += 1) {
                tasks[i-1] = tasks[i];
            }
            count -= 1;
        } else {
            System.out.println("This number is not on the list");
        }
    }

    public static void mark(String[] arr) {
        if(arr.length <= 1) {
            System.out.println("Please specify which item to mark");
            return;
        }
        if (arr.length > 2) {
            System.out.println("Please only specify one item to mark at a time");
            return;
        }
        int markNum;
        try {
            markNum = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a number to mark");
            return;
        }
        if (markNum >= 1 && markNum <= count) {
            tasks[markNum - 1].markTask();
        } else {
            System.out.println("This number is not on the list");
        }
    }

    public static void unmark(String[] arr) {
        if(arr.length <= 1) {
            System.out.println("Please specify which item to unmark");
            return;
        }
        if (arr.length > 2) {
            System.out.println("Please only specify one item to unmark at a time");
            return;
        }
        int markNum;
        try {
            markNum = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a number to unmark");
            return;
        }
        if (markNum >= 1 && markNum <= count) {
            tasks[markNum - 1].unmarkTask();
        } else {
            System.out.println("This number is not on the list");
        }
    }

    public static void addTask(String com, String fullInput) {
        Task t;
        String[] inputArr = fullInput.split(" ");
        if (com.equals("todo")) {
            if (inputArr.length <= 1) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                return;
            }
            String desc = fullInput.substring(5);
            t = new Todo(desc);
        }else if (com.equals("deadline")) {
            if (inputArr.length <= 1) {
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                return;
            }
            if(!fullInput.contains("/by")) {
                System.out.println("OOPS!!! There must by a /by date for a deadline");
                return;
            }
            String desc = fullInput.substring(9);
            String[] descArr = desc.split(" /by ");
            t = new Deadline(descArr[0], descArr[1]);
        } else if (com.equals("event")) {
            if (inputArr.length <= 1) {
                System.out.println("OOPS!!! The description of a event cannot be empty.");
                return;
            }
            String desc = fullInput.substring(6);
            String[] descArr = desc.split(" /");
            if (descArr.length != 3) {
                System.out.println("OOPS!!! You must have both a /from field and /to field for an event (1)");
                return;
            }
            if((!descArr[1].contains("from")) || (!descArr[2].contains("to"))) {
                System.out.println("OOPS!!! You must have both a /from field and /to field for an event");
                return;
            }
            String from = descArr[1].substring(5);
            String to = descArr[2].substring(3);
            t = new Event(descArr[0], from, to);
        } else {
            t = new Task(fullInput);
        }
        tasks[count] = t;
        count += 1;
        try {
            writeToFile(t);
        } catch (IOException e){
            System.out.println("error in writing to file");
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}
