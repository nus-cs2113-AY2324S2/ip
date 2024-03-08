# Hachi User Guide

![image](https://github.com/clarencepohh/ip/assets/110753446/e0aafaa9-070d-45e1-9ce6-963e4a3116e3)

Hachi is a simple chatbot that runs in the CLI that has the functionality to add Tasks to a list of Tasks.
<br>There are 3 task types: Todos (which have no complete date), Deadlines (which have a complete date), and Events (which have a start and end date) 

## help: `help` 
To get the list of valid commands, use the command `help`.

Examples: 
  * `help`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/a80c5636-6cbd-469d-b876-487d00fd1333)

## Adding todos: `todo` 
To add a Todo, use the command `todo <event_name>`.

Examples: 
  * `todo project`
  * `todo School Homework`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/aca62485-4e3c-482d-97ba-f7971592a92b)

  
## Adding deadlines: `deadline`
To add a Deadline, use the command `deadline <event_name> /by <start_date>`.
> The start_date does not have to be a date format, any text will do!

Examples: 
  * `deadline IP /by 8th March 2024`
  * `deadline IP /by 08/03/2024`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/1a2aded2-b8ee-45ca-8552-5fb494073f94)


## Adding events: `event`
To add an Event, use the command `event <event_name> /from <start_date> /to <end_date>`.
> The start_date and end_date does not have to be a date format, any text will do!

Examples: 
  * `event Chinese New Year /from 10th Feb /to 11th Feb`
  * `event recess week /from 26th Feb /to 1st Mar`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/d3ef2404-c121-4f67-91f3-dc450d8b795a)

## Getting your list of tasks: `list`
To get your list of tasks, use the command `list`.

Examples: 
  * `list`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/72644182-bb2b-416e-8a8f-95b33c9a3366)

## Finding tasks in your list that have a specified substring: `find`
If you'd like to find all tasks with a certain substring, use the command `find <substring>`.

Examples: 
  * `find IP`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/8673ebf6-c22b-433f-9da4-02af75f2fc82)

## Mark a task as done: `mark`
To mark a task as complete, use the command `mark <task_index>`.

Examples: 
  * `mark 2`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/db3e2231-f780-4861-919a-fd82175cc006)

## Mark a task as undone: `unmark`
To mark a task as incomplete, use the command `mark <task_index>`.

Examples: 
  * `unmark 2`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/84bb2227-2c20-42a2-8cb4-ba4541ef3135)

## To delete a task: `delete`
To delete a task, use the command `delete <task_index>`.

Examples: 
  * `delete 3`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/ffc5798a-b922-4de7-b1ef-2f39d685a89e)

## Closing the chatbot: `bye` or `goodbye`
To close the chatbot, use the command `bye` or `goodbye`.

Examples: 
  * `bye`
  * `goodbye`
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/b8c2b625-27d5-4707-842d-5235219419c2)
<br>![image](https://github.com/clarencepohh/ip/assets/110753446/1f2428d5-be1b-4e00-9928-fce20b4d9959)
