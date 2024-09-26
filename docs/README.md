# User Guide - ChatBBT

ChatBBT is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, ChatBBT can help you manage your tasks faster than traditional Graphical User Interface (GUI) apps.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest ChatBBT.jar from [https://github.com/Xb990219/ip/releases]
3. Copy the file to the folder you want to use as the home folder for ChatBBT.
4. Open a command terminal, cd into the folder you put the jar file in, and use the **java -jar ChatBBT** command to run the application.

## Features 

### Feature-TodoList
ChatBBT can help you manage your tasks by adding, deleting, marking, searching and listing them.

## Application initial interface
```
Data file already exists: ChatBBTData.txt
Data loaded successfully
Hello! I'm ChatBBT
What can I do for you?
------------------------------------------
[list] Enter Todo List feature
[bye] Quit
Please enter a command:
------------------------------------------
```

Enter 'list' to enter the Todo List feature, it will show the following interface
```
[list] Show your Todolist tasks
[mark X] Mark number X task as Done
[unmark X] Mark number X task as UnDone
[delete X] Delete number X task
[find XXX] Find related tasks with XXX
[todo XXX] Add a todo task
[deadline XXX /by yyyy-MM-dd HH:mm:ss] Add a deadline task
[event XXX /from yyyy-MM-dd HH:mm:ss /to yyyy-MM-dd HH:mm:ss] Add a Event task
[help] Show commands
[quit] Return to main menu
Where XXX Stands for any string (except space character)
------------------------------------------
Please enter a command:
```

Enter 'bye' to quit the application.

## Usage

### `list` - show all tasks in the list

Shows all tasks in the list.

Example of usage: 

`list`

Expected outcome:

It shows all existing todo tasks in the list.

```
Here are the tasks in your list:
1.[T][X] asldkjasldk
2.[T][ ] alskdjlaksdj
3.[D][ ] alskdjalsk (by: February 19, 1999 07:23:23 PM)
```

### `mark X` - mark the Xth task as done

Mark the Xth task as done.

Example of usage:

`mark 2`

Expected outcome:

Task 2 is marked as done.

```
Nice! I've marked this task as done:
  [T][X] alskdjlaksdj
>>>list
Here are the tasks in your list:
1.[T][X] asldkjasldk
2.[T][X] alskdjlaksdj
3.[D][ ] alskdjalsk (by: February 19, 1999 07:23:23 PM)
```

### `unmark X` - mark the Xth task as undone

Mark the Xth task as undone.

Example of usage:

`unmark 2`

Expected outcome:

Task 2 is marked as undone.

```
Nice! I've marked this task as undone:
  [T][ ] alskdjlaksdj
>>>list
Here are the tasks in your list:
1.[T][X] asldkjasldk
2.[T][ ] alskdjlaksdj
3.[D][ ] alskdjalsk (by: February 19, 1999 07:23:23 PM)
```

### `delete X` - delete the Xth task

Delete the Xth task.

Example of usage:

`delete 2`

Expected outcome:

Task 2 will be deleted.

```
Noted. I've removed this task:
  [T][ ] alskdjlaksdj
>>>list
Here are the tasks in your list:
1.[T][X] asldkjasldk
2.[D][ ] alskdjalsk (by: February 19, 1999 07:23:23 PM)
```

### `find XXX` - find tasks with input keyword

Find tasks with input keyword.

Example of usage:

`find asldk`

Expected outcome:

It shows all tasks with the keyword 'asldk'.

```
Here are the matching tasks in your list:
1.[T][X] asldkjasldk
```

### `todo` - add a todo task

Add a todo task.

Example of usage:

`todo asldkjasldk222`

Expected outcome:

It shows the added todo task.

```
Got it. I've added this task:
  [T][ ] asldkjasldk222
Now you have 3 tasks in the list.
>>>list
Here are the tasks in your list:
1.[T][X] asldkjasldk
2.[D][ ] alskdjalsk (by: February 19, 1999 07:23:23 PM)
3.[T][ ] asldkjasldk222
```

### `deadline` - add a deadline task

Add a deadline task with a deadline time yyyy-MM-dd HH:mm:ss.

Example of usage:

`deadline asldkjasldk223 /by 2021-09-09 07:23:23`

Expected outcome:

It shows the added deadline task.

```
Got it. I've added this task:
  [D][ ] asldkjasldk222 (by: September 9, 2021 07:23:23 AM)
Now you have 4 tasks in the list.
>>>list
Here are the tasks in your list:
1.[T][X] asldkjasldk
2.[D][ ] alskdjalsk (by: February 19, 1999 07:23:23 PM)
3.[T][ ] asldkjasldk222
4.[D][ ] asldkjasldk223 (by: September 9, 2021 07:23:23 AM)
```

### `event` - add a event task

Add a event task with a start time yyyy-MM-dd HH:mm:ss and a end time yyyy-MM-dd HH:mm:ss.

Example of usage:

`event asldkjasldk224 /from 2021-09-09 07:23:23 /to 2021-09-09 08:23:23`

Expected outcome:

It shows the added event task.

```
Got it. I've added this task:
  [E][ ] asldkjasldk224 (from: September 9, 2021 07:23:23 AM to: September 9, 2021 08:23:23 AM)
Now you have 5 tasks in the list.
>>>list
Here are the tasks in your list:
1.[T][X] asldkjasldk
2.[D][ ] alskdjalsk (by: February 19, 1999 07:23:23 PM)
3.[T][ ] asldkjasldk222
4.[D][ ] asldkjasldk223 (by: September 9, 2021 07:23:23 AM)
5.[E][ ] asldkjasldk224 (from: September 9, 2021 07:23:23 AM to: September 9, 2021 08:23:23 AM)
```

### `help` - show commands

Show all commands.

Example of usage:

`help`

Expected outcome:

It shows all commands.

```
[list] Show your Todolist tasks
[mark X] Mark number X task as Done
[unmark X] Mark number X task as UnDone
[delete X] Delete number X task
[find XXX] Find related tasks with XXX
[todo XXX] Add a todo task
[deadline XXX /by yyyy-MM-dd HH:mm:ss] Add a deadline task
[event XXX /from yyyy-MM-dd HH:mm:ss /to yyyy-MM-dd HH:mm:ss] Add a Event task
[help] Show commands
[quit] Return to main menu
Where XXX Stands for any string (except space character)
```

### `quit` - return to main menu

Return to main menu.

Example of usage:

`quit`

Expected outcome:

It returns to the main menu.

```
[list] Enter Todo List feature
[bye] Quit
Please enter a command:
```

