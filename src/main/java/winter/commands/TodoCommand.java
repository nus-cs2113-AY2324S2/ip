package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Task;
import winter.task.ToDo;

import java.io.IOException;



public class TodoCommand extends Command {

    private String toDo;
    public static final String COMMAND_WORD = "todo";

    private static final String MESSAGE_SUCCESS = "Great! New ToDo added: ";

    public TodoCommand(String toDo) {
        this.toDo = toDo;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task newTodo = new ToDo(tasks.getCurrentTaskIndex() + 1,false, toDo);
        tasks.addNewTask(newTodo);
        //tasks.increaseLastTaskIndex();
        ui.showTaskAddedConfirm(tasks,newTodo);
        try {
            storage.writeToFile(formatTodoForStorage(newTodo), true);
        } catch (IOException e) {
            System.out.println("Could not write to file: " + e.getMessage());
        }
    }
    private String formatTodoForStorage(Task newTodo) {

        return "T" + " | " + newTodo.getIsMarked() + " | " +
                newTodo.getTaskName() + System.lineSeparator();
    }
}
