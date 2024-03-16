# RecrBad project template

_RecrBad_ is a command line programme to help you track your TODOs/ tasks!

<hr>

- [Setting Up](#Setting-Up-in-Intellij)
- [Possible User Commands](#Possible-User-Commands)
  - [Add Todo task: `todo`](#Add-Todo-task-todo)
  - [Add Deadline task: `deadline`](#Add-Deadline-task-deadline)
  - [Add Event task: `event`](#Add-Event-task-event)
  - [List all tasks: `list`](#List-all-tasks-list)
  - [Mark a task as done: `mark`](#Mark-a-task-as-done-mark)
  - [Unmark a task as undone: `unmark`](#Unmark-a-task-as-undone-unmark)
  - [Delete a task: `delete`](#delete-a-task-delete)
  - [Find a task: `find`](#Find-a-task-find)
  - [Exit the Program: `bye`](#Exit-the-program-bye)
- [Saved tasks](#Saved-tasks)
- [Frequently-Asked-Questions (FAQ)](#FAQ)

<hr>

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project`
   to close the existing project first)
2. Download the lastest jar file [here](https://github.com/NGXZS/ip/releases)
3. Save _ip.jar_ in an appropriate folder eg Downloads
4. Open the command terminal (search "cmd") and navigate to the folder where the file is saved (`cd filepath`)
5. Enter `java -jar ip.jar` to run _ip.jar_ in the command terminal
6. After successfully starting _RecrBad_, a similar output will be as shown:
```
   Hello! I'm
 ____   ___    ___   ____    ___
| __/  / _ \  | _ \  | __|  / _ \
| |    ||_||  | / /  | |_   | | |
| |_   | _ |  | _/   |_  /  | | |
|___\  // \\  |_|    /__/ . \___/

****************************************
What can I do for you?
```
7. Refer to [Possible User Commands](#Possible-User-Commands) below to start adding tasks!
8. Have a fun and productive time!

<hr>

## Possible User Commands
<hr>

### Add Todo task `todo`
Adds a TODO task. Enter `todo` and the task description

Format: `todo <taskDescription>`

Examples:
   * `todo assignment1`
   * `todo lab revision`

### Add Deadline task `deadline`
Add a deadline task. Enter `deadline`, the task description, and the deadline ("yyyy/mm/dd")

Format: `deadline <taskDescription> /<deadline>`

Examples:
* `deadline assignment1 /2024/08/20` (deadline is by 20 August 2024)
* `deadline lab revision /morning`(deadline is by morning)

### Add Event task `event`
Add event task. Enter `event`, the task description, and the event start and end time/ dates

Format: `event <taskDescription> /<start> /<end>`

Examples:
* `event assignment1 /0800 /1000` (from: 0800 to 1000)
* `event lab revision /morning /afternoon`(from: morning to afternoon)

### List all tasks `list`
List all tasks. Enter `list`.

Format: `list`

Usage:
```
****************************************
list
1. [T][0] assignment1
2. [D][0] lab revision (by: Aug 20 2024)
3. [E][0] lab revision2 (from: 0800 to 1000)
****************************************

```
### Mark a task as done `mark`
Mark task as done. Enter `mark` and the task number

Format:  `mark <taskNum>`

Usage:
```
****************************************
mark 2
Has marked task2:
2. [D][1] lab revision (by: Aug 20 2024)
****************************************

```
### Unmark a task as undone `unmark`
Unmark task as undone. Enter `unmark` and the task number

Format:  `unmark <taskNum>`

Usage:
```
****************************************
unmark 2
Has unmarked task2:
2. [D][0] lab revision (by: Aug 20 2024)
****************************************
```

### Delete a task `delete`
Delete a task. Enter `delete` and the task number

Format: `delete <taskNum>`

Usage: 
```
****************************************
list
1. [T][0] assignment1
2. [D][0] lab revision (by: Aug 20 2024)
3. [E][0] lab revision2 (from: 0800 to 1000)
****************************************
delete 2
Good riddance, task deleted!
2. [D][0] lab revision (by: Aug 20 2024)

Congrats, now have 2 tasks
****************************************
list
1. [T][0] assignment1
2. [E][0] lab revision2 (from: 0800 to 1000)
****************************************
```

### Find a task `find`
Searches for a keyword in the list of tasks. Enter `find` and the keyword

Format: `find <keyword>`

Usage: 
```
****************************************
list
1. [T][0] assignment1
2. [E][0] lab revision2 (from: 0800 to 1000)
3. [T][0] assignment2
4. [T][0] tutorial1
5. [D][0] assignment3 (by: tonight 2359)
****************************************
find assignment
1. [T][0] assignment1
3. [T][0] assignment2
5. [D][0] assignment3 (by: tonight 2359)
****************************************
```

### Exit the program `bye`
Exit the Program. Enter `bye`

Format: `bye`

Usage:
```
****************************************
bye
Adios My Friend. Sleep early, study smarter
<a random quote>
****************************************

```
<hr>

## Saved Tasks
All tasks will be saved in a file called _saveFile.txt_ after each command and after ending the program.
Should the file not exist, a new file called _saveFile.txt_ will be created in the current directory to store the tasks.

> [!NOTE]
> After ending the program, the tasks will be saved in _saveFile.txt_.
> The next time you run the program again, you will be able to see your previously added tasks!

<hr>

## FAQ
Q: What are your references?
A: List of References
   + CS2113 iP [GitHub](https://github.com/nus-cs2113-AY2324S2/ip)
   + [GitHub formatting](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
   + Inspired by [T0nyLin](https://github.com/T0nyLin/ip/blob/master/docs/README.md?plain=1)