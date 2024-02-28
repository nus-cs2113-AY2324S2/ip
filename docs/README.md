# User guide of chatbot **Noob**

Hi, this chatbot is still a Noob. He can store and save tasks for you.

***Need upgrade.........................***

He has the following features:

## Features

### !!!!! **Notes about the command format** !!!!!

* Words in `UPPER_CASE` are the parameters to be supplied by the user
* Accepted `DATES/TIMES` format. **Note: 24-hour and 12-hour time formats are supported** for `hour:minute`
    * `year-month-day hour:minute[AM/PM]`
    * `month/day/year hour:minute[AM/PM]`
    * `year.month.day hour:minute[AM/PM]`  
      ***See examples of command for more details***

### Viewing help: `help`

* Shows the list of available commands

### Store three types of tasks: `todo` / `deadline` / `event`

* Type `todo` + `TASKCONTENT` to record a **todo** task
* Type `deadline` + `TASKCONTENT` + `/by` + `DATES/TIMES` to record a **deadline** task
* Type `event` + `TASKCONTENT` + `/from` + `DATES/TIMES` + `/to` + `DATES/TIMES` to record a **event** task

Examples:

* `todo eat bread`
* `deadline Assignment /by 2024-8-17 5:30PM`
* `event exercise /from 2/28/2024 22:36 /to 3/10/2024 9:30`

### List all tasks: `list`

* Type `list` to list what he has stored for you

### Mark / Unmark tasks: `mark` / `unmark`

* Type `mark` + `NUMBER` to mark tasks as done
* Type `unmark` + `NUMBER` to mark tasks as not done

Examples:

* `mark 1`
* `unmark 3`

### Delete tasks: `delete`

* Type `delete` + `NUMBER` to delete tasks

Examples:

* `delete 1`

### Find tasks: `find`

* Type `find` + `TASKCONTENT` to find tasks
* Type `find` + `DATE/TIMES` to find tasks

Examples:

* `find read book`
* `find 2023.10.23`

### Saving the tasks

* Task data are saved in the hard disk automatically after any command that changes the data.

### Exit the chatbot: `bye`

* Type `bye` to say goodbye to him

# Hope you have fun with Noob!
