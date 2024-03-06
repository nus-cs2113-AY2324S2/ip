# Mona User Guide

&nbsp;

Mona is a Command Line Interface (CLI) chatbot application designed to streamline task management with speed and simplicity. Named after the intuitive and resourceful character 'Mona' from the beloved TV series 'Nanalan', our application embodies the efficiency and helpfulness that Mona showcased in her adventures.

&nbsp;

- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a task](#adding-a-task)
    - [Adding a todo: `todo`](#adding-a-todo-todo)
    - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    - [Adding an event: `event`](#adding-an-event-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Locating a task by description: `find`](#locating-a-task-by-description-find)
  - [Deleting a Task: `delete`](#deleting-a-task-delete)
  - [Marking a Task as Done: `mark`](#marking-a-task-as-done-mark)
  - [Marking a Task as Not Done: `unmark`](#marking-a-task-as-not-done-unmark)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)
- [Command summary](#command-summary)

&nbsp;

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of Mona from [GitHub](https://github.com/nur-haziq/ip).
3. Open a terminal and navigate to the folder where you downloaded Mona.
4. Run the application by entering `java -jar Mona.jar` in the terminal.
5. Type `help` to see a list of available commands.

&nbsp;

## Features

> **Notes about the command format:**
> 
> - Words in `UPPER_CASE` are the parameters that must be supplied by the user. These parameters CANNOT be left empty.
>   e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo borrow book`.
> 
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, and `bye`) will be ignored.
>   e.g., if the command specifies `help 123`, it will be interpreted as `help`.
> 
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

&nbsp;

### Viewing Help: `help`

Shows a list of all available commands and their usage.

**Format:** `help`

&nbsp;

### Adding a Task

There are three types of tasks you can add: `todo`, `deadline`, and `event`.
1. **ToDos**: These are tasks that do not have a specific date or time associated with them. They represent tasks that do not have a scheduled time for completion.
2. **Deadlines**: These tasks have a specific due date and time. They represent tasks that you need to complete by a certain deadline.
3. **Events**: These are tasks that occur over a range of time, having both a start date/time and an end date/time.

&nbsp;

#### Adding a ToDo: `todo`

Adds a ToDo task to your task list.

**Format:** `todo DESCRIPTION`
- `DESCRIPTION`: The description of the todo task.

**Examples:**

- `todo Pick up groceries`
  Adds a task with the description "Pick up groceries" to your list. There is no specific date or time associated with a ToDo task. It is assumed that you can complete this task at any time.

&nbsp;

#### Adding a Deadline: `deadline`

Adds a deadline task to your task list.

**Format:** `deadline /by DATE`
- `DESCRIPTION`: The description of the deadline task.
- `DATE`: The due date of the deadline task.

**Examples:**

- `deadline Submit final project /by 20/12/2024`
  Adds a task with the description "Submit final project" that has a due date of December 20, 2024. A deadline task specifies when it needs to be completed.

> **Pro tip:** If you enter the date in the `yyyy-MM-dd HHmm` format (e.g., `2024-12-31 1830`), the due date will be stored as a `LocalDateTime` object, allowing for precise date and time tracking.
  
&nbsp;

#### Adding an Event: `event`

Adds an event task to your task list.

**Format:** `event /from FROM_DATE /to TO_DATE`
- `DESCRIPTION`: The description of the event task.
- `FROM_DATE`: The date/time of the START of the event.
- `TO_DATE`: The date/time of the END of the event.
- The `event`, `/from`, and `/to` flags must be in the correct order.

**Examples:**

- `event Sister's wedding /from 12/12/2024 11am /to 12/12/2024 3pm`  
  Adds an event with the description "Sisters wedding". The event starts at 11 AM and ends at 3 PM on December 12, 2024.

&nbsp;

### Listing All Tasks: `list`

Shows a list of all tasks in your task list.

**Format:** `list`

&nbsp;

### Locating a Task by description: `find`

Finds tasks whose descriptions contain the given keyword as a substring.

**Format:** `find KEYWORD`
- `KEYWORD`: The keyword to search for within the task descriptions.
- The search is not case-sensitive, meaning it will find tasks regardless of whether the keyword is in uppercase or lowercase. For example, `find meet` will return "meeting", "Meet", "meetings", and "Meetings".
> **Note:** The indexes of the tasks found may not be sequential as they correspond to their positions in the original complete list of tasks. For example, if three tasks contain a given keyword, they might be indexed as 2, 4, 6, (instead of 1, 2, 3) to reflect their original order. This helps you identify the correct index for other commands like `delete`, `mark`, or `unmark`.


**Examples:**

- `find budget`  
  This will return tasks with descriptions containing "budget", "Budget", "budgeting", or "Budgeting".

&nbsp;

### Deleting a Task: `delete`

Deletes a task from your task list.

**Format:** `delete INDEX`
- `INDEX`: The index of the task to be deleted.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer (1, 2, 3, ...).
- The index must not be out of bounds (not more than the total number of tasks stored).

**Examples:**

- `list` followed by `delete 3`  
  This will delete the 3rd task in the list displayed by the `list` command.

&nbsp;

### Marking a Task as Done: `mark`

Marks a task as done.

**Format:** `mark INDEX`
- `INDEX`: The index of the task to be marked as done, as shown by the `list` command.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer (1, 2, 3, ...).
- The index must not be out of bounds (not more than the total number of tasks stored).

**Examples:**

- `list` followed by `mark 2`  
  This will mark the 2nd task in the task list as done.

&nbsp;

### Marking a Task as Not Done: `unmark`

Marks a task as not done.

**Format:** `unmark INDEX`
- `INDEX`: The index of the task to be marked as not done, as shown by the `list` command.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer (1, 2, 3, ...).
- The index must not be out of bounds (not more than the total number of tasks stored).

**Examples:**

- `list` followed by `unmark 4`  
  Marks the 4th task in the task list as not done.

&nbsp;

### Exiting the program: `bye`

Exits the application.

**Format:** `bye`

&nbsp;

### Saving the Data

The data is saved automatically after any command that modifies the task list (e.g., `add`, `delete`, `mark`, `unmark`). There is no need to save manually.

&nbsp;

## Command Summary

| Command   | Usage                                             |
|-----------|---------------------------------------------------|
| `help`    | `help`                                            |
| `todo`    | `todo DESCRIPTION`                                |
| `deadline`| `deadline DESCRIPTION /by DATE`                   |
| `event`   | `event DESCRIPTION /from FROM_DATE /to TO_DATE`   |
| `list`    | `list`                                            |
| `find`    | `find KEYWORD`                                    |
| `delete`  | `delete INDEX`                                    |
| `mark`    | `mark INDEX`                                      |
| `unmark`  | `unmark INDEX`                                    |
| `bye`     | `bye`                                             |


