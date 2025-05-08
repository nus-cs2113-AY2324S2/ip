public class Ui {
    static final String LINE_BREAK = "______________________";

    public Ui() {
        greetings();
    }

    /**
     * Prints out the line break.
     *
     */
    public static void lineBreak() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints out the greetings for when the user boots up the chatbot.
     *
     */
    public static void greetings() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Miku!\n" + "What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the text for when a new task is added.
     *
     */
    public static void printNewTask(int numberOfListItems) {
        TaskList.storedList.get(numberOfListItems).printTask(numberOfListItems);
    }

    /**
     * Prints out the parting text for when the user closes the chatbot.
     *
     */
    public static void goodbye() {  
        System.out.println("Bye, hope to see you again soon! :D");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints out the text for when a task is marked.
     *
     */
    public static void printMark(int listNumberIndex) {
        System.out.println("Good job~! I've marked it as done");
        System.out.println("[" + TaskList.storedList.get(listNumberIndex).getStatusIcon()
                + "] " + TaskList.storedList.get(listNumberIndex).description);
    }

    /**
     * Prints out the text for when a task is unmarked.
     *
     */
    public static void printUnmark(int listNumberIndex) {
        System.out.println("Aww... I've marked it as undone.");
        System.out.println("[" + TaskList.storedList.get(listNumberIndex).getStatusIcon()
                + "] " + TaskList.storedList.get(listNumberIndex).description);
    }

    /**
     * Prints out the text for when a task is deleted.
     *
     */
    public static void printDelete(int listNumberIndex) {
        System.out.println("Okay, I'm deleting this task:");
        System.out.println("[" + TaskList.storedList.get(listNumberIndex).getStatusIcon()
                + "] " + TaskList.storedList.get(listNumberIndex).description);
    }

    /**
     * Prints out the number of tasks in the list for the user.
     *
     */
    public static void printNumberOfListItems(int numberOfListItems){
        System.out.println("Now you have " + numberOfListItems + " items in your list!");
    }

    /**
     * Prints out the entire current list.
     *
     */
    public static void printList(int numberOfListItems) {
        if (TaskList.numberOfListItems > 0) {
            System.out.println("Here are your list items!");
            for (int i = 0; i < numberOfListItems; i++) {
                System.out.println((i + 1) + ". " + TaskList.storedList.get(i).toString());
            }
        } else if (TaskList.numberOfListItems == 0) {
            emptyList();
        } else {
            generalError();
        }
    }

    /**
     * Prints the message for the find function
     */
    public static void printFindTask() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints out the task and index number
     */
    public static void printWholeTask(int i) {
        System.out.println((i + 1) + ". " + TaskList.storedList.get(i).toString());
    }

    /**
     * Prints an error message of if a file is not found
     */
    public static void fileError() {
        System.out.println("file is not found!");
    }

    /**
     * Prints an error message if the user input is invalid
     */
    public static void invalidInput() {
        System.out.println("That's not a command, sorry (ㅠ﹏ㅠ)");
    }

    /**
     * Prints an error message if the todo command is blank
     */
    public static void blankTodo(){
        System.out.println("Todo can't be blank!");
    }

    /**
     * Prints an error message if the deadline command is blank
     */
    public static void blankDeadline(){
        System.out.println("Deadline is incomplete, did you forget to type something?");
    }

    /**
     * Prints an error message if the inputted deadline arguments are invalid
     */
    public static void invalidDeadlineArguments(){
        System.out.println("You didn't add when it's due by D:");
    }

    /**
     * Prints an error message if the event command is blank
     */
    public static void blankEvent(){
        System.out.println("Event is incomplete, did you forget to type something?");
    }

    /**
     * Prints an error message if the inputted event arguments are invalid
     */
    public static void invalidEventArguments(){
        System.out.println("You incorrectly inputted the arguments D:");
    }

    /**
     * Prints an error message if the mark command is blank
     */
    public static void blankMark(){
        System.out.println("You forgot to say what to mark.");
    }

    /**
     * Prints an error message if the unmark command is blank
     */
    public static void blankUnmark(){
        System.out.println("You forgot to say what to unmark.");
    }

    /**
     * Prints an error message if the delete command is blank
     */
    public static void blankDelete(){
        System.out.println("Check your delete arguments!");
    }

    /**
     * Prints a message if there are no tasks matching the given keyword
     */
    public static void noMatchingFind() {
        System.out.println("There are no tasks that match your keyword :(");
    }

    /**
     * Prints an error message if the deadline command is blank
     */
    public static void blankFind(){
        System.out.println("Please input the keyword of what you want to find.");
    }

    /**
     * Prints an error message if the inputted index is not a number
     */
    public static void notProperIndex(){
        System.out.println("Enter the number index of the item.");
    }

    /**
     * Prints an error message if the list is empty and there are no items
     */
    public static void emptyList(){
        System.out.println("There are no items in your list :(");
    }

    /**
     * Prints an error message if the inputted index does not exist in the current list
     */
    public static void outOfBoundsIndex(){
        System.out.println("Error! Try to input an index that exists in your current list instead.");
    }

    /**
     * Prints an error message if there is an error while loading the data from the saved text file
     */
    public static void loadError() {
        System.out.println("Error in loading data!");
    }

    /**
     * Prints an error message if there is an error in the loaded data's arguments
     */
    public static void loadArgumentsError() {
        System.out.println("there's something wrong with the loaded data's arguments");
    }


    /**
     * Prints a general error message
     */
    public static void generalError(){
        System.out.println("There's an error somewhere D:");
    }

}