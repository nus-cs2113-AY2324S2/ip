import java.util.Scanner;
import java.util.ArrayList;
import exception.EkudException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Ekud! What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        // Task[] tasks = new Task[100];
        int taskCount = 0;
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
                    }
                }
                catch (EkudException error) {
                    System.out.println("The description of a todo cannot be empty.");
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
                    }
                }
                catch (EkudException error) {
                    System.out.println("The description of a deadline cannot be empty.");
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
                    }
                }
                catch (EkudException error) {
                    System.out.println("The description of an event cannot be empty.");
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
