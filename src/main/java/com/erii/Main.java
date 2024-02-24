package com.erii;

import com.erii.user.UserDetails;
import java.util.List;
import com.erii.core.Erii;
import com.erii.core.TaskManager;
import com.erii.data.DataStorage;
import com.erii.ui.ControlPanel;

public class Main {
    public static void main(String[] args) {
        // Prompt the user to enter their details
        DataStorage storage = new DataStorage();
        TaskManager taskManager = new TaskManager();
        UserDetails userDetails = storage.loadUserDetails();
        ControlPanel controlPanel = new ControlPanel(taskManager, storage, userDetails);

        Erii.main(args);

        // Check if userDetails are loaded correctly, otherwise prompt the user
        if (userDetails == null || userDetails.getUserName() == null || userDetails.getUserName().isEmpty()) {
            userDetails = new UserDetails();
            // Assuming UserDetails class has methods to prompt user for details
            userDetails.inputName();
            userDetails.inputBirthday();
            userDetails.inputGender();
            // Save the new user details
            storage.saveUserDetails(userDetails);
            // Print the user details with a welcome message
            String message = String.format(
                "Verification passed.\nOptions enabled.\n%s Born on %s\n%s\nID A.D.0013\n" +
                    "Rank 'S'\nListed in the Kassel Academy roster.\n" +
                    "Database access granted\nAccount activated\nCourse schedule generated\n" +
                    "I am Erii, the secretary of Kassel Academy, pleased to serve you.",
                    userDetails.getUserName(), userDetails.getUserBirthday(), userDetails.getUserGender());
            System.out.println(message);
        } else {
            String message = "Welcome back, " + userDetails.getUserName();
            System.out.println(message);
        }

        // Call control panel to start the task manager
        // Load tasks from storage
        List<TaskManager.Task> loadedTasks = storage.loadTasks(taskManager);
        // Assuming TaskManager has a method to add these loaded tasks
        for (TaskManager.Task task : loadedTasks) {
            taskManager.addTask(task); // Or another appropriate method to set the tasks directly
        }

        // Load user details from storage

        controlPanel.start();
    }
}