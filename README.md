# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

Here are some possibly useful commands:
 --------------------------------------------
 /todo {description} - Add a todo task to your task list.
 /deadline {description} - Add a deadline task to your task list.
 /event {description} - Add an event task to your task list.
 /list - List out all the tasks in your current task list.
 /mark {numerical index} - Mark a specific task as done.
 /unmark {numerical index} - Mark a specific task as undone.
 /find {keyword} - Find tasks containing the specified keyword entered.
 /bye - Terminate the program.
 --------------------------------------------


##User Guide
Adding Todo Tasks
Use the `/todo {description}` command to add a new todo task to your task list with a brief description.
```
/todo Read a book
```

Adding Deadline Tasks
Use the `/deadline {description} /by {deadline}` command to add a new deadline task to your task list with a description and deadline.
```
/deadline Submit report /by 5pm
```

Adding Event Tasks
Use the `/event {description} /from {start} /to {end}` command to add a new event task to your task list with a description and event details.
```
/event Team meeting /from 2pm /to 3pm
```

Listing Tasks
Use the `/list` command to list out all the tasks in your task list.

Marking Tasks as Done
Use the /mark {numerical index} command to mark a specific task as done using its numerical index.
```
/mark 1
```

Marking Tasks as Undone
Use the `/unmark {numerical index}` command to mark a specific task as undone using its numerical index.
```
/unmark 1
```

Finding Tasks
Use the `/find {keyword}` command to find tasks in your task list that contain a specific keyword.
```
/find book
```

Exiting the Program
Use the `/bye` command to terminate the program.
