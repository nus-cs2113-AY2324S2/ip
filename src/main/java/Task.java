import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    //Attributes
    protected TaskType taskType;
    protected String name;
    protected boolean isDone;
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String DATA_PATH = "../../../out/data.txt";
    //Constructors
    Task (String name) {
        this.name = name;
        isDone = false;
        taskType = TaskType.TASK;
    }


    //Methods
    private static void loadFromDiskNoExceptionHandle() throws FileNotFoundException{
        File f = new File(DATA_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String temp = s.nextLine();
            String[] words = temp.split(",");
            if (words[0].equals("T")){
                tasks.add(new Todo(words[2], !words[1].equals("0")));
            } else if (words[0].equals("D")){
                tasks.add(new Deadline(words[2], words[3], !words[1].equals("0")));
            } else {
                tasks.add(new Event(words[2], words[3], words[4], !words[1].equals("0")));
            }
        }
    }

    public static void loadFromDisk(){
        try {
            loadFromDiskNoExceptionHandle();
        } catch (FileNotFoundException fnfe) {
            UI.printMessage("File not found");
        }
    }
    public TaskType getTaskType () {
        return taskType;
    }

    public String getCSV () {
        return (isDone ? "1" : "0") + "," + name;
    }

    public String toString () {
        return (isDone ? "[X] " : "[ ] ") + name;
    }

    public static void listTasks () {
        System.out.println(UI.LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println(UI.LINE_SEPARATOR);
    }

    public static void addTask (Task task) {
        System.out.println(UI.LINE_SEPARATOR);

        switch (task.getTaskType()) {
            case DEADLINE:
            case EVENT:
            case TODO:
                System.out.println("Got it. I've added this task:");
                tasks.add(task);
                break;
        }
        System.out.println(task);

        System.out.println(UI.LINE_SEPARATOR);
    }

    public static void delete(int index) {
        boolean isValidIndex;
        Task deletedTask = null;
        try {
            isValidIndex = true;
            deletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
        }   catch (IndexOutOfBoundsException ioobe) {
            isValidIndex = false;
            UI.printMessage("Index out of bound!");
        }   catch (NumberFormatException nfe) {
            isValidIndex = false;
            UI.printMessage("Invalid index!");
        }
        if (isValidIndex){
            System.out.println(UI.LINE_SEPARATOR);
            System.out.println("Deleted task " + deletedTask + ".");
            System.out.println("Now you have " + tasks.size() + " task(s) left.");
            System.out.println(UI.LINE_SEPARATOR);
        }
    }

    public static void mark (int index) {
        tasks.get(index - 1).isDone = true;
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }

    public static void unmark (int index) {
        tasks.get(index - 1).isDone = false;
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }

    private static boolean isValidTask (String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

    private static boolean isValidCommand (String command) {
        return command.equals("list") || command.equals("mark") || command.equals("unmark") || command.equals("delete")
                || command.equals("save") || isValidTask(command);
    }
    protected static void saveRaw() throws IOException{
        FileWriter fw = new FileWriter(DATA_PATH);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).getCSV() + "\n");
        }
        fw.close();
    }
    protected static void save(){
        try {
            saveRaw();
        } catch (IOException ioe){
            UI.printMessage("Something wrong with the data path");
        }
    }
    public static void responseToCommand (String command) {
        String[] commandWords = command.split(" ");
        if (!isValidCommand(commandWords[0])) {
           UI.printMessage("Command not recognized");
            return;
        }
        if (commandWords[0].equals("list")) {
            Task.listTasks();
        } else if (commandWords[0].equals("mark")) {
            mark(Integer.parseInt(commandWords[1]));
            save();
        } else if (commandWords[0].equals("unmark")) {
            unmark(Integer.parseInt(commandWords[1]));
            save();
        } else if (commandWords[0].equals("delete")){
            delete(Integer.parseInt(commandWords[1]));
            save();
        } else {
            if (Parser.isValidTaskCommand(command, commandWords)) {
                Task newTask;
                newTask = Parser.parseCommand(command);
                addTask(newTask);
                save();
            }
        }
    }
}
