import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import exception.EkudException;

public class Duke {

    private static void writeToFile(String filePath, ArrayList<Task> tasks, int taskCount) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < taskCount; i++){
            fw.write(tasks.get(i).toString() + "\n");
        }
        fw.close();
    }

    private static void readFromFile(String filePath, Task[] tasks, int taskCount) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            String currentLine = s.nextLine();
            if(currentLine.contains("[T]")){
                int descriptionPosition = currentLine.indexOf("[T]") + 7;
                tasks[taskCount] = new Todo(currentLine.substring(descriptionPosition));
                taskCount++;
            }
            else if (currentLine.contains("[D]")){
                int descriptionPosition = currentLine.indexOf("[D]") + 7;
                int byPosition = currentLine.indexOf("(by:");
                tasks[taskCount] = new Deadline(currentLine.substring(descriptionPosition, byPosition - 1), currentLine.substring(byPosition + 5));
                taskCount++;
            }
            else if (currentLine.contains("[E]")){
                int descriptionPosition = currentLine.indexOf("[E]") + 7;
                int fromPosition = currentLine.indexOf("(from:");
                int toPosition = currentLine.indexOf("to:");
                tasks[taskCount] = new Event(currentLine.substring(descriptionPosition, fromPosition - 1), currentLine.substring(fromPosition + 7, toPosition - 1), currentLine.substring(toPosition + 4));
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
        // Task[] tasks = new Task[100];
        int taskCount = 0;

        try {
            // readFromFile(filePath, tasks, taskCount);
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String currentLine = s.nextLine();
                if(currentLine.contains("[T]")){
                    int descriptionPosition = currentLine.indexOf("[T]") + 7;
                    tasks.add(new Todo(currentLine.substring(descriptionPosition)));
                    taskCount++;
                }
                else if (currentLine.contains("[D]")){
                    int descriptionPosition = currentLine.indexOf("[D]") + 7;
                    int byPosition = currentLine.indexOf("(by:");
                    tasks.add(new Deadline(currentLine.substring(descriptionPosition, byPosition - 1), currentLine.substring(byPosition + 5)));
                    taskCount++;
                }
                else if (currentLine.contains("[E]")){
                    int descriptionPosition = currentLine.indexOf("[E]") + 7;
                    int fromPosition = currentLine.indexOf("(from:");
                    int toPosition = currentLine.indexOf("to:");
                    tasks.add(new Event(currentLine.substring(descriptionPosition, fromPosition - 1), currentLine.substring(fromPosition + 7, toPosition - 1), currentLine.substring(toPosition + 4)));
                    taskCount++;
                }
            }
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
                        tasks.add(new Todo(userInput.substring(dividerPosition + 1)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(taskCount));
                        taskCount++;
                        System.out.println("Now you have " + taskCount + " tasks in the list.");

                        writeToFile(filePath, tasks, taskCount);
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
                        tasks.add(new Deadline(userInput.substring(dividerPosition + 1, slashPosition - 1), userInput.substring(slashPosition + 4)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(taskCount));
                        taskCount++;
                        System.out.println("Now you have " + taskCount + " tasks in the list.");

                        writeToFile(filePath, tasks, taskCount);
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
                        int fromPosition = userInput.indexOf("/from");
                        int toPosition = userInput.indexOf("/to");

                        tasks.add(new Event(userInput.substring(dividerPosition + 1, fromPosition - 1), userInput.substring(fromPosition + 6, toPosition - 1), userInput.substring(toPosition + 4)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(taskCount));
                        taskCount++;
                        System.out.println("Now you have " + taskCount + " tasks in the list.");

                        writeToFile(filePath, tasks, taskCount);
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
