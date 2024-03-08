# Stella User Guide

## Setup

1) Make sure that you have Java 11 on your device.

2) Download the latest `ip.jar` from this repo.

3) Move `ip.jar` to the directory that you plan to use as Stella's home directory.

4) Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.

5) Type the command in the terminal and hit `Enter` to execute it.

6) Refer to the Features section for detailed tutorial on the commands.

## Features 

Items in `UPPER_CASE` are the parameters to be supplied by the user.
For example: in `todo TASK_NAME`, `TASK_NAME` can be used as `todo sleep`.

The parameters should be in correct order.

Extraneous parameters for commands that do not take in parameters will be ignored.
For example: `list 23131` is the same as `list`.

Stella will automatically save your data when you exit the program.

### `todo TASK_NAME` - Add a new to-do task.

`TASK_NAME`: the to-do task's name.

Example: 
`todo sleep`

Expected outcome: Stella will add a to-do task to the list.

Expected output:
```
--------------------------------------
Got it. I've added this task:
[T][ ] sleep
--------------------------------------
```

### `deadline TASK_NAME -by DATE` - Add a new deadline task.

`TASK_NAME`: the deadline's name.
`DATE`: the deadline's date

Example:
`deadline CS2113 Weekly Quiz -by 3/7/2024 1159`

Expected outcome: Stella will add a new deadline task into the task list.

Expected output: 
```
--------------------------------------
Got it. I've added this task:
[D][ ] CS2113 Weekly Quiz (do by: 3/7/2024 1159)
--------------------------------------
```

### `event TASK_NAME -from START_TIME -to END_TIME`

`TASK_NAME`: the event's name.
`START_TIME`: the event's start time.
`END_TIME`: the event's end time.

Example:
`event The Eras Tour -from: 2/3/2024 -to: 9/3/2024`

Expected outcome: Stella will add a new event to the task list.

Expected output:
```
--------------------------------------
Got it. I've added this task:
[E][ ] The Eras Tour (from: 2/3/2024 to: 9/3/2024)
--------------------------------------
```

### `delete INDEX` - Delete a task from the list.

`INDEX`: the index of the task in the task list user wants to delete.

Example: We have a list with 3 tasks
```
--------------------------------------
1.[T][ ] sleep
2.[D][ ] CS2113 Weekly Quiz (do by: 3/7/2024 1159)
3.[E][ ] The Eras Tour (from: 2/3/2024 to: 9/3/2024)
--------------------------------------
```
`delete 1` will result in the task no. 1 being deleted.

Expected outcome: Stella will remove the task with the specified index from the list.

### `list` - View all tasks in the current list.

Example:
```
--------------------------------------
1.[T][ ] sleep
2.[D][ ] CS2113 Weekly Quiz (do by: 3/7/2024 1159)
3.[E][ ] The Eras Tour (from: 2/3/2024 to: 9/3/2024)
--------------------------------------
```

### `mark INDEX` - Mark a task as done.

`INDEX`: the index of the task to be marked.

Example: We have a list as follows.
```
--------------------------------------
1.[T][ ] sleep
2.[D][ ] CS2113 Weekly Quiz (do by: 3/7/2024 1159)
3.[E][ ] The Eras Tour (from: 2/3/2024 to: 9/3/2024)
--------------------------------------
```
`mark 1` will result in
```
--------------------------------------
Nice! I've marked this task as done:
[T][X] sleep
--------------------------------------
```
The list will now be
```
--------------------------------------
1.[T][X] sleep
2.[D][ ] CS2113 Weekly Quiz (do by: 3/7/2024 1159)
3.[E][ ] The Eras Tour (from: 2/3/2024 to: 9/3/2024)
--------------------------------------
```

Expected outcome: Stella will mark a task as done.

### `unmark INDEX` - Mark a task as not done.

`INDEX`: the index of the task to be unmarked.

Example: From the `mark INDEX` section, out list is as follows.
```
--------------------------------------
1.[T][X] sleep
2.[D][ ] CS2113 Weekly Quiz (do by: 3/7/2024 1159)
3.[E][ ] The Eras Tour (from: 2/3/2024 to: 9/3/2024)
--------------------------------------
```
`unmark 1` will result in
```
--------------------------------------
OK, I've marked this task as not done yet:
[T][ ] sleep
--------------------------------------
```
The list will now be
```
--------------------------------------
1.[T][ ] sleep
2.[D][ ] CS2113 Weekly Quiz (do by: 3/7/2024 1159)
3.[E][ ] The Eras Tour (from: 2/3/2024 to: 9/3/2024)
--------------------------------------
```

### `find KEYWORD` - Find a task (case-sensitive)

`KEYWORD`: the keyword that the user wants to search for.

For example:
We have a list as follows.
```
--------------------------------------
1.[T][ ] sleep
2.[D][ ] CS2113 Weekly Quiz (do by: 3/7/2024 1159)
3.[E][ ] The Eras Tour (from: 2/3/2024 to: 9/3/2024)
--------------------------------------
```
`find sl` will result in
```
--------------------------------------
Here are the matching tasks in your list:
1. [T][ ] sleep
--------------------------------------
```

### `bye` - Exit from Stella.
