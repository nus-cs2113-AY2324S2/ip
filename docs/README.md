# Alexis Chatbot User Guide

Alexis is a Command Line Interface (CLI) chatbot that helps you manage your tasks. If you can type fast, Alexis can get 
your task management done faster than a traditional Graphical User Interface (GUI) application. This guide will help 
you get started with Alexis and understand its key features.

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
2. Download the latest Alexis.jar from [here](https://github.com/imanamirshah/ip/releases/tag/A-Release).
3. Copy the file into the folder that you wish to use this application in
4. Open a terminal, `cd` into the folder you put the jar file in, and use `java -jar Alexis.jar` command to run chatbot.
5. Refer to the [features](#features) below for implementations of each command.

&nbsp;

## Features

> **Notes about the command format:**
>
> - Words in `<UPPER_CASE>` are the parameters to be supplied by the user.
    >   e.g. in `todo <TASK_DESCRIPTION>`, `<TASK_DESCRIPTION>` is a parameter which can be used as `todo Read a book`.
> - Parameters must be in the specified order.
    >   e.g. if the command specifies `/from <START_DATE> /to <END_DATE>`, parameters must be supplied in that order.
`/to <END_DATE> /from <START_DATE>` is not acceptable.
> - Dates and times specified in the following format `yyyy-MM-dd HHmm` will be automatically converted to 
`MMM dd yyyy, K.mm a` in the task list
    >   e.g. if you have given `2024-02-05 1334` as your deadline, it will be displayed as `Feb 05 2024. 1.34 PM`.
> - Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
    >   e.g. if the command specifies `list 123`, it will be interpreted as `list`.
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple 
lines as space characters surrounding line-breaks may be omitted when copied over to the application.

&nbsp;

### Adding a todo: `todo`

Adds a todo task to the task list. Todo tasks are tasks that have no date/time attached to it

Format: `todo <DESCRIPTION>`

Examples:
- `todo Read a book`

&nbsp;

### Adding a deadline: `deadline`

Adds a task with a deadline. Deadline tasks are tasks that need to be done before a specific date/time.

Format: `deadline <DESCRIPTION> /by <DATE>`

Examples:
- `deadline Submit report /by 2023-03-10`

&nbsp;

### Adding an event: `event`

Adds an event task. Event tasks are tasks that start at a specific date/time and ends at a specific date/time.

Format: `event <DESCRIPTION> /from <START_DATE> /to <END_DATE>`

Examples:
- `event Team camp /from 2023-03-15 /to 2023-03-16`

&nbsp;

### Listing all tasks: `list`

Shows a list of all tasks.

Format: `list`

&nbsp;

### Marking a task as done: `mark`

Marks a task as done.

Format: `mark <TASK_INDEX>`

- Marks the task at the specified TASK_INDEX.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, …

Examples:
- `mark 2`

&nbsp;

### Unmarking a task: `unmark`

Unmarks a task.

- Marks the task at the specified TASK_INDEX.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, …

Format: `unmark <TASK_INDEX>`

Examples:
- `unmark 2`

&nbsp;

### Finding tasks: `find`

Finds tasks that contain the given keyword.

Format: `find <KEYWORD>`

- The search is case-insensitive. e.g. `book` will match `Book`
- The order of the keywords matters. e.g. `read book` will not match `book read`
- Only the task description is searched.

Examples:
- `find book`

&nbsp;

### Deleting a task: `delete`

Deletes the specified task.

Format: `delete <TASK_INDEX>`

- Deletes the task at the specified TASK_INDEX.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, …

Examples:
- `delete 3`

&nbsp;

### Exiting the program: `bye`

Exits the program.

Format: `bye`

&nbsp;

### Saving the data

Alexis automatically saves your tasks in the hard disk after any command that changes the task list. There is no need 
to save manually.

&nbsp;

## FAQ

**Q:** How do I transfer my data to another computer  
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
