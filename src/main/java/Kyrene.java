import java.util.Scanner;

public class Kyrene {
    final static String LOGO = "    _   _  _    _  ____   ____  _   _  ____\n"
            + "    | | / /\\ \\  / /|  _ \\ | ___|| \\ | || ___|\n"
            + "    | |/ /  \\ \\/ / | |_| || ===||  \\| || ===|\n"
            + "    | |\\ \\   |  |  | |\\ / | |__ | |\\  || |__\n"
            + "    |_| \\_\\  |__|  |_| \\_\\|____||_| \\_||____|   by Zhou Junmin\n";
    final static String DIVIDER = "    ⭐__________________________________________________________⭐\n";
    final static String GREETING = "    Hi, I am Kyrene, your private reminder assistant.\n"
            + "    What can I do for you?\n";
    final static String BYE = "    Bye! Wish to see you again soon!\n";
    final static String ERROR_TASK_NOT_EXIST = "    Error! This task does not exist.\n";
    final static String ERROR_MISSING_TASK = "    Error! Your task description is missing.\n    For creating tasks, please type \"[task type (todo/deadline/event)] [task description]\"([] is to be omitted).\n    For example:\n        todo have a nice day\n    or\n        deadline get a cup of coffee /by 9am\n    or\n        event celebrate birthday /from 27th Oct 1700 /to 2359\n";
    final static String ERROR_MISSING_TIME = "    Error! Your deadline/event task created is incomplete in terms of time.\n    For creating deadline task, please type \"deadline [task description] /by [time]\"([] is to be omitted).\n    For example:\n        deadline get a cup of coffee /by 9pm\n    For creating event task, please type \"event [task description] /from [starting time] /to [ending time]\"([] is to be omitted).\n    For example:\n        event celebrate birthday /from 27th Oct 1700 /to 2359\n";
    final static String ERROR_INVALID_COMMAND = "   Error! This command is invalid. Some available commands are ([] is to be omitted):\n        list\n        mark\n        unmark\n        [task type (todo/deadline/event)] [task description]\n";
    final static int MAX_ARRAY_LENGTH = 100;

    public static Task[] tasks = new Task[MAX_ARRAY_LENGTH];
    public static int taskCount = 0;

    public static void initKyrene() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    public static void exitKyrene() {
        System.out.println(BYE);
        System.out.println(DIVIDER);
    }

    public static void addTask(String sentence) throws KyreneInvalidCommandException, KyreneMissingTaskException {
        String[] words = sentence.split(" ");
        String classType = words[0];

        switch (classType) {
        case "todo":
            try {
                tasks[taskCount] = new Todo(sentence.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            }
            break;
        case "deadline":
            try {
                tasks[taskCount] = new Deadline(sentence.substring(9));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            } catch (KyreneMissingTimeException e) {
                System.out.printf("%s\n", ERROR_MISSING_TIME);
                printDivider();
                return;
            }
            break;
        case "event":
            try {
                tasks[taskCount] = new Event(sentence.substring(6));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            } catch (KyreneMissingTimeException e) {
                System.out.printf("%s\n", ERROR_MISSING_TIME);
                printDivider();
                return;
            }
            break;
        default:
            throw new KyreneInvalidCommandException();
        }

        System.out.printf("    Task has been successfully added: %s\n", tasks[taskCount].toString());
        taskCount++;
        if(taskCount == 1){
            System.out.printf("    Now you have %d task(including finished ones) in your list.\n\n", taskCount);
        }
        else{
            System.out.printf("    Now you have %d tasks(including finished ones) in your list.\n\n", taskCount);
        }
        printDivider();

    }

    public static void markTask(int taskNumber) throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new KyreneTaskNotFoundException();
        }
        tasks[taskNumber - 1].setDone(true);
        System.out.printf("    Congrats! Task %d is done!\n\n", taskNumber);
        printDivider();
    }

    public static void unmarkTask(int taskNumber) throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new KyreneTaskNotFoundException();
        }
        tasks[taskNumber - 1].setDone(false);
        System.out.printf("    Task %d is marked as not done.\n\n", taskNumber);
        printDivider();
    }

    public static void printDivider() {
        System.out.printf("%s\n", DIVIDER);
    }

    public static void printTasks() {
        System.out.println("    Here is your to-do list:");
        for(int i = 0; i < taskCount; i++){
            System.out.printf("        %d.%s\n", i + 1, tasks[i].toString());
        }
        System.out.print("\n");
    }

    public static boolean converseKyrene(String sentence) {
        boolean isEnd = false;
        printDivider();

        String[] commands = sentence.split(" ");
        String command = commands[0];
        int taskNumber = 0;

        switch(command) {
        case "bye":
            isEnd = true;
            exitKyrene();
            return isEnd;
        case "list":
            printTasks();
            printDivider();
            break;
        case "mark":
            try {
                taskNumber = Integer.parseInt(sentence.substring(5));
                markTask(taskNumber);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                System.out.println(ERROR_TASK_NOT_EXIST);
                printDivider();
            }
            break;
        case "unmark":
            try {
                taskNumber = Integer.parseInt(sentence.substring(7));
                unmarkTask(taskNumber);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                System.out.println(ERROR_TASK_NOT_EXIST);
                printDivider();
            }
            break;
        default:
            try {
                addTask(sentence);
            } catch (KyreneMissingTaskException e) {
                System.out.printf("%s\n", ERROR_MISSING_TASK);
                printDivider();
            } catch (KyreneInvalidCommandException e) {
                System.out.printf("%s\n", ERROR_INVALID_COMMAND);
                printDivider();
            }
        }

        return isEnd;
    }

    public static void main(String[] args) {
        initKyrene();
        Scanner input = new Scanner(System.in);
        String line;
        boolean exitFlag = false;
        while (!exitFlag) {
            line = input.nextLine();
            exitFlag = converseKyrene(line);
        }
    }

}
