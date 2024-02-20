import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;


import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class John {
    //Load in the data and import it into the task list
    static List<Task> tasks;
    static {
        try {
            tasks = DataManager.readData("src/main/java/data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("ヽ(•‿•)ノ");
         System.out.println("-----------------------------------");
         System.out.println("Heya, Im ya Chatbot, call me John!\nWhat can I do for you today?");
         // Taking input from the user
         getUserInput();
         System.out.println("\n---------------------------------");
         System.out.println("Hope to see you soon Bruda.");
    }

    // Method to take input and return it
    public static void getUserInput() {
        // Creating a Scanner object for input
        Scanner scanner = new Scanner(System.in);

        boolean isFinished = false;

        while (!isFinished) {
            // Prompting the user for input
            System.out.print("Enter command: ");
            // Reading the input
            String input = scanner.nextLine();

            if (input.equals("Bye") || input.equals("bye")){
                // Closing the Scanner to avoid resource leaks
                scanner.close();
                isFinished = true;
            } else if (input.equals("List") || input.equals("list")){
                for (int i=0; i<tasks.size(); i++){
                    System.out.println(Integer.toString(i+1) + ". " + tasks.get(i));
                }
            } else if (input.startsWith("unmark") || input.startsWith("Unmark")){
                try {
                    StringValidator.validateUnmarkFormat(input);
                    mark(input, tasks, false);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    DataManager.saveData(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (input.startsWith("mark") || input.startsWith("Mark")){
                try {
                    StringValidator.validateMarkFormat(input);
                    mark(input, tasks, true);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    DataManager.saveData(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (input.startsWith("delete") || input.startsWith("Delete") ) {
                try {
                    StringValidator.validateDeleteFormat(input);
                    delete(input, tasks);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    DataManager.saveData(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (input.startsWith("todo") || input.startsWith("Todo")) {
                try {
                    StringValidator.validateTodoFormat(input);
                    Todo todo = new Todo(Todo.parse(input));
                    todo.addTo(tasks);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    DataManager.saveData(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (input.startsWith("deadline") || input.startsWith("Deadline")) {
                try {
                    StringValidator.validateDeadlineFormat(input);
                    Deadline deadline = new Deadline(Deadline.parseName(input), Deadline.parseDate(input));
                    deadline.addTo(tasks);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    DataManager.saveData(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (input.startsWith("event") || input.startsWith("Event")) {
                try {
                    StringValidator.validateEventFormat(input);
                    Event event = new Event(Event.parseName(input), Event.parseStart(input), Event.parseEnd(input));
                    event.addTo(tasks);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    DataManager.saveData(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Unknown Command: " + input);
            }
        }
    }

    public static void mark(String command, List<Task> list, Boolean b) {
        int taskNum = Integer.parseInt(command.substring(command.indexOf(" ")+1));
        if (taskNum <= list.size() && taskNum != 0){
            list.get(taskNum-1).setDone(b);
            if (b) {
                System.out.println("Nice! I've marked this task as done:\n" + list.get(taskNum-1));
            } else {
                System.out.println("OK, I've marked this task as not done yet:\n" + list.get(taskNum-1));
            }
        } else{
            System.out.println("Task number out of bounds: try again");
        }
    }

    public static void delete(String command, List<Task> list) {
        int number = Integer.parseInt(command.substring(command.indexOf(" ")+1));
        if (number <= list.size() && number != 0) {
            System.out.println("____________________________________________________________\n" +
                    "Noted. I've removed this task:\n" +
                    "  " + list.get(number - 1) + "\n" +
                    "Now you have " + (list.size() - 1) + " tasks in the list.\n" +
                    "____________________________________________________________");
            list.remove(number - 1);
        } else {
            System.out.println("Task number out of bounds: try again");
        }
    }
}
