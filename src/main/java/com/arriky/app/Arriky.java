package com.arriky.app;

import com.arriky.exception.ErrorMessage;
import com.arriky.exception.IncorrectArgumentAmountException;
import com.arriky.task.TaskList;
import com.arriky.utilities.FileIO;

import java.util.Scanner;

public class Arriky {
    public static void main(String[] args) {

        // initialize
        Scanner sc = new Scanner(System.in);

        ErrorMessage em = new ErrorMessage();
        TaskList tl = new TaskList();

        // load saved entries from file
        tl.importTaskList();

        boolean running = true;
        greet();

        while(running) {
            String command = "";
            // for UI testing, to prevent "no line found" exception
            if (sc.hasNextLine()) {
                command = sc.nextLine();
            } else {
                running = false;
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
                    running = false;
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println(em.INCORRECT_ARGUMENT_AMOUNT_0);
                    printSeparation();
                }

                break;
            case "list":
                try {
                    if (arguments.length != 1) {
                        throw new IncorrectArgumentAmountException();
                    }
                    tl.listTasks();
                    printSeparation();
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println(em.INCORRECT_ARGUMENT_AMOUNT_0);
                    printSeparation();
                }

                break;
            case "mark":
                try {
                    if (arguments.length != 2) {
                        throw new IncorrectArgumentAmountException();
                    }
                    tl.markDone(Integer.parseInt(arguments[1]) - 1);
                } catch (NumberFormatException e) {
                    System.out.println(em.INVALID_ID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(em.ID_NOT_EXIST);
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println(em.INCORRECT_ARGUMENT_AMOUNT_1);
                } finally {
                    printSeparation();
                }
                break;
            case "unmark":
                try {
                    if (arguments.length != 2) {
                        throw new IncorrectArgumentAmountException();
                    }
                    tl.unmarkDone(Integer.parseInt(arguments[1]) - 1);
                } catch (NumberFormatException e) {
                    System.out.println(em.INVALID_ID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(em.ID_NOT_EXIST);
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println(em.INCORRECT_ARGUMENT_AMOUNT_1);
                } finally {
                    printSeparation();
                }
                break;
            case "todo":
                String taskName = command.substring(5);
                tl.addToDo(taskName, false);
                printSeparation();
                break;
            case "deadline": {
                try {
                    String[] segments = command.split(" /by ");
                    tl.addDeadline(segments[0].substring(9), segments[1], false);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(em.INVALID_DEADLINE_FORMAT);
                } finally {
                    printSeparation();
                }
                break;
            }
            case "event": {
                try {
                    String[] segments = command.split(" /");
                    tl.addEvent(segments[0].substring(6), segments[1].substring(5), segments[2].substring(3), false);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(em.INVALID_EVENT_FORMAT);
                    printSeparation();
                }

                break;
            }
            case "delete": {
                try {
                    if (arguments.length != 2) {
                        throw new IncorrectArgumentAmountException();
                    }
                    tl.delete(Integer.parseInt(arguments[1]) - 1);
                } catch (NumberFormatException e) {
                    System.out.println(em.INVALID_ID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(em.ID_NOT_EXIST);
                } catch (IncorrectArgumentAmountException e) {
                    System.out.println((em.INCORRECT_ARGUMENT_AMOUNT_1));
                } finally {
                    printSeparation();
                }
                break;
            }
            default:
                System.out.println(em.INVALID_COMMAND);
                printSeparation();
            }

            tl.saveTaskList();
        }
    }

    private static void echo(String cmd) {
        printSeparation();
        System.out.println(cmd);
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
