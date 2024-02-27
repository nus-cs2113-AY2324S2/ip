package command;
import errorhandle.UserInputErrorOutputHandler;
import task.TaskList;

public class FindCommand extends Command{
    String taskContentToFind;
    UserInputErrorOutputHandler userInputError;
    public FindCommand(String userCommandText){
        userInputError = new UserInputErrorOutputHandler();

        int spaceIndex = userCommandText.indexOf(" ");
        if (spaceIndex == -1) {
            userInputError.printNoSpacingErrorForFind();
            setIfNoError(false);
        } else {
            prepareFindCommand(userCommandText.substring(spaceIndex + 1));
        }
    }
    @Override
    public void execute(TaskList taskList) {
        taskList.printTaskListWithCondition(taskContentToFind);
    }
    public void prepareFindCommand(String unpreparedMarkCommand){
        unpreparedMarkCommand = unpreparedMarkCommand.trim();
        taskContentToFind = unpreparedMarkCommand;
        setIfNoError(true);
    }
}
