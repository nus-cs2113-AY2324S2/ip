# Allez User Guide

Allez is a desktop app for managing your tasks via a Command Line Interface (CLI).

## Features

> [!NOTE]
:information_source: Notes about the command format:
> - Words in `UPPER_CASE` are parameters to be supplied by users.
> e.g in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo math homework`.
> 
> - Words that begin with a `/` needs to be supplied by user.
> e.g in `deadline DESCRIPTION /by BY`, `/by` is required for the command.
>
> - Extraneous parameters for commands that do not take in parameters (such as help, list, exit and clear) will be ignored. 
e.g. if the command specifies help 123, it will be interpreted as help.
>
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

### Adding a task
There are three types of tasks you can add.

#### Adding a ToDo task: `todo`
Adds a ToDo task to the list of tasks.
Format: `todo DESCRIPTION`
Examples:
- `todo CS2113 README`
- `todo Midterms revision`

#### Adding a Deadline task: `deadline`
Adds a Deadline task to the list of tasks. Deadline has a specified end time.
Format: `deadline DESCRIPTION /by BY`
Examples:
- `deadline CS2113 IP /by tomorrow night`
- `deadline LOA Application /by next semester`

#### Adding a Event task: `event`
Adds an Event task to the list of tasks. Events have a specified start and end time.
Format: `event DESCRIPTION /from FROM /to TO`
Examples:
- `event CS2027 Midterms /from 9th March 10.30am /to 9th March 12noon`
- `event NUS Open House /from Saturday morning /to Saturday night`

### Listing all tasks: `list`
Shows a list of all tasks recorded.
Format: `list`
    
### Marking a task: `mark`
Marks specified Task as done.
Format: `mark INDEX`
- Marks the task at the corresponding `INDEX`.
- The index refers to the index number shown next to displayed task list.
Examples:
 - `mark 3`

### Deleting a task: `delete`
Deletes a task from the list.
Format: `delete INDEX`
- Marks the task at the corresponding `INDEX`.
- The index refers to the index number shown next to displayed task list.
Examples:
- `delete 3`

>[!IMPORTANT]
> Deleting a task will shift the index of the tasks below it.



### Finding a task: `find`
Find all tasks that contains the keyword in its description.
Format: `find KEYWORD`
- The search is case-sensitive. e.g `CS2113` will not match `cs2113`.
- Only the description of tasks is searched
Examples:
- `find CS2113`
- `find NUS Open House`

### Exiting the program: `bye`
Exits the program.
Format: `bye`

### Saving the data
List of tasks are saved in the hard disk automatically upon entering `bye`.

