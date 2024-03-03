# Xavier User Guide

## Features

### List tasks: list

Lists all tasks in the task list

Format: list

### Adding a Todo: todo

Adds a todo task to the task list

Format: todo TASK

Examples:
- todo borrow book
- todo buy groceries

### Adding a Deadline: deadline

Adds a deadline task to the task list

Format: deadline TASK /by DEADLINE

Examples:
- deadline return book /by tomorrow
- deadline CS2113 assignment /by 11.59pm

### Adding a Event: event

Adds a even task to the task list

Format: event TASK /from START /to END

Examples:
- event John's birthday /from today 12pm /to 6pm
- event CS2113 lecture /from tomorrow 10am /to 12pm

### Mark a task as done: mark

Marks the specifed task in the task list as done

Format: mark INDEX

Examples:
- mark 1

Tip: You can perform _list_ to view the current list of tasks before choosing which one to mark as done.

### Unmark a task (set as not done): unmark

Unmarks the specifed task in the task list (set as not done)

Format: unmark INDEX

Examples:
- unmark 2

Tip: Tip: You can perform _list_ to view the current list of tasks before choosing which one to unmark.

### Delete a task: delete

Deletes the specifed task from the task list

Format: delete INDEX

Examples:
- delete 1

Tip: You can perform _list_ to view the current list of tasks before choosing which one to delete.
