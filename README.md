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
  ![image](https://github.com/ThawTunZan/ip/assets/110762796/16817972-5027-4d10-a020-99188c037a1f)

### Features
#### Adding task:
1. Todo: todo <br>
     Format: todo NAME <br>
     Example: todo sleep. <br>
2. Deadline: deadline <br>
     Format: deadline NAME /by DUE_DATE DUE_TIME
     DUE_DATE is in mm-dd-yy (e.g. 01-04-2024 is displayed as Jan 4 2024. Note cannot replace the "-" with "/").
     DUE_TIME is in HHMM (e.g. 2359 is displayed as 11:59pm).
     Example deadline submit assignment /by 01-04-2024 2359.
4. Event: event
     Format: event NAME /from START_DATE START_TIME /to END_DATE END_TIME
     START_DATE and END_DATE is in mm-dd-yy (e.g. 01-04-2024 is displayed as Jan 4 2024. Note cannot replace the "-" with "/").
     START_TIME and END_TIME is in HHMM (e.g. 2359 is displayed as 11:59pm).
     Example: event CG2023 exam /from 01-01-2100 1500 /to 01-01-2100 1700 .

#### Deleting task:
Delete: delete
     Format: delete NUMBER
     NUMBER is the task index or the number that the task is labelled with which can be seen when using the list function.
     Example: delete 2 (it will delete the second tasks in the list and shift all the tasks at the bottom up).

#### Looking at the tasks
List: list 
     Format: list
     Will list out all the added tasks.

#### Finding specific task:
Find: find
     Format: find KEYWORD
     Will find the task in the task list that contains the particular KEYWORD.
     Example: find homework.

#### Marking certain task as complete
1. Mark: mark
     Format: mark NUMBER
     Will mark the task with the associated number as done.
     Example: mark 2.
     Will mark the second task in the task list as done (for example finish homework) as done which will be displayed with a cross when the user lists out the tasklist in the future.

2. Unmark: unmark
     Format: unmark NUMBER
     Will unmark the task with the associated number as done.
     Example: unmark 2.
     Will unmark the second task in the task list as not done (for example finish homework) as done which will be displayed without a cross when the user lists out the tasklist in the future.

#### Leaving the program
Exiting: bye
     Format: bye
     Will terminate the program but saves the file before it does that.
#### Installation

1. Download the latest release of ThawBot from the [releases page](
2. Extract the downloaded zip file to a folder on your computer.
3. Open a terminal or command prompt and navigate to the folder where you extracted the zip file.
4. Run the following command to start ThawBot:

```bash
java -jar ThawBot.jar
```
