# Dul Chatbot User Guide

Dul is a Command Line Interface (CLI) chatbot that manages your tasks

&nbsp;

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a todo: `todo`](#adding-a-todo-todo)
    - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    - [Adding an event: `event`](#adding-an-event-event)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    - [Finding tasks: `find`](#finding-tasks-find)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)

&nbsp;

## Quick Start

1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest Dul.jar from [here](#).
3. Copy the file into the folder that you wish to use this application in
4. Open a terminal, `cd` into the folder you put the jar file in, and use `java -jar Dul.jar` command to run chatbot.
5. Refer to the [features](#features) below for implementations of each command.

&nbsp;

## Features

### Adding a todo: `todo`

Adds a todo task to the task list. Todo tasks are tasks that have a description

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo read book`

&nbsp;

### Adding a deadline: `deadline`

Adds a task with a deadline. Deadline tasks need to be done before a date/time.

Format: `deadline TASK_DESCRIPTION /by TASK_DEADLINE`

Examples:
- `deadline write essay /by 2023-03-10`

&nbsp;

### Adding an event: `event`

Adds an event task. Event tasks start on a date/time and end on a date/time.

Format: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`

Examples:
- `event art workshop /from 2023-03-15 /to 2023-03-16`

&nbsp;

### Listing all tasks: `list`

Lists all tasks.

Format: `list`

&nbsp;

### Marking a task as done: `mark`

Marks a task as done using task number from 'list' command.

Format: `mark TASK_INDEX`

&nbsp;

### Unmarking a task: `unmark`

Unmarks a task using task number from 'list' command.

Format: `unmark TASK_INDEX`

&nbsp;

### Finding tasks: `find`

Finds tasks that contain the given keyword.

Format: `find KEYWORD`

Examples:
- `find plate`

&nbsp;

### Deleting a task: `delete`

Deletes the specified task using task number from 'list' command.

Format: `delete TASK_INDEX`

Examples:
- `delete 1`

&nbsp;

### Exiting the program: `bye`

Exits the program.

Format: `bye`

&nbsp;

### Saving the data

Dul automatically saves tasks in hard disk after each command for the task list.

&nbsp;

## FAQ

**Q:** How do I transfer my data to another computer? </br>

**A:** Install the app on the other computer and overwrite the empty data file
it creates with the file that contains the data.

&nbsp;

## Command summary

| **Commands** | **Usage**                                                                                   |
|--------------|---------------------------------------------------------------------------------------------|
| **todo**     | `todo <TASK_DESCRIPTION>`                                                                   |
| **deadline** | `deadline <TASK_DESCRIPTION> /by <TASK_DEADLINE>`                                           |
| **event**    | `event <TASK_DESCRIPTION> /from <START_DATE> /to <END_DATE>`                                |
| **list**     | `list`                                                                                      |
| **mark**     | `mark <TASK_INDEX>`                                                                         |
| **unmark**   | `unmark <TASK_INDEX>`                                                                       |
| **find**     | `find <TASK_DESCRIPTION>`                                                                   |
| **delete**   | `delete <TASK_INDEX>`                                                                       |
| **bye**      | `bye`                                                                                       |