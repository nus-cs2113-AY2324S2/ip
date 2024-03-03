# User Guide
Bart is a desktop app for listing out your everyday tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

## Features 

### Viewing help: `help`

Shows a list of available commands.

Format: `help`

### Adding Tasks and their types: `todo` `deadline` `event`

Adds task into a list.

Todo Format: `todo [TASK]`

Deadline Format: `deadline [TASK] /by [TIME]`

Event Format: `event [TASK] /from [START_TIME] /to [END_TIME]`

Examples:
- `todo CS2113 Lectures`
- `deadline Rob the bank /by 12 noon`
- `event Boston Tea Party /from 7pm 16/12/1776 /to 10pm 16/12/1776`

### Listing all tasks: `list`

Shows a list of tasks.

Format: `list`

### Deleting a task: `delete`

Deletes a task at given index.

Format: `delete [INDEX]`

Example: 
- `delete 3` Deletes the 3rd task in the list.

### Marking and Unmarking: `mark` `unmark`

Marks or unmarks a task in the list at given index.

Mark Format: `mark [INDEX]`

Unmark Format: `unmark [INDEX]`

Examples:
- `mark 7` Marks the 7th task in the list.
-  `unmark 2` Removes the mark in the 2nd task in the list.

### Exit and save Bart: `bye`

Exits the program and saves the list in a file located in `./data/Bart.txt`. 

This save file is automatically loaded when Bart is opened again
