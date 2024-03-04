# Mona User Guide

Mona is a Command Line Interface (CLI) chatbot application that helps you manage your tasks efficiently. This user guide will help you get started with using Mona and its features.

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of Mona from [GitHub](https://github.com/your-repo-link).
3. Open a terminal and navigate to the folder where you downloaded Mona.
4. Run the application by entering `java -jar Mona.jar` in the terminal.
5. Type `help` to see a list of available commands.

## Features

### Viewing Help: `help`

Shows a list of all available commands and their usage.

**Format:**

&nbsp;

### Adding a Task

There are three types of tasks you can add: `todo`, `deadline`, and `event`.

&nbsp;

#### Adding a Todo: `todo`

Adds a todo task to your task list.

**Format:**
- `DESCRIPTION`: The description of the todo task.

&nbsp;

#### Adding a Deadline: `deadline`

Adds a deadline task to your task list.

**Format:**
- `DESCRIPTION`: The description of the deadline task.
- `DATE`: The due date of the deadline task.

&nbsp;

#### Adding an Event: `event`

Adds an event task to your task list.

**Format:**
- `DESCRIPTION`: The description of the event task.
- `DATE`: The date of the event.

&nbsp;

### Listing All Tasks: `list`

Shows a list of all tasks in your task list.

**Format:**  

&nbsp;

### Locating a Task by Name/Description: `find`

Finds tasks whose descriptions contain the given keyword.

**Format:**
- `KEYWORD`: The keyword to search for in the task descriptions.

&nbsp;

### Deleting a Task: `delete`

Deletes a task from your task list.

**Format:**
- `INDEX`: The index of the task to be deleted, as shown by the `list` command.

&nbsp;

### Marking a Task as Done: `mark`

Marks a task as done.

**Format:**
- `INDEX`: The index of the task to be marked as done, as shown by the `list` command.

&nbsp;

### Marking a Task as Not Done: `unmark`

Marks a task as not done.

**Format:**
- `INDEX`: The index of the task to be marked as not done, as shown by the `list` command.

&nbsp;

### Exiting the Program: `bye`

Exits the application.

**Format:**

&nbsp;

### Saving the Data

The data is saved automatically after any command that modifies the task list (e.g., `add`, `delete`, `mark`, `unmark`). There is no need to save manually.

&nbsp;

## Command Summary

| Command   | Usage                                             |
|-----------|---------------------------------------------------|
| `help`    | `help`                                            |
| `todo`    | `todo [DESCRIPTION]`                              |
| `deadline`| `deadline [DESCRIPTION] /by [DATE]`              |
| `event`   | `event [DESCRIPTION] /at [DATE]`                 |
| `list`    | `list`                                            |
| `find`    | `find [KEYWORD]`                                  |
| `delete`  | `delete [INDEX]`                                  |
| `mark`    | `mark [INDEX]`                                    |
| `unmark`  | `unmark [INDEX]`                                  |
| `bye`     | `bye`                                             |


