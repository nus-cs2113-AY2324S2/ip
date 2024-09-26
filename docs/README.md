# Arriky User Guide

Arriky is a CLI chatbot that helps you manage daily tasks, including to-dos, events and deadlines efficiently.

## Features

#### Create Task

Arriky supports various tasks at work or in daily living, including `todo`, `deadline` and `event`.

#### Remove Task

You may remove task from the task list.

#### Completion Marker

To better keep track of your working progress, you can mark the completed tasks, and also undo those markings.

#### Search

In addition to browsing the entire tasklist, you may locate previously added tasks with keywords.

#### Local Storage

Everytime when you modify your task list by interacting with Arriky, the task list will be saved to a file, and loaded back when you open Arriky again.

## Usage

> [!IMPORTANT]
> All input in `TIME` fields must be in the format of `YYYY-MM-DD HH:MM` (timestamp) or `YYYY-MM-DD` (date).

### `todo` - Add a to-do

Example of usage: `todo [TASK_NAME]`

```
todo finish CS2113 Weekly Quiz
```

An acknowledgement message will be displayed after successful execution:

```
____________________________________________________________
 Got it. I've added this task:
 [T][ ] finish CS2113 Weekly Quiz
 Now you have 7 tasks in the list.
____________________________________________________________
```

### `event` - Add a event

Example of usage: `event [EVENT_NAME] /from [START_TIME] /to [END_TIME]`.

```
event CS2113 Lecture /from 2024-03-08 16:00 /to 2024-03-08 18:00
```

An acknowledgement message will be displayed after successful execution:

```
____________________________________________________________
 Got it. I've added this task:
 [E][ ] CS2113 Lecture (from: Mar 8 2024 16:00 to: Mar 8 2024 18:00)
 Now you have 6 tasks in the list.
____________________________________________________________
```

### `deadline` - Add a deadline

Example of usage: `event [DEADLINE_NAME] /by [DUE_TIME]`.

```
deadline submit CS2113 iP /by 2024-03-08 23:59
```

An acknowledgement message will be displayed after successful execution:

```
____________________________________________________________
 Got it. I've added this task:
 [D][ ] submit CS2113 iP (by: Mar 8 2024 23:59)
 Now you have 5 tasks in the list.
____________________________________________________________
```

### `list` - List all tasks

Example of usage: `list`.

Sample output:

```
____________________________________________________________
 Here are all the entries in your task list:
 1. [D][ ] assignment 1 (by: Apr 23 2024)
 2. [D][ ] assignment 2 (by: May 21 2024 18:00)
 3. [T][ ] return book
 4. [D][ ] finish book reading report (by: Mar 9 2024)
____________________________________________________________
```

### `find` - Search with keyword

Example of usage: `find [KEYWORD]`.

```
find assignment
```

A list of matching entries will be displayed:

```
____________________________________________________________
 Here are the matching tasks in your list:
 1. [D][ ] assignment 1 (by: Apr 23 2024)
 2. [D][ ] assignment 2 (by: May 21 2024 18:00)
____________________________________________________________
```

### `mark` - Mark as completed

Example of usage: `mark [TASK_ID]`.

```
mark 2
```

An acknowledgement message will be displayed after successful execution:

```
____________________________________________________________
 Nice! I've marked this task as done:
 [D][X] assignment 2 (by: May 21 2024 18:00)
____________________________________________________________
```

### `unmark` - Undo marking as completed

Example of usage: `unmark [TASK_ID]`.

```
unmark 2
```

An acknowledgement message will be displayed after successful execution:

```
____________________________________________________________
 OK, I've marked this task as not done yet:
 [D][ ] assignment 2 (by: May 21 2024 18:00)
____________________________________________________________
```

### `bye` - Exit programme

Example of usage: `bye`.

A goodbye message will be displayed:

```
____________________________________________________________
 Bye. Hope to see you again soon.
____________________________________________________________
```

## Troubleshooting

If an error occurred when Arriky is running, a prompt message will be displayed in the UI. Error messages begin with `~X~ Bot Error:` and are self-explainatory. For example:

```
~X~ Bot Error: There should be no argument for this command. Please try again.
```

```
~X~ Bot Error: Command does not exist. Please try again.
```

```
~X~ Bot Error: Invalid datetime format. Please follow 'YYYY-MM-DD HH:MM' or 'YYYY-MM-DD' format.
```

## About

Developed by [Songyue Wang](https://github.com/songyuew) for CS2113 (NUS 23-24 Spring).
