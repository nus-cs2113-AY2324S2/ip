# Nocturne v0.2

Welcome to the User Guide for Nocturne.

![The eternal nightmare... Now a servant?](https://pbs.twimg.com/media/E4FchwcVIAIfb4h?format=jpg&name=4096x4096)

## Setting up the ChatBot

Ensure that you have the ip.jar file downloaded. Take note of which directory this file is located. A convenient location would be in Downloads, or the Desktop.

Now, open up the command prompt. You can do this by pressing the Windows key and searching 'Command Prompt'. For Mac users, you can press 'Command + Space' to open up the search bar. From there you can open the Terminal by searching for it on the search bar.

Once in the terminal, type in *cd Downloads* or *cd Desktop*. This will place you in the directory where you placed Nocturne. Now, simply type 
> java -jar ip.jar

And this will initialize Nocturne.

## Nocturne's Features

Primarily, Nocturne is there to help you record tasks you need to do, as well as their completion status. To utilize them, you will need to enter the commands below. In particular, keep the commands in lowercase, and as much as possible, do not deviate from the command structure. Error messages will be thrown at you if you mess up, but not to worry, Nocturne will usually tell you what you did wrong.

For the features below, Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in *todo TASK*, *TASK* is a parameter which can be used as *todo pay the bills*.

### Adding a Todo task: **todo STRING**
Adds a Todo task to the list.
For example: >*todo do the laundry*

### Adding a deadline task: **deadline STRING /by TIME**
Adds a deadline to the list.
For example: >*deadline return books /by Friday 5pm*

### Adding an event task: **event STRING /from START /to END**
Adds an event to the list.
For example: >*event Brian's birthday /from Saturday 2pm /to Saturday 10pm

### Marking a task as complete: **mark NUMBER**
Marks a task as complete.
For example: >*mark 2*

### Marking a task as incomplete: **unmark NUMBER**
Marks a task as incomplete.
For example: >*unmark 2*

### Deleting a task: **delete NUMBER**
Deletes a task already on the list.
For example: >*delete 1*

### Finding a task: **find KEYWORD**
Finds any tasks that have your keyword and shows them to you. Does not include the TIME, START or END inputs from deadline or event.
For example: >*find swim*

### Listing all tasks: **list**
Lists all the tasks you have accummulated thus far, showing them in a numbered list, displaying their type (T for Todo, D for deadline and E for event) as well as their completion status.

### Closing the bot: **bye**
Causes the bot to exit.

Be aware that running the bot in the first place creates a text file, nocturne.txt, within the directory that your ip.jar file is placed in. This holds all of the tasks that you have inputted, which Nocturne will remember on startup once again. There is no need for manual saving, as Nocturne updates it every time you add, delete or mark tasks.

Thank you for using Nocturne.