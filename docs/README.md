# Burger Bot User Guide

## Features 

### Creating Tasks

Burger bot is able to create different types of tasks to help manage your work!  
The different types of tasks available are **_ToDo_**, **_Deadline_** and **_Event_**.

**ToDo**

ToDos are tasks without any date/time attached to it.  

Usage: `todo TASK`

Example of Usage:

```
todo borrow book
____________________________________________________________
Got it. I've added this task:
[T][ ] borrow book
Now you have 5 tasks in the list.
____________________________________________________________
```

**Deadline**

Deadlines are tasks that need to be done before a specific date/time.  

Usage: `deadline TASK /by DATE_TIME`

Example of usage: 

```
deadline return book /by Sunday
__________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: Sunday)
Now you have 6 tasks in the list.
__________________________________________________________
```

**Event**

Events are tasks that start at a specific date/time and ends at a specific date/time.  

Usage: `event TASK /from DATE_TIME /to DATE_TIME`

Example of usage: 

```
event project meeting /from Mon 2pm /to 4pm
____________________________________________________________
Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 7 tasks in the list.
____________________________________________________________
```

### Mark/Unmark Task

The ability to mark tasks as done. Also, the user can change the status back to not done.  

Usage: `mark TASK_INDEX` or `unmark TASK_INDEX`  

Example of usage: 

```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
____________________________________________________________

mark 2
____________________________________________________________
Nice! I've marked this task as done:
[D][X] return book (by: June 6th)
____________________________________________________________

unmark 2
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] return book (by: June 6th)
____________________________________________________________
```

### Delete Task

Deletes task from the list.  

Usage: `delete TASK_INDEX`  

Example of usage: 

```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
____________________________________________________________

delete 3
____________________________________________________________
Noted. I've removed this task:
[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Find Task

Finds task based on the keyword input by the user.

Usage: `find KEYWORD`

Example of usage: 

```
find book
____________________________________________________________
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
____________________________________________________________
```

### List Tasks

Displays the list of tasks the user has.

Usage: `list`

Example of usage: 

```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
4.[T][X] join sports club
5.[T][ ] borrow book
____________________________________________________________
```

### Exit

Exits the programme.

Usage: `bye`

Example of usage: 

```
bye
---------------------------------
Saving file...
File successfully saved!
---------------------------------
Bye. Hope to see you again soon!
---------------------------------
```
