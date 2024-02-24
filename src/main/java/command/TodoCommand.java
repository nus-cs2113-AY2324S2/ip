package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.Task;
import tasktype.TaskList;
import tasktype.Todo;
import ui.Ui;

public class TodoCommand implements Command {
    private final String userInput;

    public TodoCommand(String description) {
        userInput = description;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        if(userInput.isBlank()) {
            String invalidTodoInput = "Description of Todo cannot be empty.";
            throw new JingHaoExceptions(invalidTodoInput);
        }
        Task newTodo = new Todo(userInput);
        taskList.add(newTodo);
        System.out.println("Got it. I've added this task:\n " + newTodo);
        ui.printTotalTask(taskList.size());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
