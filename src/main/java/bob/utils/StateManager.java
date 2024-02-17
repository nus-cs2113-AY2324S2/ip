package bob.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StateManager {
    private static final String DATA_STATE_FILEPATH = "data/state.txt";

    public static void saveState(TaskManager taskManager) throws IOException {
        List<List<String>> tokenizedTasks = taskManager.tokenizeTasks();
        StringBuilder tasks = new StringBuilder();

        for (List<String> taskTokens : tokenizedTasks) {
            for (String token : taskTokens) {
                tasks.append(String.format("%s | ", token));
            }
            tasks.append("\n");
        }

        StateManager.write(tasks.toString());
    }

    private static void write(String input) throws IOException {
        File f = new File(DATA_STATE_FILEPATH);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(f);
        fw.write(input);
        fw.close();
    }

    public static TaskManager loadState() throws IOException {
        File f = new File(DATA_STATE_FILEPATH);

        if (f.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            return StateManager.loadTasksFromStream(br.lines());
        }

        return new TaskManager();
    }

    private static TaskManager loadTasksFromStream(Stream<String> taskStream) {
        // Process Stream<String> into List<List<String>>; tokenize inputs
        List<List<String>> result = taskStream.map(x -> Arrays
                .stream(x.split("\\|"))
                .map(String::strip)
                .collect(Collectors.toList())
        ).collect(Collectors.toList());

        TaskManager taskManager = new TaskManager();
        int currentTaskId = 1;

        for (List<String> tokenizedTask : result) {
            String taskType = tokenizedTask.get(0);
            String taskCompletionStatus = tokenizedTask.get(1);
            String taskName = tokenizedTask.get(2);

            switch (taskType) {
            case "T":
                taskManager.addTodo(taskName);
                break;
            case "D":
                String taskDueDate = tokenizedTask.get(3);
                taskManager.addDeadline(taskName, taskDueDate);
                break;
            case "E":
                String taskStartDate = tokenizedTask.get(3);
                String taskEndDate = tokenizedTask.get(4);
                taskManager.addEvent(taskName, taskStartDate, taskEndDate);
                break;
            }

            if (taskCompletionStatus.equals("1")) {
                taskManager.updateTaskProgress(currentTaskId, Command.MARK);
            }

            currentTaskId++;
        }

        return taskManager;
    }
}
