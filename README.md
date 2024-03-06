# Krot


Krot Chatbot is a task tracking app for managing your everyday tasks, optimised for the CLI. Perfect for those who likes to type away. 
Try it out for yourself and keep your tasks in order.


## Quickstart

Prerequisites: JDK 11

1. Download the latest Krot.jar from [here](https://github.com/tannerlie/ip/releases).
2. Copy the file to the folder you want to use as the home folder for your Krot Task Manager.
3. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar Krot.jar command to run the application.<br>
   A GUI similar to the below should appear in a few seconds.<br>
![Screenshot of Krot GUI](https://raw.githubusercontent.com/tannerlie/ip/master/assets/Screenshot%20of%20Krot%20GUI.png)
4. Type the command in the command box and press Enter to execute it. e.g. typing ```commands``` and pressing Enter will open the command window.
   Some example commands you can try.
   1. ```list```: Lists all the current tasks.
   2. ```todo <task>```: Creates a task to do.
   3. ```deadline <task> /by <YYYY-MM-DD>T<HH:MM>```: Creates a task with a deadline.
   4. ```event <task> /from <YYYY-MM-DD>T<HH:MM> /to <YYYY-MM-DD>T<HH:MM>```: Creates an event with a duration.
   5. ```bye```: Exits the app.
5. Refer to the [Features](#Features) below for details of each command.<br>



## Features

1. Note on the format of the commands
2. The commands are always the first word.
3. The data that the user has to input are in the form ```<text>``` where the whole
thing has to be replaced
4. For commands with dates like ``<YYYY-MM-DD>T<HH:MM>`` it has the format of 
the year in 4 digits, the month in 2 digits and day in 2 digits, followed by a capital T
and the 24hr time e.g. for the 2nd Mar 24, 5pm it will be in the format ```2024-03-02T17:00```.
5. Many commands may also have initializers such as ```/intitializers```. Do include a space between 
the input and initializers e.g ```deadline Assignment /by 2024-03-02T17:00```.
6. Please follow the commands strictly. The order and format of the commands matters.

### Open the commands list: ```commands``` <br>
Shows a list of all the commands accepted.<br>
Format: ```commands```
### See your list of tasks: ```list``` <br>
Shows a list of all the tasks in you list.<br>
Format: ```list```
### Add a task to the list: ```todo <task>```<br>
Adds a new task to do to the list.<br>
Format: ```todo <task>```<br>
- Replace ```<task>``` with your task to do.
- e.g: ```todo buy groceries```
### Add a task with deadline to the list: ```deadline```<br>
Adds a new task with a deadline to the list.<br>
Format: ```deadline <task> /by <YYYY-MM-DD>T<HH:MM>```<br>
- Replace ```<task>``` with your task and ```<YYYY-MM-DD>T<HH:MM>``` the date and time of the Task due.
- e.g: ```deadline Assignment /by <2024-03-26>T<23:59>```
### Add an event to the list: ```event```<br>
Adds a new event and its duration to the list.<br>
Format: ```event <event name> /from <YYYY-MM-DD>T<HH:MM> /to YYYY-MM-DD>T<HH:MM>```<br>
- Replace ```<event name>``` with your task and ```<YYYY-MM-DD>T<HH:MM>``` the date and time of the event start and end.
- e.g: ```event CS2113 Lecture /from 2024-03-05T12:00 /to 2024-03-05T14:00```
### Mark the tasks as done: ```mark```<br>
Marks the task as done.<br>
Format: ```mark <taskNumber>```
### Unmark the tasks as done: ```unmark```<br>
Un-marks the task.<br>
Format:```unmask <taskNumber>```
### Delete the task: ```delete```<br>
Deletes the task specified.<br>
Format:```delete <taskNumber>```
### Find all the tasks in the list: ```find```<br>
Searches the list for the task by title or date.<br>
Format:```find /title <title>``` or ```find /date <YYYY-MM-DD>```<br>
- Finds the task in the list that corresponds to either the title or date.
- The search by title is not case-sensitive.<br>
Examples<br>

![Screenshot of find by title](https://raw.githubusercontent.com/tannerlie/ip/master/assets/Screenshot%20of%20Find.png)
### End the chat session: ```bye```<br>
Ends the Chatbot and saves your list data.<br>
Format:```bye```
### Saving the tasks data <br>
Saving the date occurs everytime a command is given.

## FAQ
Q. How is my data saved for future use?<br>
A. The list of tasks will be saved whenever there is a change in your tasks details/tasks added.<br>
Q. How do I transfer my data to another computer? <br>
A. Install the app on another computer and replace the taskList.txt file in the new computer with the old one.


## Command Summary
| Action                 | Format                                                                      |
|------------------------|-----------------------------------------------------------------------------|
| Commands               | ```commands```                                                              |
| List                   | ```list```                                                                  |
| Add task todo          | ```todo <task>```                                                           |
| Add task with deadline | ```deadline <task> /by <YYYY-MM-DD>T<HH:MM>```                              |
| Add event              | ```event <event name> /from <YYYY-MM-DD>T<HH:MM> /to YYYY-MM-DD>T<HH:MM>``` |
| Mark                   | ```mark  <taskNumber>```                                                    |
| Unmark                 | ```unmask <taskNumber>```                                                   |
| Delete                 | ```delete  <taskNumber>```                                                  |
| Find                   | ```find /title <title>``` or ```find /date <YYYY-MM-DD>```                  |
| End                    | ```bye```                                                                   |