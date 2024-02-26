# GeePee User Guide

## Features

Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

### Adding a Todo task: `todo`

Adds a todo task to the list.

Format: `todo DESCRIPTION`

### Adding a Deadline task: `deadline`

Adds a deadline task to the list.

Format: `deadline DESCRIPTION /by DEADLINE`

### Adding an Event task: `event`

Adds an event task to the list.

Format: `event DESCRIPTION /from START /to END`

### Listing all tasks: `list`

Shows a list of all tasks in the list.

Format: `list`

### Marking a task as complete: `mark`

Marks a task as completed.

Format: `mark INDEX`
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

### Marking a task as incomplete: `unmark`

Marks a task as uncompleted.

Format: `unmark INDEX`
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

### Deleting a task: `delete`

Deletes the specified task from the list.

Format: `delete INDEX`
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

### Finding relevant tasks: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`
- Only the task descriptions are searched.
- The search is case-sensitive e.g. `book` will not match `Book`
- Only full words will be matched e.g. `book` will not match `books`

### Exiting the program: `bye`

Exits the program.

Format: `bye`
