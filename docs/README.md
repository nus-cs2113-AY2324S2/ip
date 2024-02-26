# Jeff
Jeff is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI).
Jeff currently provides functionality for managing todos, deadlines, and events.
Data will be stored in a task list and saved locally on your computer.

- [Features](#features)
    - [List all tasks: `list`](#list-all-tasks-list)
    - [Add a todo: `todo`](#add-a-todo-todo)
    - [Add a deadline: `deadline`](#add-a-deadline-deadline)
    - [Add an event: `event`](#add-an-event-event)
    - [Mark a task: `mark`](#mark-a-task-mark)
    - [Unmark a task: `unmark`](#unmark-a-task-unmark)
    - [Delete a task: `delete`](#delete-a-task-delete)
    - [Find tasks by description: `find`](#find-tasks-by-description-find)
    - [Exit the program: `bye`](#exit-the-program-bye)

## Features
**Notes about command format:**
- Items in square brackets are parameters supplied to the user.
<br> e.g. in `todo [description]`, `[description]` is a parameter which can be used as `todo run`.

### List all tasks: `list`

List out all the tasks in the task list.

Format: `list`

### Add a todo: `todo`

Adds a new todo task to the task list.

Format: `todo [description]`
- `[description]` should not contain `|`

### Add a deadline: `deadline`

Adds a new deadline task to the task list.

Format: `deadline [description] /by [yyyy-mm-dd]`
- `[description]` should not contain `|`
- `[yyyy-mm-dd]` should be a valid date.
  <br>e.g. `2024-02-30` is **not** a valid date.

### Add an event: `event`

Adds a new event task to the task list.

Format: `event [description] /from [from] /to [to]`
- `[description]` should not contain `|`.
- `[from]` should not contain `|` or `-`.
- `[to]` should not contain `|` or `-`.

### Mark a task: `mark`

Marks a specified task in the task list.

Format: `mark [index]`
- `[index]` should be an integer between 1 and the current size of the task list inclusive.

### Unmark a task: `unmark`

Unmarks a specified task in the task list.

Format: `unmark [index]`
- `[index]` should be an integer between 1 and the current size of the task list inclusive.

### Delete a task: `delete`

Deletes a specified task from the task list.

Format: `delete [index]`
- `[index]` should be an integer between 1 and the current size of the task list inclusive.

### Find tasks by description: `find`

List out all tasks whose description contains the specified text.

Format: `find [text]`

### Exit the program: `bye`

Exits the program.

Format: `bye`
