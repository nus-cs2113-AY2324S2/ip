# Alice Chatbot User guide

Hello! Feel free to use this bot to track your tasks :D

## Features

- Add different types of tasks: `Todo`, `Deadline`, and `Event`.
- Mark tasks as done or undone
- Delete tasks from your list
- Find tasks with a keyword search
- View all tasks in a neatly formatted list
- Save tasks persistently, so that they are not lost after the program ends

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   - Click `Open`.
   - Select the project directory, and click `OK`.
   - If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Alice.java` file, right-click it, and choose `Run Alice.main()`
5. If everything was set up correctly, you will be able to see this dialog:
____________________________________________________________
yoo i'm alice! ur virtual bestie here to keep track of ur vibes
what's poppin? u can tell me stuff to remember, or type 'bye' to dip
____________________________________________________________

## Possible Commands

- `list`: Display a list of all the tasks.
   - **Example**: `list`

- `todo [description]`: Add a new 'Todo' task with the specified description.
   - **Example**: `todo Read a book`

- `deadline [description] /by [date, time]`: Add a new 'Deadline' task with a specified due date and time.
   - **Example**: `deadline Return library books /by 2022-12-02 1800`

- `event [description] /from [start time] /to [end time]`: Add a new 'Event' task with a specified start and end time.
   - **Example**: `event Team meeting /from 2022-12-05 10:00 /to 2022-12-05 11:00`

- `mark [task number]`: Mark a task as completed.
   - **Example**: `mark 3`

- `unmark [task number]`: Revert a task to an uncompleted status.
   - **Example**: `unmark 3`

- `delete [task number]`: Remove a task from the list.
   - **Example**: `delete 4`

- `find [keyword]`: Search for tasks containing the specified keyword.
   - **Example**: `find book`

- `bye`: Exit the chatbot application.
   - **Example**: `bye`

Use these commands to manage your tasks with the chatbot. For each command, refer to the provided examples for guidance.
