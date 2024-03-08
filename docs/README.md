# Bobble User Guide

## Features

### Adding a todo:`todo`
Adds a todo to the task list.

Format:`todo <DESCRIPTION>`

Example of usage:
- `todo read book`

### Adding a deadline:`deadline`
Adds a deadline to the task list.

Format:`deadline <DESCRIPTION> /by <DUE>`

Example of usage:
- `deadline return book /by 26 Aug`
- `deadline return book to Sandy /by when i see her`

### Adding an event:`event`
Adds an event to the task list.

Format:`event <DESCRIPTION> /from <START> /to <END>`

Example of usage:
- `event Book fair /from tmr /to 12/4`

### Listing all tasks:`list`
Shows a list of all tasks in the task list.

Format:`list`

### Marking a task as done:`mark`

Format:`mark <INDEX>`
- Marks the task as done at the specified `<INDEX>`.
- The index refers to the index number of the task in task list.
- The index **must be a _positive integer_** 1, 2, 3,...etc.

Example of usage:
- `mark 2` Marks the second task in the task list as done.
```
mark 2
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book (by: 26 Aug)
```

### Marking a task as not done:`unmark`

Format:`unmark <INDEX>`
- Marks the task as not done at the specified `<INDEX>`.
- The index refers to the index number of the task in task list.
- The index **must be a _positive integer_** 1, 2, 3,...

Example of usage:
- `unmark 3` Marks the third task in the task list as not done.
```
unmark 3
____________________________________________________________
OK, I've marked this task as not done yet:
  [E][ ] Book fair (from: tmr to: 12/4)
```
### Deleting a task:`delete`
Delete the specified task from the task list.

Format:`delete <INDEX>`
- Deletes the task at the specified `<INDEX>`
- The index refers to the index number of the task in task list.
- The index **must be a _positive integer_** 1, 2, 3,...

Examples of usage:
- `delete 1` deletes the first task in the task list.
```
delete 1
____________________________________________________________
Noted. I've removed this task:
  [T][ ] read book
```

### Finding a task:`find`
Finds tasks with descriptions containing the keyword.

Format:`find <KEYWORD>`
- `<KEYWORD>` can be a word or a phrase.
- The search is case-insensitive, e.g. `book` will match `Book`
- Only the description of each task is searched.

Example of usage:
- `find Book` returns `read book`, `return book` and `Book fair` tasks.
```
find Book
____________________________________________________________
Here are the matching tasks in your list:
  1.[T][ ] read book
  2.[D][X] return book (by: 26 Aug)
  3.[E][ ] Book fair (from: tmr to: 12/4)
```
- `find ead` returns `read book` task.

```
find ead
____________________________________________________________
Here are the matching tasks in your list:
  1.[T][ ] read book
```

### Exiting the program:`bye`
Exits the program.

Format:`exit`
