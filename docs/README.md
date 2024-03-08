<!-- 
Format used here follows the structure of the following webpage:
https://se-education.org/addressbook-level3/UserGuide.html
-->

# Anonbot User Guide
Anonbot is a Command-line application that allows you to create and manage all your tasks.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Showing the list of supported commands: `help`](#showing-the-list-of-supported-commands-help)
  - [Creating a new todo task: `todo`](#creating-a-new-todo-task-todo)
  - [Creating a new deadline task: `deadline`](#creating-a-new-deadline-task-deadline)
  - [Creating a new event task: `event`](#creating-a-new-event-task-event)
  - [Showing all active tasks: `list`](#showing-all-active-tasks-list)
  - [Finding tasks matching keyword: `find`](#finding-tasks-matching-keyword-find)
  - [Marking a task as completed: `mark`](#marking-a-task-as-completed-mark)
  - [Marking a task as incomplete: `unmark`](#marking-a-task-as-incomplete-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Exiting the program: `bye`, `exit`](#exiting-the-program-bye-exit)
  - [Saving the data file](#saving-the-data-file)
  - [Editing the data file](#saving-the-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

# Quick Start
<!--
Steps outlined follows https://se-education.org/addressbook-level3/UserGuide.html#quick-start
-->

Here is how to get started with running the program.
1. Ensure you have Java `11` installed in your computer.
2. Download the latest `anonbot.jar` from [here.](https://github.com/annoy-o-mus/ip)
3. Copy the `.jar` file to the folder you want to execute the program from.
4. Open a command terminal, navigate into the directory containing the `.jar` file.
5. Type `java -jar anonbot.jar` to start running the application.

# Features
## Showing the list of supported commands: `help`
Shows the list of commands supported by anonbot.

Format: `help`
```
help
____________________________________________________________
List of Supported commands:
bye, exit, list, mark, unmark, todo, deadline, event, delete, find, help, 
____________________________________________________________
```


## Creating a new todo task: `todo`
Creates a new todo task.

Format: `todo <description>`
- An empty description for the todo command will show an error, along with the correct syntax.

Examples: 
- `todo`: Error out and provides correct syntax.
- `todo Test out the document`: Create a todo task with the description "Test out the document".
```
todo
____________________________________________________________
[Error] todo task has an empty task Description
Syntax: todo <description>
____________________________________________________________

todo Test out the document
____________________________________________________________
Alright. I have added this task:
1.[T][ ] Test out the document
Now you have 1 tasks in the list.
____________________________________________________________
```

## Creating a new deadline task: `deadline`
Creates a new deadline task.

Format: `deadline <description> [/by <end_time>]`
- An empty description for the deadline command will show an error, along with the correct syntax.
- The `/by` field is optional.
  - The `end_time` field prefers an end time, but any descriptive information is accepted.

Examples:
- `deadline`: Errors out and provides the correct syntax. 
- `deadline anonbot deployment`: Creates a deadline task without additional information.
- `deadline anonbot deployment v2 /by today ASAP`: Creates a deadline task with `/by` information.
```
deadline
____________________________________________________________
[Error] deadline task has an empty task Description
Syntax: deadline <description> [/by <end_time>]
____________________________________________________________

deadline anonbot deployment
____________________________________________________________
Alright. I have added this task:
2.[D][ ] anonbot deployment (by: )
Now you have 2 tasks in the list.
____________________________________________________________

deadline anonbot deployment v2 /by today ASAP
____________________________________________________________
Alright. I have added this task:
3.[D][ ] anonbot deployment v2 (by: today ASAP)
Now you have 3 tasks in the list.
____________________________________________________________
```

## Creating a new event task: `event`
Creates a new event task.

Format: `event <description> [/from <start_time> [/to <end_time>]]`
- An empty description for the deadline command will show an error, along with the correct syntax.
- The `/from` and `/to` fields are optional. 
  - However, if you want to use the `/to` field, you must include the `/from` field as well.
  - The `start_time` and `end_time` fields prefers a time, but any descriptive information is accepted.

Examples:
- `event`: Errors out and provides the correct syntax.
- `event Celebrate 2024`: Adds the event without additional information.
- `event Move House /from Jan`: Adds the event with additional `/from` field information.
- `event Midterms /from 1 Mar 24 2pm /to 4pm` Adds the event with `/from` and `/to` information
```
event
____________________________________________________________
[Error] event task has an empty task Description
Syntax: event <description> [/from <start_time> [/to <end_time>]]
____________________________________________________________

event Celebrate 2024
____________________________________________________________
Alright. I have added this task:
4.[E][ ] Celebrate 2024 (from:  to: )
Now you have 4 tasks in the list.
____________________________________________________________

event Move House /from Jan
____________________________________________________________
Alright. I have added this task:
5.[E][ ] Move House (from: Jan to: )
Now you have 5 tasks in the list.
____________________________________________________________

event Midterms /from 1 Mar 24 2pm /to 4pm
____________________________________________________________
Alright. I have added this task:
6.[E][ ] Midterms (from: 1 Mar 24 2pm to: 4pm)
Now you have 6 tasks in the list.
____________________________________________________________
```


## Showing all active tasks: `list`

## Finding tasks matching keyword: `find`

## Marking a task as completed: `mark`

## Marking a task as incomplete: `unmark`

## Deleting a task: `delete`

## Exiting the program: `bye`, `exit`

## Saving the data file
Anonbot data are saved to the hard disk automatically when a new entry, modification or deletion is made 
to the task list. There is no need for you to save manually. 

By default, they are saved under `./data/tasklist.txt`

## Editing the data file
Data files are saved in `./data/tasklist.txt`. Advanced users can modify the text file directly

<!--
Credit: https://raw.githubusercontent.com/se-edu/addressbook-level3/master/docs/UserGuide.md
-->
**Caution:**
If your changes to the data file makes its format invalid, 
anonbot will discard all data and start with an empty data file at the next run. 
Hence, it is recommended to take a backup of the file before editing it.

Furthermore, certain edits can cause the anonbot to behave in unexpected ways 
(e.g. Modifying the task number may end up with duplicate or unordered task numbers, which are not checked).

Therefore, edit the data file only if you are confident that you can update it correctly.

# FAQ
**Q:** Can I transfer my data to another computer? If so, how?

**A:** You can. You just need to make sure that the `/data` folder is at the same level with the `anon.jar` file.
The `tasklist.txt` file should be inside the `data` folder.
