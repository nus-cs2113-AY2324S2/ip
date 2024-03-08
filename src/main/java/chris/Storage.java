package chris;


import chris.tasktypes.Deadline;
import chris.tasktypes.Event;
import chris.tasktypes.ToDo;
import chris.tasktypes.taskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the file at filePath and returns a taskList object of tasks that were stored.
     * @return taskList object of tasks
     */
    public taskList loadTasks() {
        taskList tasks = new taskList();
        File f = new File(filePath);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            return tasks;
        }
        while (s.hasNext()) {
            String[] taskDescription = s.nextLine().split("\\|");
            switch (taskDescription[0]) {
                case "T":
                    tasks.addTask(new ToDo(new String[] {taskDescription[2]}, taskDescription[1].equals("X")));
                    break;
                case "D":
                    tasks.addTask(new Deadline(new String[]{taskDescription[2], taskDescription[3]}, taskDescription[1].equals("X")));
                    break;
                case "E":
                    tasks.addTask(new Event(new String[]{taskDescription[2], taskDescription[3], taskDescription[4]}, taskDescription[1].equals("X")));
                    break;
            }
        }
        s.close();
        return tasks;
    }

    /**
     * Saves all tasks from the taskList into the file at filePath
     * @param tasks taskList of tasks after Chris has been run.
     */
    public void saveTasks(taskList tasks){
        try {
            FileWriter f = new FileWriter(filePath);
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                f.write(tasks.getTask(i).saveString());
                f.write(System.lineSeparator());
            }
            f.close();
        } catch (IOException e) {
            System.out.println("Sorry, I was unable to save you tasks!");
        }
    }

}

