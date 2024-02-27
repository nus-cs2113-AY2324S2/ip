package duke;

import duke.tasks.*;

import java.io.IOException;
import java.util.Scanner;

public class Parser {

    /**
     * Checks if the string is a number
     * @param str The string that is to be defined as a number or sentence
     * @return true or false
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Reads the inputs from user and carries out the appropriate actions
     * @param ui The Ui
     * @param taskList The task list
     * @param storage The text file storage
     */
    public void readInputs(Ui ui, TaskList taskList, Storage storage) {
        String line;
        Scanner in = new Scanner(System.in);
        boolean userSaidBye = false;
        while (!userSaidBye) {
            line = in.nextLine();
            try {

                if (line.equals("bye")) {
                    userSaidBye = true;

                } else if (line.equals("list")) {
                    // Prints the all the tasks in the list
                    ui.printLine();
                    System.out.println("     Here are the tasks in your list:");
                    taskList.listTasks();
                    ui.printLine();

                } else if (line.startsWith("mark")) {
                    // Marks the task in the list
                    String[] sentence = line.split(" ");
                    if (sentence.length < 2 || !isNumeric(sentence[1])) {
                        throw new DukeException("Please do not give a non numeric or empty description\nMark format: mark 1");
                    }
                    ui.printLine();
                    int taskNumber = Integer.parseInt(sentence[1]);
                    taskList.checkTask(taskNumber);
                    ui.printMarkTaskAsDone(taskList.getTasks(), taskNumber);
                    ui.printLine();

                } else if (line.startsWith("unmark")) {
                    // Unmarks the task in the list
                    String[] sentence = line.split(" ");
                    if (sentence.length < 2 || !isNumeric(sentence[1])) {
                        throw new DukeException("Please do not give a non numeric or empty description\nUnmark format: unmark 1");
                    }
                    ui.printLine();
                    int taskNumber = Integer.parseInt(sentence[1]);
                    taskList.uncheckTask(taskNumber);
                    ui.printMarkTaskAsNotDone(taskList.getTasks(), taskNumber);
                    ui.printLine();

                } else if (line.startsWith("deadline")) {
                    // Adds a deadline task into the list
                    String[] sentence = line.split("deadline|/by");
                    if (sentence.length < 3 || sentence[1].isBlank() || sentence[2].isBlank()) {
                        throw new DukeException("ERROR!?!? deadline has an incomplete or empty description mortal!\nDeadline format: deadline help me /by Mon 2pm");
                    }
                    Task newTask = new Deadlines(sentence[1], sentence[2]);
                    taskList.addTask(newTask);
                    ui.printLine();
                    ui.printAddTask(newTask, taskList);
                    ui.printLine();

                } else if (line.startsWith("event")) {
                    // Adds an event task into the list
                    String[] sentence = line.split("event|/from|/to");
                    if (sentence.length < 4 || sentence[1].isBlank() || sentence[2].isBlank() || sentence[3].isBlank()) {
                        throw new DukeException("ERROR!?!? event has an incomplete or empty description mortal!\nEvent format: event help me /from Mon 2pm /to 3pm");
                    }
                    Task newTask = new Events(sentence[1], sentence[2], sentence[3]);
                    taskList.addTask(newTask);
                    ui.printLine();
                    ui.printAddTask(newTask, taskList);
                    ui.printLine();

                } else if (line.startsWith("todo")) {
                    // Adds an todo task into the list
                    String[] sentence = line.split("todo");
                    // Checks if the description of the task is empty
                    if (sentence.length < 2 || sentence[1].isBlank()) {
                        throw new DukeException("ERROR!?!? todo has to have a description mortal!\nTodo format: todo help me");
                    }
                    Task newTask = new ToDos(sentence[1]);
                    taskList.addTask(newTask);
                    ui.printLine();
                    ui.printAddTask(newTask, taskList);
                    ui.printLine();

                } else if (line.startsWith("delete")) {
                    // Deletes the task in the list
                    String[] sentence = line.split(" ");
                    if (sentence.length < 2 || !isNumeric(sentence[1])) {
                        throw new DukeException("Please do not give a non numeric or empty description\nDelete format: delete 1");
                    }
                    ui.printLine();
                    int taskNumber = Integer.parseInt(sentence[1]);
                    taskList.removeTask(taskNumber, ui);
                    ui.printLine();

                } else if (line.startsWith("help")) {
                    // Prints all the valid commands
                    ui.printCommands();
                } else {
                    // Throws an invalid command error
                    throw new DukeException("I do not understand this command mortal\nType help for help");
                }
                // Saves the task list into duke.txt
                storage.saveTaskList(taskList);

            } catch (DukeException e) {
                ui.printDukeError(e);
            } catch (IOException e) {
                ui.printFileCantSave();
            }
        }
    }
}
