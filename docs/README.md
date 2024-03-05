# Bob User Guide

Bob is a desktop app for managing tasks, deadlines and events, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Bob can get your task management done faster than traditional GUI apps.

## Features 

### Todos

Keep track of your todos.

### Deadlines

Keep track of your deadlines with a date and time.

### Events

Keep track of your events with a start and end date and time.

## Commands

Note that everything is autosaved to a file called `bob.txt`, so you can close the app and reopen it and your list will still be there.

### `todo`

Add a todo task to your list. Everything after the command
will be saved to the list, no quotations needed.

Example of usage: 

`todo eat an apple a day to keep the doctor away`

Expected outcome:

Saves todo to list.

### `deadline`

Add a deadline task to your list. Everything after the command
will be saved to the list, no quotations needed. 

Example of usage:

`deadline finish homework /by 5pm`

Expected outcome:

Saves deadline to list.

### `event`

Add an event task to your list. Everything after the command
will be saved to the list, no quotations needed.

Example of usage:

`event attend concert /from 5pm /to 7pm`

Expected outcome:

Saves event to list.

### `list`

List all tasks in your list.

Example of usage:

`list`

Expected outcome:

Lists all tasks in your list.

### `mark`

Mark a task as done. Use number after command to denote the item you want to mark.

Example of usage:

`mark 1`

Expected outcome:

Marks the first item in the list as done.

### `unmark`

Unmark a task as done. Use number after command to denote the item you want to unmark.

Example of usage:

`unmark 1`

Expected outcome:

Unmarks the first item in the list as done.

### `delete`

Delete a task from your list. Use number after command to denote the item you want to delete.

Example of usage:

`delete 1`

Expected outcome:

Deletes the first item in the list.

### `find`

Find a task from your list. Everything after the command will be saved to the list, no quotations needed.

Example of usage:

`find apple`

Expected outcome:

Finds all tasks with the word apple in it.

### `bye`

Exits the program.

Example of usage:

`bye`

Expected outcome:

Exits the program.

### `save`

Manually save the list to a file.

Example of usage:

`save`

Expected outcome:

Saves the list to a file.