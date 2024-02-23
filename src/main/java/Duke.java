import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();

    public static int taskLength = 0;
    public static final String NEW_LINE = "____________________________________________________________\n";
    public static final String FILE_PATH = "duke.txt";

    //Create a new task
    public static void createTask(String taskType, String task, boolean quietLoad) {
        switch (taskType) {
        case "todo":
            list.add(new Todo(task));
            break;
        case "deadline":
            list.add(new Deadline(task));
            break;
        default:
            list.add(new Event(task));
        }
        taskLength++;
        if (!quietLoad) {
            System.out.println(NEW_LINE + "Okay, I've added: " + task + "\n" + NEW_LINE);
        }
    }

    public static void deleteTask(int taskNumber) {
        Task task = list.get(taskNumber);
        list.remove(taskNumber);
        taskLength--;
        try {
            saveFile();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(NEW_LINE + "Okay, I've removed: " + task.description + "\n" + NEW_LINE);
    }

    //Handle different types of tasks
    public static void handleTasks(String input) throws EmptyTaskException {
        int index = input.indexOf(" ");
        String taskType = input.substring(0, index);
        String taskContent = input.substring(index);
        String taskFinal;

        switch (taskType) {
        case "deadline":
            try {
                //Empty deadline exception
                String deadlineContent = taskContent.split("/by ")[0];
                if (deadlineContent.trim().isEmpty()) {
                    throw new EmptyTaskException();
                } else {
                    String deadlineTiming = taskContent.split("/by ")[1];
                    taskFinal = taskContent.split("/")[0] + " (by: " + deadlineTiming + ")";
                    createTask(taskType, taskFinal, false);
                    saveFile();
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot parse deadline start date!\n"
                + "Format: deadline [task] /by [time]");
            }
            catch (IOException e) {
                System.out.println("Error saving file" + e.getMessage());
            }
            break;

        case "event":
            try {
                //empty event exception
                String eventContent = taskContent.split("/from ")[0];
                if (eventContent.trim().isEmpty()) {
                    throw new EmptyTaskException();
                } else {
                    String eventTiming = taskContent.split("/from ")[1];
                    String eventFrom = eventTiming.split("/to ")[0];
                    String eventTo = eventTiming.split("/to ")[1];
                    taskFinal = taskContent.split("/")[0] + " (from: " + eventFrom + " to: " + eventTo + ")";
                    createTask(taskType, taskFinal, false);
                    saveFile();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot parse event start/end date!\n"
                +  "Format: event [task] /from [start time] /to [end time]");
            } catch (IOException e) {
                System.out.println("Error saving file" + e.getMessage());
            }
            break;

        //case for tod0
        default:
            if (taskContent.trim().isEmpty()) {
                throw new EmptyTaskException();
            } else {
                try {
                    createTask(taskType, taskContent, false);
                    saveFile();
                } catch (IOException e) {
                    System.out.println("Error saving file" + e.getMessage());
                }
            }
        }
    }

    //show current tasks
    public static void showTasks() {
        System.out.println(NEW_LINE);
        System.out.println("Your current tasks");
        for (int i = 0; i < taskLength; i++){
            System.out.print((i + 1) + ". ");
            System.out.println(list.get(i).getTask());
        }
        System.out.print(NEW_LINE);
    }

    public static void mark(int taskNumber) throws DuplicateMarkException {
        if (list.get(taskNumber - 1).isDone) {
            throw new DuplicateMarkException();
        } else {
            list.get(taskNumber - 1).markAsDone();
            System.out.println("Good job! I've marked task " + taskNumber + " as done");
        }
    }

    public static void unMark(int taskNumber){
        System.out.println("I've marked task " + taskNumber + " as not done");
        list.get(taskNumber - 1).markNotDone();
    }

    // Save file when creating/deleting tasks
    public static void saveFile() throws IOException {
        File myFile = new File(FILE_PATH);
        FileWriter fw = new FileWriter(myFile, false);
        BufferedWriter bw = new BufferedWriter(fw);
        String postFormat = "";
        for (Task item : list) {
            String preFormat = item.getTask();
            String taskType = preFormat.split("]")[0];
            String isDone = item.isDone ? "1" : "0";

            if (taskType.equals("[T")) {
                postFormat = "T | " + isDone + " | " + item.description;
            } else if (taskType.equals("[D")) {
                postFormat = "D | " + isDone + " | " + item.description.split("\\(")[0] + " | " + item.getTime();
            } else {
                postFormat = "E | " + isDone + " | " + item.description.split("\\(")[0] + " | " + item.getTime();
            }
            bw.write(postFormat);
            bw.newLine();
        }
        bw.close();
    }

    // Load text file to local list
    private static void loadFile() throws FileNotFoundException {
        File f = new File(Duke.FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String taskType = line.split(" \\| ")[0];
            String taskDone = line.split(" \\| ")[1];
            String taskContent = line.split(" \\| ")[2];
            //type of task
            if (taskType.equals("T")) {
                createTask("todo", taskContent, true);
            } else if (taskType.equals("D")) {
                String taskTiming = line.split(" \\| ")[3];
                createTask("deadline", taskContent + "(by: " + taskTiming + ")", true);
            } else {
                String taskTiming = line.split(" \\| ")[3];
                String taskFrom = taskTiming.split("to")[0];
                String taskTo = taskTiming.split("to")[1];
                createTask("event", taskContent + "(from: " + taskFrom + "to" + taskTo + ")", true);
            }
        }
    }

    public static void main(String[] args) {
        //Load file at the start
        System.out.println("Welcome...loading previous data...");
        try {
            loadFile();
            System.out.println("Done :D");
        } catch (FileNotFoundException e) {
            System.out.println("No file found to load!");
        }

        String chat_name = "Sigma";
        String output = NEW_LINE + "Hello! I'm " + chat_name + "\n"
                + "What can I do for you?\n" + NEW_LINE;
        System.out.println(output);

        //Takes user inputs
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                showTasks();
            } else if (input.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    mark(taskNumber);
                }
                catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                    System.out.println("That task does not exist!");
                }
                catch (DuplicateMarkException e) {
                    System.out.println("Task is already marked!");
                }
                catch (Exception e){
                    System.out.println("Error marking task :/");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    unMark(taskNumber);
                }
                catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                    System.out.println("That task does not exist!");
                }
                catch (Exception e){
                    System.out.println("Error marking task :/");
                }
            } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                try {
                    handleTasks(input);
                }
                catch (EmptyTaskException e) {
                    System.out.println("Task cannot be empty!");
                }
            } else if (input.startsWith("remove ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                try{
                    deleteTask(taskNumber - 1);
                }
                //catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                //    System.out.println("That task does not exist!");
                //}
                catch (Exception e){
                    System.out.println("Error removing task");
                }
            } else {
                System.out.println(NEW_LINE + "Sorry, I don't recognise that input\n"
                                    + "Hint: Use todo/event/deadline [task] to list tasks\n"
                                    + "OR Use mark/unmark/remove [task number] to edit tasks\n"
                                    + NEW_LINE );
            }
        }
        System.out.println(NEW_LINE + "Bye. Hope to see you again soon!\n" + NEW_LINE);
    }
}

