import exceptions.KikuEmptyTaskException;
import exceptions.KikuException;
import exceptions.KikuInvalidTaskException;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class KikuBot {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String HORIZONTAL = "____________________________________________________________";
    private static final String BOT_NAME = "Kiku";
    private static final String FILE_NAME = "./data/Kiku.txt";

    private static void saveTasks() {
        try {
            File file = new File(FILE_NAME);
            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_NAME);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found. Starting with an empty list.");
            new File(file.getParent()).mkdirs();
        }
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;

        if ("T".equals(parts[0]) && parts.length >= 3) {
            task = new Todo(parts[2]);
        } else if ("D".equals(parts[0]) && parts.length >= 4) {
            task = new Deadline(parts[2], parts[3]);
        } else if ("E".equals(parts[0]) && parts.length >= 4) {
            task = new Event(parts[1], parts[2], parts[3]);
        }

        if (task != null && "1".equals(parts[1])) {
            task.markAsDone();
        }
        return task;
    }

    private static int markIndex(String userInput, int num) {
        String markIndexChar = userInput.substring(num, userInput.length());
        return Integer.parseInt(markIndexChar) - 1;
    }

    private static Task addTask(String userInput) throws KikuException {
        int n = userInput.length();
        if (userInput.startsWith("todo")) {
            if (userInput.trim().length() == 4) {
                throw new KikuEmptyTaskException();
            }

            String description = userInput.substring(5, n);
            return new Todo(description);

        } else if (userInput.startsWith("deadline")) {
            if (userInput.trim().length() == 8) {
                throw new KikuEmptyTaskException();
            }

            String description = userInput.substring(9, n);
            if(!description.contains("/by")) {
                throw new KikuInvalidTaskException("Oh no! Please specify the deadline!");
            } else if (description.trim().startsWith("/by")) {
                throw new KikuInvalidTaskException("Oh no! Please specify the description!");
            }

            String[] deadline = description.split(" /by ");
            return new Deadline(deadline[0], deadline[1]);

        } else if (userInput.startsWith("event")) {
            if (userInput.trim().length() == 5) {
                throw new KikuEmptyTaskException();
            }

            String description = userInput.substring(6, n);
            if(!description.contains("/from") || !description.contains("/to")) {
                throw new KikuInvalidTaskException("Oh no! " +
                        "Check that you've included the description, start and end time!");
            } else if (description.trim().startsWith("/from")) {
                throw new KikuInvalidTaskException("Oh no! Please specify the description!");
            }

            String[] start = description.split(" /from ");
            String[] end = start[1].split(" /to ");
            return new Event(start[0], end[0], end[1]);

        } else {
            throw new KikuException("Oh no! Please specify a todo, deadline, or event! " +
                    "Make sure that all spellings are correct!");
        }
        //return task;
    }
    public static void main(String[] args) {
        //load saved tasks
        loadTasks();
        System.out.println(HORIZONTAL);

        //greetings
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL);

        //add task
        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = in.nextLine();

        //check for exit word
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (userInput.startsWith("mark")) {
                int taskIndex = markIndex(userInput, 5);
                tasks.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskIndex));

            } else if (userInput.startsWith("unmark")) {
                int taskIndex = markIndex(userInput, 7);
                tasks.get(taskIndex).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskIndex));

            } else if (userInput.startsWith("delete")) {
                int taskIndex = markIndex(userInput, 7);
                System.out.println("Alright, I've removed this task: ");
                System.out.println(tasks.get(taskIndex));
                tasks.remove(taskIndex);
                System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");

            } else {
                try {
                    Task newTask = addTask(userInput);
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
                    saveTasks();

                } catch (KikuEmptyTaskException e) {
                    System.out.println(e.getMessage());
                } catch (KikuInvalidTaskException e) {
                    System.out.println(e.getMessage());
                } catch (KikuException e) {
                    System.out.println(e.getMessage());
                }

            }
            System.out.println(HORIZONTAL);
            userInput = in.nextLine();
        }

        //exit
        if (userInput.equals("bye")) {
            saveTasks();
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL);
        }
    }
}