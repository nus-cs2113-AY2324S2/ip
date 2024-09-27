# Chelle User Guide

_Chelle is a simple program that allows you to manage your tasks._

## Features

### Add a task: `list`

Shows a list of all tasks in the task list <br>
- Format: ```list```
<br>

### Add a task: `todo`

Adds a task to the task list. <br>
- Format: ```todo [TASK]```
```
todo Math Homework
todo Do laundry
```
- Sample output:
> 1.[T][ ] Math Homework <br>
> 2.[T][ ] Do laundry
<br>

### Add a task with a deadline: `deadline`

Adds a task with a deadline to the task list. <br>
- Format: ```deadline [TASK] /by [DD/MM/YYYY hhmm]```
```
deadline Reply emails /by tomorrow
deadline English essay /by 12/3/2024 2359
deadline Christmas shopping /by 8/12/2024
```
- Sample output:
> 1.[D][ ] Reply emails (by: tomorrow) <br>
> 2.[D][ ] English essay (by: 12 Mar 2024 23:59Hrs) <br>
> 3.[D][ ] Christmas shopping (by: 8 Dec 2024) 
<br>

### Add an event: `event`

Adds an event to the task list. <br>
- Format: ```event [TASK] /from [DATE] /to [DATE]```
```
event Concert /from 6pm /to 9pm
event Hackathon /from Mon /to Tue
```
- Sample output:
> 1.[E][ ] Concert (from: 6pm to: 9pm) <br>
> 2.[E][ ] Hackathon (from: Mon to: Tue)
<br>

### Mark a task as complete: `mark`

Marks the indicated task. <br>
- Format: ```mark [TASK_NUMBER]```
```
mark 1
```
- Sample output:
> 1.[T][X] Math Homework 
<br>

### Mark a task as incomplete: `unmark`

Unmarks the indicated task. <br>
- Format: ```unmark [TASK_NUMBER]```
```
unmark 2
```
- Sample output:
> 2.[D][ ] English essay (by: 12 Mar 2024 23:59Hrs)
<br>

### Delete a task: `delete`

Deletes the indicated task. <br>
- Format: ```delete [TASK_NUMBER]```
```
delete 1
```
<br>

### Search for a task: `find`

Finds all tasks that contain the keyword. <br>
- Format: ```find [KEYWORD]```
```
find homework
```
- Sample output:
> 2.[D][ ] English Homework (by: 12 Mar 2024 23:59Hrs) <br>
> 3.[T][ ] Math Homework
<br>

### Save and close program: `bye`

Saves current task list and exits program. <br>
- Format: ```bye```
<br>

> [!IMPORTANT]
> Tasks are not saved automatically and will not be saved unless this command is run.


## Command Summary

| **Command** | **Format, Examples**                               |
|-------------|----------------------------------------------------|
| list        | `list`                                             |
| todo        | `todo [TASK]`<br> _e.g.,_ `todo Math Homework`     |
| deadline    | `deadline [TASK] /by [DD/MM/YYYY hhmm]`<br> _e.g.,_ `deadline English essay /by 12/3/2024 2359` |
| event       | `event [TASK] /from [DATE] /to [DATE]`<br> _e.g.,_ `event Concert /from 6pm /to 9pm` |
| mark        | `mark [TASK_NUMBER]`<br> _e.g.,_ `mark 1`          |
| unmark      | `unmark [TASK_NUMBER]`<br> _e.g.,_ `unmark 2`      |
| delete      | `delete [TASK_NUMBER]`<br> _e.g.,_ `delete 1`      |
| find        | `find [KEYWORD]`<br> _e.g.,_ `find homework`       |
| bye         | `bye`                                              |
