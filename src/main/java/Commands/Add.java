package Commands;

import DukeException.Command_Not_Exist;
import DukeException.No_Description;
import Task.*;
import Utils.Parser;
import Utils.Storage;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


/**
 * Represents the Add Command. A <code>Add</code> object corresponds to
 * a Add Command
 */
public class Add extends Command{
    protected String taskDescription;
    protected String taskDate;
    public Add(Parser parser){
        super(parser);
        if(parser.getTaskDate()!=null) {
            this.taskDate = parser.getTaskDate();
            try {
                LocalDate date = LocalDate.parse(taskDate);

                this.taskDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy").withLocale(Locale.US));
            } catch (DateTimeParseException e) {
                System.out.println("\tYou can also use date format like 2023-07-30");
            }
        }
        this.taskDescription = parser.getTaskDescription();
    }
    /**
     * Execute the Add command with a given task
     * @param tasksList stores in ChatBot
     */
    @Override
    public void execute(ArrayList<Task> tasksList){
        try {
            if (commandType.equals("todo") || commandType.equals("deadline") || commandType.equals("event")) {
                if (taskDescription == null) {
                    throw new No_Description();
                }

                switch (commandType) {
                    case "todo": {
                        tasksList.add(new Todo(taskDescription));
                        break;
                    }
                    case "deadline": {
                        tasksList.add(new Deadline(taskDescription, taskDate));
                        break;
                    }
                    case "event": {
                        tasksList.add(new Event(taskDescription, taskDate));
                        break;

                    }

                }
                System.out.println("\t" + "Got it. I've added this task:");
                System.out.println("\t" + tasksList.get(tasksList.size()-1) + "\n");
                System.out.println("\tNow you have " + tasksList.size() + " tasks in the list. \n");
                System.out.println("\tStatus changed, I will save the data for you!");
                Storage storage = new Storage();
                Storage.saveStatus(tasksList);
            }
            else
            {
                throw new Command_Not_Exist();
            }

        }catch(No_Description e){
            int a=0;
        }catch(Command_Not_Exist e){
            System.out.println("\tSorry, I do not understand your command.");
        }catch(IndexOutOfBoundsException e){
            System.out.println("\tSorry, you should use \\ to indicate the time!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
