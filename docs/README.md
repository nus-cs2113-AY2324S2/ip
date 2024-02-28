# JunBot

JunBot is a **desktop app for task management, created for use via a Command Line Interface (CLI).** If you can type fast, JunBot can speed up your task management.

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a Todo task](#adding-a-todo-task--todo)
    - [Adding a Deadline task](#adding-a-deadline-task--deadline)
    - [Adding an Event task](#adding-a-event-task---event)
    - [Listing all tasks](#listing-all-tasks--list)
    - [Locating tasks by name](#locating-tasks-by-name--find)
    - [Deleting a task](#deleting-a-task--delete)
    - [Marking a task](#marking-a-task--mark)
    - [Unmarking a task](#unmarking-a-task--unmark)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 installed on your Computer
2. Download the latest `JunBot.jar` from [here](#)
3. Copy the file to the folder you want to use as the home folder for your JunBot
4. Open a command terminal, `cd` into the folder containing the jar file, and use the command `java -jar JunBot.jar` to run the application.
5. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing Enter will open all tasks in the list.
   Some example commands you can try:
    - `todo find a job`: Adds a todo task to the current list
    - `deadline assignment /by 5/6/2024`: Adds a deadline task named "assignment" with the due date as 5/6/2024 to the current list
    - `bye`: Exits the app.

6. Refer to the [Features](#features) below for details of each command

---

## Features

### Adding a Todo task : `todo`

Adds a todo task to the task list
Format: `todo TASK`

Examples:
- `todo find a job`
- `todo do the laundry`

### Adding a Deadline task : `deadline`

Adds a deadline task to the task list  
Format: `deadline TASK /by DATE`

- Adds a task with name `TASK` with a deadline of `DATE`.
- `DATE` should be in the format of `d/m/yyyy`

Examples:
- `deadline find a job /by 5/11/2024`: Adds a task called find a job, with a deadline of 5/11/2024
- `deadline assignment /by 10/11/2024`: Adds a task called assignment with a deadline of 10/11/2024

### Adding an Event task : `event`

Adds an Event task to the task list  
Format: `event TASK /from START /to END`

- Adds a task with name `TASK`, with a start date of `START` and end date of `END`
- Both `START` and `END` should be in the format of `d/m/yyyy`

Examples:
- `event class /from 05/12/2024 /to 06/12/2024`: Adds a task called class, with a start date of 05/12/2024 and end date of 06/12/2024

### Listing all tasks : `list`

Shows a list of all tasks in the task list  
Format: `list`

### Locating tasks by name : `find`

Finds tasks whose names contain the given keyword  
Format: `find KEYWORD`

- The search is case-sensitive e.g. `job` will not match `Job`
- Similar words will be matched e.g. `job` will match `jobbers`

Examples:
- `find job` returns `job` and `jobbers`

### Deleting a task : `delete`

Deletes the specified task from the task list  
Format: `delete INDEX`

- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer and a valid list number
- The index number is always relative to the displayed task list from the use of `list`

Examples:
- `list` followed by `delete 2` deletes the 2nd task in the task list

### Marking a task : `mark`

Marks the specified task from the task list  
Format: `mark INDEX`

- Marks the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer and a valid list number
- The index number is always relative to the displayed task list from the use of `list`

Examples:
- `list` followed by `mark 2` marks the 2nd task in the task list

### Unmarking a task : `unmark`

Marks the specified task from the task list  
Format: `unmark INDEX`

- Unmarks the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer and a valid list number
- The index number is always relative to the displayed task list from the use of `list`

Examples:
- `list` followed by `unmark 2` marks the 2nd task in the task list

### Saving the data

JunBot data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

JunBot data is saved automatically as a txt file `[JAR file location]/data/tasks.txt`. Advanced users are welcome to update data directly by editing that data file.

> ⚠️ **Caution:** Certain edits can cause JunBot to behave in unexpected ways (e.g. if value entered is outside acceptable range, or the entries are not in the right format). Therefore, edit the data file only if you are confident that you can update it correctly

---

## FAQ

***Q:*** How do I transfer my data to another Computer?  
***A:*** Install the app on the other computer and run it once. Simply overwrite the `tasks.txt` file that JunBot creates with your previous `tasks.txt`.

---

## Known issues

1. ***When setting dates in deadline and event***, users can input out-of-order dates, etc. `event task /from 05/10/2024 /to 05/10/1999`
   Should unintended dates be inputted, the remedy is to delete the corresponding task and create it again with the corrected dates.

---

## Command Summary

| Action             | Format, Examples                                                                          |
|--------------------|-------------------------------------------------------------------------------------------|
| Add todo task      | `todo TASK` <br/> e.g. `todo find a job`                                                 |
| Add deadline task  | `deadline TASK /by DATE` <br/> e.g. `deadline find a job /by 5/11/2024`                   |
| Add event task     | `event TASK /from START /to END` <br/> e.g. `event class /from 05/12/2024 /to 06/12/2024` |
| Mark               | `mark INDEX` <br/> e.g.  `mark 2`                                                        |
| Unmark             | `unmark INDEX` <br/> e.g.  `unmark 2`                                                    |
| Delete             | `delete INDEX` <br/> e.g.  `delete 2`                                                    |
| Find               | `find KEYWORD` <br/> e.g.  `find job`                                                    |
| List               | `list`                                                                                    |
