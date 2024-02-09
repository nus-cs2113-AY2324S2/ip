import java.util.Scanner;
public class Lotes {
    public static final Scanner inputCommand = new Scanner(System.in); // Prompt for continuous user input.

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        System.out.println(TaskList.greetingsMessage);

        while (inputCommand.hasNextLine()) {
            String userInput = inputCommand.nextLine();

            if (TaskList.readUserInput(userInput, taskList)) {
                break; // Exit the program
            }
        }
    }
}
