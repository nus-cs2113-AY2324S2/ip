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
                ResponseManager.listTaskToUser(taskList);
                break;

            case "todo":
                Task newTodo = new Todo(info);
                taskList.add(newTodo);
                ResponseManager.sendTaskAddedToUser(taskList);
                break;

            case "deadline":
                String[] deadlineInfo = MessageDecoder.decodeDeadline(info);
                taskList.add(new Deadline(deadlineInfo));
                ResponseManager.sendTaskAddedToUser(taskList);
                break;

            case "event":
                String[] eventInfo = MessageDecoder.decodeEvent(info);
                taskList.add(new Event(eventInfo));
                ResponseManager.sendTaskAddedToUser(taskList);
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
