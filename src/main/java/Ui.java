import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static void handleUserInput(ArrayList<Task> taskArrayList) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while(isRunning) {
             String userInput = input.nextLine();

             if (userInput.equalsIgnoreCase("bye")) {
                 break;
             }

             else if (userInput.equalsIgnoreCase("list")) {
                 Printer.printTaskList(taskArrayList);
             }

             else if (userInput.startsWith("mark")) {
                 int index = Parser.obtainIndexToMark(userInput);
                 taskArrayList.get(index).markAsDone();
                 Printer.printMark(taskArrayList.get(index));
             }

             else if (userInput.startsWith("unmark")) {
                 int index = Parser.obtainIndexToUnmark(userInput);
                 taskArrayList.get(index).unmarkDone();
                 Printer.printUnmark(taskArrayList.get(index));
             }

             else if (userInput.startsWith("todo ")) {
                 TaskList.addTodo(userInput, taskArrayList);
                 Printer.printAddedTask(taskArrayList);
             }

             else if (userInput.startsWith("deadline" )) {
                 TaskList.addDeadline(userInput, taskArrayList);
                 Printer.printAddedTask(taskArrayList);
             }

             else if (userInput.startsWith("event")) {
                 TaskList.addEvent(userInput, taskArrayList);
                 Printer.printAddedTask(taskArrayList);
             }

             else if (userInput.startsWith("delete")) {
                 Printer.printDelete(taskArrayList, Parser.obtainDeleteIndex(userInput));
                 TaskList.deleteTask(Parser.obtainDeleteIndex(userInput), taskArrayList);
             }

             else {
                 System.out.println("OHHH NOOOOOOO! I don't know what that means.");
             }


        }



    }




}
