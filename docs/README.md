# Nick Chatbot User Guide

Nick Chatbot is a task manager desktop app, optimized for use via a Command Line Interface (CLI). 
If you can type fast, Nick Chatbot can help manage your day-to-day tasks faster than ever. 

* [Quick Start](#Quick-Start)
* [Features](#Features)
  * [Adding task:](#adding-task-todo)
    * [`Todo`](#adding-task-todo)
    * [`Deadline`](#adding-task-deadline)
    * [`Event`](#adding-task-event)
  * [List all tasks: `list`](#list-all-tasks-list)
  * [Find tasks by keyword: `find`](#find-tasks-by-keyword-find)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Marking a task: `mark`](#marking-a-task-mark)
  * [Un-marking a task: `unmark`](#un-marking-a-task-unmark)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
  * [Saving the tasks](#saving-the-tasks)
  * [Loading the tasks](#loading-the-tasks)

---

## Quick Start
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest nick.jar.
3. Copy the file to the folder you want to use as the home folder for your Nick Chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar nick.jar`
command to run the application.
5. Type any command and press Enter to execute it.
6. Refer to the [Features](#Features) below for details of each command.

## Features 

### Adding task: `todo`
Adds a Todo task.

Format: `todo [Todo task]`

Examples:
* `todo buy books`

### Adding task: `deadline`
Adds a Deadline task. State the deadline after `/by`.

Format: `deadline [Deadline task] /by [deadline]`

Examples:
* `deadline finish CS2113T assignment /by 2024-03-01`
* `deadline group project /by Monday afternoon`

### Adding task: `event`
Adds an Event task. State the event duration after `/to` and after `/from`.

Format: `event [Event task] /from [from timing] /to [to timing]`

Examples:
* `event Programming workshop /from Monday 10am /to Monday 1pm`

### List all tasks: `list`
Shows a list of all tasks in the Nick Chatbot.

Format: `list`

### Find tasks by keyword: `find`
Find related tasks from the specified keyword.

Format: `find [keyword]`

### Deleting a task: `delete`
Delete a task from index.

Format: `delete [index]`

Examples:
* `delete 1`

### Marking a task: `mark`
Mark a task as done.

Format: `mark 1`

### Un-marking a task: `unmark`
Unmark a task as undone.

Format: `unmark 1`

### Exiting the program: `bye`
Exit Nick Chatbot.

Format: `bye`

### Saving the tasks
Nick Chatbot tasks are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Loading the tasks
Nick Chatbot loads the tasks instantly when it starts up. There is no need to load manually.
