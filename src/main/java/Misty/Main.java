package misty;

import misty.exception.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserUi userUi = new UserUi();
        Storage storage = new Storage();

        try {
            storage.createFiles();
        } catch (IOException e) {
            userUi.printErrorIO();
        } catch (SecurityException e) {
            userUi.printErrorSecurity();
        }

        List taskList = new List(storage, userUi);

        try {
            storage.loadData(taskList);
        } catch (FileNotFoundException e) {
            userUi.printErrorFileNotFound();
        } catch (EmptyTaskNameException e) {
            userUi.printErrorCorruptedFile();
        } catch (IllegalListIndexException e) {
            userUi.printErrorCorruptedFile();
        } catch (EmptyByException e) {
            userUi.printErrorCorruptedFile();
        } catch (EmptyFromException e ) {
            userUi.printErrorCorruptedFile();
        } catch (EmptyToException e) {
            userUi.printErrorCorruptedFile();
        } catch (UnknownTaskException e) {
            userUi.printErrorCorruptedFile();
        }

        userUi.printWelcomeMessage();

        String userInput;

        while (true) {
            userInput = userUi.getUserCommand();

            userUi.printMessageBorder();
            switch (userInput) {
            case "list":
                taskList.listAll();
                break;
            case "bye":
                userUi.printByeMessage();
                userUi.printMessageBorder();
                System.exit(0);
            default:
                if (userInput.startsWith("unmark")) {
                    int index;

                    try {
                        index = Integer.parseInt(userInput.substring(userInput.indexOf(" ")).trim());
                    } catch (NumberFormatException e) {
                        userUi.printErrorNoId();
                        userUi.printUsageUnmark();
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorNoId();
                        userUi.printUsageUnmark();
                        break;
                    }

                    try {
                        taskList.unmarkTask(index);
                    } catch (IllegalListIndexException e) {
                        userUi.printErrorInvalidId();
                        userUi.printUsageUnmark();
                        break;
                    }
                } else if (userInput.startsWith("mark")) {
                    int index;

                    try {
                        index = Integer.parseInt(userInput.substring(userInput.indexOf(" ")).trim());
                    } catch (NumberFormatException e) {
                        userUi.printErrorNoId();
                        userUi.printUsageMark();
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorNoId();
                        userUi.printUsageMark();
                        break;
                    }

                    try {
                        taskList.markTask(index);
                    } catch (IllegalListIndexException e) {
                        userUi.printErrorInvalidId();
                        userUi.printUsageMark();
                        break;
                    }
                } else if (userInput.startsWith("todo")) {
                    String description;

                    try {
                        description = userInput.substring(userInput.indexOf(" ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorNoTaskName();
                        userUi.printUsageUsageTodo();
                        break;
                    }

                    try {
                        taskList.addTodo(description);
                    } catch (EmptyTaskNameException e) {
                        userUi.printErrorNoTaskName();
                        userUi.printUsageUsageTodo();
                        break;
                    }
                } else if (userInput.startsWith("deadline")) {
                    String description;
                    String by;

                    try {
                        description = userInput.substring(userInput.indexOf(" "), userInput.indexOf(" /by ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorInvalidFormat();
                        userUi.printUsageDeadline();
                        break;
                    }

                    try {
                        by = userInput.substring(userInput.indexOf(" /by ") + 4).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorInvalidFormat();
                        userUi.printUsageDeadline();
                        break;
                    }

                    try {
                        taskList.addDeadline(description, by);
                    } catch (EmptyTaskNameException e) {
                        userUi.printErrorNoTaskName();
                        userUi.printUsageDeadline();
                        break;
                    } catch (EmptyByException e) {
                        userUi.printErrorNoBy();
                        userUi.printUsageDeadline();
                        break;
                    }
                } else if (userInput.startsWith("event")) {
                    String description;
                    String from;
                    String to;

                    try {
                        description = userInput.substring(userInput.indexOf(" "), userInput.indexOf(" /from ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorInvalidFormat();
                        userUi.printUsageEvent();
                        break;
                    }

                    try {
                        from = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf(" /to ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorInvalidFormat();
                        userUi.printUsageEvent();
                        break;
                    }

                    try {
                        to = userInput.substring(userInput.indexOf("/to ") + 3).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorInvalidFormat();
                        userUi.printUsageEvent();
                        break;
                    }

                    try {
                        taskList.addEvent(description, from, to);
                    } catch (EmptyTaskNameException e) {
                        userUi.printErrorNoTaskName();
                        userUi.printUsageEvent();
                        break;
                    } catch (EmptyFromException e) {
                        userUi.printErrorNoFrom();
                        userUi.printUsageEvent();
                        break;
                    } catch (EmptyToException e) {
                        userUi.printErrorNoTo();
                        userUi.printUsageEvent();
                        break;
                    }
                } else if(userInput.startsWith("delete")) {
                    int index;
                    try {
                        index = Integer.parseInt(userInput.substring(userInput.indexOf(" ")).trim());
                    } catch (NumberFormatException e) {
                        userUi.printErrorNoId();
                        userUi.printUsageDelete();
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorNoId();
                        userUi.printUsageDelete();
                        break;
                    }

                    try {
                        taskList.deleteTask(index);
                    } catch (IllegalListIndexException e) {
                        userUi.printErrorInvalidId();
                        userUi.printUsageDelete();
                        break;
                    }
                } else {
                    userUi.printUnknownCommandMessage();
                }
            }

            userUi.printMessageBorder();
        }
    }
}
