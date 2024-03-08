# Chatbot Battch User Guide

This is a chatbot built based on the Project Duke template. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ____________________________________________________________
   Hello! I'm Battch
   What can I do for you?
   ____________________________________________________________
   ```

## Launch Battch Using JAR file

1. Copy the jar file into an empty folder.
2. Open a command window in that folder.
3. Run the command `java -jar {filename}.jar` e.g., `java -jar Duke.jar` (i.e., run the command in the same folder as the jar file).

## Features

Enter a line of text adds the item to the list. You should see something like the below as the output:
```
read book
____________________________________________________________
added: read book
Now you have 1 tasks in the list.
____________________________________________________________
```

Command List:
1. `list`: print all existing tasks in the list.
2. `mark INDEX_NUMBER`: mark the task indicated by the index as Done:
   ```
   mark 1
   ____________________________________________________________
   Nice! I've marked this task as done:
   [X] read book
   ____________________________________________________________
   ```
3. `unmark INDEX_NUMBER`: mark the task indicated by the index as Not Done:
   ```
   unmark 1
   ____________________________________________________________
   OK, I've marked this task as not done yet:
   [ ] read book
   ____________________________________________________________
   ```
4. `todo DESCRIPTION`: add a todo task.
   ```
   todo attend cs2113 lecture
   ____________________________________________________________
   Got it. I've added this task:
   [T][ ] attend cs2113 lecture
   Now you have 2 tasks in the list.
   ____________________________________________________________
   ```
5. `deadline DESCRIPTION [/by DUE_DATE]`: add a task that need to be done before a specific date/time.
   ```
   deadline submit ip /by Friday 2359
   ____________________________________________________________
   Got it. I've added this task:
   [D][ ] submit ip (by: Friday 2359)
   Now you have 3 tasks in the list.
   ____________________________________________________________
   ```
6. `event DESCRIPTION [/from START_DATE] [/to END_DATE]`: add a task that start at a specific date/time and ends at a specific date/time.
   ```
   event cs2113 tutorial /from 3pm /to 4pm
   ____________________________________________________________
   Got it. I've added this task:
   [E][ ] cs2113 tutorial (from: 3pm to: 4pm)
   Now you have 4 tasks in the list.
   ____________________________________________________________
   ```
7. `delete INDEX_NUMBER`: remove the task indicated by the index from the list.
8. `bye`: exit the program. You should see something like the below as the output:
   ```
   bye
   ____________________________________________________________
   Bye. Hope to see you again soon!
   ____________________________________________________________
   ```

Notes about the command format:

Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `add n/NAME`, `NAME` is a parameter which can be used as add n/John Doe.

Items in square brackets are optional.
e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
e.g. if the command specifies `help 123`, it will be interpreted as `help`.
