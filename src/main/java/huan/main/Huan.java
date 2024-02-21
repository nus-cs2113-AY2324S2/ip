package huan.main;

import huan.task.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class Huan {
    private static List<Task> tasks = new ArrayList<>();

    private static String taskFile = "tasklist.txt";
    public static Boolean isIndexValid(int index) {
        return index >= 1 && index <= tasks.size();
    }
    public static void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public static void listTasks() {
        int cnt = 0;
        System.out.println("You have a total of " + tasks.size() + " tasks.");
        for (Task task : tasks) {
            cnt += 1;
            System.out.printf(cnt + ". ");

            switch (task.getTaskType()) {
            case (1):
                TodoTask todoTask = (TodoTask)task;
                todoTask.printTask();
                break;
            case (2):
                EventTask eventTask = (EventTask)task;
                eventTask.printTask();
                break;
            case (3):
                DeadlineTask deadlineTask = (DeadlineTask)task;
                deadlineTask.printTask();
                break;
            default:
                task.printTask();
                break;
            }
        }
    }

    public static void writeTasks() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile));
            for (Task task : tasks) {
                writer.write(task.writeLine());
                writer.newLine();
            }
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processLine(String line) throws Exception{
        String[] words = line.split(" ");
        String suffixWord = line.substring(words[0].length() + 1);

        if (words[0].length() != 2 || (words[0].charAt(1) != 'T' && words[0].charAt(1) != 'F')) {
            throw new Exception();
        }

        switch (words[0].charAt(0)) {
        case ('T'):
            addTask(new TodoTask(suffixWord, words[0].charAt(1) == 'T'));
            break;
        case ('D'):
            addTask(new DeadlineTask(suffixWord, words[0].charAt(1) == 'T'));
            break;
        case ('E'):
            addTask(new EventTask(suffixWord, words[0].charAt(1) == 'T'));
            break;
        default:
            throw new Exception();
        }
    }

    public static void readProcessCommands() {

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("-------------------------");
            String inputCommand = scanner.nextLine();
            String[] words = inputCommand.split(" ");
            String firstWord = words[0];
            String suffixWord;
            if(words.length > 1) {
                suffixWord = inputCommand.substring(words[0].length() + 1);
            }
            else {
                suffixWord = "";
            }
            switch (firstWord) {
            case ("bye"):
                if(!suffixWord.isEmpty()) {
                    System.out.println("Invalid format! Should be 'bye'");
                    break;
                }
                System.out.println("Bye! See ya!");
                return;
            case ("list"):
                if(!suffixWord.isEmpty()) {
                    System.out.println("Invalid format! Should be 'list'.");
                    break;
                }
                listTasks();
                break;
            case ("mark"):
                try {
                    int markIndex = Integer.parseInt(suffixWord);
                    if (!isIndexValid(markIndex)) {
                        System.out.println("Invalid task index!");
                    } else {
                        tasks.get(markIndex - 1).setIsDone(true);
                        System.out.println("Set task number " + markIndex + ": " + tasks.get(markIndex - 1).getName() + " as done.");
                        writeTasks();
                    }
                } catch (Exception e){
                    System.out.println("Incorrect format! Should be 'mark *n', where n is the index of the task you wish to mark as finished.");
                }
                break;
            case ("unmark"):
                try {
                    int unmarkIndex = Integer.parseInt(suffixWord);
                    if (!isIndexValid(unmarkIndex)) {
                        System.out.println("Invalid task index!");
                    } else {
                        tasks.get(unmarkIndex - 1).setIsDone(false);
                        System.out.println("Set task number " + unmarkIndex + ": " + tasks.get(unmarkIndex - 1).getName() + " as not done.");
                        writeTasks();
                    }
                } catch (Exception e){
                   System.out.println("Incorrect format! Should be 'unmark *n', where n is the index of the task you wish to mark as unfinished.");
                }
                break;
            case ("todo"):
                if(suffixWord.isEmpty()) {
                    System.out.println("Incorrect format! Should be 'todo *task_name'.");
                    break;
                }
                TodoTask todoTask = new TodoTask(suffixWord, false);
                addTask(todoTask);
                System.out.println("Added todo type task with name: " + todoTask.getName());
                writeTasks();
                break;
            case ("event"):
                try {
                    EventTask eventTask = new EventTask(suffixWord, false);
                    addTask(eventTask);
                    System.out.println("Added event type task with name: " + eventTask.getName());
                    writeTasks();
                } catch (Exception e) {
                    System.out.println("Incorrect format! Should be 'event *event_name /from *start_time /to *end_time'.");
                }
                break;
            case ("deadline"):
                try {
                    DeadlineTask deadlineTask = new DeadlineTask(suffixWord, false);
                    addTask(deadlineTask);
                    System.out.println("Added deadline type task with name: " + deadlineTask.getName());
                    writeTasks();
                } catch (Exception e) {
                    System.out.println("Incorrect format! Should be 'deadline *task_name /by *deadline_time'");
                }
                break;

            default:
                System.out.println("Unrecognized command, please try again!");
                break;
            }

        }
    }

    public static void main(String[] args) {
        String botName = "Huan";
        System.out.println("Hello! I'm " + botName + ", a chat bot");

        try (BufferedReader reader= new BufferedReader(new FileReader(taskFile))){
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    processLine(line);
                }
            } catch (Exception e) {
                System.out.println("Corrupt task save file");
                tasks.clear();
            }

        } catch (IOException e) {
            tasks.clear();
        }
        readProcessCommands();
    }
}
