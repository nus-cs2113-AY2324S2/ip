# Sebastian User Guide

## Features 

### Tracks 3 different types of tasks

There are 3 different types of tasks.
- Todo 
- Deadline 
- Event

The Todo task tracks task description. Deadline tracks the task description and its deadline. 
Event tracks the task description, start date and end date.  

### Add, deleting, marking, unmarking and lists tasks

You can add and delete tasks as well as mark or unmark them based on whether they are 
completed and list all tasks.
<br> <br>The format is as follows:
- add 'task'
- delete 'taskNum'
- mark 'taskNum'
- unmark 'taskNum'
- list

### Saves the task on a txt file

The tasks are automatically loaded at the start and saved as you edit the task list.

### Finds tasks

The tasks can be searched for using a keyword

## Usage

### `todo/deadline/event` - Adds a task to be tracked

Example of usage: todo borrow book

Todo: todo 'task description' <br>
Deadline: deadline 'task description' /by 'deadline' <br>
Event: event 'task description' /from 'startDate' /to 'endDate'

```
Got it. I've added this task:
[T] [ ]  borrow book
Now you have 1 task in the list.
```

### `list` - Lists all tasks

Example of usage: list

```
Here are the tasks in your list:
1. [T] [ ]  borrow book
```

### `Mark/unmark` - Marks and unmarks tasks

Example of usage: mark 1

```
Nice! I've marked this task as done:
[T] [X] borrow book
```

Example of usage: unmark 1

```
OK, I've marked this task as not done yet:
[T] [ ] borrow book
```

### `delete` - Deletes a specified task

Example of usage: delete 1

```
Noted. I've removed this task:
[T] [ ] borrow book
Now you have 0 tasks in the list.
```

### `find` - Finds all tasks with the keyword

Example of usage: find book

```
Here are the matching tasks in your list:
     1.[T][X] read book
     2.[D][X] return book (by: June 6th)
```

### `bye` - Exits out of the program

Example of usage: bye

```
Bye. I will be awaiting your next order! =)
```