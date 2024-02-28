# Dor User Guide

Dor is a CLI chatbot that helps users track their tasks.

1. Quick Start
2. Features
   - Add
   - List
   - Delete
   - Mark
   - Find
   - Save
   - Load
   - Quit


## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `Dor.jar` file from JianJiaT/ip/Releases on GitHub.
3. Open a command terminal and `cd` to the folder you put the JAR file in
4. Enter `java -jar Dor.jar` into the terminal to run the chatbot
5. Refer to the Features section below for details of each command


## Features 

- Input is case-sensitive
- Words in UPPER_CASE are parameters
- Extraneous parameters are ignored
- Parameter order must follow that specified in the SYNOPSIS of the command
- Parameters in curly braces must match one of the options specified in the DESCRIPTION of the command
- Items in square brackets are to be included or omitted as specified in the DESCRIPTION of the command


### Adding tasks

SYNOPSIS

   `{TASK_TYPE} [TASK_NAME] [/by DEADLINE_DUE_DATE_TIME] [/from EVENT_START_DATE_TIME] [/to EVENT_END_DATE_TIME]`

DESCRIPTION
   - Adds a task to be tracked by the chatbot
   - The added task is marked as not done
   - {TASK_TYPE} must match one of "todo", "deadline" or "event"
   - [/by DEADLINE_DUE_DATE_TIME] is to be included if {TASK_TYPE} is "deadline" and refers to the due date/time of the task, omitted otherwise
   - [/from EVENT_START_DATE_TIME] is to be included if {TASK_TYPE} is "event" and refers to the start date/time of the event, omitted otherwise
   - [/to EVENT_END_DATE_TIME] is to be included if {TASK_TYPE} is "event" and refers to the end date/time of the event, omitted otherwise

EXAMPLES
   - `todo go shopping`
   - `deadline assignment 1 /by 15 may`
   - `event friend's birthday party /from 14 april 2pm /to 6pm`


### Listing tasks: list

SYNOPSIS

   `list`

DESCRIPTION
   - Lists all tasks tracked by the chatbot


### Deleting tasks: delete

SYNOPSIS

   `delete INDEX`

DESCRIPTION
   - Deletes the task at INDEX
   - INDEX refers to the index number shown in the task list displayed by `list`


### Marking tasks

SYNOPSIS

   `{MARK_OR_UNMARK} INDEX`

DESCRIPTION
   - Marks the task at INDEX as done or not done, represented by "X" and " " respectively in the task list displayed by `list`
   - {MARK_OR_UNMARK} must match one of "mark" or "unmark"
   - INDEX refers to the index number shown in the task list displayed by `list`


### Finding tasks: find

SYNOPSIS

   `find KEYWORD`

DESCRIPTION
   - Lists all tasks containing KEYWORD in their name (case sensitive)

EXAMPLES
   - `find cook` lists "todo cook meat" and "event cooking competition /from 6pm /to 8pm"
   - `find dance` lists "todo dance" but not "todo DANCE"


### Saving data

Task data is automatically saved by the chatbot to the data file `./data/dor.txt` following any command that changes a task


### Loading data

Task data is automatically loaded by the chatbot from the data file `./data/dor.txt` on startup


### Quitting the chatbot: bye

SYNOPSIS

   `bye`

DESCRIPTION
   - Quits the chatbot
