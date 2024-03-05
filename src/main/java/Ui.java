import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Ui {
    static final String LINE_BREAK = "______________________";


    public Ui() {
        greetings();
    }

    public static void lineBreak() {
        System.out.println(LINE_BREAK);
    }

    public static void greetings() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Miku!\n" + "What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    public static void printNewTask(int numberOfListItems) {
        TaskList.storedList.get(numberOfListItems).printTask(numberOfListItems);
    }

    public static void fileError(){
        System.out.println("file is not found!");
    }


    public static void goodbye() {
        System.out.println("Bye, hope to see you again soon! :D");
        System.out.println(LINE_BREAK);
    }

    public static void printMark(int listNumberIndex) {
        System.out.println("Good job~! I've marked it as done");
        System.out.println("[" + TaskList.storedList.get(listNumberIndex).getStatusIcon()
                + "] " + TaskList.storedList.get(listNumberIndex).description);
    }

    public static void printUnmark(int listNumberIndex) {
        System.out.println("Aww... I've marked it as undone.");
        System.out.println("[" + TaskList.storedList.get(listNumberIndex).getStatusIcon()
                + "] " + TaskList.storedList.get(listNumberIndex).description);
    }

    public static void printDelete(int listNumberIndex) {
        System.out.println("Okay, I'm deleting this task:");
        System.out.println("[" + TaskList.storedList.get(listNumberIndex).getStatusIcon()
                + "] " + TaskList.storedList.get(listNumberIndex).description);
    }

    public static void printNumberOfListItems(int numberOfListItems){
        System.out.println("Now you have " + numberOfListItems + " items in your list!");
    }

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

    public static void printFindTask() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public static void printWholeTask(int i) {
        System.out.println((i + 1) + ". " + TaskList.storedList.get(i).toString());
    }

    public static void invalidInput() {
        System.out.println("That's not a command, sorry (ㅠ﹏ㅠ)");
    }

    public static void blankTodo(){
        System.out.println("Todo can't be blank!");
    }

    public static void blankDeadline(){
        System.out.println("Deadline is incomplete, did you forget to type something?");
    }

    public static void invalidDeadlineArguments(){
        System.out.println("You didn't add when it's due by D:");
    }

    public static void blankEvent(){
        System.out.println("Event is incomplete, did you forget to type something?");
    }

    public static void invalidEventArguments(){
        System.out.println("You incorrectly inputted the arguments D:");
    }

    public static void blankMark(){
        System.out.println("You forgot to say what to mark.");
    }

    public static void blankUnark(){
        System.out.println("You forgot to say what to unmark.");
    }

    public static void blankDelete(){
        System.out.println("Check your delete arguments!");
    }

    public static void notProperIndex(){
        System.out.println("Enter the number index of the item.");
    }

    public static void emptyList(){
        System.out.println("There are no items in your list :(");
    }

    public static void outOfBoundsIndex(){
        System.out.println("Error! Try to input an index that exists in your current list instead.");
    }

    public static void loadError() {
        System.out.println("Error in loading data!");
    }

    public static void loadArgumentsError() {
        System.out.println("there's something wrong with the loaded data's arguments");
    }

    public static void blankFind() {
        System.out.println("There are no tasks that match your keyword :(");
    }
    public static void generalError(){
        System.out.println("There's an error somewhere D:");
    }

}