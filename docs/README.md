# User Guide

The chatbot name is "aoliba".

## Features

### Feature: Task Management

The chatbot allows you to manage your tasks. You can add, delete, and mark tasks as completed.

### Feature: Handle invalid inputs

The chatbot can figure out invalid command, index, string format, etc..

## Usage

### `list` - List all tasks

This command lists all the tasks that have been stored.

Example of usage: `list`
Expected outcome:
The chatbot will display a numbered list of all the tasks.
```
Here are the tasks in your list:

[T][ ] Task 1
[D][ ] Task 2 (by: Sep 30 2021)
[E][ ] Task 3 (at: Oct 1 2021)
```

### `todo`/`event`/`deadline` - Add a new task

This command allows you to add a new task to the list.

Example of usage: 
`todo borrow book` - Add a Todo task "borrow book"
`event project meeting /from 2pm /to 4pm` - Add an Event task "project meeting", start at "2pm" and end at "4pm"
`deadline return book /by Sunday` - Add a Deadline task "return book" by "Sunday"

### `delete` - Delete a task

This command allows you to delete a task from the list.

Example of usage: `delete` 2
Example outcome: the 2nd task will be deleted

### `mark` - Mark a task as completed

This command allows you to mark a task as completed.

Example of usage: `mark 2`
Example outcome: the 2nd task will be marked as done

### `unmark` - Unmark a task as completed

This command allows you to unmark a task as completed.

Example of usage: `unmark 2`
Example outcome: the 2nd task will be marked as done
