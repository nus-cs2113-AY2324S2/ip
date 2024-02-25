# Casper User Guide
Casper is a chatbot-like desktop app for tracking and managing your tasks using the Command Line Interface (CLI). 

## Quick Start
1.  Ensure you have Java `11` installed.
2.  Install the latest `.jar` file [here](https://github.com/jensonjenkins/ip/releases/tag/A-Jar).
3.  Copy the file to a directory where you want to use Casper.
4. Open a command terminal, navigate to the directory where the `.jar` file is located.
5. Run `java -jar ip.jar` to run the application. You should be greeted with the following:
```dtd
    Starting
       ___ __ _ ___ _ __   ___ _ __
      / __/ _` / __| '_ \ / _ \ '__|
     | (_| (_| \__ \ |_) |  __/ |   
      \___\__,_|___/ .__/ \___|_|   
                   | |              
                   |_|               
    _______________________________________________________________________
     Boo! I'm Casper!
     What can I do for you?
    _______________________________________________________________________
```
6. To execute a command, type in the command and press the Enter key. Typing an unrecognized command will prompt a list of valid keywords to try as such: 
```dtd
help    
    _______________________________________________________________________
     Pardon? I didn't quite understand "help"
     Maybe refer to the following list of commands?
      - bye
      - list
      - mark
      - unmark
      - deadline
      - event
      - todo
      - delete
      - find
    _______________________________________________________________________
```
7. Refer to the Features below for the details of each command.


## Features
### Exit Casper: `bye`
Exits the program. 
\
Format: `bye`

### List out all your tasks: `list`
Lists out all your current tasks.
\
Format: `list`

### Mark a task as done: `mark`
Marks a given task as done.
\
Format: `mark INDEX`

- Marks the task at the specified `INDEX`.
- `INDEX` must be **a positive integer** 1, 2, 3, etc. within the bounds of your task list. 
- An invalid `INDEX` will be handled.

Examples:
- `mark 1` Marks the first task in the list.

### Un-mark a task: `unmark`
Un-marks a given task as not done.
\
Format: `unmark INDEX`

- Marks the task at the specified `INDEX`.
- `INDEX` must be **a positive integer** 1, 2, 3, etc. within the bounds of your task list.
- An invalid `INDEX` will be handled.

Examples:
- `unmark 3` Un-marks the 3rd task in the list.

### Add a todo task: `todo`
Adds a task to your list of type "todo".
\
Format: `todo DESCRIPTION`

- `DESCRIPTION` is the description of your todo task.
Examples:
- `todo Take out the trash` Adds the task of type "todo" with a description "Take out the trash" to your task list.

### Add a deadline: `deadline`
Adds a task of type "deadline" to your list.
\
Format: `deadline DESCRIPTION /by BYDATE`

- `DESCRIPTION` is the description of your deadline.
- `BYDATE` is the description of when your deadline has to be done by (Does not necessarily have to be a date).

Examples:
- `deadline Take out the trash /by 13-02-24` Adds a deadline with a description "Take out the trash" to be done by "13-02-24" to your task list.
- `deadline Take out the trash /by tomorrow` Adds a deadline description "Take out the trash" to be done by "tomorrow" to your task list.

### Add an event : `event`
Adds a task of type "event" to your list.
\
Format: `event DESCRIPTION /from FROMDATE /to TODATE`
- `DESCRIPTION` is the description of your event.
- `FROMDATE` is the string representing when the event is held from.
- `TODATE` is the string representing when the event is held until.

Examples:
- `event summer break /from next week /to next month` Adds an event called "summer break" dating from "next week" to "next month" to your list.
- `event summer break /from 01-01-24 /to 01-05-24` Adds an event called "summer break" dating from "01-01-24" to "01-05-24" to your list.

### Delete a task: `delete`
Deletes a task from your list.
\
Format: `delete INDEX`

- deletes the task at the specified `INDEX`.
- `INDEX` must be **a positive integer** 1, 2, 3, etc. within the bounds of your task list.
- An invalid `INDEX` will be handled.

Examples:
- `delete 5` Deletes the 5th task in your list.
### Find related tasks: `find`
Finds a task from your list given a keyword.
\
Format: `find KEYWORD`

- `KEYWORD` is a string you want to search for in your task list.

Examples:
-  `find Trash` returns you all tasks in your task list in which its `DESCRIPTION` contains the string "Trash".


