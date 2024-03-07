# FredBot User Guide

FredBot is a desktop app for managing tasks, optimised for use via a Command Line Interface (CLI).

## Features 

### Managing tasks

There are three different types of tasks: Todos, Deadlines and Events.

Users can add, find and delete tasks from their task list.

### Tracking tasks

Tasks can be marked as done for easier tracking.

### Saving the task list

The task list is automatically saved on your computer, which is then loaded up the next time FredBot is opened.

## Usage

### `todo` - Creates a Todo

Adds a Todo into the task list.

Example of usage: 

`todo DESCRIPTION_1`

Expected outcome:

FredBot will echo the Todo that is added to the task list, as well as count the number of tasks present in the list.

```
Are you sure you'll ever get to it? Fine, I've added this task:
[T][ ] DESCRIPTION_1
You now have 1 task in the list.
```

### `deadline` - Creates a Deadline

Adds a Deadline into the task list.

Example of usage:

`deadline DESCRIPTION_2 /by 2024-03-06`

Expected outcome:

FredBot will echo the Deadline that is added to the task list, as well as count the number of tasks present in the list.

```
Are you sure you'll ever get to it? Fine, I've added this task:
[D][ ] DESCRIPTION_2 (by: Mar 06 2024)
You now have 2 tasks in the list.
```

### `event` - Creates an Event

Adds an Event into the task list.

Example of usage:

`event DESCRIPTION_2 /from Mar 06 2024 2pm /to 4pm`

Expected outcome:

FredBot will echo the Event that is added to the task list, as well as count the number of tasks present in the list.

```
Are you sure you'll ever get to it? Fine, I've added this task:
[E][ ] DESCRIPTION_2 (from: Mar 06 2024 2pm to: 4pm)
You now have 3 tasks in the list.
```

### `mark` - Marks a task as done

Marks the task specified through its index on the task list as done.

Example of usage:

`mark 2`

Expected outcome:

FredBot will echo the Todo that is being marked.

```
Ok and do you want a medal for that? I've marked this as done:
[D][X] DESCRIPTION_2 (by: Mar 06 2024)
```

### `unmark` - Clears the mark on a task

Removes the mark on the task specified through its index on the task list.

Example of usage:

`unmark 2`

Expected outcome:

FredBot will echo the Todo that is being cleared of its mark.
```
Why am I not surprised... I've marked this task as not done yet:
[D][ ] DESCRIPTION_2 (by: Mar 06 2024)
```

### `list` - Shows the task list

Lists all the tasks that are present in the task list.

Example of usage:

`list`

Expected outcome:

FredBot will retrieve and print all the tasks in the task list.

```
Mr Busy over here has these tasks in his list:
1. [T][ ] DESCRIPTION_1
2. [D][ ] DESCRIPTION_2 (by: Mar 06 2024)
3. [E][ ] DESCRIPTION_2 (from: Mar 06 2024 2pm to: 4pm)
```

### `find` - Finds relevant tasks

Shows all the tasks with descriptions containing the provided keyword.

Example of usage:

`find DESCRIPTION_2`

Expected outcome:

FredBot will retrieve and print all relevant tasks from the task list.

```
Are these what you're looking for? I found these:
1. [D][ ] DESCRIPTION_2 (by: Mar 06 2024)
2. [E][ ] DESCRIPTION_2 (from: Mar 06 2024 2pm to: 4pm)
```

### `delete` - Removes a task from the task list

Deletes a task specified through its index on the task list.

Example of usage:

`delete 1`

Expected outcome:

FredBot will echo the task that is being removed, as well as count the number of tasks present in the list.

```
Finally lifting my load? I've removed this task:
[T][ ] DESCRIPTION_1
You now have 2 tasks in the list.
```