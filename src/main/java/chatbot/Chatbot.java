package chatbot;

import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Chatbot {
    private final String CHATBOT_NAME;
    private final Task[] taskList = new Task[100];
    private int listLength = 0;
    private boolean isExit = false;
    String input;
    Task selectedItem;
    Scanner in = new Scanner(System.in);

    public Chatbot(String name) {
        this.CHATBOT_NAME = name;
    }
    public void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("./data/chatbot.txt");
        for (int i = 0; i < listLength; i += 1) {
            fileWriter.write(taskList[i].getData() + "\n");
        }
        fileWriter.close();
    }
    public void save() {
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    public static void printTask(Task task) {
        System.out.println(task.getData());
    }

    public void printLength() {
        System.out.println("You have " + this.listLength + " tasks in the list. ");
    }

    public void printResponse(String command) {
        switch (command) {
        case "bye":
            System.out.println("Bye. Hope not to see you again! ");
            return;
        case "list":
            if (this.listLength > 0) {
                System.out.println("Here are the tasks in your list: ");
            } else {
                System.out.println("There's nothing here. ");
            }
            return;
        case "unmark":
            System.out.println("Failing to meet expectations, are we? Unmarked. ");
            return;
        case "mark":
            System.out.println("Not as incompetent as I thought. Marked. ");
            return;
        case "todo":
        case "deadline":
        case "event":
            System.out.println("Got it. Don't worry, I won't forget. ");
            return;
        default:
        }
    }

    public void initiate() {
        System.out.println("Hello. You may call me " + CHATBOT_NAME + ". ");
        System.out.println("Looks like you need me to remember things for you. Again. ");
    }

    public void execute(String command, String description) throws ChatbotException {
        int inputNum;
        switch (command) {
        case "bye":
            printResponse(command);
            this.isExit = true;
            break;
        case "list":
            printResponse(command);
            for (int i = 0; i < listLength; i += 1) {
                System.out.print((i + 1) + ". ");
                printTask(taskList[i]);
            }
            break;
        case "unmark":
            inputNum = new Scanner(input).useDelimiter("\\D+").nextInt();
            if (inputNum > listLength) {
                throw new ChatbotException("You only have " + listLength + " task(s). Choose one of them. ");
            }
            selectedItem = taskList[inputNum - 1];
            selectedItem.markAsNotDone();
            printResponse(command);
            printTask(selectedItem);
            break;
        case "mark":
            inputNum = new Scanner(input).useDelimiter("\\D+").nextInt();
            if (inputNum > listLength) {
                throw new ChatbotException("You only have " + listLength + " task(s). Choose one of them. ");
            }
            selectedItem = taskList[inputNum - 1];
            selectedItem.markAsDone();
            printResponse(command);
            printTask(selectedItem);
            break;
        case "todo":
            taskList[listLength] = new Todo(description);
            printResponse(command);
            printTask(taskList[listLength]);
            listLength += 1;
            printLength();
            break;
        case "deadline":
            taskList[listLength] = new Deadline(description);
            printResponse(command);
            printTask(taskList[listLength]);
            listLength += 1;
            printLength();
            break;
        case "event":
            taskList[listLength] = new Event(description);
            printResponse(command);
            printTask(taskList[listLength]);
            listLength += 1;
            printLength();
            break;
        default:
            throw new ChatbotException("I'm not omnipotent, you know? \n" +
                    "I can mark, unmark, and list items, or add a todo, deadline, or event. \n" +
                    "Or may I recommend using bye and thinking for yourself? ");
        }
    }

    public void run() {
        do {
            try {
                System.out.println("-------------------");
                //input
                input = in.nextLine();
                String[] inputArray = input.split(" ", 2);
                String command = inputArray[0];
                String description = "";
                boolean isAddingTask = (Objects.equals(command, "todo") ||
                        Objects.equals(command, "deadline") || Objects.equals(command, "event"));

                if (!isAddingTask) {
                    this.execute(command, description);
                    continue;
                }

                if (inputArray.length == 1) {
                    throw new ChatbotException("Have you forgotten your task already? ");
                } else {
                    description = input.split(" ", 2)[1];
                    this.execute(command, description);
                }

            } catch (ChatbotException e) {
                e.printDescription();
            }
        } while (!this.isExit);
    }
}
