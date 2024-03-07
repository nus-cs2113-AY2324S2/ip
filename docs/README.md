# Jane User Guide

Welcome to Jane, a chatbot used for task management, via a Command Line Interface 
(CLI). Jane is designed to help you organize your tasks efficiently and stay on top
of your to-do list. 

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
   * [Add Todo Task: `todo`](#add-todo-task-todo)
   * [Add Deadline Task: `deadline`](#add-deadline-task-deadline)
   * [Add Event Task: `event`](#add-event-task-event)
   * [List Tasks: `list`](#list-tasks-list)
   * [Mark Task As Done: `mark`](#mark-task-as-done-mark)
   * [Mark Task As Undone: `unmark`](#mark-task-as-undone-unmark)
   * [Delete A Task: `delete`](#delete-a-task-delete)
   * [Find Keyword: `find`](#find-keyword-find)
   * [Exit Jane: `bye`](#exit-jane-bye)
3. [Frequently Asked Questions (FAQ)](#frequently-asked-questions-faq)

## Quick Start

1. Ensure that you have the latest Java 11.
2. Download the latest `ip.jar` file from https://github.com/syj02/ip/releases.
3. Copy the file to the folder you want to use as the home folder for Jane.
4. Open a command terminal, `cd` to the folder containing `ip.jar` and use 
   `java -jar ip.jar` to run the application.
5. The welcome message for Jane should be printed to the console.
6. Type commands in the command line and press <kbd>Enter</kbd> to execute it.

```
$ java -jar ip.jar
 _____    _____    ____ _    _____ 
|____ |  |     |  |    | |  | ____|
    | |  |  |  |  | |  | |  | |___ 
 _  | |  |  _  |  | |  | |  |  ___|
| |_| |  | | | |  | |  | |  | |___ 
|_____|  |_| |_|  |_| ___|  |_____|
____________________________________________________________
Hello! I am Jane.
What can I do for you?
____________________________________________________________
```

Some example commands:
* `list`: List all tasks recorded.
* `bye`: Exit Jane chatbot.

## Features 

### Notes about the command format

* Words in `UPPER_CASE` are parameters to be provided by users.
  * E.g. `todo TASK`, `TASK` is a parameter that could be used in the form of 
  `todo Submit assignment`.

### Add Todo Task: `todo`

Adds a Todo task. No specifications on deadline, start date, or end date.

Format: `todo TASK`
* All fields must be provided.
* `TASK` represents the description or name of the task.

Example:
* `todo Watch a movie`
* `todo take a nap`

Expected Output:
```
todo Watch a movie
____________________________________________________________
Got it. I've added this task:
[T][ ]  Watch a movie
Now you have 1 tasks in the list.
____________________________________________________________
```

### Add Deadline Task: `deadline`

Adds a Deadline task. It has a specific **deadline**.

Format: `deadline TASK /by DEADLINE`
* All fields must be provided.
* `TASK` represents the description or name of the task.
* `DEADLINE` represents the due date and/or time of the task.

Example:
* `deadline Complete quiz /by tomorrow`
* `deadline go for a run /by Tuesday 6pm`

Expected Output:
```
deadline go for a run /by Tuesday 6pm
____________________________________________________________
Got it. I've added this task:
[D][ ]  go for a run (by: Tuesday 6pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Add Event Task: `event`

Adds an Event task. It has a specific **start date/time** and **end date/time**.

Format: `event TASK /from START_DATE_TIME /to END_DATE_TIME`
* All fields must be provided.
* `TASK` represents the description or name of the task.
* `START_DATE_TIME` represents the date and/or time of the start of the task.
* `END_DATE_TIME` represents the date and/or time of the end of the task.

Example:
* `event Attend lecture /from today 4pm /to 6pm`
* `event sports competition /from 31 Mar 1pm /to 4 May 5pm`

Expected Output:
```
event sports competition /from 31 Mar 1pm /to 4 May 5pm
____________________________________________________________
Got it. I've added this task:
[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

### List Tasks: `list`

Lists all tasks recorded in the task list.

Format: `list`

Expected Output:
```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ]  Watch a movie
2.[D][ ]  go for a run (by: Tuesday 6pm)
3.[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
____________________________________________________________
```

### Mark Task As Done: `mark`

Mark a task as done, which will be denoted as [X] in the task list.

Format: `mark SEQUENCE`
* All fields must be provided.
* `SEQUENCE` represents the number of the task in the list. Check `list` to see number.

Example:
* `mark 1`

Expected Output:
```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ]  Watch a movie
2.[D][ ]  go for a run (by: Tuesday 6pm)
3.[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
____________________________________________________________
mark 1
____________________________________________________________
Nice! I've marked this task as done:
[T][X]  Watch a movie
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X]  Watch a movie
2.[D][ ]  go for a run (by: Tuesday 6pm)
3.[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
____________________________________________________________
```

### Mark Task As Undone: `unmark`

Mark a task as undone, which will be denoted as [ ] in the task list.

Format: `mark SEQUENCE`
* All fields must be provided.
* `SEQUENCE` represents the number of the task in the list. Check `list` to see number.

Example:
* `unmark 3`

Expected Output:
```
list 
____________________________________________________________
Here are the tasks in your list:
1.[T][X]  Watch a movie
2.[D][ ]  go for a run (by: Tuesday 6pm)
3.[E][X]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
____________________________________________________________
unmark 3
____________________________________________________________
OK, I've marked this task as not done yet:
[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X]  Watch a movie
2.[D][ ]  go for a run (by: Tuesday 6pm)
3.[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
____________________________________________________________
```

### Delete A Task: `delete`

Deletes a task from the list.

Format: `delete SEQUENCE`
* All fields must be provided.
* `SEQUENCE` represents the number of the task in the list. Check `list` to see number.

Example:
* `delete 2`

Expected Output:
```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X]  Watch a movie
2.[D][ ]  go for a run (by: Tuesday 6pm)
3.[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
4.[T][ ]  eat a Banana
5.[D][ ]  make a banana cake (by: 2 March)
____________________________________________________________
delete 2
____________________________________________________________
Noted. I've removed this task:
[D][ ]  go for a run (by: Tuesday 6pm)
Now you have 4 tasks in the list.
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X]  Watch a movie
2.[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
3.[T][ ]  eat a Banana
4.[D][ ]  make a banana cake (by: 2 March)
____________________________________________________________
```

### Find Keyword: `find`

Find all tasks that contains **ONE keyword** provided and print them out.

Format: `find KEYWORD`
* All fields must be provided.
* `KEYWORD` represents word to be matched in the tasks' description.
* `KEYWORD` should be **ONE WORD LONG**.

Example:
* `find banana`
* `find Banana`
* `find 1010`

Expected Output:
```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X]  Watch a movie
2.[E][ ]  sports competition (from: 31 Mar 1pm to: 4 May 5pm)
3.[T][ ]  eat a Banana
4.[D][ ]  make a banana cake (by: 2 March)
____________________________________________________________
find banana
____________________________________________________________
Here are the matching tasks in your list:
[D][ ]  make a banana cake (by: 2 March)
____________________________________________________________
find Banana
____________________________________________________________
Here are the matching tasks in your list:
[T][ ]  eat a Banana
____________________________________________________________
find 1010
____________________________________________________________
Here are the matching tasks in your list:
____________________________________________________________
```

### Exit Jane: `bye`

Exit the Jane chatbot. Exiting message will be printed.

Format: `bye`

Expected Output:
```
bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Frequently Asked Questions (FAQ)
1. If I exit the chatbot without using `bye`, will I lose data?
   
   No, data might not be lost. However, there is a chance of data corruption which may 
   result in a full or partial loss of data.

2. How do I transfer data to other machines?

   Copy the `ip.jar` file and `jane.txt` to the other machine. Place both of the files 
   in the same directory, and Jarvas will be able to load the data file.

