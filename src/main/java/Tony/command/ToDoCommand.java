package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.task.Task;
import Tony.task.Todo;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoCommand implements Command{
    private final String USER_INPUT;
    private ArrayList<Task> tasks;
    private FileSaver fileSaver;
    private Ui ui;
    private final Parser parser;

    public ToDoCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.fileSaver = fileSaver;
        String[] toDoTask = USER_INPUT.split("todo");

        try {
            parser.checkArrayLength(toDoTask);
            addTodoCommand(toDoTask);
        } catch (TonyException e) {
            System.out.println("OOPS!! The description of " + USER_INPUT
                    + " cannot be empty." + System.lineSeparator());
        }

    }

    public void addTodoCommand(String[] toDoTask) throws IOException {
        Todo todo = new Todo(toDoTask[1]);
        tasks.add(todo);
        ui.printAddOrDeleteTask(toDoTask[0], tasks.indexOf(todo));
        String todoLine = fileSaver.saveTodo(todo);
        fileSaver.saveData(todoLine, true);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
