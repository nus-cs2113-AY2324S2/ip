package allez;

import allez.task.Task;
import java.util.ArrayList;

public class Command {
    private final TaskList tasks;
    public Command(TaskList tasks) {
        this.tasks = tasks;
    }
    public void markTask(String line) {
        int toMark;
        try {
            toMark = Parser.parseMarkTask(line);
            tasks.markTask(toMark);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + tasks.getSpecficTask(toMark).toString());
        } catch (NumberFormatException e) {
            System.out.println("Please input a number only");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Please input a number within current number of tasks");
        }
    }

    public void createTask(String line, Allez.TaskType type) {
        boolean taskCreated;

        switch (type){
            case TODO:
                taskCreated = createToDoTask(line);
                break;
            case DEADLINE:
                taskCreated = createDeadlineTask(line);
                break;
            case EVENT:
                taskCreated = createEventTask(line);
                break;
            default:
                System.out.println("Invalid TaskType occurred.");
                return;
        }

        if(!taskCreated){
            return;
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks.getSpecficTask(tasks.getNumberOfTasks()-1).toString());
        System.out.println("Now you have " + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    private boolean createToDoTask(String line) {
        String description = line.substring(4).trim();
        if (description.isEmpty()) {
            System.out.println("Please add a description of your task");
            return false;
        }
        tasks.addToDo(description);
        return true;
    }

    private boolean createDeadlineTask(String line) {
        try {
            String[] lineSegment = Parser.parseDeadlineInput(line);
            tasks.addDeadline(lineSegment);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input deadline for the task in a format as shown below");
            System.out.println("deadline <description> /by <deadline>");
        } catch (MissingDetailsException e) {
            System.out.println("Please input something for description and deadline");
        } catch (Exception e) {
            System.out.println("Error encountered");
        }
        return false;
    }

    private boolean createEventTask(String line) {
        try {
            String[] lineSegment = Parser.parseEventInput(line);
            tasks.addEvent(lineSegment);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input event for the task in a format as shown below");
            System.out.println("event <description> /from <start> /to <end>");
        } catch (MissingDetailsException e) {
            System.out.println("Please input something for description, from and to");
        } catch (Exception e) {
            System.out.println("Error encountered");
        }
        return false;
    }

    public void printList(ArrayList<Task> tasks){
        int count = 0;

        if(tasks.isEmpty()) {
            System.out.println("List is currently empty");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("\t" + (count+1) + ". " + task.toString());
            count+=1;
        }

    }

    public void deleteTask(String line) {
        try {
            int toDelete = Parser.parseDelete(line);
            Task temp = tasks.getSpecficTask(toDelete);
            tasks.deleteTask(toDelete);
            System.out.println("Aight. I've removed this task:");
            System.out.println("\t" + temp.toString());
            System.out.println("Now you have " + tasks.getNumberOfTasks() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a number only");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Please input a number within current number of tasks");
        }
    }


    public void findTask(String line) {
        try {
            String keyword = Parser.parseFind(line);
            if (keyword.isBlank()) {
                System.out.println("Please type in a keyword to find");
                return;
            }

            boolean anyTaskFound = false;
            int count = 0;
            ArrayList<Integer> storeTasksFound = new ArrayList<Integer>();
            for(Task task : tasks.getTasks()) {
                count += 1;
                if(!task.getDescription().contains(keyword)){
                    continue;
                }
                anyTaskFound = true;
                storeTasksFound.add(count);
            }

            if (!anyTaskFound) {
                System.out.println("No tasks found. Try a different keyword");
            } else {
                for (int c : storeTasksFound) {
                    System.out.println("\t" + (c) + ". " + tasks.getSpecficTask(c-1).toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error encountered while finding tasks");;
        }
    }
}
