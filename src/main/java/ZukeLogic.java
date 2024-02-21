import java.util.Scanner;
import task.Deadline;
import task.Task;
import task.Todo;
import task.Event;
import task.TaskList;
import tool.DataManager;
import tool.MessageDecoder;
import tool.ResponseManager;
import exception.InputException;

public class ZukeLogic {
    private TaskList taskList;

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
        DataManager.createFolder();
        loadData();
        while (userInput.hasNextLine()) {
            DataManager.saveData(taskList);
            String[] processedMessage = MessageDecoder.separateCommand(userInput.nextLine());
            String command = processedMessage[0];
            String info = processedMessage[1];

            try {
                switch (command) {
                case "bye":
                    //DataManager.saveData(taskList);
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
                    int markIndex = MessageDecoder.decodeIndex(info);
                    taskList.markTask(markIndex);
                    ResponseManager.printActionOnTasks("mark",
                            taskList.getPosAt(markIndex) + "\n");
                    break;

                case "unmark":
                    int unmarkIndex = MessageDecoder.decodeIndex(info);
                    taskList.unmarkTask(unmarkIndex);
                    ResponseManager.printActionOnTasks("unmark",
                            taskList.getPosAt(unmarkIndex) + "\n");
                    break;

                case "delete":
                    int deleteIndex = MessageDecoder.decodeIndex(info);
                    ResponseManager.printActionOnTasks(command,
                            taskList.getPosAt(deleteIndex) + "\n" + taskList + "\n");
                    taskList.deleteTaskAt(deleteIndex);
                    break;

                default:
                    throw new InputException(ResponseManager.COMMAND_ERROR);
                }
            } catch (InputException error) {
                ResponseManager.indentPrint(error.getMessage());
            }

        }
    }

    private void loadData() {
        try {
            this.taskList = DataManager.readSavedData();
        } catch (InputException error) {
            ResponseManager.indentPrint(error.getMessage());
        }
    }
}
