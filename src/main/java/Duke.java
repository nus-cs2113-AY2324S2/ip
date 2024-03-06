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
import task.TaskList;

public class Duke {


    /*
    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < taskCount; i++){
            fw.write(tasks.get(i).toString() + "\n");
        }
        fw.close();
    }
    */

    /*
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
    }*/

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

        // String filePath = "tasks.txt";

        String userInput;
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();

        /*
        try {
            readFromFile(filePath, tasks);
        }
        catch (FileNotFoundException error){
            System.out.println("The file cannot be found.");
        }
        */

        userInput = in.nextLine();

        while(!userInput.equals("bye")){
            String[] userInputWords = userInput.split(" ");

            if(userInput.equals("list")){
                tasks.list();
            }
            else if(userInputWords[0].equals("mark")){
                tasks.mark(userInput);
            }
            else if(userInputWords[0].equals("unmark")){
                tasks.unmark(userInput);
            }
            else if(userInputWords[0].equals("todo")){
                tasks.addTodo(userInput);
            }
            else if(userInputWords[0].equals("deadline")){
                tasks.addDeadline(userInput);
            }
            else if(userInputWords[0].equals("event")){
                tasks.addEvent(userInput);
            }
            else if(userInputWords[0].equals("delete")){
                tasks.delete(userInput);
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
