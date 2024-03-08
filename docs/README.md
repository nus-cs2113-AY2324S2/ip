# Chris
Welcome to chatbot Chris's user guide!
## Contents
- [Setting up IntelliJ](#setting-up-intellij)
- [Features](#features)
    - [To Do task](#to-do-task-todo-description)
    - [Deadline task](#deadline-task-deadline-description-by-date)
    - [Event task](#event-task-event-description-from-date-to-date)
    - [Mark task](#mark-task-mark-index)
    - [Delete task](#delete-task-delete-index)
    - [List all tasks](#list-all-tasks-list)
    - [Find tasks](#find-tasks-find-keyword)
    - [Exiting the chatbot](#exiting-the-chatbot-quit)

## Setting up IntelliJ

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Chris.java` file, right-click it, and choose `Run Chris.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ---------------------------------------
   Hello, I am Chris!
   What can I do for you?
   ---------------------------------------
   ```

## Features

### To Do task: ```todo DESCRIPTION```
Adds a todo task.
- ```DESCRIPTION``` must not be empty

Examples:
- ```todo buy milk```
- ```todo go for a run```

### Deadline task: ```deadline DESCRIPTION /by DATE```
Adds a deadline task.
- ```DESCRIPTION``` must not be empty
- ```DATE``` must not be empty
- ```DATE``` can take the form of any string
- ```/by``` must be present

Examples:
- ```deadline Complete CS2113 ip /by 8 Mar 2359```
- ```deadline Purchase plane tickets /by next week```

### Event task: ```event DESCRIPTION /from date /to DATE```
Adds an event task.
- ```DESCRIPTION``` must not be empty
- ```DATE``` must not be empty
- ```DATE``` can take the form of any string
- ```/from``` must be present
- ```/to``` must be present

Examples:
- ```event Mom's birthday dinner /from Sunday 1900 /to 2100```
- ```event Midterms on Tuesday /from 5pm /to 6.30pm ```

### Mark task: ```mark INDEX```
Mark or unmark the task at the index.
- ```INDEX``` must be in integer from 1 to total number of tasks
- ```INDEX``` must not be empty

Example:
- ```mark 1```

### Delete task: ```delete INDEX```
Deletes the task at the index.
- ```INDEX``` must be in integer from 1 to total number of tasks
- ```INDEX``` must not be empty

Example:
- ```delete 1```

### List all tasks: ```list```
Displays all the current tasks to the user and shows whether each task is completed.

### Find tasks: ```find KEYWORD```
Finds tasks that contain the keyword in the description.
- ```KEYWORD``` must not be empty

Examples:
- ```find dinner```

### Exiting the chatbot: ```quit```
Exits Chris and saves the tasks.