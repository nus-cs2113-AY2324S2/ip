package utilities.commands;

import tasks.Task;
import ui.Constants;
import utilities.parser.InputParser;

import java.util.ArrayList;

public class FindTask {
    public static void findTasks(InputParser input, ArrayList<Task> list) {

        String keyword = input.getSplitInput()[0];
        ArrayList<Task> tasksFound = new ArrayList<>();
        if (list.isEmpty()) {
            System.out.println(Constants.EMPTYFIND);
            return;
        }

        for (Task task : list) {
            if (task.getDescriptionOnly().contains(keyword)) {
                tasksFound.add(task);
            }
        }

        if (tasksFound.isEmpty()) {
            System.out.println(Constants.EMPTYFIND);
            return;
        }

        DisplayList.listCommand(tasksFound);
        System.out.println(Constants.TAB + tasksFound.size() + Constants.FINDTASK);
        System.out.println(Constants.LINE);
    }
}