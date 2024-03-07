package Bobble;

import Bobble.task.Deadline;
import Bobble.task.Event;
import Bobble.task.ToDo;

import java.io.IOException;
import java.util.Scanner;

public class Bobble {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bobble(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            String[] UserInputs = Parser.getCommandAndDescription(userInput);
            String command = UserInputs[0];
            try {
                switch (command.toLowerCase()) {
                case "list":
                    ui.listResponse(tasks.taskList);
                    break;
                case "todo":
                    ToDo newToDo = new ToDo(UserInputs[1]);
                    tasks.addNewTask(newToDo);
                    ui.addTaskResponse(tasks.taskList, newToDo);
                    storage.saveAddedTask(tasks.taskList);
                    break;
                case "deadline":
                    Deadline newDeadline = Parser.getNewDeadline(UserInputs[1]);
                    tasks.addNewTask(newDeadline);
                    ui.addTaskResponse(tasks.taskList, newDeadline);
                    storage.saveAddedTask(tasks.taskList);
                    break;
                case "event":
                    Event newEvent = Parser.getNewEvent(UserInputs[1]);
                    tasks.addNewTask(newEvent);
                    ui.addTaskResponse(tasks.taskList, newEvent);
                    storage.saveAddedTask(tasks.taskList);
                    break;
                case "mark":
                    int taskNumber = Parser.getTaskNumber(userInput, "mark");
                    tasks.markTask(taskNumber);
                    storage.saveWholeList(tasks.taskList);
                    ui.printMarkResponse(tasks.taskList.get(taskNumber).toString());
                    break;
                case "unmark":
                    taskNumber = Parser.getTaskNumber(userInput, "unmark");
                    tasks.unmarkTask(taskNumber);
                    storage.saveWholeList(tasks.taskList);
                    ui.printUnmarkResponse(tasks.taskList.get(taskNumber));
                    break;
                case "delete":
                    taskNumber = Parser.getTaskNumber(userInput, "delete");
                    ui.printDeleteResponse(tasks.taskList, tasks.taskList.get(taskNumber));
                    tasks.deleteTask(taskNumber);
                    storage.saveWholeList(tasks.taskList);
                    break;
                case "find":
                    String keyword = UserInputs[1];
                    tasks.findTask(keyword);
                    break;
                default:
                    throw new BobbleExceptionCommand();
                }
            } catch (BobbleExceptionCommand e) {
                ui.printErrorMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.printEmptyFieldErrorMessage(command);
            }
            userInput = input.nextLine();
        }
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Bobble("data/Bobble.txt").run();
    }
}
