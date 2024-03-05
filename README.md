# KuroBot

This is a chat bot that stores tasks and performs various operations to the tasks. Given below are instructions on how to use it.

## Features
> [!NOTE]
> Words in UPPER_CASE are the parameters to be supplied by the user.

\
**1. Adding a todo:** todo 

Adds a task of type todo to the task list.

Format: todo TASK_NAME 

Example:
>todo borrow book

\
**2. Adding a deadline:** deadline

Adds a task of type deadline to the task list.

Format: todo TASK_NAME /by DEADLINE

Example:
>deadline return book /by Sunday

\
**3. Adding a event:** event

Adds a task of type event to the task list.

Format: todo TASK_NAME /from START_TIME /to END_TIME

Example:
>event project meeting /from Mon 2pm /to 4pm

\
**4. Viewing all tasks:** list 

Shows a list of all the added tasks.

Format: list

\
**5. Mark a task as done:** mark

Marks the specified task as completed.

Format: mark TASK_INDEX

Example:
>mark 2

\
**6. Mark a task as undone:** unmark 

Marks the specified task a incompleted.

Format: unmark TASK_INDEX

Example:
>unmark 2

\
**7. Delete a task from list:** delete 

Removes the specified task from the list.

Format: delete TASK_INDEX

Example:
>delete 3

\
**8. End the session:** bye 

Exits the program.

Format: bye

> [!TIP]
> Tasks data are saved in the hard disk automatically after any command that changes the data. \
> There is no need to save manually.


