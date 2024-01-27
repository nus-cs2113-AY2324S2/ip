package ToDoListFeature;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoList {
    private final ArrayList<ToDoItem> toDoListArray;
    private static final String MARK_FEATURE_PATTERN = "^mark \\d+$";
    private static final String UNMARK_FEATURE_PATTERN = "^unmark \\d+$";

    public ToDoList() {
        this.toDoListArray = new ArrayList<ToDoItem>();
    }

    private void markTask(int index) {
        if (index > toDoListArray.size()) {
            System.out.println("Task does not exist");
            return;
        }
        this.toDoListArray.get(index - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.toDoListArray.get(index -1).toString());
    }

    private void unMarkTask(int index) {
        if (index > toDoListArray.size()) {
            System.out.println("Task does not exist");
            return;
        }
        this.toDoListArray.get(index - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this.toDoListArray.get(index -1).toString());
    }

    public void execute(Scanner input) {
        featureIntroMessage();
        boolean isFinished = false;
        while (!isFinished) {
            String inputText = input.nextLine();
            if (matchesPattern(inputText, MARK_FEATURE_PATTERN)) {
                int numberPosition = inputText.indexOf(" ");
                markTask(Integer.parseInt(inputText.substring(numberPosition + 1)));
            } else if(matchesPattern(inputText, UNMARK_FEATURE_PATTERN)) {
                int numberPosition = inputText.indexOf(" ");
                unMarkTask(Integer.parseInt(inputText.substring(numberPosition + 1)));
            } else {
                switch (inputText) {
                case "list":
                    printToDoList();
                    break;

                case "^mark \\d+$":
                    System.out.println("nice");
                    break;

                case "help":
                    featureIntroMessage();
                    break;

                case "quit":
                    return;

                default:
                    addItem(new ToDoItem(inputText));
                    break;
                }
            }
        }
    }

    public void printToDoList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println(toString());
        messageDivider();
    }

    public void addItem(ToDoItem item) {
        this.toDoListArray.add(item);
        System.out.println("added: " + item.getName());
        messageDivider();
    }

    @Override
    public String toString() {
        if (this.toDoListArray.isEmpty()) {
            return "Empty ToDoList";
        }
        String output = "";
        int count = 1;
        for (ToDoItem item: this.toDoListArray) {
            output += count + "." + item.toString() + "\n";
            count ++;
        }
        return output.substring(0, output.length()-1);
    }

    public static void featureIntroMessage() {
        System.out.println("[list] Show your Todolist tasks");
        System.out.println("[mark X] Mark number X task as Done");
        System.out.println("[unmark X] Mark number X task as UnDone");
        System.out.println("[quit] Return to main menu");
        System.out.println("Any other words will be added as Todo tasks");
        System.out.println("[help] Show commands");
        messageDivider();
        System.out.println("Please enter a command:");
    }

    public static void messageDivider() {
        System.out.println("------------------------------------------");
    }

    private static boolean matchesPattern(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        return matcher.matches();
    }
}
