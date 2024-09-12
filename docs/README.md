# User Guide

## Features 

### deadline

## Usage

### `deadline` - add the task deadline to tasks list

Example of input; 

`deadline return book /by Sunday`

Expected outcome:

deadline task is added to task list

```
____________________________________________________________

Got it. I've added this task:
[D] [ ] return book  (by: sunday)
Now you have 4 tasks in the list.
____________________________________________________________

```
### event

## Usage

### `event` - add the task event to tasks list

Example of input:

`event LIN Siyan attend CS2113 lecture Friday 23 Feb 2024 /from 4 /to 6pm`

Expected outcome:

todo task is added to task list

```
____________________________________________________________

Got it. I've added this task:
[E] [ ] lin siyan attend cs2113 lecture friday 23 feb 2024  (from: 4 to: 6pm)
Now you have 5 tasks in the list.
____________________________________________________________


```
### todo

## Usage

### `todo` - add the task todo to tasks list

Example of input:

`todo borrow chair`

Expected outcome:

todo task is added to task list

```
____________________________________________________________

Got it. I've added this task:
[T] [ ] borrow chair
Now you have 6 tasks in the list.
____________________________________________________________

```

### mark

## Usage

### `mark` - mark the task as DONE

Example of input:

`mark 1`

Expected outcome:

task with the index is marked as DONE

```
____________________________________________________________

OK, I've marked this task as done yet:

[D] [X] return book (by: sunday)
____________________________________________________________

```
### unmark

## Usage

### `unmark` - unmark the task as UNDONE

Example of input:

`unmark 1`

Expected outcome:

task with the index is unmarked as UNDONE

```
____________________________________________________________

OK, I've marked this task as not done yet:

[D] [ ] return book (by: sunday)
____________________________________________________________


```

### list

## Usage

### `list` - list all the tasks in the task list

Example of input:

`list`

Expected outcome:

all tasks in the list are shown

```
____________________________________________________________

1.[D] [ ] return book (by: sunday)
2.[T] [X] feed dogs
3.[D] [X] project meeting (by: mon 2pm)
4.[D] [ ] return book (by: sunday)
5.[E] [ ] lin siyan attend cs2113 lecture friday 23 feb 2024 (from: 4to: 6pm)
6.[T] [ ] borrow chair
____________________________________________________________


```
### find

## Usage

### `find` - find the tasks contain a specific word

Example of input:

`find book`

Expected outcome:

tasks that contains book are all shown 

```
____________________________________________________________

____________________________________________________________

Here are the matching tasks in your list:

1.[D] [ ] return book (by: sunday)
2.[D] [ ] return book (by: sunday)
____________________________________________________________

____________________________________________________________

```
### delete

## Usage

### `delete` - delete a specific task

Example of input:

`delete 3`

Expected outcome:

the task with index 3 will be deleted

```
____________________________________________________________
Noted. I've removed this task:
[D] [X] project meeting (by: mon 2pm)
Now you have 4 tasks in the list.
____________________________________________________________

```
### bye

## Usage

### `bye` - end the chat with dukeRobot

Example of input:

`bye`

Expected outcome:

the program exit

```
____________________________________________________________

____________________________________________________________
Bye. Hope to see you again soon!

____________________________________________________________

____________________________________________________________

(base) celinelam@CelinedeMacBook-Air java % 

```

