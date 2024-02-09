public class ConditionHandlers {
    public static void listIsNotEmpty(Task[] taskList, int taskCounter) {
        Messages.startOfListMessage();
        for (int i = 0; i < taskCounter; i += 1) {
            switch (taskList[i].getTaskType()) {
                case "T":
                    Messages.todoListMessage(i, taskList[i].getTaskType(),
                            taskList[i].getStatusIcon(), taskList[i].getDescription());
                    break;
                case "D":
                    Messages.deadlineListMessage(i, taskList[i].getTaskType(),
                            taskList[i].getStatusIcon(), taskList[i].getDescription(),
                            taskList[i].getEndDate());
                    break;
                case "E":
                    Messages.eventListMessage(i, taskList[i].getTaskType(),
                            taskList[i].getStatusIcon(), taskList[i].getDescription(),
                            taskList[i].getStartDate(), taskList[i].getEndDate());
                    break;
            }
        }
        Messages.printVerticalLines();
    }
    public static void markTask(String receivedMessage, Task[] taskList) {
        String number = "";
        for (int j = 0; j < receivedMessage.length(); j += 1) { // reads number from input and store it in String number
            if (Character.isDigit(receivedMessage.charAt(j))) {
                number += receivedMessage.charAt(j);
            }
        }
        if (number.isEmpty()) {
            Messages.errorMessage();
            return;
        } else {
            taskList[Integer.parseInt(number) - 1].markAsDone();
        }
        Messages.markOrUnmarkTaskMessage(taskList[Integer.parseInt(number) - 1].getTaskType(),
                taskList[Integer.parseInt(number) - 1].getStatusIcon(),
                taskList[Integer.parseInt(number) - 1].getDescription(), "complete");
    }
    public static void unmarkTask(String receivedMessage, Task[] taskList){
        String number = "";
        for (int j = 0; j < receivedMessage.length(); j += 1) { // reads number from input and store it in String number
            if (Character.isDigit(receivedMessage.charAt(j))) {
                number += receivedMessage.charAt(j);
            }
        }
        if (number.isEmpty()) {
            Messages.errorMessage();
            return;
        } else {
            taskList[Integer.parseInt(number) - 1].markAsUndone();
        }
        Messages.markOrUnmarkTaskMessage(taskList[Integer.parseInt(number) - 1].getTaskType(),
                taskList[Integer.parseInt(number) - 1].getStatusIcon(),
                taskList[Integer.parseInt(number) - 1].getDescription(), "incomplete");
    }
    public static int addTodoTaskToList(String receivedMessage, Task[] taskList, int taskCounter) {
        String[] splittedMessage = receivedMessage.split("todo ");
        taskList[taskCounter] = new Task(splittedMessage[1]);
        taskList[taskCounter].setTaskType("todo");
        taskCounter += 1;
        Messages.addTodoMessage(taskList[taskCounter - 1].getTaskType(),
                taskList[taskCounter - 1].getStatusIcon(), taskList[taskCounter - 1].getDescription(),
                taskCounter);
        return taskCounter;
    }
    public static int addDeadlineTaskToList(String receivedMessage, Task[] taskList, int taskCounter) {
        String[] splittedMessage = receivedMessage.split("deadline ");
        String[] doubleSplittedMessage = splittedMessage[1].split(" /by ");
        taskList[taskCounter] = new Task(doubleSplittedMessage[0]);
        taskList[taskCounter].setTaskType("deadline");
        taskList[taskCounter].setEndDate(doubleSplittedMessage[1]);
        taskCounter += 1;
        Messages.addDeadlineMessage(taskList[taskCounter - 1].getTaskType(),
                taskList[taskCounter - 1].getStatusIcon(), taskList[taskCounter - 1].getDescription(),
                taskList[taskCounter - 1].getEndDate(), taskCounter);
        return taskCounter;
    }
    public static int addEventTaskToList(String receivedMessage, Task[] taskList, int taskCounter){
        String[] splittedMessage = receivedMessage.split("event ");
        String[] doubleSplittedMessage = splittedMessage[1].split(" /from ");
        String[] tripleSplittedMessage = doubleSplittedMessage[1].split(" /to ");
        taskList[taskCounter] = new Task(doubleSplittedMessage[0]);
        taskList[taskCounter].setTaskType("event");
        taskList[taskCounter].setStartDate(tripleSplittedMessage[0]);
        taskList[taskCounter].setEndDate(tripleSplittedMessage[1]);
        taskCounter += 1;
        Messages.addEventMessage(taskList[taskCounter - 1].getTaskType(),
                taskList[taskCounter - 1].getStatusIcon(), taskList[taskCounter - 1].getDescription(),
                taskList[taskCounter - 1].getStartDate(), taskList[taskCounter - 1].getEndDate(),
                taskCounter);
        return taskCounter;
    }
}
