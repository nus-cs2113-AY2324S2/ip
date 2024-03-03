# User Guide
Ava is a chatbot for managing tasks, optimized for use via a Command Line Interface (CLI).

## Features 

### Adding a task: `todo` `deadline` or `event`

Adds a task to the task list.

Format:
`todo TASKNAME`
`deadline TASKNAME /by DEADLINE`
`event TASKNAME /from STARTDATE /to ENDDATE`
- `todo` `deadline` and `event` are three types of tasks.
- `todo` is a normal task. 
- `deadline` is a task with a deadline.
- `event` is a task with a start date and an end date.

Examples:

`todo CS2113 homework`

`deadline CS2113 homework /by Friday`

`event CS2113 homework /from Monday /to Friday`

Expected outcome:
```
Got it! I've added this task:
[T][ ] CS2113 homework

Got it! I've added this task:
[D][ ] CS2113 homework(by: Friday)

Got it! I've added this task:
[E][ ] CS2113 homework(from: Monday to: Friday)
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified INDEX.
- The index refers to the index number shown after `list` command.
- The index must be a positive integer 1, 2, 3, ...

### Marking a task as done: `mark`

Marks a task in the task list as done.

Format: `mark INDEX`
- Marks the task at the specified INDEX as done.
- Description of the index is same as the description in `delete`.

### Marking a task as not done: `unmark`

Marks a task in the task list as not done.

Format: `unmark INDEX`
- Marks the task at the specified INDEX as not done.
- Description of the index is same as the description in `delete`.

### Finding a task by keyword: `find`

Finds tasks in the task list which contain the given keyword.

Format: `find KEYWORD`
- The search is case-sensitive.

Examples:
- `find CS2113` returns `[T][ ] CS2113 homework`, `[D][ ] CS2113 homework(by: Friday)` and `[E][ ] CS2113 homework(from: Monday to: Friday)`

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.
