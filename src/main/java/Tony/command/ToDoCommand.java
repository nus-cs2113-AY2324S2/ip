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

    /**
     * Represents {@code ToDoCommand} object to handle todo task type.
     * @param line is the String input by user
     * @param parser to help with processing the String input.
     */
    public ToDoCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }

    /**
     * Executes the <code>addTodoCommand</code> method to add Todo task into list,
     * checks if there are no words after todo keyword
     * @param tasks is the current list of <code>tasks</code> to save to the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     * @throws IOException If there is error in saving data into file.
     */

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
            System.out.println("OOPS!! The description of '" + USER_INPUT
                    + "' cannot be empty." + System.lineSeparator());
        }

    }

    /**
     * Processes the <code>toDoTask</code> String after the todo keyword
     * @param toDoTask array String after todo keyword.
     * @throws IOException If there is error in saving data into file.
     */
    public void addTodoCommand(String[] toDoTask) throws IOException {
        Todo todo = new Todo(toDoTask[1]);
        tasks.add(todo);
        ui.printAddOrDeleteTask(toDoTask[0], tasks.indexOf(todo));
        String todoLine = fileSaver.saveTodo(todo);
        fileSaver.saveData(todoLine, true);
    }
    /**
     * Returns <code>false</code> if command not entered <code>bye</code>
     * @return <code>false</code> and does not exit program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
