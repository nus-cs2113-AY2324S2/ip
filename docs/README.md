# Jeff
Jeff is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI).
Jeff currently provides functionality for managing todos, deadlines, and events.
Data will be stored in a task list and saved locally on your computer.

- [Quick Start](#quick-start)
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

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `jeff.jar` from [here](https://github.com/awesomesjh/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the *home folder* for Jeff.
4. Open a command terminal, `cd` into the folder you put the jar file in, 
and use the `java -jar jeff.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it.
<br> Some example commands you can try:
   - `list`: List all tasks
   - `todo run`: Adds a todo task with the description `run` to the task list.
   - `delete 3`: Deletes the 3rd task shown in the current task list.
   - `bye`: Exit the app.
6. Refer to the [Features](#features) below for details of each command.

## Features
**Notes about command format:**
- Items in square brackets are parameters supplied to the user.
<br> e.g. in `todo [description]`, `[description]` is a parameter which can be used as `todo run`.

<br>

### List all tasks: `list`

List out all the tasks in the task list.

Format: `list`

Example:
```
Here are the tasks in your list:
1.[T][ ] go for a walk
2.[D][ ] project (by: Mar 8 2024)
3.[E][ ] exam (from: 9am to: 11am)
```

<br>

### Add a todo: `todo`

Adds a new todo task to the task list.

Format: `todo [description]`
- `[description]` should not contain `|`

Example:
- `todo go for a walk`: 
Adds a todo task with the description `go for a walk` to the task list.
```
Got it. I've added this task:
  [T][ ] go for a walk
Now you have 1 tasks in the list.
```

<br>

### Add a deadline: `deadline`

Adds a new deadline task to the task list.

Format: `deadline [description] /by [yyyy-mm-dd]`
- `[description]` should not contain `|`
- `[yyyy-mm-dd]` should be a valid date.
  <br>e.g. `2024-02-30` is **not** a valid date.

Example:
- `deadline project /by 2024-03-08`: 
Adds a deadline task with the description `project` and deadline `2024-03-08` to the task list.
```
Got it. I've added this task:
  [D][ ] project (by: Mar 8 2024)
Now you have 2 tasks in the list.
```

<br>

### Add an event: `event`

Adds a new event task to the task list.

Format: `event [description] /from [from] /to [to]`
- `[description]` should not contain `|`.
- `[from]` should not contain `|` or `-`.
- `[to]` should not contain `|` or `-`.

Example:
- `event exam /from 9am /to 11am`:
Adds an event task with the description `exam`, start time `9am` and end time `11am` to the task list.
```
Got it. I've added this task:
  [E][ ] exam (from: 9am to: 11am)
Now you have 3 tasks in the list.
```

<br>

### Mark a task: `mark`

Marks a specified task in the task list.

Format: `mark [index]`
- `[index]` should be an integer between 1 and the current size of the task list inclusive.

Example:
- `mark 1`:
Marks the 1st task in the current task list.
```
Nice! I've marked this task as done:
  [T][X] go for a walk
```

<br>

### Unmark a task: `unmark`

Unmarks a specified task in the task list.

Format: `unmark [index]`
- `[index]` should be an integer between 1 and the current size of the task list inclusive.

Example:
- `unmark 1`:
Unmarks the 1st task in the current task list.
```
OK, I've marked this task as not done yet:
  [T][ ] go for a walk
```

<br>

### Delete a task: `delete`

Deletes a specified task from the task list.

Format: `delete [index]`
- `[index]` should be an integer between 1 and the current size of the task list inclusive.

Example:
- `delete 1`:
Deletes the 1st task from the current task list.
```
Noted. I've removed this task:
  [T][ ] go for a walk
Now you have 2 tasks in the list.
```

<br>

### Find tasks by description: `find`

List out all tasks with the description containing the specified text.

Format: `find [text]`

Example:
- `find exam`:
List out all tasks with the description containing the text `exam`.
```
Here are the matching tasks in your list:
1.[E][ ] exam (from: 9am to: 11am)
```

<br>

### Exit the program: `bye`

Exits the program.

Format: `bye`

Example:
```
Bye. Hope to see you again soon!
```