import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Katleen {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static final String LINE = "____________________________________________________________";
   private static final String filename = "./saved.txt";
   private static final Path filePath = Paths.get(filename);
   private static void loadFileContents() {
       try {
           File file = filePath.toFile();
           if (!file.exists()) {
               System.out.println("Savefile not found, creating new file");
               file = new File("./" + filename);
               System.out.println("File created at: " + file.getAbsolutePath());
           } else {
               Scanner s = new Scanner(filePath);
               int temp = 0;
               while (s.hasNext()) {
                   String savedTask = s.nextLine();
                   String[] save = parseCommand(savedTask);
                   String[] splitSaved = save[0].split(" ");
                   Task saved = getTask(savedTask, splitSaved[0], splitSaved, false);
                   tasks.add(saved);
                   if (!save[1].isEmpty()) {
                       tasks.get(temp).setDone(true);
                   }
                   temp++;
               }
               }
       } catch (IncompleteTaskException e) {
           System.out.println("Could not create new task");
       } catch (IOException e) {
           System.out.println("IOException");
       }
   }

    public static void main(String[] args) throws IncompleteTaskException, UnrecognizedCommandException {
        System.out.println(LINE);
        System.out.println("Hello! I'm Katleen.");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
        loadFileContents();
        Scanner in = new Scanner(System.in);
        String text = "";
        while (!text.equals("bye")) {
            text = in.nextLine();
            System.out.println(LINE);
            parseString(text);
            System.out.println(LINE);
        }
    }

    private static void parseString(String input) {
        String[] splitInput = input.split(" ");
        String cmdWord = splitInput[0];
        int taskCount = tasks.size();
        try {
            switch (cmdWord) {
            case "list":
                if (tasks.isEmpty()) {
                    System.out.println("You have no tasks!");
                    break;
                }
                System.out.println("Here are the tasks in your list:");
                int count = 1;
                for (Task t : tasks) {
                    System.out.print(count + ". ");
                    t.printTask();
                    count++;
                }
                break;
            case "bye":
                for (Task t : tasks) {
                    FileWriter fw = new FileWriter(filename);
                    fw.write(t.toString());
                }
                System.out.println(LINE);
                System.out.println("Bye, have a nice day!");
                System.out.println(LINE);
                break;
            case "mark":
            case "unmark":
            case "delete":
                String index = splitInput[1];
                int i = Integer.parseInt(index) - 1;
                if (cmdWord.equals("delete")) {
                    Task temp = tasks.get(i);
                    tasks.remove(i);
                    System.out.println("Task deleted: " + temp);
                    break;
                }
                if (cmdWord.equals("mark")) {
                    tasks.get(i).setDone(true);
                    break;
                }
                tasks.get(i).setDone(false);
                break;
            case "todo":
            case "deadline":
            case "event":
                Task task = getTask(input, cmdWord, splitInput, true);
                if (task == null) {
                    throw new UnrecognizedCommandException();
                }
                tasks.add(task);
                tasks.set(taskCount, task);
                tasks.get(taskCount).printTask();
                break;
            default:
                throw new UnrecognizedCommandException();
            }
        } catch (IncompleteTaskException e) {
            System.out.println("Excuse me, you're missing the deadline or duration.");
        } catch (UnrecognizedCommandException e) {
            System.out.println("Invalid command, please try again");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Task getTask(String input, String cmdWord, String[] splitInput, boolean saveToFile) throws IncompleteTaskException {
        if (!cmdWord.equals("todo") && splitInput.length < 3) {
            throw new IncompleteTaskException();
        }
        try {
            FileWriter fw = new FileWriter(filename, true);
            switch (cmdWord) {
            case "todo":
                ToDo todo = new ToDo(input.replace("todo ", "").trim());
                if (saveToFile) {
                    System.out.println("Added: ");
                    fw.write(todo.toString()+ "\n");
                    fw.close();
                } else {
                    System.out.println("Restored: ");
                    todo.printTask();
                }
                return todo;
            case "deadline":
                int due = input.indexOf("/by");
                String by = input.substring(due + 3).trim();
                String deadline = input.substring(8, due).trim();
                Deadline dead = new Deadline(deadline, by);
                if (saveToFile) {
                    System.out.println("Added: ");
                    fw.write(dead.toString() + "\n");
                    fw.close();
                } else {
                    System.out.println("Restored: ");
                    dead.printTask();
                }
                return dead;
            case "event":
                int splitFrom = input.indexOf("/from");
                int splitTo = input.indexOf("/to");
                String from = input.substring(splitFrom + 5, splitTo).trim();
                String to = input.substring(splitTo + 3).trim();
                String desc = input.substring(5, splitFrom).trim();
                Event event = new Event(desc, from, to);
                if (saveToFile) {
                    System.out.println("Added: ");
                    fw.write(event.toString() + "\n");
                    fw.close();
                } else {
                    System.out.println("Restored: ");
                    event.printTask();
                }
                return event;
            }
        } catch (IOException e) {
            System.out.println("There was an error writing to the file.");
        }
        return null;
    }

    private static String[] parseCommand(String input) {
       String[] response = {"", ""};
       String task = input.substring(6).trim();
       char taskType = input.charAt(1);
       switch (taskType) {
       case 'T':
           response[0] = "todo " + task;
           break;
       case 'D':
           task = task.substring(0, task.indexOf("by"));
           String by = " /" + task.substring(task.indexOf("by")).trim();
           response[0] = "deadline " + task + by;
       case 'E':
           task = task.substring(0, task.indexOf("from"));
           String from = " /" + task.substring(task.indexOf("from"), task.indexOf("to")).trim();
           String to = " /" + task.substring(task.indexOf("to"));
           response[0] = "event" + task + from + to;
        }
       char done = input.charAt(4);
       if (done == 'X') {
           response[1] = ("mark");
       }
       return response;
    }
}
