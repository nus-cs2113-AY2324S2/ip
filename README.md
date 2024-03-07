# JX Clown Taskmanager User Guide

## Features 

### ToDo

<br>Create a new ToDo task not bounded by any time constraints.</br>
Example of usage: 
<br>`todo <taskDescription>`</br>
`todo savemepls`

### Deadline

<br>Create a new Deadline task that tracks when the task needs to be accomplished.</br>
<br>Date and Time format is in: `<dd/mm/yyyy hh:mm:ss>`</br>
Example of usage: 
<br>`deadline <taskDescription> /by <dd/mm/yyyy hh:mm:ss>`</br>
`deadline helpmepls /by 20/12/2024 10:10:40`

### Event

<br>Create a new Event task that tracks when the task starts and when it needs to be accomplished.</br>
<br>Date and Time format is in: `<dd/mm/yyyy hh:mm:ss>`</br>
Example of usage: 
<br>`deadline <taskDescription> /from <dd/mm/yyyy hh:mm:ss> /to <dd/mm/yyyy hh:mm:ss>`</br>
`event helpmepls /from 20/12/2024 10:10:40 /to 21/12/2024 10:10:40`

### Mark/Unmark tasks

Mark/Unmark tasks as done or not done
Example of usage: 
<br>`mark <list_index>`</br>
`mark 1`
<br>`unmark <list_index>`</br>
`unmark 1`

### Delete tasks

Delete tasks
Example of usage: 
<br>`delete <list_index>`</br>
`delete 1`

### Find tasks

Find tasks based on keywords
Example of usage: 
<br>`find <keyword>`</br>
`find abc`


```
expected output
```
