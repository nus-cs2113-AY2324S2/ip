package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import tasks.TaskList;
import tasks.*;
import interactions.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    public void addTask(Task newTask, TaskList taskList) {
        taskList.addToList(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.print(INDENT);
        newTask.print();
        taskList.countTasks();
    }

    public void addNewTask(TaskList taskList)
            throws IncompletePromptException, UnknownPromptException {
        String taskDescription = extractTaskOrDate(line, firstWord);
        if (taskDescription.isEmpty()) {
            throw new IncompletePromptException();
        }
        Task newTask;
        switch (firstWord) {
        case "todo":
            newTask = new ToDo(taskDescription);
            ((ToDo)newTask).setHaveToDo(true);
            newTask.setTaskType("T");
            break;
        case "deadline":
            newTask = new Deadline(taskDescription);
            String deadline = extractTaskOrDate(line, "by");
            if (deadline.isEmpty()) {
                throw new IncompletePromptException();
            }
            ((Deadline)newTask).setDeadline(deadline);
            newTask.setTaskType("D");
            break;
        case "event":
            newTask = new Event(taskDescription);
            String dateFrom = extractTaskOrDate(line, "from");
            String dateTo = extractTaskOrDate(line, "to");
            if (dateFrom.isEmpty() || dateTo.isEmpty()) {
                throw new IncompletePromptException();
            } else {
                ((Event)newTask).setEventFrom(dateFrom);
                ((Event)newTask).setEventTo(dateTo);
                ((Event)newTask).setEvent(true);
                newTask.setTaskType("E");
            }
            break;
        default:
            throw new UnknownPromptException();
        }
        addTask(newTask, taskList);
    }
    @Override
    public void execute(TaskList taskList, Storage storage)
            throws IncompletePromptException, UnknownPromptException {
        addNewTask(taskList);
        try {
            storage.saveToFile("data/list.txt", taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
