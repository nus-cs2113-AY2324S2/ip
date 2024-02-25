package soot.manager;

import soot.EmptyTaskException;
import soot.task.Deadline;
import soot.task.Event;
import soot.task.Task;
import soot.task.Todo;

import java.util.ArrayList;

public class CommandManager {
    ArrayList<Task> taskList;
    public static int listCounter;

    public CommandManager() {
        taskList = new ArrayList<>();
        listCounter = 0;
    }

    public static String getCommand(String input) throws EmptyTaskException {
        if (input.equals("bye") || input.equals("list")) {
            return input;
        }

        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            throw new EmptyTaskException();
        }
        return input.substring(0, spaceIndex);
    }
    public boolean isInputBye(String input) {
        String inputCommand = input;
        try {
            inputCommand = getCommand(input);
            String inputTask;
            switch (inputCommand) {
            case "bye":
                return true;
            case "list":
                System.out.println("tasks to be done:");
                if (taskList.size() == 0) {
                    System.out.println("  >> nothing so far :)");
                    drawLine();
                    break;
                }
                for (int i = 0; i < taskList.size(); i++) {
                    taskList.get(i).printTask(i+1);
                }
                drawLine();
                break;
            case "done":
                inputTask = input.substring(5);
                int listIndex = Integer.parseInt(inputTask) - 1;
                taskList.get(listIndex).markDone();
                drawLine();
                break;
            case "unmark":
                inputTask = input.substring(7);
                listIndex = Integer.parseInt(inputTask) - 1;
                taskList.get(listIndex).markUndone();
                drawLine();
                break;
            case "todo":
                inputTask = input.substring(5);
                taskList.add(new Todo(inputTask));
                listCounter++;
                taskList.get(taskList.size()-1).printRespond();
                drawLine();
                break;
            case "deadline":
                inputTask = input.substring(9);
                //split taskName and dueDate
                int deadlineIndex = inputTask.indexOf('/');
                String taskName = inputTask.substring(0, deadlineIndex - 1);
                String dueDate = inputTask.substring(deadlineIndex + 4);

                taskList.add(new Deadline(taskName, dueDate));
                listCounter++;
                taskList.get(taskList.size()-1).printRespond();
                drawLine();
                break;
            case "event":
                inputTask = input.substring(6);
                //split taskName and time frames
                int startIndex = inputTask.indexOf('/');
                taskName = inputTask.substring(0, startIndex - 1);

                String timeLine = inputTask.substring(startIndex + 6);
                int endIndex = timeLine.indexOf('/');

                String startDate = timeLine.substring(0, endIndex - 1);
                String endDate = timeLine.substring(endIndex + 4);

                taskList.add(new Event(taskName, startDate, endDate));
                listCounter++;
                taskList.get(taskList.size()-1).printRespond();
                drawLine();
                break;
            default:
                System.out.println("  !! this isn't a command i recognise...\n  sorry, pls try again");
                drawLine();
                break;
            }
        } catch (EmptyTaskException e) {
            System.out.println("  !! hmmm, no task was specified.");
            drawLine();
        }
        return false;
    }

    //TODO: repeated method from main
    public static void drawLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public int getListSize() {
        int listSize = taskList.size();
        return listSize;
    }

}