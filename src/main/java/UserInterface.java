import java.util.Scanner;

public class UserInterface {
    public static final Scanner scan = new Scanner(System.in);
    private static String username;

    public static void printBanner() {
        String logo = "  __ _ _ __| |_ ___ _ __ ___ (_)___" + System.lineSeparator() +
                " / _` | '__| __/ _ \\ '_ ` _ \\| / __|" + System.lineSeparator() +
                "| (_| | |  | ||  __/ | | | | | \\__ \\"  + System.lineSeparator() +
                " \\__,_|_|   \\__\\___|_| |_| |_|_|___/" + System.lineSeparator();
        System.out.println("======================================" + System.lineSeparator());
        System.out.println(logo);
    }

    public static void printGoodbye() {
        System.out.println("======================================");
        System.out.println("goodbye! hope to see you again soon!");
        System.out.println("======================================");
        scan.close();
    }

    public static void requestUsername() {
        System.out.println("hello! i'm artemis. what is your name?");
        System.out.print("[unknown user]: ");
        username = scan.nextLine();
    }

    public static void printList() {
        if (TaskHandler.listCount == 0) {
            System.out.println("[artemis]: Your list is empty!");
            return;
        }

        System.out.println("[artemis]: Your list is as such:");
        for (int index = 0; index < TaskHandler.listCount; index++) {
            Task currentTask = TaskHandler.taskList[index];
            System.out.printf("%d. %s\n", index+1, currentTask);
        }
    }

    public static void printHelp() {
        System.out.printf("welcome to your personal list, %s!%s", username, System.lineSeparator());
        System.out.println("usage: todo [item]");
        System.out.println("       deadline [item] /by [due date]");
        System.out.println("       event [item] /from [start] /to [end]");
        System.out.println("you may also enter \"list\" to see your current list.");
        System.out.println("you may also exit by entering \"bye\".");

    }

    public static void commandLine() {
        String userInput;
        Task currentTask;

        while (true) {
            System.out.printf("[%s]: ", username);
            userInput = scan.nextLine().trim();
            Parser.Command commandGiven = Parser.parseCommand(userInput);

             try {
                 switch (commandGiven) {
                 case TODO:
                     currentTask = TaskHandler.createToDo(userInput);
                     break;
                 case DEADLINE:
                     currentTask = TaskHandler.createDeadline(userInput);
                     break;
                 case EVENT:
                     currentTask = TaskHandler.createEvent(userInput);
                     break;
                 case LIST:
                     printList();
                     continue;
                 case MARK:
                 case UNMARK:
                    Object[] parsedContent = Parser.parseMarkUnmark(userInput);
                    int markItem = (int) parsedContent[0];
                    boolean isMarked = (boolean) parsedContent[1];
                    TaskHandler.markUnmarkItem(markItem, isMarked);

                    continue;
                 case BYE:
                     printGoodbye();
                     return;
                 case UNKNOWN:
                 default:
                     System.out.println("[artemis]: unknown command!");
                     continue;
                 }

                TaskHandler.taskList[TaskHandler.listCount] = currentTask;
                TaskHandler.listCount++;

                System.out.printf("[artemis]: you have added this task:%s %s%s",
                        System.lineSeparator(), currentTask, System.lineSeparator());

             } catch (Errors.InvalidTodoException e) {
                 System.out.println("[artemis]: invalid todo given. please enter \"todo <task>\"");
             } catch (Errors.InvalidDeadlineException e) {
                 System.out.println("[artemis]: invalid deadline given. please enter \"deadline <task> /by <deadline of task>\"");
             } catch (Errors.InvalidEventException e) {
                 System.out.println("[artemis]: invalid event given. please enter \"event <event title> /from <start datetime> /to <end datetime>\"");
             } catch (Errors.InvalidMarkUnmarkException e) {
                 System.out.println("[artemis]: please enter \"[mark/unmark] <list item number>\".");
             } catch (Errors.InvalidMarkUnmarkIndexException e) {
                 System.out.println("[artemis]: the given list item number given is invalid!");
             }
        }
    }
}
