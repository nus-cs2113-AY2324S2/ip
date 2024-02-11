import java.util.Scanner;

public class Lotes {

    // Setting the Scanner

    public static final Scanner inputCommand = new Scanner(System.in);

    public static void main(String[] args) {
        TaskList taskList = new TaskList(); // Creating the TaskList object

        System.out.println(TaskList.greetingsMessage); // Print greetings message

        while (inputCommand.hasNextLine()) { // Prompt for continuous user input
            String userInput = inputCommand.nextLine();

            if (parser.performUserInput(userInput, taskList)) { // if ixExit returns true
                break; // Exit reading and interpreting next line
            }
        }
    }
}
