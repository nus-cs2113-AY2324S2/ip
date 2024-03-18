package huan.main;
import huan.task.*;

import java.util.Objects;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for parsing all user commands
 */
public class Parser {
    /**
     * Method for parsing command inputs
     */
    public static void parseCommands() {

        Scanner scanner = new Scanner(System.in);
        while(true) {
            UI.displaySeparator();

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
                    UI.displayFormatError("'bye'");
                    break;
                }
                UI.displayByeMessage();
                return;
            case ("list"):
                if(!suffixWord.isEmpty()) {
                    UI.displayFormatError("'list'");
                    break;
                }
                UI.listTasks();
                break;
            case ("mark"):
                try {
                    int markIndex = Integer.parseInt(suffixWord);
                    if (!TaskList.isIndexValid(markIndex)) {
                        UI.displayIndexError();
                    } else {
                        TaskList.tasks.get(markIndex - 1).setIsDone(true);

                        UI.displayMarkTaskSuccess(markIndex);

                        Storage.writeFile();
                    }
                } catch (Exception e){
                    UI.displayFormatError("'mark *n', where n is the index of the task you wish to mark as finished");
                }
                break;
            case ("unmark"):
                try {
                    int unmarkIndex = Integer.parseInt(suffixWord);
                    if (!TaskList.isIndexValid(unmarkIndex)) {
                        UI.displayIndexError();
                    } else {
                        TaskList.tasks.get(unmarkIndex - 1).setIsDone(false);

                        UI.displayUnmarkTaskSuccess(unmarkIndex);

                        Storage.writeFile();
                    }
                } catch (Exception e){
                    UI.displayFormatError("'unmark *n', where n is the index of the task you wish to mark as unfinished.");
                }
                break;
            case ("todo"):
                if(suffixWord.isEmpty()) {
                    UI.displayFormatError("'todo *task_name'");
                    break;
                }
                TodoTask todoTask = parseTodoTask(suffixWord, false);
                TaskList.addTask(todoTask);

                UI.displayAddTodoSuccess(todoTask.getName());

                Storage.writeFile();
                break;
            case ("event"):
                try {
                    EventTask eventTask = parseEventTask(suffixWord, false);
                    TaskList.addTask(eventTask);

                    UI.displayAddEventSuccess(eventTask.getName());

                    Storage.writeFile();
                } catch (Exception e) {
                    UI.displayFormatError("'event *event_name /from *start_time /to *end_time'");
                }
                break;
            case ("deadline"):
                try {
                    DeadlineTask deadlineTask = parseDeadlineTask(suffixWord, false);

                    TaskList.addTask(deadlineTask);

                    UI.displayAddDeadlineSuccess(deadlineTask.getName());

                    Storage.writeFile();
                } catch (Exception e) {
                    UI.displayFormatError("'deadline *task_name /by *deadline_time'");
                }
                break;
            case ("delete"):
                try {
                    int deleteIndex = Integer.parseInt(suffixWord);
                    if (!TaskList.isIndexValid(deleteIndex)) {
                        UI.displayIndexError();
                    } else {
                        TaskList.tasks.get(deleteIndex - 1).setIsDone(true);

                        UI.displayDeleteTaskSuccess(deleteIndex);

                        TaskList.tasks.remove(deleteIndex - 1);

                        Storage.writeFile();
                    }
                } catch (Exception e) {
                    UI.displayFormatError("'delete *n', where n is the index of the task you wish to delete.");
                }
                break;
            case ("find"):
                if(!suffixWord.isEmpty()) {
                    UI.displayMatchingTasks(suffixWord);
                } else {
                    UI.displayFormatError("'find *keyword'");
                }
                break;   
            case ("list_deadline"):
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    LocalDateTime dateTime = LocalDateTime.parse(suffixWord, formatter);

                    UI.listTaskBeforeDateTime(dateTime);
                } catch(DateTimeParseException e) {
                    UI.displayFormatError("'list_deadline yyyy-MM-dd HH:mm:ss'");
                }
                break;
            default:
                UI.displayUnrecognizedMessage();
                break;
            }

        }
    }

    /**
     * Method for parsing a TodoTask from the command
     * @param suffixWord the command-string to be parsed
     * @param isDone whether the task is marked as finished
     * @return a parsed TodoTask type object
     */
    public static TodoTask parseTodoTask(String suffixWord, Boolean isDone) {
        return new TodoTask(suffixWord, isDone);
    }

    /**
     * Method for parsing a DeadlineTask from the command
     * @param suffixWord the command-string to be parsed
     * @param isDone whether the task is marked as finished
     * @return a parsed DeadlineTask type object
     * @throws Exception detect whether the command format is incorrect
     */
    public static DeadlineTask parseDeadlineTask(String suffixWord, Boolean isDone) throws Exception{
        StringBuilder ddlTime = new StringBuilder();
        StringBuilder name = new StringBuilder();
        String[] words = suffixWord.split(" ");
        /*
          state:
          0 means currently concatenating name
          1 means currently concatenating ddlTime
        */
        int state = 0;
        for(String word : words) {
            if (Objects.equals(word, "/by")) {
                state += 1;
            }
            else {
                switch (state) {
                case (0):
                    name.append((name.length() == 0) ? "" : " ").append(word);
                    break;
                case (1):
                    ddlTime.append((ddlTime.length() == 0 ? "" : " ")).append(word);
                    break;
                }
            }
        }
        if(state != 1 || name.toString().isEmpty() || ddlTime.toString().isEmpty()) {
            throw new Exception();
        }

        return new DeadlineTask(name.toString(), ddlTime.toString(), isDone);
    }

    /**
     * Method for parsing a EventTask from the command
     * @param suffixWord the command-string to be parsed
     * @param isDone whether the task is marked as finished
     * @return a parsed EventTask type object
     * @throws Exception detect whether the command format is incorrect
     */
    public static EventTask parseEventTask(String suffixWord, Boolean isDone) throws Exception{
        StringBuilder startTime = new StringBuilder();
        StringBuilder endTime = new StringBuilder();
        StringBuilder name = new StringBuilder();
        String[] words = suffixWord.split(" ");
        /*
          state:
          0 means currently concatenating name
          1 means currently concatenating startTime
          2 means currently concatenating endTime
         */
        int state = 0;
        for(String word : words) {
            if (Objects.equals(word, "/from")) {
                state += 1;
            }
            else if (Objects.equals(word, "/to")) {
                state += 1;
            }
            else {
                switch (state) {
                case (0):
                    name.append((name.length() == 0) ? "" : " ").append(word);
                    break;
                case (1):
                    startTime.append((startTime.length() == 0 ? "" : " ")).append(word);
                    break;
                case (2):
                    endTime.append((endTime.length() == 0 ? "" : " ")).append(word);
                    break;
                }
            }
        }
        if(state != 2 || name.toString().isEmpty() || startTime.toString().isEmpty() || endTime.toString().isEmpty()) {
            throw new Exception();
        }

        return new EventTask(name.toString(), startTime.toString(), endTime.toString(), isDone);
    }
}
