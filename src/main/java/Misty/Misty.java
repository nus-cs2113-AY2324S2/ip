package misty;

import misty.exception.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Misty {
    public static void main(String[] args) {
        try {
            SaveFile.createFiles();
        } catch (IOException e) {
            UserUi.printErrorIO();
        } catch (SecurityException e) {
            UserUi.printErrorSecurity();
        }

        List taskList = new List();

        try {
            SaveFile.loadData(taskList);
        } catch (FileNotFoundException e) {
            UserUi.printErrorFileNotFound();
        } catch (EmptyTaskNameException e) {
            UserUi.printErrorCorruptedFile();
        } catch (IllegalListIndexException e) {
            UserUi.printErrorCorruptedFile();
        } catch (EmptyByException e) {
            UserUi.printErrorCorruptedFile();
        } catch (EmptyFromException e ) {
            UserUi.printErrorCorruptedFile();
        } catch (EmptyToException e) {
            UserUi.printErrorCorruptedFile();
        } catch (UnknownTaskException e) {
            UserUi.printErrorCorruptedFile();
        }

        UserUi.printWelcomeMessage();

        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            UserUi.printMessageBorder();
            switch (input) {
            case "list":
                taskList.listAll();
                break;
            case "bye":
                UserUi.printByeMessage();
                UserUi.printMessageBorder();
                System.exit(0);
            default:
                if (input.startsWith("unmark")) {
                    int index;

                    try {
                        index = Integer.parseInt(input.substring(input.indexOf(" ")).trim());
                    } catch (NumberFormatException e) {
                        UserUi.printErrorNoId();
                        UserUi.printUsageUnmark();
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorNoId();
                        UserUi.printUsageUnmark();
                        break;
                    }

                    try {
                        taskList.unmarkTask(index);
                    } catch (IllegalListIndexException e) {
                        UserUi.printErrorInvalidId();
                        UserUi.printUsageUnmark();
                        break;
                    }
                } else if (input.startsWith("mark")) {
                    int index;

                    try {
                        index = Integer.parseInt(input.substring(input.indexOf(" ")).trim());
                    } catch (NumberFormatException e) {
                        UserUi.printErrorNoId();
                        UserUi.printUsageMark();
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorNoId();
                        UserUi.printUsageMark();
                        break;
                    }

                    try {
                        taskList.markTask(index);
                    } catch (IllegalListIndexException e) {
                        UserUi.printErrorInvalidId();
                        UserUi.printUsageMark();
                        break;
                    }
                } else if (input.startsWith("todo")) {
                    String description;

                    try {
                        description = input.substring(input.indexOf(" ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorNoTaskName();
                        UserUi.printUsageUsageTodo();
                        break;
                    }

                    try {
                        taskList.addTodo(description);
                    } catch (EmptyTaskNameException e) {
                        UserUi.printErrorNoTaskName();
                        UserUi.printUsageUsageTodo();
                        break;
                    }
                } else if (input.startsWith("deadline")) {
                    String description;
                    String by;

                    try {
                        description = input.substring(input.indexOf(" "), input.indexOf(" /by ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorInvalidFormat();
                        UserUi.printUsageDeadline();
                        break;
                    }

                    try {
                        by = input.substring(input.indexOf(" /by ") + 4).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorInvalidFormat();
                        UserUi.printUsageDeadline();
                        break;
                    }

                    try {
                        taskList.addDeadline(description, by);
                    } catch (EmptyTaskNameException e) {
                        UserUi.printErrorNoTaskName();
                        UserUi.printUsageDeadline();
                        break;
                    } catch (EmptyByException e) {
                        UserUi.printErrorNoBy();
                        UserUi.printUsageDeadline();
                        break;
                    }
                } else if (input.startsWith("event")) {
                    String description;
                    String from;
                    String to;

                    try {
                        description = input.substring(input.indexOf(" "), input.indexOf(" /from ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorInvalidFormat();
                        UserUi.printUsageEvent();
                        break;
                    }

                    try {
                        from = input.substring(input.indexOf("/from") + 5, input.indexOf(" /to ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorInvalidFormat();
                        UserUi.printUsageEvent();
                        break;
                    }

                    try {
                        to = input.substring(input.indexOf("/to ") + 3).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorInvalidFormat();
                        UserUi.printUsageEvent();
                        break;
                    }

                    try {
                        taskList.addEvent(description, from, to);
                    } catch (EmptyTaskNameException e) {
                        UserUi.printErrorNoTaskName();
                        UserUi.printUsageEvent();
                        break;
                    } catch (EmptyFromException e) {
                        UserUi.printErrorNoFrom();
                        UserUi.printUsageEvent();
                        break;
                    } catch (EmptyToException e) {
                        UserUi.printErrorNoTo();
                        UserUi.printUsageEvent();
                        break;
                    }
                } else if(input.startsWith("delete")) {
                    int index;
                    try {
                        index = Integer.parseInt(input.substring(input.indexOf(" ")).trim());
                    } catch (NumberFormatException e) {
                        UserUi.printErrorNoId();
                        UserUi.printUsageDelete();
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        UserUi.printErrorNoId();
                        UserUi.printUsageDelete();
                        break;
                    }

                    try {
                        taskList.deleteTask(index);
                    } catch (IllegalListIndexException e) {
                        UserUi.printErrorInvalidId();
                        UserUi.printUsageDelete();
                        break;
                    }
                } else {
                    UserUi.printUnknownCommandMessage();
                }
            }

            UserUi.printMessageBorder();
        }
    }
}
