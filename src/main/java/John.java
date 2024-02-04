import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class John {
    public static void main(String[] args) {
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
        Task[] tasks = new Task[100];
        int taskCount = 0;
        boolean isFinished = false;

        while (!isFinished) {
            // Prompting the user for input
            System.out.print("Enter new Task: ");
            // Reading the input
            String input = scanner.nextLine();

            if (input.equals("Bye") || input.equals("bye")){
                // Closing the Scanner to avoid resource leaks
                scanner.close();
                break;
            }
            else if (input.equals("List") || input.equals("list")){
                for (int i=0; i<taskCount; i++){
                    System.out.println(Integer.toString(i+1) + ". " + tasks[i]);
                }
            }
            else if (input.contains("unmark ") || input.contains("Unmark ")){
                mark(input, tasks, taskCount, false);
            }
            else if (input.contains("mark ") || input.contains("Mark ")){
                mark(input, tasks, taskCount, true);
            }
            else {
                // Displaying the input
                tasks[taskCount] = new Task(input);
                taskCount ++;
                System.out.println("added: " + input);
            }
        }
    }

    public static void mark(String str, Task[] list, int listLength, Boolean b) {
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
        Matcher matcher = regex.matcher(str);
        if (matcher.matches()) {
            int taskNum = Integer.parseInt(str.substring(str.indexOf(" ")+1));
            if (taskNum <= listLength){
                list[taskNum -1].setDone(b);
                if (b) {
                    System.out.println("Nice! I've marked this task as done:\n" + list[taskNum -1]);
                }
                else {
                    System.out.println("OK, I've marked this task as not done yet:\n" + list[taskNum-1]);
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
