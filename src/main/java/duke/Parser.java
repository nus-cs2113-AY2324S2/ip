package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Parser {
    private ArrayList<Task> parsedTaskList = new ArrayList<>(2);
    private ArrayList<String> unparsedTaskList = new ArrayList<>(2);

    public void trySaveList() {
        try {
            saveList();
        } catch (IOException e) {
            System.out.println("savedList not found, creating now");
        }
    }

    public void saveList() throws IOException {
        FileWriter fileInput = new FileWriter("./savedList.txt");
        for (int i = 0; i < unparsedTaskList.size(); i++) {
            fileInput.write(unparsedTaskList.get(i) + "\n");
        }
        fileInput.close();
    }

    public void tryRetrieveList() {
        try {
            retrieveList();
        }
        catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    public void retrieveList() throws FileNotFoundException {
        File f = new File("./savedList.txt");
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            Command.directAddTask(in.nextLine(), parsedTaskList, unparsedTaskList);
        }
    }

    public void parseInput(String input) throws PythiaException {
        if (input.equalsIgnoreCase("list")) {
            Command.list(parsedTaskList);
        }
        else if (isTaskCommand(input)) {
            Command.tryAddTask(input, parsedTaskList, unparsedTaskList);
        }
        else if (input.contains("unmark ")) {
            Command.unmark(input, parsedTaskList);
        }
        else if (input.contains("mark ")) {
            Command.mark(input, parsedTaskList);
        }
        else if (input.contains("delete ")) {
            Command.delete(input, parsedTaskList, unparsedTaskList);
        }
        else {
            throw new PythiaException();
        }
        trySaveList();
        System.out.println(MoodSprite.getLineBreak());
    }
    public String[] splitTaskTrimmer(String[] splitTask) {
        for (int i = 0; i < splitTask.length; i++) {
            splitTask[i] = splitTask[i].trim();
        }
        return splitTask;
    }
    public String[] parseDeadline(String task) {
        task = task.replaceFirst("deadline", "");
        return splitTaskTrimmer(task.split("/"));
    }

    public String[] parseEvent(String task) {
        task = task.replaceFirst("event", "");
        return splitTaskTrimmer((task.split("/")));
    }

    public boolean isTaskCommand(String task) {
        return (task.contains("todo") | task.contains("deadline") | task.contains("event"));
    }
}
