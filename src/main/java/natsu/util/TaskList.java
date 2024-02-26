package natsu.util;

import natsu.task.Task;

import java.util.ArrayList;

/**
 * Manages the list of tasks in the application.
 * This class provides a static list to store tasks, allowing for global access
 * and manipulation of tasks across the application.
 */
public class TaskList {

    /**
     * A static list that holds all tasks.
     * This list is designed to be accessed from anywhere within the application
     * to perform operations on tasks such as adding, removing, and querying tasks.
     */
    public static final ArrayList<Task> list = new ArrayList<>();
}
