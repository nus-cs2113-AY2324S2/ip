# User Guide

## Introduction

Sparky is a command-line task manager for tracking todos, deadlines, and events. You can add tasks, list them, mark them as done, remove them, and search for them by keyword.

## Quick Start

1. Start the application.
2. Enter commands one at a time in the terminal.
3. Use `bye` to exit the program.

## Command Summary

- `todo <description>`: Adds a todo task
- `deadline <description> /by <date>`: Adds a deadline task
- `event <description> /from <start> /to <end>`: Adds an event task
- `list`: Shows all tasks
- `mark <task number>`: Marks a task as done
- `unmark <task number>`: Marks a task as not done
- `delete <task number>`: Deletes a task
- `find <keyword>`: Finds tasks containing a keyword
- `bye`: Exits the program

## Features

### Adding a todo: `todo`

Adds a todo task to the task list.

Format:

`todo <description>`

Example:

`todo read book`

Expected outcome:

```text
Got it. I've added this task:
  [T][ ] read book
Now you have X tasks in the list.
```

### Adding a deadline: `deadline`

Adds a deadline task with a due date.

Format:

`deadline <description> /by <date>`

Example:

`deadline return book /by June 6th`

Expected outcome:

```text
Got it. I've added this task:
  [D][ ] return book (by: June 6th)
Now you have X tasks in the list.
```

### Adding an event: `event`

Adds an event task with a start time and end time.

Format:

`event <description> /from <start> /to <end>`

Example:

`event project meeting /from 2pm /to 4pm`

Expected outcome:

```text
Got it. I've added this task:
  [E][ ] project meeting (from: 2pm to: 4pm)
Now you have X tasks in the list.
```

### Listing tasks: `list`

Shows the tasks currently stored in the list.

Format:

`list`

Example:

`list`

Expected outcome:

```text
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (from: 2pm to: 4pm)
```

### Marking a task: `mark`

Marks a task as done.

Format:

`mark <task number>`

Example:

`mark 2`

Expected outcome:

```text
Nice! I've marked this task as done:
  [D][X] return book (by: June 6th)
```

### Unmarking a task: `unmark`

Marks a task as not done.

Format:

`unmark <task number>`

Example:

`unmark 2`

Expected outcome:

```text
OK, I've marked this task as not done yet:
  [D][ ] return book (by: June 6th)
```

### Deleting a task: `delete`

Removes a task from the list.

Format:

`delete <task number>`

Example:

`delete 1`

Expected outcome:

```text
Noted. I've removed this task:
  [T][ ] read book
Now you have X tasks in the list.
```

### Finding tasks: `find`

Finds tasks whose descriptions contain a given keyword.

Format:

`find <keyword>`

Example:

`find book`

Expected outcome:

```text
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: June 6th)
```

### Exiting the program: `bye`

Exits the application.

Format:

`bye`

Example:

`bye`

Expected outcome:

```text
Bye. Hope to see you again soooon!
```

## Notes

- Task numbering is 1-based.
- Tasks are loaded from and saved to `./data/duke.txt`.
- The task list is saved when the program exits with `bye`.
- `find` performs substring matching on task descriptions.
- `find` is case-sensitive because it matches the stored task text directly.
