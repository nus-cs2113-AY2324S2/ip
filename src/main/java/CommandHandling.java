import java.util.ArrayList;
import java.util.Scanner;

public class CommandHandling {
    public static final int MAX_TASKS = 100;

    private static final int MARK_OFFSET = 4;
    private static final int UNMARK_OFFSET = 6;
    private static final int TODO_OFFSET = 4;
    private static final int EVENT_OFFSET = 5;
    private static final int DEADLINE_OFFSET = 8;
    private static final int ARRAY_START_INDEX = 0;

    public static void processInput() {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS);
        String userInput = in.nextLine();

        while (true) {
            // Convert to uppercase before processing
            String instruction = userInput.toUpperCase().split(" ")[0];

            try {
                Command command = Command.valueOf(instruction); // Use enum directly

                switch (command) {
                case BYE: {
                    return;
                }
                case MARK:
                case UNMARK: {
                    handleMarkUnmark(userInput, tasks, command);
                    break;
                }
                case LIST:
                    if (Task.enumerateTask() == ARRAY_START_INDEX) {
                        throw new CustomException(Reply.EMPTY_LIST);
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < Task.enumerateTask(); i++) {
                        System.out.println((i + 1) + ". "  + tasks.get(i));
                    }
                    break;
                case TODO: {
                    int i = Task.enumerateTask();
                    String label = userInput.substring(TODO_OFFSET).trim();
                    if (label.isEmpty()) {
                        throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
                    }
                    ToDo toDo = new ToDo(label);


                    tasks.set(i, toDo);
                    Reply.printReply(toDo, i+1);
                    break;
                }
                case EVENT: {
                    int taskCount = Task.enumerateTask();
                    String eventDetails = userInput.substring(EVENT_OFFSET).trim();
                    if (eventDetails.isEmpty()) {
                        throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
                    }
                    String[] eventParameters = Event.getInterval(eventDetails);
                    String eventLabel = eventParameters[0];
                    Event event = new Event(eventLabel, eventParameters[1], eventParameters[2]);

                    tasks.set(taskCount, event);
                    Reply.printReply(event, taskCount+1);
                    break;
                }
                case DEADLINE: {
                    int taskCount = Task.enumerateTask();
                    String deadlineDetails = userInput.substring(DEADLINE_OFFSET).trim();
                    if (deadlineDetails.isEmpty()) {
                        throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
                    }
                    String[] deadlineParameters = Deadline.getDeadline(deadlineDetails);
                    String deadlineLabel = deadlineParameters[0];
                    Deadline deadline = new Deadline(deadlineLabel, deadlineParameters[1]);

                    tasks.set(taskCount, deadline);
                    Reply.printReply(deadline, taskCount+1);
                    break;
                }
                case HELP: {
                    Reply.printHelp();
                    break;
                }
                default: {
                    throw new CustomException(Reply.INVALID_COMMAND);
                }
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



    private static void handleMarkUnmark(String userInput, ArrayList<Task> tasks, Command command) throws CustomException {
        String index = userInput.substring(command == Command.MARK ? MARK_OFFSET : UNMARK_OFFSET).trim();
        if (index.isEmpty()) {
            throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
        }

        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex < 0 || taskIndex >= Task.enumerateTask()) {
            throw new CustomException(Reply.INVALID_PARAMETER);
        }

        Task markTask = tasks.get(taskIndex);
        markTask.setCompleted(command == Command.MARK); // Use enum for logic

        if (command == Command.MARK) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("Okay, I've marked this task as not done yet:");
        }
        System.out.println(markTask);
    }

    public static void initialiseBot() {
        Reply.printWelcomeMessage();
    }
    public static void terminateBot() {
        Reply.printGoodbyeMessage();
    }
}
