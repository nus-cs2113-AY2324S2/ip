# Chat Bot Adam - User Guide
This is a user manual for Chat Bot Adam, a task-management desktop application designed to be used with a Command Line Interface (CLI).<br>
If you can type fast, Adam can get your tasks managed faster than some GUI apps.<br>
[View the User Guide in Simple Theme](OriginalTheme%2FSimpleView.md)
## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a task](#adding-a-task)
  - [Listing all tasks](#listing-all-tasks)
  - [Marking a task as done](#marking-a-task-as-done)
  - [Deleting a task](#deleting-a-task)
  - [Finding a task by keyword](#finding-a-task-by-keyword)
  - [Exiting the program](#exiting-the-program)
  - [Viewing help](#viewing-help)
- [Appendix](#Appendix)

## Introduction
Adam the Chat Bot is a versatile task management application designed to simplify your daily workflow.<br>
Whether you're organizing personal tasks, planning projects, or managing deadlines, Adam is here to help.<br>
With its intuitive Command Line Interface (CLI), Adam offers a seamless and efficient task management experience.<br>
This user guide provides a comprehensive overview of Adam's features and commands, as well as a quick start guide to get you up and running in no time.

## Quick Start
1. Ensure you have `Java 11` or above installed in your Computer.
2. Download the latest `adam.jar` from [here](https://github.com/adamzzq/ip/releases/download/A-UserGuide/adam.jar)
3. Copy the file to the folder you want to use as the home folder for Adam the Chat Bot.
4. Start a terminal and navigate to the folder where `adam.jar` is located.
5. Run the command `java -jar adam.jar` to start the app. The app should start with the following output displayed:
![img.png](img.png)
6. Type the command in the command box and press Enter to execute it. Refer to the [Features](#features) section for available commands.
7. Note that the data is saved in the hard disk **automatically** after any command that changes the data. There is no need to save manually.
8. Please take note that **deleting** the `tasks.dat` file will result in the **loss of all data**.
9. Please do not edit the `tasks.dat` file manually as it may result in the **loss of all data**.
10. Please ensure that the data file named `tasks.dat` is in the **same directory** as the `adam.jar` file to load the saved data.<br>
If there is no `tasks.dat` file in the same directory as the `adam.jar` file, an empty `tasks.dat` file will be created to store the data.


> [!NOTE]
> * All the spaces in the commands are for clarity and are **optional** to include in the actual command.<br>
> * Commands are **case-insensitive**.<br>
> * The `<angle-brackets>` are used to denote the parameters that should be replaced with the **actual user input**.


## Features
### Adding a task
Adds a task to the list of tasks. The task can be a todo, deadline, or event task.<br>
The task descriptions are **case-sensitive** and are displayed as entered.

#### Usage
> `todo <description>`<br>
>  Adds a todo task with the given description.

> `deadline <description> /by <date and/or time>`<br>
>  Adds a deadline task with the given description and date.

> `event <description> /from <start date and/or time> /to <end date and/or time>`<br>
>  Adds an event task with the given description and start and end dates.

* The date and time format is specified in the [Appendix](#Date-and-Time-Format).

#### Example of usage
`todo read book`<br>
Adds a todo task with the description "read book".

`deadline return book /by 2024-03-30 1600`<br>
Adds a deadline task with the description "return book" and the date "2024-03-30".

`event project meeting /from 2024-03-07 100 /to 2024-03-07 230`<br>
Adds an event task with the description "project meeting"<br>
with the start date and time `Mar 7, 2024, 1:00 PM` and the end date `Mar 7, 2024, 2:30 PM`.

> [!NOTE]
> - The time field is optional and defaults to 12:00 AM if not specified.<br>
> - Unspecified dates are automatically set to the current date from the system clock.
> - Using unsupported date and time formats will create a tasks with the date and time as the literal input.
>   - For example:
      `deadline Hand in assignments /by tomorrow afternoon`<br>
      Adds a deadline task with the description "Hand in assignments" and the date and time as a literal "tomorrow afternoon".

#### Expected outcome
```
Awesome sauce! Task successfully added:
  [T][ ] read book
You now have a whopping 1 tasks in the list. Keep 'em coming!
```
```
Awesome sauce! Task successfully added:
  [D][ ] return book (by: Mar 30, 2024, 4:00 PM)
You now have a whopping 2 tasks in the list. Keep 'em coming!
```
```
Awesome sauce! Task successfully added:
  [E][ ] project meeting (from: Mar 7, 2024, 1:00 PM to: Mar 7, 2024, 2:30 PM)
You now have a whopping 3 tasks in the list. Keep 'em coming!
```


### Listing all tasks
Lists all the tasks in the list of tasks.

#### Usage
> `list` or `ls` - Lists all the tasks in the list of tasks.

#### Example of usage
`list` - Display all the tasks in the list of tasks.

#### Expected outcome
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][X] return book (by: Mar 30, 2024, 4:00 PM)
3. [E][X] project meeting (from: Mar 7, 2024, 2:30 PM to: Mar 7, 2024, 2:30 PM)
```


### Marking a task as done
Marks a task as done in the list of tasks.

#### Usage
> `mark <index>` - Marks the task at the given index as done.<br>
> `unmark <index>` - Marks the task at the given index as not done.

#### Example of usage
`mark 1` - Marks the 1st task in the list as done.

`unmark 2` - Marks the 2nd task in the list as not done.

#### Expected outcome
```
Ta-da! Task conquered! I've marked this task as done:
   [T][X] read book
```
```
Whoopsie-daisy! This task is back in action:
   [D][ ] return book (by: Mar 30, 2024, 4:00 PM)
```


### Deleting a task
Deletes a task from the list of tasks.

#### Usage
> `delete <index>` - Deletes the task at the given index from the list of tasks.

#### Example of usage
`delete 3` - Deletes the 3rd task from the list of tasks.

#### Expected outcome
```
Task gone with the digital wind!
  [E][ ] project meeting (from: Mar 7, 2024, 1:00 PM to: Mar 7, 2024, 2:30 PM)
Now down to 1 tasks in the list.
```


### Finding a task by keyword
Finds a task from the list of tasks that contains the given keyword.

#### Usage
> `find <keyword>` - Finds a task from the list of tasks that contains the given keyword.

#### Example of usage
`find book` - Finds a task from the list of tasks that contains the keyword "book".

#### Expected outcome
```
Behold! Your tasks surface:
1.[T][X] read book
2.[D][ ] return book (by: Mar 30, 2024, 4:00 PM)
```


### Exiting the program
Exits the program.

#### Usage
> `bye` or `ex` or `q` - Exits the program.

#### Example of usage
`bye` - Exits the program.

#### Expected outcome
```
Farewell, adventurer! Until our paths cross again!
```


### Viewing help
Displays the help message.

#### Usage
> `help` or `h` - Displays the help message.

#### Example of usage
`help` - Displays the help message.

#### Expected outcome
```
Need a hand? Here's your guide to Adam's commands!

Available commands:
- bye (or ex, q): Exit the chatbot.
- todo [description]: Add a new todo task.
- deadline [description] /by [deadline]: Add a new task with a deadline.
- event [description] /from [start time] /to [end time]: Add a new event task.
- list (or ls): View all tasks in the task list.
- mark [task number]: Mark a task as completed.
- unmark [task number]: Mark a completed task as incomplete.
- delete [task number]: Delete a task from the list. (Watch out, it's permanent!)
- find [keyword]: Search for tasks containing the specified keyword.
- help (or h): Display this help menu.

To use a command, simply type it followed by any necessary parameters
(e.g. "deadline Hand in assignments /by 4th June").
Enjoy chatting with Adam!
```

## Appendix
### Date and Time Format
> The date and time format is **case-insensitive**.
> `deadline` and `event` commands accept dates and times in the following format:
- Date:
- `YYYYMMDD` (4-digit year, 2-digit month, 2-digit day,<br>seperated by any symbols of: `-` `/` `.` or a space character)
- `DDMMYYYY` (2-digit day, 2-digit month, 4-digit year,<br>seperated by any symbols of: `-` `/` `.` or a space character)
- `ddMMMyyyy` (2-digit day, 3-letter month, 4-digit year,<br>seperated by any symbols of:  `-` `/` or a space character)
- `MMMddyyyy` (3-letter month, 2-digit day, 4-digit year,<br>seperated by a space character)
- `MMM dd, yyyy` (3-letter month, 2-digit day, 4-digit year,<br>seperated by a space character with a comma `,` after the day)
- Time:
- 24-hour or 12-hour format with an optional leading zero,<br>and optional colon `:` as the separator between hours and minutes.
- For 12-hour format, the time should be followed by `am` or `pm`.

#### Format Examples
Date:
- `2024-03-30`
- `30/03/2024`
- `30.03.2024`
- `30 03 2024`
- `30 Mar 2024`
- `MAR 30, 2024`

Time:
- `01:00pm`
- `13:00`
- `1:00PM`
- `01:00`
- `100`
- `1300`

