import java.util.Scanner;
public class Lotes {
    public static final Scanner inputCommand = new Scanner(System.in); // Prompt for continuous user input.

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        System.out.println(TaskList.greetings);

        while (inputCommand.hasNextLine()) {
            String userInput = inputCommand.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(taskList.horizontalLine + taskList.lineSeparator
                        + "    Bye. Hope to see you again soon!\n" + taskList.horizontalLine);
                break; // Exit the program

            } else if (userInput.equals("list")) {
                taskList.printTasksList();

            } else if (userInput.startsWith("mark ")) {
                taskList.markTask(userInput);

            } else if (userInput.startsWith("unmark ")) {
                taskList.unMarkTask(userInput);

            } else if (userInput.startsWith("todo ")) {
                taskList.addToDo(userInput);

            } else if (userInput.startsWith("deadline ")) {
                taskList.addDeadline(userInput);

            } else if (userInput.startsWith("event ")) {
                taskList.addEvent(userInput);

            } else if (userInput.startsWith("add ")) {
                taskList.addNewTask(userInput);

            } else {
                System.out.println("Enter something to add to your task list");

            }
        }
    }
}
