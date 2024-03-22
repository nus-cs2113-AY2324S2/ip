package chatman;

import chatman.commands.GreetCommand;
import chatman.exceptions.*;
import chatman.storage.StorageHandler;
import chatman.utility.Tasklist;
import chatman.utility.Ui;


import java.io.IOException;

/**
 * Provides entry point for ChatMan.
 *
 * @author LWachtel1
 * */
public class ChatMan {

    private Ui chatbot;

    public ChatMan() {
        chatbot = new Ui();
    }

    /**
     * Opens task storage file and loads file contents into list of stored tasks upon initial program execution.
     * If successful, prints greeting for user and calls chatbot.read() to trigger ChatMan loop execution.
     * Also provides handlers for any exceptions.
     **/
    public void run() {

        try {
            StorageHandler.openStorageFile();
            StorageHandler.loadStorageFile();

            GreetCommand hello = new GreetCommand(" ");
            hello.perform();


            chatbot.read();

        } catch(IOException e) {
            System.out.println(e.getMessage());
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

    /**
     * Main method of program.
     **/
    public static void main(String[] args) {
        new ChatMan().run();
    }
}
