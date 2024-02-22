import exceptions.KikuEmptyTaskException;
import exceptions.KikuException;
import exceptions.KikuInvalidTaskException;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class KikuBot {

    private static void saveTasks(Task[] tasks, int taskCount) {
        try {
            FileWriter writer = new FileWriter("./data/Kiku.txt");
            for (int i = 0; i < taskCount; i++) {
                if (tasks[i] != null) {
                    writer.write(tasks[i].toFileFormat() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    private static int loadTasks(Task[] tasks) {
        int taskCount = 0;
        try {
            File file = new File("./data/Kiku.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine() && taskCount < tasks.length) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks[taskCount++] = task;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found. Starting with an empty list.");
        }
        return taskCount; // Return the number of tasks loaded
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;
        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        }
        if (task != null && parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private static int markIndex(String userInput, int num) {
        String markIndexChar = userInput.substring(num, userInput.length());
        return Integer.parseInt(markIndexChar) - 1;
    }

    private static Task addTask(String userInput) throws KikuException {
        Task task;
        int n = userInput.length();
        if (userInput.startsWith("todo")) {
            if (userInput.trim().length() == 4) {
                throw new KikuEmptyTaskException();
            }

            String description = userInput.substring(5, n);
            task = new Todo(description);

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
            task = new Deadline(deadline[0], deadline[1]);

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
            task = new Event(start[0], end[0], end[1]);

        } else {
            throw new KikuException("Oh no! Please specify a todo, deadline, or event! " +
                    "Make sure that all spellings are correct!");
        }
        return task;
    }
    public static void main(String[] args) {
        //greetings
        String HORIZONTAL = "____________________________________________________________";
        String BOT_NAME = "Kiku";
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL);

        //echo
        //add task
        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = in.nextLine();

        //store in array
        Task[] tasks = new Task[100];
        int taskCount = loadTasks(tasks);
        int indexTask = 0;
        int markIndexInt;

        //check for exit word
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < indexTask; i++) {
                    //when printing toString() method used automatically
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (userInput.startsWith("mark")) {
                markIndexInt = markIndex(userInput, 5);
                tasks[markIndexInt].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[markIndexInt]);
            } else if (userInput.startsWith("unmark")) {
                markIndexInt = markIndex(userInput, 7);
                tasks[markIndexInt].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[markIndexInt]);
            } else {
                try {
                    tasks[indexTask] = addTask(userInput);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[indexTask]);
                    System.out.println("Now you have " + (indexTask + 1) + " tasks in the list.");
                    indexTask++;
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
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL);
        }
    }
}