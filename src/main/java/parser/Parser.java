package parser;

import command.*;
import errorhandle.UserInputErrorOutputHandler;
import format.Formatter;
import task.*;

import java.io.IOException;

import storage.Storage;
import ui.Ui;


public class Parser {
    protected Formatter formatter;
    protected UserInputErrorOutputHandler userInputError;
    protected Ui ui;

    public Parser() {
        formatter = new Formatter();
        userInputError = new UserInputErrorOutputHandler();
        ui = new Ui();
    }

    public void parseCommand(String userCommandText, TaskList taskList) {
        Command command = null;

        if (userCommandText.equalsIgnoreCase("list")) {
            taskList.printTaskList();
        } else if (userCommandText.equalsIgnoreCase("help")) {
            formatter.printDividingLine();
            formatter.printFunctionality();
            formatter.printDividingLine();
        } else {
            String userCommandTextLowerCase = userCommandText.toLowerCase();
            if (userCommandTextLowerCase.startsWith("unmark")) {
                command = new UnmarkCommand(userCommandText, taskList);
            } else if (userCommandTextLowerCase.startsWith("delete")) {
                command = new DeleteCommand(userCommandText, taskList);
            } else if (userCommandTextLowerCase.startsWith("mark")) {
                command = new MarkCommand(userCommandText, taskList);
            } else if (userCommandTextLowerCase.startsWith("todo")) {
                command = new TodoCommand(userCommandText);
            } else if (userCommandTextLowerCase.startsWith("deadline")) {
                command = new DeadlineCommand(userCommandText);
            } else if (userCommandTextLowerCase.startsWith("event")) {
                command = new EventCommand(userCommandText);
            } else {
                userInputError.printUndefinedCommandError();
            }
            if (command != null && command.getIfNoError()) {
                command.execute(taskList);
            }
        }
    }
}