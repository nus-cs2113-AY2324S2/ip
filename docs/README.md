# Mona User Guide

Mona is a Command Line Interface (CLI) chatbot application that helps you manage your tasks efficiently. This user guide will help you get started with using Mona and its features.

- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help](#viewing-help-help)
  - [Adding a task](#adding-a-task)
  - [Adding a todo](#adding-a-todo-todo)
  - [Adding a deadline](#adding-a-deadline-deadline)
  - [Adding an event](#adding-an-event-event)
  - [Listing all tasks](#listing-all-tasks-list)
  - [Locating a task by description](#locating-a-task-by-description-find)
  - [Deleting a Task](#deleting-a-task-delete)
  - [Marking a Task as Done](#marking-a-task-as-done-mark)
  - [Marking a Task as Not Done](#marking-a-task-as-not-done-unmark)
  - [Exiting the program](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)
- [Command summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of Mona from [GitHub](https://github.com/your-repo-link).
3. Open a terminal and navigate to the folder where you downloaded Mona.
4. Run the application by entering `java -jar Mona.jar` in the terminal.
5. Type `help` to see a list of available commands.

&nbsp;

## Features

> :information_source: **Notes about the command format:**
> 
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
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

&nbsp;

#### Adding a Todo: `todo`

Adds a todo task to your task list.

**Format:** `todo DESCRIPTION`
- `DESCRIPTION`: The description of the todo task.

&nbsp;

#### Adding a Deadline: `deadline`

Adds a deadline task to your task list.

**Format:** `deadline /by DATE`
- `DESCRIPTION`: The description of the deadline task.
- `DATE`: The due date of the deadline task.

&nbsp;

#### Adding an Event: `event`

Adds an event task to your task list.

**Format:** `event /from FROM_DATE /to TO_DATE`
- `DESCRIPTION`: The description of the event task.
- `FROM_DATE`: The date/time of the START of the event.
- `TO_DATE`: The date/time of the END of the event.

&nbsp;

### Listing All Tasks: `list`

Shows a list of all tasks in your task list.

**Format:** `list`

&nbsp;

### Locating a Task by description: `find`

Finds tasks whose descriptions contain the given keyword.

**Format:** `find KEYWORD`
- `KEYWORD`: The keyword to search for in the task descriptions.

&nbsp;

### Deleting a Task: `delete`

Deletes a task from your task list.

**Format:** `delete INDEX`
- `INDEX`: The index of the task to be deleted, as shown by the `list` command.

&nbsp;

### Marking a Task as Done: `mark`

Marks a task as done.

**Format:** `mark INDEX`
- `INDEX`: The index of the task to be marked as done, as shown by the `list` command.

&nbsp;

### Marking a Task as Not Done: `unmark`

Marks a task as not done.

**Format:** `unmark INDEX`
- `INDEX`: The index of the task to be marked as not done, as shown by the `list` command.

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
| `todo`    | `todo [DESCRIPTION]`                              |
| `deadline`| `deadline [DESCRIPTION] /by [DATE]`               |
| `event`   | `event [DESCRIPTION] /at [DATE]`                  |
| `list`    | `list`                                            |
| `find`    | `find [KEYWORD]`                                  |
| `delete`  | `delete [INDEX]`                                  |
| `mark`    | `mark [INDEX]`                                    |
| `unmark`  | `unmark [INDEX]`                                  |
| `bye`     | `bye`                                             |


