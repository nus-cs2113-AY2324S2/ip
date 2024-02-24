package com.erii.data;

import com.erii.core.TaskManager;
import com.erii.core.TaskManager.Task;
import com.erii.user.UserDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStorage {
    private static final String TASKS_FILE = "./data/tasks.txt";
    private static final String USER_DETAILS_FILE = "./data/userDetails.txt";

    public void saveTasks(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new File(TASKS_FILE))) {
            for (Task task : tasks) {
                writer.println(taskToFileString(task));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    public List<Task> loadTasks(TaskManager taskManager) {

        List<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(TASKS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue; // Skip empty lines
                Task task = fileStringToTask(line, taskManager);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tasks file not found. Starting with an empty task list.");
        }
        return tasks;
    }

    public void saveUserDetails(UserDetails userDetails) {
        File file = new File(USER_DETAILS_FILE);
        // Ensure parent directories exist
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs(); // This will create the directory path if it doesn't exist
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(userDetails.getUserName());
            writer.println(userDetails.getUserBirthday());
            writer.println(userDetails.getUserGender());
        } catch (IOException e) {
            System.out.println("An error occurred while saving user details: " + e.getMessage());
        }
    }

    public UserDetails loadUserDetails() {
        UserDetails userDetails = new UserDetails();
        try (Scanner scanner = new Scanner(new File(USER_DETAILS_FILE))) {
            if (scanner.hasNextLine()) {
                userDetails.setUserName(scanner.nextLine());
            } else {
                System.out.println("User name not found in file.");
            }
            if (scanner.hasNextLine()) {
                userDetails.setUserBirthday(scanner.nextLine());
            } else {
                System.out.println("User birthday not found in file.");
            }
            if (scanner.hasNextLine()) {
                userDetails.setUserGender(scanner.nextLine());
            } else {
                System.out.println("User gender not found in file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("User details file not found. Starting with default user details.");
        }
        return userDetails;
    }
    

    private String taskToFileString(TaskManager.Task task) {
        String taskType = task instanceof TaskManager.Todo ? "T" : 
                          task instanceof TaskManager.Deadline ? "D" : "E";
        String status = task instanceof TaskManager.Todo && ((TaskManager.Todo) task).isDone() ? "1" : "0";
        String priority = task.getPriority().name();
        String description = task.getDescription();
        String dateInfo = "";
    
        if (task instanceof TaskManager.Deadline) {
            dateInfo = "|" + ((TaskManager.Deadline) task).getBy();
        } else if (task instanceof TaskManager.Event) {
            dateInfo = "|" + ((TaskManager.Event) task).getStart() + "|" + ((TaskManager.Event) task).getEnd();
        }
    
        return String.join("|", taskType, status, priority, description) + dateInfo;
    }
    
    

    private TaskManager.Task fileStringToTask(String line, TaskManager taskManager) {
        String[] parts = line.split("\\|");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        TaskManager.Priority priority = TaskManager.Priority.valueOf(parts[2]);
        String description = parts[3];
    
        switch (taskType) {
            case "T":
                TaskManager.Todo todo = taskManager.new Todo("Todo", description, priority);
                todo.setDone(isDone);
                return todo;
            case "D":
                String byDate = parts[4];
                TaskManager.Deadline deadline = taskManager.new Deadline("Deadline", description, byDate, priority);
                deadline.setDone(isDone);
                return deadline;
            case "E":
                String startDate = parts[4];
                String endDate = parts[5];
                TaskManager.Event event = taskManager.new Event("Event", description, startDate, endDate, priority);
                event.setDone(isDone);
                return event;
            default:
                return null; // or throw an exception if the task type is unrecognized
        }
    }
    
    
}
