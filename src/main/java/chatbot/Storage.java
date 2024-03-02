package chatbot;

import chatbot.commands.Command;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "data/chatbot.txt";
    public TaskList readFile() throws FileNotFoundException, ChatbotException {
        TaskList taskList = new TaskList();
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        String dataLine = "";
        while(s.hasNext()) {
            dataLine = s.nextLine();
            String[] dataArray = dataLine.split("@");
            if (dataArray.length != 3) {
                throw new ChatbotException("Text file formatting error. Fix it and try again. ");
            }

            boolean isMarked = dataArray[0].equals("true");
            String command = dataArray[1];
            String description = dataArray[2];
            Command newCommand = Parser.readCommand(taskList, command + " " + description);
            newCommand.execute(true);
        }
        return taskList;
    }
    public static void writeFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(".\\data\\chatbot.txt");
        for (Task task : taskList.getTasks()) {
            fileWriter.write(task.isDone() + "@" + task.getTaskName() + "@" + task.getCommand() + "\n");
        }
        fileWriter.close();

    }
}
