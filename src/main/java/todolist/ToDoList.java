package todolist;
import todolist.keywordfinder.Keyword;
import todolist.keywordfinder.KeywordPatternMatcher;
import todolist.task.DeadLinesTask;
import todolist.task.EventsTask;
import todolist.task.Task;
import todolist.task.ToDoTask;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class ToDoList {
    private final ArrayList<Task> toDoListArray;
    //Keywords

    /**
     * Constructor for ToDoList
     */
    public ToDoList() {
        this.toDoListArray = new ArrayList<>();
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
    public void execute(Scanner input) {
        featureIntroMessage();
        while (true) {
            String inputText = input.nextLine();
            KeywordPatternMatcher keywordFinder = new KeywordPatternMatcher(inputText);
            if (keywordFinder.getKeywordType() == Keyword.none) {
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
            messageDivider();
            try {
                writeToFile();
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

    /**
     * Print the feature intro message
     */
    public static void featureIntroMessage() {
        System.out.println("[list] Show your Todolist tasks");
        System.out.println("[mark X] Mark number X task as Done");
        System.out.println("[unmark X] Mark number X task as UnDone");
        System.out.println("[delete X] Delete number X task");
        System.out.println("[todo XXX] Add a todo task");
        System.out.println("[deadline XXX /by XXX] Add a deadline task");
        System.out.println("[event XXX /from XXX /to XXX] Add a Event task");
        System.out.println("[help] Show commands");
        System.out.println("[quit] Return to main menu");
        System.out.println("Where XXX Stands for any string (except space character)");
        messageDivider();
        System.out.println("Please enter a command:");
    }

    /**
     * Print the message divider
     */
    public static void messageDivider() {
        System.out.println("------------------------------------------");
    }

    private void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("ChatBBTData.txt");
        fw.write(storeDataString());
        fw.close();
    }

    private String storeDataString() {
        StringBuilder dataString = new StringBuilder();
        for (Task task : this.toDoListArray) {
            dataString.append(task.storeDataString()).append("\n");
        }
        dataString.append("#end");
        return dataString.toString();
    }

    public void loadData() {
        String filePath = "ChatBBTData.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (!Objects.equals(line = reader.readLine(), "#end")) {
                char isDone = line.charAt(2);
                switch(line.charAt(0)){
                case 'E' :
                    //get name
                    int splitIndexEvent1 = line.indexOf('|',4);
                    //get start and end time
                    int splitIndexEvent2 = line.indexOf('|',splitIndexEvent1 + 1);
                    this.toDoListArray.add(new EventsTask(line.substring(4,splitIndexEvent1 - 1),
                            isDone != '0',
                            line.substring(splitIndexEvent1 + 1, splitIndexEvent2 -1),
                            line.substring(splitIndexEvent2 + 1)));
                    break;
                case 'T' :
                    this.toDoListArray.add(new ToDoTask(line.substring(4), isDone != '0'));
                    break;
                case 'D' :
                    int splitIndexDeadline = line.indexOf('|',4);
                    this.toDoListArray.add(new DeadLinesTask(line.substring(4,splitIndexDeadline - 1),
                            isDone != '0',
                            line.substring(splitIndexDeadline + 1)));
                    break;
                default:
                    throw new IOException();
                }
            }
        } catch (IOException | StringIndexOutOfBoundsException e) {
            System.err.println("Data file damaged, please continue");
        }
    }

}
