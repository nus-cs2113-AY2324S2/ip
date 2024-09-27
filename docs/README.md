# **Bart: One of the Chatbots _<span style="color:orange;">ever</span>_**
> Greatest chatbot this month! - B.Bernard

# <span style="color:orange;">User Guide</span>
Bart is a desktop app for listing out your everyday tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

- [Quick Start](https://jackieneoceg.github.io/ip/#quick-start)
- [Features](https://jackieneoceg.github.io/ip/#features)
  - [Viewing help: `help`](https://jackieneoceg.github.io/ip/#viewing-help-help)
  - [Adding Tasks and their types: `todo` `deadline` `event`](https://jackieneoceg.github.io/ip/#adding-tasks-and-their-types-todo-deadline-event)
  - [Listing all tasks: `list`](https://jackieneoceg.github.io/ip/#listing-all-tasks-list)
  - [Deleting a task: `delete`](https://jackieneoceg.github.io/ip/#deleting-a-task-delete)
  - [Marking and Unmarking: `mark` `unmark`](https://jackieneoceg.github.io/ip/#marking-and-unmarking-mark-unmark)
  - [Exit and save Bart: `bye`](https://jackieneoceg.github.io/ip/#exit-and-save-bart-bye)

## <span style="color:Orange;">Quick Start</span>

1. Ensure you have `Java 11` or above installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/JackieNeoCEG/ip/).
3. Copy the file to the folder you want to use as the home folder for your Bart.
4. Open a command terminal, cd into the folder you put the jar file in, and input `java -jar ip.jar` to run the application.
A GUI similar to the below should appear in a few seconds. Note how the app shows a `help` command immediately.

```


     ░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░▒▓███████▓▒░  
     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     
     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     
     ░▒▓███████▓▒░░▒▓████████▓▒░▒▓███████▓▒░  ░▒▓█▓▒░     
     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     
     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     
     ░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     

Hello! I'm Bartholomew, but you can call me Bart for short :)
What can I do for you?
Type 'help' for a list of available commands!
________________________________________________________________________________

```
Refer to the [Features](https://jackieneoceg.github.io/ip/#features) list below for more information of the commands, type your input down and press `Enter`.

## <span style="color:Orange;">Features</span> 

### <span style="color:coral;">Viewing **help**: `help`</span> 

Shows a list of available commands.

Format: `help`

<br>

### <span style="color:coral;">**Adding** Tasks and their types: `todo` `deadline` `event`</span> 

Adds task into a list.

Todo Format: `todo [TASK]` <br>
Deadline Format: `deadline [TASK] /by [TIME]`<br>
Event Format: `event [TASK] /from [START_TIME] /to [END_TIME]`

Examples:
- `todo CS2113 Lectures`
- `deadline Rob the bank /by 12 noon`
- `event Boston Tea Party /from 7pm 16/12/1776 /to 10pm 16/12/1776`

<br>

### <span style="color:coral;">**Listing** all tasks: `list`</span> 

Shows a list of tasks.

Format: `list`

<br>

### <span style="color:coral;">**Deleting** a task: `delete`</span> 

Deletes a task at given index.

Format: `delete [INDEX]`

Example: 
- `delete 3` Deletes the 3rd task in the list.

<br>

### <span style="color:coral;">**Marking** and **Unmarking**: `mark` `unmark`</span> 

Marks or unmarks a task in the list at given index.

Mark Format: `mark [INDEX]`

Unmark Format: `unmark [INDEX]`

Examples:
- `mark 7` Marks the 7th task in the list.
-  `unmark 2` Removes the mark in the 2nd task in the list.

<br>

### <span style="color:coral;">**Exit** and save Bart: `bye`</span> 

Exits the program and saves the list in a file located in `./data/Bart.txt`. 

This save file is _automatically loaded_ when Bart is opened again
