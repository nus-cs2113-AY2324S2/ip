import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import exception.EkudException;
import task.TaskList;
import storage.Storage;

public class Duke {

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

        String userInput;
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();

        Storage storage = new Storage();

        try {
            tasks = storage.readFromFile();
        }
        catch (FileNotFoundException error){
            System.out.println("The file cannot be found.");
        }

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

        try {
            storage.writeToFile(tasks);
        }
        catch(IOException error) {
            System.out.println("Oops. Something went wrong when saving tasks.");
        }

        System.out.println("Bye. Hope to see you again soon.");
    }
}
