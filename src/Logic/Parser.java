package logic;

import command.BaseCommand;
import command.ByeCommand;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.FinddateCommand;
import command.ListCommand;
import command.MarkCommand;
import command.PostponeCommand;
import command.ToDoCommand;
import command.UnmarkCommand;
import exceptions.MarioFileError;
import templates.BaseDate;
import templates.TaskList;
import templates.task.Deadline;
import templates.task.Event;
import templates.task.Task;
import templates.task.ToDo;

public class Parser {
    public static BaseCommand parse(String args) throws Exception {
        String firstWord = args.split(" ")[0].toLowerCase();
        String taskString = removeFirstWord(args);
        BaseCommand c = createCommand(firstWord, taskString);
        if (c == null) {
            throw new Exception("I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }

    private static BaseCommand createCommand(String commandType, String taskString) throws Exception {
        switch (commandType) {
            case "list":
                return new ListCommand(taskString);
            case "bye":
                return new ByeCommand();
            case "deadline":
                checkTaskString(taskString);
                return new DeadlineCommand(taskString);
            case "delete":
                checkTaskString(taskString);
                return new DeleteCommand(taskString);
            case "event":
                checkTaskString(taskString);
                return new EventCommand(taskString);
            case "find":
                checkTaskString(taskString);
                return new FindCommand(taskString);
            case "find-date":
                checkTaskString(taskString);
                return new FinddateCommand(taskString);
            case "mark":
                checkTaskString(taskString);
                return new MarkCommand(taskString);
            case "unmark":
                checkTaskString(taskString);
                return new UnmarkCommand(taskString);
            case "todo":
                checkTaskString(taskString);
                return new ToDoCommand(taskString);
            case "postpone":
                checkTaskString(taskString);
                return new PostponeCommand(taskString);
            default:
                return null;
        }
    }

    private static String removeFirstWord(String input) {
        if (input != null && !input.isEmpty()) {
            int firstSpaceIndex = input.indexOf(' ');
            if (firstSpaceIndex != -1) {
                return input.substring(firstSpaceIndex + 1).trim();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static void checkTaskString(String args) throws Exception {
        if (args == null || args.isBlank()) {
            throw new Exception("Missing arguement!");
        }
    }

    public static void parseTaskFromString(String data, TaskList taskList) throws Exception{
        Task task = null;
        data = data.substring(data.indexOf("["), data.length());
        Boolean markedStatus = data.charAt(5) == 'X';
        String eventString, startDateString, endDateString;
        BaseDate startDate, endDate;
        try {
            switch (data.charAt(1)) {
                case 'T':
                    eventString = data.substring(data.indexOf("]") + 4).trim();
                    task = new ToDo(eventString);
                    task.setCompleted(markedStatus);
                    break;
                case 'E':
                    eventString = data.substring(data.indexOf("]") + 4, data.indexOf(" (from:")).trim();
                    startDateString = data
                            .substring(data.indexOf(" (from:") + " (from:".length(), data.indexOf("to:"))
                            .trim();
                    endDateString = data.substring(data.indexOf("to:") + "to:".length(), data.indexOf(")"))
                            .trim();
                    startDate = new BaseDate(startDateString);
                    endDate = new BaseDate(endDateString);
                    task = new Event(eventString, startDate, endDate);
                    task.setCompleted(markedStatus);
                    break;
                case 'D':
                    eventString = data.substring(data.indexOf("]") + 4, data.indexOf(" (by: ")).trim();
                    startDateString = data
                            .substring(data.indexOf(" (by: ") + " (by: ".length(), data.indexOf(")"))
                            .trim();
                    startDate = new BaseDate(startDateString);
                    task = new Deadline(eventString, startDate);
                    task.setCompleted(markedStatus);
                    break;
                default:
                    throw new MarioFileError();
            }
            if (task != null) {
                taskList.addTask(task);
            }
        } catch (Exception e) {
            throw new MarioFileError();
        }
    }
}
