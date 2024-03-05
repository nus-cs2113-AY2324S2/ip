package com.arriky.app;

import com.arriky.exception.ErrorMessage;
import com.arriky.exception.IncorrectArgumentAmountException;
import com.arriky.task.TaskList;
import java.util.Scanner;

public class Arriky {
    public static void main(String[] args) {

        // initialize
        Scanner scanner = new Scanner(System.in);

        ErrorMessage errMsg = new ErrorMessage();
        TaskList taskList = new TaskList();

        // load saved entries from file
        taskList.importTaskList();

        boolean isRunning = true;
        greet();

        while(isRunning) {
            String command = "";

            // for automated UI testingz
            if (scanner.hasNextLine()) {
                command = scanner.nextLine();
            } else {
                isRunning = false;
                System.exit(0);
            }

            printSeparation();

            String[] arguments = command.split(" ");

            switch (arguments[0]) {
            case "bye":
                try {
                    if (arguments.length != 1) {
                        throw new IncorrectArgumentAmountException();
                    }
                    endSession();
                    isRunning = false;
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println(errMsg.INCORRECT_ARGUMENT_AMOUNT_0);
                }
                break;

            case "list":
                try {
                    if (arguments.length != 1) {
                        throw new IncorrectArgumentAmountException();
                    }
                    taskList.listTasks();
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println(errMsg.INCORRECT_ARGUMENT_AMOUNT_0);
                }
                break;

            case "mark":
                try {
                    if (arguments.length != 2) {
                        throw new IncorrectArgumentAmountException();
                    }
                    taskList.markDone(Integer.parseInt(arguments[1]) - 1);
                } catch (NumberFormatException e) {
                    System.out.println(errMsg.INVALID_ID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(errMsg.ID_NOT_EXIST);
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println(errMsg.INCORRECT_ARGUMENT_AMOUNT_1);
                }
                break;

            case "unmark":
                try {
                    if (arguments.length != 2) {
                        throw new IncorrectArgumentAmountException();
                    }
                    taskList.unmarkDone(Integer.parseInt(arguments[1]) - 1);
                } catch (NumberFormatException e) {
                    System.out.println(errMsg.INVALID_ID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(errMsg.ID_NOT_EXIST);
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println(errMsg.INCORRECT_ARGUMENT_AMOUNT_1);
                }
                break;

            case "todo":
                String taskName = command.substring(5);
                taskList.addToDo(taskName, false);
                break;

            case "deadline":
                try {
                    String[] segments = command.split(" /by ");
                    taskList.addDeadline(segments[0].substring(9), segments[1], false);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(errMsg.INVALID_DEADLINE_FORMAT);
                }
                break;

            case "event":
                try {
                    String[] segments = command.split(" /");
                    taskList.addEvent(segments[0].substring(6), segments[1].substring(5), segments[2].substring(3), false);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(errMsg.INVALID_EVENT_FORMAT);
                }
                break;

            case "delete":
                try {
                    if (arguments.length != 2) {
                        throw new IncorrectArgumentAmountException();
                    }
                    taskList.delete(Integer.parseInt(arguments[1]) - 1);
                } catch (NumberFormatException e) {
                    System.out.println(errMsg.INVALID_ID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(errMsg.ID_NOT_EXIST);
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println((errMsg.INCORRECT_ARGUMENT_AMOUNT_1));
                }
                break;

            default:
                System.out.println(errMsg.INVALID_COMMAND);
            }

            printSeparation();
            taskList.saveTaskList();
        }
    }

    private static void greet() {
        printSeparation();
        System.out.println(
                " Hello! I'm Arriky\n" +
                " What can I do for you?"
        );
        printSeparation();
    }

    private static void endSession() {
        System.out.println(" Bye. Hope to see you again soon.");
        printSeparation();
    }

    private static void printSeparation() {
        System.out.println("____________________________________________________________");
    }
}
