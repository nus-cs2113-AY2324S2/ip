package Commands;

import DukeException.Command_Not_Exist;
import DukeException.No_Description;
import Task.*;
import Utils.Parser;
import Utils.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class Add extends Command{
    protected String taskDescription;
    protected String taskDate;
    public Add(Parser parser){
        super(parser);
        this.taskDate = parser.getTaskDate();
        this.taskDescription = parser.getTaskDescription();
    }

    @Override
    public void execute(ArrayList<Task> tasks_list){
        try {
            if (commandType.equals("todo") || commandType.equals("deadline") || commandType.equals("event")) {
                if (taskDescription == null) {
                    throw new No_Description();
                }

                switch (commandType) {
                    case "todo": {
                        tasks_list.add(new Todo(taskDescription));
                        break;
                    }
                    case "deadline": {
                        tasks_list.add(new Deadline(taskDescription, taskDate));
                        break;
                    }
                    case "event": {
                        tasks_list.add(new Event(taskDescription, taskDate));
                        break;

                    }

                }
                System.out.println("\t" + "Got it. I've added this task:");
                System.out.println("\t" + tasks_list.get(tasks_list.size()-1) + "\n");
                System.out.println("\tNow you have " + tasks_list.size() + " tasks in the list. \n");
                System.out.println("\tStatus changed, I will save the data for you!");
                Storage storage = new Storage();
                Storage.saveStatus(tasks_list);
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
