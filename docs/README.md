# JingHao User Guide

JingHao is a Command Line Interface (CLI) chatbot that is able to manage
your task list.

* [Quick Start](#quick-start)
* [Features](#features)
    - [Adding a todo task: `todo`](#adding-a-todo-task---todo)
    - [Adding a deadline task: `deadline`](#adding-a-deadline-task---deadline)
    - [Adding an event task: `event`](#adding-an-event-task---event)
    - [Listing all tasks: `list`](#list-all-tasks---list)
    - [Marking task as complete: `mark`](#marking-task-as-complete---mark)
    - [Marking task as incomplete: `unmark`](#marking-tasks-as-incomplete---unmark)
    - [Finding tasks by keyword: `find`](#find-tasks-by-keyword---find)
    - [Deleting a task: `delete`](#delete-a-task---delete)
    - [Exiting the chatbot: `bye`](#Exiting-the-chatbot---bye)
    - [Saving the storage data](#saving-the-storage-data)
* [FAQ](#faq) 


--------------------------------------------------------------

### Quick Start

1. Ensure that you have at least `Java 11` installed in your computer.
2. Download the latest `JingHao.jar` from [here](https://github.com/JingHaoooo/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the directory you have saved the .jar file in.
5. Run the command `java -jar JingHao.jar` to run the application. 
<br> A CLI should appear within a few seconds as shown below:
~~~
____________________________________________________________
Hello! I'm JingHao
What can I do for you?
____________________________________________________________
Input: 
~~~

Now, you are ready to use the chatbot. Type the command press Enter to execute it. <br>
Some example commands you can try:
* `list`: Lists all the task in the list.
* `todo Read book`: Adds a todo task named Read book to the list.
* `mark 1`: Marks the first task in the list as completed.
* `delete 1`: Deletes the first task in the list.
* `bye`: Exits the chatbot app.

For new users, you may refer to the [Features](#features) below for
a detailed explanation of all the available commands.

--------------------------------------------------------------
## Features

> [!NOTE] Notes about the command format:
> * `Description` is the parameters to be supplied by the user. <br>
    > For example, in`todo Description`, the command `todo return book` 
    takes in the parameter `return book`as`Description`.
> 
>
> * Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored. <br>
    > For example, if the command specifies `list 123`, it will be interpreted as `list` instead.


### Adding a Todo task - `todo`
Add a todo tasks into the task list, without any date/time.

Format: `todo INPUT`

Example of usage: 
`todo Read book`

Icon: `[T]` 

Expected outcome: <br>
JingHao will display a message similar to the following upon successful addition of the todo task.
~~~
Got it. I've added this task:
 [T][ ] Read book
Now you have 1 tasks in the list.
~~~

### Adding a Deadline task - `deadline`
> [!IMPORTANT] <br>
> 1. If the time `HHMM` is not given, the time will be defaulted to 2359.
> 2. If the time given is already over, an error message will be shown.
> 

Deadline feature allow users to add a deadline task into the task list, 
with the specific deadline date/time.

Format: `deadline Description /by YYYY-MM-DD HHMM`

Example of usage:
* `deadline Return book /by 2024-03-01 1300`
* `deadline Complete CS2113 UG /by 2024-03-08`

Icon: `[D]`

Expected outcome: <br>
JingHao will display a message similar to the following upon successful addition of the deadline task.
~~~
Got it. I've added this task:
 [D][ ] Return book (By: 1 Mar 2024 1300)
Now you have 2 tasks in the list.
~~~
~~~
Got it. I've added this task:
 [D][ ] Complete CS2113 UG (By: 8 Mar 2024 2359)
Now you have 3 tasks in the list.
~~~

### Adding an Event task - `event`
> [!IMPORTANT]
> 1. If the time `HHMM` is not given, the time will be defaulted to 2359.
> 2. If the end date/time is earlier than start date/time, an error will be shown.

Adds an event task into the task list, with the specific start date/time and end date/time.

Format: 
* `event Return book /from 2024-02-25 2300 /to 2024-02-29 1800`
* `event CS2113 IP /from 2024-01-15 0000 /to 2024-03-08`

Icon: `[E]`

Expected outcome: <br>
JingHao will display a message similar to the following upon successful addition of the event task.
~~~
Got it. I've added this task:
 [E][ ] Return book (From: 25 Feb 2024 2300 To: 29 Feb 2024 1800)
Now you have 4 tasks in the list.
~~~
~~~
Got it. I've added this task:
 [E][ ] CS2113 IP (From: 15 Jan 2024 0000 To: 8 Mar 2024 2359)
Now you have 5 tasks in the list.
~~~

### List all tasks - `list`
Displays all tasks in the task list on the screen.

Format: `list`

Expected outcome: <br>
JingHao will display a message similar to the following upon receiving the `list` command
~~~
Here are the tasks in your list:
1.[T][ ] Read book
2.[D][ ] Return book (By: 1 Mar 2024 1300)
3.[D][ ] Complete CS2113 UG (By: 8 Mar 2024 2359)
4.[E][ ] Return book (From: 25 Feb 2024 2300 To: 29 Feb 2024 1800)
5.[E][ ] CS2113 IP (From: 15 Jan 2024 0000 To: 8 Mar 2024 2359)
~~~

### Marking task as complete - `mark`
>[!NOTE] `Description` refers to the `index` of the task in the list to be marked.

Marks the specified task in the task list as complete.

Format: `mark Description`
* Marks the task at the specified index.
* The index refers to the index number shown in the displayed task list.
* The index must be a **positive integer**

Example of usage: `mark 3`

Expected outcome: <br>
JingHao will display a message similar to the following upon receiving the `mark` command
~~~
Nice! I've marked this task as done: 
[D][X] Complete CS2113 UG (By: 8 Mar 2024 2359)
~~~

### Marking tasks as incomplete - `unmark`
>[!NOTE] `Description` refers to the `index` of the task in the list to be marked.

Marks the specified task in the task list as incomplete.

Format: `unmark Description`
* Marks the task at the specified index.
* The index refers to the index number shown in the displayed task list.
* The index must be a **positive integer**

Example of usage: `unmark 3`

Expected outcome: <br>
JingHao will display a message similar to the following upon receiving the `unmark` command
~~~
OK, I've marked this task as not done yet: 
[D][ ] Complete CS2113 UG (By: 8 Mar 2024 2359)
~~~

### Find tasks by keyword - `find`
>[!NOTE] `Description` refers to the keyword.


Find a task by searching for a keyword.

Format: `find Description`
* The search is not case-sensitive. e.g. `BOOK` will match `book`
* The order of the keywords does matter. e.g. `read book` will not match `book read`
* Only the description is searched.

Example of usage: 
* `find Book`
* `find Food`

Expected outcome: <br>
JingHao will display a message similar to the following if there are matches found.
~~~
Here are the matching tasks in your list:
1.[T][ ] Read book
2.[D][ ] Return book (By: 1 Mar 2024 1300)
3.[E][ ] Return book (From: 25 Feb 2024 2300 To: 29 Feb 2024 1800)
~~~
Otherwise, JingHao will display the following message if there are no matches. 
~~~
There are no matching task found.
~~~

### Delete a Task - `delete`
>[!NOTE] `Description` refers to the `index` of the task in the list to be deleted.

Deletes the specified task from the task list.

Format: `delete Description`
* Marks the task at the specified index.
* The index refers to the index number shown in the displayed task list.
* The index must be a **positive integer**

Example of usage: `delete 1`

Expected outcome: <br>
JingHao will display a message similar to the following upon receiving the `delete` command.
~~~
Noted. I have removed this task:
[T][ ] Read book
Now you have 4 tasks in the list.
~~~

### Exiting the chatbot - `bye`
Exits the chatbot.

Format: `bye`

Example of usage: `bye`

Expected outcome: <br>
JingHao will display the following message upon input of the `bye` command.
~~~
Bye. Hope to see you again soon!
~~~

### Saving the storage data
> [!CAUTION]
> Storage data will be saved in directory `data` in your home directory. <BR>
> Corrupted data file will be deleted immediately upon running the bot.

JingHao data are saved in the hard disk automatically after any command that changes the data. <br>
There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another computer? <br>
**A**: Install the app in the other computer and overwrite the empty data file 
it creates with the file that contains the data of your previous JingHao home folder.
