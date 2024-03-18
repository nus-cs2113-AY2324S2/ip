@ECHO OFF

REM create bin directory if it doesn't exist
if not exist C:\Users\ansel\bin mkdir C:\Users\ansel\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp "C:\Users\ansel\Desktop\CS2113\ip\src\main\java\seedu" -Xlint:none -d C:\Users\ansel\bin C:\Users\ansel\Desktop\CS2113\ip\src\main\java\seedu\laika\Laika.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath C:\Users\ansel\bin Laika < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
