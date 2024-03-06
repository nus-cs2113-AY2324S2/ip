import exceptions.InvalidDeadlineSyntaxException;
import exceptions.InvalidEventSyntaxException;
import exceptions.InvalidTodoSyntaxException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui/User Interface deals with interactions with the user
 */

public class Ui {

    public static void handleUserInput(ArrayList<Task> taskArrayList) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while(isRunning) {
             String userInput = input.nextLine().trim();

             try {
                 if (userInput.equalsIgnoreCase("bye")) {
                     break;
                 } else if (userInput.equalsIgnoreCase("list")) {
                     Printer.printTaskList(taskArrayList);
                 } else if (userInput.startsWith("mark")) {
                     int index = Parser.obtainIndexToMark(userInput);
                     taskArrayList.get(index).markAsDone();
                     Printer.printMark(taskArrayList.get(index));
                 } else if (userInput.startsWith("unmark")) {
                     int index = Parser.obtainIndexToUnmark(userInput);
                     taskArrayList.get(index).unmarkDone();
                     Printer.printUnmark(taskArrayList.get(index));
                 } else if (userInput.startsWith("todo")) {
                     TaskList.addTodo(userInput, taskArrayList);
                     Printer.printAddedTask(taskArrayList);
                 } else if (userInput.startsWith("deadline")) {
                     TaskList.addDeadline(userInput, taskArrayList);
                     Printer.printAddedTask(taskArrayList);
                 } else if (userInput.startsWith("event")) {
                     TaskList.addEvent(userInput, taskArrayList);
                     Printer.printAddedTask(taskArrayList);
                 } else if (userInput.startsWith("delete")) {
                     Printer.printDelete(taskArrayList, Parser.obtainDeleteIndex(userInput));
                     TaskList.deleteTask(Parser.obtainDeleteIndex(userInput), taskArrayList);
                 } else {
                     Printer.printUnknownInput();
                 }
             } catch (InvalidTodoSyntaxException e) {
                 Printer.handleInvalidTodoSyntaxException(e);
             } catch (InvalidDeadlineSyntaxException e) {
                 Printer.handleInvalidDeadlineSyntaxException(e);
             } catch (InvalidEventSyntaxException e) {
                 Printer.handleInvalidEventSyntaxException(e);
             }


        }



    }




}
