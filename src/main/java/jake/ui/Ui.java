package jake.ui;

import static jake.common.Messages.MESSAGE_GOODBYE;
import static jake.common.Messages.MESSAGE_GREETING;
import static jake.common.Messages.MESSAGE_INVALID_COMMAND;
import static jake.common.Messages.MESSAGE_INVALID_FILEPATH;
import static jake.common.Messages.MESSAGE_LINE_STRING;
import static jake.common.Messages.MESSAGE_LISTED_TASKS;
import static jake.common.Messages.MESSAGE_TASK_ADDED;
import static jake.common.Messages.MESSAGE_TASK_DELETED;
import static jake.common.Messages.MESSAGE_TASK_DOES_NOT_EXIST;
import static jake.common.Messages.MESSAGE_TASK_ERROR_ENCOUNTERED;
import static jake.common.Messages.MESSAGE_TASK_MARKED;
import static jake.common.Messages.MESSAGE_TASK_UNMARKED;
import static jake.common.Messages.MESSAGE_TASK_FIND_TASK;


public class Ui {
    public void showLineString() {
        System.out.println(MESSAGE_LINE_STRING);
    }

    public void showGreeting() {
        System.out.println(MESSAGE_GREETING);
    }

    public void showGoodbye() {
        System.out.println(MESSAGE_GOODBYE);
    }
    
    public void showListedTasks() {
        System.out.println(MESSAGE_LISTED_TASKS);
    }

    public void showInvalidCommand() {
        System.out.println(MESSAGE_INVALID_COMMAND);
    }

    public void showInvalidPath() {
        System.out.println(MESSAGE_INVALID_FILEPATH);
    }

    public void showNonexistentTask() {
        System.out.println(MESSAGE_TASK_DOES_NOT_EXIST);
    }    
    
    public void showTaskMarked() {
        System.out.println(MESSAGE_TASK_MARKED);
    }

    public void showTaskUnmarked() {
        System.out.println(MESSAGE_TASK_UNMARKED);
    }

    public void showTaskAdded() {
        System.out.println(MESSAGE_TASK_ADDED);
    }

    public void showTaskDeleted() {
        System.out.println(MESSAGE_TASK_DELETED);
    }

    public void showError() {
        System.out.println(MESSAGE_TASK_ERROR_ENCOUNTERED);
    }

    public void showFindTask() {
        System.out.println(MESSAGE_TASK_FIND_TASK);
    }
}