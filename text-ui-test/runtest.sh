#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi


# create data directory if it doesn't exist
if [ ! -d "../data" ]
then
    mkdir ../data
fi

# if previous copy of EXPECTED_FILE.logs exists, then create new copy it
if [ -e "../data/mimi.logs" ]
then
    rm ../data/mimi.logs
fi

if [ ! -e "../data/mimi.logs" ]
then
    touch ../data/mimi.logs
fi


# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi


# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java/mimi -Xlint:none -d ../bin ../src/main/java/mimi/classes/*.java  ../src/main/java/mimi/helper/*.java   ../src/main/java/mimi/*.java ../src/main/java/mimi/exceptions/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi
cd ../
# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath bin mimi/Duke < text-ui-test/input.txt > text-ui-test/ACTUAL.TXT

cd text-ui-test
# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    diff ../data/mimi.logs EXPECTED_FILE.logs
    if [ $? -eq 0 ]
    then
        echo "Test result: PASSED"
        exit 0
    else
        echo "Test result: FAILED"
        exit 1
    fi
else
    echo "Test result: FAILED"
    exit 1
fi

