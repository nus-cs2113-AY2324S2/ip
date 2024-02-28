package Tony;

import Tony.FileManager.FileLoader;
import Tony.FileManager.FileSaver;

import Tony.UI.Ui;
import Tony.task.Deadline;
import Tony.task.Event;
import Tony.task.Task;
import Tony.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Tony.UI.Ui.LINE_BREAKER;

public class Tony {
    private final FileSaver fileSaver;
    private final FileLoader fileLoader;
    private final Ui ui;
    private final ArrayList<Task> tasks;

    public Tony() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui(this.tasks);
        this.fileSaver = new FileSaver(this.tasks);
        this.fileLoader = new FileLoader(this.tasks);
    }

    public void run() throws Exception {
        ui.printWelcomeMessage();
        fileLoader.checkFileExists();
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            String line = userInput.nextLine();
            if (line.equals("bye")) {
                ui.printByeMessage();
                return;
            } else if (line.equals("list")) {
                ui.printTaskList();
            } else if (line.contains("mark")) {
                try {
                    String[] subCommand = line.split(" ");
                    int num = Integer.parseInt(subCommand[1]);
                    checkNumberWithinRange(num);
                    markTasks(subCommand, num);
                } catch (NumberFormatException nfe) {
                    System.out.println("Suggest only number after 'mark'!");
                } catch (TonyException e) {
                    System.out.println("To mark a task, suggest a number available in the list!");
                }
            } else if (line.startsWith("todo") || line.startsWith("deadline")
                    || line.startsWith("event")) {
                try {
                    addATask(line);
                } catch (TonyException e) {
                    System.out.println(LINE_BREAKER + "OOPS!! The description of " + line
                            + " cannot be empty." + System.lineSeparator() + LINE_BREAKER);
                }
            } else if (line.startsWith("delete")) {
                deleteCommand(line);
            } else {
                checkCommandWord();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Tony().run();
    }

    private static void checkCommandWord() {
        System.out.println("Please begin input with the following words: "
                + System.lineSeparator()
                + "'todo / deadline / event / delete / mark'");
    }

    private void deleteCommand(String line) throws IOException {
        try {
            String[] subCommand = line.split(" ");
            int num = Integer.parseInt(subCommand[1]);
            checkNumberWithinRange(num);
            deleteATask(subCommand[0], num);
        } catch (NumberFormatException nfe) {
            System.out.println("Suggest only number after 'delete'!");
        } catch (TonyException e) {
            System.out.println("To delete a task, suggest a number available in the list!");
        }
    }

    private void deleteATask(String subCommand, int num) throws IOException {
        ui.printAddOrDeleteTask(subCommand, num - 1);
        tasks.remove(num - 1);
        fileSaver.updateFile();
    }

    private void checkNumberWithinRange(int num) throws TonyException {
        if (num > tasks.size()) {
            throw new TonyException();
        }
    }


    private void markTasks(String[] subCommand, int num) throws IOException {
        if (subCommand[0].equals("mark")) {
            tasks.get(num - 1).markDone();
            System.out.println(
                    LINE_BREAKER
                            + "\tNice! I've marked this task as done:"
                            + System.lineSeparator() + tasks.get(num - 1) + System.lineSeparator()
                            + LINE_BREAKER);
        } else {
            tasks.get(num - 1).markNotDone();
            System.out.println(
                    LINE_BREAKER
                            + "\tOK, I've marked this task as not done yet:"
                            + System.lineSeparator() + tasks.get(num - 1) + System.lineSeparator()
                            + LINE_BREAKER);
        }
        fileSaver.updateFile();
    }

    private void addATask(String userInput) throws TonyException, IOException {
        if (userInput.startsWith("todo")) {
            String[] toDoTask = userInput.split("todo");
            checkArrayLength(toDoTask);
            addTodoTask(toDoTask);
        } else if (userInput.startsWith("deadline")) {
            String[] deadlineTask = userInput.split("deadline");
            checkArrayLength(deadlineTask);
            addDeadlineTask(deadlineTask);
        } else if (userInput.startsWith("event")) {
            String[] eventTask = userInput.split("event");
            checkArrayLength(eventTask);
            addEventTask(eventTask);
        }
    }

    private void checkArrayLength(String[] toDoTask) throws TonyException {
        if (toDoTask.length != 2) {
            throw new TonyException();
        }
    }

    private void addEventTask(String[] eventTask) throws IOException {
        String[] description = eventTask[1].split("/from | /to");
        Event event = new Event(description[0], description[1], description[2]);
        tasks.add(event);
        ui.printAddOrDeleteTask(description[0], tasks.indexOf(event));
        String eventLine = fileSaver.saveEvent(event);
        fileSaver.saveData(eventLine, true);
    }

    private void addDeadlineTask(String[] deadlineTask) throws IOException {
        String[] description = deadlineTask[1].split("/by");
        Deadline deadline = new Deadline(description[0], description[1]);
        tasks.add(deadline);
        ui.printAddOrDeleteTask(description[0], tasks.indexOf(deadline));
        String deadllineLine = fileSaver.saveDeadline(deadline);
        fileSaver.saveData(deadllineLine, true);
    }

    private void addTodoTask(String[] toDoTask) throws IOException {
        Todo todo = new Todo(toDoTask[1]);
        tasks.add(todo);
        ui.printAddOrDeleteTask(toDoTask[0], tasks.indexOf(todo));
        String todoLine = fileSaver.saveTodo(todo);
        fileSaver.saveData(todoLine, true);
    }
}