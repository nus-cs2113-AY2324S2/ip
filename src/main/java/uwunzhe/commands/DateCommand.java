package uwunzhe.commands;

import java.time.LocalDate;

import uwunzhe.tasks.Task;
import uwunzhe.tasks.Deadline;
import uwunzhe.tasks.Event;
import uwunzhe.tasks.TaskType;
import uwunzhe.util.TaskList;
import uwunzhe.util.Ui;
import uwunzhe.handler.Storage;
import uwunzhe.handler.DateHandler;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.exceptions.ExceptionMessages;

public class DateCommand extends Command {
    /**
     * Constructor for DateCommand.
     * 
     * @param commandString The command from the user.
     * @param taskString The string appended to the command to be executed.
     */
    public DateCommand(String commandString, String taskString) {
        super(commandString, taskString);
    }

    public void execute(TaskList taskList, Storage storage)
            throws UwunzheException {
        if (this.taskString.length() == 0) {
            throw new UwunzheException(ExceptionMessages.EXPECTED_EXTRA_COMMAND);
        }

        LocalDate date = DateHandler.parseDate(this.taskString);
        int size = taskList.getSize();
        int counter = 0;

        for (int i = 0; i < size; i++) {
            Task task = taskList.getTask(i);
            
            if (task.getTaskType().equals(TaskType.DEADLINE.getType())) {
                Deadline deadline = (Deadline) task;
                LocalDate end = deadline.getDeadline();

                if (end.equals(date)) {
                    counter++;
                    String counterString = Integer.toString(counter) + ".";
                    Ui.printlnTask(taskList, i, counterString);
                }

            } else if (task.getTaskType().equals(TaskType.EVENT.getType())) {
                Event event = (Event) task;
                LocalDate start = event.getStart();
                LocalDate end = event.getEnd();
                
                if (start.equals(date) || end.equals(date)
                        || (start.isBefore(date) && end.isAfter(date))) {
                    counter++;
                    String counterString = Integer.toString(counter) + ".";
                    Ui.printlnTask(taskList, i, counterString);
                }
            }
        }

        if (counter == 0) {
            Ui.println("Dun hv...");
        }
    }
}
