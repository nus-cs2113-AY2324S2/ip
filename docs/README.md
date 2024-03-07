# Jarvas User Guide

Jarvas is a bot used for managing a task list, optimised for use via a Command Line Interface (CLI).

* [Quick Start](#quick-start)
* [Features](#features)
  - [Add Todo task: `todo`](#add-todo-task-todo)
  - [Add Deadline Task: `deadline`](#add-deadline-task-deadline)
  - [Add Event Task: `event`](#add-event-task-event)
  - [List Tasks: `list`](#list-tasks-list)
  - [Mark Task as Done: `mark`](#mark-task-as-done-mark)
  - [Mark Task as Undone: `unmark`](#mark-task-as-undone-unmark)
  - [Deleting Tasks: `delete`](#deleting-tasks-delete)
  - [Find a Task: `find`](#find-a-task-find)
  - [Print Help Message: `help`](#print-help-message-help)
  - [Exit Bot and Write Data: `bye`](#exit-bot-bye)
* [Frequently Asked Questions (FAQ)](#faq)

## Quick Start

1. Ensure that you have the latest Java 11.
2. Download the latest `ip.jar` file from https://github.com/L5-Z/ip/releases.
3. Create a new folder to use as the home folder for Jarvas, on your local drive.
4. Copy the file to the folder you have created.
5. Open a command terminal (either cmd.exe or bash), `cd` to the folder with `ip.jar` in it, and use `java -jar ip.jar` to run the application.
6. The welcome message for Jarvas should be printed to the console.
7. Type commands in the command line and press <kbd>Enter</kbd> to execute it. Using `help` and pressing <kbd>Enter</kbd> will print the help message.

```
$ java -jar ip.jar
____________________________________________________________
 _____
(___  )                                 
    | |   _ _  _ __  _   _    _ _   ___ 
 _  | | /'_` )( '__)( ) ( ) /'_` )/',__)
( )_| |( (_| || |   | \_/ |( (_| |\__, \
`\___/'`\__,_)(_)   `\___/'`\__,_)(____/
____________________________________________________________
Hello! I'm Jarvas
What can I do for you?
____________________________________________________________
____________________________________________________________
Prior data file found
Previous data has been imported.
____________________________________________________________
```

Some example commands:
* `help`: Prints the help message out.
* `list`: Lists out all tasks recorded.

## Features

### Notes about Command Format

* Words in `UPPER_CASE` are parameters supplied **by the user**.
  - E.g. in `todo LABEL`, `LABEL` is a parameter which can be used in the form `todo Finish my coding`.

### Add Todo Task: `todo`

Adds a todo task. Does not have specific deadline nor start or end date.

Format: `todo LABEL`
* All fields must be provided.
* `LABEL` represents the task name/description.

Example:
* `todo shower`
* `todo workout at the gym`

Expected Output:
```
todo homework
____________________________________________________________
Got it. I've added: 
[T][ ] homework
You now have 1 task in the list.
____________________________________________________________
```

### Add Deadline Task: `deadline`

Adds a deadline task, which has **one** specific deadline.

Format: `deadline LABEL /by DUE`
* `LABEL` represents the task description.
* `DUE` is a string representing the due date/time.

Example:
* `deadline return book /by tomorrow
* `deadline sleep /by 10pm tonight`

Expected Output:
```
deadline sleep /by 10pm tonight
____________________________________________________________
Got it. I've added: 
[D][ ] sleep (by: 10pm tonight)
You now have 1 task in the list.
____________________________________________________________
```

### Add Event Task: `event`

Adds an event task, which has a start time and end time.

Format: `event LABEL /by STARTTIME /to ENDTIME`
* `LABEL` represents the task description.
* `STARTTIME` and `ENDTIME` are strings representing the start date/time and end date/time respectively

Example:
* `event project meeting /from Monday 2pm /to 4pm`

Expected Output:
```
event project meeting /from Monday 2pm /to 4pm
____________________________________________________________
Got it. I've added: 
[E][ ] project meeting (from: Monday 2pm to: 4pm)
You now have 1 task in the list.
____________________________________________________________
```

### List Tasks: `list`

Lists all tasks within the task list.

Format: `list`

Expected Output:
```
list
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
1. [T][ ] homework
2. [D][ ] sleep (by: 10pm tonight)
3. [E][ ] project meeting (from: Monday 2pm to: 4pm)
____________________________________________________________
```
### Mark Task as Done: `mark`

Marks a task as done. Task list will use an `X` to denote a task is marked as done.

Format: `mark INDEX`
* `INDEX` represents the task number. Check `list` to see each number.

Example:
* `mark 1`

Expected Output:
```
list
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
1. [T][ ] homework
____________________________________________________________
mark 1
____________________________________________________________
Nice! I've marked this task as done:
[T][X] homework
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
1. [T][X] homework
____________________________________________________________
```

### Mark Task as Undone: `unmark`

Marks a task as undone. Task list will use a blank space character to denote a task is marked as undone.

Format: `unmark INDEX`
* `INDEX` represents the task number. Check `list` to see each number.

Example:
* `unmark 2`

Expected Output:

```
list
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
1. [T][ ] homework
2. [E][X] project meeting (from: Monday 2pm to: 4pm)
____________________________________________________________
unmark 2
____________________________________________________________
Okay, I've marked this task as not done yet:
[E][ ] project meeting (from: Monday 2pm to: 4pm)
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
1. [T][ ] homework
2. [E][ ] project meeting (from: Monday 2pm to: 4pm)
____________________________________________________________
```

### Deleting Tasks: `delete`

Deletes a task from the list.

Format: `delete INDEX`
* `INDEX` represents the task number. Check `list` to see each number.

Example: 
* `delete 3`

Expected Output:

```
list
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
1. [T][ ] homework
2. [E][ ] project meeting (from: Monday 2pm to: 4pm)
3. [D][ ] sleep (by: 10pm tonight)
____________________________________________________________
delete 3
____________________________________________________________
Deleted: 3. [D][ ] sleep (by: 10pm tonight)
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
1. [T][ ] homework
2. [E][ ] project meeting (from: Monday 2pm to: 4pm)
____________________________________________________________
```

### Find a Task: `find`

Finds a task based on **ONE keyword** and prints it out.

Format: `find KEYWORD`
* `KEYWORD` should only be **1 word long**.
* `KEYWORD` has to match the task **exactly** if longer than 1 word.

Example:
* `find book`
* `find CS2113`

Expected Output:

```
list
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
1. [T][ ] homework
2. [E][ ] project meeting (from: Monday 2pm to: 4pm)
3. [D][ ] sleep (by: 10pm tonight)
4. [T][ ] put baby to sleep
____________________________________________________________
find sleep
____________________________________________________________
Here are the matching tasks in your list:
1. [D][ ] sleep (by: 10pm tonight)
2. [T][ ] put baby to sleep
____________________________________________________________
find proj meet
____________________________________________________________
There are no results that match your search query.
____________________________________________________________
```

### Print Help Message: `help`

Prints help message.

Format: `help`

Expected Output:

```
help
____________________________________________________________
Commands List:

list - prints out the List
help - procures command list
bye - terminates the bot
____________________________________________________________
todo - adds an item to the List
event - adds an event to the List
deadline - adds a deadline to the List
mark - indicates an item on the List as done
unmark - indicates an item on the List as not done
delete - deletes a task from the List
find - searches for a task from the List containing the keyword
____________________________________________________________
todo format: todo *parameter*
event format: event *parameter* /from *start time* /to *end time*
deadline format: deadline *parameter* /by *end time*
unmark format: unmark *index*
mark format: mark *index*
delete format: delete *index*
find format: find *keyword*
____________________________________________________________
```

### Exit Bot: `bye`

Exits bot and writes task list data to `saveFile.txt`. 
Exiting bot **without** `bye` **MAY write** to `saveFile.txt` but can **result in corruption**.

Format: `bye`

Expected Output:
```
bye
____________________________________________________________
Have a good day!
Bye, see you soon!
____________________________________________________________
____________________________________________________________
Saved tasks as: saveFile.txt
____________________________________________________________
```

## FAQ

1. If I exit the bot using anything other than `bye`, will I lose data?

No, data **might not** be lost. However, there is a chance of data corruption which may inevitably result in a full or partial loss of data depending on where it occurs.

2. How do I transfer data to other machines?

Copy the `ip.jar` file and `saveFile.txt` to the other machine. Place both of the files in the same directory, and Jarvas will be able to load the data file.