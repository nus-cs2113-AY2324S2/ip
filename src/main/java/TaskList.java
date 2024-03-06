import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskList {
    private Task[] list;
    private int count;

    public TaskList(Task[] list, int count) {
        this.list = list;
        this.count = count;
    }

    public void initTasks(String filePath) {
        loadTasksFromFile(filePath);
    }

    public void addTask(String description) {
        if (count == list.length) {
            list = Arrays.copyOf(list, count * 2);
        }
        list[count] = new Task(description);
        count++;
        saveTasksToFile("C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt");
        System.out.println("____________________________________________________________");
        System.out.println("Added: " + description);
        System.out.println("____________________________________________________________");
    }
    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > count) {
            System.out.println("Task with specified number does not exist.");
            return;
        }

        // Adjust the index since taskNumber is 1-based and our array is 0-based
        int index = taskNumber - 1;

        System.out.println(Echo.break_line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list[index]);
        System.out.println(Echo.break_line);

        // Shift tasks one position to the left from the index of the removed task
        for (int i = index; i < count - 1; i++) {
            list[i] = list[i + 1];
        }

        list[count - 1] = null; // Help the garbage collector
        count--; // Decrease the count of tasks
        saveTasksToFile("C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt");
    }


    public void addTodo(String description) {
        if (description.isEmpty()) {
            System.out.println("ToDo requires a description.");
        } else {
            ToDo newToDo = new ToDo(description);
            if (count == list.length) {
                list = Arrays.copyOf(list, count * 2);
            }
            list[count] = new Task(description);
            this.list[count].markAsToDo();
            count++;
            saveTasksToFile("C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt");

            System.out.println(Echo.break_line);
            System.out.println("Got it. I've added this Event:");
            System.out.println(newToDo);
            System.out.println("Now you have " + this.count + " tasks in the list.");
            System.out.println(Echo.break_line);
        }
    }

    public void addDeadline(String description) {
        String[] deadlineParts = description.split(" /by ", 2);
        if (count == list.length) {
            list = Arrays.copyOf(list, count * 2);
        }
        Deadline newDDL = new Deadline(deadlineParts[0], deadlineParts[1]);

        list[count] = new Deadline(deadlineParts[0], deadlineParts[1]);
        this.list[count].markAsDDL();
        count++;
        saveTasksToFile("C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt");

        System.out.println(Echo.break_line);
        System.out.println("Got it. I've added this Event:");
        System.out.println(newDDL);
        System.out.println("Now you have " + this.count + " tasks in the list.");
        System.out.println(Echo.break_line);
    }


    public void addEvent(String description) {
        String[] eventParts = description.split(" /from ", 2);
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (count == list.length) {
            list = Arrays.copyOf(list, count * 2);
        }

        list[count] = new Event(eventParts[0], timeParts[0], timeParts[1]);
        this.list[count].markAsEvent();
        count++;
        saveTasksToFile("C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt");

        System.out.println(Echo.break_line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E][ ] " + eventParts[0] + " (from: " + timeParts[0] + " to: " + timeParts[1] + ")");
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(Echo.break_line);


    }


    public void clearList() {
        // Re-initialize the list with its initial capacity
        list = new Task[list.length]; // or a specific initial capacity, like new Task[10];
        count = 0; // Reset the count to 0
        clearFileContents("C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt");
        System.out.println(Echo.break_line);
        System.out.println("All tasks have been cleared.");
        System.out.println(Echo.break_line);
    }

    public void listTasks() {
        System.out.println(Echo.break_line);
        System.out.println("Here are your tasks in your list:");

        for (int i = 0; i < this.count; i++) {
            Task task = this.list[i];
            // This line will automatically call the overridden toString() method
            // of each Task object, which includes ToDo tasks with their [T] prefix.
            System.out.println(" " + (i + 1) +".["+task.getTypeIcon()+"]"+ "[" + task.getStatusIcon() + "]" + task);
        }
        System.out.println(Echo.break_line);

    }

    public void markTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= count) {
            this.list[taskNumber - 1].markAsDone();
            System.out.println(Echo.break_line);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [X] " + this.list[taskNumber - 1]);
        } else {
            System.out.println(Echo.break_line);
            System.out.println("Error: Task number " + taskNumber + " does not exist in the list.");
        }
        System.out.println(Echo.break_line);
        saveTasksToFile("C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt");
    }

    public void unmarkTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= count) {
            this.list[taskNumber - 1].unmarkAsDone();
            System.out.println(Echo.break_line);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + this.list[taskNumber - 1]);
        } else {
            System.out.println(Echo.break_line);
            System.out.println("Error: Task number " + taskNumber + " does not exist in the list.");
        }
        System.out.println(Echo.break_line);
        saveTasksToFile("C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt");
    }

    public void saveTasksToFile(String filePath) {
        if(count == 0){
            return;
        }
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // This will create the directory.
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : list) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            //throw new HikoExceptions("An error occurred while trying to save tasks to file, Hiko is working on it now!", e);
            System.out.println("An error occurred while trying to save tasks to file.");
            e.printStackTrace();
        }
    }

    public void loadTasksFromFile(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs(); // This will create the directory along with any necessary parent directories.
        }
        if (!file.exists()) {
            return; // Exit the method as there's nothing to load
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                // Assuming you have a method to parse the line into a Task and add it to your list
                parseAndAddTask(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to load tasks from file.");
            e.printStackTrace();
        }
    }

    private void parseAndAddTask(String taskString) {
        String[] parts = taskString.split(" \\| ");
        switch (parts[0]) {
        case "T":
            addTodo("todo " + parts[2]); // You might need to adjust how you construct tasks here
            break;
        case "D":
            addDeadline("deadline " + parts[2] + " /by " + parts[3]);
            break;
        case "E":
            addEvent("event " + parts[2] + " /from " + parts[3]); // Adjust according to your actual format
            break;
        }
    }

    public static void clearFileContents(String filePath) {
        try {
            // Initialize FileWriter with the append flag set to false
            FileWriter writer = new FileWriter(filePath, false);
            writer.close(); // Closing the writer without writing clears the file
        } catch (IOException e) {
            System.out.println("An error occurred while trying to clear the file.");
            e.printStackTrace();
        }
    }

}
