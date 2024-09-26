# ChatMan User Guide

**ChatMan** is a chatbot that enables storage and tracking of a user's different tasks. 

It possesses 3 main categories of tasks:

- **todos** (tasks without any timeframe)
- **deadlines** (tasks with endpoints for completion)
- **events** (tasks with start-point and endpoints)

A user can interact with **ChatMan** using commands that allow them to add & remove tasks from storage, mark them as 
complete/incomplete, list all currently stored tasks and find stored tasks using keyword search.

## Features 

This section details all **ChatMan** commands available to a user, what they do and how to correctly format them.

### Listing stored tasks: `list`

Displays all currently stored tasks to user.

Format: `list`

*Example output:*
```
1.[T][ ]return book
2.[D][ ]buy gifts (by: 24th December)
3.[E][ ]job interview (from: 25th March 10am to: 11am)
```
### Searching stored tasks: `find`

Returns all tasks whose descriptions contain the keyword/key-phrase provided with 'find'.

Format: `find KEYWORD`

>Please note that `KEYWORD` cannot contain any **/** characters

*Example input:*
`find job`

*Example output:*
```
Here are the full matches in your list for 'job':
3.[E][ ]job interview (from: 25th March 10am to: 11am)
```
### Adding a todo: `todo`
Adds a task without any timeframe to the task list. By default, this task is not marked as complete.

Format: `todo DESCRIPTION`

>Please note that `DESCRIPTION` cannot contain any **/** characters

*Example input:*
`todo return book`

*Example output:*
```
Got it. I've added this task:
[T][ ]return book
Now you have 1 tasks in the list.
```

### Adding a deadline: `deadline`
Adds a task with an endpoint to the task list. By default, this task is not marked as complete.

Format: `deadline DESCRIPTION /by ENDPOINT`

>Please note that all ARGUMENTS cannot contain any **/** characters

*Example input:*
`deadline buy gifts /by 24th December`

*Example output:*
```
Got it. I've added this task:
[D][ ]buy gifts (by: 24th December)
Now you have 2 tasks in the list.
```

### Adding an event: `event`
Adds a task with a start-point and an endpoint to the task list. By default, this task is not marked as complete.

Format: `event DESCRIPTION /from STARTPOINT /to ENDPOINT`

>Please note that all ARGUMENTS cannot contain any **/** characters

*Example input:*
`event job interview /from 25th March 10am /to 11am`

*Example output:*
```
Got it. I've added this task:
[E][ ]job interview (from: 25th March 10am to: 11am)
Now you have 3 tasks in the list.
```
### Marking & unmarking tasks: `mark` and `unmark`

`mark` labels a stored task (at the provided list position) as complete.
`unmark` labels a stored task (at the provided list position) as incomplete.

*[X]* is the 'complete' label.

*[ ]* is the 'incomplete' label.

Format: 
- `mark LIST_POSITION`
- `unmark LIST_POSITION`

>Please note that LIST_POSITION must be a numerical position within the bounds of the list.

*Example input:*
`mark 1`

*Example Output:*
```
Nice! I've marked this task as done:
[T][X]return book
```
The action of this command is also reflected when the user next enters `list`:
```
1.[T][X]return book
2.[D][ ]buy gifts (by: 24th December)
3.[E][ ]job interview (from: 25th March 10am to: 11am)
```

### Deleting tasks: `delete`

Removes the stored task, at the provided list position, from the task list.

Format: `delete LIST_POSITION`

>Please note that LIST_POSITION must be a numerical position within the bounds of the list.

*Example input:*
`delete 1`

*Example Output:*
```
Noted. I've removed this task:
[T][X]return book
Now you have 2 tasks in the list.
```
The action of this command is also reflected when the user next enters `list`:
```
1.[D][ ]buy gifts (by: 24th December)
2.[E][ ]job interview (from: 25th March 10am to: 11am)
```

### Exiting ChatMan: `bye`

Terminates **ChatMan** execution.

Format: `bye`

*Output:*
`Bye. Hope to see you again soon!`

## Command Summary

| Action                    | Command Format                                    |
|---------------------------|---------------------------------------------------|
| list tasks                | `list`                                            |
| keyword/key-phrase search | `find KEYWORD`                                    |
| add a todo                | `todo DESCRIPTION`                                |
| add a deadline            | `deadline DESCRIPTION /by ENDPOINT`               |
| add an event              | `event DESCRIPTION /from STARTPOINT /to ENDPOINT` |
| mark a task as complete   | `mark LIST_POSITION`                              |
| mark a task as incomplete | `unmark LIST_POSITION`                            |
| delete a task             | `delete LIST_POSITION`                            |
| exit                      | `bye`                                             |