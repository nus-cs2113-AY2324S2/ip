# Brennan-Task Management Application

# User Guide

## Features

### Adding Tasks

This feature allows users to add different types of tasks such as Todo, Deadline, and Event to the task list.

### Listing Tasks

This feature enables users to view all the tasks currently stored in the task list.

### Marking Tasks as Completed

With this feature, users can mark tasks as completed, indicating that they have been finished.

### Unmarking Completed tasks

With this feature, users can unmark tasks which were completed, indicating that they have not been finished.

### Deleting Tasks

Users can delete tasks from the task list using this feature.

### Finding Tasks

This feature allows users to search for tasks containing specific keywords.

### Exiting the Application

This feature allows users to gracefully exit the application.

## Usage

### `todo` - Add a Todo task

Adds a Todo task to the task list.

Example of usage:

`todo Buy meat`

Expected outcome:

A new Todo task "Buy meat" is added to the task list.

```
____________________________________________________________
____________________________________________________________
Well done, you've added a new task: 
[T][ ] Buy meat
Currently you have 6 task(s) in your list!
____________________________________________________________
____________________________________________________________

```

### `deadline` - Add a Deadline task

Adds a Deadline task to the task list.

Example of usage:

`deadline Submit essay /by: 2024-03-17`

Expected outcome:

A new Deadline task "Submit essay" with the deadline "2024-03-17" is added to the task list.

```
____________________________________________________________
____________________________________________________________
Well done, you've added a new task: 
[D][ ] Submit essay (by: 2024-03-17)
Currently you have 7 task(s) in your list!
____________________________________________________________
____________________________________________________________
```

### `event` - Add an Event task

Adds an Event task to the task list.

Example of usage:

`event Marriage /from: 2pm /to:5pm`

Expected outcome:

A new Event task "Marriage" from 2p to 5pm
```
____________________________________________________________
____________________________________________________________
Well done, you've added a new task: 
[E][ ] Marriage  (from:  2pm  to: 5pm)
Currently you have 8 task(s) in your list!
____________________________________________________________
____________________________________________________________
```

### `list` - List all tasks

Displays all tasks currently stored in the task list.

Example of usage:

`list`

Expected outcome:

A list of all tasks currently stored in the task list is displayed.

```
____________________________________________________________
Here are the tasks in your list: 
____________________________________________________________
1.  [T][ ] analyse book
2.  [T][ ] haircut
3.  [D][ ] Submit essay (by: 2024-03-17)
4.  [E][ ] Marriage  (from:  2pm  to: 5pm)
____________________________________________________________
____________________________________________________________

```

### `mark` - Mark a task as completed

Marks a task as completed based on its index in the task list.

Example of usage:

`mark 2`

Expected outcome:

The task at index 2 is marked as completed.

```
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
2. [X] haircut
____________________________________________________________
____________________________________________________________

```

### `unmark` - Unmark a task

Unmarks a previously completed task, setting it back to an incomplete status.

Example of usage:

`unmark 2`

Expected outcome:

The task at index 2 is marked as incomplete.

```
____________________________________________________________
____________________________________________________________
 OK, I've marked this task as not done yet:
2. [ ] Marriage  (from:  2pm  to: 5pm)
____________________________________________________________
____________________________________________________________
```

### `delete` - Delete a task

Deletes a task from the task list based on its index.

Example of usage:

`delete 1`

Expected outcome:

The task at index 1 is deleted from the task list.

```
____________________________________________________________
Command received. I've removed this task:
1. [ ] analyse book
Currently you have 2 tasks in the list below.
____________________________________________________________

```

### `find` - Find tasks

Searches for tasks containing a specific keyword.

Example of usage:

`find essay`

Expected outcome:

Tasks containing the keyword "essay" are displayed.

```
____________________________________________________________
Here are all the tasks that match your search keyword: 
____________________________________________________________
1.  [D][ ] Submit essay (by: 2024-03-17)
____________________________________________________________
____________________________________________________________

```
### `bye` - Exit the application

Terminates the application and closes the program.

Example of usage:

`bye`

Expected outcome:

The application terminates and the program closes.

```
____________________________________________________________
Goodbye! Have a nice day!
____________________________________________________________

```