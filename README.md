# DoraemonBot

This is a project building a simple chat bot to keep track of tasks. Given below are instructions on how to use it.

## Setting up 

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Ensure that you have Java 11 or above installed on your computer.
1. Download the lastest JAR file from ___
2. Copy the file to the folder you want to use as the home folder for DoraemonBot.
3. Open a command terminal, `cd` into the folder where the JAR file is placed in and use `java -jar CS2113_IP_New.jar` command to run the programme.

## Add
To Add Tasks: Use the `todo` command and type in the name of the task and it will be added.
Example:
```
todo project
```

To Add Deadlines: Use the `deadline` and `/by ` commands and type in the name and due date of the task.
Example:
```
deadline project /by tomorrow
```

To Add Events: Use the `deadline`, `/from ` and `/to` commands and type in the name, starting time and ending time of the event.
Example:
```
event project /from today 2pm /to 3pm
```

## List
To List tasks you have saved. Type the command `list`.
Example:
```
list
```

## Mark
To Mark tasks completed, type the command `mark` followed by the task number of the task.
Example:
```
mark 3
```




