package jeff;

import jeff.exceptions.InvalidCharacterInDescriptionException;
import jeff.exceptions.InvalidCharacterInFromException;
import jeff.exceptions.InvalidCharacterInToException;
import jeff.exceptions.InvalidCommandException;
import jeff.exceptions.InvalidDeadlineSyntaxException;
import jeff.exceptions.InvalidDeleteSyntaxException;
import jeff.exceptions.InvalidEventSyntaxException;
import jeff.exceptions.InvalidFindSyntaxException;
import jeff.exceptions.InvalidMarkSyntaxException;
import jeff.exceptions.InvalidTodoSyntaxException;
import jeff.exceptions.InvalidUnmarkSyntaxException;
import jeff.exceptions.UnableToDeleteException;
import jeff.exceptions.UnableToMarkException;
import jeff.exceptions.UnableToUnmarkException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Serves as the interface between the user and the application.
 */
public class Ui {

    /**
     * Handles user input by continuously reading input from the standard input (keyboard),
     * parsing it into commands, and executing the corresponding commands.
     * It continues processing user input until an exit command is received.
     */
    public static void handleUserInput() {
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = in.nextLine();
                Printer.printDivider();
                Command command = Parser.parseCommand(userInput);
                command.execute();
                isExit = command.isExit();
            } catch (InvalidCommandException e) {
                ExceptionHandler.handleInvalidCommandException();
            } catch (InvalidTodoSyntaxException e) {
                ExceptionHandler.handleInvalidTodoSyntaxException();
            } catch (InvalidDeadlineSyntaxException | DateTimeParseException e) {
                ExceptionHandler.handleInvalidDeadlineSyntaxException();
            } catch (InvalidEventSyntaxException e) {
                ExceptionHandler.handleInvalidEventSyntaxException();
            } catch (InvalidMarkSyntaxException e) {
                ExceptionHandler.handleInvalidMarkSyntaxException();
            } catch (InvalidUnmarkSyntaxException e) {
                ExceptionHandler.handleInvalidUnmarkSyntaxException();
            } catch (InvalidDeleteSyntaxException e) {
                ExceptionHandler.handleInvalidDeleteSyntaxException();
            } catch (InvalidFindSyntaxException e) {
                ExceptionHandler.handleInvalidFindSyntaxException();
            } catch (InvalidCharacterInDescriptionException e) {
                ExceptionHandler.handleInvalidCharacterInDescriptionException();
            } catch (InvalidCharacterInFromException e) {
                ExceptionHandler.handleInvalidCharacterInFromException();
            } catch (InvalidCharacterInToException e) {
                ExceptionHandler.handleInvalidCharacterInToException();
            } catch (UnableToMarkException e) {
                ExceptionHandler.handleUnableToMarkException();
            } catch (UnableToUnmarkException e) {
                ExceptionHandler.handleUnableToUnmarkException();
            } catch (UnableToDeleteException e) {
                ExceptionHandler.handleUnableToDeleteException();
            } finally {
                Printer.printDivider();
            }
        }
    }
}
