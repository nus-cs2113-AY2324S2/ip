# Bob User Guide

## Features 

### Add Tasks

Add a Task to your Task List! Choose from one of the three available Task types:
* Todo (Tasks with no timeframe)
* Deadline (Tasks with a due date)
* Event (Tasks with a start and end date)

### List Tasks

List out all Tasks in your Task List!

### Delete Tasks

Delete any unwanted Tasks from your Task List!

### Find Tasks

Find Tasks in your Task List using a search keyword!

### Update Task Statuses

Mark Tasks as done, or unmark them if they are incomplete.

## Usage

### `todo` - Add a Todo Task to your Task List

Add a new Todo Task to your Task List.

`todo <task_name>`

Example of usage: 

`todo CS2113 Week 7 Quiz`

Expected outcome:

A new Todo Task is added to your Task List.

```
____________________________________________________________
 Got it. I've added this task:
   [T][ ] CS2113 Week 7 Quiz
 Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Add a Deadline Task to your Task List

Add a new Deadline Task to your Task List.

`deadline <task_name> /by <due_date>`

`due_date` format: `DD/MM/YYYY HH:MM`

Example of usage:

`deadline CS2040 Midterm Revision /by 21/03/2024 23:59`

Expected outcome:

A new Deadline Task is added to your Task List.

```
____________________________________________________________
 Got it. I've added this task:
   [D][ ] CS2040 Midterm Revision (by: 21/03/2024 23:59)
 Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Add an Event Task to your Task List

Add a new Event Task to your Task List.

`event <task_name> /from <start_date> /to <end_date>`

`start_date` and `end_date` format: `DD/MM/YYYY HH:MM`

Example of usage:

`event NUS Hack&Roll /from 20/01/2024 10:00 /to 21/01/2024 18:00`

Expected outcome:

A new Event Task is added to your Task List.

```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] NUS Hack&Roll (from: 20/01/2024 10:00 to: 21/01/2024 18:00)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### `list` - List all Tasks in your Task List

List all Tasks in your Task List.

Example of usage:

`list`

Expected outcome:

All Tasks in your Task List are listed.

```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] CS2113 Week 7 Quiz
 2.[D][ ] CS2040 Midterm Revision (by: 21/03/2024 23:59)
 3.[E][ ] NUS Hack&Roll (from: 20/01/2024 10:00 to: 21/01/2024 18:00)
____________________________________________________________
```

### `delete` - Delete a Task from your Task List

Delete a Task from your Task List.

`delete <task_id>`

Example of usage:

`delete 2`

Expected outcome:

A new Event Task is added to your Task List.

```
____________________________________________________________
 Noted. I've removed this task:
   [D][ ] CS2040 Midterm Revision (by: 21/03/2024 23:59)
 Now you have 2 tasks in the list.
____________________________________________________________
```

### `find` - Find a Task in your Task List using a search keyword

Find a Task in your Task List using a search keyword.

`find <keyword>`

Example of usage:

`find CS2113`

Expected outcome:

All Tasks containing the keyword in their name will be listed.

```
____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][ ] CS2113 Week 7 Quiz
____________________________________________________________
```

### `mark` - Mark a Task as done

Mark a Task as done (completed).

`mark <task_id>`

Example of usage:

`mark 1`

Expected outcome:

The Task is marked as completed.

```
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] CS2113 Week 7 Quiz
____________________________________________________________
```

### `unmark` - Mark a Task as incomplete

Mark a Task as incomplete.

`unmark <task_id>`

Example of usage:

`unmark 1`

Expected outcome:

The Task is marked as incomplete.

```
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] CS2113 Week 7 Quiz
____________________________________________________________
```