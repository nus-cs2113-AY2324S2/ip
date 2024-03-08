# GeePee User Guide

GeePee is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI).

- Quick start
- Features
  - Adding a Todo task: `todo`
  - Adding a Deadline task: `deadline`
  - Adding an Event task: `event`
  - Listing all tasks: `list`
  - Marking a task as complete: `mark`
  - Marking a task as incomplete: `unmark`
  - Deleting a task: `delete`
  - Finding relevant tasks: `find`
  - Exiting the program: `bye`

## Quick start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `ip.jar` from [here](https://github.com/owx0130/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_for your task list.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.
5. Refer to the Features below for details of the commands available to you in this application.

## Features

> Words in `UPPER_CASE` are the parameters to be supplied by the user.
> e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

### Adding a Todo task: `todo`

Adds a todo task to the list.

Format: `todo DESCRIPTION`

Examples:
- `todo read book`

Expected outcome:
```
todo read book
    ________________________________________________
    Got it. I've added this task:
      [T][ ] read book
    Now you have 1 task in the list.
    ________________________________________________
```

### Adding a Deadline task: `deadline`

Adds a deadline task to the list.

Format: `deadline DESCRIPTION /by DEADLINE`

Examples:
- `deadline read book /by Monday 6pm`

Expected outcome:
```
deadline read book /by Monday 6pm
    ________________________________________________
    Got it. I've added this task:
      [D][ ] read book (by: Monday 6pm)
    Now you have 2 tasks in the list.
    ________________________________________________
```

### Adding an Event task: `event`

Adds an event task to the list.

Format: `event DESCRIPTION /from START /to END`

Examples:
- `event attend CS2113 lecture /from Fri 4pm /to Fri 6pm`

Expected outcome:
```
event attend CS2113 lecture /from Fri 4pm /to Fri 6pm
    ________________________________________________
    Got it. I've added this task:
      [E][ ] attend CS2113 lecture (from: Fri 4pm to: Fri 6pm)
    Now you have 3 tasks in the list.
    ________________________________________________
```

### Listing all tasks: `list`

Shows a list of all tasks in the list.

Format: `list`

Expected outcome:
```
list
    ________________________________________________
    Here are the current tasks in your list:
    1.[T][ ] read book
    2.[D][ ] read book (by: Monday 6pm)
    3.[E][ ] attend CS2113 lecture (from: Fri 4pm to: Fri 6pm)
    ________________________________________________
```

### Marking a task as complete: `mark`

Marks a task as completed.

Format: `mark INDEX`
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Examples:
- `mark 1`

Expected outcome:
```
mark 1
    ________________________________________________
    Nice! I've marked this task as done:
      [T][X] read book
    ________________________________________________
```

### Marking a task as incomplete: `unmark`

Marks a task as uncompleted.

Format: `unmark INDEX`
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Examples:
- `unmark 1`

Expected outcome:
```
unmark 1
    ________________________________________________
    OK, I've marked this task as not done yet:
      [T][ ] read book
    ________________________________________________
```

### Deleting a task: `delete`

Deletes the specified task from the list.

Format: `delete INDEX`
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Examples:
- `delete 1`

Expected outcome:
```
delete 1
    ________________________________________________
    Noted. I've removed this task:
      [T][ ] read book
    Now you have 2 tasks in the list.
    ________________________________________________
```

### Finding relevant tasks: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`
- Only the task descriptions are searched.
- The search is case-sensitive e.g. `book` will not match `Book`
- Only full words will be matched e.g. `book` will not match `books`

Examples:
- `find CS2113`

Expected outcome:
```
find CS2113
    ________________________________________________
    Here are the relevant tasks in your list:
    1.[E][ ] attend CS2113 lecture (from: Fri 4pm to: Fri 6pm)
    ________________________________________________
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Expected outcome:
```
bye
    ________________________________________________
    Bye! Hope to see you again soon!
    ________________________________________________
```