package oley.commands;
import oley.tasks.*;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Oley {
    public static Task[] tasks = new Task[100];
    public static int taskNumber = 0;

    public static void appendToFile (String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void changeFile (String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks[0].format() + System.lineSeparator());
        fw.close();
        for (int i = 1; i < taskNumber; i++) {
            appendToFile(filePath, tasks[i].format());
        }
    }

    public static void addTask(String sentence, boolean isBegin) throws InputNotRecognizedException {
        if (sentence.startsWith("deadline")) {
            try {
                tasks[taskNumber] = new Deadline(sentence.substring(9));
            } catch (TimingNotFoundException e) {
                System.out.println("OOPS, we have encountered an error!");
                System.out.println("A specific deadline would be better for you to complete your task on time! ( •̀ .̫ •́ )✧");
                System.out.println("You may use /by to indicate the time.");
                return;
            }
        } else if (sentence.startsWith("todo")) {
            tasks[taskNumber] = new Todo(sentence.substring(5));
        } else if (sentence.startsWith("event")) {
            try {
                tasks[taskNumber] = new Event(sentence.substring(6));
            } catch (TimingNotFoundException e) {
                System.out.println("OOPS, we have encountered an error!");
                System.out.println("A specific timing of the event would be clearer! ( •̀ .̫ •́ )✧");
                System.out.println("You may use /from and /to to indicate the starting and ending time.");
                return;
            }
        }
        if (!(sentence.startsWith("deadline") || sentence.startsWith("todo") || sentence.startsWith("event"))) {
            throw new InputNotRecognizedException();
        }
        taskNumber++;
        if (!isBegin) {
            String file = "./data/Oley.txt";
            try {
                appendToFile(file, tasks[taskNumber - 1].format());
            } catch (IOException e) {
                System.out.println("    OOPS, we have encountered an error!");
                System.out.println("    Write to file failed.");
            }
            System.out.println("    " + "added: " + tasks[taskNumber - 1].getTaskName());
            if (taskNumber <= 1) {
                System.out.println("    Now you have " + taskNumber + " task in the list.");
            } else {
                System.out.println("    Now you have " + taskNumber + " tasks in the list.");
            }
        }

    }

    public static void initialise() {
        String logo = "  _____  __       \n"
                + " /  _  \\|  | ____ ___  ___ \n"
                + "|  | |  |  |/ ___ \\  \\/  /\n"
                + "|  |_|  |  |  ____/\\    /\n"
                + " \\_____/|__|\\_____|/   /\n"
                + "                  /___/";
        System.out.println("Greetings from\n" + logo);
        lineBreaker();
        System.out.println("    Hello, I'm your cute and lovely friend Oley.");
        System.out.println("    What can I do for you?");
        lineBreaker();

        String file = "./data/Oley.txt";
        Scanner s = null;
        try {
            File f = new File(file);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("    OOPS, the file does not exist! I will now create one for you~ (๑･∀･๑)");
            lineBreaker();
            Path folder = Paths.get("./data/");
            Path filePath = Paths.get(file);
            try {
                Files.createDirectory(folder);
                Files.createFile(filePath);
            } catch (IOException ex) {
                System.out.println("    Sorry! I am unable to create file for you.(・ε・｀)");
            }
            return;
        }
        while (s.hasNext()) {
            String line = s.nextLine();
            String isDone = line.substring(0,1);
            String task = line.substring(1);
            try {
                addTask(task, true);
            } catch (InputNotRecognizedException e) {
                System.out.println("    OOPS, the file seems to be corrupted!");
            }
            if (isDone.equals("1")) {
                mark("mark " + taskNumber, true);
            }
        }
    }

    public static void exit() {
        System.out.println("    Bye~ Feel free to talk to me anytime. I will always be here waiting for you. ฅʕ•̫͡•");
    }

    public static void lineBreaker() {
        System.out.println(" ");
        System.out.println("    🌙~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~🌙");
    }

    public static void printTask() {
        System.out.println("    Here are the tasks in your list:");
        int i = 0;
        while (i < taskNumber) {
            System.out.println("    " + (i + 1) + "." + tasks[i].toString());
            i++;
        }
    }

    public static void mark(String sentence, boolean isBegin) {
        String[] markInstructions = sentence.split(" ");
        int toBeMarked = Integer.parseInt(markInstructions[1]) - 1;
        if (toBeMarked >= taskNumber) {
            System.out.println("You have not created Task " + (toBeMarked + 1) + " yet. Jiayous. I will always support you. ฅ •ﻌ•♡");
        } else if (markInstructions[0].equals("mark")) {
            tasks[toBeMarked].setDone();
            if (!isBegin) {
                System.out.println("    Good job! I've marked this task as done:");
                System.out.println("    " + tasks[toBeMarked]);
            }
        } else if (markInstructions[0].equals("unmark")) {
            tasks[toBeMarked].setNotDone();
            if (!isBegin) {
                System.out.println("    Sure~ I've marked this task as not done yet:");
                System.out.println("    " + tasks[toBeMarked]);
            }
        }

        if (!isBegin) {
            String file = "./data/Oley.txt";
            try {
                changeFile(file);
            } catch (IOException e) {
                System.out.println("    OOPS, we have encountered an error!");
                System.out.println("    Write to file failed.");
            }
        }
    }

    public static void instructions() {
        Scanner in = new Scanner(System.in);
        String message;
        message = in.nextLine();
        lineBreaker();
        while (message != null) {
            if (message.equals("bye")) {
                exit();
                return;
            } else if (message.equals("list")) {
                printTask();
                lineBreaker();
            } else if (message.contains("unmark") || message.contains("mark")) {
                mark(message, false);
                lineBreaker();
            } else {
                try {
                    addTask(message, false);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS, we have encountered an error!");
                    System.out.println("The description of a task cannot be empty! (๑•́ ₃•̀๑)");
                } catch (InputNotRecognizedException e) {
                    System.out.println("So sorry, I do not understand the commands. I will try to improve!! (ง •̀_•́)ง");
                    System.out.println("Meanwhile, you can use todo, deadline or event to indicate the type of tasks.");
                }
                lineBreaker();
            }
            message = in.nextLine();
            lineBreaker();
        }
    }

    public static void main(String[] args) {
        initialise();
        instructions();
    }
}
