package jeff;

import jeff.exceptions.InvalidCommandException;
import jeff.exceptions.InvalidDeadlineSyntaxException;
import jeff.exceptions.InvalidDeleteSyntaxException;
import jeff.exceptions.InvalidEventSyntaxException;
import jeff.exceptions.InvalidMarkSyntaxException;
import jeff.exceptions.InvalidTodoSyntaxException;
import jeff.exceptions.InvalidUnmarkSyntaxException;
import java.util.Scanner;

public class UserInterface {
    public static void handleUserInput() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String userInput = in.nextLine().trim();
                Printer.printDivider();
                if (userInput.equals("list")) {
                    CommandHandler.handleList();
                } else if (userInput.equals("todo") || userInput.startsWith("todo ")) {
                    CommandHandler.handleTodo(userInput);
                } else if (userInput.equals("deadline") || userInput.startsWith("deadline ")) {
                    CommandHandler.handleDeadline(userInput);
                } else if (userInput.equals("event") || userInput.startsWith("event ")) {
                    CommandHandler.handleEvent(userInput);
                } else if (userInput.equals("mark") || userInput.startsWith("mark ")) {
                    CommandHandler.handleMark(userInput);
                } else if (userInput.equals("unmark") || userInput.startsWith("unmark ")) {
                    CommandHandler.handleUnmark(userInput);
                } else if (userInput.equals("delete") || userInput.startsWith("delete ")) {
                    CommandHandler.handleDelete(userInput);
                } else if (userInput.equals("bye")) {
                    CommandHandler.handleBye();
                    return;
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                ExceptionHandler.handleInvalidCommandException();
            } catch (InvalidTodoSyntaxException e) {
                ExceptionHandler.handleInvalidTodoSyntaxException();
            } catch (InvalidDeadlineSyntaxException e) {
                ExceptionHandler.handleInvalidDeadlineSyntaxException();
            } catch (InvalidEventSyntaxException e) {
                ExceptionHandler.handleInvalidEventSyntaxException();
            } catch (InvalidMarkSyntaxException e) {
                ExceptionHandler.handleInvalidMarkSyntaxException();
            } catch (InvalidUnmarkSyntaxException e) {
                ExceptionHandler.handleInvalidUnmarkSyntaxException();
            } catch (InvalidDeleteSyntaxException e) {
                ExceptionHandler.handleInvalidDeleteSyntaxException();
            }
            finally {
                Printer.printDivider();
            }
        }
    }
}
