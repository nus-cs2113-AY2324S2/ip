package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;
public class ExitCommand extends Command{

        public ExitCommand(){
        }
        public void execute(TaskList tasks, Ui ui, Storage storage){
            ui.showExit();
        }

        @Override
        public boolean isExit(){
            return true;
        }
}
