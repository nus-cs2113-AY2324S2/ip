# Kobot User Guide

[Features](#features)
- [Add to-Do](#add-to-do-to-list-todo)
- [Add deadline](#add-deadline-to-list-deadline)
- [Add event](#add-event-to-list-event)
- [List all tasks](#list-all-tasks-list)
- [Mark task as completed](#mark-task-as-completed-mark)
- [Mark task as not completed](#mark-task-as-not-completed-unmark)
- [Locating task by description](#locating-task-by-description-find)
- [Delete task](#delete-a-task-delete)
- [Exiting the program](#exiting-the-program-exit-or-bye)

## Features

### Notes about the Command format
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo homework`.


- Parameters must be in specified order.

  e.g. if the command specifies `/from START_DATETIME /to END_DATETIME`, `/to END_DATETIME /from START_DATETIME` will not be accepted.


- Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `bye`) will be ignored.
  
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Add To-do to list: `todo`

Adds a new to-do task to the task list.

Format: `todo DESCRIPTION`

Examples:
- `todo Clean room`


### Add Deadline to list: `deadline`

Adds a new deadline to the task list.

Format: `deadline DESCRIPTION /by DATETIME`

Examples:
- `deadline CS2113T Quiz /by Sunday, 2359`


### Add Event to list: `event`

Adds a new event to the task list.

Format: `event DESCRIPTION /from START_DATETIME /to END_DATETIME`

Examples:
- `event CS2113T Lecture /from Friday 4pm /to Friday 6pm`


### List all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Mark task as completed: `mark`

Marks a specific task as completed.

Format: `mark INDEX`
- Marks the task at the specified `index` as completed.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3...

### Mark task as not completed: `unmark`

Marks a specific task as not completed.

Format: `unmark INDEX`
- Marks the task at the specified `index` as not completed.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3...

### Locating task by description: `find`

Finds tasks which contain the given keywords.
- The search is case sensitive. e.g. `Homework` will not match `homework`
- Only the description is searched.
- Full and partial words will be matched. e.g. `work` will match `Homework` and `work`

Format: `find KEYWORD`

### Delete a task: `delete`

Delete a specific task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `index`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3...

### Exiting the program: `exit` or  `bye`
Exits the program

Format: `exit`

Format: `bye`