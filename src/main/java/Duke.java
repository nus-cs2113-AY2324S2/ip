import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import exception.EkudException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Duke {

    private static int taskCount = 0;

    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < taskCount; i++){
            fw.write(tasks.get(i).toString() + "\n");
        }
        fw.close();
    }

    private static void readFromFile(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            String currentLine = s.nextLine();
            if(currentLine.contains("[T]")){
                int descriptionStart = currentLine.indexOf("[T]") + 7;
                tasks.add(new Todo(currentLine.substring(descriptionStart)));
                taskCount++;
            }
            else if (currentLine.contains("[D]")){
                int descriptionStart = currentLine.indexOf("[D]") + 7;
                int descriptionEnd = currentLine.indexOf("(by:") - 1;
                int byStart = currentLine.indexOf("(by:") + 5;
                tasks.add(new Deadline(currentLine.substring(descriptionStart, descriptionEnd), currentLine.substring(byStart)));
                taskCount++;
            }
            else if (currentLine.contains("[E]")){
                int descriptionStart = currentLine.indexOf("[E]") + 7;
                int descriptionEnd = currentLine.indexOf("(from:") - 1;
                int fromStart = currentLine.indexOf("(from:") + 7;
                int fromEnd = currentLine.indexOf("to:") - 1;
                int toStart = currentLine.indexOf("to:") + 4;
                tasks.add(new Event(currentLine.substring(descriptionStart, descriptionEnd), currentLine.substring(fromStart, fromEnd), currentLine.substring(toStart)));
                taskCount++;
            }
        }
    }

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        System.out.println("I'm Ekud! What can I do for you?");

        String filePath = "tasks.txt";

        String userInput;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        // task.Task[] tasks = new task.Task[100];
        // int taskCount = 0;

        try {
            readFromFile(filePath, tasks);
        }
        catch (FileNotFoundException error){
            System.out.println("The file cannot be found.");
        }

        userInput = in.nextLine();

        while(!userInput.equals("bye")){
            String[] userInputWords = userInput.split(" ");

            if(userInput.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < taskCount; i++){
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            }
            else if(userInputWords[0].equals("mark")){
                try {
                    if (userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                        throw new EkudException();
                    }
                    else {
                        int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                        tasks.get(taskIndex).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(taskIndex));
                    }
                }
                catch (EkudException error) {
                    System.out.println("The task number is not valid or not provided.");
                }
            }
            else if(userInputWords[0].equals("unmark")){
                try {
                    if (userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                        throw new EkudException();
                    }
                    else {
                        int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                        tasks.get(taskIndex).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks.get(taskIndex));
                    }
                }
                catch (EkudException error) {
                    System.out.println("The task number is not valid or not provided.");
                }
            }
            else if(userInputWords[0].equals("todo")){
                int dividerPosition = userInput.indexOf(" ");
                try {
                    if (dividerPosition == -1) {
                        throw new EkudException();
                    }
                    else {
                        int descriptionStart = dividerPosition + 1;
                        tasks.add(new Todo(userInput.substring(descriptionStart)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(taskCount));
                        taskCount++;
                        System.out.println("Now you have " + taskCount + " tasks in the list.");

                        writeToFile(filePath, tasks);
                    }
                }
                catch (EkudException error) {
                    System.out.println("The description of a todo cannot be empty.");
                }
                catch (IOException error) {
                    System.out.println("Something went wrong: " + error.getMessage());
                }
            }
            else if(userInputWords[0].equals("deadline")){
                int dividerPosition = userInput.indexOf(" ");
                try {
                    if (dividerPosition == -1) {
                        throw new EkudException();
                    }
                    else {
                        int slashPosition = userInput.indexOf("/by");
                        int descriptionStart = dividerPosition + 1;
                        int descriptionEnd = slashPosition - 1;
                        int byStart = slashPosition + 4;
                        tasks.add(new Deadline(userInput.substring(descriptionStart, descriptionEnd), userInput.substring(byStart)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(taskCount));
                        taskCount++;
                        System.out.println("Now you have " + taskCount + " tasks in the list.");

                        writeToFile(filePath, tasks);
                    }
                }
                catch (EkudException error) {
                    System.out.println("The description of a deadline cannot be empty.");
                }
                catch (IOException error) {
                    System.out.println("Something went wrong: " + error.getMessage());
                }
            }
            else if(userInputWords[0].equals("event")){
                int dividerPosition = userInput.indexOf(" ");
                try {
                    if (dividerPosition == -1) {
                        throw new EkudException();
                    }
                    else {
                        int descriptionStart = dividerPosition + 1;
                        int descriptionEnd = userInput.indexOf("/from") - 1;
                        int fromStart = userInput.indexOf("/from") + 6;
                        int fromEnd = userInput.indexOf("/to") - 1;
                        int toStart = userInput.indexOf("/to") + 4;

                        tasks.add(new Event(userInput.substring(descriptionStart, descriptionEnd), userInput.substring(fromStart, fromEnd), userInput.substring(toStart)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(taskCount));
                        taskCount++;
                        System.out.println("Now you have " + taskCount + " tasks in the list.");

                        writeToFile(filePath, tasks);
                    }
                }
                catch (EkudException error) {
                    System.out.println("The description of an event cannot be empty.");
                }
                catch (IOException error) {
                    System.out.println("Something went wrong: " + error.getMessage());
                }
            }
            else if(userInputWords[0].equals("delete")){
                try {
                    if(userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                        throw new EkudException();
                    }
                    else{
                        int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(tasks.get(taskIndex));
                        tasks.remove(taskIndex);
                        taskCount--;
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                    }
                }
                catch(EkudException error){
                    System.out.println("The task number is not valid or not provided.");
                }
            }
            else{
                try {
                    throw new EkudException();
                }
                catch (EkudException error){
                    System.out.println("OOPS! That is not a valid input.");
                }
            }
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon.");
    }
}
