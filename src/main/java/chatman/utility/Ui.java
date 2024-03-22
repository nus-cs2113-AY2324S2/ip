package chatman.utility;

import chatman.exceptions.*;
import chatman.commands.*;
import chatman.storage.StorageHandler;

import java.io.IOException;
import java.util.Scanner;

/**
 * Implements functionality to continuously read in user input and trigger input parsing and subsequent execution of
 * valid commands.
 *
 * @author LWachtel1
 * */
public class Ui {

    private static String chatbotSeparator ="____________________________________________________________";
    public Ui() {
    }

    public static String getChatbotSeparator() {
        return chatbotSeparator;
    }

    /**
     * Provides main loop of ChatMan; reads user input and instantiates Parser object to parse input &
     * instantiate appropriate Command subclass object to perform corresponding command. Upon program exit, it
     * saves all currently stored tasks to the hard disk.
     * Also provides exception handlers to catch all exceptions thrown from subsequent method calls.
     **/
    public void read() {

        Scanner commandReader = new Scanner(System.in);
        String userCommand;
        boolean shouldExitLoop = false;
        Parser parser = new Parser();

        while (!shouldExitLoop) {
            System.out.printf("%s%n%n", getChatbotSeparator());
            userCommand = commandReader.nextLine();

            try {
                Command performedCommand = parser.parse(userCommand, false);
                if (performedCommand instanceof ByeCommand) {
                    shouldExitLoop = true;
                }
            } catch (FalseCommandException e) {
                e.sendErrorMsg();
            } catch (FullListException e) {
                e.sendErrorMsg();
            } catch (IncorrectArgumentNumException e) {
                e.sendErrorMsg();
            } catch (IncorrectIndexException e) {
                e.sendErrorMsg();
            } catch (EmptyListException e) {
                e.sendErrorMsg();
            } catch (IncorrectFormatException e) {
                e.sendErrorMsg();
            }

        }

        try {
            StorageHandler.writeStorageFile();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
