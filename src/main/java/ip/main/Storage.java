package ip.main;

import ip.task.Deadline;
import ip.task.Event;
import ip.task.Task;
import ip.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private File file;
    private Ui ui;

    public Storage(String filePath, Ui ui) throws IOException {
        this.ui = ui;
        file = new File(filePath);

        if (! file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (! file.exists()) {
            file.createNewFile();
        }
    }

    public void readStoredData(TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] dataLine = str.split(" :: ");
            try {
                boolean isDone = (Integer.parseInt(dataLine[1]) == 1);

                switch (dataLine[0]) {
                case "T":
                    tasks.add(new Todo(isDone, dataLine[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(isDone, dataLine[2], dataLine[3]));
                    break;
                case "E":
                    tasks.add(new Event(isDone, dataLine[2], dataLine[3], dataLine[4]));
                    break;
                default:
                    ui.printWithoutLeadingSpace("I have no idea what this is: " + str);
                }
            } catch (IndexOutOfBoundsException e) {
                ui.printWithoutLeadingSpace("Missing information: " + str);
            }
        }
    }

    public void updateStoredData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("./data/task_list.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task taskToWrite = tasks.get(i);
                String isDoneString = taskToWrite.getDone() ? "1" : "0";
                String description = taskToWrite.getDescription();

                if (Todo.class.isInstance(taskToWrite)) {
                    fw.write("T :: " + isDoneString + " :: " + description + "\n");
                } else if (Deadline.class.isInstance(taskToWrite)) {
                    Deadline deadlineToWrite = (Deadline) taskToWrite;
                    fw.write("D :: " + isDoneString + " :: " + description
                            + " :: " + deadlineToWrite.getBy() + "\n");
                } else {
                    Event eventToWrite = (Event) taskToWrite;
                    fw.write("E :: " + isDoneString + " :: " + description + " :: "
                            + eventToWrite.getFrom() + " :: " + eventToWrite.getTo() + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            ui.printWithoutLeadingSpace("Unable to update data file!");
        }
    }
}
