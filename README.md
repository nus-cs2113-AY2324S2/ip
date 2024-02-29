# Omoh 
Welcome to the Omoh chatbot user guide!

## Content Page

- [Setting up in Intellij](#setting-up-in-intellij)
- [Features](#features)
   - [Adding a todo task: `todo TASK_DESCRIPTION`](#adding-a-todo-task-todo-task_description)
   - [Adding a deadline task: `deadline TASK_DESCRIPTION /by DATE`](#adding-a-deadline-task-deadline-task_description-by-date)
   - [Adding an event task: `event TASK_DESCRIPTION /from DATE /to DATE`](#adding-a-event-task-event-task_description-from-date-to-date)
   - [Marking a task: `mark TASK_NUMBER`](#marking-a-task-mark-task_number)
   - [Unmarking a task: `unmark TASK_NUMBER`](#unmarking-a-task-unmark-task_number)
   - [Deleting a task: `delete TASK_NUMBER`](#deleting-a-task-delete-task_number)
   - [Listing all tasks: `list`](#listing-all-tasks-list)
   - [Finding tasks based on keyword: `find KEYWORD`](#finding-tasks-based-on-keyword-find-keyword)
   - [Exiting the program: `bye`](#exiting-the-program-bye)

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/omoh/Omoh.java` file, right-click it, and choose `Run Omoh.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

```
_________________________________________________
Hello! I'm Omoh
What can I do for you?
__________________________________________________
```

## Features


### Adding a todo task: `todo TASK_DESCRIPTION`
Adds a todo task into the task list.
* `TASK_DESCRIPTION` must not be empty.

Examples:
* todo eat lunch
* todo study for cs2113


### Adding a deadline task: `deadline TASK_DESCRIPTION /by DATE`
Adds a deadline task into the task list.
* `TASK_DESCRIPTION` must not be empty.
* `DATE` must not be empty
* `/by` must be included

Examples:
* deadline cs2113 ip /by wednesday
* deadline cs1010 mid term revision /by 10th March 2024


### Adding a event task: `event TASK_DESCRIPTION /from DATE /to DATE`
Adds a event task into the task list.
* `TASK_DESCRIPTION` must not be empty.
* `DATE` must not be empty
* `/from` and `/to` must be included

Examples:
* event cs2113 lecture /from 4pm /to 6pm
* event CDE career fair /from 29th Feb 10am /to 29th feb 10pm


### Marking a task: `mark TASK_NUMBER`
Marks a task in task list as completed. X stands for completed, empty stands for not completed.
* `TASK_NUMBER` must be a positive integer within number of total tasks.
* `TASK_NUMBER` must not be empty

Examples:
* mark 1
* mark 2


### Unmarking a task: `unmark TASK_NUMBER`
Marks a task in task list as not completed. X stands for completed, empty stands for not completed.
* `TASK_NUMBER` must be a positive integer within number of total tasks.
* `TASK_NUMBER` must not be empty

Examples:
* unmark 1
* unmark 2


### Deleting a task: `delete TASK_NUMBER`
Deletes a task from task list according to task number provided
* `TASK_NUMBER` must be a positive integer within number of total tasks.
* `TASK_NUMBER` must not be empty

Examples:
* `list` followed by `delete 1 `deletes the first task in the task list.


### Listing all tasks: `list`
Shows a list of all tasks in task list


### Finding tasks based on keyword: `find KEYWORD`
Finds matching task(s) in task list based on a keyword provided
* `KEYWORD` must not be empty

Examples:
* `find books` returns `read books` and `return book to book drop`
* `find study` returns `study at library` and `study tonight`


### Exiting the program: `bye`
Exits programe and saves the task list into ../data/output.txt file
