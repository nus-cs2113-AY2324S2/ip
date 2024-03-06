# Sam User Guide

// Update the title above to match the actual product name

![](https://i.ibb.co/4j2FWkD/Sam.png)

Welcome to SAM (Simple Task Manager) - a command-line task management application designed to help you stay organized and on top of your tasks. SAM offers a variety of features to streamline your task management process. This user guide will walk you through the key functionalities of SAM and provide examples to help you get started.

## Adding deadlines

To add a deadline task in SAM, you can use the deadline command followed by the description of the task and the deadline end using the /by keyword.

Example 1: `deadline Submit report /by 2024-03-15`

Expected Outcome:
```
Got it. I've added this task:
[D] [ ] Submit report (by: Mar 15 2024)
```
Example 2: `deadline Return book /by Friday night`

```
Got it. I've added this task:
[D][ ] Return book (by: Friday night)
```

## Adding event

To add an event task in SAM, you can use the event command followed by the description of the task, the event start using the /from keyword, then the event end using the /to keyword.

Example 1: `event Attend EE2026 lecture /from 2024-03-07 0900 /to 2024-03-07 1030`

Expected Outcome:
```
____________________________________________________________
Got it. I've added this task:
[E][ ] Attend EE2026 lecture (Mar 7 2024 0900 to Mar 7 2024 1030)
____________________________________________________________
```
Example 2: `event EE2211 Midterms /from 2024-03-16 10am /to 11am`

```
Got it. I've added this task:
[E][ ] EE2211 Midterms (Mar 16 2024 10am to 11am)
```

## Adding todo

To add a todo, you can use todo followed by the description.

Example 1: `todo buy milk`

Expected Outcome:
```
____________________________________________________________
Got it. I've added this task:
[T][ ] buy milk
____________________________________________________________
```

## List tasks

You can show all current tasks with the list command.

Example 1: `list`

```
Here are the tasks in your list:
1.[D][ ] Return book (by: Friday night)
2.[E][ ] Attend EE2026 lecture (Mar 7 2024 0900 to Mar 7 2024 1030)
3.[E][ ] EE2211 Midterms (Mar 16 2024 10am to 11am)
4.[T][ ] buy milk
Now you have 4 tasks in the list.
```

## Find tasks by keyword

If you are looking for a certain task, use find to filter to tasks containing a keyword

Example 1: `find EE`

```
Here are the tasks matching your search:
2.[E][ ] Attend EE2026 lecture (Mar 7 2024 0900 to Mar 7 2024 1030)
3.[E][ ] EE2211 Midterms (Mar 16 2024 10am to 11am)
Now you have 2 tasks in the search list.
```

## Tasks saved between use

Tasks created in a previous sessions are saved and are available in future sessions.

![](https://i.ibb.co/hg1fp99/Save-feature.png)

## Mark/Unmark tasks

You can mark/unmark tasks by their list index to reflect their status.

Example 1: `mark 4`

```
Nice! I've marked this task as done:
[T][X] buy milk
```

Example 2: `unmark 4`

```
OK, I've marked this task as not done yet:
[T][ ] buy milk
```

## Delete tasks

You can delete tasks from the list by their list index, or delete all to clear all tasks.

Example 1: `delete 1`

```
Noted. I've removed this task:
[D][ ] Return book (by: Friday night)
Now you have 3 tasks in the list.
```

Example 2: `delete all`

```
You sure you want to completely clear your task list? Enter 'y'/'Y' to confirm
```

Option 1: Enter: `y` or `Y`

```
Confirmed, I deleted your entire list.
```

Option 2: Enter: `<anything else>`

```
Understood, I won't clear your list.
```

## Close program

Say bye to Sam to exit

`bye`

```
Hope to see you again soon!
  _____             ____            __
 / ___/__  ___  ___/ / /  __ _____ / /
/ (_ / _ \/ _ \/ _  / _ \/ // / -_)_/ 
\___/\___/\___/\_,_/_.__/\_, /\__(_)  
                        /___/         
```