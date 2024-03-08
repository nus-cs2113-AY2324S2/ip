# Daisy User Guide

This is the user guide for Daisy, a Command Line Interface (CLI) application optimised for the tracking of daily tasks.
This guide will go through all the available operations to utilise Daisy, as well as how user inputs should be correctly
formatted to avoid encountering errors while interacting with the app.

## Setting up Daisy
To set up daisy, simply go through the following list of operations.
1.    Download the <i>ip.jar</i> file from the latest release
2.    Place the file into your desired directory. It is <b>recommended</b> that you create a <b>separate directory</b>
      to hold the application instead of directly using the <b>root directory</b>.

If all is well, Daisy will have the following folder structure during its operation. There is no need to create any of the
shown directories/files, as Daisy will be able to automatically do so for you during while running normally.

  ```
   <Your created directory>
│  ip.jar
│
└─data
        Daisy.txt

   ```
Use the <i>java -jar</i> operation to execute the  ip.jar file. You will be greeted with the following ui printouts if
initial execution is successful.
   ```
   Please wait while Daisy loads your previous data!
   No previous data found! Daisy will create a new one for this entry!
   ____________________________________
   Good day! This is Daisy.
   Any task for today?
   ____________________________________
   ```

## List of Operations
* Notes about the command  format:
    * Values field within the <> symbol are required user defined parameters.
    * Keywords starting with the / symbol are important formatting keywords used to separate parameters.
    * The first word within every user command will be identified as the action keyword used to signify the desired
      operation.
### Adding Tasks: Todo, Deadline, Event
There are a total of 3 different task type for you to categorise their daily tasks into.

* The <b>Todo</b> task type is suitable for activities which only the task name is important. To add a task of <b>Todo</b>
  type, format your task information according to the following structure.
   ```
   todo <task name>
   //e.g. todo do homework
   ```

* The <b>Deadline</b> task type is suitable for activities with an important due date. To add a task of <b>Deadline</b>
  type, format your task information according to the following structure.
   ```
   deadline <task name> /by <due date in yyyy-MM-dd HHmm>
   //e.g. deadline do homework /by 2024-03-08 2359
   ```

* The <b>Event</b> task type deals with activities that have both a starting and ending time. To add a task of <b>Event</b>
  type, format your task information according to the following structure.
  ```
   event <task name> /from <starting date> <starting time> /to <ending date> <ending time>
   //e.g. event do homework /from 2024-03-08 1200 /to 2024-03-08 2359
   ```
If the adding operation is successful, Daisy will alert you by printing out the success message. For e.g.,
```
____________________________________
Task received! The following has been added to your list of todos:
[E][ ] do homework (from: Mar 08 2024 12:00PM to: Mar 08 2024 11:59PM)
Now you have 1 tasks in your todo list.
____________________________________
```

### Listing Tasks
To get an overview of your current list of tasks, use the following input:
  ```
  list
  ```
Daisy will output the list correspondingly if operation is successful. For e.g.,
  ```
  ____________________________________
  1.[E][ ] do homework (from: Mar 08 2024 12:00PM to: Mar 08 2024 11:59PM)
  2.[T][ ] eat breakfast
  ____________________________________
  ```
Note that the first square bracket value depicts the task type (<i>"T" - Todo, "D" - Deadline, "E" - Event</i>), while the
second depicts the isDone status of the task (<i>"X" - Done, " " - Undone</i>).

### Changing Tasks: Mark, Unmark, Delete
There are 3 different operations available for you to edit the current list of tasks.

* The <b>Mark</b> operation helps you mark a task into Done status. To <b>mark</b> a task, format your input according to the
  following structure.
  ```
  mark <task index as shown in the task list>
  //e.g. mark 1
  ```
  Daisy will display the updated task if everything goes well. For e.g.,
  ```
  ____________________________________
  Congrats on completing the task!
  [E][X] do homework (from: Mar 08 2024 12:00PM to: Mar 08 2024 11:59PM)
  ____________________________________
  ```

* The <b>Unmark</b> operation helps you unmark a task into Undone status. To <b>unmark</b> a task, format your input according
  to the following structure.
  ```
  unmark <task index as shown in the task list>
  //e.g. unmark 1
  ```
  Daisy will display the updated task if everything goes well. For e.g.,
   ```
  ____________________________________
  More time needed for the following task? Sure!
  [E][ ] do homework (from: Mar 08 2024 12:00PM to: Mar 08 2024 11:59PM)
  ____________________________________
  ```

* The <b>Delete</b> operation helps you delete a task entirely. To <b>delete</b> a task, format your input according
  to the following structure.
  ```
  delete <task index as shown in the task list>
  //e.g. delete 1
  ```
  Daisy will display the task that is deleted as well as the number of remaining tasks if everything goes well. For e.g.,
  ```
  ____________________________________
  I see. The following task will be removed from your list:
  [E][ ] do homework (from: Mar 08 2024 12:00PM to: Mar 08 2024 11:59PM)
  Now you have 1 tasks in your todo list.
  ____________________________________
  ```

### Finding Tasks
Daisy is also able to return you a list of tasks with task name containing a specified <b>keyword</b>. To do so, format your
input according to the following structure.
  ```
  find <keyword>
  //e.g. find assignment
  ```
The list of matching tasks will be returned if operation is successful. For e.g.,
  ```
  find assignment //find operation
  ____________________________________
  1.[T][ ] math assignment
  2.[T][ ] physics assignment
  ____________________________________
  ```

### Ending the program
The following command can be used to end a call to Daisy.
  ```
  bye
  ```
After receiving the ending prompt, Daisy will attempt to save the current task data. If successful, the following message
will be displayed.
  ```
  ____________________________________
  Daisy will begin saving the data for this entry!
  Successfully saved file. Program will now exit.
  ____________________________________
  ```
This will allow all task data to be written in the form of Comma-Separated Values within the storage file "Daisy.txt".

## Common Errors

### Failure to adhere to command format
Error messages will be output by daisy in the event if your user output is not matching the corresponding formatting for
your desired operation. For e.g.
   ```
   ____________________________________
   Error! No event detected for todo. Try again!
   ____________________________________
   ```
Or
   ```
   ____________________________________
   Error! Event entry is not following format. Try again!
   ____________________________________
   ```
This is could be caused by
* Misspelled Action keywords (e.g. lsit instead of list)
* Absence of required user parameters
* Absence of important formatting keywords (e.g. /by)
* User parameters does not follow intended standards (e.g. Index field receives a character value)

### I/O permission errors
Daisy's operation include reading and writing of text files. If permission is not granted on the administrative level,
the saving of task data may be aborted with the following error message thrown.
  ```
  ____________________________________
  Daisy will begin saving the data for this entry!
  Error has occurred when saving. This entry will not be saved!
  ____________________________________
  ```
This is could be caused by
* ip.jar is directly thrown to the root directory
* ip.jar was not executed in administrative mode
* ip.jar was not given read/write permissions to ./data/Daisy.txt
* ./data/ was deleted during program operation

## Summary of Commands
| Operation           | Command Format                                                                                    |
|---------------------|---------------------------------------------------------------------------------------------------|
| <b>Add</b> Todo     | todo \<task name\>                                                                                |
| <b>Add</b> Deadline | deadline \<task name\> /by \<due date in yyyy-MM-dd HHmm\>                                        |
| <b>Add</b> Event    | event \<task name\> /from \<starting date\> \<starting time\> /to \<ending date\> \<ending time\> |
| <b>List</b> Tasks   | list                                                                                              |
| <b>Mark</b> Task    | mark \<task index as shown in the task list>                                                      |
| <b>Unmark</b> Task  | unmark \<task index as shown in the task list>                                                    |
| <b>Delete</b> Task  | delete \<task index as shown in the task list>                                                    |
| <b>Find</b> Task    | find \<keyword>                                                                                   |
| <b>End Program</b>  | bye                                                                                               |