package task;

import format.Formatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static constant.NormalConstant.DISPLAY_LOCAL_DATE_FORMAT;

/**
 * Maintain a list of task with extra functionalities
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    protected Formatter formatter;

    public TaskList() {
        tasks = new ArrayList<>();
        formatter = new Formatter();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void printTaskList() {
        formatter.printDividingLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i).getIdentity()
                    + tasks.get(i).getStatusIcon() + " " + tasks.get(i));
        }
        formatter.printDividingLine();
    }

    /**
     * Print the task according to the task content condition
     *
     * @param condition
     */
    public void printTaskListWithTaskContentCondition(String condition) {
        int index = 0;
        formatter.printDividingLine();
        System.out.println("\tHere are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.getDescription().contains(condition)) {
                System.out.println("\t" + (index + 1) + "." + task.getIdentity()
                        + task.getStatusIcon() + " " + task);
                index++;
            }
        }
        formatter.printDividingLine();
    }

    /**
     * Print the task according to the timeCondition
     *
     * @param timeCondition
     */
    public void printTaskListWithTimeCondition(LocalDate timeCondition) {
        int index = 0;
        formatter.printDividingLine();
        System.out.println("\tHere are the matching tasks in your list:");
        for (Task task : tasks) {
            boolean ifYearMatch = false;
            boolean ifDayOfYearMatch = false;

            if (task instanceof Deadline) {
                LocalDateTime currentTask =
                        LocalDateTime.parse(task.getBy(), DateTimeFormatter.ofPattern(DISPLAY_LOCAL_DATE_FORMAT));

                ifYearMatch = timeCondition.getYear() == currentTask.getYear();
                ifDayOfYearMatch = ifYearMatch && (timeCondition.getDayOfYear() == currentTask.getDayOfYear());
            } else if (task instanceof Event) {
                LocalDateTime currentTaskFrom =
                        LocalDateTime.parse(task.getFrom(), DateTimeFormatter.ofPattern(DISPLAY_LOCAL_DATE_FORMAT));
                LocalDateTime currentTaskTo =
                        LocalDateTime.parse(task.getTo(), DateTimeFormatter.ofPattern(DISPLAY_LOCAL_DATE_FORMAT));

                int yearOfCurrentTaskFrom = currentTaskFrom.getYear();
                int dayOfYearOfCurrentTaskFrom = currentTaskFrom.getDayOfYear();
                int yearOfCurrentTaskTo = currentTaskTo.getYear();
                int dayOfYearOfCurrentTaskTo = currentTaskTo.getDayOfYear();
                int yearOfTimeCondition = timeCondition.getYear();
                int dayOfYearOfTimeCondition = timeCondition.getDayOfYear();

                ifYearMatch = (yearOfCurrentTaskFrom <= yearOfTimeCondition)
                        && (yearOfCurrentTaskTo >= yearOfTimeCondition);
                ifDayOfYearMatch = ifYearMatch && (yearOfCurrentTaskFrom < yearOfTimeCondition
                        && yearOfCurrentTaskTo > yearOfTimeCondition)
                        || (yearOfCurrentTaskFrom < yearOfTimeCondition
                        && yearOfCurrentTaskTo == yearOfTimeCondition
                        && dayOfYearOfCurrentTaskTo >= dayOfYearOfTimeCondition)
                        || (yearOfCurrentTaskFrom == yearOfTimeCondition
                        && yearOfCurrentTaskTo > yearOfTimeCondition
                        && dayOfYearOfCurrentTaskFrom <= dayOfYearOfTimeCondition)
                        || (yearOfCurrentTaskFrom == yearOfTimeCondition
                        && yearOfCurrentTaskTo == yearOfTimeCondition
                        && dayOfYearOfCurrentTaskFrom <= dayOfYearOfTimeCondition
                        && dayOfYearOfCurrentTaskTo >= dayOfYearOfTimeCondition);
            }
            if (ifYearMatch && ifDayOfYearMatch) {
                System.out.println("\t" + (index + 1) + "." + task.getIdentity()
                        + task.getStatusIcon() + " " + task);
                index++;
            }
        }
        formatter.printDividingLine();
    }
}
