import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.List;

public class John {
    public static void main(String[] args) {
        System.out.println("ヽ(•‿•)ノ");
         System.out.println("-----------------------------------");
         System.out.println("Heya, Im ya Chatbot, call me John!\nWhat Can I do for you today?");
         // Taking input from the user
         getUserInput();
         System.out.println("\n-----------------------------------");
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
            else if (input.contains("unmark ") || input.contains("Unmark ")){
                mark(input, tasks, false);
            }
            else if (input.contains("mark ") || input.contains("Mark ")){
                mark(input, tasks, true);
            }
            //Todo Command
            else if (input.contains("todo ") || input.contains("Todo ")) {
                Todo todo = new Todo(Todo.parse(input));
                todo.addTo(tasks);
            }
            //Deadline Command
            else if (input.contains("deadline ") || input.contains("Deadline ")) {
                // Define the regex pattern
                String regex = "(?i)deadline (.*?) /by\\s+(.*?)";
                // Compile the pattern
                Pattern pattern = Pattern.compile(regex);
                // Create a Matcher object
                Matcher matcher = pattern.matcher(input);

                if (matcher.matches()) {
                    Deadline deadline = new Deadline(Deadline.parseName(input), Deadline.parseDate(input));
                    deadline.addTo(tasks);
                }
                else {
                    System.out.println("Please enter deadline in format: deadline [description] /by [date]");
                }
            }
            //Event Command
            else if (input.contains("event ") || input.contains("Event ")) {
                // Define the regex pattern
                String regex = "(?i)event (.*?) /from\\s+(.*?)\\s/to\\s+(.*?)";
                // Compile the pattern
                Pattern pattern = Pattern.compile(regex);
                // Create a Matcher object
                Matcher matcher = pattern.matcher(input);

                if (matcher.matches()) {
                    Event event = new Event(Event.parseName(input), Event.parseStart(input), Event.parseEnd(input));
                    event.addTo(tasks);
                }
                else {
                    System.out.println("Please enter event in format: event [description] /from [time] /to [time]");
                }
            }
            //Unknown Command
            else {
                System.out.println("Unknown Command: " + input);
            }
        }
    }

    public static void mark(String command, List<Task> list, Boolean b) {
        String pattern = "";
        if (b){
            pattern = "mark \\d+";
        }
        else {
            pattern = "unmark \\d+";
        }
        // Create a Pattern object
        Pattern regex = Pattern.compile(pattern);
        // Create a Matcher object for input1
        Matcher matcher = regex.matcher(command);

        if (matcher.matches()) {
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
                System.out.println("Task number out of bounds: try again");
            }
        }
        else {
            if (b){
                System.out.println("Please enter mark command in following format: mark #");
            }
            else {
                System.out.println("Please enter mark command in following format: unmark #");
            }

        }
    }
}
