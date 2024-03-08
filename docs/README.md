# User Guide

## Features 

### Add Task 

Add tasks to the task list. Tasks can be todos, deadlines, or events.

### Delete Task

Delete tasks from the task list.

### Mark Task as Done

Mark tasks as done in the task list.

### List Tasks
View the list of tasks in the task list.

### Find Task

Search for tasks containing a specific keyword in the task list.


## Usage

### `todo <description>` - Add Todo Task

Adds a todo task to the task list.

Example of usage:

`todo read book`

Expected outcome:

The todo task "read book" is added to the task list.

```
Got it. I've added this task:
  [T][ ] read book
Now you have X tasks in the list.

```

### `event <description> /from <start time> /to <end time>` - Add Event Task

Adds an event task to the task list with a start and end time.

Example of usage:

`event project meeting /from 2pm /to 4pm`

Expected outcome:

The event task "project meeting" with a start time of "2pm" and end time of "4pm" is added to the task list.

```
Got it. I've added this task:
  [E][ ] project meeting (from: 2pm to: 4pm)
Now you have X tasks in the list.

```

### `deadline <description> /by <due date>` - Add Deadline Task

Adds a deadline task to the task list with a due date.

Example of usage:

`deadline return book /by June 6th`

Expected outcome:

The deadline task "return book" with a due date of "June 6th" is added to the task list.

```
Got it. I've added this task:
  [D][ ] return book (by: June 6th)
Now you have X tasks in the list.


```
