import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Sinep {
    static String greeting = "Hello! I'm Sinep, your personal chatbot!\n"
            + "What can I do for you today?";
    static String bye = "Bye. Hope to see you again soon!";
    static String line = "_____________________________________________________________________";
    static String nl = System.lineSeparator();
    static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(line + nl + greeting + nl+ line);

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {  // Check if the input is "bye"
                printBye();
                break;
            } else if (input.equals("list")) { // Check if the input is "list"
                printList();
            } else if (input.startsWith("mark ")) { // Check if the input is "mark"
                markList(input);
            } else if (input.startsWith("unmark ")) { // Check if the input is "unmark"
                unmarkList(input);
            } else if (input.startsWith("todo ")) {
                addTodo(input);
            } else if (input.startsWith("deadline ")) {
                addDeadline(input);
            } else if (input.startsWith("event")) {
                addEvent(input);
            } else {
                addToList(input);
            }
        }
        scanner.close();
    }

    public static void printBye() {
        System.out.println(line + nl + bye + nl + line);

    }

    public static void printList() {
        System.out.println(line);
        System.out.println("Here are the current tasks in your list:");
        if (taskList.isEmpty()) {
            System.out.println("Great job! You have no tasks!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
        System.out.println(line);
    }

    public static void markList(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        Task markingTask = taskList.get(taskIndex);
        markingTask.markAsDone();
        System.out.println(line + nl + "Got it! Task " + (taskIndex + 1) + " marked as done:");
        System.out.println(markingTask.getStatusIcon() + " " + markingTask.description + nl + line);
    }

    public static void unmarkList(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        Task markingTask = taskList.get(taskIndex);
        markingTask.unmarkAsDone();
        System.out.println(line + nl + "Got it! Task " + (taskIndex + 1) + " marked as undone:");
        System.out.println(markingTask.getStatusIcon() + " " + markingTask.description + nl + line);
    }

    public static void addToList(String input) {
        Task newTask = new Task(input); // Add new task into the list
        taskList.add(newTask);
        System.out.println(line + nl + "Added to task list: " + input + nl + line);
    }

    public static void addTodo(String input) {
        String actualDescription = input.replace("todo ", "");
        Todo newTodo = new Todo(actualDescription);
        taskList.add(newTodo);
        System.out.println(line + nl + "Added to task list: " + nl + newTodo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + nl + line);
    }

    public static void addDeadline(String input) {
        String actualDescription = input.replace("deadline ", "");
        Deadline newDeadline = new Deadline(actualDescription);
        taskList.add(newDeadline);
        String descriptionToPrint = newDeadline.toString();
        System.out.println(line + nl + "Added to task list: " + nl + descriptionToPrint);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + nl + line);
    }

    public static void addEvent(String input) {
        String actualDescription = input.replace("event ", "");
        Event newEvent = new Event(actualDescription);
        taskList.add(newEvent);
        String descriptionToPrint = newEvent.toString();
        System.out.println(line + nl + "Added to task list: " + nl + descriptionToPrint);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + nl + line);
    }

}
