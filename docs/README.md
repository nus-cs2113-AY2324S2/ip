# Katleen User Guide

## Features 

Notes about command format:
 - Words that are in `UPPERCASE` are parameters to be supplied by the user. eg: `todo DESCRIPTION`, 
        `DESCRIPTION` is a parameter which can be used as `todo Submit CS2113 ip`

### Adding a new task: `todo / deadline / event`

There are 3 types of Tasks supported, ToDos, Deadlines and Events.
Adds a new task to the bot.

Format: 

`todo DESCRIPTION`

`deadline DESCRIPTION /by DEADLINE`

`event DESCRIPTION /from START /to END`

### Marking a task as done or undone: `mark / unmark` 

Marks the task as done or undone based on the command given.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as done

`unmark INDEX`
- Marks the task at the specified `INDEX` as undone

Example:
`mark 3` will mark the third task shown in `list` as done.
`unmark 3` will unmark the third task shown in `list`


### Listing all tasks: `list`

Lists all the current tasks, with its details and whether it's marked as done.
Tasks marked as done will be labelled with an X in the second box, 
as shown in the expected output section below. 
`T, D, E` represent `ToDo`, `Deadline` and `Event` Task types respectively.

Format: `list`

#### Expected output
```
1. [T][ ] TASK_DESCRIPTION
2. [D][ ] TASK_DESCRIPTION by DEADLINE
3. [T][X] TASK_DESCRIPTION
```

### Filtering the tasks: `find`

Format: `find QUERY`
- Filters the list of Tasks that contain the `QUERY`
- Search is case-insensitive e.g. `EAT` will match `eat`
- Only the description is searched
- Partial matches will return as well e.g. `rive` will return `drive`

Example:
`find s` will return `scan` and `store`

### Deleting a task: `delete`

Format: `delete INDEX`
- Deletes a task at the `INDEX` specified.

Example: 
`delete 2` deletes the second task in the `list`

### Closing the chatbot: `bye`

Safely closes the chatbot, and performs a final save to the save file before
terminating the program.

#### Expected output
```
Bye, have a nice day!
```