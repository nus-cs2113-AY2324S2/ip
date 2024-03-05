# User Guide

Blue is a command line application to manage and keep track of your tasks.

## Quick start
1. Ensure you have Java 11 or above installed.
2. Download the latest `blue.jar`.
3. Move the file to a folder where you want to store your tasks.
4. Open a terminal window and navigate to your previously chosen folder
5. Run `java -jar blue.java`
Note that a folder `data` containing file `tasks.txt` will be created on first use.
6. Refer to Features below on how to use the application.

## Features 

### Adding a general task: `todo`

Adds a general task to the task list.

Format: `todo TASK_DESCRIPTION`

### Adding a deadline task: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

### Adding an event: `event`

Adds an event to the task list.

Format: `event EVENT_DESCRIPTION /from START_TIME /to END_TIME`

### Listing all tasks: `list`

Lists all tasks in the task list.

### Marking a task as done: `mark`

Marks a specified task as done.

Format: `mark TASK_INDEX`

### Finding a task by its description: `find`

Finds tasks with descriptions matching the provided query.

Format: `find QUERY`

Note that not specifying a query (i.e finding with a "" query) is no different from listing all tasks.

### Deleting a task: `delete`

Removes a specified task from the task list.

Format: `delete TASK_INDEX`

### Exiting the application: `bye`

Exits the application, saving the task list to disk.

## Example Usage

### `todo buy stationery` - Adds a task to buy stationery.
Expected output:
```
-------------------------------------------------------------
 added: buy stationery
-------------------------------------------------------------
```

### `deadline post letter /by Tuesday` - Adds a task to post a letter by Tuesday.
Expected output:
```
-------------------------------------------------------------
 added: post letter
-------------------------------------------------------------
```

### `event SFA exhibition on 23 March /from 1700 /to 2000` - Adds an event on 23 March.
Expected output:
```
-------------------------------------------------------------
 added: SFA exhibition on 23 March
-------------------------------------------------------------
```

### `mark 1` - Marks the task with index 1 (buy stationery) as done.
Expected output:
```
-------------------------------------------------------------
 Task buy stationery marked as done.
-------------------------------------------------------------
```

### `list` - Lists all tasks we have so far.
Expected output:
```
-------------------------------------------------------------
 1. buy stationery [X]
 2. post letter [ ] by: Tuesday
 3. SFA exhibition on 23 March from 1700 to 2000
-------------------------------------------------------------
```

### `find letter` - Finds tasks with the description query "letter".
Expected output:
```
-------------------------------------------------------------
 1. post letter [ ] by: Tuesday
-------------------------------------------------------------
```

### `bye` - Exits the application.
Expected output:
```
-------------------------------------------------------------
 Till we meet again.
-------------------------------------------------------------
```
