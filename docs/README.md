# Schmidt User Guide
Schmidt is a task manager CLI application that helps you to keep track of your tasks. It is designed to be simple and easy to use, and it is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Schmidt can help you manage your tasks faster than traditional GUI apps.
* [Quick Start](#quick-start)
* [Features](#features)
  * [Viewing Help: `help`](#viewing-help-help) 
  * [Adding a task: `todo`, `deadline`, `event`](#adding-a-task-todo-deadline-event)
    * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an event task: `event`](#adding-an-event-task-event)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Marking a task as done or undone: `mark`, `unmark`](#marking-a-task-as-done-or-undone-mark-unmark)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Finding a task: `find`](#finding-a-task-find)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
* [Known Issues](#known-issues)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `schmidt.jar` from [here](https://github.com/mihirheda02/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your Schmidt.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   ```
    ░██████╗░█████╗░██╗░░██╗███╗░░░███╗██╗██████╗░████████╗
    ██╔════╝██╔══██╗██║░░██║████╗░████║██║██╔══██╗╚══██╔══╝
    ╚█████╗░██║░░╚═╝███████║██╔████╔██║██║██║░░██║░░░██║░░░
    ░╚═══██╗██║░░██╗██╔══██║██║╚██╔╝██║██║██║░░██║░░░██║░░░
    ██████╔╝╚█████╔╝██║░░██║██║░╚═╝░██║██║██████╔╝░░░██║░░░
    ╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═════╝░░░░╚═╝░░░
    ------------------------------------------------------------
    Hello! I'm Schmidt
    What can I do for you?
    ------------------------------------------------------------
        ->
   ```
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will display the help message.
6. Refer to the [Features](#features) below for details of each command.

## Features

> [!NOTE]
> **Notes about the command format**:
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.\
>   e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

### Viewing Help: `help`
Shows a message explaining the usage of each command.\
Format: `help`
```
------------------------------------------------------------
The valid commands you can use are:
	bye
	list
	todo <description>
	deadline <description> /by <date>
	event <description> /from <date> /to <date>
	mark <task number>
	unmark <task number>
	delete <task number>
------------------------------------------------------------
```

### Adding a task: `todo`, `deadline`, `event`
#### Adding a todo task: `todo`
Adds a todo task to the task list.\
Format: `todo DESCRIPTION`
* Example of usage: 
  * `todo read book`
#### Adding a deadline task: `deadline`
Adds a deadline task to the task list.\
Format: `deadline DESCRIPTION /by DATE`
* Example of usage: 
  * `deadline return book /by 2021-09-30`
#### Adding an event task: `event`
Adds an event task to the task list.\
Format: `event DESCRIPTION /from DATE /to DATE`\
* Example of usage: 
  * `event project meeting /from 2021-09-30 /to 2021-10-01`

### Listing all tasks: `list`
Shows a list of all tasks in the task list.\
Format: `list`

### Marking a task as done or undone: `mark`, `unmark`
#### Marking a task as done: `mark`
Marks a task as done using its index from the task list.\
Format: `mark INDEX`
#### Marking a task as undone: `unmark`
Marks a task as undone using its index from the task list.\
Format: `unmark INDEX`
* Examples of usage: 
  * `mark 1`
  * `unmark 4`

### Deleting a task: `delete`
Deletes a task from the task list using its index from the task list.\
Format: `delete INDEX`
* Example of usage: 
  * `delete 3`

### Finding a task: `find`
Finds tasks whose description or date contains the given keyword.\
Format: `find KEYWORD`
* Example of usage: 
  * `find book`

### Exiting the program: `bye`
Exits the program.\
Format: `bye`

### Saving the data
Schmidt data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
Schmidt data are saved as a text file `[JAR file location]/data/tasks.txt`. Advanced users are welcome to update the data directly by editing that file.

> [!CAUTION]
> If you choose to edit the data file directly, please follow the format specified in the file. Otherwise, the data may not be loaded correctly.

## Known Issues
1. The program does not support the use of the `|` character in the description or dates of tasks as it is used as a delimiter in the data file.

## Command Summary

| Action         | Format, Examples                                                                                     |
|----------------|------------------------------------------------------------------------------------------------------|
| Help           | `help`                                                                                               |
| Add todo       | `todo DESCRIPTION` e.g. `todo read book`                                                             |
| Add deadline   | `deadline DESCRIPTION /by DATE` e.g. `deadline return book /by 2021-09-30`                           |
| Add event      | `event DESCRIPTION /from DATE /to DATE` e.g. `event project meeting /from 2021-09-30 /to 2021-10-01` |
| List           | `list`                                                                                               |
| Mark as done   | `mark INDEX` e.g. `mark 1`                                                                           |
| Mark as undone | `unmark INDEX` e.g. `unmark 4`                                                                       |
| Delete         | `delete INDEX` e.g. `delete 3`                                                                       |
| Find           | `find KEYWORD` e.g. `find book`                                                                      |
| Exit           | `bye`                                                                                                |
