package misty;

import misty.exception.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserUi userUi = new UserUi();

        try {
            SaveFile.createFiles();
        } catch (IOException e) {
            userUi.printErrorIO();
        } catch (SecurityException e) {
            userUi.printErrorSecurity();
        }

        List taskList = new List();

        try {
            SaveFile.loadData(taskList);
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

        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            userUi.printMessageBorder();
            switch (input) {
            case "list":
                taskList.listAll();
                break;
            case "bye":
                userUi.printByeMessage();
                userUi.printMessageBorder();
                System.exit(0);
            default:
                if (input.startsWith("unmark")) {
                    int index;

                    try {
                        index = Integer.parseInt(input.substring(input.indexOf(" ")).trim());
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
                } else if (input.startsWith("mark")) {
                    int index;

                    try {
                        index = Integer.parseInt(input.substring(input.indexOf(" ")).trim());
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
                } else if (input.startsWith("todo")) {
                    String description;

                    try {
                        description = input.substring(input.indexOf(" ")).trim();
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
                } else if (input.startsWith("deadline")) {
                    String description;
                    String by;

                    try {
                        description = input.substring(input.indexOf(" "), input.indexOf(" /by ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorInvalidFormat();
                        userUi.printUsageDeadline();
                        break;
                    }

                    try {
                        by = input.substring(input.indexOf(" /by ") + 4).trim();
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
                } else if (input.startsWith("event")) {
                    String description;
                    String from;
                    String to;

                    try {
                        description = input.substring(input.indexOf(" "), input.indexOf(" /from ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorInvalidFormat();
                        userUi.printUsageEvent();
                        break;
                    }

                    try {
                        from = input.substring(input.indexOf("/from") + 5, input.indexOf(" /to ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        userUi.printErrorInvalidFormat();
                        userUi.printUsageEvent();
                        break;
                    }

                    try {
                        to = input.substring(input.indexOf("/to ") + 3).trim();
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
                } else if(input.startsWith("delete")) {
                    int index;
                    try {
                        index = Integer.parseInt(input.substring(input.indexOf(" ")).trim());
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
