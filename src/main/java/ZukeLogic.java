import java.util.Scanner;

public class ZukeLogic {
    private String receivedMessage;
    private final TaskList taskList;

    ZukeLogic() {
        receivedMessage = "";
        taskList = new TaskList();
    }

    public void chattingStart() {
        Scanner userInput = new Scanner(System.in);

        while (userInput.hasNextLine()) {
            receivedMessage = userInput.nextLine();
            String[] processedMessage = MessageDecoder.separateCommand(receivedMessage);
            String command = processedMessage[0];
            String info = processedMessage.length == 1 ?
                    "" : processedMessage[1];

            switch(command) {
            case "bye":
                ResponseManager.sayGoodbye();
                userInput.close();
                return;

            case "list":
                ResponseManager.listTaskToUser(taskList.listTasks());
                break;

            case "todo":
                Task newTodo = new Todo(info);
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

            case "mark" :
                String markIndexStr =
                        MessageDecoder.removePrefixMark(receivedMessage, "mark").trim();

                if (!markIndexStr.matches("\\d+") ||
                        Integer.parseInt(markIndexStr) > taskList.getSize()) {
                    ResponseManager.printErrorMessage("mark");
                    break;
                }

                int markIndex = Integer.parseInt(markIndexStr);
                taskList.markTask(markIndex);
                ResponseManager.printMarkOrUnMarkTask("mark",
                        taskList.getPosAt(markIndex).toString());
                break;

            case "unmark" :
                String unmarkIndexStr =
                        MessageDecoder.removePrefixMark(receivedMessage, "unmark").trim();

                if (!unmarkIndexStr.matches("\\d+") ||
                        Integer.parseInt(unmarkIndexStr) > taskList.getSize()) {
                    ResponseManager.printErrorMessage("unmark");
                    break;
                }

                int unmarkIndex = Integer.parseInt(unmarkIndexStr);
                taskList.unmarkTask(unmarkIndex);
                ResponseManager.printMarkOrUnMarkTask("unmark",
                        taskList.getPosAt(unmarkIndex).toString());
                break;

            default:
                ResponseManager.printErrorMessage("error");
                break;
            }
        }
    }
}
