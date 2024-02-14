import java.util.Scanner;

import task.Deadline;
import task.Task;
import task.Todo;
import task.Event;
import task.TaskList;
import tool.MessageDecoder;
import tool.ResponseManager;
import exception.InputException;

public class ZukeLogic {
    private final TaskList taskList;

    ZukeLogic() {
        taskList = new TaskList();
    }

    public static ZukeLogic initZuke() {
        ResponseManager.greet();
        return new ZukeLogic();
    }

    public static void exitZuke() {
        ResponseManager.sayGoodbye();
    }

    public void chattingStart() {
        Scanner userInput = new Scanner(System.in);

        while (userInput.hasNextLine()) {
            String[] processedMessage = MessageDecoder.separateCommand(userInput.nextLine());
            String command = processedMessage[0];
            String info = processedMessage[1];

            try {
                switch (command) {
                case "bye":
                    userInput.close();
                    return;

                case "list":
                    ResponseManager.listTaskToUser(taskList.listTasks());
                    break;

                case "todo":
                    String todo = MessageDecoder.decodeTodo(info);
                    Task newTodo = new Todo(todo);
                    taskList.add(newTodo);
                    ResponseManager.sendTaskAddedToUser(newTodo +
                            "\n" + taskList);
                    break;

                case "deadline":
                    String[] deadlineInfo = MessageDecoder.decodeDeadline(info);
                    Task newDeadline = new Deadline(deadlineInfo);
                    taskList.add(newDeadline);
                    ResponseManager.sendTaskAddedToUser(newDeadline +
                            "\n" + taskList);
                    break;

                case "event":
                    String[] eventInfo = MessageDecoder.decodeEvent(info);
                    Task newEvent = new Event(eventInfo);
                    taskList.add(newEvent);
                    ResponseManager.sendTaskAddedToUser(newEvent +
                            "\n" + taskList);
                    break;

                case "mark":
                    int markIndex = MessageDecoder.decodeToggleMark(info);
                    if (markIndex > taskList.getSize()) {
                        throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
                    }
                    taskList.markTask(markIndex);
                    ResponseManager.printMarkOrUnMarkTask("mark",
                            taskList.getPosAt(markIndex).toString());
                    break;

                case "unmark":
                    int unmarkIndex = MessageDecoder.decodeToggleMark(info);
                    if (unmarkIndex > taskList.getSize()) {
                        throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
                    }
                    taskList.unmarkTask(unmarkIndex);
                    ResponseManager.printMarkOrUnMarkTask("unmark",
                            taskList.getPosAt(unmarkIndex).toString());
                    break;

                default:
                    throw new InputException(ResponseManager.COMMAND_ERROR);
                }
            } catch (InputException error) {
                ResponseManager.indentPrint(error.getMessage());
            }

        }
    }
}
