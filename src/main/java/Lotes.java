import java.util.Scanner;
public class Lotes {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        System.out.println(taskList.horizontalLine + taskList.lineSeparator
                + "    Hello! I'm" + taskList.logo + taskList.lineSeparator
                + "    What can I do for you?" + taskList.lineSeparator
                + taskList.horizontalLine);

        Scanner inputCommand = new Scanner(System.in); // Prompt for continuous user input.

        while (true) {
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

//            } else if (!userInput.isEmpty()) {
//                taskList.addTask(userInput); // Automatically add user input to tasks
                //taskList.addNewTask(userInput);

            } else if (userInput.startsWith("deadline ")) {
                taskList.addDeadline(userInput);
            } else {
                System.out.println("Enter something to add to your task list");

            }
        }
    }
}
