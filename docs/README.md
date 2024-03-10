# User Guide

Nehsik is a CLI-based Personal Assistant ChatBot that helps the user to keep track of their daily tasks

## Features 

### Add tasks : `todo`, `deadline`, `event`

Tasks of the following types can be added:
- **Todo**: Contains task description only
- **Deadline**: Contains task description and the deadline time
- **Event**: Contains task description and the duration of the event
  
Format: `todo TASK_DESCRIPTION` `deadline TASK_DESCRIPTION /by DEADLINE` `event TASK_DESCRIPTION /from START_TIME /to END_TIME`

### List tasks : `list`

Displays all the tasks present in the task list.
Format: `list`

### Delete task : `delete` 

Deletes the required task based on the task number of the task to be deleted in the latest task list.
Format: `delete TASK_NUM`

### Mark task : `mark`

Marks the required task as done based on the task number of the task to be marked as done in the latest task list.
Format: `mark TASK_NUM`

### Unmark task : `unmark`

Marks the required task as not done based on the task number of the task to be marked as not done in the latest task list.
Format: `unmark TASK_NUM`

### Find task : `find`

Displays the list of matching tasks which contain the keyword in their descriptions.
Format: `find KEYWORD`

### Exit : `bye`
Displays bye message and exits from the program.
Format: `bye`

### Saving the data
Data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
Your task list can be found in the "nehsik.txt" file created in the same directory where the Nehsik.jar file is present.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
