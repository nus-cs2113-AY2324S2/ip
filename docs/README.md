# JunBot

JunBot is a **desktop app for task management, created for use via a Command Line Interface (CLI).** If you can type fast, JunBot can speed up your task management.

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a Todo task](#adding-a-todo-task)
    - [Adding a Deadline task](#adding-a-deadline-task)
    - [Adding an Event task](#adding-an-event-task)
    - [Listing all tasks](#listing-all-tasks)
    - [Locating tasks by name](#locating-tasks-by-name)
    - [Deleting a task](#deleting-a-task)
    - [Marking a task](#marking-a-task)
    - [Unmarking a task](#unmarking-a-task)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 installed on your computer.
2. Download the latest `JunBot.jar` from [here](#) (replace "#" with the actual link).
3. Copy the file to the folder you want to use as the home folder for your JunBot.
4. Open a command terminal, navigate to the folder containing the JAR file, and run the command `java -jar JunBot.jar` to execute the application.
5. Type the desired command in the command box and press Enter to execute it. For example, typing `list` and pressing Enter will display all tasks in the list.
   Some example commands you can try:
    - `todo find a job`: Adds a todo task to the current list
    - `deadline assignment /by 5/6/2024`: Adds a deadline task named "assignment" with the due date set to 5/6/2024
    - `bye`: Exits the app.

6. Refer to the [Features](#features) section below for details on each command.

---

## Features

### Adding a Todo task

Adds a todo task to the task list.
- Format: `todo TASK`
- Examples:
    - `todo find a job`
    - `todo do the laundry`

### Adding a Deadline task

Adds a deadline task to the task list.
- Format: `deadline TASK /by DATE`
- Examples:
    - `deadline find a job /by 5/11/2024`: Adds a task named "find a job" with a deadline of 5/11/2024.
    - `deadline assignment /by 10/11/2024`: Adds a task named "assignment" with a deadline of 10/11/2024.

### Adding an Event task

Adds an event task to the task list.
- Format: `event TASK /from START /to END`
- Examples:
    - `event class /from 05/12/2024 /to 06/12/2024`: Adds a task named "class" with a start date of 05/12/2024 and an end date of 06/12/2024.

### Listing all tasks

Shows a list of all tasks in the task list.
- Format: `list`

### Locating tasks by name

Finds tasks whose names contain the given keyword.
- Format: `find KEYWORD`
- Examples:
    - `find job` returns tasks with names containing "job".

### Deleting a task

Deletes the specified task from the task list.
- Format: `delete INDEX`
- Examples:
    - `list` followed by `delete 2` deletes the second task in the task list.

### Marking a task

Marks the specified task from the task list.
- Format: `mark INDEX`
- Examples:
    - `list` followed by `mark 2` marks the second task in the task list.

### Unmarking a task

Unmarks the specified task from the task list.
- Format: `unmark INDEX`
- Examples:
    - `list` followed by `unmark 2` unmarks the second task in the task list.

### Saving the data

JunBot data is automatically saved to the hard disk after any command that changes the data. There is no need to save manually.

### Editing the data file

JunBot data is saved automatically as a text file `[JAR file location]/data/tasks.txt`. Advanced users are welcome to update the data directly by editing this file. However, caution is advised as certain edits may cause JunBot to behave unexpectedly.

---

## FAQ

***Q:*** How do I transfer my data to another computer?  
***A:*** Install the app on the other computer and run it once. Simply overwrite the `tasks.txt` file that JunBot creates with your previous `tasks.txt`.

---

## Known issues

1. **When setting dates in deadline and event tasks**, users can input out-of-order dates, etc. (`event task /from 05/10/2024 /to 05/10/1999`). If unintended dates are inputted, the remedy is to delete the corresponding task and create it again with the corrected dates.

---

## Command Summary

| Action            | Format, Examples                                                                          |
|-------------------|-------------------------------------------------------------------------------------------|
| Add todo task     | `todo TASK` <br/> e.g. `todo find a job`                                                  |
| Add deadline task | `deadline TASK /by DATE` <br/> e.g. `deadline find a job /by 5/11/2024`                   |
| Add event task    | `event TASK /from START /to END` <br/> e.g. `event class /from 05/12/2024 /to 06/12/2024` |
| Mark              | `mark INDEX` <br/> e.g.  `mark 2`                                                         |
| Unmark            | `unmark INDEX` <br/> e.g.  `unmark 2`                                                     |
| Delete            | `delete INDEX` <br/> e.g.  `delete 2`                                                     |
| Find              | `find KEYWORD` <br/> e.g.  `find job`                                                     |
| List              | `list`                                                                                    |                     