package ToDoListFeature;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoList {
    private final ArrayList<ToDo> toDoListArray;
    //Keywords
    private static final String MARK_FEATURE_PATTERN = "^mark \\d+$"; //mark
    private static final String UNMARK_FEATURE_PATTERN = "^unmark \\d+$"; //unmark
    private static final String TODOTASK_FEATURE_PATTERN = "^todo .*"; //todoItem
    private static final String DEADLINESTASK_FEATURE_PATTERN = "^deadline .*"; //todoItem
    private static final String EVENTSTASK_PATTERN = "^event .*"; //todoItem
    private static final int SPACE_OFFSET = 1; //keyword " " offset
    private static final int BY_OFFSET = 4; //keyword "/by" offset
    private static final int FROM_OFFSET = 6; //keyword "/from" offset
    private static final int TO_OFFSET = 4; //keyword "/to" offset

    /**
     * Constructor for ToDoList
     */
    public ToDoList() {
        this.toDoListArray = new ArrayList<ToDo>();
    }

    /**
     * Mark a task in the ToDoList
     * @param index the index of the task to be marked
     */
    private void markTask(int index) {
        if (index > toDoListArray.size()) {
            System.out.println("Task does not exist");
            return;
        }
        this.toDoListArray.get(index - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.toDoListArray.get(index -1).toString());
    }

    /**
     * Unmark a task in the ToDoList
     * @param index the index of the task to be unmarked
     */
    private void unMarkTask(int index) {
        if (index > toDoListArray.size()) {
            System.out.println("Task does not exist");
            return;
        }
        this.toDoListArray.get(index - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this.toDoListArray.get(index -1).toString());
    }

    /**
     * Execute the ToDoList feature
     * @param input the scanner to read user input
     */
    public void execute(Scanner input) {
        featureIntroMessage();
        boolean isFinished = false;
        while (!isFinished) {
            String inputText = input.nextLine();
            try {
                if (!processKeywordInput(inputText)) {
                    //add a specific todoTask based on the keyword input
                    switch (inputText) {
                    case "list":
                        printToDoList();
                        break;
                
                    case "help":
                        featureIntroMessage();
                        break;
                
                    case "quit":
                        return;
                
                    default:
                        System.out.println("invalid command, use [help] to show all the commands");
                        break;
                    }
                }
                messageDivider();
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("invalid command, use [help] to show all the commands");
                messageDivider();
            }
        }
    }

    /**
     * Print the ToDoList
     */
    public void printToDoList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println(toString());
    }

    /**
     * Add a ToDo item to the ToDoList
     * @param item the ToDo item to be added
     */
    public void addItem(ToDo item) {
        this.toDoListArray.add(item);
        System.out.println("added: " + item.getName());
        System.out.println("Now you have " + this.toDoListArray.size() + " task(s) in the list");
    }

    /**
     * Get the string representation of the ToDoList
     * @return the string representation of the ToDoList
     */
    @Override
    public String toString() {
        if (this.toDoListArray.isEmpty()) {
            return "Empty ToDoList";
        }
        String output = "";
        int count = 1;
        for (ToDo item: this.toDoListArray) {
            output += count + "." + item.toString() + "\n";
            count ++;
        }
        return output.substring(0, output.length()-1);
    }

    /**
     * Print the feature intro message
     */
    public static void featureIntroMessage() {
        System.out.println("[list] Show your Todolist tasks");
        System.out.println("[mark X] Mark number X task as Done");
        System.out.println("[unmark X] Mark number X task as UnDone");
        System.out.println("[todo XXX] Add a todo task");
        System.out.println("[deadline XXX /by XXX] Add a deadline task");
        System.out.println("[event XXX /from XXX /to XXX] Add a Event task");
        System.out.println("[help] Show commands");
        System.out.println("[quit] Return to main menu");
        messageDivider();
        System.out.println("Please enter a command:");
    }

    /**
     * Print the message divider
     */
    public static void messageDivider() {
        System.out.println("------------------------------------------");
    }
    
    /**
     * Get the position of the first space in the input text
     * @param inputText the input text
     * @return the position of the first space in the input text
     */
    private int getNumberPosition(String inputText) {
        return inputText.indexOf(" ");
    }
    
    /**
     * Process the keyword input and add the corresponding task to the list
     * @param inputText the input text
     * @return true if the input is a keyword, false otherwise
     */
    private boolean processKeywordInput(String inputText) {
        int numberPosition = getNumberPosition(inputText);
        if (matchesPattern(inputText, MARK_FEATURE_PATTERN)) {
            markTask(Integer.parseInt(inputText.substring(numberPosition + SPACE_OFFSET)));
            return true;
        } else if (matchesPattern(inputText, UNMARK_FEATURE_PATTERN)) {
            unMarkTask(Integer.parseInt(inputText.substring(numberPosition + SPACE_OFFSET)));
            return true;
        } else if (matchesPattern(inputText, TODOTASK_FEATURE_PATTERN)) {
            addItem(new ToDoTask(inputText.substring(numberPosition + SPACE_OFFSET)));
            return true;
        } else if (matchesPattern(inputText, DEADLINESTASK_FEATURE_PATTERN)) {
            int keywordPosition = inputText.indexOf("/by");
            addItem(new DeadLinesTask(inputText.substring(numberPosition + SPACE_OFFSET, keywordPosition - 1),
                    inputText.substring(keywordPosition + BY_OFFSET)));
            return true;
        } else if (matchesPattern(inputText, EVENTSTASK_PATTERN)) {
            int keywordPosition1 = inputText.indexOf("/from"); // theck /from keyword position
            int keywordPosition2 = inputText.indexOf("/to"); // check /to keyword position
            addItem(new EventsTask(inputText.substring(numberPosition + SPACE_OFFSET, keywordPosition1 - 1),
                    inputText.substring(keywordPosition1 + FROM_OFFSET, keywordPosition2 - 1),
                    inputText.substring(keywordPosition2 + TO_OFFSET)));
            return true;
        }
        return false;
    }

    /**
     * Check if the input matches the pattern
     * @param input the input text
     * @param pattern the pattern to be matched
     * @return true if the input matches the pattern, false otherwise
     */
    private static boolean matchesPattern(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        return matcher.matches();
    }
}
