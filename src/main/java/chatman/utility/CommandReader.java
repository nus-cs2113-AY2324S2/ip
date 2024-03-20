package chatman.utility;

import chatman.exceptions.*;
import chatman.commands.*;

import java.util.Scanner;

/**
 * Implements functionality to continuously read in user input and trigger input parsing and subsequent execution of
 * valid commands.
 *
 * @author LWachtel1
 * */
public class CommandReader {

    public CommandReader() {
    }

    /**
     * Provides main loop of ChatMan; reads user input and instantiates CommandParser object to parse input &
     * instantiate appropriate Command subclass object to perform corresponding command.
     * Also provides exception handlers to catch all exceptions thrown from subsequent method calls.
     **/
    public void read() {
        Scanner commandReader = new Scanner(System.in);
        String userCommand;
        boolean shouldExitLoop = false;
        CommandParser parser = new CommandParser();

        while (!shouldExitLoop) {
            System.out.printf("%s%n%n", "____________________________________________________________");
            userCommand = commandReader.nextLine();

            try {
                Command performedCommand = parser.parse(userCommand);
                if (performedCommand instanceof ByeCommand) {
                    shouldExitLoop = true;
                }
            } catch (FalseCommandException e) {
                e.sendErrorMsg();
            } catch (FullListException e) {
                e.sendErrorMsg();
            } catch (IncorrectArgumentNumException e) {
                e.sendErrorMsg();
            } catch (IncorrectMarkUnmarkException e) {
                e.sendErrorMsg();
            } catch (EmptyListException e) {
                e.sendErrorMsg();
            }

        }
    }
}
