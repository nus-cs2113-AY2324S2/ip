# RoleyPoley User Guide

RoleyPoley is a desktop app for managing a user's task, optimized for use via a Command Line Interface. If you think
you can type fast without errors, RoleyPoley can store and manage your tasks faster than you writing it out manually!

## Features 

### Addition of tasks

In the world we live in, there are many different type of tasks. Fret not because RoleyPoley can even manage tasks with
a due date or that has to be completed within a time frame! Tasks that are added are assumed to be not completed.

#### 1. Todo tasks: `todo`

Add a task that is not constraint by time. 

Format: `todo task_description`

Examples:
```
todo Water the plants everyday
todo Go and make friends in University
```

#### 2. Deadline tasks: `deadline`

Add a task that involves a task that has a due date.

Format: `deadline task_description /by due_date`

Examples:
```
deadline Water the plants /by 7am in the morning
deadline Go and make 5 friends in University /by Next Tuesday
```

#### 3. Event tasks: `event`

Add a task that involves a task that has a time frame.

Format: `event task_description /from start_time /to end_time`

Examples:
```
event Water the plants anytime /from 7am /to 12pm daily
event Go and make friends in University /from 9am /to 12pm every Tuesday
```

### List all tasks: `list`

Shows a list of all the tasks in the task list.

Format: `list`

### Delete task: `delete`

Remove task from the task list.

> [ !NOTE ]
> When deleting task, the task list will shrink and task numbers of certain task will change.
> To prevent any task from being deleted, check the task list number by calling `list`

Format: `delete INDEX`

* Deletes task at the specified `INDEX`. The index refers to the index number shown in the task list. The index must be a **positive integer**, e.g. 1,2,3...

Examples:
```    
delete 1 //Removes and deletes task 1 in task list
delete 5 //Removes and deletes task 5 in task list
```

### Mark task: `mark`

Indicate task as completed in the task list.

Format: `mark INDEX`

* Mark task at the specified `INDEX` as **completed**. The index refers to the index number shown in the task list. The index must be a **positive integer**, e.g. 1,2,3...

Examples:
```
mark 1 //Indicates that task 1 in task list has been completed
```

### Unmark task: `unmark`

Indicate task as not completed in the task list.

Format: `unmark INDEX`

* Mark task at the specified `INDEX` as **not completed**. The index refers to the index number shown in the task list. The index must be a **positive integer**, e.g. 1,2,3...

Examples:
```        
unmark 1 //Indicates that task 1 in task list has not been completed
```

### Filtering task list by keyword: `find`

Finds the task that contains any of the given keywords.

Format: `find keyword`

* The search is case-insensitive e.g. `university` will match `University`.
* Partial words will be matched e.g. `uni` will match `University`.
* Time frame can also be searched e.g. `7pm` will match all tasks that has a time constraint of 7pm.


Examples:
```
find university
```

### Exit the application: `bye`

Exits the program.

Format: `bye`


## Command Summary

| Action             | Format, Examples                                                                                                           |
|--------------------|----------------------------------------------------------------------------------------------------------------------------|
| Add todo tasks     | `todo task_description`<br/> `e.g. todo Water the plants everyday`                                                         |
| Add deadline tasks | `deadline task_description /by due_date`<br/> `e.g. deadline Water the plants /by 7am in the morning`                      |
| Add event tasks    | `event task_description /from start_time /to end_time`<br/> `e.g. event Water the plants anytime /from 7am /to 12pm daily` |
| Delete             | `delete INDEX`<br/> `e.g. delete 2`                                                                                        |
| Mark               | `mark INDEX`<br/> `e.g mark 1`                                                                                             |
| Unmark             | `unmark INDEX`<br/> `e.g unmark 1`                                                                                         |
| List               | `list`                                                                                                                     |
| Find               | `find KEYWORD`<br/> `e.g. find University`                                                                                 |
| Bye                | `bye`                                                                                                                      |
