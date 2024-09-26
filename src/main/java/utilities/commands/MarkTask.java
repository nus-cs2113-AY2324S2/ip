package utilities.commands;

import main.Aragorn;
import ui.Constants;
import utilities.parser.InputParser;

public class MarkTask {

    /**
     * Marks a task from the task list as done and prints the number of tasks remaining.
     *
     * @param input Parsed input containing index of the task being marked as complete.
     * @param remainingTasks Number of tasks that remain incomplete.
     */
    protected static void markTask(InputParser input, int remainingTasks) {
        try {
            int index = Integer.parseInt(input.getSplitInput()[0]);
            String icon = Aragorn.getList().get(index).getStatusIcon();
            if (icon.equals(Constants.COMPLETE)) {
                System.out.println(Constants.ALREADYMARKED);
                return;
            }
            Aragorn.getList().get(index).markAsDone();
            remainingTasks -= 1;
            System.out.println(Constants.MARKTASK + Aragorn.getList().get(index).taskString() + Constants.NEWLINE);
            Constants.printRemainingTasks(remainingTasks, Aragorn.getList().size());
        } catch (NullPointerException e) {
            System.out.println(Constants.INVALIDINDEX);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Constants.INVALIDTASK);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INVALIDTASK);
        }
    }
}
