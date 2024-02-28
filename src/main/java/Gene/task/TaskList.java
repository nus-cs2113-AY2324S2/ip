package Gene.task;

import Gene.storage.Storage;
import Gene.ui.Ui;
import Gene.GeneException;

import java.util.ArrayList;
import java.util.Objects;

import static Gene.command.MarkCommand.isNumeric;

public class TaskList {
    private ArrayList<Task> toDoList = new ArrayList<>();
    private final Storage fileData = new Storage();
    public TaskList() {
        toDoList = fileData.loadTasksFromFile();
    }

    private void saveTasksToFile() {
        fileData.saveTasksToFile(toDoList);
    }

    public void addTask(Task newTask) {
        toDoList.add(newTask);
        saveTasksToFile();
        Ui.printLineSeparation();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
        Ui.printLineSeparation();
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= toDoList.size();
    }

    public void markTaskAsDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            Task task = toDoList.get(taskNumber - 1);
            task.setDone(true);
            saveTasksToFile();
            Ui.printLineSeparation();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   " + task.getStatusIcon() + " " + task.description);
            Ui.printLineSeparation();
        } else {
            System.out.println("ERROR: Invalid task number. Please provide a valid task number.");
        }
    }

    public void markTaskAsNotDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            Task task = toDoList.get(taskNumber - 1);
            task.setDone(false);
            saveTasksToFile();
            Ui.printLineSeparation();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("   " + task.getStatusIcon() + " " + task.description);
            Ui.printLineSeparation();
        } else {
            System.out.println("Invalid task number. Please provide a valid task number.");
        }
    }

    public void printListItems() {
        Ui.printLineSeparation();
        System.out.println("Here are the items in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            Task task = toDoList.get(i);
            System.out.println((i + 1) + ". " + task);
        }
        if (toDoList.isEmpty()) {
            System.out.println("  <empty list>");
        }
        Ui.printLineSeparation();
    }

    public void findListItems(String keyword) {
        Ui.printLineSeparation();
        System.out.println("Here are the matching tasks in your list:");
        int listOrder = 0;
        for (Task task : toDoList) {
            String[] phrases = task.description.split(" ");
            for (String words : phrases) {
                if (Objects.equals(words, keyword)) {
                    System.out.println((listOrder+1) + ". " + task);
                    listOrder++;
                    break;
                }
            }
        }
        if (listOrder == 0) {
            System.out.println("  <empty list>");
        }
        Ui.printLineSeparation();
    }
    public void deleteListItem(String command) throws GeneException {
        String[] parts = command.split(" ");
        if (parts.length < 2 || !isNumeric(parts[1]) || Integer.parseInt(parts[1]) > toDoList.size()) {
            throw new GeneException("Please provide a valid task number." + System.lineSeparator()
                    + "Use Format: delete (number)");
        }

        int taskNumber = Integer.parseInt(parts[1]);
        System.out.println("Got it. I've deleted this task:");
        System.out.println("  " + toDoList.get(taskNumber - 1));
        toDoList.remove(taskNumber - 1);
        saveTasksToFile();
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
        Ui.printLineSeparation();
    }


}
