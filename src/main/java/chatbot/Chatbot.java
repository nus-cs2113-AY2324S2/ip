package chatbot;

import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Chatbot {
    private final String CHATBOT_NAME;
    private final ArrayList<Task> taskList = new ArrayList<>();
    private int listLength = 0;
    private boolean isExit = false;
    private boolean isReading = false;
    String input;
    Task selectedItem;
    Scanner in = new Scanner(System.in);

    public Chatbot(String name) {
        this.CHATBOT_NAME = name;
    }
    public void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(".\\data\\chatbot.txt");
        for (int i = 0; i < listLength; i += 1) {
            Task task = taskList.get(i);
            fileWriter.write(task.isDone() + "@" + task.getTaskName() + "@" + task.getCommand() + "\n");
        }
        fileWriter.close();
    }
    public void read() throws FileNotFoundException, ChatbotException {
        this.isReading = true;
        File f = new File(".\\data\\chatbot.txt");
        Scanner s = new Scanner(f);
        String dataLine = "";
        while (s.hasNext()) {
            dataLine = s.nextLine();
            String[] dataArray = dataLine.split("@");
            if (dataArray.length != 3) {
                throw new ChatbotException("Text file formatting error. Fix it and try again. ");
            }
            boolean isMarked = dataArray[0].equals("true");
            String command = dataArray[1];
            String description = dataArray[2];

            this.execute(command, description);
            if (isMarked) {
                selectedItem = taskList.get(listLength - 1);
                selectedItem.markAsDone();
            }
        }
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
            break;
        case "mark":
            System.out.println("Not as incompetent as I thought. Marked. ");
            break;
        case "delete":
            System.out.println("Deleted. ");
            break;
        case "todo":
        case "deadline":
        case "event":
            System.out.println("Got it. Don't worry, I won't forget. ");
            break;
        case "help":
            System.out.println("Want to know what I can do? \n" +
                    "I can add 'todo', 'deadline', and 'event' tasks. \n" +
                    "Or 'list', 'mark', 'unmark', and 'delete' tasks. \n" +
                    "Also, type 'bye' to exit. I suggest this one. ");
            return;
        default:
            break;
        }
        printTask(selectedItem);
    }

    public void initiate() {
        System.out.println("Hello. You may call me " + CHATBOT_NAME + ". \n" +
                "Looks like you need me to remember things for you. Again. ");
        try {
            read();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ChatbotException e) {
            e.printDescription();
        }
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
                printTask(taskList.get(i));
            }
            break;
        case "unmark":
            inputNum = new Scanner(input).useDelimiter("\\D+").nextInt();
            if (inputNum > listLength) {
                throw new ChatbotException("You only have " + listLength + " task(s). Choose one of them. ");
            }
            selectedItem = taskList.get(inputNum - 1);
            selectedItem.markAsNotDone();
            if (!isReading) {
                printResponse(command);
            }
            break;
        case "mark":
            inputNum = new Scanner(input).useDelimiter("\\D+").nextInt();
            if (inputNum > listLength) {
                throw new ChatbotException("You only have " + listLength + " task(s). Choose one of them. ");
            }
            selectedItem = taskList.get(inputNum - 1);
            selectedItem.markAsDone();
            if (!isReading) {
                printResponse(command);
            }
            break;
        case "delete":
            inputNum = new Scanner(input).useDelimiter("\\D+").nextInt();
            if (inputNum > listLength) {
                throw new ChatbotException("You only have " + listLength + " task(s). Choose one of them. ");
            }
            selectedItem = taskList.get(inputNum - 1);
            if (!isReading) {
                printResponse(command);
            }
            taskList.remove(inputNum - 1);
            listLength -= 1;
            break;
        case "todo":
            selectedItem = new Todo(description);
            taskList.add(selectedItem);
            listLength += 1;
            if (!isReading) {
                printResponse(command);
                printLength();
            }
            break;
        case "deadline":
            selectedItem = new Deadline(description);
            taskList.add(selectedItem);
            listLength += 1;
            if (!isReading) {
                printResponse(command);
                printLength();
            }
            break;
        case "event":
            selectedItem = new Event(description);
            taskList.add(selectedItem);
            listLength += 1;
            if (!isReading) {
                printResponse(command);
                printLength();
            }
            break;
        case "help":
            printResponse(command);
            break;
        default:
            throw new ChatbotException("I don't know what that is. Type 'help' for help.");
        }
    }

    public void run() {
        this.isReading = false;
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
