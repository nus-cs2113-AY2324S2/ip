# Joey

## Features 

### Add task via Todo

Users can add a task via the following command

`todo (task)`

example: `todo CS2113 iP`

```
____________________________________________________________
 Got it. I've added this task:
   [T] [ ] CS2113 iP
 Now you have 1 tasks in the list.
____________________________________________________________
```

### Add task via Deadline

Users can add a task with a **deadline** via the following command

The deadline can include the date and time as well in the following formats:

- d-MM-yyyy HHmm, dd-MM-yyyy HHmm, yyyy-MM-dd HHmm, d/MM/yyyy HHmm, dd/MM/yyyy HHmm or yyyy/MM/dd HHmm

The deadline would then be displayed in the following format:

- MMM dd yyyy h.mm 

`deadline (task) /by (deadline)`

example: `deadline Finish CS2113 Exercise /by 07/03/2024 2359`

```
____________________________________________________________
 Got it. I've added this task:
   [D] [ ] Finish CS2113 Exercise (by: Mar 07 2024, 11:59 pm)
 Now you have 3 tasks in the list.
____________________________________________________________
```

example: `deadline Finish CS2113 Assignment 2 /by tomorrow noon`

```
____________________________________________________________
 Got it. I've added this task:
   [D] [ ] Finish CS2113 Assignment 2 (by: tomorrow noon)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### Add task via Event

Users can add a task with event details via the following command

`event (event) /from (start date and time) /to (end date and time)`

example: `event CS2113 Finals /from 8/3/2024 4pm /to 8/3/2024 6pm`

```
____________________________________________________________
 Got it. I've added this task:
   [E] [ ] CS2113 Finals (from: 8/3/2024 4pm to: 8/3/2024 6pm)
 Now you have 4 tasks in the list.
____________________________________________________________
```

### show List

Users see the list of tasks via the following command

`list`

```
____________________________________________________________
Here are the tasks in your list:
 1. [T] [ ] CS2113 iP
 2. [D] [ ] Finish CS2113 Exercise (by: Mar 07 2024, 11:59 pm)
 3. [D] [ ] Finish CS2113 Assignment 2 (by: tomorrow noon)
 4. [E] [ ] CS2113 Finals (from: 8/3/2024 4pm to: 8/3/2024 6pm)
____________________________________________________________
```

### Mark task as Done

Users can mark a specific task as done upon completion via the following command:

`mark (task number)`

task number can be retrieved after viewing the list via the show List feature

example: `mark 3`

```
____________________________________________________________
Here are the tasks in your list:
 1. [T] [ ] CS2113 iP
 2. [D] [ ] Finish CS2113 Exercise (by: Mar 07 2024, 11:59 pm)
 3. [D] [X] Finish CS2113 Assignment 2 (by: tomorrow noon)
 4. [E] [ ] CS2113 Finals (from: 8/3/2024 4pm to: 8/3/2024 6pm)
____________________________________________________________
```

### Unmark task as Done

Users can unmark a specific task as not done via the following command:

`unmark (task number)`

task number can be retrieved after viewing the list via the show List feature

example: `unmark 3`

```
____________________________________________________________
Here are the tasks in your list:
 1. [T] [ ] CS2113 iP
 2. [D] [ ] Finish CS2113 Exercise (by: Mar 07 2024, 11:59 pm)
 3. [D] [ ] Finish CS2113 Assignment 2 (by: tomorrow noon)
 4. [E] [ ] CS2113 Finals (from: 8/3/2024 4pm to: 8/3/2024 6pm)
____________________________________________________________
```

### Delete task from List

Users can delete a specific task as not done via the following command:

`delete (task number)`

task number can be retrieved after viewing the list via the show List feature

example: `delete 3`

```
____________________________________________________________
Here are the tasks in your list:
 1. [T] [ ] CS2113 iP
 2. [D] [ ] Finish CS2113 Exercise (by: Mar 07 2024, 11:59 pm)
 3. [E] [ ] CS2113 Finals (from: 8/3/2024 4pm to: 8/3/2024 6pm)
____________________________________________________________
```

### Find a task by searching for a keyword

Users can find a specific task by searching using a keyword via the following command:

`find (keyword)`

task number can be retrieved after viewing the list via the show List feature

example: `find CS2113`

```
____________________________________________________________
Here are the matching tasks in your list:
 1. [T] [ ] CS2113 iP
 2. [D] [ ] Finish CS2113 Exercise (by: Mar 07 2024, 11:59 pm)
 3. [E] [ ] CS2113 Finals (from: 8/3/2024 4pm to: 8/3/2024 6pm)
____________________________________________________________
```

### Save all tasks in List to File

Users can save all the tasks in the list via the following command:

`bye`

```
 ____________________________________________________________
 Hey girlie! I hope I am helpful to managing your tasks.
 Don't worry, I have all your tasks saved! bye bye, take care:)!
 bye bye, take care:)!
____________________________________________________________
```

