# Kowalski Bot 
If you've watched **[Penguins of Madagascar](https://youtu.be/Z-5SrndEVrI?si=_ivcZTA_PTUoJhI8)**, you'll know **_[Kowalski](https://youtu.be/f3CVuo_HFLM?si=v1hv_y0UU4YP8HYb)_**.

He is often referred to as Skipper's Lieutenant, the second-in-command.

If you're someone who needs a Kowalski by your side at all times, this bot is for you!


## Overview 

Kowalski is a bot for you to keep track of your everyday tasks.
Be it keeping track of your homework assignments, 
your daily chores, Kowalski's got your back!

Kowalski has got 3 types of tasks - ToDo task, Deadline task, and Event task.

### Task types
- ToDo: Task with ```Name```
- Deadline: Task with ```Name``` and ```Deadline``` 
- Event: Task with ```Name```, ```From``` and ```To```


### Kowalski.txt

Kowalski is able to save the tasks which you add by storing them in a text file called **Kowalski.txt**.

If you are using this bot for the **_first time_**, this bot will 
automatically create a directory called **data** which will then store your **Kowalski.txt** inside of it.

E.g. ```C:\Users\Skipper\Desktop\Kowalski_Bot\data\Kowalski.txt``` 
assuming you are storing the jar file in ```C:\Users\Skipper\Desktop\Kowalski_Bot```

## Running Kowalski Bot
#### Prerequisites: 
- Java _(version >= 11)_ installed
- Having the latest JAR file release of Kowalski
- ~~[Kowalski's energy](https://youtu.be/xv9ek6RP4r8?si=AhwSaukCiyUihn-X)~~

#### Steps:
1. Open terminal and change directory to the directory in which you've stored the JAR file. 
2. Run `java -jar ./Kowalski.jar`
 
To exit the application, type the keyword `bye`. 
To restart the application, repeat steps 1 & 2.

## Features

### 1. List all tasks ```list```

Lists all tasks which the user has input.

### 2. Find matching tasks ```find```

Finds and filters tasks containing a keyword, and prints them out in a list.

### 3. Remove a task ```delete```

Removes a task from the list of tasks.

### 4. Mark a task ```mark```

Marks a task as complete.

### 5. Unmark a task ```unmark```

Marks a task as **_incomplete_**.

### 5. Add a ToDo task ```todo```

Adds in a task for the user, containing only the name.

### 6. Add a Deadline task ```deadline```

Adds in a deadline for the user, containing a name and deadline of the task.

### 7. Add an Event task ```event```

Adds in an event for the user, containing a name, start date/time and end date/time.

## Usage

### Adding a task

The format for adding specific tasks are as follows. The command keyword is not case sensitive, but the /*vars* are. 


**Formats**:
- Todo: ```todo NAME```
- Deadline: ```deadline NAME /by STRING```
- Event: ```event NAME /from STRING /to STRING```


***Example usage***:

- ```todo Code for CS1010 Assignment```
- ```deadline Write up your analysis report for Skipper /by Friday 2359```
- ```event Attend Snowball Olympics /from Tomorrow 9am /to Friday 6pm```


***Expected outcome***:

If the arguments were entered correctly, the task should be added successfully.

***Example output***:

_For Todo Tasks:_
```
Skipper you've got this work to do:
  [T][ ] Code for CS1010 Assignment
Now you have 1 task in the list.
____________________________________________________________
Kowalski analysing inputs...
Change recorded in the Text file!
____________________________________________________________
```
_For Deadline Tasks:_
```
Skipper, I have recorded this deadline:
  [D][ ] Write up your analysis report for Skipper (by: Friday 2359)
Now you have 2 tasks in the list.
____________________________________________________________
Kowalski analysing inputs...
Change recorded in the Text file!
____________________________________________________________
```
_For Event Tasks:_
```
Skipper I've noted this event in my calendar:
  [E][ ] Attend Snowball Olympics (from: Tomorrow 9am to: Friday 6pm)
Now you have 3 tasks in the list.
____________________________________________________________
Kowalski analysing inputs...
Change recorded in the Text file!
____________________________________________________________
```


### ```mark``` / ```unmark``` : Mark/Unmark tasks

**Format**: ```mark/unmark NUMBER```

Firstly, the task number must be retrieved. This can be done easily using the command ```list```.

Next, use ```mark x``` or ```unmark x``` to set the state of the task to the desired state.

***Example usage***:

- ```mark 1```
- ```unmark 3```

***Expected outcome***:

If a valid number is entered, a response marking the task will be shown.

***Example output***:

_After marking task:_
``` 
Way to go Skipper! I've marked this task as done:
  [T][X] Code for CS1010 Assignment
____________________________________________________________
Kowalski analysing inputs...
Change recorded in the Text file!
____________________________________________________________ 
```
_After unmarking task:_

```
C'mon Skipper, you're much better than that! I've marked this task as undone:
  [E][ ] Attend Snowball Olympics (from: Tomorrow 9am to: Friday 6pm)
____________________________________________________________
Kowalski analysing inputs...
Change recorded in the Text file! 
____________________________________________________________
```


### List: ```list```: Listing all tasks

**Format**: ```list```

By typing list, the user can print out all existing tasks into the command line.

***Example usage***:

- ```list```

*Expected outcome*:

All tasks will be printed out in the console.

*Example output*:

```
1.[T][X] Code for CS1010 Assignment
2.[D][ ] Write up your analysis report for Skipper (by: Friday 2359)
3.[E][ ] Attend Snowball Olympics (from: Tomorrow 9am to: Friday 6pm)
____________________________________________________________
```


### Find: ```find``` : Finding keywords

**Format**: ```find STRING```

The command looks through all existing tasks, and prints them out if their name contains the string query.

*Example usage*:

- ```find lecture```
- ```find homework```

*Expected outcome*:

All tasks containing the keywords in their name will be printed out.

*Example output*:

__Input:__``` find report```

```
Skipper here are the matching tasks:
1.[D][ ] Write up your analysis report for Skipper (by: Friday 2359)
____________________________________________________________
```

### Delete: ```delete``` : Deleting a task

**Format**: ```delete NUMBER```

As with *mark/unmark*, the number of the task to be deleted must be obtained. 

Next, typing the command in the given format will delete said task from the list.

Once deleted, said task **CANNOT** be retrieved.

***Example usage***:

- ```delete 4```

***Expected outcome***:

Task of the given number (in the list) will be deleted.

***Example output***:

```
Damn Skipper, you've got some courage removing this task:
  [T][ ] Admire favourite snack
Now you have 3 tasks in the list.
____________________________________________________________
Kowalski analysing inputs...
Change recorded in the Text file!
____________________________________________________________
```

### Bye:```bye``` : Exit the program

**Format**: ```bye```

***Expected outcome***:

Exits the program and updates the stored tasks in **_Kowalski.txt_** file.

***Expected output***:
```
Bye Skipper! Hope to serve you again for your next mission!
____________________________________________________________
```


