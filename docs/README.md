# Bob User Guide

<!-- TOC -->
* [Bob User Guide](#bob-user-guide)
  * [Features](#features-)
    * [Add Tasks](#add-tasks)
    * [List Tasks](#list-tasks)
    * [Delete Tasks](#delete-tasks)
    * [Find Tasks](#find-tasks)
    * [Update Task Statuses](#update-task-statuses)
  * [Usage](#usage)
    * [`todo` - Add a Todo Task](#todo---add-a-todo-task)
    * [`deadline` - Add a Deadline Task](#deadline---add-a-deadline-task)
    * [`event` - Add an Event Task](#event---add-an-event-task)
    * [`list` - List out all Tasks](#list---list-out-all-tasks)
    * [`delete` - Delete a Task](#delete---delete-a-task)
    * [`find` - Find a Task](#find---find-a-task)
    * [`mark` - Mark a Task as complete](#mark---mark-a-task-as-complete)
    * [`unmark` - Mark a Task as incomplete](#unmark---mark-a-task-as-incomplete)
<!-- TOC -->

## Features 

### Add Tasks

Add a Task to your Task List! Choose from one of the three available Task types:
* Todo (Tasks with no timeframe)
* Deadline (Tasks with a due date)
* Event (Tasks with a start date and end date)

### List Tasks

List out all Tasks in your Task List.

### Delete Tasks

Delete any unwanted Tasks from your Task List.

### Find Tasks

Find Tasks in your Task List using a search keyword.

### Update Task Statuses

Mark Tasks as complete, or unmark them if they are incomplete.

## Usage

### `todo` - Add a Todo Task

Add a new Todo Task to your Task List.

Format: `todo <task_name>`

Example: 

`todo CS2113 Week 7 Quiz`

```
____________________________________________________________
 Got it. I've added this task:
   [T][ ] CS2113 Week 7 Quiz
 Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Add a Deadline Task

Add a new Deadline Task to your Task List.

Format: `deadline <task_name> /by <due_date>`
* Note that the format of `due_date` is: `DD/MM/YYYY HH:MM`. Please ensure your times are in 24-hour format.

Example:

`deadline CS2040 Midterm Revision /by 21/03/2024 23:59`

```
____________________________________________________________
 Got it. I've added this task:
   [D][ ] CS2040 Midterm Revision (by: 21/03/2024 23:59)
 Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Add an Event Task

Add a new Event Task to your Task List.

Format: `event <task_name> /from <start_date> /to <end_date>`
* Note that the format of `start_date` and `end_date` are: `DD/MM/YYYY HH:MM`. 
Please ensure your times are in 24-hour format.

Example:

`event NUS Hack&Roll /from 20/01/2024 10:00 /to 21/01/2024 18:00`

```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] NUS Hack&Roll (from: 20/01/2024 10:00 to: 21/01/2024 18:00)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### `list` - List out all Tasks

List out all Tasks in your Task List.

Example:

`list`

```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] CS2113 Week 7 Quiz
 2.[D][ ] CS2040 Midterm Revision (by: 21/03/2024 23:59)
 3.[E][ ] NUS Hack&Roll (from: 20/01/2024 10:00 to: 21/01/2024 18:00)
____________________________________________________________
```

### `delete` - Delete a Task

Delete a Task from your Task List.

Format: `delete <task_id>`

Example:

`delete 2`

```
____________________________________________________________
 Noted. I've removed this task:
   [D][ ] CS2040 Midterm Revision (by: 21/03/2024 23:59)
 Now you have 2 tasks in the list.
____________________________________________________________
```

### `find` - Find a Task

Find a Task in your Task List using a search keyword.

Format: `find <keyword>`

Example:

`find CS2113`

```
____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][ ] CS2113 Week 7 Quiz
____________________________________________________________
```

### `mark` - Mark a Task as complete

Mark a Task as complete.

Format: `mark <task_id>`

Example:

`mark 1`

```
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] CS2113 Week 7 Quiz
____________________________________________________________
```

### `unmark` - Mark a Task as incomplete

Mark a Task as incomplete.

Format: `unmark <task_id>`

Example:

`unmark 1`

```
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] CS2113 Week 7 Quiz
____________________________________________________________
```