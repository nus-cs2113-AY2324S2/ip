import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class John {
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
        List<Task> tasks = new ArrayList<>();

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
            }
            else if (input.equals("List") || input.equals("list")){
                for (int i=0; i<tasks.size(); i++){
                    System.out.println(Integer.toString(i+1) + ". " + tasks.get(i));
                }
            }
            //Mark Command
            else if (input.startsWith("unmark") || input.startsWith("Unmark")){
                try {
                    StringValidator.validateUnmarkFormat(input);
                    mark(input, tasks, false);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (input.startsWith("mark") || input.startsWith("Mark")){
                try {
                    StringValidator.validateMarkFormat(input);
                    mark(input, tasks, true);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            //commands.Todo Command
            else if (input.startsWith("todo") || input.startsWith("commands.Todo")) {
                try {
                    StringValidator.validateTodoFormat(input);
                    Todo todo = new Todo(Todo.parse(input));
                    todo.addTo(tasks);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            //commands.Deadline Command
            else if (input.startsWith("deadline") || input.startsWith("commands.Deadline")) {
                try {
                    StringValidator.validateDeadlineFormat(input);
                    Deadline deadline = new Deadline(Deadline.parseName(input), Deadline.parseDate(input));
                    deadline.addTo(tasks);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            //commands.Event Command
            else if (input.startsWith("event") || input.startsWith("commands.Event")) {
                try {
                    StringValidator.validateEventFormat(input);
                    Event event = new Event(Event.parseName(input), Event.parseStart(input), Event.parseEnd(input));
                    event.addTo(tasks);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            //Unknown Command
            else {
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
            }
            else {
                System.out.println("OK, I've marked this task as not done yet:\n" + list.get(taskNum-1));
            }
        }
        else{
            System.out.println("commands.Task number out of bounds: try again");
        }
    }
}
