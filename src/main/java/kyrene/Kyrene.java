package kyrene;

import kyrene.task.*;
import kyrene.exception.*;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    final static String FILE_PATH = "./data/Kyrene.txt";
    final static String FOLDER_PATH = "./data/";
    final static String ERROR_WRITE_TO_FILE_FAILED = "    Error! Write to file failed.\n";
    final static String ERROR_FOLDER_CREATION_FAILED = "    Error! Folder creation failed.\n";
    final static String ERROR_FILE_CREATION_FAILED = "    Error! File creation failed.\n";
    final static String ERROR_FILE_CORRUPTED = "    Error! File loaded is corrupted.\n";
    final static String ERROR_TASK_NOT_EXIST = "    Error! This task does not exist.\n";
    final static String ERROR_MISSING_TASK = "    Error! Your task description is missing.\n    For creating tasks, please type \"[task type (todo/deadline/event)] [task description]\"([] is to be omitted).\n    For example:\n        todo have a nice day\n    or\n        deadline get a cup of coffee /by 9am\n    or\n        event celebrate birthday /from 27th Oct 1700 /to 2359\n";
    final static String ERROR_MISSING_TIME = "    Error! Your deadline/event task created is incomplete in terms of time.\n    For creating deadline task, please type \"deadline [task description] /by [time]\"([] is to be omitted).\n    For example:\n        deadline get a cup of coffee /by 9pm\n    For creating event task, please type \"event [task description] /from [starting time] /to [ending time]\"([] is to be omitted).\n    For example:\n        event celebrate birthday /from 27th Oct 1700 /to 2359\n";
    final static String ERROR_INVALID_COMMAND = "    Error! This command is invalid. Some available commands are ([] is to be omitted):\n        list\n        mark\n        unmark\n        [task type (todo/deadline/event)] [task description]\n        bye\n";

    public static ArrayList<Task> tasks = new ArrayList<>();
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

    public static void addTask(String sentence, boolean whetherWriteToFile) throws KyreneInvalidCommandException, KyreneMissingTaskException {
        String[] words = sentence.split(" ");
        String classType = words[0];

        switch (classType) {
        case "todo":
            try {
                tasks.add(new Todo(sentence.substring(5)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            }
            break;
        case "deadline":
            try {
                tasks.add(new Deadline(sentence.substring(9)));
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
                tasks.add(new Event(sentence.substring(6)));
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

        int taskCount = tasks.size();
        System.out.printf("    Task has been successfully added: %s\n", tasks.get(taskCount - 1).toString());
        printTaskCount();

        if (whetherWriteToFile) {
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.printf("%s\n", ERROR_WRITE_TO_FILE_FAILED);
                printDivider();
            }
        }

        if(taskCount == 1){
            System.out.printf("    Now you have %d task(including finished ones) in your list.\n\n", taskCount);
        }
        else{
            System.out.printf("    Now you have %d tasks(including finished ones) in your list.\n\n", taskCount);
        }
        printDivider();
    }

    public static void markTask(int taskNumber) throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new KyreneTaskNotFoundException();
        }
        tasks.get(taskNumber - 1).setDone(true);

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.printf("%s\n", ERROR_WRITE_TO_FILE_FAILED);
            printDivider();
        }

        System.out.printf("    Congrats! Task %d is done!\n\n", taskNumber);
        printDivider();
    }

    public static void unmarkTask(int taskNumber) throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new KyreneTaskNotFoundException();
        }
        tasks.get(taskNumber - 1).setDone(false);

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.printf("%s\n", ERROR_WRITE_TO_FILE_FAILED);
            printDivider();
        }

        System.out.printf("    Task %d is marked as not done.\n\n", taskNumber);
        printDivider();
    }

    public static void deleteTask(int taskNumber) throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new KyreneTaskNotFoundException();
        }
        Task taskDeleted = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.printf("%s\n", ERROR_WRITE_TO_FILE_FAILED);
            printDivider();
        }

        System.out.printf("    Sure! I have successfully deleted this task from your list:\n        %s\n", taskDeleted.toString());
        printTaskCount();
        printDivider();
    }

    public static void printDivider() {
        System.out.printf("%s\n", DIVIDER);
    }

    public static void printTaskCount() {
        taskCount = tasks.size();
        if(taskCount < 2){
            System.out.printf("    Now you have %d task(including finished ones) in your list.\n\n", taskCount);
        }
        else{
            System.out.printf("    Now you have %d tasks(including finished ones) in your list.\n\n", taskCount);
        }
    }

    public static void printTasks() {
        System.out.println("    Here is your to-do list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.printf("        %d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.print("\n");
    }

    public static boolean converseKyrene(String sentence) {
        boolean isEnd = false;
        printDivider();

        String[] commands = sentence.split(" ");
        String command = commands[0];
        int taskNumber;

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
                taskNumber = Integer.parseInt(commands[1]);
                markTask(taskNumber);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                System.out.println(ERROR_TASK_NOT_EXIST);
                printDivider();
            }
            break;
        case "unmark":
            try {
                taskNumber = Integer.parseInt(commands[1]);
                unmarkTask(taskNumber);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                System.out.println(ERROR_TASK_NOT_EXIST);
                printDivider();
            }
            break;
        case "delete":
            try {
                taskNumber = Integer.parseInt(commands[1]);
                deleteTask(taskNumber);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                System.out.println(ERROR_TASK_NOT_EXIST);
                printDivider();
            }
            break;
        default:
            try {
                addTask(sentence, true);
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

    public static void loadFileToKyrene() throws FileNotFoundException {
        System.out.printf("    Loading file %s ...\n", FILE_PATH);
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        int lineNumber = 1;
        while (s.hasNext()) {
            String line = s.nextLine();
            boolean isDone = line.startsWith("true");
            String task;
            if (isDone) {
                task = line.substring("true ".length());
            } else {
                task = line.substring("false ".length());
            }
            try {
                addTask(task, false);
                tasks.get(tasks.size() - 1).setDone(isDone);
            } catch (KyreneMissingTaskException | KyreneInvalidCommandException e) {
                System.out.printf("%s    Error occurs at line %d.\n", ERROR_FILE_CORRUPTED, lineNumber);
                printDivider();
            }
            lineNumber ++;
        }
        System.out.printf("    File %s has been loaded.\n", FILE_PATH);
    }

    public static void writeToFile() throws IOException {
        FileWriter clearWriter = new FileWriter(FILE_PATH);
        clearWriter.write("");
        clearWriter.close();

        FileWriter fw = new FileWriter(FILE_PATH, true);
        for (Task task : tasks) {
            fw.write(task.format());
        }
        fw.close();
    }

    public static void main(String[] args) {
        try {
            loadFileToKyrene();
        } catch (FileNotFoundException e) {
            System.out.printf("    File %s is not found.\n", FILE_PATH);
            Path folderPath = Paths.get(FOLDER_PATH);
            Path filePath = Paths.get(FILE_PATH);
            if (Files.exists(folderPath)) {
                try {
                    Files.createFile(filePath);
                } catch (IOException ex) {
                    System.out.printf("%s\n", ERROR_FILE_CREATION_FAILED);
                    return;
                }
            } else {
                System.out.printf("    Folder %s is not found.\n", FOLDER_PATH);
                try {
                    Files.createDirectory(folderPath);
                    Files.createFile(filePath);
                } catch (IOException ex) {
                    System.out.printf("%s\n", ERROR_FOLDER_CREATION_FAILED);
                    return;
                }
                System.out.printf("    Folder %s is successfully created.\n", FOLDER_PATH);
            }
            System.out.printf("    File %s is successfully created.\n", FILE_PATH);
        }
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
