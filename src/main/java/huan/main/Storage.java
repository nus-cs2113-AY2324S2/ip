package huan.main;

import huan.task.DeadlineTask;
import huan.task.EventTask;
import huan.task.Task;
import huan.task.TodoTask;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class Storage {
    private static String taskFile = "tasklist.txt";

    public static void processLine(String line) throws Exception{
        String[] words = line.split(" ");
        String suffixWord = line.substring(words[0].length() + 1);

        if (words[0].length() != 2 || (words[0].charAt(1) != 'T' && words[0].charAt(1) != 'F')) {
            throw new Exception();
        }

        switch (words[0].charAt(0)) {
        case ('T'):
            TaskList.addTask(Parser.parseTodoTask(suffixWord, words[0].charAt(1) == 'T'));
            break;
        case ('D'):
            TaskList.addTask(Parser.parseDeadlineTask(suffixWord, words[0].charAt(1) == 'T'));
            break;
        case ('E'):
            TaskList.addTask(Parser.parseEventTask(suffixWord, words[0].charAt(1) == 'T'));
            break;
        default:
            throw new Exception();
        }
    }

    public static void readFile() {
        try (BufferedReader reader= new BufferedReader(new FileReader(taskFile))){
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    processLine(line);
                }
            } catch (Exception e) {
                TaskList.clearTasks();
            }
        } catch (IOException e) {
            TaskList.clearTasks();
        }
    }

    public static void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile));
            for (Task task : TaskList.tasks) {
                writer.write(task.writeLine());
                writer.newLine();
            }
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
