# User Guide for Quill

## Quick Start
1. Ensure you have `Java-11` installed on your computer
2. Download the `ip.jar` from the project GitHub
3. Copy the jar file to your desired folder
4. Open a command prompt in the same directory as the jar file
5. Run `java -jar ip.jar` to run the application.

## Features 

### Task list

Quill helps user to keep track of todos, deadlines and events 
in the form of a task list.

### Mark as done / not done

Quill allows user to mark tasks as done or not done.

### Memory Storage

Quill saves your task list in a text file, ensuring your tasks 
are retained between sessions. This enables you to effortlessly
pick up where you left off each time you use Quill.

## Usage
> **Notes about the command format:**
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.\
    > e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
> - `DATE` must be in the format `YYYY-MM-DD HH:mm`, where HH:mm is time in 24 hours format.\
    > e.g. `2024-03-02 18:16`
> - `INDEX` must be an integer and is not larger than the size of task list.

### `todo` - Add a todo task

Adds a new todo task to the task list.

Format: `todo DESCRIPTION`

Example of usage: `todo read book`

Expected outcome: 

The new todo task has now been added to the task list 
and a confirmation message will be shown.

```
____________________________________________________________
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________`
```

### `deadline` - Add a deadline task

Adds a new deadline task to the task list.

Format: `deadline DESCRIPTION /by DATE`

Example of usage: `deadline return book /by 2024-03-02 18:16`

Expected outcome: 

The new deadline task has now been added to the task list
and a confirmation message will be shown.

```
____________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: 02 Mar 2024 06:16 PM)
Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Add a event task

Adds a new event task to the task list.

Format: `event DESCRIPTION /from DATE /to DATE`

Example of usage: `event book signing /from 2024-03-01 02:30 /to 2024-03-30 21:40`

Expected outcome:

The new event task has now been added to the task list 
and a confirmation message will be shown.

```
____________________________________________________________
Got it. I've added this task:
[E][ ] book signing (from: 01 Mar 2024 02:30 AM to: 30 Mar 2024 09:40 PM)
Now you have 3 tasks in the list.
____________________________________________________________
```

### `list` - List all tasks

Lists all tasks in the task list.

Format: `list`

Expected outcome:

The task list will be shown.

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: 02 Mar 2024 06:16 PM)
3.[E][ ] book signing (from: 01 Mar 2024 02:30 AM to: 30 Mar 2024 09:40 PM)
____________________________________________________________
```

### `mark` - Mark a task as done

Marks a task as done in the task list.

Format: `mark INDEX`

Example of usage: `mark 1`

Expected outcome:

Task will be mark as done and a confirmation message will be shown.

```
____________________________________________________________
Nice! I've marked this task as done:
[T][X] read book
____________________________________________________________
```

### `unmark` - Mark a task as not done

Marks a task as not done in the task list.

Format: `unmark INDEX`

Example of usage: `unmark 1`

Expected outcome:

Task will be mark as not done and a confirmation message will be shown.

```
____________________________________________________________
OK, I've marked this task as not done yet:
[T][ ] read book
____________________________________________________________
```

### `delete` - Delete a task

Delete a task from the task list.

Format: `delete INDEX`

Example of usage: `delete 1`

Expected outcome: 

Task will be deleted from task list and a confirmation message will be shown.

```
____________________________________________________________
Got it. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
____________________________________________________________
```

### `find` - Find tasks by keyword

Finds matching tasks in the task list by keyword.

Format: `find DESCRIPTION`

Example of usage: `find return`

Expected outcome: 

A list of tasks that have matching descriptions to the keyword will be shown.

```
____________________________________________________________
Here are the matching tasks in your list:
1.[D][ ] return book (by: 02 Mar 2024 06:16 PM)
____________________________________________________________
```

### `bye` - Close Quill

Close Quill and store the data into the memory file.

Format: `bye`

Expected outcome: 

A goodbye message will be shown.

```
____________________________________________________________
Bye! Hope to see you again soon!
____________________________________________________________

```
