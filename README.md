#User Guide for chatbot "Apple"

Chatbot Apple is an app to manage tasks, optimised for use through a Command Line Interface (CLI).

##Quick Start
1. Ensure Java 11 or above is installed in your computer
2. Download `ip.jar` from [here](https://github.com/hongyijie06/ip/releases/tag/A-Jar)
3. Open a command terminal, `cd` into the folder with the jar file and use the `java -jar ip.jar` command to run the application.

A CLI similar to the picture below should appear in a few seconds. Note how the app returns the path to which the list of tasks is saved on the local computer.

[picture](/initialSetup.png)

4. Type the command into the command line and press Enter to execute it. e.g. typing list and pressing Enter will list the current tasks.
Some example commands you can try:

- `todo tutorial`: Adds task "tutorial" to the list
- `deadline return book by 4pm`: Adds task "return book" nd deadline "4pm" to the list
- `list`: Lists all tasks
- `bye`: Exits the app

5. Refer to the Features below for more details on each command.

##Features

[!NOTE]
> Words in `UPPER_CASE` are parameters to be supplied by the user. e.g. in `delete INDEX`, `INDEX` is a parameter which can be used as `delete 3`

##Listing tasks: `list`
Lists all tasks in the task list.

Format: `list`

[picture1](/List.png)

##Add tasks
###Add todo : `todo`
Adds task of type todo to the list of tasks

Format: `todo DESCRIPTION`

Examples:
- `todo tutorial` Adds task `tutorial` to the list of tasks
- `todo watch lecture` Adds task `watch lecture` to the list of tasks

ADD PIC HERE

###Add deadline: `deadline`
Adds task of type deadline to the list of tasks

Format: `deadline DESCRIPTION by DEADLINE`

Examples:
- `deadline return book by 4pm` Adds task `return book` with deadline `4pm` to the list of tasks
- `deadline submit report by 7pm` Adds task `submit report` with deadline `7pm` to the list of tasks

ADD PIC HERE

##Add event: `event`
Adds task of type event to the list of tasks

Format: `event DESCRIPTION from START to END`

Examples:
- `event Math exam from 2pm to 4pm` Adds task `Math exam` with timeline `2pm` to `4pm` to the list of tasks
- `event project group meeting from 11am to 1pm` Adds task `project group meeting` with timeline `11am` to `1pm` to the list of tasks

ADD PIC HERE

##Delete tasks: `delete`
Deletes task from the list of tasks

Format: `delete INDEX`

- Deletes task at the specified `INDEX`. 
- The index refers to the index of the task in the list of tasks. 
- The index must be a positive integer.

Examples:
- `delete 3` Deletes third task in the list
- `delete 5` Deletes fifth task in the list

##Mark tasks as done: `mark`
Marks tasks as completed

Format: `mark INDEX`

- Marks task as done at the specified `INDEX`. 
- The index refers to the index of the task in the list of tasks. 
- The index must be a positive integer.

Examples: 
- `mark 1` Marks first task in the list as done
- `mark 6` Marks sixth task in the list as done

##Unmark tasks: `unmark`
Marks tasks as undone

Format: `unmark INDEX`

- Marks task as not done at the specified `INDEX`. 
- The index refers to the index of the task in the list of tasks. 
- The index must be a positive integer.

Examples:
- `unmark 1` Marks first task in the list as notdone
- `unmark 2` Marks second task in the list as not done

##Find Keywords: `find`
Filters list of tasks to those containing the keyword the user wants to find

Format: `find KEYWORD`

Examples:
- `find book` CLI returns a list with all the tasks with the `KEYWORD` `book`.
- `find tutorial` CLI returns a list with all the tasks with the `KEYWORD` `tutorial`.

##Exit app: `bye`
Exits the chatbot

Format: `bye`

##Command Summary

| Command | Format,Examples |
| ------- | --------------- |
| `list` | `list` |
| `todo` | `todo DESCRIPTION` e.g. `todo tutorial` |
| `deadline` | `deadline DESCRIPTION by DEADLINE` e.g. `deadline return book by 4pm` |
| `event` | `event DESCRIPTION from START to END` e.g. event Math exam from 2pm to 4pm` |
| `delete` | `delete INDEX` e.g. `delete 3` |
| `mark` | `mark INDEX` e.g. `mark 2` |
| `unmark` | `unmark INDEX` e.g. `unmark 1` |
| `find` | `find KEYWORD` e.g. `find book` |
| `bye` | `bye` |