# Rose User Guide

## Features 

### Feature-TODO

Add a todo task with just a description.

### Feature-EVENT

Schedule events by specifying a start and end time along with a description.

### Feature-DEADLINE

Set deadlines with a specific due date or time and a description.

### Feature-LIST

View a list of all your tasks, including todos, events, and deadlines.

### Feature-MARK

Mark a task as completed.

### Feature-UNMARK

Mark a previously completed task as incomplete.

### Feature-FIND

Search for tasks by keywords.

### Feature-DELETE

Remove tasks from the list that are no longer need to be tracked. 

## Usage

### `todo` - Add a todo task

Adds a todo task to your list. 

Example of usage: 

`todo read a book `

Expected outcome:

A todo task is added to your list.

```
Got it. I've added this task:
  [T][ ] read a book
Now you have 1 tasks in the list. 
```

### `event` - Schedule an event

Adds an event with a start and end time. 

Example of usage: 

`event project meeting /at Monday 2pm to 4pm`

Expected outcome:

An event is added to your list.

```
Got it. I've added this task:
  [E][ ] project meeting (from: Monday 2pm to 4pm)
Now you have 1 tasks in the list. 
```
### `deadline` - Set a deadline

Adds a task with a deadline.

Example of usage: 

`deadline submit report /by Friday 5pm`

Expected outcome:

An deadline task is added to your task list

```
Got it. I've added this task:
  [D][ ] submit report (by: Friday 5pm)
Now you have 1 tasks in the list. 
```
### `list` - List of all tasks

Displays all tasks in your task list. 

Example of usage: 

`list`

Expected outcome:

A list of all your tasks is displayed. 

```
Here are the tasks in your list:
1.[T][ ] read a book
2.[E][ ] project meeting (from : Monday 2pm to 4pm)
3.[D][ ] submite report (by: Friday 5pm)
```
### `mark` - Mark a task as done

Marks a specific task as completed

Example of usage: 

`mark 1`

Expected outcome:

The specific task is marked as done.

```
OK, I've marked this task as done:
  [T][X] read a book
```
### `unmark` - Mark a task as not done

Marks a previously completed task as not done. 

Example of usage: 

`unmark 1`

Expected outcome:

The specific task is marked as not done.

```
OK, I've marked this task as not done yet:
  [T][ ] read a book
```
### `find` - Find tasks by keyword

Finds and displays tasks containing the specified keyword.

Example of usage: 

`find book`

Expected outcome:

Tasks containing the keyword are displayed.

```
Here are the matching tasks in your list:
1.[T][ ] read a book
```
### `delete` - Delete a task

Removes a specified task from your list. 

Example of usage: 

`delete 1`

Expected outcome:

The specified task is removed from your list.

```
Noted. I've removed this task:
  [T][ ] read a book
```
