package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command{

        public ExitCommand(){
        }

        /**
         * Executes the command to exit the program.
         * Prints the goodbye message.
         * @param tasks the list of tasks
         * @param ui the user interface
         * @param storage the storage
         */
        public void execute(TaskList tasks, Ui ui, Storage storage){
            ui.showExit();
        }

        @Override
        public boolean isExit(){
            return true;
        }
}
