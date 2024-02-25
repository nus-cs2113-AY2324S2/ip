package jeff;

import jeff.exceptions.*;

import java.util.Scanner;

public class Ui {
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
