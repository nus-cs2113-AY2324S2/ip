# User Guide

PeeKay is a desktop app for managing your daily tasks, **optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI)**.
This app is especially catered towards those who can **type fast**,
as you can get your daily tasks done much faster than traditional GUI apps.

PeeKay categorises a `Task` into one of three types - `ToDo`, `Deadline` or `Event`

- Quick Start
- Features
    - Viewing help : `help`
    - Adding a `ToDo` : `todo`
    - Adding a `Deadline` : `deadline`
    - Adding an `Event` : `event`
    - Listing all tasks in the task list : `list`
    - Deleting a task from the task list : `delete`
    - Marking a task as done : `mark`
    - Marking a task as not done : `unmark`
    - Finding tasks using a keyword : `find`
    - Exiting the app : `bye`
- Command Summary

## Quick Start

1. **Download PeeKay:**
  - Download the PeeKay application (JAR file) from here.

2. **Setup Storage File:**
  - Ensure you have a storage file (`peekay.txt`) containing your existing tasks, located in the `src/data/` directory.

3. **Run PeeKay:**
  - Open a command prompt or terminal window.

4. **Navigate to the Directory:**
  - Navigate to the directory where the PeeKay JAR file is located.

5. **Run the Application:**
  - Run the PeeKay application by executing the following command:
    ```
    java -jar PeeKay.jar
    ```

6. **Use PeeKay:**
  - Once the application is running, you will be greeted with a welcome message and a command line interface.
  - For help with PeeKay's commands, enter the `help` command or refer to the features list below!

## Features

> [!NOTE] Notes about the command format:
>
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
    > e.g. in `todo /DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Assignment`.
> - Parameters must be in the specified order.
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `bye`) will be
    ignored.
    e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Viewing help: `help`

Shows a message explaining the different commands.

Format:`help`

Expected Outcome: ![Help Command](/docs/assets/help.png)

### Adding a `ToDo` : `todo`

Adds a ToDo task to the task list.

Format:`todo /DESCRIPTION`

- Adds the ToDo with corresponding `DESCRIPTION` to the task list.

Example:

- `todo assignment`

### Adding a `Deadline`: `deadline`

Adds a Deadline task to the task list.

Format:`deadline /DESCRIPTION /by DEADLINE`

- Adds the Deadline task with corresponding `DESCRIPTION` and `DEADLINE`  the task list.

Examples:

- `deadline go to supermarket /by 7 March 2024`
- `deadline submit internship application /by 2359hrs`

### Adding an `Event`: `event`

Adds an Event task to the task list.

Format: `event /DESCRIPTION /from START_TIME /to END_TIME`

- Adds the Event task with corresponding `DESCRIPTION`, `START_TIME` and `END_TIME` to the task list.

Examples:

- `event lecture /from 7 March 2024 4pm /to 7 March 2024 6pm `
- `event technical interview /from 8pm /to 10pm`

### Listing all tasks in the task list: `list`

Displays all tasks currently stored in the task list, including their type, description, and completion status.

Format: `list`

Expected Outcome:\
![List Command](/docs/assets/list.png)

### Deleting a task from the task list: `delete`

Allows the user to remove a task from the task list based on its index.

Format: `delete INDEX`

- Deletes the task at the given INDEX of the task list
- User can refer to the list using the `list` command

Example:

- `delete 1`

Expected Outcome: \
![Delete Command](/docs/assets/delete.png)

### Marking a task as done: `mark`

Marks a task in the task list as **completed** based on its index.

Format: `mark INDEX`

- Marks the task at the given INDEX of the task list as **completed**
- User can refer to the list using the `list` command

Example:

- `mark 1`

Expected Outcome:\
![Mark Command](/docs/assets/mark.png)

### Marking a task as not done: `unmark`

Marks a task in the task list as **uncompleted** based on its index.

Format: `unmark INDEX`

- Marks the task at the given INDEX of the task list as **uncompleted**
- User can refer to the list using the `list` command

Example:

- `unmark 1`

Expected Outcome:\
![Unmark Command](/docs/assets/unmark.png)

### Finding tasks using a keyword: `find`

Searches for tasks in the task list containing a specific keyword in their description and displays the matched tasks.

Format: `find KEYWORD`

- Searches and prints the task list for task descriptions containing `KEYWORD`

Example:

- `find assignment`
- `find lecture`

Expected Outcome:\
![Find Command](/docs/assets/find.png)

### Exiting the app: `bye`

Saves the current task list to the storage file and exits the app.

Format: `bye`

Expected Outcome:\
![Bye Command](/docs/assets/bye.png)

## Command Summary

- `help` : Displays available commands and their descriptions.
- `todo [DESCRIPTION]` : Adds a ToDo task to the task list.
- `deadline [DESCRIPTION] /by [DEADLINE]` : Adds a Deadline task to the task list with the specified deadline.
- `event [DESCRIPTION] /from [START TIME] /to [END TIME]` : Adds an Event task to the task list with the specified start
  and end times.
- `list` : Displays all tasks in the task list.
- `delete [INDEX]` : Deletes the task at the specified index from the task list.
- `mark [INDEX]` : Marks the task at the specified index as done.
- `unmark [INDEX]` : Marks the task at the specified index as not done.
- `find [KEYWORD]` : Finds tasks containing the specified keyword in their description and displays them.
