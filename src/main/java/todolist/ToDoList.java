package todolist;
import todolist.keywordfinder.Keyword;
import todolist.keywordfinder.KeywordPatternMatcher;
import todolist.task.Task;
import todolist.ui.ErrorMessages;
import todolist.ui.FeatureMessages;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<Task> toDoListArray;
    //Keywords

    /**
     * Constructor for ToDoList
     */
    public ToDoList() {
        this.toDoListArray = new ArrayList<>();
    }

    public ArrayList<Task> getToDoListArray() {
        return toDoListArray;
    }

    /**
     * Mark a task in the ToDoList
     * @param index the index of the task to be marked
     */
    private void markTask(int index) {
        try {
            this.toDoListArray.get(index - 1).mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + this.toDoListArray.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            ErrorMessages.taskDoesNotExistErrorMessage();
        }
    }

    /**
     * Unmark a task in the ToDoList
     * @param index the index of the task to be unmarked
     */
    private void unMarkTask(int index) {
        try {
            this.toDoListArray.get(index - 1).unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + this.toDoListArray.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            ErrorMessages.taskDoesNotExistErrorMessage();
        }

    }

    /**
     * Execute the ToDoList feature
     * @param input the scanner to read user input
     */
    public void execute(Scanner input, DataManager dataManager) {
        FeatureMessages.featureIntroMessage();
        while (true) {
            String inputText = input.nextLine();
            KeywordPatternMatcher keywordFinder = new KeywordPatternMatcher(inputText);
            if (keywordFinder.getKeywordType() == Keyword.none) {
                switch (inputText) {
                case "list":
                    printToDoList();
                    break;

                case "help":
                    FeatureMessages.featureIntroMessage();
                    break;

                case "quit":
                    return;

                default:
                    ErrorMessages.invalidCommandErrorMessage();
                    break;
                }
            } else {
                switch (keywordFinder.getKeywordType()) {
                case delete:
                    deleteTask(keywordFinder.findNumberIndex());
                    break;
                case mark:
                    markTask(keywordFinder.findNumberIndex());
                    break;
                case unmark:
                    unMarkTask(keywordFinder.findNumberIndex());
                    break;
                case todoError:
                    ErrorMessages.todoTaskFormatIncorrectErrorMessage();
                    break;
                case find:
                    findTask(keywordFinder.findSearchInput());
                    break;
                default:
                    // the method will return null if the input is incorrect
                    if (keywordFinder.processKeywordInput() == null) { 
                        ErrorMessages.taskFormatIncorrectErrorMessage();
                    } else {
                        addTask(keywordFinder.processKeywordInput());
                    }
                    break;
                }
            }
            FeatureMessages.messageDivider();
            try {
                dataManager.writeToFile(this);
            } catch (IOException e) {
                System.out.println("File does not exist, please do not delete any files");
            }
        }
    }

    /**
     * Print the ToDoList
     */
    public void printToDoList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println(this);
    }

    private void findTask(String searchText) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Task item : this.toDoListArray) {
            if (item.getName().contains(searchText)) {
                System.out.println(count + "." + item);
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No matching tasks found");
        }
    }

    /**
     * Add a Task to the ToDoList
     * @param task the Task to be added
     */
    public void addTask(Task task) {
        this.toDoListArray.add(task);
        System.out.println("added: " + task.getName());
        System.out.println("Now you have " + this.toDoListArray.size() + " task(s) in the list");
    }

    public void deleteTask(int index) {
        try {
            String taskNameString = index + ". " + this.toDoListArray.get(index - 1).toString();
            this.toDoListArray.remove(index - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(taskNameString);
            System.out.println("Now you have " + this.toDoListArray.size() + " task(s) in the list");
        } catch (IndexOutOfBoundsException e) {
            ErrorMessages.taskDoesNotExistErrorMessage();
        }
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
        for (Task item : this.toDoListArray) {
            output += count + "." + item.toString() + "\n";
            count++;
        }
        return output.substring(0, output.length()-1);
    }
}
