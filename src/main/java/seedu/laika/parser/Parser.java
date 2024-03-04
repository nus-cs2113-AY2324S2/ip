package seedu.laika.parser;

import seedu.laika.LaikaException;
import seedu.laika.storage.Storage;
import seedu.laika.tasklist.TaskList;
import seedu.laika.ui.TextUi;

public class Parser {

    public boolean parse(String line, TaskList tasks){
        String[] words = line.split(" ", 2);

        switch(words[0]){
            case "mark":
            case "unmark":
                try {
                    tasks.modifyTask(words);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Laika: You dont have so many tasks!");
                }
                return true;
            case "list":
                tasks.displayTasks();
                return true;
            case "bye":
                return false;
            case "delete":
                try {
                    tasks.deleteTask(words);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Laika: You dont have so many tasks!");
                }

                System.out.println("Laika: You have " + (tasks.getSize() - 1) + " tasks left!");
                return true;
            default:

                try{
                    tasks.addTask(line);
                }
                catch(LaikaException e) {
                    System.out.println("Laika: Hmmmm, I dont understand you?");
                    break;
                }


                System.out.println("Laika: Gotcha! I've added the task for you\n  "
                        + tasks.getTask(tasks.getSize() - 1) + System.lineSeparator()
                        + "You have " + (tasks.getSize()) + " tasks in your list. :P");

                return true;



        }
        return true;
    }

}
