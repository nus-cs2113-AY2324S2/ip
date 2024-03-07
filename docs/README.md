# User Guide for Helpy
Helpy is a chatbot that helps users to manage their tasks via a Command Line Interface (CLI).

## Quick Start
1. Ensure you have Java `11` installed on your computer.
2. Download the latest `helpy.jar` from [here](https://github.com/webtjs/ip/releases)
3. Copy the file to the folder you want to use as the _home folder_ for your chatbot
4. Open a command terminal, `cd` into the folder in which you placed the `helpy.jar` then use the command `java -jar helpy.jar` to run the chatbot.
5. You should see a greeting message like the following one:
   ```
   ___________________________________________________________________________
   Greetings, I am
   ░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█
   ░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀
   ░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░
   
   How can I help you?
   ___________________________________________________________________________
   
   ```
6. Type the command in the CLI and press Enter to execute it e.g. typing `help` then pressing
Enter will display the help menu. <br/>Here are some example commands you can try:
- `list`: Displays all the tasks in the task list with a task number
- `event project meeting /from 20/2/2024 1200 /to 20/2/2024 1300`: Adds an event with the description `project meeting`
with starting date and time `20 Feb 2024 12:00 PM` to `20 Feb 2024 01:00 PM`
- `delete 3`: Deletes the task with the task number `3` from the task list
- `bye`: Exits from the chatbot

7. Refer to the Features below for details of each command

## Features 
### Notes about command format
- Words in angled brackets `<>` are parameters to be provided by the user.  
e.g. in `todo <description>`, `<description>` is a parameter which can be used  
as `todo watch a new movie`.
- Parameters provided for commands that don't require one (such as `list`, `help`, and `bye`) 
will be ignored.  
e.g. `list abc` will be intepreted as `list`.  

### Help menu: `help`

Shows a help menu of all the chatbot's available commands.

Format: `help`

Expected outcome:
```
___________________________________________________________________________
Need some help? Here's the commands that are available (^-^)

- help: Shows this help menu
- bye: Exits from the chatbot
- todo <description>: Adds a new todo task with the provided description
- deadline <description> /by <d/m/yyyy HHmm>: Adds a new task with the
  specified description and deadline
- event <description> /from <d/m/yyyy HHmm> /to <d/m/yyyy HHmm>: Adds a
  new event with the given description and time period
- list: Displays all the tasks in the task list with a task number
- mark <task number>: Marks a task as completed
- unmark <task number>: Marks a task as not completed
- delete <task number>: Removes the task from the task list
- find <keyword>: Search for tasks containing the provided keyword
___________________________________________________________________________
```

### Listing all tasks: `list`

Displays all the tasks in the task list with a task number.
The first pair of square brackets`[ ]` indicates the type of task,
and the second pair indicates if the task is done(`[X]`) or not done(`[ ]`).

Format: `list`

Expected outcome:
```
___________________________________________________________________________
These are the tasks in your list:
1.[D][X] read book (by: 5 Feb 2024, 03:00 PM)
2.[T][ ] CS2113 Quiz
3.[T][ ] book review
4.[E][ ] Project meeting (from: 4 Oct 2023, 09:00 AM to: 4 Oct 2023, 12:00 PM)
___________________________________________________________________________
```

### Mark a task as done: `mark`

Marks a task as completed 

Format: `mark <task number>`  

Example of usage:  
`mark 2` Marks the task that has the task number 2 in the task list

Expected outcome:
```
___________________________________________________________________________
Good job! \(^o^)/ I've marked this task as done:
	[T][X] CS2113 Quiz
___________________________________________________________________________
```

### Mark a task as not done: `unmark`

Marks a task as not completed

Format: `unmark <task number>`

Example of usage:  
`unmark 1` Marks the task that has the task number 1 in the task list

Expected outcome:
```
___________________________________________________________________________
Ok, this task has been marked as not done yet:
	[D][ ] read book (by: 5 Feb 2024, 03:00 PM)
___________________________________________________________________________
```

### Delete a task: `delete`

Removes the task from the task list

Format: `delete <task number>`

Example of usage:  
`delete 2` Removes the task that has the task number 2 in the task list

Expected outcome:
```
___________________________________________________________________________
Got it, I've removed this task from the list:
	[T][ ] CS2113 Quiz
There are 3 tasks left in the list.
___________________________________________________________________________
```

### Search for tasks: `find`

Search for tasks containing the provided keyword

Format: `find <keyword>`

Example of usage:  
`find book`

Expected outcome:
```
___________________________________________________________________________
I found these tasks that match the keyword "book"
1.[D][ ] read book (by: 5 Feb 2024, 03:00 PM)
2.[T][ ] book review
___________________________________________________________________________
```

### Add a new todo task: `todo`

Adds a new todo task with the provided description

Format: `todo <description>`

Example of usage:  
`todo CS2113 Quiz`

Expected outcome:
```
___________________________________________________________________________
Ok I just added:
	[T][ ] CS2113 Quiz
There are 4 tasks in the list.
___________________________________________________________________________
```

### Add a new task with a deadline: `deadline`

Adds a new task with the specified description and deadline

Format: `deadline <description> /by <d/m/yyyy HHmm>`

Example of usage:  
`deadline read textbook /by 5/3/2024 1600`

Expected outcome:
```
___________________________________________________________________________
Ok I just added:
	[D][ ] read book (by: 5 Mar 2024, 04:00 PM)
There are 5 tasks in the list.
___________________________________________________________________________
```

### Add a new event: `event`

Adds a new event with the given description and time period

Format: `event <description> /from <d/m/yyyy HHmm> /to <d/m/yyyy HHmm>`

Example of usage:  
`event Overseas trip /from 4/12/2023 0900 /to 11/12/2023 1900`

Expected outcome:
```
___________________________________________________________________________
Ok I just added:
	[E][ ] Overseas trip (from: 4 Dec 2023, 09:00 AM to: 11 Dec 2023, 07:00 PM)
There are 6 tasks in the list.
___________________________________________________________________________

```

### Exiting the program: `bye`

Exits from the chatbot

Format: `bye`

Expected outcome:
```
___________________________________________________________________________
Goodbye, see you next time! (^_^)
___________________________________________________________________________
```


## Appendix

### Date and time format

For tasks that accept a date and time (e.g. `deadline` and `event`), the
format of the date and time to be provided must be in the form of
`d/m/yyyy HHmm` where `d` can be a one or two digit date, `m` can
be a one or two digit month, `yyyy` is the year, and `HHmm` is the
time in 24-hour clock.

### Search for tasks

The list of tasks shown after using the `find <keyword>` command is for viewing only.
When performing operations on the task list (e.g. `mark <task number>`, `unmark <task number>`,
`delete <task number>`), you will have to use the task number in the original list (task list
shown when running the `list` command).
