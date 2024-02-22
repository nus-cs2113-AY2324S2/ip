import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Bot {
    private final String filePath = "./data/Venti.txt";
    ArrayList<Task> taskList;
    public Bot() {

        taskList=new ArrayList<Task>();
        createFileIfNotExist();
        loadTasksFromFile();
    }
    private void echo(String st)
    {
        System.out.println("added: "+st);
        printLine();
    }
    private void createFileIfNotExist() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create directory if it doesn't exist
            file.createNewFile(); // Create file if it doesn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasksFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                // Assuming the format is: Type | Status | Description | [Additional Info]
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        taskList.add(new Todo(parts[2],parts[1].equals("1")));
                        break;
                    case "D":
                        taskList.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                        break;
                    case "E":
                        taskList.add(new Event(parts[2], parts[3],parts[4], parts[1].equals("1")));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file.");
        }
    }

    private void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks from file.");
        }
    }

    private void printLine()
    {
        System.out.println("____________________________________________________________");
    }
    private void addList(Task t)
    {
        this.taskList.add(t);
        saveTasksToFile();
    }
    private void sayBye()
    {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    private void printList()
    {
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<this.taskList.size();i++)
        {
            System.out.println((i+1)+". "+this.taskList.get(i).toString());
        }
        printLine();
    }
    private void markAsDone(int index)
    {
        if (index >= 0 && index < this.taskList.size()) {
            if(this.taskList.get(index).isComplete()){
                System.out.println("Sorry, this task have been marked as completed. You cannot mark this task again.");
                printLine();
                return;
            }
            System.out.println("Nice! I've marked this task as done:");
            this.taskList.get(index).markTaskAsDone();
            System.out.println(this.taskList.get(index).toString());
            printLine();
        } else {
            System.out.println("Invalid task index.");
            printLine();
        }
        saveTasksToFile();
    }
    private void unmarkAsDone(int index)
    {
        if (index >= 0 && index < this.taskList.size()) {
            if(!this.taskList.get(index).isComplete()) {
                System.out.println("Sorry, this task is not completed yet. You cannot unmark as done.");
                printLine();
                return;
            }
            System.out.println("OK, I've marked this task as not done yet:");
            this.taskList.get(index).unmarkTaskAsDone();
            System.out.println(this.taskList.get(index).toString());
            printLine();
        } else {
            System.out.println("Invalid task index.");
            printLine();
        }
        saveTasksToFile();
    }

    private void deleteTask(int index) throws BotException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new BotException("Invalid task index.");
        }
        Task removedTask = this.taskList.remove(index);
        Task.deleteTask();
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + Task.number + " tasks in the list.");
        printLine();
        saveTasksToFile();
    }

    private void handleTodo(String input) throws BotException{
        if (input.trim().equals("todo")) {
            throw new BotException("The description of a todo cannot be empty.");
        }
        String taskDescription = input.substring(5);
        System.out.println("Got it. I've added this task:");
        Todo todo = new Todo(taskDescription);
        System.out.println(todo);
        addList(todo);

    }

    private void handleDeadline(String input) throws BotException{
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new BotException("The deadline date of a deadline cannot be empty. (in format: /by xxx )");
        }
        try{
            String taskDescription = parts[0].substring(9);
            String by = parts[1];
            Deadline deadline = new Deadline(taskDescription, by);
            printLine();
            System.out.println("Got it. I've added this task:");
            addList(deadline);
            System.out.println(deadline);
        } catch(IndexOutOfBoundsException e){
            printLine();
            System.out.println("Opps! The content of deadline is missing.");
            printLine();
        }

    }

    private void handleEvent(String input) throws  BotException{
        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            throw new BotException("The time (from and to) of an event cannot be empty. (in format: /from xx /to xx)");
        }
        try{
            String taskDescription = parts[0].substring(6);
            String from = parts[1];
            String to = parts[2];
            System.out.println("Got it. I've added this task:");
            Event event = new Event(taskDescription, from, to);
            System.out.println(event);
            addList(event);
        } catch(IndexOutOfBoundsException e){
            printLine();
            System.out.println("Opps! The event description is missing");
            printLine();
        }
    }

    private void handleMark(String input) throws BotException{
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new BotException("The index of mark cannot be empty.");
        }
        try
        {
            int index = Integer.parseInt(parts[1]) - 1;
            if(index+1> taskList.size()) {
                throw new BotException("The index is out of the list size."); //check the index range
            }
            markAsDone(index);
        } catch (NumberFormatException e){
            System.out.println("The index is not a number! "); // check the index kind
        }
    }

    private void handleUnmark(String input) throws  BotException{
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new BotException("The index of unmark cannot be empty.");
        }
        try{
            int index = Integer.parseInt(parts[1]) - 1;
            if(index+1> taskList.size()) {
                throw new BotException("The index is out of the list size."); //check the index range
            }
            unmarkAsDone(index);
        } catch (NumberFormatException e){
            System.out.println("The index is not a number! "); // check the index kind
        }
    }

    private void handleDelete(String input) throws  BotException{
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new BotException("The index of delete cannot be empty.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            deleteTask(index);
        } catch (NumberFormatException e){
            System.out.println("The index is not a number! "); // check the index kind
            printLine();
        }


    }

    private void processInput(String input) throws BotException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        switch (command) {
            case "list":
                printList();
                break;
            case "mark":
                handleMark(input);
                break;
            case "unmark":
                handleUnmark(input);
                break;
            case "todo":
                handleTodo(input);
                break;
            case "deadline":
                handleDeadline(input);
                break;
            case "event":
                handleEvent(input);
                break;
            case "delete":
                handleDelete(input);
                break;
            default:
                throw new BotException("I'm sorry, but I don't know what that means :-(");
        }

    }
    protected void run() {
        Scanner sc = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Venti.");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                }
                processInput(input);
            } catch (BotException e) {
                printLine();
                System.out.println("OOPS!!! " + e.getMessage());
                printLine();
            }
        }
        sayBye();
    }
}
