package Misty;

import Misty.Exception.*;

import java.util.Scanner;

public class Misty {
    public static void main(String[] args) {
        List itemList = new List();
        Parser.printWelcomeMessage();

        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            Parser.printMessageBorder();
            switch (input) {
            case "list":
                itemList.listAll();
                break;
            case "bye":
                Parser.printByeMessage();
                Parser.printMessageBorder();
                System.exit(0);
            default:
                if (input.startsWith("unmark")) {
                    int index;

                    try {
                        index = Integer.parseInt(input.substring(input.indexOf(" ")).trim());
                    } catch(NumberFormatException e) {
                        Parser.printErrorNoId();
                        Parser.printUsageUnmark();
                        break;
                    } catch(StringIndexOutOfBoundsException e) {
                        Parser.printErrorNoId();
                        Parser.printUsageUnmark();
                        break;
                    }

                    try {
                        itemList.unmarkTask(index);
                    } catch(IllegalListIndexException e) {
                        Parser.printErrorInvalidId();
                        Parser.printUsageUnmark();
                        break;
                    }
                } else if (input.startsWith("mark")) {
                    int index;

                    try {
                        index = Integer.parseInt(input.substring(input.indexOf(" ")).trim());
                    } catch(NumberFormatException e) {
                        Parser.printErrorNoId();
                        Parser.printUsageMark();
                        break;
                    } catch(StringIndexOutOfBoundsException e) {
                        Parser.printErrorNoId();
                        Parser.printUsageMark();
                        break;
                    }

                    try {
                        itemList.markTask(index);
                    } catch(IllegalListIndexException e) {
                        Parser.printErrorInvalidId();
                        Parser.printUsageMark();
                        break;
                    }

                } else if (input.startsWith("todo")) {
                    String description;

                    try {
                        description = input.substring(input.indexOf(" ")).trim();
                    } catch(StringIndexOutOfBoundsException e) {
                        Parser.printErrorNoTaskName();
                        Parser.printUsageUsageTodo();
                        break;
                    }

                    try {
                        itemList.addTodo(description);
                    } catch (EmptyTaskNameException e) {
                        Parser.printErrorNoTaskName();
                        Parser.printUsageUsageTodo();
                        break;
                    }
                } else if (input.startsWith("deadline")) {
                    String description;
                    String by;

                    try {
                        description = input.substring(input.indexOf(" "), input.indexOf(" /by ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        Parser.printErrorInvalidFormat();
                        Parser.printUsageDeadline();
                        break;
                    }

                    try {
                        by = input.substring(input.indexOf(" /by ") + 4).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        Parser.printErrorInvalidFormat();
                        Parser.printUsageDeadline();
                        break;
                    }

                    try {
                        itemList.addDeadline(description, by);
                    } catch (EmptyTaskNameException e) {
                        Parser.printErrorNoTaskName();
                        Parser.printUsageDeadline();
                        break;
                    } catch (EmptyByException e) {
                        Parser.printErrorNoBy();
                        Parser.printUsageDeadline();
                        break;
                    }
                } else if (input.startsWith("event")) {
                    String description;
                    String from;
                    String to;

                    try {
                        description = input.substring(input.indexOf(" "), input.indexOf(" /from ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        Parser.printErrorInvalidFormat();
                        Parser.printUsageEvent();
                        break;
                    }

                    try {
                        from = input.substring(input.indexOf("/from") + 5, input.indexOf(" /to ")).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        Parser.printErrorInvalidFormat();
                        Parser.printUsageEvent();
                        break;
                    }

                    try {
                        to = input.substring(input.indexOf("/to ") + 3).trim();
                    } catch (StringIndexOutOfBoundsException e) {
                        Parser.printErrorInvalidFormat();
                        Parser.printUsageEvent();
                        break;
                    }

                    try {
                        itemList.addEvent(description, from, to);
                    } catch (EmptyTaskNameException e) {
                        Parser.printErrorNoTaskName();
                        Parser.printUsageEvent();
                        break;
                    } catch (EmptyFromException e) {
                        Parser.printErrorNoFrom();
                        Parser.printUsageEvent();
                        break;
                    } catch (EmptyToException e) {
                        Parser.printErrorNoTo();
                        Parser.printUsageEvent();
                        break;
                    }
                } else {
                    Parser.printUnknownCommandMessage();
                }
            }

            Parser.printMessageBorder();
        }
    }
}
