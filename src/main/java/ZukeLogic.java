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
                String markIndex = MessageDecoder.removePrefixMark(receivedMessage, "mark").trim();
                if (markIndex.matches("\\d+") &&
                        Integer.parseInt(markIndex) <= taskList.getSize()) {
                    taskList.markTask(Integer.parseInt(markIndex));
                } else {
                    ResponseManager.printErrorMessage("mark");
                }
                break;

            case "unmark" :
                String unmarkIndex = MessageDecoder.removePrefixMark(receivedMessage, "unmark").trim();
                if (unmarkIndex.matches("\\d+") &&
                        Integer.parseInt(unmarkIndex) <= taskList.getSize()) {
                    taskList.unmarkTask(Integer.parseInt(unmarkIndex));
                } else {
                    ResponseManager.printErrorMessage("unmark");
                }
                break;

            default:
                ResponseManager.printErrorMessage("error");
                break;
            }
        }
    }
}
