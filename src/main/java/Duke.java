import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import exception.EkudException;
import task.TaskList;
import storage.Storage;
import ui.UI;
import ui.Parser;

public class Duke {

    public static void main(String[] args) {

        System.out.println(UI.GREETING_MESSAGE);

        String userInput;
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();

        Storage storage = new Storage();

        try {
            tasks = storage.readFromFile();
        }
        catch (FileNotFoundException error){
            System.out.println(UI.FILE_NOT_FOUND_MESSAGE);
        }

        userInput = in.nextLine();

        while(!userInput.equals("bye")){
            String[] userInputWords = userInput.split(" ");
            String userCommand = userInputWords[0].toLowerCase();

            if(userCommand.equals("list")){
                tasks.list();
            }
            else if(userCommand.equals("mark")){
                tasks.mark(userInput);
            }
            else if(userCommand.equals("unmark")){
                tasks.unmark(userInput);
            }
            else if(userCommand.equals("todo")){
                tasks.addTodo(userInput);
            }
            else if(userCommand.equals("deadline")){
                tasks.addDeadline(userInput);
            }
            else if(userCommand.equals("event")){
                tasks.addEvent(userInput);
            }
            else if(userCommand.equals("delete")){
                tasks.delete(userInput);
            }
            else{
                try {
                    throw new EkudException();
                }
                catch (EkudException error){
                    System.out.println(UI.INVALID_COMMAND_MESSAGE);
                }
            }
            userInput = in.nextLine();
        }

        try {
            storage.writeToFile(tasks);
        }
        catch(IOException error) {
            System.out.println(UI.FILE_WRITE_ERROR_MESSAGE);
        }

        System.out.println(UI.EXIT_MESSAGE);
    }
}
