import java.util.Scanner;
import java.util.ArrayList;

public class CommandHandling {
    private static final int MARK_OFFSET = 4;
    private static final int UNMARK_OFFSET = 6;
    private static final int TODO_OFFSET = 4;
    private static final int EVENT_OFFSET = 5;
    private static final int DEADLINE_OFFSET = 8;
    private static final int DELETE_OFFSET = 6;
    private static final int ARRAY_START_INDEX = 0;

    public static void processInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (true) {
            // Convert to uppercase before processing
            String instruction = userInput.toUpperCase().split(" ")[0];

            try {
                Command command = Command.valueOf(instruction);

                switch (command) {
                case BYE: {
                    return;
                }
                case MARK:
                case UNMARK: {
                    handleMarkUnmark(userInput, command);
                    break;
                }
                case LIST:
                    if (List.getTotal() == ARRAY_START_INDEX) {
                        throw new CustomException(Reply.EMPTY_LIST);
                    }
                    List.printTasks();
                    break;
                case TODO: {
                    int taskCount = List.getTotal();
                    String label = userInput.substring(TODO_OFFSET).trim();
                    if (label.isEmpty()) {
                        throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
                    }
                    ToDo toDo = new ToDo(label);


                    List.addTask(toDo);
                    Reply.printReply(toDo, taskCount+1);
                    break;
                }
                case EVENT: {
                    int taskCount = List.getTotal();
                    String eventDetails = userInput.substring(EVENT_OFFSET).trim();
                    if (eventDetails.isEmpty()) {
                        throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
                    }
                    String[] eventParameters = Event.getInterval(eventDetails);
                    String eventLabel = eventParameters[0];
                    Event event = new Event(eventLabel, eventParameters[1], eventParameters[2]);

                    List.addTask(event);
                    Reply.printReply(event, taskCount+1);
                    break;
                }
                case DEADLINE: {
                    int taskCount = List.getTotal();
                    String deadlineDetails = userInput.substring(DEADLINE_OFFSET).trim();
                    if (deadlineDetails.isEmpty()) {
                        throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
                    }
                    String[] deadlineParameters = Deadline.getDeadline(deadlineDetails);
                    String deadlineLabel = deadlineParameters[0];
                    Deadline deadline = new Deadline(deadlineLabel, deadlineParameters[1]);

                    List.addTask(deadline);
                    Reply.printReply(deadline, taskCount+1);
                    break;
                }
                case HELP: {
                    Reply.printHelp();
                    break;
                }
                case DELETE: {
                    deleteItem(List.tasks, userInput);
                    break;
                }
                default:
                    throw new CustomException(Reply.INVALID_COMMAND);
                }
            } catch (IllegalArgumentException e) {
                Reply.printInvalidCommand();
            } catch (CustomException e) {
                Reply.printException(e);
            }

            Reply.printLine();
            userInput = in.nextLine();
        }
    }



    private static void handleMarkUnmark(String userInput, Command command) throws CustomException {
        String index = userInput.substring(command == Command.MARK ? MARK_OFFSET : UNMARK_OFFSET).trim();
        if (index.isEmpty()) {
            throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
        }

        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex < 0 || taskIndex >= List.getTotal()) {
            throw new CustomException(Reply.INVALID_PARAMETER);
        }

        Task markTask = List.tasks.get(taskIndex);
        markTask.setCompleted(command == Command.MARK); // Use enum for logic

        if (command == Command.MARK) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("Okay, I've marked this task as not done yet:");
        }
        System.out.println(markTask);
    }
    public static void deleteItem(ArrayList<Task> tasks, String userInput) throws CustomException {
        String index = userInput.substring(DELETE_OFFSET).trim();
        if (index.isEmpty()) {
            throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
        }

        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= List.getTotal()) {
                throw new CustomException(Reply.INVALID_PARAMETER);
            }

            Reply.printReply("Deleted: " + (taskIndex + 1) + tasks.get(taskIndex));
            tasks.remove(taskIndex);

        }
        catch (NumberFormatException e) {
            throw new CustomException(Reply.INVALID_PARAMETER);
        }
    }

    public static void initialiseBot() {
        Reply.printWelcomeMessage();
    }
    public static void terminateBot() {
        Reply.printGoodbyeMessage();
    }
}
