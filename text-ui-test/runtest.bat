@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\hachi\*.java ..\src\main\java\hachi\data\task\*.java ..\src\main\java\hachi\data\*.java ..\src\main\java\hachi\parser\*.java ..\src\main\java\hachi\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from test.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin hachi.Hachi < test.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT